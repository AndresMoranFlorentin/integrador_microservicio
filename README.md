Microservicios:
  Monopatin: puerto 9090
             BBDD monopatindb
  Mantenimiento: puerto 8060
             BBDD mantenimientodb
  Administracion: puerto 9080
             BBDD administraciondb
  Usuario: puerto 8070
             BBDD usuariodb
  Cliente: puerto 5080
             BBDD clientedb

  Eureka: puerto 8761
  Gateway: puerto 8080
  Config: puerto 8888 

## Para iniciar la aplicacion se debe encender:
 - Config-Server
 - Eureka-Server
 - Gateway-Server
 - Monopatin-Server
 - en adelante cualquier otro microservicio que se necesite usar
  
### el microservicio cliente tiene precargado