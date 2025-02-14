#include "checkbox.h"
#include "config.h"
#include "graphics.h"

void CheckBox::draw() const
{
	graphics::Brush br;
	float h = 1.0f * highlighted;		// Float h : 1 if highlighted == true, else 0
	br.outline_opacity = h;				// Outline : 1 if highlighted == true, else 0
	br.fill_opacity = 0.0f;				// Opacity = 0 , means that the disk is transparent
	graphics::drawDisk(pos_x, pos_y, CHECKBOX_SIZE * 0.45f, br);
	br.fill_opacity = 1.0f;
	br.fill_color[0] = 1.0f;
	br.fill_color[1] = 1.0f;
	br.fill_color[2] = 1.0f;
	br.texture = m_active? ASSET_PATH + std::string("checkbox_withtick.png") : ASSET_PATH + std::string(image);
	// The texure of the checkbox is the unticked image if the checkbox is not active (m_active == false)
	// The texure of the checkbox is the ticked image if the checkbox is active (m_active == true)

	br.outline_opacity = 0.0f;
	graphics::drawRect(pos_x, pos_y, CHECKBOX_SIZE, CHECKBOX_SIZE, br); // Draw the rectangle
	graphics::drawText(pos_x - 14, pos_y - 18, 14, gen, br);	// And draw the text (genre) above the rectangle
}

CheckBox::CheckBox(unsigned short x, unsigned short y, std::string img, std::string gen)
	: gen(gen)
{													// Constructor
	pos_x = x;										// x : x position , y : y position
	pos_y = y;										// img : image of the checkbox
	image = img;									// gen : the genre that the checkbox contains
}

/*bool CheckBox::contains(float x, float y) const			// Contains method, x : x of the mouse, y : y of the mouse
{
	return distance(x, y, pos_x, pos_y) < CHECKBOX_SIZE * 0.5f;
}
*/