#pragma once
#include <string>

// Abstract class Widget

class Widget {
protected:
	unsigned short pos_x;									// X position of the widget
	unsigned short pos_y;									// Y position of the widget
	bool highlighted = false;								// Bool variable: True if it is highlighted else False

public:
	virtual void draw() const {};							// Pure virtual method draw
	virtual bool contains(float x, float y) { return true; }	// Pure virtual method contains
	virtual void setHighlight(bool h) = 0;					// Pure virtual method setHighlight
	virtual ~Widget() {};									// Virtual destructor
};