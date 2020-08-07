//
// Created by valva on 8/4/2020.
//

#pragma once

#include "Game Structs.h"
#include "Constants.h"

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

// Level
void update_game_level(Game *game,unsigned int new_level);
unsigned int get_game_level(Game *game);

// Lives
void update_game_lives(Game *game,unsigned int new_lives);
unsigned int get_game_lives(Game *game);

// Score
void update_game_score(Game *game,unsigned int added_score);
unsigned int get_game_score(Game *game);

// Pill Active
void update_game_pill_active(Game *game,bool new_pill_state);
bool get_pill_state(Game *game);

// Pac Man
void set_game_pac_man(Game *game,Pair new_pos, int new_sprite);
Pac_Man get_game_pac_man(Game *game);

void set_game_pac_man_pos(Game *game,Pair new_pos);
Pair get_game_pac_man_pos(Game *game);

// Ghosts
void set_game_ghosts(Game *game, Ghost *new_ghosts);
Ghost* get_game_ghosts(Game *game);

void set_game_ghosts_pos(Game *game,Pair* new_pos);
// Pair* get_game_ghosts_pos(Game *game);

// Pill
void add_pill_to_game(Game *game,Pill new_pill);
void delete_pill_from_game(Game *game,Pill pill);

void get_game_pills(Game* game,char *string);

// Fruit
void add_fruit_to_game(Game *game,Fruit new_fruit);
void delete_fruit_from_game(Game *game,Fruit fruit);
