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
    D_Node dots;
    Pac_Man pac_man;
    Ghost ghosts[4];
    int gameState;
} Game;

/**
 * Creates a Game struct
 * @return Game struct
 */
Game create_game();

// Level

/**
 * Updates the value level from a given Game struct
 * @param game
 * @param new_level
 */
void update_game_level(Game *game,unsigned int new_level);

/**
 * Gets the value level from a given Game struct
 * @param game
 * @return level value
 */
unsigned int get_game_level(Game *game);

// Lives

/**
 * Updates the value lives from a given Game struct
 * @param game
 * @param new_lives
 */
void update_game_lives(Game *game,unsigned int new_lives);

/**
 * Gets the value lives from a given Game struct
 * @param game
 * @return lives value
 */
unsigned int get_game_lives(Game *game);

// Score

/**
 * Updates the value score from a given Game struct
 * @param game
 * @param added_score
 */
void update_game_score(Game *game,unsigned int added_score);

/**
 * Gets the value score from a given Game struct
 * @param game
 * @return score value
 */
unsigned int get_game_score(Game *game);

// Pill Active

/**
 * Updates the value pill active from a given Game struct
 * @param game
 * @param new_pill_state
 */
void update_game_pill_active(Game *game,bool new_pill_state);

/**
 * Gets the value pill state from a given Game struct
 * @param game
 * @return pill state value
 */
bool get_pill_state(Game *game);

// Pac Man

/**
 * Sets the Pac Man struct inside the Game struct with new values
 * @param game
 * @param new_pos
 * @param new_sprite
 */
void set_game_pac_man(Game *game,Pair new_pos, int new_sprite);

/**
 * Gets the Pac Man struct of a given Game struct
 * @param game
 * @return Game's Pac Man
 */
Pac_Man get_game_pac_man(Game *game);

/**
 * Sets the Pac Man struct's position inside the Game struct
 * @param game
 * @param new_pos
 */
void set_game_pac_man_pos(Game *game,Pair new_pos);

/**
 * Gets the Game's Pac Man position
 * @param game
 * @retur Game's Pac Man position
 */
Pair get_game_pac_man_pos(Game *game);

// Ghosts

/**
 * Sets the Game's list of Ghost structs with new ones
 * @param game
 * @param new_ghosts
 */
void set_game_ghosts(Game *game, Ghost *new_ghosts);

/**
 * Returns the Game's list of Ghosts
 * @param game
 * @return Game's list of Ghosts
 */
Ghost* get_game_ghosts(Game *game);

/**
 * Sets the Game's list of Ghosts positions to new ones
 * @param game
 * @param new_pos
 */
void set_game_ghosts_pos(Game *game,Pair* new_pos);

// Pill

/**
 * Adds a Pill to the Game's linked list of pills
 * @param game
 * @param new_pill
 */
void add_pill_to_game(Game *game,Pill new_pill);

/**
 * Deletes a given Pill form the Game's linked list of pills
 * @param game
 * @param pill
 */
void delete_pill_from_game(Game *game,Pill pill);

/**
 * Gets, as a string, the positions of all the pills in the Game's linked list of pills
 * @param game
 * @param string
 */
void get_game_pills(Game* game,char *string);

// Fruit

/**
 * Adds a Fruit to the Game's linked list of fruits
 * @param game
 * @param new_fruit
 */
void add_fruit_to_game(Game *game,Fruit new_fruit);

/**
 * Deletes a given Fruit from the Game's linked list of fruits
 * @param game
 * @param fruit
 */
void delete_fruit_from_game(Game *game,Fruit fruit);

/**
 * Gets, as a string, the positions of all the fruits in the Game's linked list of fruits
 * @param game
 * @param string
 */
void get_game_fruits(Game *game,char *string);

// Dot

/**
 * Adds a Dot to the Game's linked list of dots
 * @param game
 * @param new_dot
 */
void add_dot_to_game(Game *game,Dot new_dot);

/**
 * Deletes a given Dot from the Game's linked list of dots
 * @param game
 * @param dot
 */
void delete_dot_from_game(Game *game,Dot dot);

/**
 *
 * @param game
 * @param string
 */
void get_game_dots(Game *game,char *string);

// Game state

/**
 * Updates the Game's state
 * @param game
 * @param newState
 */
void update_game_state(Game *game, int newState);

/**
 * Gets the Game's state
 * @param game
 * @return  Game's state
 */
int get_game_state(Game* game);

void itoa(int n, char s[]);

void reverse(char s[]);
