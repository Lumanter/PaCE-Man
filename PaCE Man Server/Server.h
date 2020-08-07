//
// Created by valva on 7/28/2020.
//

#ifndef PACE_MAN_SERVER_H
#define PACE_MAN_SERVER_H

#define PORT 7799
#define SERVER_IP "192.168.31.188"
#define BUFFERSIZE 5000

#include<stdio.h>
#include<stdlib.h>
#include<sys/socket.h>
#include<netinet/in.h>
#include<string.h>
#include <arpa/inet.h>
#include <fcntl.h> // for open
#include <unistd.h> // for close
#include<pthread.h>

//Local includes
#include "Game.h"
#include "MessageParser.h"

typedef struct AdminInfo{
    Game* game;
    pthread_mutex_t* lock;
    char* inputBuffer;
    int* inputBufferSize;
}ADMININFO;

/**
 * Initiates a thread for a given socket connection
 * @param arg: socket connection
 *
 */
void *socketThread(void *arg);

void processRequest(char* message, Game* game);
/**
 * Starts the main server in listening mode
 * @return Error codes if any
 */
int startMainServer();

void startAdmin(void* args);

#endif //PACE_MAN_SERVER_H
