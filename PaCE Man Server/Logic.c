//
// Created by valva on 7/29/2020.
//

#include "Logic.h"

// P A I R - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

Pair create_pair(int x,int y){
    Pair p;
    p.x = x;
    p.y = y;
    return p;
}

// P A C - M A N - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

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

// G H O S T - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  - - - - - - - - - - - - -

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

// P I L L - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

Pill create_pill(Pair pos){
    Pill p;
    p.pos = pos;
    return p;
}

P_Node create_p_node(){
    P_Node node;
    node = (P_Node)malloc(sizeof(struct Linked_List_Pill));
    node->next = NULL;
    return node;
}

P_Node add_p_node(P_Node head,Pill pill){
    P_Node node, p;

    node = create_p_node();
    node->pill = pill;

    if (head == NULL){
        head = node;
    }
    else {
        p = head;
        while (p->next != NULL){
            p = p->next;
        }
        p->next = node;
    }
    return head;
}

P_Node delete_p_node(P_Node head,Pill pill){
    // The list is empty
    if (head->next == NULL){
        return head;
    }

    // The node to delete is on the first node
    int xc = head->next->pill.pos.x;
    int yc = head->next->pill.pos.y;

    if ((xc == pill.pos.x) && (yc == pill.pos.y)){
        P_Node temp = create_p_node();
        temp = head->next;

        head->next = head->next->next;
        free(temp);
        return head;
    }

    P_Node previous = head->next;
    P_Node current = head->next;
    while (current->next != NULL){
        xc = current->pill.pos.x;
        yc = current->pill.pos.y;

        if ((xc == pill.pos.x) && (yc == pill.pos.y)){
            previous->next = current->next;
            free(current);
            return head;
        }
        else {
            previous = current;
            current = current->next;
        }
    }
    return head;
}

// G A M E - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  - - - - - - - - - - - - - -

Game create_game(){
    Game game;
    game.level = 1;
    game.lives = 3;
    game.score = 0;
    game.pac_man = create_pac_man();
    game.pill_state = false;
    game.pills = NULL;
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
    game->score += added_score;
}

unsigned int get_game_score(Game *game){
    return game->score;
}

void update_game_pill_active(Game *game,bool new_pill_state){
    game->pill_state = new_pill_state;
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

void add_pill_to_game(Game *game, Pill new_pill){
    game->pills = add_p_node(game->pills,new_pill);
}

void delete_pill_from_game(Game *game,Pill pill){
    game->pills = delete_p_node(game->pills,pill);
}