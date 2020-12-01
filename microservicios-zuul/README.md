# Microservicio Zuul

**Descripción**

<div class=text-justify>
    En este repositorio te enseño a como implementar un api gateway con Zuul.
</div>

## Indice

1. [Dependencias Requeridas](#dependencias-requeridas)
2. [Habilitar Zuul](#habilitar-zuul)
3. [Habilitar Eureka Client](#habilitar-eureka-client)
4. [Configurar archivo application](#habilitar-archivo-application)

---

### Dependencias Requeridas

<div class=text-justify>
    <ul>
        <li>Spring Web</li>
        <li>Spring Boot DevTools</li>
        <li>Zuul</li>
        <li>Eureka Discovery Client</li>
    </ul>
</div>

---

### Habilitar Zuul

<div class=text-justify>
    Se agrego la anotacion @EnableEurekaClient en la aplicacion Main.
</div>

    @EnableEurekaClient
    @SpringBootApplication
    public class MicroserviciosZuulApplication {

        public static void main(String[] args) {
            SpringApplication.run(MicroserviciosZuulApplication.class, args);
        }

    }

---

### Habilitar Eureka Client

<div class=text-justify>
    Se agrego la anotacion @EnableZuulProxy en la aplicacion Main.
</div>

    @EnableZuulProxy
    @EnableEurekaClient
    @SpringBootApplication
    public class MicroserviciosZuulApplication {

        public static void main(String[] args) {
            SpringApplication.run(MicroserviciosZuulApplication.class, args);
        }

    }

---

### Configurar archivo application

<div class=text-justify>
    Configuración de nuetra app.
</div>

    spring.application.name=microservicio-zuul
    server.port=8090

    eureka.client.service-url.defaultZone=http://localhost:8761/eureka

    # Enrutar nuestros servicios
    zuul.routes.usuarios.service-id=microservicio-usuarios
    zuul.routes.usuarios.path=/api/alumnos/**

    zuul.routes.cursos.service-id=microservicio-cursos
    zuul.routes.cursos.path=/api/cursos/**
