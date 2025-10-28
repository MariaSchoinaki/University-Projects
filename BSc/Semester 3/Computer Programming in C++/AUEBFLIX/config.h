#pragma once

#define ASSET_PATH "assets\\"

#define WINDOW_WIDTH 1200
#define WINDOW_HEIGHT 600

#define CANVAS_WIDTH 1000
#define CANVAS_HEIGHT 500

#define BUTTON_SIZE 15
#define CHECKBOX_SIZE 30
#define SOUNDBOX_SIZE 20

#define BUTTON_WIDTH 100
#define BUTTON_HEIGHT 20


inline float distance(const float& x1,const float& y1,const float& x2,const float& y2) {
	float dx = x1 - x2;
	float dy = y1 - y2;
	return sqrtf(dx * dx + dy * dy);
}