//
// Created by valva on 7/29/2020.
//

#pragma once

#include <stdio.h>
#include <stdbool.h>

typedef struct Pair {
    int x;
    int y;
} Pair;

typedef struct Pac_Man {
    Pair pos;
    int sprite;
} Pac_Man;

typedef struct Ghost {
    Pair pos;
    bool active;
    int color;
} Ghost;

typedef struct Pill {
    Pair pos;
} Pill;

typedef struct Game {
    unsigned int level;

    unsigned int lives;

    bool pill_active;

    Pac_Man pac_man;

    Ghost ghosts[4];

    // Linked list of pills
} Game;

Pair create_pair(int x,int y);

// PAC-MAN

Pac_Man create_pac_man();

void modify_pac_man(Pac_Man* p_m_ptr,Pair new_pos,int new_sprite);

void modify_pac_man_position(Pac_Man* p_m_ptr,Pair new_pos);

// GHOST

Ghost create_ghost(int color);

void modify_ghost_position(Ghost* g,Pair new_pos);

void activate_ghost(Ghost* g);

void deactivate_ghost(Ghost* g);

// PILL

Pill create_pill(Pair pos);

// GAME

Game create_game();

// GAME INTERACTION

void update_game_pac_man(Game *game,Pair new_pos,int new_sprite);

void update_game_pac_man_pos(Game *game,Pair new_pos);

void update_game_ghosts_pos(Game *game,Pair* new_pos);


