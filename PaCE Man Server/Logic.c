//
// Created by valva on 7/29/2020.
//

#include "Logic.h"

Pair create_pair(int x,int y){
    Pair p;
    p.x = x;
    p.y = y;
    return p;
}

Pac_Man create_pac_man(){
    Pac_Man p_m;
    p_m.pos = create_pair(200,300); // Starting pos
    p_m.sprite = 0; // Staring sprite
    return p_m;
}

void modify_pac_man(Pac_Man* p_m_ptr,Pair new_pos,int new_sprite){
    p_m_ptr->pos = new_pos;
    p_m_ptr->sprite = new_sprite;
}

void modify_pac_man_position(Pac_Man* p_m_ptr,Pair new_pos){
    p_m_ptr->pos = new_pos;
}

Ghost create_ghost(int color) {
    Ghost g;
    g.pos = create_pair(200,180); // Starting pos
    g.active = false;
    g.color = color;
    return g;
}

void modify_ghost_position(Ghost* g,Pair new_pos){
    g->pos = new_pos;
}

void activate_ghost(Ghost* g){
    g->active = true;
}

void deactivate_ghost(Ghost* g){
    g->active = false;
}

Pill create_pill(Pair pos){
    Pill p;
    p.pos = pos;
    return p;
}

Game create_game(){
    Game game;
    game.level = 1;
    game.lives = 3;
    game.pac_man = create_pac_man();
    game.pill_active = false;
    game.ghosts[0] = create_ghost(1);
    game.ghosts[1] = create_ghost(2);
    game.ghosts[2] = create_ghost(3);
    game.ghosts[3] = create_ghost(4);
    return game;
}

void update_game_pac_man(Game *game,Pair new_pos,int new_sprite){
    modify_pac_man(&game->pac_man,new_pos,new_sprite);
}

void update_game_pac_man_pos(Game *game,Pair new_pos){
    modify_pac_man_position(&game->pac_man, new_pos);
}

void update_game_ghosts_pos(Game *game,Pair* new_pos){
    modify_ghost_position(&game->ghosts[0],new_pos[0]);
    modify_ghost_position(&game->ghosts[1],new_pos[1]);
    modify_ghost_position(&game->ghosts[2],new_pos[2]);
    modify_ghost_position(&game->ghosts[3],new_pos[3]);
}