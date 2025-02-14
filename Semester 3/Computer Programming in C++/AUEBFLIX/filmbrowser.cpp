#include "filmbrowser.h"
#include "graphics.h"
#include "config.h"
#include <string>
#include <algorithm>	// Contains the transform function that retains specific characters of a string and turns them to lower case

// inline functions for the filtered search
inline int getNextMovieNGenre(const short& i ,Movie** m, const short& mYear , const short& MYear, const bool& b,TextField** t);
inline int getNextMovieWGenre(const short& i,const std::string& gen,Movie** m, const short& mYear, const short& MYear, const bool& b, TextField** t);

void FilmBrowser::draw() const
{
	
	//------------- Background draw ----------------------------------------
	std::string info;
	graphics::Brush br;
	br.texture = ASSET_PATH + std::string("background.png");
	br.outline_opacity = 0.0f;
	graphics::drawRect(CANVAS_WIDTH / 2, CANVAS_HEIGHT / 2, CANVAS_WIDTH, CANVAS_HEIGHT, br);
	info = "AUEBFLIX";
	graphics::drawText(10, 30, 40,info, br);
	info = "Filters:";
	graphics::drawText(700, 90, 25, info, br);
	info = "Min Year :";
	graphics::drawText(740, 130, 18, info, br);
	info = std::to_string(minYear);
	graphics::drawText(860, 130, 17, info, br);
	info = "Max Year :";
	graphics::drawText(740, 180, 18, info, br);
	info = std::to_string(maxYear);
	graphics::drawText(860, 180, 17, info, br);
	info = "Clear Filters";
	graphics::drawText(841, 90, 17, info, br);
	info = "Rating:";
	graphics::drawText(130, 350, 16, info, br);
	//----------------------------------------------------------------------

	//------- Movie draw -------
	movies[iterator]->draw();
	//--------------------------

	//------- Button draw ------
	for (auto b : m_buttons) {
		b->draw();
	}
	//--------------------------

	//------ TextFields draw -------------
	for (auto text : m_textf) {
		text->draw();
	}
	//-----------------------------------
}

