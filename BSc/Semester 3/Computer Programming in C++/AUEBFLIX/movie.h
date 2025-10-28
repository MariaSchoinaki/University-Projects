#pragma once
#include <string>

// Class Movie : Describes the movies of the app

class Movie {
	std::string title;						// Title of the movie
	std::string genre[2];					// Array of the genres
	std::string actors[3];					// Array of the actors
	std::string director;					// The Director of the movie
	short year;								// The release year of the movie
	std::string image;						// The image of the movie
	float stars;							// The rating of the movie
	std::string plot[4];					// The plot of the movie

public:
	std::string getTitle() const{ return title; }						// Title getter
	std::string getGenre(int i) const { return genre[i]; }				// Getter of the i-th genre
	std::string getActor(int i) const { return actors[i]; }				// Getter of the i-th actor
	std::string getDirector() const { return director; }				// Getter of the Director
	short getYear() const { return year; }								// Getter of the Year
	std::string getImage() const { return image; }						// Getter of the image
	void draw() const;													// The draw method of the movie
	// The movie constructor
	Movie(std::string title, std::string genre1, std::string genre2, std::string actor1, std::string actor2, std::string actor3, std::string director, short year, std::string image, float stars, std::string d1, std::string d2, std::string d3, std::string d4);
};