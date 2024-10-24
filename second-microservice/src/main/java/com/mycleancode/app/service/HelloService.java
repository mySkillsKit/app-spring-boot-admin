package com.mycleancode.app.service;

import com.mycleancode.app.entity.Greeting;
import com.mycleancode.app.repository.GreetingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HelloService {

    private final GreetingRepository greetingRepository;

    public String hello() {
        var hello = "Hello";
        var entity = new Greeting(hello);
        if (greetingRepository.findAll().stream().noneMatch(v -> v.getMessage().equals(hello))) {
            greetingRepository.save(entity);
        }
        return String.format("Success get from database: %s", hello);
    }
}
