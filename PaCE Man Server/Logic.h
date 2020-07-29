//
// Created by valva on 7/29/2020.
//

#ifndef PACE_MAN_SERVER_LOGIC_H
#define PACE_MAN_SERVER_LOGIC_H

struct pac_man {
    int x;
    int y;
    int sprite;
};

struct ghost {
    int x;
    int y;
    int color;
};

struct pill {
    int x;
    int y;
};

struct pac_man create_pac_man();

struct ghost create_ghost(int color);

struct pill create_pill(int x,int y);

#endif //PACE_MAN_SERVER_LOGIC_H
