## 📋 Descripción del Proyecto
Microservicio REST desarrollado con **Java 17** y **Spring Boot** que proporciona tasas de cambio oficiales del Banco Central de Venezuela (BCV) para dólares y euros. El sistema realiza web scraping automático cada 4 horas para mantener actualizada la información.

## 🎯 Objetivo
Proveer una API integrable con cualquier sistema para cálculos de servicios, productos o facturación utilizando las tasas de cambio oficiales emitidas por el BCV.

## 🚀 Características Principales
* **Web Scraping Automático:** Obtención de tasas desde el BCV  en horarios programados.
* **API REST:** Endpoints para consulta de tasas de cambio.
* **Persistencia:** Almacenamiento en base de datos MySQL.
* **Arquitectura Limpia:** Separación de responsabilidades con schedulers.

## 🛠 Tecnologías y Dependencias

### Versiones Principales
* **Java:** 17
* **Spring Boot:** 3.5.7
* **MySQL:** Conector Java

### Dependencias Clave

| Dependencia | Versión | Propósito |
| :--- | :--- | :--- |
| **Spring Boot Starter Web** | 3.5.7 | API REST |
| **Spring Boot Data JPA** | 3.5.7 | Persistencia |
| **Selenium Java** | 4.38.0 | Web Scraping |
| **MapStruct** | 1.5.5.Final | Mapeo DTOs |
| **Lombok** | - | Reducción de código boilerplate |
| **WebDriverManager** | 5.9.2 | Gestión automática de drivers |

## 📁 Estructura del Proyecto

```text
src/main/java/com/leopoldo/bcv/
├── controllers/        # Controladores REST
├── dtos/               # Objetos de Transferencia de Datos
├── exceptions/         # Manejo de excepciones
├── models/             # Entidades JPA
├── repositories/       # Interfaces de repositorio
├── schedulers/         # Tareas programadas (scraping)
├── mappers/            # Mapeos de los Dtos (MapStruct)
└── services/           # Lógica de negocio
