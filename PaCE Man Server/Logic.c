//
// Created by valva on 7/29/2020.
//

#include "Logic.h"


Pac_Man create_pac_man(){
    Pac_Man p_m;
    p_m.x = 0; // Starting X
    p_m.y = 0; // Staring Y
    p_m.sprite = 1; // Staring sprite
    return p_m;
}


Ghost create_ghost(int color) {
    Ghost g;
    g.x = 0; // Starting X
    g.y = 0; // Starting Y
    g.color = color;
    return g;
};

Pill create_pill(int x,int y){
    Pill p;
    p.x = x;
    p.y = y;
    return p;
}