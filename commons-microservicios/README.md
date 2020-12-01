# Libreria Common

**Descripción**

<div class=text-justify>
    En este repositorio, te enseño a como crear una libreria para reutilización de codigo.
</div>

## Indice

1. [Dependencias Requeridas](#dependencias-requeridas)
2. [Modificar el pom y eliminamos la clase main](#modificar-el-pom-y-eliminamos-la-clase-main)
3. [Creacion de la interfaz ICommonService](#creacion-de-la-interfaz-icommonservice)
4. [Creacion de la clase CommonServiceImpl](#creacion-de-la-clase-commonserviceimpl)
5. [Creacion del controlador CommonController](#creacion-del-controlador-commoncontroller)

---

### Dependencias Requeridas

<div class=text-justify>
    <ul>
        <li>Spring Web</li>
        <li>Spring Data JPA</li>
    </ul>
</div>

---

### Modificar el pom y eliminamos la clase main

<div class=text-justify>
    Para que sea un proyecto de libreria tenemos que eliminar el contenido de las etiquetas '&lt;build&gt;' dentro del archivo pom.xml.
</div>
<div class=text-justify>
    Especificamente eliminamos todo esto:
</div>

    <build>
            <plugins>
                <plugin>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-maven-plugin</artifactId>
                </plugin>
            </plugins>
    </build>

---

### Creacion de la interfaz ICommonService

<div class=text-justify>
    Dentro del paquete 'service' crearemos una interfaz llamada ICommonService.
</div>
<div class=text-justify>
    Estructura de la clase ICommonService:
</div>

    package com.formacionbdi.microservicios.commons.services;

    import java.util.Optional;

    public interface ICommonService<E> {

        public Iterable<E> findAll();

        public Optional<E> findById(Long id);

        public E save(E entity);

        public void deleteById(Long id);

    }

---

### Creacion de la clase CommonServiceImpl

<div class=text-justify>
    Dentro del paquete 'service' crearemos una clase llamada CommonServiceImpl.
</div>
<div class=text-justify>
    Estructura de la clase CommonServiceImpl:
</div>

package com.formacionbdi.microservicios.commons.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

    public class CommonServiceImpl<E, R extends CrudRepository<E, Long>> implements ICommonService<E> {

        @Autowired
        protected R repository;

        @Override
        @Transactional(readOnly = true)
        public Iterable<E> findAll() {
            return repository.findAll();
        }

        @Override
        @Transactional(readOnly = true)
        public Optional<E> findById(Long id) {
            return repository.findById(id);
        }

        @Override
        @Transactional
        public E save(E entity) {
            return repository.save(entity);
        }

        @Override
        @Transactional
        public void deleteById(Long id) {
            repository.deleteById(id);
        }

    }

---

### Creacion del controlador CommonController

<div class=text-justify>
    Dentro del paquete 'controllers' crearemos una clase llamada CommonController.
</div>
<div class=text-justify>
    Estructura de la clase CommonController:
</div>

    package com.formacionbdi.microservicios.commons.controllers;

    import java.util.Optional;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.DeleteMapping;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.PathVariable;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestBody;

    import com.formacionbdi.microservicios.commons.services.ICommonService;

    public class CommonController<E, S extends ICommonService<E>> {

        @Autowired
        protected S service;

        @GetMapping
        public ResponseEntity<?> listar() {
            return ResponseEntity.ok().body(service.findAll());
        }

        @GetMapping("/{id}")
        public ResponseEntity<?> ver(@PathVariable Long id) {
            Optional<E> o = service.findById(id);

            if (o.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(o.get());
        }

        @PostMapping
        public ResponseEntity<?> crear(@RequestBody E entity) {
            E entityDB = service.save(entity);
            return ResponseEntity.status(HttpStatus.CREATED).body(entityDB);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<?> eliminar(@PathVariable Long id) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }
    }
