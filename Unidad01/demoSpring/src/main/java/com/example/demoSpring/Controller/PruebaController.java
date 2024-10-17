package com.example.demoSpring.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PruebaController {
    @GetMapping("/prueba/{nombre}")
    public String prueba(@PathVariable String nombre) {
        return "Hola " + nombre;
    }
}
