# Tms-diploma project

My project is a platform for booking language lessons with native speakers. Its main feature, distinguishing it from existing platforms of this type, is the ability to book live lessons. The meeting location, duration, and lesson price are set by the tutor. The student's task is to filter tutors based on their criteria and choose a suitable lesson. After the lesson, both participants share their feedback about the lesson. Then, these feedbacks are displayed on the users' profiles.

## Requirements

Before installing, make sure you have the following components installed:

1. Java Development Kit (JDK) version 17 or above
2. Node.js and npm (Node Package Manager)
3. Docker

## Project installation
1. Clone the project repository: `git clone https://github.com/dmnvch98/tms-diploma`
2. Navigate to the project directory: `cd tms-diploma`
3. Install the server-side (Spring) dependencies: `./mvnw install`
4. Start the docker compose: `docker-compose up -d`
5. Run the main classes in the following modules:
   1. api-gateway (ApiGatewayApplication)
   2. conversation-service (ConvServiceApplication)
   3. file-service (FileLoaderApplication)
   4. user-service (UserServiceApplication)
6. Install the client-side dependencies: `cd ui` `npm install`
7. Run the client-side:  `npm start`



