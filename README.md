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
