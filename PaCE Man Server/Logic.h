//
// Created by valva on 7/29/2020.
//

#pragma once

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

// P A I R - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

typedef struct Pair {
    int x;
    int y;
} Pair;

Pair create_pair(int x,int y);

// P A C - M A N - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

typedef struct Pac_Man {
    Pair pos;
    int sprite;
} Pac_Man;

Pac_Man create_pac_man();

void modify_pac_man(Pac_Man* p_m_ptr,Pair new_pos,int new_sprite);

void modify_pac_man_position(Pac_Man* p_m_ptr,Pair new_pos);

// G H O S T - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  - - - - - - - - - - - - -

typedef struct Ghost {
    Pair pos;
    bool active;
    int color;
} Ghost;

Ghost create_ghost(int color);

void modify_ghost_position(Ghost* g,Pair new_pos);

void activate_ghost(Ghost* g);

void deactivate_ghost(Ghost* g);

// P I L L - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

typedef struct Pill {
    Pair pos;
} Pill;

Pill create_pill(Pair pos);

struct Linked_List_Pill {
    Pill pill;
    struct Linked_List_Pill *next;
};

typedef struct Linked_List_Pill *P_Node;

P_Node create_p_node();

P_Node add_p_node(P_Node head, Pill pill);

P_Node delete_p_node(P_Node head,Pill pill);

// G A M E - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  - - - - - - - - - - - - - -

typedef struct Game {
    unsigned int level; // Update -
    unsigned int lives; // Update -
    bool pill_state; // Update -
    P_Node pills; // Update (Add last & delete a specif) -
    Pac_Man pac_man; // Update (pos + sprite & pos alone) -
    Ghost ghosts[4]; // Update (all 4 ghost via int list[4]) -
} Game;

Game create_game();

void update_game_level(Game *game,int new_level);

void update_game_lives(Game *game,int new_lives);

void update_game_pill_active(Game *game,bool new_pill_state);

void update_game_pac_man(Game *game,Pair new_pos,int new_sprite);

void update_game_pac_man_pos(Game *game,Pair new_pos);

void update_game_ghosts_pos(Game *game,Pair* new_pos);

void add_pill_to_game(Game *game,Pill new_pill);

void delete_pill_from_game(Game *game,Pill pill);