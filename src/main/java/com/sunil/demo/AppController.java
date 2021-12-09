package com.sunil.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {
    @GetMapping("/person")
     public Person person() {
       Person person = new Person();
       person.setName("hello");
       person.setCreated(1639010825l);
       return person;
    }
}
