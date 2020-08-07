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

/**
 * Counts the amount of commas in a string
 * @param text
 * @param size
 * @return amount of commas
 */
int countCommas(char* text, int size);

/**
 * copies a parse message for the buffer
 * @param message
 * @param sizeOfMessage
 * @param buffer
 */
void copyParseMessageToBuffer(char** message, int sizeOfMessage, char** buffer);

/**
 * parses a message
 * @param message
 * @param delimiter
 * @param resultPtr
 */
void parseMessage(char* message,char delimiter[], char** resultPtr);

/**
 * Convert a message to an updateInfo struct
 * @param parsedMessage
 * @param size
 * @param updateinfo
 */
void convertMessageToUpdateInfo(char** parsedMessage, int size, UPDATEINFO* updateinfo);

/**
 * Convert events in request
 * @param updateinfo
 * @param restOfMessage
 * @param currentIndex
 * @param size
 */
void convertEventInMessage(UPDATEINFO* updateinfo, char** restOfMessage, int currentIndex, int size);

/**
 * Adds element to a list
 * @param list
 * @param value
 */
void addToList(LIST* list, void* value);

/**
 * Converts an
 * @param updateinfo
 * @param message
 * @return
 */
int convertUpdateInfoToMessage(UPDATEINFO* updateinfo, char* message);



#endif //PACE_MAN_SERVER_MESSAGEPARSER_H
