//
// Created by valva on 8/4/2020.
//

#include "Admin.h"

void admin(Game* game){
    // Intro
    printf("\n- - - - - Welcome to the PaCE-Man admin menu - - - - -\n");
    printf("Here you can make a series of actions to affect the game:\n");
    printf("* Press 0 exit\n");
    printf("* Press 1 to add a pill\n");
    printf("* Press 2 to add a ghost\n");
    printf("* Press 3 to add a fruit\n\n");

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
                add_pill_to_game(game,create_pill(create_pair(x,y)));

                // We inform the user
                printf("> You chose to add a pill to the game\n\n");
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
                printf("> ");

                // We get which ghost the user wants
                int ghost;
                scanf("%d",&ghost);

                // We check the value was correct
                if ((ghost != 0) && (ghost != 1) && (ghost != 2) && (ghost != 3)) {
                    printf("\n> We couldn't add the ghost\n");
                    break;
                }

                // We activate a ghost in the game
                game->ghosts[ghost].active = true;
                printf("\n>You chose to add a ghost to the game\n\n");
                break;
            }
            case '3':
                printf("> You chose to add a fruit to the game\n");
                break;
            default:
                //printf("> Input not recognized\n");
                break;
        }
    } while (exit == false);
}