package com.example.demoSpring.Repository;

import com.example.demoSpring.Model.Cancion;

import java.util.List;

public interface CancionRepository {
    List<Cancion> findAll();
    Cancion findById(int id);
    Cancion findByTitulo(String titulo);
    int save(Cancion cancion);
    int update(Cancion cancion);
    int deleteById(int id);
}
