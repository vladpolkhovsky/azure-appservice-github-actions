package by.bsu.vpolkhovsky.azure;

import by.bsu.vpolkhovsky.azure.controllers.DefaultController;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AzureAppserviceGithubActionsApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private DefaultController defaultController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Order(1)
    @DisplayName("Context loading")
    void contextLoads() {
        assertThat(defaultController).isNotNull();
    }

    private String getUrl(String mapping) {
        return String.format("http://localhost:%d%s", port, mapping);
    }

    private <T> T connect(String mapping, Class<T> expectedClass) {
        return this.restTemplate.getForObject(getUrl(mapping), expectedClass);
    }

    @Test
    @Order(2)
    @DisplayName("Test default message")
    void defaultControllerOutput() {
        assertThat(connect("/api/message", String.class)).isEqualTo("undefined");
    }


    @Test
    @Order(3)
    @DisplayName("Test custom message")
    void customControllerOutput() {
        assertThat(connect("/api/message?msg=123", String.class)).isEqualTo("msg=123");
        assertThat(connect("/api/message?msg=333", String.class)).isEqualTo("msg=333");
        assertThat(connect("/api/message?msg=Minsk", String.class)).isEqualTo("msg=Minsk");
    }

}

