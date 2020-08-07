//
// Created by valva on 7/29/2020.
//

#pragma once

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include "Constants.h"

// P A I R - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

typedef struct Pair {
    int x;
    int y;
} Pair;

/**
 * Creates a Pair struct
 * @param x
 * @param y
 * @return Pair struct
 */
Pair create_pair(int x,int y);

// P A C - M A N - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

typedef struct Pac_Man {
    Pair pos;
    int sprite;
} Pac_Man;

/**
 * Creates a Pac Man struct
 * @return Pac Man
 */
Pac_Man create_pac_man();

/**
 * Modifies the values of a Pac Man struct
 * @param p_m_ptr
 * @param new_pos
 * @param new_sprite
 */
void modify_pac_man(Pac_Man* p_m_ptr,Pair new_pos,int new_sprite);

/**
 * Modifies only the position value of a Pac Man struct
 * @param p_m_ptr
 * @param new_pos
 */
void modify_pac_man_position(Pac_Man* p_m_ptr,Pair new_pos);

// G H O S T - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  - - - - - - - - - - - - -

typedef struct Ghost {
    Pair pos;
    bool active;
    int color;
} Ghost;

/**
 * Creates a Ghost struct
 * @param color
 * @return Ghost struct
 */
Ghost create_ghost(int color);

/**
 * Modifies only the position of a ghost
 * @param ghost
 * @param new_pos
 */
void modify_ghost_position(Ghost* ghost,Pair new_pos);

/**
 * Makes true the active value of a Ghost struct
 * @param ghost
 */
void activate_ghost(Ghost* ghost);

/**
 * Makes false the active value of a Ghost struct
 * @param ghost
 */
void deactivate_ghost(Ghost* ghost);

// P I L L - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

typedef struct Pill {
    Pair pos;
} Pill;

/**
 * Creates a Pill struct
 * @param pos
 * @return Pill struct
 */
Pill create_pill(Pair pos);

struct Linked_List_Pill {
    Pill pill;
    struct Linked_List_Pill *next;
};

typedef struct Linked_List_Pill *P_Node;

/**
 * Creates a P Node struct
 * @return P Node
 */
P_Node create_p_node();

/**
 * Adds a P Node struct as the last element of the linked list of P Nodes passed
 * @param head of the linked list
 * @param pill to add in the node that will be added
 * @return A linked list of P Nodes with a new P Node at the back
 */
P_Node add_p_node(P_Node head, Pill pill);

/**
 * Deletes a given P Node of a given P Node linked list by searching the Pills in the P nodes
 * @param head of the linked list
 * @param pill of the P Node to delete
 * @return P Node linked list without a specific P Node
 */
P_Node delete_p_node(P_Node head,Pill pill);

// F R U I T - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  - - - - - - - - - - - - - -

typedef struct Fruit {
    Pair pos;
    int value;
} Fruit;

/**
 * Creates a Fruit struct
 * @param pos
 * @param value
 * @return Fruit struct
 */
Fruit create_fruit(Pair pos,int value);

struct Linked_List_Fruit {
    Fruit fruit;
    struct Linked_List_Fruit *next;
};

typedef struct Linked_List_Fruit *F_Node;

/**
 * Creates a F Node struct
 * @return F Node
 */
F_Node create_f_node();

/**
 * Adds a F Node struct as the last element of the linked list of F Nodes passed
 * @param head of the linked list
 * @param fruit to add in the node that will be added
 * @return linked list of F Nodes with a new F Node at the back
 */
F_Node add_f_node(F_Node head,Fruit fruit);

/**
 * Deletes a given F Node of a given F Node linked list by searching the Fruits in the F nodes
 * @param head of the linked list
 * @param fruit of the F Node to delete
 * @return F Node linked list without a specific F Node
 */
F_Node delete_f_node(F_Node head,Fruit fruit);

// D O T S - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  - - - - - - - - - - - - - - -

typedef struct Dot {
    Pair pos;
} Dot;

/**
 * Creates a Dot struct
 * @param pos
 * @return Dot struct
 */
Dot create_dot(Pair pos);

struct Linked_List_Dot {
    Dot dot;
    struct Linked_List_Dot *next;
};

typedef struct Linked_List_Dot *D_Node;

/**
 * Creates a D Note struct
 * @return D Note struct
 */
D_Node create_d_node();

/**
 * Adds a D Node struct as the last element of the linked list of D Nodes passed
 * @param head
 * @param dot
 * @return D Node linked list without a specific D Node
 */
D_Node add_d_node(D_Node head,Dot dot);

/**
 * Deletes a given D Node of a given D Node linked list by searching the Dots in the D nodes
 * @param head
 * @param dot
 * @return D Node linked list without a specific D Node
 */
D_Node delete_d_node(D_Node head,Dot dot);