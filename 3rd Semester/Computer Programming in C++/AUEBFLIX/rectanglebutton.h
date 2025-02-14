#pragma once
#include "widget.h"
#include "config.h"

// RectangleButton : Class used for the 2 level inheritance of the rectangle buttons 

class RectangleButton : public Widget {

public:
	bool contains(float x, float y) { return (x > pos_x - BUTTON_WIDTH / 2 && x<pos_x + BUTTON_WIDTH / 2 && y>pos_y - BUTTON_HEIGHT / 2 && y < pos_y + BUTTON_HEIGHT / 2); }
	// contains :      x,y : position of the mouse in the canvas
	// returns true if the mouse is inside the rectangle
};