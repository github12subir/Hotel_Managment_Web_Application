package com.AirBnb.controler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/airbnb/dummy")
public class DummyController {
    @GetMapping("/getMessage")
    public String getMessage(){
        return "dummy class runs";
    }
}
