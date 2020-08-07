//
// Created by jondorito on 04/08/20.
//

#include <printf.h>
#include <stdio.h>
#include <stdlib.h>
#include "MessageParser.h"

/*Size = 2c+1
 * c = (size-1)/2
 * */
void parseMessage(char* message, char delimiter[], char** resultPtr){

    char* token = strtok(message, delimiter);

    int counter = 1;

    resultPtr[0] = token;

    while((token = strtok(NULL, delimiter)) != NULL){
        char* current = token;
        resultPtr[counter] = current;
        counter++;
    }
}

void copyParseMessageToBuffer(char **message, int sizeOfMessage, char **buffer) {
    for(int i = 0; i < sizeOfMessage; i++){
        buffer[i] = message[i];
    }
}

void convertToInfo(UPDATEINFO *updateinfo, char *message) {
    int size = strlen(message);
    int amountCommas = countCommas(message, size);
    char* parsedMessage[amountCommas + 1];

    parseMessage(message, ",", parsedMessage);

    convertMessageToUpdateInfo(parsedMessage, amountCommas+1, updateinfo);
}

void convertMessageToUpdateInfo(char** parsedMessage, int size, UPDATEINFO* updateinfo){

    for(int i = 0; i < 4; i++){

        char* ptr;

        updateinfo->PacmanInfo[i] = (int)strtol(parsedMessage[i], &ptr, 10);
        updateinfo->Ghost1_Info[i] = (int)strtol(parsedMessage[4 + i], &ptr, 10);
        updateinfo->Ghost2_Info[i] = (int)strtol(parsedMessage[8 + i], &ptr, 10);
        updateinfo->Ghost3_Info[i] = (int)strtol(parsedMessage[12 + i], &ptr, 10);
        updateinfo->Ghost4_Info[i] = (int)strtol(parsedMessage[16 + i], &ptr, 10);

    }

    convertEventInMessage(updateinfo, parsedMessage, 21, size);

}

void addToList(LIST* list, void* value){
    if(list->length == 0) {
        NODE node;
        node.value = value;
        list->head = &node;
    }

    NODE* currentNode = list->head;

    for(int length = list->length; length > 0; length--){
        if(length == 1){
            NODE newNode;
            newNode.value = value;
            currentNode->next = &newNode;
        }
    }
}

void convertEventInMessage(UPDATEINFO* updateinfo, char** restOfMessage, int currentIndex, int size){

    if(currentIndex >= size){
        return;
    }

    char* currentEvent = restOfMessage[currentIndex];

    if(strcmp(currentEvent, "createGhost") == 0){
        EVENTCREATEGHOST* event = malloc(sizeof(EVENTCREATEGHOST));
        int pos1 = (int)strtol(restOfMessage[currentIndex+1], NULL, 10);
        int pos2 = (int)strtol(restOfMessage[currentIndex+2], NULL, 10);
        event->pos[0] = (int)strtol(restOfMessage[currentIndex+1], NULL, 10);
        event->pos[1] = (int)strtol(restOfMessage[currentIndex+2], NULL, 10);
        addToList(updateinfo->events, event);
        convertEventInMessage(updateinfo, restOfMessage, currentIndex+3, size);
    }
}

int countCommas(char *text, int sizeText) {
    char current;
    int commas = 0;
    for(int size = 0; size < sizeText; size++){
        current = text[size];
        if(current == ','){
            commas++;
        }
    }return commas;
}

int convertUpdateInfoToMessage(UPDATEINFO* updateinfo, char* message){
    strcpy(message, "update,");


    for (int i = 0; i < 4; i++) {
        char pacman_i[4];
        sprintf(pacman_i, "%d", updateinfo->PacmanInfo[i]);
        strcat(message, pacman_i);
        strcat(message, ",");
    }
    for (int i = 0; i < 4; i++) {
        char ghost1_i[4];
        sprintf(ghost1_i, "%d", updateinfo->Ghost1_Info[i]);
        strcat(message, ghost1_i);
        strcat(message, ",");
    }
    for (int i = 0; i < 4; i++) {
        char ghost2_i[4];
        sprintf(ghost2_i, "%d", updateinfo->Ghost2_Info[i]);
        strcat(message, ghost2_i);
        strcat(message, ",");
    }
    for (int i = 0; i < 4; i++) {
        char ghost3_i[4];
        sprintf(ghost3_i, "%d", updateinfo->Ghost3_Info[i]);
        strcat(message, ghost3_i);
        strcat(message, ",");
    }
    for (int i = 0; i < 4; i++) {
        char ghost4_i[4];
        sprintf(ghost4_i, "%d", updateinfo->Ghost4_Info[i]);
        strcat(message, ghost4_i);
        strcat(message, ",");
    }



        int counter = 0;

        char currentChar;

        while((currentChar = message[counter]) != '\0'){
            counter++;
        }

        return counter;
    }
