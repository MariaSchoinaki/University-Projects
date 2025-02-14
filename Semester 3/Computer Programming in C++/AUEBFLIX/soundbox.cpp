#include "soundbox.h"
#include "config.h"
#include "graphics.h"

void SoundBox::draw() const
{
	graphics::Brush br;
	float h = 1.0f * highlighted;		// Float h : 1 if highlighted == true, else 0
	br.outline_opacity = h;				// Outline : 1 if highlighted == true, else 0
	br.fill_opacity = 0.0f;				// Opacity = 0 , means that the disk is transparent
	graphics::drawDisk(pos_x, pos_y, SOUNDBOX_SIZE * 0.55f, br);
	br.fill_opacity = 1.0f;
	br.fill_color[0] = 1.0f;
	br.fill_color[1] = 1.0f;
	br.fill_color[2] = 1.0f;
	br.texture = m_active ? ASSET_PATH + std::string("soundoff.png") : ASSET_PATH + std::string(image);
	// The texure of the soundbox is the unmuted image if the soundbox is not active (m_active == false)
	// The texure of the soundbox is the muted image if the soundboxbox is active (m_active == true)

	br.outline_opacity = 0.0f;
	graphics::drawRect(pos_x, pos_y, SOUNDBOX_SIZE, SOUNDBOX_SIZE, br); // Draw the rectangle
}

SoundBox::SoundBox(unsigned short x, unsigned short y, std::string img)
{													// Constructor
	pos_x = x;										// x : x position , y : y position
	pos_y = y;										
	image = img;									// img : image of the soundbox
}
