# Microservicio Eureka

**¿Que es Eureka?**

<div class=text-justify>
    Eureka es un servidor para el registro y localización de microservicios, balanceo de carga y tolerancia a fallos. Su función radica en registrar las diferentes instancias de microservicios existentes, su localización, estado, metadatos etc.
</div>

## Indice

1. [Dependencias Requeridas](#habilitar-eureka-server)
2. [Habilitar Eureka Server](#habilitar-eureka-server)
3. [Impotar modulo JAXB](#impotar-modulo-jaxb)
4. [Configurar archivo application](#configurar-archivo-application)

---

### Dependencias Requeridas

<div class=text-justify>
    <ul>
        <li>Eureka Server</li>
        <li>Modulo JAXB</li>
    </ul>
</div>

---

### Habilitar Eureka Server

<div class=text-justify>
    Para habilitar en nuestro proyecto la dependencia de Eureka Server es necesario:
</div>

- Agregar la siguiente anotación en nuesta clase principal.

        @EnableEurekaServer

---

### Impotar modulo JAXB

<div class=text-justify>
    Debe de incluir la siguiente dependencia dentro del archivo pom.xml
</div>

    <dependency>
    	<groupId>org.glassfish.jaxb</groupId>
    	<artifactId>jaxb-runtime</artifactId>
    </dependency>

---

### Configurar archivo application

<div class=text-justify>
    Se debe de configurar nuestro servidor Eureka de la siguiente forma:
</div>

    # Nombre de la aplicación
    spring.application.name=microservicio-eureka-server

    # Puerto de la aplicación
    server.port=8761

    # Desabilitar el auto registro
    eureka.client.register-with-eureka=false
    eureka.client.fetch-registry=false

---
