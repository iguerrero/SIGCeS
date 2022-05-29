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

Esta etapa es INDIVIDUAL.
Alumno: **Ignacio Guerrero**

En lo que respecta a la __Consigna__, el presente trabajo codifica las clases que se corresponden a las entidades identificadas en la primera instancia del trabajo práctico.
También implementa el código Java que da resolución a la situación problemática, aunque de forma parcial.

- Para ingresar como <span style="color:red">Médico</span>, utilizar el DNI 32123123 y la CLAVE 1234.
- **Para ingresar como Admin, utilizar el DNI 38111222 y la CLAVE 1234.**
- Las opciones de menú en <span style="color:red">rojo</span> aún no fueron implementadas.
- Se adjunta una copia de las planillas admins.csv y medicos.csv, en caso de pérdida de información.
- Aún no están implementadas las opciones de "volver al menú anterior", por lo que al llegar al final del menú, el programa termina.
- Se espera poder migrar la aplicación de archivos CSV a una base de datos SQL para el próximo TP.

### Acerca del TP N° 3

Esta etapa es GRUPAL

Para esta etapa hemos optado en implementar una base de datos **SQLite** junto con un patrón de diseño **DAO** (Data Access Object)

- La base de datos **sigces.db** se encuentra dentro de la carpeta **src**.
- Se adjunta el driver **sqlite-jdbc-3.7.2.jar**.
- Se adjunta la herramienta **SQLiteStudio** para trabajar con la base de datos fuera de la aplicación.