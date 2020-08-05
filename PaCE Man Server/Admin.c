//
// Created by valva on 8/4/2020.
//

#include "Admin.h"

void admin(Game* game){
    printf("\n- - - - - Welcome to the PaCE-Man admin menu - - - - -\n");
    printf("Here you can make a series of actions to affect the game:\n");
    printf("* Press 0 exit\n");
    printf("* Press 1 to add a pill\n");
    printf("* Press 2 to add a ghost\n");
    printf("* Press 3 to add a fruit\n\n");

    bool exit = false;

    do {
        char command;
        //printf(">");

        command = getchar();

        switch (command){
            case '0':
                printf("> Thanks for playing\n");
                exit = true;
                break;
            case '1' : {
                int x,y;
                printf("> You chose to add a pill to the game\n");
                printf("> Now choose the position of the pill\n");
                printf("> X:");
                scanf("%d",&x);
                printf("\n> Y:");
                scanf("%i",&y);
                printf("\n> You chose to add a pill to the game\n");
                add_pill_to_game(game,create_pill(create_pair(x,y)));
                break;
            }
            case '2':
                printf("> You chose to add a ghost to the game\n");
                break;
            case '3':
                printf("> You chose to add a fruit to the game\n");
                break;
            default:
                //printf("> Input not recognized\n");
                break;
        }
    } while (exit == false);
}