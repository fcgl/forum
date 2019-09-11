# Forum Microservices

**Note:** 
    * You will only need docker installed on your computer to run this app

## Git Steps
1. Fork Branch
2. Open terminal and clone **forked branch**: `git clone https://github.com/<YOUR USERNAME>/forum.git`
3. Go inside forum directory: `cd forum`
3. Add upstream repo: `git remote add upstream https://github.com/fcgl/forum.git`
4. Confirm that you have an origin and upstream repos: `git remote -v`

## Build & Run App

This build should work for both macOS and Linux

1. Download docker for your operating system
2. From project root run the following commands to build and run the app:
    * `docker-compose up --build`

## API

In order to make API requests you will need a JWT token. You can get a token by building and running the user-interaction
repo: https://github.com/fcgl/user-interaction making the following POST request: 

* endpoint: `http://localhost:8080/auth/login`
* body: `{"email": "test@fcgl.com", "password": "password"}`
with content type: `application/json`

You will get back a token that you will have to add to your header before making any request. We recommend downloading 
Postman to make API requests: https://www.getpostman.com/downloads/

Example Curl Request:
```
curl -X GET \
  http://localhost:8082/forum/post/v1/all \
  -H 'Accept: */*' \
  -H 'Accept-Encoding: gzip, deflate' \
  -H 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI1IiwiZXhwIjoxNTY5MDQ2MzU1LCJpYXQiOjE1NjgxODIzNTUsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdfQ.15gI6MJaRQvtczvSnQ_FHhadcXmVJiTr_EklPSK8ECK3go5DnmL02GUIjYF72XTEmsdjbPO2L8WSOoPxmxiylA' \
  -H 'Cache-Control: no-cache' \
  -H 'Connection: keep-alive' \
  -H 'Host: localhost:8082' \
  -H 'Postman-Token: b36a1b1b-0915-4fca-aebf-421267b4a905,b2ac6b88-4ad5-4c4d-8f51-c0a01993d9c4' \
  -H 'User-Agent: PostmanRuntime/7.16.3' \
  -H 'cache-control: no-cache'
```

**The following token will be available until 6/7/2022 (June 7, 2022):**

```
eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI1IiwiZXhwIjoxNjU0NTgzMDcyLCJpYXQiOjE1NjgxODMwNzIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdfQ.Tly-cqQSVziwdLwEzg8Pswv6OVINahoS0d9yhQnC30DLWf7UZGzbgdm4naTYgNkNNBgY00Dx2jkh-r4eYDtyIg
```

## Health Endpoint

Confirm everything was ran correctly by going to the following endpoint: 
    * http://localhost:8082/health/v1/marco

## Database

#### PGAdmin
You can access the DB through pgadmin

1. Go to http://localhost:9000
    * email: test@test.com
    * password: test
2. Credentials to connect to server:
    * password: password
    * user: postgres

## Patterns

**TODO: Move these to a wiki**

#### Circuit Breaker Pattern

The `Circuit Breaker Pattern` helps us in preventing a cascade of failures when 
a remote service is down.

After a number of failed attempts, we can consider that the service is unavailable/overloaded
and eagerly reject all subsequent requests to it. In this way, we can save system
resources for calls which are likely to fail. 

##### Resilience4j Circuit Breaker
The circuitBreaker is implemented via a finite state machine with three normal states:
CLOSED, OPEN, and HALF_OPEN and two special states DISABLED and FORCED_OPEN.

The state of the CircuitBreaker changes from CLOSED to OPEN when the failure rate is above a 
configurable threshold. Then all calls are rejected for a configurable time diration. 

After the time duration has elapsed, the CircuitBreaker state changes from OPEN to HALF_OPEN and
allows a configurable number of calls to see if the backend is still unavailable or has become available again. 


More information can be found here: https://resilience4j.readme.io/docs/circuitbreaker
Resilience4j demo: https://github.com/resilience4j/resilience4j-spring-boot2-demo

#### Bulkhead Pattern

The goal of the bulkhead pattern is to avoid faults in one part of a system to tae the entire
system down. The term comes from ships where a ship is divided in separate watertight
compartments to avoid a single hull breach to flood the entire ship; it will only
flood one bulkhead.
