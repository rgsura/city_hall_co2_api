#City Hall CO2 Reading API

This project is a simply a demo project showcasing how an api 
that deals with both GET and POST request from multiple users with
specific rights can be implemented.

The easiest way to run the project would be through gradle by running this command:

CMD:
```sh
gradlew bootRun -Pargs=--BASIC_AUTH_CRED_READ=wien_r:wienpwr,barcelona_r:barcelonapwr,münchen_r:münchenpwr;--BASIC_AUTH_CRED_WRITE=wien_w:wienpww,barcelona_w:barcelonapww,münchen_w:münchenpww
```
BASH:
```sh
./gradlew bootRun -Pargs=--BASIC_AUTH_CRED_READ=wien_r:wienpwr,barcelona_r:barcelonapwr,münchen_r:münchenpwr;--BASIC_AUTH_CRED_WRITE=wien_w:wienpww,barcelona_w:barcelonapww,münchen_w:münchenpww
```
The environment variables are to set the users. If no users are put, the application fails to start.
The read users can only query data (that would be the city halls) and the write users
can only insert data (which would be the sensors). In addition to that, one user can
only get/post for the city for which its user is assigned.

An alternative way to start the application would be through IntelliJ IDEA.

If the application is run through gradle, it appears as though the run is stuck at around 80%, 
but that is just because the application is already running successfully on localhost:8080

The Swagger UI page is here: http://localhost:8080/swagger-ui/index.html

NOTE: I would recommend for testing purposes to open it in an incognito session as 
I didn't make it possible to sign out of the session.

## Test calls
Here are some test calls:
```sh
curl -X 'GET' \
'http://localhost:8080/city/Wien/co2readings?district=Penzing&max-days=30' \
-H 'accept: */*'
```

```sh
curl -X 'POST' \
  'http://localhost:8080/city/Wien/district/Penzing/co2reading' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "co2level": 30,
  "timestamp": "2022-03-07T19:48:21.955Z"
}'
```