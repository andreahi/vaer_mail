Spring app that sends weather forecast every hour, to all registered e-mails.



### BUILD

mvn package



### RUN

java -jar target/spring-0.0.1-SNAPSHOT.jar






### USAGE

add new email:

localhost:8080/add?mail=email@test.com

get email by id:

localhost:8080/get?id=0

delete email by id:

localhost:8080/del?id=0

