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
    Game g;
    g.level = 1;
    g.lives = 3;
    g.pac_man = create_pac_man();
    g.pill_active = false;
    g.ghosts[0] = create_ghost(1);
    g.ghosts[1] = create_ghost(2);
    g.ghosts[2] = create_ghost(3);
    g.ghosts[3] = create_ghost(4);
}