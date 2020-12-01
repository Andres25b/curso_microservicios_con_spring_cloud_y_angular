package com.formacionbdi.microservicios.app.usuarios.repository;

import org.springframework.data.repository.CrudRepository;

import com.formacionbdi.microservicios.commons.alumnos.entity.Alumno;

public interface IAlumnoRepository extends CrudRepository<Alumno, Long> {

}
