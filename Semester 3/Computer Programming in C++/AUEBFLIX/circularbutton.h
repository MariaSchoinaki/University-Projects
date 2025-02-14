#pragma once
#include "widget.h"
#include "config.h"

// CircularButton : Class used for the 2 level inheritance of the circular buttons 

class CircularButton : public Widget {

public:
	bool contains(float x, float y) { return distance(x, y, pos_x, pos_y) < BUTTON_SIZE * 0.7f; }
	// contains :      x,y : position of the mouse in the canvas
	// returns true if the mouse is inside the circle
};