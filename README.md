# Disclaimer

 - This code uses lombok library in order to generate getter, setter and more methods. If you are trying to compile the code on eclipse or intellij you may find some compilation problems, please take a look at https://projectlombok.org for more information.
 
# To install and run the service with java in console

 1- Execute on the root path of the service the command ´mvn clean install´
 
 2- Execute on the root path of the service the command ´java -jar target/*jar´ 
 
# To install and run the service with docker

 1- Execute on the root path of the service the command ´mvn clean install´
 
 2- Execute on the path where dockerfile is the command ´docker build -t meep-app .´
 
 3- Execute the command ´docker run -p 8080:8080 -t meep-app:latest´
 
# Questions for the test

 ## ¿Cómo de escalable es tu solución propuesta? 
  - La aplicacion no escala ya que hace un procesamiento único, solo utiliza un hilo de ejecución sobre la consulta del endpoint. Se podría hacer mas eficiente haciendolo asincrono y paralelizando pero no tiene sentido ya que se establece un período de tiempo concreto (1 o 2 minutos entre pulls).
 ## ¿Que problemas a futuro podría presentar? Si has detectado alguno, ¿Qué alternativa/s propones para solventar dichos problemas?
  - Si el endpoint no diera a basto o se cayera no habría informacón que actualizar y en dos llamadas las diferencias serían 0. Sería mucho mas eficiente, escalable y práctico que fuera al revés, en lugar de exponer un endpoint al que hacer pull crear un evento que haga push a un endpoint expuesto y pudiera actualizar los resultados dados ciertos eventos.
 

# Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.4.RELEASE/maven-plugin/)

