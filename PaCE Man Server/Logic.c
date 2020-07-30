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
    p_m.pos = create_pair(0,0); // Starting pos
    p_m.sprite = 1; // Staring sprite
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
    g.pos = create_pair(0,0); // Starting pos
    g.color = color;
    return g;
};

void modify_ghost_position(Ghost* g,Pair new_pos){
    g->pos = new_pos;
}

Pill create_pill(Pair pos){
    Pill p;
    p.pos = pos;
    return p;
}