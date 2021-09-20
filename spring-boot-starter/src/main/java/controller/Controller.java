package controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/spring")
public class Controller {

    @GetMapping("/helloworld")
    public String test(@RequestParam(name = "param") String param) {
        log.info("Test log");
        return "Hello word param is " + param;
    }
}