void FilmBrowser::update()
{
	graphics::MouseState ms;
	graphics::getMouseState(ms);

	float mx = graphics::windowToCanvasX(ms.cur_pos_x);
	float my = graphics::windowToCanvasY(ms.cur_pos_y);

	
	//---------- Pointers to the highlighted buttons (Only 1 at time) ---------------
	IncDecButton* current_idbutton = nullptr;
	CheckBox* current_checkbox = nullptr;
	ClearFilterButton* current_cfb = nullptr;
	SoundBox* current_soundbox = nullptr;
	//-------------------------------------------------------------------------------


	for (auto b : m_buttons) {							// Access every button on the list
		if (b->contains(mx, my)) {						// If the mouse is inside the button radius
			b->setHighlight(true);						// then highlight that button
			current_idbutton = dynamic_cast<IncDecButton*> (b);	  // Check if the type of the button is IncDecButton
			if (!current_idbutton) {	// If the type of the button is not IncDecButton then				
				current_checkbox = dynamic_cast<CheckBox*>(b);	// Check if the type of the button is CheckBox
				if (!current_checkbox) {	// If the type of the button is not CheckBox then
					current_cfb = dynamic_cast<ClearFilterButton*>(b);// Check if the type of the button is ClearFilterButton
					if (!current_cfb) {    // If the type of the button is not ClearFilterButton then
						current_soundbox = dynamic_cast<SoundBox*>(b); // Check if the type of the button is SoundBox
					}
				}
			}
		}
		else {							// If the mouse is not inside the button radius
			b->setHighlight(false);		// then set the highlight false
		}
	}

	// ------------------------- Check if a IncDecButton is pressed --------------------------------------------

	if (ms.button_left_pressed && current_idbutton) { //If the left mouse button is pressed and current_idbutton!=nullptr
		graphics::playSound(ASSET_PATH + std::string("button.wav"), 0.2f);		// Play the sound of the button
		current_idbutton->update();												// Button update method
		
		// if the reference of the button equals the iterator (Buttons that change the movie displayed) and if the 
		// incVal equals 1 (right button)
		if (current_idbutton->getRef() == iterator && current_idbutton->getIncVal() == 1) {
			right_b = true;				// then the right button is pressed (right_b = true)
		}
		// if the reference of the button equals the iterator (Buttons that change the movie displayed) and if the 
		// incVal equals -1 (left button)
		if (current_idbutton->getRef() == iterator && current_idbutton->getIncVal() == -1) {
			right_b = false;	// then the left button is pressed (right_b = false)
		}
		// The right_b always keeps the last direction of the movie change so the app knows where to search
		// (left or right) for the next movie if there are filters applied
	}

	//-----------------------------------------------------------------------------------------------------------

	// -------- Limits for the movies array iterator and for the min and max year ------------------------

	if (minYear > maxYear) {
		minYear = maxYear;
	}

	if (iterator > 11) {						
		iterator = 0;
	}

	if (iterator < 0) {						
		iterator = 11;
	}

	if (minYear < 2007) {				
		minYear = 2007;
	}

	if (maxYear > 2022) {				
		maxYear = 2022;
	}

	if (maxYear < minYear) {
		maxYear = minYear;
	}
	

	//----------------------------------------------------------------------------------------------------

	//----------------- Check if a soundbox is pressed ---------------------------------------------------

	if (ms.button_left_pressed && current_soundbox) {// If the left mouse button is pressed and current_checkbox!=nullptr
		graphics::playSound(ASSET_PATH + std::string("button.wav"), 0.2f); // Play the sound of the button
		graphics::stopMusic();											  //Stop the music
		if (current_soundbox == m_activesoundbox) {	// If the active soundbox is pressed again
			m_activesoundbox->setActive(false);		// then set it as inactive
			m_activesoundbox = nullptr;				// And set the filmbrowser soundbox pointer to nullptr
			graphics::playMusic(ASSET_PATH + std::string("theme.wav"), 0.3f);
		}
		else {										// If the soundbox pressed is not equal to the active soundbox
			m_activesoundbox = current_soundbox;	// Keep this soundbox as the active soundbox
			m_activesoundbox->setActive(true);		// Set this soundbox as active
		}
	}

	//-----------------------------------------------------------------------------------------------------

	//----------------- Check if a checkbox is pressed ---------------------------------------------------

	if (ms.button_left_pressed && current_checkbox) {// If the left mouse button is pressed and current_checkbox!=nullptr
		graphics::playSound(ASSET_PATH + std::string("button.wav"), 0.2f); // Play the sound of the button
		if (current_checkbox==m_activecheckbox) {	// If the active checkbox is pressed again
			m_activecheckbox->setActive(false);		// then set it as inactive
			m_activecheckbox = nullptr;				// And set the filmbrowser checkbox pointer to nullptr
		}
		else {										// If the checkbox pressed is not equal to the active checkbox
			m_activecheckbox = current_checkbox;	// Keep this checkbox as the active checkbox
			m_activecheckbox->setActive(true);		// Set this checkbox as active
			CheckBox* temp_ptr = nullptr;			// Initiallize a temporary CheckBox pointer as nullptr
			for (auto chb : m_buttons) {			// Access all the buttons in the list
				temp_ptr = dynamic_cast<CheckBox*> (chb);	// Set the temp_ptr as the dynamic cast to CheckBox* of the chb
				if (temp_ptr != nullptr && temp_ptr != m_activecheckbox)
					temp_ptr->setActive(false);	// Set as inactive every other check box in the button list
			}
		}
	}

	//-----------------------------------------------------------------------------------------------------

	//-------------- Check if the ClearFilterButton is pressed --------------------------------------------

	if (current_cfb && ms.button_left_pressed) { // If the left mouse button is pressed and current_cfb!=nullptr
		if (m_activecheckbox)					// If there is an active checkbox (m_activecheckbox != nullptr)
			m_activecheckbox->setActive(false);	// Set it as inactive
		m_activecheckbox = nullptr;				// Set m_activecheckbox to nullptr
		minYear = 2007;							// Reset the minYear to default value
		maxYear = 2022;							// Reset the maxYear to default value
		for (auto text : m_textf) {
			text->clear();
		}
	}

	//-----------------------------------------------------------------------------------------------------


	// -------------------- Check if the current movie displayed matches the filters ----------------------

	// If the movie displayed doesnt match the filters then the iterator value updates to the next movie that match
	// the filter. If there isnt such a movie then the blank movie is displayed

	// There are 2 cases we need to check
	
//---------- First initiallize variables to check if we need to change the current movie displayed ------------------------	
	
	std::string titlet = movies[iterator]->getTitle().substr(0, m_textf[0]->getSize());
//titlet: gets the title of the current movie and keeps the same number of characters as the characters writtens in the textfield
	std::transform(titlet.begin(), titlet.end(), titlet.begin(), ::tolower);
	//titlet string turns to lower case
	std::string directort = movies[iterator]->getDirector().substr(0, m_textf[1]->getSize());
//directort:gets the director and keeps the same number of characters as the characters writtens in the textfield
	std::transform(directort.begin(), directort.end(), directort.begin(), ::tolower);
	//directort string turns to lower case
	std::string actor1t = movies[iterator]->getActor(0).substr(0, m_textf[2]->getSize());
	//actor1t:gets the actor1 and keeps the same number of characters as the characters writtens in the textfield
	std::transform(actor1t.begin(), actor1t.end(), actor1t.begin(), ::tolower);
	//actor1t string turns to lower case
	std::string actor2t = movies[iterator]->getActor(1).substr(0, m_textf[2]->getSize());
	//actor2t:gets the actor1 and keeps the same number of characters as the characters writtens in the textfield
	std::transform(actor2t.begin(), actor2t.end(), actor2t.begin(), ::tolower);
	//actor2t string turns to lower case
	std::string actor3t = movies[iterator]->getActor(2).substr(0, m_textf[2]->getSize());
	//actor3t:gets the actor1 and keeps the same number of characters as the characters writtens in the textfield
	std::transform(actor3t.begin(), actor3t.end(), actor3t.begin(), ::tolower);
	//actor3t string turns to lower case
	
//----------------------------------------------------------------------------------------------------------------------------
	
	// 1. There is no active checkbox (No genre filter) but only year filters or textfield filters
	if (!m_activecheckbox && (movies[iterator]->getYear() < minYear || movies[iterator]->getYear() > maxYear || m_textf[0]->getStr()!= titlet || m_textf[1]->getStr()!= directort || (m_textf[2]->getStr() != actor1t && m_textf[2]->getStr() != actor2t && m_textf[2]->getStr() != actor3t))) {
		iterator = getNextMovieNGenre(iterator, movies, minYear, maxYear, right_b,m_textf);
	}

	// 2. There is an active checkbox (Genre filter) and there are year filters or textfield filters
	// This case works even if there are no year filters but only an active checkbox
	if (m_activecheckbox) {
		if ((movies[iterator]->getGenre(0) != m_activecheckbox->getGen() && movies[iterator]->getGenre(1) != m_activecheckbox->getGen()) || (movies[iterator]->getYear() < minYear || movies[iterator]->getYear() > maxYear || m_textf[0]->getStr() != titlet || m_textf[1]->getStr() != directort || (m_textf[2]->getStr() != actor1t && m_textf[2]->getStr() != actor2t && m_textf[2]->getStr() != actor3t))) {
			iterator = getNextMovieWGenre(iterator, m_activecheckbox->getGen(), movies, minYear, maxYear, right_b,m_textf);
		}
	}

	// ----------------------------------------------------------------------------------------------------


	// ---------------------------------- Update the text field ----------------------------------------------------
	for (auto text : m_textf) {
		if (text->contains(mx, my)) {
			text->setHighlight(true);
			text->update();
		}
		else {
			text->setHighlight(false);
		}
	}

	//-------------------------------------------------------------------------------------------------------------
}

