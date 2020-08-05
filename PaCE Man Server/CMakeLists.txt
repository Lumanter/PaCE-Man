# cmake_minimum_required(VERSION <specify CMake version here>)
project(PaCE_Man_Server C)

set(CMAKE_C_STANDARD 99)

add_executable(PaCE_Man_Server main.c Server.c Server.h Logic.c Logic.h Game.c Game.h)