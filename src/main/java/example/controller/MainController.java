package example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import example.domain.Entity;
import example.service.SomeService;

import java.util.UUID;

/**
 * The Controller handles incoming requests, validates parameters and
 * makes a call to a service bean executing some business logic.
 */
@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private SomeService someService;

    @RequestMapping
    @ResponseBody
    public String getContent() {
        StringBuilder s = new StringBuilder();
        for (Entity entity : someService.getEntities()) {
            s.append(entity.toString());
        }
        return "content of database: " + s.toString();
    }

    @RequestMapping("/add")
    @ResponseBody
    public String addContent() {
        return "stored: " + someService.saveEntity("random value: " + UUID.randomUUID().toString());

    }

}