void FilmBrowser::init()
{
	// Initialization of the movies and buttons
	// Sets the background music of the app
	// Sets the font of the app
	graphics::playMusic(ASSET_PATH + std::string("theme.wav"), 0.3f);
	Movie* temp_movie;
	IncDecButton* temp_button;
	CheckBox* temp_checkbox;
	ClearFilterButton* tem;
	SoundBox* temp_soundbox;
	graphics::setFont(ASSET_PATH + std::string("Babylon.ttf"));
	
	temp_movie = new Movie("Scream 5", "Thriller", "Mystery", "Cortney Cox", "Neve Campbell", "David Arquette", "Matt Bettinelli - Olpin", 2022, "scream.png",3.5,"25 years after a streak of brutal murders shocked the quiet town of Woodsboro,","Calif., a new killer dons the Ghostface mask and begins targeting a group of","teenagers to resurrect secrets from the town's deadly past.","");
	movies[0] = temp_movie;
	temp_movie = new Movie("X", "Thriller", "Mystery", "Mia Goth", "Jenna Ortega", "Britanny Snow", "Ti West", 2022, "X.png",3.5,"In 1979, a group of young filmmakers set out to make an adult film in rural","Texas, but when their reclusive, elderly hosts catch them in the act, the cast find ","themselves fighting for their lives.","");
	movies[7] = temp_movie;
	temp_movie = new Movie("Salt", "Action", "Thriller", "Angelina Jolie", "Liev Schreiber", "Chiwetel Ejiofor", "Phillip Noyce",2010, "salt.png",3,"A CIA agent goes on the run after a defector accuses her of being a Russian spy.", "", "", "");
	movies[1] = temp_movie;
	temp_movie = new Movie("Shooter", "Action", "Drama", "Mark Wahlberg", "Michael Pena", "Rhona Mitra", "Antoine Fuqua", 2007, "shooter.png",3.5,"A marksman living in exile is coaxed back into action after hearing of a plot","to kill the President. After being-double crossed for the attempt and on the run,","he sets out for the real killer and the truth.","");
	movies[9] = temp_movie;
	temp_movie = new Movie("Avatar", "Adventure", "Action", "Sam Worthington", "Zoe Saldana", "Sigourney Weaver", "James Cameron",2009, "avatar.png",4,"Jake Sully lives with his newfound family formed on the extrasolar moon","Pandora. Once a familiar threat returns to finish what was previously started,","Jake must work with Neytiri and the army of the Na'vi race to protect their home.","");
	movies[2] = temp_movie;
	temp_movie = new Movie("Wonder Woman 1984", "Adventure", "Action", "Gal Gadot", "Chris Pine", "Kristen Wiig", "Patty Jenkins", 2020, "wonder.png",2.5,"Diana must contend with a work colleague, and with a businessman whose desire","for extreme wealth sends the world down a path of destruction, after an ancient","artifact that grants wishes goes missing.","");
	movies[10] = temp_movie;
	temp_movie = new Movie("Jumanji Welcome To The Jungle", "Comedy", "Adventure", "Dwayne Johnson", "Karen Gillan", "Kevin Hart", "Jake Kasdan", 2017, "jumanji.png",3.5,"Four teenagers are sucked into a magical video game, and the only way they can","escape is to work together to finish the game.","","");
	movies[3] = temp_movie;
	temp_movie = new Movie("Jumanji The Next Level", "Comedy", "Adventure", "Dwayne Johnson", "Jack Black", "Kevin Hart", "Jake Kasdan", 2019, "jumanji2.png",3.5,"In Jumanji: The Next Level, the gang is back but the game has changed.","As they return to rescue one of their own, the players will have to brave","parts unknown from arid deserts to snowy mountains, to escape the world's","most dangerous game.");
	movies[8] = temp_movie;
	temp_movie = new Movie("Hugo", "Drama", "Adventure", "Asa Butterfield", "Chloe Grace Moretz", "Christopher Lee", "Martin Scorsese",2011, "hugo.png",4,"In 1931 Paris, an orphan living in the walls of a train station gets wrapped up","in a mystery involving his late father and an automaton.","","");
	movies[4] = temp_movie;
	temp_movie = new Movie("Fifty Shades Of Grey", "Drama", "Thriller", "Dakota Johnson", "Jamie Dornan", "Jeniffer Ehle", "Sam Taylor-Johnson",2015, "fifty.png",2,"Literature student Anastasia Steele's life changes forever when she meets","handsome, yet tormented, billionaire Christian Grey.","","");
	movies[11] = temp_movie;
	temp_movie = new Movie("Inferno", "Mystery", "Adventure", "Tom Hanks", "Felicity Jones", "Irrfan Khan", "Ron Howard", 2016, "inferno.png",3,"When Robert Langdon wakes up in an Italian hospital with amnesia, he teams up","with Dr. Sienna Brooks and they race across Europe together against the clock","to foil a deadly global plot.","");
	movies[5] = temp_movie;
	temp_movie = new Movie("Enola Holmes", "Mystery", "Drama", "Millie Bobby Brown", "Henry Cavill", "Sam Claflin", "Harry Bradbeer", 2020, "holmes.png",3.5,"When Enola Holmes-Sherlock's teen sister-discovers her mother is missing,","she sets off to find her, becoming a super-sleuth in her own right as she","outwits her famous brother and unravels a dangerous conspiracy around a ","mysterious young Lord.");
	movies[6] = temp_movie;
	temp_movie = new Movie("No Such Movie Found", "-", "-", "-", "-", "-", "-", 0, " ",0,"","","","");
	movies[12] = temp_movie;
	
	temp_button = new IncDecButton(920, 125, minYear, 1, "right_arrow.png");
	m_buttons.push_back(temp_button);
	temp_button = new IncDecButton(830, 125, minYear, -1, "left_arrow.png");
	m_buttons.push_back(temp_button);
	temp_button = new IncDecButton(920, 175, maxYear, 1, "right_arrow.png");
	m_buttons.push_back(temp_button);
	temp_button = new IncDecButton(830, 175, maxYear, -1, "left_arrow.png");
	m_buttons.push_back(temp_button);
	temp_button = new IncDecButton(25, 210, iterator, -1, "left_arrow.png");
	m_buttons.push_back(temp_button);
	temp_button = new IncDecButton(265, 210, iterator, 1, "right_arrow.png");
	m_buttons.push_back(temp_button);
	
	temp_checkbox = new CheckBox(760, 235, "checkbox_notick.png", "Drama");
	m_buttons.push_back(temp_checkbox);
	temp_checkbox = new CheckBox(820, 235, "checkbox_notick.png", "Thriller");
	m_buttons.push_back(temp_checkbox);
	temp_checkbox = new CheckBox(880, 235, "checkbox_notick.png", "Action");
	m_buttons.push_back(temp_checkbox);
	temp_checkbox = new CheckBox(760, 285, "checkbox_notick.png", "Adventure");
	m_buttons.push_back(temp_checkbox);
	temp_checkbox = new CheckBox(820, 285, "checkbox_notick.png", "Comedy");
	m_buttons.push_back(temp_checkbox);
	temp_checkbox = new CheckBox(880, 285, "checkbox_notick.png", "Mystery");
	m_buttons.push_back(temp_checkbox);
	
	tem = new ClearFilterButton(875, 85);
	m_buttons.push_back(tem);
	
	temp_soundbox = new SoundBox(40, 479, "soundon.png");
	m_buttons.push_back(temp_soundbox);
	
	TextField* textf = new TextField(840, 330, "Title");
	m_textf[0] = textf;
	textf = new TextField(840, 360 , "Director");
	m_textf[1] = textf;
	textf = new TextField(840, 390, "Actor");
	m_textf[2] = textf;
}

