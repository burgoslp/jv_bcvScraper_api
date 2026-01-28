## 📋 Descripción del Proyecto
Microservicio REST desarrollado con **Java 17** y **Spring Boot** que proporciona tasas de cambio oficiales del Banco Central de Venezuela (BCV) para dólares y euros. El sistema realiza web scraping automático separado de las peticiones http cada 4 horas para mantener actualizada la información.

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
```

## Diagrama de la base de datos:
<img width="806" height="619" alt="Untitled" src="https://github.com/user-attachments/assets/c814463c-91fb-4b16-a898-a2ab958885a8" />


## 📜 Listado de endpoints 
la api cuenta con multiples rutas que nos permite la consulta de las diferentes tasas y forzar el scraping.

<a name="indice"></a>
## 📑 Índice de Endpoints

- 📊 [1. Listar todas las tasas](#1-listar-todas-las-tasas)
- 💵 [2. Listar tasas Por Nombre](#2-listar-tasas-por-nombre)
- 📅 [3. Consultar Histórico por rango de Fecha y nombre de moneda](#3-listar-historico-por-rango-fecha-nombre)
- 🔄 [4. Forzar scraping Manual](#4-forzar-scraping-manual)


<a name="1-listar-todas-las-tasas"></a>
## 📊 1. Listar todas las tasas [🔙](#indice)

**Método:** `GET`  
**Endpoint:** `/rateScraping/v1/exchanges`
**Validación:** `SIN AUTENTICACIÓN` 

#### 📝 Descripción
Listas todas las tasas de cambio existentes, la api ya tiene predetarminada la tasa de cambio del BCV

#### 📥 Request Body
```json
{
    "code": 200,
    "message": "OK",
    "data": [
        {
            "coinName": "Dolares",
            "rateName": "BCV",
            "value": 358.92470000,
            "previousValue": 0E-8,
            "updateAt": "2026-01-27T15:14:12"
        },
        {
            "coinName": "Euros",
            "rateName": "BCV",
            "value": 426.18718878,
            "previousValue": 0E-8,
            "updateAt": "2026-01-27T15:14:12"
        }
    ]
}
```


<a name="3-listar-historico-por-fecha"></a>
## 💵 2. Listar tasas Por Nombre [🔙](#indice)

**Método:** `GET`  
**Endpoint:** `/rateScraping/v1/exchanges/{coinName}`
**Validación:** `SIN AUTENTICACIÓN` 

#### 📝 Descripción
Lista todas las tasas de cambio que contengan el nombre de la moneda, predertarminadamente el sistema solo tiene dos monedas (euros y dolares)

#### 📥 Request Body
```json
{
    "code": 200,
    "message": "OK",
    "data": [
        {
            "coinName": "Dolares",
            "rateName": "BCV",
            "value": 358.92470000,
            "previousValue": 0E-8,
            "updateAt": "2026-01-27T15:14:12"
        }
    ]
}
```
<a name="3-listar-historico-por-rango-fecha-nombre"></a>
## 📅 3. Consultar Histórico por rango de Fecha y nombre de moneda [🔙](#indice)

**Método:** `GET`  
**Endpoint:** `/rateScraping/history/coin/{coinName}/between/{start}/{end}`
**Validación:** `SIN AUTENTICACIÓN` 

#### 📝 Descripción
Lista todas las tasas de cambio del historico que contengan el nombre de la moneda en un rago de fecha, el sistema solo cuenta con dos monedas (euros y dolares)
la fecha debe tener el formato (yyyy-mm-dd).

#### 📝 Ejemplo de consulta
**Endpoint:** `/rateScraping/history/coin/dolares/between/2026-01-27/2026-01-27`

#### 📥 Request Body
```json
{
    "code": 200,
    "message": "OK",
    "data": [
        {
            "id": 1,
            "rateName": "BCV",
            "coinName": "Dolares",
            "value": 0E-8,
            "previousValue": 0E-8,
            "createAt": "2026-01-27T14:50:45"
        }
    ]
}
```
<a name="4-forzar-scraping-manual"></a>
## 📅 4. Forzar scraping Manual [🔙](#indice)

**Método:** `GET`  
**Endpoint:** `/rateScraping/v1/exchanges/force`
**Validación:** `SIN AUTENTICACIÓN` 

#### 📝 Descripción
Fuerza el scraping para obtener los datos de la página oficial del bcv sin necesidad de espera los horarios programados.

#### 📥 Request Body
```json
{
    "code": 200,
    "message": "Scraping forzado con exito.",
    "data": null
}
```
