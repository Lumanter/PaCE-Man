//
// Created by valva on 7/29/2020.
//

#include "Game Structs.h"

// P A I R - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

/**
 * Creates a Pair struct
 * @param x
 * @param y
 * @return Pair struct
 */
Pair create_pair(int x,int y){
    Pair p;
    p.x = x;
    p.y = y;
    return p;
}

// P A C - M A N - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

/**
 * Creates a Pac Man struct
 * @return Pac Man
 */
Pac_Man create_pac_man(){
    Pac_Man p_m;
    p_m.pos = create_pair(INIT_PAC_POS_X,INIT_PAC_POS_Y);
    p_m.sprite = INIT_PAC_SPRITE;
    return p_m;
}

/**
 * Modifies the values of a Pac Man struct
 * @param p_m_ptr
 * @param new_pos
 * @param new_sprite
 */
void modify_pac_man(Pac_Man* p_m_ptr,Pair new_pos,int new_sprite){
    p_m_ptr->pos = new_pos;
    p_m_ptr->sprite = new_sprite;
}

/**
 * Modifies only the position value of a Pac Man struct
 * @param p_m_ptr
 * @param new_pos
 */
void modify_pac_man_position(Pac_Man* p_m_ptr,Pair new_pos){
    p_m_ptr->pos = new_pos;
}

// G H O S T - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  - - - - - - - - - - - - -

/**
 * Creates a Ghost struct
 * @param color
 * @return Ghost struct
 */
Ghost create_ghost(int color) {
    Ghost g;
    g.pos = create_pair(INIT_GHOST_POS_X,INIT_GHOST_POS_Y); // Starting pos
    g.active = INIT_GHOST_STATE;
    g.color = color;
    return g;
}

/**
 * Modifies only the position of a ghost
 * @param ghost
 * @param new_pos
 */
void modify_ghost_position(Ghost* ghost,Pair new_pos){
    ghost->pos = new_pos;
}

/**
 * Makes true the active value of a Ghost struct
 * @param ghost
 */
void activate_ghost(Ghost* ghost){
    ghost->active = true;
}

/**
 * Makes false the active value of a Ghost struct
 * @param ghost
 */
void deactivate_ghost(Ghost* ghost){
    ghost->active = false;
}

// P I L L - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -


/**
 * Creates a Pill struct
 * @param pos
 * @return Pill struct
 */
Pill create_pill(Pair pos){
    Pill p;
    p.pos = pos;
    return p;
}

/**
 * Creates a P Node struct
 * @return P Node
 */
P_Node create_p_node(){
    P_Node node;
    node = (P_Node)malloc(sizeof(struct Linked_List_Pill));
    node->next = NULL;
    return node;
}

/**
 * Adds a P Node struct as the last element of the linked list of P Nodes passed
 * @param head of the linked list
 * @param pill to add in the node that will be added
 * @return A linked list of P Nodes with a new P Node at the back
 */
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

/**
 * Deletes a given P Node of a given P Node linked list by searching the Pills in the P nodes
 * @param head of the linked list
 * @param pill of the P Node to delete
 * @return P Node linked list without a specific P Node
 */
P_Node delete_p_node(P_Node head,Pill pill){
    // The list is empty
    if (head->next == NULL){
        return head;
    }

    // The node to delete is on the first node
    int xc = head->pill.pos.x;
    int yc = head->pill.pos.y;

    if ((xc == pill.pos.x) && (yc == pill.pos.y)){
        P_Node temp = create_p_node();
        temp = head;

        head = head->next;
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

/**
 * Creates a Fruit struct
 * @param pos
 * @param value
 * @return Fruit struct
 */
Fruit create_fruit(Pair pos,int value){
    Fruit f;
    f.pos = pos;
    f.value = value;
    return f;
}

/**
 * Creates a F Node struct
 * @return F Node
 */
F_Node create_f_node(){
    F_Node node;
    node = (F_Node)malloc(sizeof(struct Linked_List_Fruit));
    node->next = NULL;
    return node;
}

/**
 * Adds a F Node struct as the last element of the linked list of F Nodes passed
 * @param head of the linked list
 * @param fruit to add in the node that will be added
 * @return linked list of F Nodes with a new F Node at the back
 */
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

/**
 * Deletes a given F Node of a given F Node linked list by searching the Fruits in the F nodes
 * @param head of the linked list
 * @param fruit of the F Node to delete
 * @return F Node linked list without a specific F Node
 */
F_Node delete_f_node(F_Node head,Fruit fruit){
    // The list is empty
    if (head->next == NULL){
        return head;
    }

    // The node to delete is on the first node
    int xc = head->fruit.pos.x;
    int yc = head->fruit.pos.y;

    if ((xc == fruit.pos.x) && (yc == fruit.pos.y)){
        F_Node temp = create_f_node();
        temp = head;

        head = head->next;
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

// D O T S - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  - - - - - - - - - - - - - - -

/**
 * Creates a Dot struct
 * @param pos
 * @return Dot struct
 */
Dot create_dot(Pair pos){
    Dot d;
    d.pos = pos;
    return d;
}

/**
 * Creates a D Note struct
 * @return D Note struct
 */
D_Node create_d_node(){
    D_Node node;
    node = (D_Node)malloc(sizeof(struct Linked_List_Dot));
    node->next = NULL;
    return node;
}

/**
 * Adds a D Node struct as the last element of the linked list of D Nodes passed
 * @param head
 * @param dot
 * @return D Node linked list without a specific D Node
 */
D_Node add_d_node(D_Node head,Dot dot){
    D_Node node, p;

    node = create_d_node();
    node->dot = dot;

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

/**
 * Deletes a given D Node of a given D Node linked list by searching the Dots in the D nodes
 * @param head
 * @param dot
 * @return D Node linked list without a specific D Node
 */
D_Node delete_d_node(D_Node head,Dot dot){
    // The list is empty
    if (head->next == NULL){
        return head;
    }

    // The node to delete is on the first node
    int xc = head->dot.pos.x;
    int yc = head->dot.pos.y;

    if ((xc == dot.pos.x) && (yc == dot.pos.y)){
        D_Node temp = create_d_node();
        temp = head;

        head = head->next;
        free(temp);
        return head;
    }

    // The node to delete is on the rest of the list
    D_Node previous = head->next;
    D_Node current = head->next;
    while (current->next != NULL){
        xc = current->dot.pos.x;
        yc = current->dot.pos.y;

        if ((xc == dot.pos.x) && (yc == dot.pos.y)){
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