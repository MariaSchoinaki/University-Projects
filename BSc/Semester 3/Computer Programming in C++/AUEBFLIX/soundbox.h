#pragma once
#include "widget.h"
#include "circularbutton.h"

// SoundBox class : used for the Music

class SoundBox : public CircularButton {
private:							
	bool m_active = false;						// Bool variable that shows if the soundbox is active
	std::string image;							// Image of the soundbox

public:
	void draw() const;							// Draw method
	SoundBox(unsigned short x, unsigned short y, std::string img);		// Constructor
	//bool contains(float x, float y) const;					// Contains method
	void setHighlight(bool h) { highlighted = h; }			// setHighlight method
	void setActive(bool a) { m_active = a; }				// setActive method
};