FilmBrowser::~FilmBrowser()	// Destructor of the FilmBrowser
{
	for (auto b : m_buttons) {	// Delete every button in the list
		if (b)
			delete b;
		b = nullptr;
	}

	for (auto m : movies) {		// Delete every movie in the array
		if (m)
			delete m;
		m = nullptr;
	}

	for (auto t : m_textf) {	// Delete every textfield in the array
		if (t)
			delete t;
		t = nullptr;
	}
}

//------------------------ Function used for filtered search ( If there is no genre filter)--------------------------
// i: reference to the iterator, m: pointer to the array, mYear: reference to the minYear
// MYear: reference to the maxYear, b: reference to the right_b , t: Pointer to the textfield* array
inline int getNextMovieNGenre(const short& i,Movie** m, const short& mYear, const short& MYear, const bool& b, TextField** t) {
	short temp = i;			// Set a temp variable equal to the iterator
	int inc = b ? 1 : -1;	// if b==true (right_b == true) then inc = 1 else -1
	while (true) {			// while loop
		temp += inc;		// Increase or Decrease the temp (Search right or left)
		if (temp > 11)		// Limit of the temp (Iterator)
			temp = 0;
		if (temp < 0)		// Limit of the temp (Iterator)
			temp = 11;
		if ((*(m + temp))->getYear() >= mYear && (*(m + temp))->getYear() <= MYear) {	
			// If the year of the movies[temp] is between the mYear and the MYear then return temp	
			
			std::string titlet = (*(m + temp))->getTitle().substr(0, (*t)->getSize());
			//titlet: gets the title of the current movie and keeps the same number of characters as the characters writtens in the textfield
			std::transform(titlet.begin(), titlet.end(), titlet.begin(), ::tolower);
			//titlet string turns to lower case
			std::string directort = (*(m + temp))->getDirector().substr(0, (*(t + 1))->getSize());
			//directort:gets the director and keeps the same number of characters as the characters writtens in the textfield
			std::transform(directort.begin(), directort.end(), directort.begin(), ::tolower);
			//directort string turns to lower case
			std::string actor1t = (*(m + temp))->getActor(0).substr(0, (*(t + 2))->getSize());
			//actor1t:gets the actor1 and keeps the same number of characters as the characters writtens in the textfield
			std::transform(actor1t.begin(), actor1t.end(), actor1t.begin(), ::tolower);
			//actor1t string turns to lower case
			std::string actor2t = (*(m + temp))->getActor(1).substr(0, (*(t + 2))->getSize());
			//actor2t:gets the actor1 and keeps the same number of characters as the characters writtens in the textfield
			std::transform(actor2t.begin(), actor2t.end(), actor2t.begin(), ::tolower);
			//actor2t string turns to lower case
			std::string actor3t = (*(m + temp))->getActor(2).substr(0, (*(t + 2))->getSize());
			//actor3t:gets the actor1 and keeps the same number of characters as the characters writtens in the textfield
			std::transform(actor3t.begin(), actor3t.end(), actor3t.begin(), ::tolower);
			//actor3t string turns to lower case
			
			if (((*t)->getSize() == 0 || (*t)->getStr() == titlet) && (((*(t + 1))->getSize() == 0 || (*(t + 1))->getStr() == directort ) && ((*(t + 2))->getSize() == 0 || ((*(t + 2))->getStr() == actor1t || (*(t + 2))->getStr() == actor2t || (*(t + 2))->getStr() == actor3t)))) {
				//Checks if there is a text field filter for title, director and actors. If there is it checks if it matches
				// the string of the title, director and actor from above
				return temp;	// If true return the index of this movie
			}
		}
		if (temp == i) {
			break;	// If temp == i (Searched the whole array and came back to the starting point) get out of the loop
		}
	}
	return 12;	// return 12 ( Index of the blank movie)
	// Function returns 12 only if there is no movie matching the filters
}

