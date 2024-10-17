package com.example.demoSpring.Controller;

import com.example.demoSpring.Model.Grupo;
import com.example.demoSpring.Repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupos")
public class GrupoController {
    @Autowired
    private GrupoRepository grupoRepository;

    @GetMapping
    public List<Grupo> getAllGrupos() {
        return grupoRepository.findAll();
    }
    @PostMapping
    public String createGroup(@RequestBody Grupo grupo) {
        int n=grupoRepository.save(grupo);
        if (n==1) {
            return "Grupo creado";
        } else {
            return "Error al crear el grupo";
        }
    }
}
