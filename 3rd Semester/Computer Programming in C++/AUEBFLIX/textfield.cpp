#include "textfield.h"
#include "graphics.h"
#include "scancodes.h"

void TextField::draw() const
{
	graphics::Brush br;
	std::string info = Name + " : ";							
	graphics::drawText(pos_x - 120, pos_y + 5, 17, info, br);	// Draws the name of textfield (e.x. Title:)
	float h = 1.0f * highlighted;								
	br.fill_color[0] = h;
	br.fill_color[1] = h;										// If the cursor is inside the rectangle the color of the rect is
	br.fill_color[2] = h;										// white (1.0) else black (0.0)
	graphics::drawRect(pos_x, pos_y, 105, 20, br);				// Draws a rectangle behind the textfield rectangle
	br.fill_color[0] = 0.0f;
	br.fill_color[1] = 0.0f;
	br.fill_color[2] = 0.0f;
	graphics::drawRect(pos_x, pos_y, 100, 15, br);				// Draws the textfield rectangle
	br.fill_color[0] = 1.0f;
	br.fill_color[1] = 1.0f;
	br.fill_color[2] = 1.0f;
	info="";
	for (char a : text) {										
		info += a;
	}
	graphics::drawText(pos_x - 47 , pos_y + 5, 13, info, br);	// Draws the written text inside the rectangle
}

TextField::TextField(unsigned short x, unsigned short y, std::string name): Name(name) {
	pos_x = x;								// Constructor of the textfield
	pos_y = y;								//pos_x,pos_y: x,y position , Name: name of the textfield
}

void TextField::addChar(char a)
{
	text.push_back(a);				// Method addChar: Adds the char a in the vector
}

void TextField::update()			// Update method
{
		static float delay = 0.0f;					// Variable that keeps the delay
		static int prev = 0;						// Variable that keeps the previous button
		char ascii = 0;								// Variable that keeps the ascii code of the button pressed
		delay += graphics::getDeltaTime();			// Update the delay
			
		for (int i = graphics::SCANCODE_A; i <= graphics::SCANCODE_SPACE; i++) {	// Run all the scan codes
			if (graphics::getKeyState((graphics::scancode_t)i)) {					// If one of them is pressed
				
				if (i==40 || i == 41 || i == 43) {			// If escape or tab or enter is pressed continue
					continue;
				}
				if (prev == i && delay < 300.0f) // if the button pressed is the same as previous and the delay is lower than 300
					continue;					// continue
				else {
					prev = i;					// update the previous button
					delay = 0.0f;				// set the delay as zero
					if (i == 42) {				// If the button pressed is backspace
						if(!text.empty())		// If there is at least one char in the vector
							popChar();			// remove the char
					}
				}
				if (i != 42) {					// If the button pressed is backspace
					if (i == 44) {				// If the button pressed is space
						addChar(32);			// add the space char
					}
					else {
						ascii = (char)(i + 93);		// get the ascii code of the button pressed
						addChar(ascii);				// add the char to the vector
					}
				}	
			}
		}
}

void TextField::popChar()						// Method popChar
{
	text.pop_back();							// Remove the char from the back of the vector
}

std::string TextField::getStr()					// Method getStr: Returns the string that the vector contains
{
	std::string temps = "";						// String variable temp
	for (char a : text) {						// for every char in the vector
		temps += a;								// Update the string by adding the char
	}
	return std::string(temps);					// return the final string
}

