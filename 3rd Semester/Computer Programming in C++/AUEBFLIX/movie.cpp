#include "movie.h"
#include "graphics.h"
#include "config.h"

void Movie::draw() const
{
	graphics::Brush br;
	br.texture = std::string(ASSET_PATH) + getImage();			// Set the movie image as the texture
	br.outline_opacity = 0.0f;
	if (getImage() == " ") {									// If the string of the image is the blank string
		br.fill_opacity = 0.0f;									// we set the fill opacity 0 (Blank movie)
	}
	graphics::drawRect(145, 210, 180, 240,br);					// Draw the image of the movie
	br.fill_opacity = 1.0f;
	std::string info;
	info = "Title : " + getTitle();
	graphics::drawText(300, 130, 19, info, br);					// Draw the title
	info = "Genre : " + getGenre(0) + ", " + getGenre(1);
	graphics::drawText(300, 150, 19, info, br);					// Draw the genres
	info = "Year : " + std::to_string(getYear());
	graphics::drawText(300, 170, 19, info, br);					// Draw the year
	info = "Director : " + getDirector();
	graphics::drawText(300, 190, 19, info, br);					// Draw the Director
	info = "Actors : " + getActor(0) + ", " + getActor(1) + ", " + getActor(2);
	graphics::drawText(300, 210, 19, info, br);					// Draw the actors
	info = std::string("Plot :");
	graphics::drawText(300, 250, 19, info, br);					// Draw the word Plot
	info = plot[0];
	graphics::drawText(300, 270, 17, info, br);					// Draw the first line of the plot
	info = plot[1];
	graphics::drawText(300, 290, 17, info, br);					// Draw the second line of the plot
	info = plot[2];
	graphics::drawText(300, 310, 17, info, br);					// Draw the third line of the plot
	info = plot[3];
	graphics::drawText(300, 330, 17, info, br);					// Draw the fourth line of the plot
	br.texture = " ";											// Draw the rating of the movie
	br.fill_color[0] = 1.0f;
	br.fill_color[1] = 0.9f;									// Set the color of the rectangle as yellow
	br.fill_color[2] = 0.2f;
	graphics::drawRect(145-((5-stars)*10), 370,20*stars, 25, br);	// A yellow rectangle is drawn in the background
	// The center x of the rectangle and the width are determined by the stars of the movie (Rating of the movie)
	br.texture = ASSET_PATH + std::string("stars.png"); // The texture of the rectangle is the stars.png 
	// (Black rectangle with 5 transparent stars)
	graphics::drawRect(145, 370, 100, 25, br);			// Draw the rectangle with the image above
}

Movie::Movie(std::string title, std::string genre1, std::string genre2, std::string actor1, std::string actor2, std::string actor3, std::string director, short year, std::string image,float stars,std::string d1, std::string d2, std::string d3, std::string d4)
	:title(title), director(director), year(year), image(image),stars(stars)
{
	genre[0] = genre1;								// Constructor of the movie
	genre[1] = genre2;								// title: title of the movie, year: year of the movie
	actors[0] = actor1;								// genre1,genre2 : 2 genres of the movie		   
	actors[1] = actor2;								// actor 1-3 :	3 actors of the movie
	actors[2] = actor3;								// director: director of the movie,image: image of the movie
	plot[0] = d1;									// d1,d2,d3,d4: The plot of the movie
	plot[1] = d2;
	plot[2] = d3;
	plot[3] = d4;
}


