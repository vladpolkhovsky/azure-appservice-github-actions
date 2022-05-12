package by.bsu.vpolkhovsky.azure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class AzureAppserviceGithubActionsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AzureAppserviceGithubActionsApplication.class, args);
    }

}
