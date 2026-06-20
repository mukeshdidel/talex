package com.talex.talex.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PublicController {

    @GetMapping("/api/public")
    public String getPublic() {
        return "hello world";
    }
}
