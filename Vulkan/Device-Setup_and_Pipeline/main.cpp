#include "app.hpp"
#include <cstdlib>
#include <iostream>
#include <stdexcept>
//lve = little vulkan engine window 

int main() {
	lve::App app{};
	try {
		app.run();		
	} catch (const std::exception& e) {
		std::cerr << e.what() << "\n";
		return EXIT_FAILURE;
	}

	return EXIT_SUCCESS;
}
