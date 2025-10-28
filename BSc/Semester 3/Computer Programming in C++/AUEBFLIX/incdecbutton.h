#pragma once
#include "widget.h"
#include "circularbutton.h"

// Class IncDecButton : It is used to change between movies (Increase-Decrease Iterator) and to Increase-Decrease
// the Minimum and Maximum Year

class IncDecButton : public CircularButton {
private:
	short& reference;							// reference : reference to the value we want to increase-decrease
	short id_amount;							// id_amount : the amount we add to the reference ( 1 or -1)
	std::string image;							// image : image of the button
public:
	void draw() const;											// Draw method
	void update();												// Update method
	IncDecButton(unsigned short x,unsigned short y, short& r, short a, std::string img );		// Constructor
	//bool contains(float x, float y) const;						// Contains method
	void setHighlight(bool h) { highlighted = h; }				// SetHighlight method
	int getRef() const { return reference; }					// getRef method : returns the reference value
	int getIncVal() const { return id_amount; }				// getIncValue method : returns inc_amount (1 or -1)
};