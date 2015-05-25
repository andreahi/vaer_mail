Spring app that sends weather forecast every hour, to all registered e-mails.



==============_BUILD_==============

mvn package



===============_RUN_===============

java -jar target/spring-0.0.1-SNAPSHOT.jar






==============_USAGE_==============

add new email:

localhost:8080/add?mail=email@test.com

get email by id:

localhost:8080/get?id=0

delete email by id:

localhost:8080/del?id=0

