#include "lve_pipeline.hpp"


std::vector<char> lve::LvePipeline::readFile(const char* filePath) {
	// ate   : seek to the end immediately, getting size is a bit easier
	// binary: we read it as a binary, since we don't want to convert any text
	std::ifstream ifstr(filePath, std::ios::ate | std::ios::binary);

	if(!ifstr.is_open()) {
		throw std::runtime_error("failed to open file: " + std::string(filePath));
	}

	// because we're already at the end of the file, when we use tellg, we get the 
	// last position of the file size
	size_t fileSize = static_cast<size_t>(ifstr.tellg());
	std::vector<char> buffer(fileSize);

	// now go to the start, since we want to start reading it
	ifstr.seekg(0);
	ifstr.read(buffer.data(), fileSize);
	
	ifstr.close();
	return buffer; 
}

void lve::LvePipeline::createGraphicsPipeline(const char* vertFilePath, const char* fragFilePath) const {
	// Just making sure it works
	std::vector<char> vertCode = this->readFile(vertFilePath);
	std::vector<char> fragCode = this->readFile(fragFilePath);

	std::cout << "Vertex Shader Code Size: " << vertCode.size() << "\n";
	std::cout << "Fragmennt Shader Code Size: " << fragCode.size() << "\n";
}


lve::LvePipeline::LvePipeline(const char* vertFilePath, const char* fragFilePath) {
	this->createGraphicsPipeline(vertFilePath, fragFilePath);
}