#include <omp.h>
#include <iostream>

int main(int argc, char** argv) {
	// TESTING NON-CANONICAL LOOP
	const int n { std::stoi(argv[1]) };
	
	#pragma omp parallel for
	for(int i = 0; i < n; i += 2) {
		std::cout << omp_get_thread_num() << "\n";
		//++i;
	}
}

/**
omp_set_num_threads(2);
	const int n = std::stoi(argv[1]);
	
	char arr[8] = {};
	
	#pragma omp parallel for
	for(int i = 0; i < n; ++i) {
		arr[i] = omp_get_thread_num() + 'a';
	}
	
	for(int i = 0; i < n; ++i) std::cout << arr[i] << ", ";
	
	return 1;
*/