# Sistema Integral de Gestión para Centros de Salud (SIGCeS)

Por alumnos de la cátedra **Programación Orientada a Objetos** de la **Universidad Empresarial Siglo 21**. <br>
Año: **2022**.<br>

Integrantes del grupo: **Ignacio Guerrero, Ignacio González Dapello, Rodrigo Lema, Facundo Peral** y **Alberto Aguirre**.

Proyecto: __*Sistema Integral de Gestión para Centros de Salud (SIGCeS)*__

### Descripción
En una primera etapa, el proyecto pretende crear un Sistema de Gestión que permita incorporar nuevos pacientes, reconocer pacientes existentes,
que estos puedan autogestionar sus turnos, y que el médico pueda acceder a su historia clínica.
Todo esto, a través de la línea de comandos.

Los métodos son sencillos. Algunas de las limitaciones de la aplicación son:
- En lugar de una base de datos SQL, se utiliza una planilla de cálculos CSV, y archivos JSON para alojar
las historias clínicas.
- El usuario de la aplicación es el propio DNI.
- El campo clave, en los archivos .CSV, **no está encriptado**.

Téngase en cuenta que este es un proyecto de índole *educativa* y, por lo tanto, no es apto para entornos profesionales.

### Acerca del TP N° 2

En lo que respecta a la __Consigna__, 

- Para ingresar como Médico, utilizar el DNI 32123123 y la CLAVE 1234.
- Para ingresar como Admin, utilizar el DNI 38111222 y la CLAVE 1234.
- Las opciones de menú en rojo aún no fueron implementadas.