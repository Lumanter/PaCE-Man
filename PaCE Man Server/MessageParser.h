//
// Created by jondorito on 03/08/20.
//

#ifndef PACE_MAN_SERVER_MESSAGEPARSER_H
#define PACE_MAN_SERVER_MESSAGEPARSER_H

#include <string.h>


typedef struct Node{
    void* value;
    struct Node* next;
}NODE;

typedef struct List{
    NODE* head;
    int length;
}LIST;

typedef struct createGhostEvent{
    int pos[2];
}EVENTCREATEGHOST;

typedef struct UpdateInfo{
    int PacmanInfo[4];
    int Ghost1_Info[4];
    int Ghost2_Info[4];
    int Ghost3_Info[4];
    int Ghost4_Info[4];
    LIST* events;
    int score;
    int pillState;
    int level;
    int gameState;
}UPDATEINFO;

typedef struct requestLevel{
    int level;
}REQUESTLEVEL;

int countCommas(char* text, int size);

void copyParseMessageToBuffer(char** message, int sizeOfMessage, char** buffer);

void parseMessage(char* message,char delimiter[], char** resultPtr);

void convertMessageToUpdateInfo(char** parsedMessage, int size, UPDATEINFO* updateinfo);

void convertEventInMessage(UPDATEINFO* updateinfo, char** restOfMessage, int currentIndex, int size);

void addToList(LIST* list, void* value);

void convertToInfo(UPDATEINFO* updateinfo, char* message);

int convertUpdateInfoToMessage(UPDATEINFO* updateinfo, char* message);



#endif //PACE_MAN_SERVER_MESSAGEPARSER_H
