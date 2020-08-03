//
// Created by valva on 7/29/2020.
//

#pragma once

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

// Pair - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
typedef struct Pair {
    int x;
    int y;
} Pair;

Pair create_pair(int x,int y);

// Pac-Man - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
typedef struct Pac_Man {
    Pair pos;
    int sprite;
} Pac_Man;

Pac_Man create_pac_man();

void modify_pac_man(Pac_Man* p_m_ptr,Pair new_pos,int new_sprite);

void modify_pac_man_position(Pac_Man* p_m_ptr,Pair new_pos);

// Ghost - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
typedef struct Ghost {
    Pair pos;
    bool active;
    int color;
} Ghost;

Ghost create_ghost(int color);

void modify_ghost_position(Ghost* g,Pair new_pos);

void activate_ghost(Ghost* g);

void deactivate_ghost(Ghost* g);

// Pill - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
typedef struct Pill {
    Pair pos;
} Pill;

Pill create_pill(Pair pos);

// Pill Linked List
struct Linked_List_Pill {
    Pill pill;
    struct Linked_List_Pill *next;
};

typedef struct Linked_List_Pill *P_Node;

P_Node create_p_node();

P_Node add_p_node(P_Node head, Pill pill);


// Game - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
typedef struct Game {
    unsigned int level;

    unsigned int lives;

    bool pill_active;

    P_Node pills;

    Pac_Man pac_man;

    Ghost ghosts[4];

    // Linked list of pills
} Game;

Game create_game();

void update_game_pac_man(Game *game,Pair new_pos,int new_sprite);

void update_game_pac_man_pos(Game *game,Pair new_pos);

void update_game_ghosts_pos(Game *game,Pair* new_pos);

void add_pills_to_game(Game *game,Pill new_pill);