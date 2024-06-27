# Los Microservicios y sus bases de datos y puertos
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

## Para iniciar la aplicacion se debe encender primero:
- Config-Server
- Eureka-Server
- Gateway-Server
- Monopatin-Server
- en adelante cualquier otro microservicio que se necesite usar
### ejemplos de distintos tipos de usuarios :

Ejemplos:

{
    "username": "super-admin",
    "password": "password",
    "roles":["ADMIN,MAINTENANCE,USER"]
}

{
    "username": "admin",
    "password": "password",
    "roles":["ADMIN"]
}

{
    "username": "maintenance",
    "password": "password",
    "roles":["MAINTENANCE"]
}

{
    "username": "user",
    "password": "password",
    "roles":["USER"]
}
