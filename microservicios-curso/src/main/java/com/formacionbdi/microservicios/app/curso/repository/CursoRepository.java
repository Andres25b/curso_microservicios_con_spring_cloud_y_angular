package com.formacionbdi.microservicios.app.curso.repository;

import org.springframework.data.repository.CrudRepository;

import com.formacionbdi.microservicios.app.curso.entity.Curso;

public interface CursoRepository extends CrudRepository<Curso, Long> {

}
