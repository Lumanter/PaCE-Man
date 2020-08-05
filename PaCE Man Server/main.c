//
// Created by valva on 7/28/2020.
//

#include <stdio.h>
#include "Game.h"

int main(){
    Game my_game = create_game();

    Pill first = create_pill(create_pair(100,100));
    Pill second = create_pill(create_pair(200,200));
    Pill third = create_pill(create_pair(300,300));

    add_pill_to_game(&my_game,first);
    add_pill_to_game(&my_game,second);
    add_pill_to_game(&my_game,third);

    printf("%i\n",my_game.pills->next->pill.pos.x);

    delete_pill_from_game(&my_game,second);

    printf("%i\n",my_game.pills->next->pill.pos.x);
    return 0;
}