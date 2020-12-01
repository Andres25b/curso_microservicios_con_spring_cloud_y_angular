package com.formacionbdi.microservicios.app.curso.service;

import org.springframework.stereotype.Service;

import com.formacionbdi.microservicios.app.curso.entity.Curso;
import com.formacionbdi.microservicios.app.curso.repository.CursoRepository;
import com.formacionbdi.microservicios.commons.services.CommonServiceImpl;

@Service
public class CursoServiceImpl extends CommonServiceImpl<Curso, CursoRepository> implements ICursoService {

}
