package com.example.demoSpring.Controller;

import com.example.demoSpring.Model.Cancion;
import com.example.demoSpring.Repository.CancionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cancion")
public class CancionController {
    @Autowired
    private CancionRepository cancionRepository;

    @GetMapping
    public ResponseEntity<?> getAllCanciones() {
        List<Cancion> cancion = cancionRepository.findAll();
        if (cancion.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(cancion);
        }
    }
    @PostMapping
    public ResponseEntity<?> createCancion(@RequestBody Cancion cancion) {
        if (cancionRepository.save(cancion)==1) {
            return ResponseEntity.status(HttpStatus.CREATED).body(cancion);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la cancion");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCancionById(@PathVariable int id) {
        Cancion cancion = cancionRepository.findById(id);
        if (cancion==null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(cancion);
        }
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<?> getCancionByLocalidad(@PathVariable String titulo) {
        Cancion cancion = cancionRepository.findByTitulo(titulo);
        if (cancion==null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(cancion);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateGroup(@PathVariable int id, @RequestBody Cancion cancion) {
        cancion.setNumCancion(id);
        if (cancionRepository.update(cancion)==1) {
            return ResponseEntity.ok(cancion);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar la cancion");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGroup(@PathVariable int id) {
        if (cancionRepository.deleteById(id)==1) {
            return ResponseEntity.ok("Canción eliminada");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la cancion");
        }
    }
}
