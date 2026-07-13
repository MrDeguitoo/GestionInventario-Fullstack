# Gestion Inventario
Estudiantes: Diego Uribe y Job Arjel
- Sistema de Gestión de Inventario - Arquitectura de Microservicios

Este proyecto es un sistema robusto de Gestión de Inventario construido bajo una arquitectura de Microservicios utilizando Spring Boot y Spring Cloud. Está diseñado para ser escalable, seguro y fácil de mantener.

- Arquitectura del Sistema

El ecosistema está compuesto por 10 microservicios independientes que se comunican de forma centralizada a través de un API Gateway. Todo el tráfico externo es interceptado y validado mediante JSON Web Tokens (JWT).

- Microservicios Desarrollados:

API Gateway (ms-api-gateway - Puerto: 8080): Enrutador principal y filtro de seguridad (JWT).

Usuario Service (usuario-service): Gestión de usuarios, roles y autenticación.

Bodega Service (bodega-service): Administración de sucursales y bodegas físicas.

Producto Service (producto-service): Catálogo general de productos.

Proveedor Service (proveedor-service): Gestión de proveedores externos.

Inventario Service (inventario-service): Control exacto de stock por bodega y producto.

Compra Service (compra-service): Órdenes de abastecimiento a proveedores.

Despacho Service (despacho-service): Control de salidas y envíos.

Notificación Service (notificacion-service): Sistema de alertas (ej. stock bajo).

Reporte Service (reporte-service): Generación de métricas e informes.

Auditoria Service (auditoria-service): Trazabilidad de ajustes manuales.

- Tecnologías Utilizadas

Backend: Java 21, Spring Boot 3.2.x, Spring Cloud Gateway MVC.

Seguridad: Spring Security, JWT (Stateless Auth).

Base de Datos: MySQL (Un esquema/base de datos por microservicio).

Migraciones: Flyway DB (Control de versiones de base de datos).

Resiliencia: Spring Cloud Circuit Breaker (Resilience4j).

Documentación/Pruebas: Archivos .http nativos de IntelliJ IDEA / VS Code.

Contenedores: Docker & Docker Compose.

- Requisitos Previos

Para ejecutar este proyecto de forma local necesitas:

Java 21

Maven

Docker y Docker Compose

IntelliJ IDEA (Recomendado) o VS Code.

- Cómo levantar el proyecto

1. Levantar la Infraestructura (Bases de Datos)
   Las bases de datos están contenerizadas. En la raíz del proyecto, ejecuta:

docker-compose up -d


2. Ejecutar los Microservicios
   Puedes iniciar los microservicios desde tu IDE. El orden recomendado de inicialización es:

ms-api-gateway

usuario-service

Resto de microservicios (Bodega, Producto, etc.)

Nota: Flyway se encargará automáticamente de crear las tablas necesarias en MySQL al iniciar cada microservicio.

- Pruebas y Documentación de la API

La documentación de los endpoints y los payloads de ejemplo no requieren Postman. En la raíz del proyecto se encuentra el archivo coleccion-pruebas.http.

¿Cómo usarlo?

Abre el archivo en IntelliJ IDEA.

Ejecuta la petición 1.1 Registrar Administrador (solo la primera vez).

Ejecuta la petición 1.2 Hacer Login.

Copia el token JWT de la respuesta y pégalo en la variable @token en la parte superior del archivo.

