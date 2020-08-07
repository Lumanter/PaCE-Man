//
// Created by valva on 7/29/2020.
//

#include "Game Structs.h"

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
    p_m.pos = create_pair(INIT_PAC_POS_X,INIT_PAC_POS_Y);
    p_m.sprite = INIT_PAC_SPRITE;
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
    g.pos = create_pair(INIT_GHOST_POS_X,INIT_GHOST_POS_Y); // Starting pos
    g.active = INIT_GHOST_STATE;
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

    // The node to delete is on the rest of the list
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

// F R U I T - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  - - - - - - - - - - - - - -

Fruit create_fruit(Pair pos,int value){
    Fruit f;
    f.pos = pos;
    f.value = value;
    return f;
}

F_Node create_f_node(){
    F_Node node;
    node = (F_Node)malloc(sizeof(struct Linked_List_Fruit));
    node->next = NULL;
    return node;
}

F_Node add_f_node(F_Node head,Fruit fruit){
    F_Node node, p;

    node = create_f_node();
    node->fruit = fruit;

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

F_Node delete_f_node(F_Node head,Fruit fruit){
    // The list is empty
    if (head->next == NULL){
        return head;
    }

    // The node to delete is on the first node
    int xc = head->next->fruit.pos.x;
    int yc = head->next->fruit.pos.y;

    if ((xc == fruit.pos.x) && (yc == fruit.pos.y)){
        F_Node temp = create_f_node();
        temp = head->next;

        head->next = head->next->next;
        free(temp);
        return head;
    }

    // The node to delete is on the rest of the list
    F_Node previous = head->next;
    F_Node current = head->next;
    while (current->next != NULL){
        xc = current->fruit.pos.x;
        yc = current->fruit.pos.y;

        if ((xc == fruit.pos.x) && (yc == fruit.pos.y)){
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

