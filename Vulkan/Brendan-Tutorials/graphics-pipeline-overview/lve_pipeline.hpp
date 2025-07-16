#ifndef LVE_PIPELINE_HPP
#define LVE_PIPELINE_HPP

#include <vector>
#include <fstream>
#include <stdexcept>
#include <iostream>
#include <string>

namespace lve {
	class LvePipeline {
	  public:

	  	LvePipeline(const char* vertFilePath, const char* fragFilePath);

	  private:

	  	static std::vector<char> readFile(const char* filePath);
	  	void createGraphicsPipeline(const char* vertFilePath, const char* fragFilePath) const;
	};
};

#endif