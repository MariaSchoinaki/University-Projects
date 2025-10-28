#pragma once
#include "widget.h"
#include "circularbutton.h"

// CheckBox class : used for the genre filters

class CheckBox : public CircularButton {
private:
	std::string gen;							// String of the genre
	bool m_active = false;						// Bool variable that shows if the checkbox is active
	std::string image;							// Image of the checkbox

public:
	void draw() const;							// Draw method
	CheckBox(unsigned short x, unsigned short y,std::string img, std::string gen );		// Constructor
	//bool contains(float x, float y) const;					// Contains method
	void setHighlight(bool h) { highlighted = h; }			// setHighlight method
	std::string getGen() const { return gen; }				// Genre getter
	void setActive(bool a) { m_active = a; }				// setActive method
};