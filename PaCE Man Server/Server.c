//
// Created by valva on 7/28/2020.
//

#include "Server.h"
#include "Admin.h"

//Constants

pthread_t currentConnections[500];
char* outputBufferPlayer[BUFFERSIZE];
int outputBufferPlayer_currentSize = 0;
char* outputBufferObserver[BUFFERSIZE];
int outputBufferObserver_currentSize = 0;
char inputPlayerBuffer[BUFFERSIZE];
int inputPlayerBuffer_currentSize = 0;
char inputObserverBuffer[BUFFERSIZE];
int inputObserverBuffer_currentSize = 0;
Game game;
int testingToggle = 0;
pthread_mutex_t lock = PTHREAD_MUTEX_INITIALIZER;

ADMININFO admininfo;

void processPlayerRequest(){
    char* action = outputBufferPlayer[1];

    char* numberVault[26];

    for(int i = 0; i < 26; i++){
        numberVault[i] = outputBufferPlayer[i];
    }
    char* error;
    Game* game1 = &game;

    if(strcmp(action, "update") == 0){
        UPDATEINFO updateinfo;

        Pair Pacman;

        Pacman.x = (int)strtol(outputBufferPlayer[2], NULL, 10);
        Pacman.y = (int)strtol(outputBufferPlayer[3], NULL, 10);
        int sprite = (int)strtol(outputBufferPlayer[4], NULL, 10);
        int lives = (int)strtol(outputBufferPlayer[5], NULL, 10);

        set_game_pac_man(&game, Pacman, sprite);
        update_game_lives(&game, (unsigned int)lives);

        Pair ghostPos[4];

        Pair ghost_1;
        ghost_1.x = (int)strtol(outputBufferPlayer[6], NULL, 10);
        ghost_1.y = (int)strtol(outputBufferPlayer[7], NULL, 10);
        ghostPos[0] = ghost_1;
        Pair ghost_2;
        ghost_2.x = (int)strtol(outputBufferPlayer[10], NULL, 10);
        ghost_2.y = (int)strtol(outputBufferPlayer[11], NULL, 10);
        ghostPos[1] = ghost_2;
        Pair ghost_3;
        ghost_3.x = (int)strtol(outputBufferPlayer[14], NULL, 10);
        ghost_3.y = (int)strtol(outputBufferPlayer[15], NULL, 10);
        ghostPos[2] = ghost_3;
        Pair ghost_4;
        ghost_4.x = (int)strtol(outputBufferPlayer[18], NULL, 10);
        ghost_4.y = (int)strtol(outputBufferPlayer[19], NULL, 10);
        ghostPos[3] = ghost_4;

        set_game_ghosts_pos(&game, ghostPos);

        Ghost* ghosts = get_game_ghosts(&game);

        ghosts[0].active = (bool)strtol(outputBufferPlayer[8], NULL, 10);
        ghosts[0].color = (int)strtol(outputBufferPlayer[9], NULL, 10);

        ghosts[1].active = (bool)strtol(outputBufferPlayer[12], NULL, 10);
        ghosts[1].color = (int)strtol(outputBufferPlayer[13], NULL, 10);

        ghosts[2].active = (bool)strtol(outputBufferPlayer[16], NULL, 10);
        ghosts[2].color = (int)strtol(outputBufferPlayer[17], NULL, 10);

        ghosts[3].active = (bool)strtol(outputBufferPlayer[20], NULL, 10);
        ghosts[3].color = (int)strtol(outputBufferPlayer[21], NULL, 10);

        update_game_pill_active(&game, (bool)strtol(outputBufferPlayer[22], NULL, 10));

        update_game_level(&game, (unsigned int)strtol(outputBufferPlayer[23], NULL, 10));

        update_game_score(&game, (unsigned int)strtol(outputBufferPlayer[24], NULL, 10));

        update_game_state(&game, (int)strtol(outputBufferPlayer[25], NULL, 10));






        /*if(testingToggle == 0){
            char putGhost[] = "events,createGhost,1";
            strcpy(inputPlayerBuffer, putGhost);
            inputPlayerBuffer_currentSize = sizeof(putGhost);
            testingToggle++;
        }*/

    }

    memset(outputBufferPlayer, 0, BUFFERSIZE*sizeof(outputBufferPlayer[0]));
}

void startGame(Game* game){
    pthread_mutex_lock(&lock);
    update_game_level(game, 1);
    update_game_lives(game, 3);
    update_game_score(game, 0);

    char messageToClient[8];

    strcpy(messageToClient, "start,");

    int x = get_game_level(game);
    int length = snprintf( NULL, 0, "%d", x );
    char* str = malloc( length + 1 );

    messageToClient[6] = str[0];
    messageToClient[7] = '\n';

    strcpy(inputPlayerBuffer, messageToClient);


    pthread_mutex_unlock(&lock);

    free(str);
}

