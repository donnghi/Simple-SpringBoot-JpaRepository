package com.donnghi.demoSecuritySQL.controller;


import com.donnghi.demoSecuritySQL.Entities.Person;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SimpleController
{
    private List<Person> person;

    public SimpleController() {
        this.person = new ArrayList<>();
        this.person.add(new Person(1L, "Huu-Nghi", "Nguyen"));
        this.person.add(new Person(2L, "Bao-Quoc", "Nguyen"));
        this.person.add(new Person(3L, "Ngoc-Phuc", "Dang"));
    }

    @RequestMapping("/")
    public String index() {
        return "Congratulation from SimpleController.java";
    }

    @GetMapping("/api/v1/people")
    public List<Person> getRequestAllPerson() {
        return this.person;
    }

    @GetMapping("/api/v{version}/people/{id}")
    public Person getRequestPerson(@PathVariable String id, @PathVariable String version) {
        int personId = Integer.parseInt(id);
        int versionId = Integer.parseInt(version);
        if (personId < 0 || this.person.size() <= personId)
            return this.person.get(0);
        if (versionId != 1)
            return new Person(404L, "Hello", "World");
        return this.person.get(personId);
    }

}
