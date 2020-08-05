//
// Created by valva on 8/4/2020.
//

#pragma once

#include "Logic.h"

// G A M E - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  - - - - - - - - - - - - - -

typedef struct Game {
    unsigned int level;
    unsigned int lives;
    unsigned int score;
    bool pill_state;
    P_Node pills;
    F_Node fruits;
    Pac_Man pac_man;
    Ghost ghosts[4];
} Game;

Game create_game();

void update_game_level(Game *game,unsigned int new_level);

unsigned int get_game_level(Game *game);

void update_game_lives(Game *game,unsigned int new_lives);

unsigned int get_game_lives(Game *game);

void update_game_score(Game *game,unsigned int added_score);

unsigned int get_game_score(Game *game);

void update_game_pill_active(Game *game,bool new_pill_state);

bool get_pill_state(Game *game);

void update_game_pac_man(Game *game,Pair new_pos,int new_sprite);

void update_game_pac_man_pos(Game *game,Pair new_pos);

void update_game_ghosts_pos(Game *game,Pair* new_pos);

void add_pill_to_game(Game *game,Pill new_pill);

void delete_pill_from_game(Game *game,Pill pill);
