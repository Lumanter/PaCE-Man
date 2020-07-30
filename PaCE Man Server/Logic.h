//
// Created by valva on 7/29/2020.
//

#ifndef PACE_MAN_SERVER_LOGIC_H
#define PACE_MAN_SERVER_LOGIC_H

#include <stdio.h>
#include <stdbool.h>

typedef struct Pac_Man {
    int x;
    int y;
    int sprite;
} Pac_Man;

typedef struct Ghost {
    int x;
    int y;
    int color;
} Ghost;

typedef struct Pill {
    int x;
    int y;
} Pill;

typedef struct Game {
    unsigned int lives;
    bool pill_active;
    Pac_Man pac_man;
} Game;

Pac_Man create_pac_man();

Ghost create_ghost(int color);

Pill create_pill(int x,int y);

#endif //PACE_MAN_SERVER_LOGIC_H
