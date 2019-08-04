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

