//
// Created by valva on 8/4/2020.
//

#include "Game.h"

/**
 * Creates a Game struct
 * @return Game struct
 */
Game create_game(){
    Game game;
    game.level = INIT_LEVEL;
    game.lives = INIT_LIVES;
    game.score = INIT_SCORE;
    game.pac_man = create_pac_man();
    game.pill_state = INIT_PILL_STATE;
    game.pills = NULL;
    game.fruits = NULL;
    game.dots = NULL;
    game.ghosts[0] = create_ghost(1);
    game.ghosts[1] = create_ghost(2);
    game.ghosts[2] = create_ghost(3);
    game.ghosts[3] = create_ghost(4);
    return game;
}

/**
 * Updates the value level from a given Game struct
 * @param game
 * @param new_level
 */
void update_game_level(Game *game,unsigned int new_level){
    game->level = new_level;
}

/**
 * Gets the value level from a given Game struct
 * @param game
 * @return level value
 */
unsigned int get_game_level(Game *game){
    return game->level;
}

/**
 * Updates the value lives from a given Game struct
 * @param game
 * @param new_lives
 */
void update_game_lives(Game *game,unsigned int new_lives){
    game->lives = new_lives;
}

/**
 * Gets the value lives from a given Game struct
 * @param game
 * @return lives value
 */
unsigned int get_game_lives(Game *game){
    return game->lives;
}

/**
 * Updates the value score from a given Game struct
 * @param game
 * @param added_score
 */
void update_game_score(Game *game,unsigned int added_score){
    game->score = added_score;
}

/**
 * Gets the value score from a given Game struct
 * @param game
 * @return score value
 */
unsigned int get_game_score(Game *game){
    return game->score;
}

/**
 * Updates the value pill active from a given Game struct
 * @param game
 * @param new_pill_state
 */
void update_game_pill_active(Game *game,bool new_pill_state){
    game->pill_state = new_pill_state;
}

/**
 * Gets the value pill state from a given Game struct
 * @param game
 * @return pill state value
 */
bool get_pill_state(Game *game){
    return game->pill_state;
}

/**
 * Sets the Pac Man struct inside the Game struct with new values
 * @param game
 * @param new_pos
 * @param new_sprite
 */
void set_game_pac_man(Game *game, Pair new_pos, int new_sprite){
    modify_pac_man(&game->pac_man,new_pos,new_sprite);
}

/**
 * Gets the Pac Man struct of a given Game struct
 * @param game
 * @return Game's Pac Man
 */
Pac_Man get_game_pac_man(Game *game){
    return game->pac_man;
}

/**
 * Sets the Pac Man struct's position inside the Game struct
 * @param game
 * @param new_pos
 */
void set_game_pac_man_pos(Game *game, Pair new_pos){
    modify_pac_man_position(&game->pac_man, new_pos);
}

/**
 * Gets the Game's Pac Man position
 * @param game
 * @retur Game's Pac Man position
 */
Pair get_game_pac_man_pos(Game *game){
    return game->pac_man.pos;
}

/**
 * Sets the Game's list of Ghost structs with new ones
 * @param game
 * @param new_ghosts
 */
void set_game_ghosts(Game *game, Ghost *new_ghosts){
    game->ghosts[0] = new_ghosts[0];
    game->ghosts[1] = new_ghosts[1];
    game->ghosts[2] = new_ghosts[2];
    game->ghosts[3] = new_ghosts[3];
}

/**
 * Returns the Game's list of Ghosts
 * @param game
 * @return Game's list of Ghosts
 */
Ghost* get_game_ghosts(Game *game){
    return game->ghosts;
}

/**
 * Sets the Game's list of Ghosts positions to new ones
 * @param game
 * @param new_pos
 */
