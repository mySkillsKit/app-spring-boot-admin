package com.mycleancode.app.rest;

import com.mycleancode.app.service.HelloService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class HelloController {
    private final HelloService helloService;

    @GetMapping
    public String hello() {
        return helloService.hello();
    }
}
