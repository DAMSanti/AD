package com.example.demoSpring.Repository;

import com.example.demoSpring.Model.Grupo;

import java.util.List;

public interface GrupoRepository {
    List<Grupo> findAll();
    Grupo findById(int id);
    Grupo findByLocalidad(String location);
    int save(Grupo grupo);
    int update(Grupo grupo);
    int deleteById(int id);
}
