#include "lve_pipeline.hpp"


std::vector<char> lve::LvePipeline::readFile(const char* filePath) {
	// ate   : seek to the end immediately, getting size is a bit easier
	// binary: we read it as a binary, since we don't want to convert any text
	std::ifstream ifstre(filePath, std::ios::ate, std::ios::binary);
}