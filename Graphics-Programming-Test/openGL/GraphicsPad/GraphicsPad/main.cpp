#include "MeGlWindow.hpp"
#include <iostream>
#include <stdexcept>

int main(int argc, char** argv) {
		
	MeGlWindow meWindow{};
	try {
		meWindow.show();
	}
	catch (const std::exception& e) {
		std::cerr << e.what() << "\n";
		return EXIT_FAILURE;
	}
	return EXIT_SUCCESS;
}