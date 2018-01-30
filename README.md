Elaborado por: Andres Felipe Gutierrez Camacho
Identificado con CC: 1019011235
----------------------------------------------

-----------------------------------------------------------------------------------
------------------------- Indicaciones Generales ----------------------------------
-----------------------------------------------------------------------------------

La aplicación se desarrollo sobre Eclipse Oxygen.2 usando JDK 8 y Maven 3.5.0
El proyecto se llama CallCenter y esta desarrollado usando Spring boot 1.5.7.RELEASE.
Esta pensanda en servicios rest stateless. La implementacion de los servicios esta
pensado para no persistir datos, lo que quiere decir que cada vez que se cierre la 
aplicación se borran los datos insertados.

La plataforma funciona creando distintos tipos de empleado y si entra una llamada y
no hay ningún empleado creado (logueado) o no hay empleados disponibles almacena la 
llamada en una lista de llamadas rechazadas. Las llamadas atendidas se guardan en 
una lista diferente. 

La aplicación expone tres servicios REST para crear operadores, supervisores y 
directores. de igual forma expone otros tres para borrar empleados. Cada uno recibe 
un queryString: "name" donde se debe enviar el nombre del empleado que se quiere 
loguear(crear) o desloguear(Borrar). Si se desea crear el método a usar es POST, para
borrar el empleado se debe enviar el metodo DELETE

* /v1/employee/operator?name={{nombreEmpleado}} (POST, DELETE)
* /v1/employee/supervisor?name={{nombreEmpleado}} (POST, DELETE)
* /v1/employee/manager?name={{nombreEmpleado}} (POST, DELETE)

De igual forma la aplicación expone un servicio de consulta para poder ver los 
empleado que se encuentran logueados en la plataforma y su estado. El método que se 
debe usar para esta operación es GET

* /v1/employee/ (GET)

Para atender una llamada la aplicación expone un servicio REST con método POST que
también recibe una QueryString: "clientId" donde se debe enviar un string para
identificar el cliente. Cuando se recibe la llamada el servicio retorna el id con 
el que se creo la llamada de igual forma la duración y el empleado que la esta 
atendiendo. Cuando la llamada finaliza se actualiza la llamada y se asigna la fecha
y hora en que se inicio y se finalizo.

* /v1/notice/call?clientId={{clientId}} (POST)

Por ultimo se exponen dos servicios de consulta para revisar tanto las llamadas 
atendidas como las llamadas que fueron rechazadas por falta de empleados. Y un ultimo
servicio para buscar una llamada según su UUID.

* /v1/notice/call/{callId} (GET) 
* /v1/notice/call/attendCalls  (GET)
* /v1/notice/call/rejectCalls  (GET)

-----------------------------------------------------------------------------------
-------------------------- Indicaciones Técnicas ----------------------------------
-----------------------------------------------------------------------------------

Para ejecutar las pruebas unitarias, es necesario instalar Maven 3.3.9 o superior. 
Desde la consola, dirigirse a la carpeta del proyecto y ejecutar el comando 
"mvn test". Puede fallar las dos o tres primeras veces ya que maven descarga las 
librerías necesarias y si no están listas genera error de compilación.

De igual forma para generar el archivo ejecutable .jar de la aplicación, es 
necesario dirigirse a la carpeta del proyecto y ejecutar el comando "mvn package". 
Después de terminar se genera una carpeta con el nombre target donde se podrá 
encontrar el archivo "CallCenterAlmundo.jar".

Para iniciar la aplicación es necesario dirigirse a la carpeta target y ejecutar
el comando "java -jar CallCenterAlmundo.jar". Por defecto la aplicación se ejecuta
en el puerto 9091. Para realizar el llamado a cualquiera de los servicios es debe 
colocar el servidor y el puerto donde se esta ejecutando la aplicación. Ejemplo
http://localhost:9091/v1/notice/call/


-----------------------------------------------------------------------------------
---------------------------------- Extras/Plus ------------------------------------
-----------------------------------------------------------------------------------

1) Qué pasa con una llamada cuando no hay ningún empleado libre?

Actualmente la aplicación rechaza las llamadas cuando no hay mas empleados
disponibles. Sin embargo como alternativa se puede implementar una cola de espera
donde donde cada vez que un empleado quede libre, pueda tomar la llamada mas 
antigua en la cola. Debido a que son servicios REST cada llamada debe tener un 
tiempo limite en la cola y si ningún empleado queda disponible proceder a rechazarla.

2) Qué pasa con una llamada cuando entran más de 10 llamadas concurrentes?

Debido a que son servicios REST es posible utilizar nodos y balanceadores de carga
para soportar una gran concurrencia. Para el proyecto se creo un test de carga 
usando JMeter el cual se puede encontrar en la carpeta raíz del proyecto e 
importarlo con el archivo "HTTP Request Defaults.jmx". Este test ejecuta en el
mismo instante 20 hilos para creación de Empleados y 20 hilos para creación de 
llamadas. El resultado fue exitoso y la aplicación soporto la carga concurrente.

3) Agregar los tests unitarios que se crean convenientes.

Se agregaron cuatro test para la creación de empleados, y seis en la creación de
llamadas. En total son 10 test unitarios.

4) Agregar documentación de código.

Las clases mas relevantes tienen la documentación requerida, sin embargo considero
que el código es auto-descriptivo por ende la documentación necesaria para entender
el código es mínima.