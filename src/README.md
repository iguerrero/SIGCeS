# Sistema Integral de Gesti�n para Centros de Salud (SIGCeS)

Por alumnos de la c�tedra **Programaci�n Orientada a Objetos** de la **Universidad Empresarial Siglo 21**. <br>
A�o: **2022**.<br>

Integrantes del grupo: **Ignacio Guerrero, Ignacio Gonz�lez Dapello, Rodrigo Lema, Facundo Peral** y **Alberto Aguirre**.

Proyecto: __*Sistema Integral de Gesti�n para Centros de Salud (SIGCeS)*__

### Descripci�n
En una primera etapa, el proyecto pretende crear un Sistema de Gesti�n que permita incorporar nuevos pacientes, reconocer pacientes existentes,
que estos puedan autogestionar sus turnos, y que el m�dico pueda acceder a su historia cl�nica.
Todo esto, a trav�s de la l�nea de comandos.

Los m�todos son sencillos. Algunas de las limitaciones de la aplicaci�n son:
- En lugar de una base de datos SQL, se utiliza una planilla de c�lculos CSV, y archivos JSON para alojar
las historias cl�nicas.
- El usuario de la aplicaci�n es el propio DNI.
- El campo clave, en los archivos .CSV, **no est� encriptado**.

T�ngase en cuenta que este es un proyecto de �ndole *educativa* y, por lo tanto, no es apto para entornos profesionales.

### Acerca del TP N� 2

Esta etapa es INDIVIDUAL.
Alumno: **Ignacio Guerrero**

En lo que respecta a la __Consigna__, el presente trabajo codifica las clases que se corresponden a las entidades identificadas en la primera instancia del trabajo pr�ctico.
Tambi�n implementa el c�digo Java que da resoluci�n a la situaci�n problem�tica, aunque de forma parcial.

- Para ingresar como <span style="color:red">M�dico</span>, utilizar el DNI 32123123 y la CLAVE 1234.
- **Para ingresar como Admin, utilizar el DNI 38111222 y la CLAVE 1234.**
- Las opciones de men� en <span style="color:red">rojo</span> a�n no fueron implementadas.
- Se adjunta una copia de las planillas admins.csv y medicos.csv, en caso de p�rdida de informaci�n.
- A�n no est�n implementadas las opciones de "volver al men� anterior", por lo que al llegar al final del men�, el programa termina.
- Se espera poder migrar la aplicaci�n de archivos CSV a una base de datos SQL para el pr�ximo TP.

### Acerca del TP N� 3

Esta etapa es GRUPAL

Para esta etapa hemos optado en implementar una base de datos **SQLite** junto con un patr�n de dise�o **DAO** (Data Access Object)

- La base de datos **sigces.db** se encuentra dentro de la carpeta **src**.
- Se adjunta el driver **sqlite-jdbc-3.7.2.jar**.
- Se adjunta la herramienta **SQLiteStudio** para trabajar con la base de datos fuera de la aplicaci�n.