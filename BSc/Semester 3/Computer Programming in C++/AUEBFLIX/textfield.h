#pragma once
#include "rectanglebutton.h"
#include <vector>

class TextField : public RectangleButton {
	std::vector<char> text;
	std::string Name;
public:
	void draw() const;								// Draw method
	TextField(unsigned short x, unsigned short y, std::string name);	// Constructor
	void setHighlight(bool h) { highlighted = h; }				// setHighlight method
	void addChar(char a);
	void update();
	void popChar();
	int getSize() { return text.size(); }
	void clear() { text.clear(); }
	std::string getStr();
};