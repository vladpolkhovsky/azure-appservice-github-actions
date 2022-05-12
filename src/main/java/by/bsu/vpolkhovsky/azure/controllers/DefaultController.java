package by.bsu.vpolkhovsky.azure.controllers;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/api")
public class DefaultController {

    public static final String DEFAULT_MESSAGE_VALUE = "undefined";

    public static final String RESPONSE_FORMAT = "msg=%s";

    public static String format(String format, Object... args) {
        return String.format(format, args);
    }

    @GetMapping(value = "/message")
    HttpEntity<String> getMessage(@RequestParam(value = "msg", required = false) Optional<String> msg) {
        return new HttpEntity<>(msg.map(s -> format(RESPONSE_FORMAT, s)).orElse(DEFAULT_MESSAGE_VALUE));
    }

}
