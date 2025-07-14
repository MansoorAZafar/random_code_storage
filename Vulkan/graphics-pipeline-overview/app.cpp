#include "app.hpp"

void lve::App::run() {
	while(!this->lveWindow.shouldClose()) {
		// while our window doesn't want to close, pull window events
		// window events = keystrokes, user click, ... etc
		lveWindow.render();
		glfwPollEvents();
	}
}