//------------------------------------------------------------------------------------------------------------------

//------------------------------- Function used for filtered search (If there is a genre filter) -------------------
// i: reference to the iterator,gen: reference to the genre of the active checkbox, m: pointer to the array
// mYear: reference to the minYear, MYear: reference to the maxYear, b: reference to the right_b, t: Pointer to the textfield* array
inline int getNextMovieWGenre(const short& i,const std::string& gen,Movie** m , const short& mYear, const short& MYear , const bool& b, TextField** t)
{
	int temp = i;			// Set a temp variable equal to the iterator
	int inc = b ? 1 : -1;	// if b==true (right_b == true) then inc = 1 else -1
	while (true) {			// while loop
		temp+=inc;			// Increase or Decrease the temp (Search right or left)
		if (temp > 11)		// Limit of the temp (Iterator)
			temp = 0;
		if (temp < 0)		// Limit of the temp (Iterator)
			temp = 11;
		// If the year of the movies[temp] is between the mYear and the MYear and the genre of the movies[temp]
		// equals to the genre of the active checkbox
		if ((*(m + temp))->getYear() >= mYear && (*(m + temp))->getYear() <= MYear && ((*(m + temp))->getGenre(0) == gen || (*(m + temp))->getGenre(1) == gen)) {
			
			std::string titlet = (*(m + temp))->getTitle().substr(0, (*t)->getSize());
			//titlet: gets the title of the current movie and keeps the same number of characters as the characters writtens in the textfield
			std::transform(titlet.begin(), titlet.end(), titlet.begin(), ::tolower);
			//titlet string turns to lower case
			std::string directort = (*(m + temp))->getDirector().substr(0, (*(t+1))->getSize());
			//directort:gets the director and keeps the same number of characters as the characters writtens in the textfield
			std::transform(directort.begin(), directort.end(), directort.begin(), ::tolower);
			//directort string turns to lower case
			std::string actor1t = (*(m + temp))->getActor(0).substr(0, (*(t+2))->getSize());
			//actor1t:gets the actor1 and keeps the same number of characters as the characters writtens in the textfield
			std::transform(actor1t.begin(), actor1t.end(), actor1t.begin(), ::tolower);
			//actor1t string turns to lower case
			std::string actor2t = (*(m + temp))->getActor(1).substr(0, (*(t + 2))->getSize());
			//actor2t:gets the actor1 and keeps the same number of characters as the characters writtens in the textfield
			std::transform(actor2t.begin(), actor2t.end(), actor2t.begin(), ::tolower);
			//actor2t string turns to lower case
			std::string actor3t = (*(m + temp))->getActor(2).substr(0, (*(t + 2))->getSize());
			//actor3t:gets the actor1 and keeps the same number of characters as the characters writtens in the textfield
			std::transform(actor3t.begin(), actor3t.end(), actor3t.begin(), ::tolower);
			//actor3t string turns to lower case

			if (((*t)->getSize() == 0 || (*t)->getStr() == titlet ) && (((*(t+1))->getSize() == 0 || ((*(t + 1))->getStr() == directort) && ((*(t + 2))->getSize() == 0 || ((*(t + 2))->getStr() == actor1t || (*(t + 2))->getStr() == actor2t || (*(t + 2))->getStr() == actor3t)))))
				//Checks if there is a text field filter for both tilte and director. If there is it checks if it matches
				// the string of the title and director from above
				return temp;	// If true then return the index of the movie
		}
		if (temp == i) {
			break;	// If temp == i (Searched the whole array and came back to the starting point) get out of the loop
		}
	}
	return 12;	// return 12 ( Index of the blank movie)
	// Function returns 12 only if there is no movie matching the filters
}
//-------------------------------------------------------------------------------------------------------------------
