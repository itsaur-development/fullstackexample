#### DEV machine setup
Install the following
- IDE of your choice ([intellij](https://www.jetbrains.com/idea/) recommended)
- [Java 8 SDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [GIT](https://git-scm.com/)
- [Docker CE](https://www.docker.com/products/overview)
- [Docker Compose](https://docs.docker.com/compose/install/) (on windows it is installed along with docker)
- [NodeJS](https://nodejs.org/en/)

###### Run docker

Go to ./docker folder and run

    "docker-compose -f services.yml up"

Run "docker-compose -f services.yml stop" to stop docker

###### Import project to IDE
Most java IDEs support importing maven projects. 

- Intellij: File->Open and select the root project folder.
- eclipse: File->Import select Gradle Project and import the root project folder.

###### Run the server
From your IDE run Application class from web-server project or run ./gradlew bootRun

It will open a server that runs on http://localhost:8080

#### Run the client
- Run ./gradlew npmInstall
- Run ./gradlew webpackDevServer

It will open a server that runs on http://localhost:8000

WINDOWS: Note that the second command doesn't work on windows you have to run the following commands instead
- cd web-client
- .\node_modules\.bin\webpack-dev-server --compress --progress --port 8000 --host localhost --env.BASE_SERVER_URL http://localhost:8080

#### Restart the development
After the first setup the next time you will restart your machine and you want to start developing the following steps must be performed:
- Run docker
- Run the server
- Run the client
