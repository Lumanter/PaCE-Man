//
// Created by valva on 7/28/2020.
//

#include <stdio.h>
#include "Game.h"
#include "MessageParser.h"
#include "Server.h"

int main(){
    /*char testText[] = "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20";

    char* testPtr = testText;

    UPDATEINFO updateinfo;

    LIST events;
    events.length = 0;

    updateinfo.events = &events;


    convertToInfo(&updateinfo, testPtr);

    int a = 1;*/

    startMainServer();

    /*Game my_game = create_game();

    Pill first = create_pill(create_pair(100,100));
    Pill second = create_pill(create_pair(200,200));
    Pill third = create_pill(create_pair(300,300));
    Pill fourth = create_pill(create_pair(400,400));
    Pill fifth = create_pill(create_pair(500,500));

    Fruit first_1 = create_fruit(create_pair(100,100),1000);
    Fruit second_2 = create_fruit(create_pair(200,200),1000);
    Fruit third_3 = create_fruit(create_pair(300,300),1000);
    Fruit fourth_4 = create_fruit(create_pair(400,400),1000);
    Fruit fifth_5 = create_fruit(create_pair(500,500),1000);

    Dot d1 = create_dot(create_pair(100,100));
    Dot d2 = create_dot(create_pair(200,200));
    Dot d3 = create_dot(create_pair(300,300));
    Dot d4 = create_dot(create_pair(400,400));

    add_pill_to_game(&my_game,first);
    add_pill_to_game(&my_game,second);
    add_pill_to_game(&my_game,third);
    add_pill_to_game(&my_game,fourth);
    add_pill_to_game(&my_game,fifth);

    add_fruit_to_game(&my_game,first_1);
    add_fruit_to_game(&my_game,second_2);
    add_fruit_to_game(&my_game,third_3);
    add_fruit_to_game(&my_game,fourth_4);
    add_fruit_to_game(&my_game,fifth_5);

    add_dot_to_game(&my_game,d1);
    add_dot_to_game(&my_game,d2);
    add_dot_to_game(&my_game,d3);
    add_dot_to_game(&my_game,d4);

    printf("%i\n",my_game.pills->pill.pos.x);
    printf("%i\n",my_game.pills->next->pill.pos.x);
    printf("%i\n",my_game.pills->next->next->pill.pos.x);
    printf("%i\n",my_game.pills->next->next->next->pill.pos.x);
    printf("%i\n\n",my_game.pills->next->next->next->next->pill.pos.x);

    printf("%i\n",my_game.fruits->fruit.value);
    printf("%i\n",my_game.fruits->next->fruit.value);
    printf("%i\n",my_game.fruits->next->next->fruit.value);
    printf("%i\n",my_game.fruits->next->next->next->fruit.value);
    printf("%i\n\n",my_game.fruits->next->next->next->next->fruit.value);

    printf("%i\n",my_game.dots->dot.pos.x);
    printf("%i\n",my_game.dots->next->dot.pos.x);
    printf("%i\n",my_game.dots->next->next->dot.pos.x);
    printf("%i\n",my_game.dots->next->next->next->dot.pos.x);

    char test[1764];
    get_game_fruits(&my_game, test);
    printf("%s", test);*/

    /*
    delete_pill_from_game(&my_game,first);
    delete_pill_from_game(&my_game,second);
    delete_pill_from_game(&my_game,third);

    printf("%i\n",my_game.pills->pill.pos.x);
    printf("%i\n",my_game.pills->next->pill.pos.x);
    //printf("%i\n",my_game.pills->next->next->pill.pos.x);
    //printf("%i\n",my_game.pills->next->next->next->pill.pos.x);
    //printf("%i\n\n",my_game.pills->next->next->next->next->pill.pos.x);
     */
    //char str_1[500];

    //char str_2[500];

    //get_game_pills(&my_game,str_1);

    //get_game_pills(&my_game,str_2);

    //printf("%s\n",str_1);
    //printf("%s\n",str_2);

}