void set_game_ghosts_pos(Game *game,Pair* new_pos){
    modify_ghost_position(&game->ghosts[0],new_pos[0]);
    modify_ghost_position(&game->ghosts[1],new_pos[1]);
    modify_ghost_position(&game->ghosts[2],new_pos[2]);
    modify_ghost_position(&game->ghosts[3],new_pos[3]);
}

/**
 * Adds a Pill to the Game's linked list of pills
 * @param game
 * @param new_pill
 */
void add_pill_to_game(Game *game, Pill new_pill){
    game->pills = add_p_node(game->pills,new_pill);
}

/**
 * Deletes a given Pill form the Game's linked list of pills
 * @param game
 * @param pill
 */
void delete_pill_from_game(Game *game,Pill pill){
    game->pills = delete_p_node(game->pills,pill);
}

/**
 * Gets, as a string, the positions of all the pills in the Game's linked list of pills
 * @param game
 * @param string
 */
void get_game_pills(Game *game,char *string){
    P_Node t = game->pills;
    while (t != NULL){
        int x = t->pill.pos.x;
        int y = t->pill.pos.y;

        char sub_str_1[500];
        char sub_str_2[500];

        itoa(x, sub_str_1);
        strcat(sub_str_1, ",");
        itoa(y, sub_str_2);
        strcat(sub_str_2, ",");
        t = t->next;

        strcat(sub_str_1, sub_str_2);
        strcat(string, sub_str_1);
    }
}

/**
 * Adds a Fruit to the Game's linked list of fruits
 * @param game
 * @param new_fruit
 */
void add_fruit_to_game(Game *game,Fruit new_fruit){
    game->fruits = add_f_node(game->fruits,new_fruit);
}

/**
 * Deletes a given Fruit from the Game's linked list of fruits
 * @param game
 * @param fruit
 */
void delete_fruit_from_game(Game *game,Fruit fruit){
    game->fruits = delete_f_node(game->fruits,fruit);
}

/**
 * Gets, as a string, the positions of all the fruits in the Game's linked list of fruits
 * @param game
 * @param string
 */
void get_game_fruits(Game *game,char *string){
    F_Node t = game->fruits;
    while (t != NULL){
        int x = t->fruit.pos.x;
        int y = t->fruit.pos.y;

        char sub_str_1[500];
        char sub_str_2[500];

        itoa(x, sub_str_1);
        strcat(sub_str_1, ",");
        itoa(y, sub_str_2);
        strcat(sub_str_2, ",");
        t = t->next;

        strcat(sub_str_1, sub_str_2);
        strcat(string, sub_str_1);
    }
}

void itoa(int n, char *s) {
    int i, sign;

    if ((sign = n) < 0)  /* record sign */
        n = -n;          /* make n positive */
    i = 0;
    do {       /* generate digits in reverse order */
        s[i++] = n % 10 + '0';   /* get next digit */
    } while ((n /= 10) > 0);     /* delete it */
    if (sign < 0)
        s[i++] = '-';
    s[i] = '\0';
    reverse(s);
}

void reverse(char *s) {
    int i, j;
    char c;

    for (i = 0, j = strlen(s)-1; i<j; i++, j--) {
        c = s[i];
        s[i] = s[j];
        s[j] = c;
    }
}


/**
 * Adds a Dot to the Game's linked list of dots
 * @param game
 * @param new_dot
 */
void add_dot_to_game(Game *game,Dot new_dots){
    game->dots = add_d_node(game->dots,new_dots);
}

/**
 * Deletes a given Dot from the Game's linked list of dots
 * @param game
 * @param dot
 */
void delete_dot_from_game(Game *game,Dot dot){
    game->dots = delete_d_node(game->dots,dot);
}

/**
 * Updates the Game's state
 * @param game
 * @param newState
 */
void update_game_state(Game *game, int newState) {
    game->gameState = newState;
}

/**
 * Gets the Game's state
 * @param game
 * @return  Game's state
 */
int get_game_state(Game *game) {
    return game->gameState;
}