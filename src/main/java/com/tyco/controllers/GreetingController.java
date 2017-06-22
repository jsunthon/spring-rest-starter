package com.tyco.controllers;

import com.tyco.models.Greeting;
import com.tyco.repositories.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jamessunthonlap on 6/21/17.
 */
@RestController
@RequestMapping(path="greetings")
public class GreetingController {
    private static final String template = "Hello, %s!";

    @Autowired
    private GreetingRepository greetingRepository;

    /**
     * Get all the greetings
     *
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Iterable<Greeting> getGreetings() {
        return greetingRepository.findAll();
    }

    /**
     * Add a new greeting
     *
     * @param name
     * @return
     */
    @RequestMapping(value = "/{name}", method = RequestMethod.POST)
    public Greeting addGreeting(@PathVariable("name") String name) {
        Greeting greeting = new Greeting();
        greeting.setContent(String.format(template, name));
        return greetingRepository.save(greeting);
    }

    /**
     * Delete an existing greeting
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody String deleteGreeting(@PathVariable() long id) {
        greetingRepository.deleteById(id);
        return "Deleted greeting";
    }
}

