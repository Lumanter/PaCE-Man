//
// Created by valva on 7/29/2020.
//

#pragma once

#include <stdio.h>
#include <stdbool.h>

typedef struct Pair{
    int x;
    int y;
} Pair;

typedef struct Pac_Man {
    Pair pos;
    int sprite;
} Pac_Man;

typedef struct Ghost {
    Pair pos;
    int color;
} Ghost;

typedef struct Pill {
    Pair pos;
} Pill;

typedef struct Game {
    unsigned int lives;
    bool pill_active;
    Pac_Man pac_man;
} Game;

Pair create_pair(int x,int y);

Pac_Man create_pac_man();

void modify_pac_man(Pac_Man* p_m_ptr,Pair new_pos,int new_sprite);

void modify_pac_man_position(Pac_Man* p_m_ptr,Pair new_pos);

Ghost create_ghost(int color);

void modify_ghost_position(Ghost* g,Pair new_pos);

Pill create_pill(Pair pos);