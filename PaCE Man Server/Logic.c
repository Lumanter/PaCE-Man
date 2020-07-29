//
// Created by valva on 7/29/2020.
//

#include "Logic.h"

struct pac_man create_pac_man(){
    struct pac_man p_m;
    p_m.x = 0; // Starting X
    p_m.y = 0; // Staring Y
    p_m.sprite = 1; // Staring sprite
}


struct ghost create_ghost(int color) {
    struct ghost g;
    g.x = 0; // Starting X
    g.y = 0; // Starting Y
    g.color = color;
    return g;
};

struct pill create_pill(int x,int y){
    struct pill p;
    p.x = x;
    p.y = y;
    return p;
}