#include "clearfilterbutton.h"
#include "graphics.h"
#include "config.h"

void ClearFilterButton::draw() const
{
	graphics::Brush br;
	br.fill_color[0] = 0.8f;
	br.fill_color[1] = highlighted ? 0.5f : 0.6f;	// The final colour is determined by the value of highlighted
	br.fill_color[2] = 0.4f;
	graphics::drawRect(pos_x, pos_y, BUTTON_WIDTH, BUTTON_HEIGHT, br);
}

ClearFilterButton::ClearFilterButton(unsigned short x, unsigned short y)
{
	pos_x = x;									// Constructor
	pos_y = y;									// x : x position , y : y position
}

/*bool ClearFilterButton::contains(float x, float y) const	// Contains method, x : x of the mouse, y: y of the mouse
{
	return (x > pos_x - BUTTON_WIDTH/2 && x<pos_x + BUTTON_WIDTH/2 && y>pos_y - BUTTON_HEIGHT/2 && y < pos_y + BUTTON_HEIGHT/2);
}
*/