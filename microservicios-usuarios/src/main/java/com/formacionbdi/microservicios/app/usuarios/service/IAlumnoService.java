package com.formacionbdi.microservicios.app.usuarios.service;

import java.util.Optional;

import com.formacionbdi.microservicios.app.usuarios.entity.Alumno;

public interface IAlumnoService {
	
	public Iterable<Alumno> findAll();
	
	public Optional<Alumno> findById(Long id);
	
	public Alumno save(Alumno alumno);
	
	public void deleteById(Long id);
	
}
