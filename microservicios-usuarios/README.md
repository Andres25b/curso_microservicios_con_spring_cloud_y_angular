# Microservicio Usuarios

**Descripción**

<div class=text-justify>
    En este repositorio te enseño a como crear el microservicio alumno
</div>

## Indice

1. [Dependencias Requeridas](#dependencias-requeridas)
2. [Estructura de directorios del proyecto](#estructura-de-directorios-del-proyecto)
3. [Creacion de la entity](#creacion-de-la-entity)
4. [Creacion de repository](#creacion-de-repository)
5. [Creacion de service](#creacion-de-service)
6. [Creacion de controller](#creacion-de-controller)
7. [Habilitar Eureka Discovery Cliente](#habilitar-eureka-discovery-cliente)

---

### Dependencias Requeridas

<div class=text-justify>
    <ul>
        <li>Spring Web</li>
        <li>Spring Boot DevTools</li>
        <li>Spring Data JPA</li>
        <li>MySQL Driver</li>
    </ul>
</div>

---

### Estructura de directorios del proyecto

<div class=text-justify>
    Esta sera la estructura de directorios que manejaremos a lo largo del proyecto:
</div>

    microservicios-alumno
    |-- pom.xml
    `-- src
        |-- main
        |   `-- java
        |       `-- com
        |           `--formacionbdi
        |               `--microservicios
        |                   `--app
        |                       `--usuarios
        |                          `-- controllers
        |                           `-- entity
        |                           `-- repository
        |                           `-- service
        |                           |-- MicroserviciosUsuariosApplication.java
        `-- resorces
            `--static
            `--templates
            |--application.properties

---

### Creacion de la entity

<div class=text-justify>
    Dentro del paquete 'entity' crearemos una clase llamada Alumno.
</div>
<div class=text-justify>
    Estructura de la clase Alumno:
</div>

    package com.formacionbdi.microservicios.app.usuarios.entity;

    import java.io.Serializable;
    import java.util.Date;

    import javax.persistence.Column;
    import javax.persistence.Entity;
    import javax.persistence.GeneratedValue;
    import javax.persistence.GenerationType;
    import javax.persistence.Id;
    import javax.persistence.PrePersist;
    import javax.persistence.Table;
    import javax.persistence.Temporal;
    import javax.persistence.TemporalType;

    @Entity
    @Table(name = "alumnos")
    public class Alumno implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        private String nombre;

        private String apellido;

        private String email;

        @Column(name = "create_at")
        @Temporal(TemporalType.TIMESTAMP)
        private Date createAt;

        @PrePersist
        public void prePersist() {
            this.createAt = new Date();
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getApellido() {
            return apellido;
        }

        public void setApellido(String apellido) {
            this.apellido = apellido;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Date getCreateAt() {
            return createAt;
        }

        public void setCreateAt(Date createAt) {
            this.createAt = createAt;
        }

        private static final long serialVersionUID = -1272921451330633566L;

    }

---

### Creacion de repository

<div class=text-justify>
    Dentro del paquete 'repository' crearemos una interfaz llamada IAlumnoRepository la cual implementara de la clase CrudRepository.
</div>
<div class=text-justify>
    Estructura de la interfaz IAlumnoRepository:
</div>

    package com.formacionbdi.microservicios.app.usuarios.repository;

    import org.springframework.data.repository.CrudRepository;

    import com.formacionbdi.microservicios.app.usuarios.entity.Alumno;

    public interface IAlumnoRepository extends CrudRepository<Alumno, Long> {

    }

---

### Creacion de service

<div class=text-justify>
    Dentro del paquete 'service' crearemos una interfaz llamada IAlumnoService.
</div>
<div class=text-justify>
    Estructura de la interfaz IAlumnoService:
</div>

    package com.formacionbdi.microservicios.app.usuarios.service;

    import com.formacionbdi.microservicios.app.usuarios.entity.Alumno;
    import com.formacionbdi.microservicios.commons.services.ICommonService;

    public interface IAlumnoService extends ICommonService<Alumno> {

    }

<div class=text-justify>
    Dentro del mismo paquete 'service' crearemos una clase llamada AlumnoServiceImpl que implementara de la interfaz IAlumnoService.
</div>
<div class=text-justify>
    Estructura de la clase AlumnoServiceImpl:
</div>

    package com.formacionbdi.microservicios.app.usuarios.service;


    import org.springframework.stereotype.Service;

    import com.formacionbdi.microservicios.app.usuarios.entity.Alumno;
    import com.formacionbdi.microservicios.app.usuarios.repository.IAlumnoRepository;
    import com.formacionbdi.microservicios.commons.services.CommonServiceImpl;

    @Service
    public class AlumnoServiceImpl extends CommonServiceImpl<Alumno, IAlumnoRepository> implements IAlumnoService {


    }

---

### Creacion de controller

<div class=text-justify>
    Dentro del paquete 'controllers' crearemos una clase llamada AlumnoController.
</div>
<div class=text-justify>
    Estructura de la interfaz AlumnoController:
</div>

    package com.formacionbdi.microservicios.app.usuarios.controllers;

    import java.util.Optional;

    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.PathVariable;
    import org.springframework.web.bind.annotation.PutMapping;
    import org.springframework.web.bind.annotation.RequestBody;
    import org.springframework.web.bind.annotation.RestController;

    import com.formacionbdi.microservicios.app.usuarios.entity.Alumno;
    import com.formacionbdi.microservicios.app.usuarios.service.IAlumnoService;
    import com.formacionbdi.microservicios.commons.controllers.CommonController;

    @RestController
    public class AlumnoController extends CommonController<Alumno, IAlumnoService> {

        @PutMapping("/{id}")
        public ResponseEntity<?> editar(@RequestBody Alumno alumno, @PathVariable Long id) {
            Optional<Alumno> o = service.findById(id);
            if (o.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            Alumno alumnoDb = o.get();
            alumnoDb.setNombre(alumno.getNombre());
            alumnoDb.setApellido(alumno.getApellido());
            alumnoDb.setEmail(alumno.getEmail());

            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(alumnoDb));
        }
    }

---

### Habilitar Eureka Discovery Cliente

<div class=text-justify>
    En la clase principal, agregaremos la anotación @EnableEurekaClient.
</div>

    package com.formacionbdi.microservicios.app.usuarios;

    import org.springframework.boot.SpringApplication;
    import org.springframework.boot.autoconfigure.SpringBootApplication;
    import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

    @EnableEurekaClient
    @SpringBootApplication
    public class MicroserviciosUsuariosApplication {

        public static void main(String[] args) {
            SpringApplication.run(MicroserviciosUsuariosApplication.class, args);
        }

    }

<div class=text-justify>
    Acontinuacion configuramos nuestro archivo application.properties.
</div>

    # Configurar Eureka Client
    spring.application.name=microservicio-usuarios
    server.port=${PORT:0}
    eureka.instance.instance-id=${spring.application.name}:${random.value}

    # Ruta de Ureka
    eureka.client.service-url.defaultZone=http://localhost:8761/eureka

    # Configuracion Mysql y herramientas de developer
    spring.datasource.url=jdbc:mysql://localhost:3306/db_microservicios_examenes?useSSL=false&serverTimezone=UTC&useLegacyDateTimeCode=false
    spring.datasource.username=root
    spring.datasource.password=15106077b
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
    spring.jpa.generate-ddl=true
    logging.level.org.hibernate.SQL=debug
