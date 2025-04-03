# Microservicios con Spring Boot y Docker

Este proyecto implementa una arquitectura de microservicios con Spring Boot, Docker y Kafka.

## Tecnologías utilizadas

- **Java 17**
- **Spring Boot**
- **PostgreSQL**
- **Docker & Docker Compose**
- **Kafka**

# Configuración Inicial

Para comenzar con la configuración de la aplicación, es necesario crear un archivo `.env` con las siguientes variables de entorno:

```env
SPRING_DATASOURCE_USERNAME=homestead
SPRING_DATASOURCE_PASSWORD=secret

SPRING_DATASOURCE_URL_CLIENT=jdbc:postgresql://postgres-db:5432/client
SPRING_DATASOURCE_URL_ACCOUNT=jdbc:postgresql://postgres-db:5432/account

SPRING_KAFKA_BOOTSTRAP_SERVERS=localhost:9092

CLIENT_API_URL=http://client:8080/api/clientes
```

## Configuración y despliegue con Docker

### Levantar los servicios con Docker Compose

```sh
docker-compose up --build
```

Esto iniciará:

- **PostgreSQL** en el puerto `5432`
- **Kafka y Zookeeper**
- **Account** en el puerto `8081`
- **Client** en el puerto `8080`

### Verificar los contenedores

```sh
docker ps
```

Esto mostrará los contenedores en ejecución.