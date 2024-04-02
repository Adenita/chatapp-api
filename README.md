# VioletVibe - ChatApp

This project is a Spring Boot real-time chat application inspired by platforms like Discord.
It allows users to chat in real time with other singed up users or in group chats.
The application utilizes Netty Socket.IO for real-time communication and features authentication using JWT token

## Features
 - It provides real time communication for users
 - Users can create, join and chat in different available chat rooms
 - Users can chat with other signed users directly
 - The authentication is implemented using JWT token

## Getting Started
Please replace the occurrence of `<your-local-ip>` in `SocketIOConfig`

``` 
config.setHostname("${your-ip-address}");
```
with your local IP address in the source code.
This is necessary for the socket communication.

### Setting up the Database
This project utilizes a PostgreSQL database managed via Docker. To set up the database, follow these steps:
1. Ensure you have Docker installed on your system
2. In your terminal, navigate to the root directory of your project where the Dockerfile is located.
3. Build the Docker Image for the database by running the following command: 
```
docker build . -t chat-db
```
4. After building the image, you can run the Docker container with the following command:
```
docker run --name chat-database -e POSTGRES_PASSWORD=postgres -p 5432:5432 -d chat-db
```
This will start a POSTGRESQL container with the Dockerfile configurations.

## FrontEnd Compatibility 
Ensure that the frontend version is compatible with the Netty Socket.IO version used in this project.
This project uses `netty-socketio: 1.7.18`, which is compatible with `socket.io-client: ^2.1.1`.

## Usage
Connect to the Socket endpoint using the following url: `http://<your-local-ip>:8081` where you should replace
`<your-local-ip>` with your local ip address.

