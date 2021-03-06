//
// Created by valva on 8/4/2020.
//

#include "Game.h"

Game create_game(){
    Game game;
    game.level = INIT_LEVEL;
    game.lives = INIT_LIVES;
    game.score = INIT_SCORE;
    game.pac_man = create_pac_man();
    game.pill_state = INIT_PILL_STATE;
    game.pills = NULL;
    game.fruits = NULL;
    game.ghosts[0] = create_ghost(1);
    game.ghosts[1] = create_ghost(2);
    game.ghosts[2] = create_ghost(3);
    game.ghosts[3] = create_ghost(4);
    return game;
}

void update_game_level(Game *game,unsigned int new_level){
    game->level = new_level;
}

unsigned int get_game_level(Game *game){
    return game->level;
}

void update_game_lives(Game *game,unsigned int new_lives){
    game->lives = new_lives;
}

unsigned int get_game_lives(Game *game){
    return game->lives;
}

void update_game_score(Game *game,unsigned int added_score){
    game->score = added_score;
}

unsigned int get_game_score(Game *game){
    return game->score;
}

void update_game_pill_active(Game *game,bool new_pill_state){
    game->pill_state = new_pill_state;
}

bool get_pill_state(Game *game){
    return game->pill_state;
}

void set_game_pac_man(Game *game, Pair new_pos, int new_sprite){
    modify_pac_man(&game->pac_man,new_pos,new_sprite);
}

Pac_Man get_game_pac_man(Game *game){
    return game->pac_man;
}

void set_game_pac_man_pos(Game *game, Pair new_pos){
    modify_pac_man_position(&game->pac_man, new_pos);
}

Pair get_game_pac_man_pos(Game *game){
    return game->pac_man.pos;
}

void set_game_ghosts(Game *game, Ghost *new_ghosts){
    game->ghosts[0] = new_ghosts[0];
    game->ghosts[1] = new_ghosts[1];
    game->ghosts[2] = new_ghosts[2];
    game->ghosts[3] = new_ghosts[3];
}

Ghost* get_game_ghosts(Game *game){
    return game->ghosts;
}

void set_game_ghosts_pos(Game *game,Pair* new_pos){
    modify_ghost_position(&game->ghosts[0],new_pos[0]);
    modify_ghost_position(&game->ghosts[1],new_pos[1]);
    modify_ghost_position(&game->ghosts[2],new_pos[2]);
    modify_ghost_position(&game->ghosts[3],new_pos[3]);
}

void add_pill_to_game(Game *game, Pill new_pill){
    game->pills = add_p_node(game->pills,new_pill);
}

void delete_pill_from_game(Game *game,Pill pill){
    game->pills = delete_p_node(game->pills,pill);
}

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

void add_fruit_to_game(Game *game,Fruit new_fruit){
    game->fruits = add_f_node(game->fruits,new_fruit);
}
void delete_fruit_from_game(Game *game,Fruit fruit){
    game->fruits = delete_f_node(game->fruits,fruit);
}

void update_game_state(Game *game, int newState) {
    game->gameState = newState;
}

int get_game_state(Game *game) {
    return game->gameState;
}


void get_game_fruits(Game *game,char *string){
    F_Node t = game->fruits;
    while (t != NULL){
        int x = t->fruit.pos.x;
        int y = t->fruit.pos.y;

        char sub_str_1[500];
        char sub_str_2[500];

        itoa(x, sub_str_1, 10);
        strcat(sub_str_1, ",");
        itoa(y, sub_str_2, 10);
        strcat(sub_str_2, ",");
        t = t->next;

        strcat(sub_str_1, sub_str_2);
        strcat(string, sub_str_1);
    }
}