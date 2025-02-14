#pragma once
#include "movie.h"
#include "incdecbutton.h"
#include "checkbox.h"
#include "clearfilterbutton.h"
#include "textfield.h"
#include "soundbox.h"
#include <list>
class FilmBrowser 
{
	Movie* movies[13];								// Array with pointers to the movies
	short iterator = 0;								// iterator of the array
	short minYear = 2007;							// Minimum Year of the movies
	short maxYear = 2022;							// Maximum Year of the movies
	CheckBox* m_activecheckbox = nullptr;			// Pointer to the active checkbox (only 1 at time)
	SoundBox* m_activesoundbox = nullptr;			// Pointer to the active soundbox
	std::list<Widget*> m_buttons;					// List with all the buttons
	bool right_b = false;							// Bool variable that shows the direction of the last movie change
	// True means that the user pressed the right button to navigate to the next movie
	// False means that the user pressed the left button to navigate to the previous movie
	// This variable is used in the filtered search
	TextField* m_textf[3];
public:
	void draw() const;								// Draw method
	void update();									// Update method
	void init();									// Init method
	short getIter() const { return iterator; }		// Iterator getter
	short getMinYear() const { return minYear; }	// Minimum year getter
	short getMaxYear() const{ return maxYear; }		// Maximum year getter
	~FilmBrowser();									// Destructor of the Film Browser
};