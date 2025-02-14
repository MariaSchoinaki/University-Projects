#include "incdecbutton.h"
#include "graphics.h"
#include "config.h"

void IncDecButton::draw() const
{
	graphics::Brush br;
	float h = 1.0f * highlighted;								// Float h : 1 if highlighted == true else 0
	br.outline_opacity = 0.0f;										// If it is highlighted outline == 1
	br.fill_opacity = h;										// Fill_opacity is 0 so the disk is transparent
	graphics::drawDisk(pos_x, pos_y, BUTTON_SIZE * 0.6f, br);	// Draw the disk behind the button
	br.fill_opacity = 1.0f;
	br.fill_color[0] = 1.0f;
	br.fill_color[1] = 1.0f;
	br.fill_color[2] = 1.0f;
	br.texture = ASSET_PATH + std::string(image);				// We set the image as the brush texture
	br.outline_opacity = 0.0f;									// Outline is 0
	graphics::drawRect(pos_x, pos_y, BUTTON_SIZE, BUTTON_SIZE, br);		// Draw the button
}

void IncDecButton::update()	
{
	reference += id_amount;									// We add the inc_amount (1 or -1) to the reference
}

IncDecButton::IncDecButton(unsigned short x,unsigned short y, short& r, short a , std::string img)
	:reference(r),id_amount(a)
{												// Constructor of the button
	pos_x = x;									// x : x position of the button, y : y position of the mouse
	pos_y = y;									// r : the reference we want to increase-decrease
	image = img;								// a : the inc_amount (1 or -1), img : the image of the button
}

/**bool IncDecButton::contains(float x, float y) const		// X : x position of the mouse, Y : y position of the mouse
{
	return distance(x, y, pos_x, pos_y) < BUTTON_SIZE * 0.6f;	// returns true if the mouse is inside the range of the
}																// button
*/