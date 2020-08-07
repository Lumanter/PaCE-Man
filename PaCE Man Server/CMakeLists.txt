# cmake_minimum_required(VERSION <specify CMake version here>)
project(PaCE_Man_Server C)

set(CMAKE_C_STANDARD 99)

add_executable(PaCE_Man_Server main.c Server.c Server.h "Game Structs.c" "Game Structs.h" Game.c Game.h Admin.c Admin.h Constants.h)