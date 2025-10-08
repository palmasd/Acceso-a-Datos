# Acceso-a-Datos
# 📋 Log de Incidencias

[![Java](https://img.shields.io/badge/Java-8+-blue)](https://www.java.com/)

## Descripción

Este proyecto es un **sistema de registro de incidencias** diseñado para registrar, almacenar y consultar errores provocados manualmente por el usuario.  
Las incidencias se guardan en un fichero de texto (`.txt`) y pueden ser consultadas posteriormente por **usuario** o por **rango de fechas**.

El proyecto tiene como objetivo **practicar el patrón MVC** en Java, incorporando capas de **Servicio** y **Repositorio**, manejo de **excepciones personalizadas** y validación de datos.

---

## Tabla de Contenidos

- [Funcionalidades](#funcionalidades)  
- [Arquitectura](#arquitectura)  
- [Formato de las incidencias](#formato-de-las-incidencias)
- [Validaciones importantes](#validaciones-importantes)  
- [Tecnologías utilizadas](#tecnologías-utilizadas)  
- [Estructura de carpetas](#estructura-de-carpetas)    


---

## Funcionalidades

1. **Crear incidencia manualmente**
   - El usuario provoca la incidencia introduciendo un dato incorrecto.
   - Se valida el formato de la entrada.
   - La incidencia se guarda en un fichero de texto con formato:

     ```
     yyyy-MM-dd;HH:mm:ss;Descripción de la excepción;usuario
     ```

     **Ejemplo:**

     ```
     2025-09-18;16:01:00;excepción test 3;usuario1
     ```

2. **Buscar incidencias por usuario**
   - Permite filtrar y mostrar todas las incidencias relacionadas con un usuario específico.

3. **Buscar incidencias por rango de fecha**
   - Permite filtrar y mostrar todas las incidencias dentro de un rango de fechas.
   - Las fechas deben tener un formato válido (`yyyy-MM-dd HH:mm:ss`).
   - Incluye validación de meses, días y años bisiestos.

4. **Salir del programa**
   - Termina la ejecución de manera segura.

---

## Arquitectura

El proyecto sigue el **patrón MVC** (Modelo-Vista-Controlador) con capas adicionales:

- **Modelo (Model):**  
  Entidad `Incidencia` con atributos como `fecha`, `descripcion` y `usuario`.

- **Vista (View):**  
  Clase `Consola` para mostrar menús y mensajes al usuario, usando `Escaner` para la entrada.

- **Controlador (Controller):**  
  Gestiona la lógica del menú principal y llama a los servicios según la opción seleccionada.

- **Servicios (Service Layer):**  
  Gestiona la lógica de negocio: validación de datos, filtrado de incidencias y control de errores.

- **Repositorio (Repository Layer):**  
  Clase `ServicioFichero` para leer y escribir incidencias en el fichero de texto.

---

## Formato de las incidencias

Cada incidencia se almacena en `datos.txt` con el patrón:
yyyy-MM-dd;HH:mm:ss;Descripción;Usuario


---

## Validaciones importantes

- Las fechas introducidas deben tener **formato válido**.  
- No se permite introducir **días o meses incorrectos** (incluye años bisiestos).  
- Si el usuario introduce datos inválidos, se lanza una **ExcepciónPersonalizada** que se muestra en consola y se guarda en el log.

---

## Tecnologías utilizadas

- **Java 8+**  
- Patrones de diseño: **MVC** con capas **Servicio** y **Repositorio**  
- Persistencia en **fichero de texto** (`.txt`)  
- Manejo de **excepciones personalizadas** (`ExcepcionPersonalizada`)  

---

## Estructura de carpetas

src/
│
├─ model/ # Entidades del sistema (Incidencia)
├─view/ # Clases para mostrar menús y mensajes
├─controller/ # Controladores del flujo de la aplicación
├─service/ # Lógica de negocio
├─repository/ # Lectura/escritura de ficheros
└─utils/ # Validaciones de fechas y utilidades