void *socketThread(void *arg){
    Game* gamePtr = &game;

    char client_message[BUFFERSIZE];
    char buffer[BUFFERSIZE];

    //printf("Created new client thread");
    int newSocket = *((int *)arg);
    // Send message to the client socket
    while (recv(newSocket , client_message , 150 , 0)) {

        if(client_message[0] == '\000') {
            //printf("Exit socketThread \n");
            close(newSocket);
            //printf("Socket has been closed");
            pthread_exit(NULL);
        }

        pthread_mutex_lock(&lock);

        char* message = malloc(BUFFERSIZE);
        int amountCommas = countCommas(client_message,sizeof(client_message));

        char* temporalBuffer[amountCommas+1];
        parseMessage(client_message, ",", temporalBuffer);

        char* command = temporalBuffer[0];

        if(strcmp(command, "player") == 0){
            copyParseMessageToBuffer(temporalBuffer, amountCommas+1, outputBufferPlayer);
            outputBufferPlayer_currentSize = amountCommas+1;

            processPlayerRequest();

            testingToggle++;

            strcpy(message, inputPlayerBuffer);

            message[strlen(message)] = '\n';

            send(newSocket, message, strlen(message), 0);
            memset(outputBufferPlayer, 0, BUFFERSIZE);
            memset(inputPlayerBuffer, 0, BUFFERSIZE);
            memset(client_message, 0, sizeof(client_message));
            memset(message, 0, sizeof(message));
            free(message);



        }else if(strcmp(command, "observer") == 0){
            copyParseMessageToBuffer(temporalBuffer, amountCommas+1, outputBufferObserver);

            UPDATEINFO updateinfo;

            Pair pacman = get_game_pac_man_pos(&game);

            updateinfo.PacmanInfo[0] = pacman.x;
            updateinfo.PacmanInfo[1] = pacman.y;
            updateinfo.PacmanInfo[2] = get_game_pac_man(&game).sprite;
            updateinfo.PacmanInfo[3] = get_game_lives(&game);

            Ghost* ghosts = get_game_ghosts(&game);



            updateinfo.Ghost1_Info[0] = ghosts[0].pos.x;
            updateinfo.Ghost1_Info[1] = ghosts[0].pos.y;
            updateinfo.Ghost1_Info[2] = ghosts[0].active;
            updateinfo.Ghost1_Info[3] = ghosts[0].color;

            updateinfo.Ghost2_Info[0] = ghosts[1].pos.x;
            updateinfo.Ghost2_Info[1] = ghosts[1].pos.y;
            updateinfo.Ghost2_Info[2] = ghosts[1].active;
            updateinfo.Ghost2_Info[3] = ghosts[1].color;

            updateinfo.Ghost3_Info[0] = ghosts[2].pos.x;
            updateinfo.Ghost3_Info[1] = ghosts[2].pos.y;
            updateinfo.Ghost3_Info[2] = ghosts[2].active;
            updateinfo.Ghost3_Info[3] = ghosts[2].color;

            updateinfo.Ghost4_Info[0] = ghosts[3].pos.x;
            updateinfo.Ghost4_Info[1] = ghosts[3].pos.y;
            updateinfo.Ghost4_Info[2] = ghosts[3].active;
            updateinfo.Ghost4_Info[3] = ghosts[3].color;

            updateinfo.pillState = get_pill_state(&game);
            updateinfo.level = get_game_level(&game);
            updateinfo.score = get_game_score(&game);
            updateinfo.gameState = get_game_state(&game);

            convertUpdateInfoToMessage(&updateinfo, message);

            message[strlen(message)] = '\n';

            send(newSocket, message, strlen(message), 0);

            memset(client_message, 0, sizeof(client_message));
            memset(message, 0, sizeof(message));
        }

        pthread_mutex_unlock(&lock);
    }
}

int startMainServer() {

    int serverSocket;
    int newSocket;

    struct sockaddr_in serverAddr;
    struct sockaddr_storage serverStorage;

    socklen_t size;

    //Creating of listening socket
    serverSocket = socket(PF_INET, SOCK_STREAM,0);

    socklen_t addr_size;

    // Address family = Internet
    serverAddr.sin_family = AF_INET;
    //Set port number, using htons function to use proper byte order
    serverAddr.sin_port = htons(PORT);
    //Set IP address to localhost
    serverAddr.sin_addr.s_addr = inet_addr(SERVER_IP);
    //Set all bits of the padding field to 0
    memset(serverAddr.sin_zero, '\0', sizeof serverAddr.sin_zero);
    //Bind the address struct to the socket
    bind(serverSocket, (struct sockaddr *) &serverAddr, sizeof(serverAddr));
    //Listen on the socket, with "reasonable" max connection requests queued
    if(listen(serverSocket,SOMAXCONN)==0)
        printf("Listening\n");
    else
        printf("Error\n");
    pthread_t tid[60];
    int i = 0;

    admininfo.game = &game;
    admininfo.lock = &lock;
    admininfo.inputBuffer = inputPlayerBuffer;
    admininfo.inputBufferSize = &inputPlayerBuffer_currentSize;
    pthread_create(&currentConnections[0], NULL, startAdmin, &admininfo);

    i++;

    while(1)
    {
        //Accept call creates a new socket for the incoming connection
        addr_size = sizeof serverStorage;
        newSocket = accept(serverSocket, (struct sockaddr *) &serverStorage, &addr_size);
        //for each client request creates a thread and assign the client request to it to process
        //so the main thread can entertain next request
        if(i >= 500){
            i = 0;
        }
        if( pthread_create(&currentConnections[i++], NULL, socketThread, &newSocket) != 0 )
            printf("Failed to create thread\n");
        if( i >= 50)
        {
            i = 0;
            while(i < 50)
            {
                pthread_join(currentConnections[i++],NULL);
            }
            i = 0;
        }
    }
    return 0;
}

void processRequest(char *message, Game *game) {
    return;
}

void startAdmin(void* args){
    admin((ADMININFO*) args);
}
