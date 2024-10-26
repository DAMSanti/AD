package com.example.demoSpring.Controller;

import com.example.demoSpring.Model.Grupo;
import com.example.demoSpring.Repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupos")
public class GrupoController {
    @Autowired
    private GrupoRepository grupoRepository;
/*
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
    }*/
    @GetMapping
    public ResponseEntity<?> getAllGroupos() {
        List<Grupo> grupos = grupoRepository.findAll();
        if (grupos.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(grupos);
        }
    }
    @PostMapping
    public ResponseEntity<?> createGroup(@RequestBody Grupo grupo) {
        if (grupoRepository.save(grupo)==1) {
            return ResponseEntity.status(HttpStatus.CREATED).body(grupo);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el grupo");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getGroupById(@PathVariable int id) {
        Grupo grupo = grupoRepository.findById(id);
        if (grupo==null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(grupo);
        }
    }

    @GetMapping("/localidad/{localidad}")
    public ResponseEntity<?> getGroupByLocalidad(@PathVariable String localidad) {
        Grupo grupo = grupoRepository.findByLocalidad(localidad);
        if (grupo==null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(grupo);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateGroup(@PathVariable int id, @RequestBody Grupo grupo) {
        grupo.setCodGrupo(id);
        if (grupoRepository.update(grupo)==1) {
            return ResponseEntity.ok(grupo);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el grupo");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGroup(@PathVariable int id) {
        if (grupoRepository.deleteById(id)==1) {
            return ResponseEntity.ok("Grupo eliminado");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el grupo");
        }
    }
}
