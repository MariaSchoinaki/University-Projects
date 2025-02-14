#include "graphics.h"
#include "filmbrowser.h"
#include "config.h"

void update(float ms) {                         // Update function of the main app
    FilmBrowser* MovieB = reinterpret_cast<FilmBrowser*> (graphics::getUserData());
    MovieB->update();
}

void draw() {                                   // Draw function of the main app
    FilmBrowser* MovieB = reinterpret_cast<FilmBrowser*> (graphics::getUserData());
    MovieB->draw();
}

int main() {                                    // Main
    
    FilmBrowser MovieBrowser;
    
    graphics::createWindow(WINDOW_WIDTH, WINDOW_HEIGHT, "AUEBFLIX");

    graphics::setUserData(&MovieBrowser);

    graphics::setDrawFunction(draw);
    graphics::setUpdateFunction(update);

    graphics::setCanvasSize(CANVAS_WIDTH, CANVAS_HEIGHT);
    graphics::setCanvasScaleMode(graphics::CANVAS_SCALE_FIT);

    MovieBrowser.init();                        // The init method of the film browser
    graphics::startMessageLoop();
    
    return 0;
}