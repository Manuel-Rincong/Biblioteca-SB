# Proyecto Biblioteca

Integrantes: Kiven Steve Pirajan Diaz y Manuel Alejandro Rincon Gomez

## 1. Alcance

Vamos a desarrollar un sistema en Java para gestionar el préstamo de libros y equipos de una biblioteca. Permitirá registrar estudiantes y docentes, consultar los recursos disponibles y llevar el control de los préstamos, las renovaciones y las devoluciones; también permitirá aplicar reglas de préstamo diferentes según el tipo de usuario y de recurso, calcular multas por retraso y manejar una lista de espera para avisar al siguiente usuario cuando un recurso esté disponible. Los avisos se mostrarán dentro del programa. Se podrán consultar los préstamos activos, los vencidos y el historial de cada usuario. El sistema funcionará por consola y mediante una interfaz gráfica.

Dejaremos fuera los usuarios externos y los recursos distintos de libros y equipos para mantener el proyecto manejable dentro del tiempo disponible. Tampoco incluiremos el reporte de los recursos más solicitados, porque nos concentraremos en el registro y control de los préstamos y las devoluciones. No realizaremos cobros de multas ni enviaremos correos o mensajes reales, ya que estas funciones requieren servicios adicionales. Los datos se guardarán en memoria mientras el programa esté abierto; no utilizaremos una base de datos.

Las tres áreas principales que pueden cambiar son las reglas de préstamo, las políticas de multas y los avisos de disponibilidad. Por ejemplo, podrían cambiar los días de préstamo para los docentes, la forma de calcular una multa o el medio utilizado para avisar a un usuario.

## 2. Requisitos

### 2.1 Requisitos funcionales

- El sistema debe permitir registrar libros y equipos con su identificador, nombre y tipo.
- El sistema debe permitir consultar el catálogo y la disponibilidad de los recursos.
- El sistema debe permitir registrar usuarios e identificar si son estudiantes o docentes.
- El sistema debe registrar los préstamos con el usuario, el recurso, la fecha de préstamo y la fecha límite de devolución.
- El sistema debe verificar la disponibilidad del recurso y el límite de préstamos del usuario antes de autorizar un préstamo.
- El sistema debe aplicar los plazos, los límites y las renovaciones correspondientes al tipo de usuario y de recurso.
- El sistema debe permitir renovar un préstamo cuando no esté vencido, no haya usuarios en espera y no se haya alcanzado el límite de renovaciones.
- El sistema debe registrar las devoluciones y actualizar la disponibilidad de los recursos.
- El sistema debe calcular las multas por retraso según los días de demora
- El sistema debe permitir que los usuarios se inscriban y se retiren de la lista de espera de un recurso no disponible.
- El sistema debe avisar dentro del programa al siguiente usuario de la lista de espera cuando se devuelva el recurso, respetando el orden de inscripción.
- El sistema debe dar prioridad al usuario avisado para solicitar el préstamo del recurso disponible.
- El sistema debe permitir consultar los préstamos activos, los vencidos y el historial de préstamos de cada usuario.
- El sistema debe mantener un catálogo único y actualizado durante la ejecución del programa.

### 2.2 Requisitos no funcionales

- El sistema debe estar desarrollado en Java
- El sistema debe contar con una versión por consola y una interfaz gráfica que compartan la misma lógica de negocio
- El sistema debe presentar opciones claras y mensajes en español para facilitar su uso.
- El sistema debe responder en menos de 5 segundos en las operaciones de registro, consulta, préstamo y devolución 
- El sistema debe manejar los datos inválidos mediante mensajes de error sin cerrar inesperadamente el programa
- El sistema debe mantener su código organizado en paquetes que separen las interfaces de la lógica de negocio
- El sistema debe permitir incorporar nuevas reglas de préstamo, políticas de multas y formas de aviso sin reescribir el proceso completo de préstamo y devolución.

## Repositorio de GitHub

https://github.com/Manuel-Rincong/Biblioteca-SB

