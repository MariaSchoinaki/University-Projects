#pragma once
#include "widget.h"
#include "rectanglebutton.h"
#include <string>

// Class ClearFilterButton : Button used to clear all the filters

class ClearFilterButton : public RectangleButton {
public:
	void draw() const ;								// Draw method
	ClearFilterButton(unsigned short x,unsigned short y);	// Constructor
	//bool contains (float x, float y) const;						// Contains method
	void setHighlight(bool h) { highlighted = h; }				// setHighlight method
};