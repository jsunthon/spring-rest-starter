package com.tyco.controllers;

import com.tyco.models.Greeting;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by jamessunthonlap on 6/21/17.
 */
@RestController
public class GreetingController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value = "/greeting/{name}", method = RequestMethod.GET)
    public Greeting greeting(@PathVariable("name") String name, @RequestParam(name = "lim",
            required = false, defaultValue = "3") int limit) {
        long id = counter.incrementAndGet();
        return new Greeting(id, String.format(template, name), limit);
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }
}
