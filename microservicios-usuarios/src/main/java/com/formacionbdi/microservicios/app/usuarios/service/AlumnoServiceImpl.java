package com.formacionbdi.microservicios.app.usuarios.service;


import org.springframework.stereotype.Service;

import com.formacionbdi.microservicios.app.usuarios.repository.IAlumnoRepository;
import com.formacionbdi.microservicios.commons.alumnos.entity.Alumno;
import com.formacionbdi.microservicios.commons.services.CommonServiceImpl;

@Service
public class AlumnoServiceImpl extends CommonServiceImpl<Alumno, IAlumnoRepository> implements IAlumnoService {


}
