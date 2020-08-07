//
// Created by valva on 8/4/2020.
//

#include "Admin.h"

void admin(void* args){

    ADMININFO* admininfo = (ADMININFO*) args;
    Game* game = admininfo->game;
    pthread_mutex_t* lock = admininfo->lock;
    char* inputBuffer = admininfo->inputBuffer;
    int* inputBufferSize = admininfo->inputBufferSize;


    // Intro
    printf("\n- - - - - Welcome to the PaCE-Man admin menu - - - - -\n");
    printf("Here you can make a series of actions to affect the game:\n");
    printf("* Press 0 exit\n");
    printf("* Press 1 to add a pill\n");
    printf("* Press 2 to add a ghost\n");
    printf("* Press 3 to add a fruit\n");
    printf("* Press 4 to change to speed of the game\n\n");

    bool exit = false; // For exiting the loop

    do {
        // We get the command
        char command;
        command = getchar();

        // We process the command
        switch (command){
            case '0': // EXIT
                printf("> Thanks for playing\n");
                exit = true;
                break;
            case '1' : { // PILL
                int x,y; // For storing the pos

                // Info
                printf("> You chose to add a pill to the game. Now choose the position of the pill,\n");
                printf("> there can not be two pills on one position   \n");

                // We get the data
                printf("> X:");
                scanf("%d",&x);
                printf("> Y:");
                scanf("%d",&y);

                // We add a pill to the game

                pthread_mutex_lock(lock);
                char command[15];
                sprintf(command, "events,createPill,%i,%i", x, y);
                strcat(inputBuffer, command);
                add_pill_to_game(game,create_pill(create_pair(x,y)));
                pthread_mutex_unlock(lock);

                // We inform the user
                printf("> You added a pill to the game\n\n");
                break;
            }
            case '2': { // GHOST
                // Info
                printf("> You chose to add a ghost to the game. Now choose one of the four ghosts,\n");
                printf("> of which there can not be two of the same ghost in game, to add: \n");
                printf("> * Press 0 for Blinky: \n");
                printf("> * Press 1 for Pinky: \n");
                printf("> * Press 2 for Inky: \n");
                printf("> * Press 3 for Clyde: \n");
                printf(">");

                // We get which ghost the user wants
                int ghost;
                scanf("%d",&ghost);

                // We check the value was correct
                if ((ghost != 0) && (ghost != 1) && (ghost != 2) && (ghost != 3)) {
                    printf("> We couldn't add the ghost\n");
                    break;
                }

                pthread_mutex_lock(lock);
                char commandM[15];
                sprintf(commandM, "events,createGhost,%i", ghost);
                strcat(inputBuffer, commandM);
                pthread_mutex_unlock(lock);

                // We activate a ghost in the game
                game->ghosts[ghost].active = true;

                // We inform the user
                printf("> You added a ghost to the game\n\n");
                break;
            }
            case '3': {
                int val; // For storing the value
                int x,y; // For storing the pos

                printf("> You chose to add a fruit to the game. Now choose the value of the fruit and\n");
                printf("> its position\n");

                // We get the data
                printf("> Value:");
                scanf("%d", &val);
                printf("> X:");
                scanf("%d", &x);
                printf("> Y:");
                scanf("%d", &y);

                // We add a fruit to the game
                pthread_mutex_lock(lock);
                char commandM[15];
                sprintf(commandM, "events,createPill,%i,%i", x, y);
                strcat(inputBuffer, commandM);
                add_fruit_to_game(game,create_fruit(create_pair(x,y),val));
                pthread_mutex_unlock(lock);

                // We inform the user
                printf("> You added a fruit to the game\n\n");
                break;
            }
            case '4':{
                int val; // For storing the value

                printf("> You chose game speed, enter the new game speed\n");

                // We get the data
                printf("> Value:");
                scanf("%d", &val);
                pthread_mutex_lock(lock);
                char commandM[15];
                sprintf(commandM, "events,changeSpeed,%i", val);
                strcat(inputBuffer, commandM);
                pthread_mutex_unlock(lock);


                printf("> You changed the speed of the game\n\n");
                break;
            }
            default:
                //printf("> Input not recognized\n");
                break;
        }

        admin(args);

    } while (exit == false);
}