#ifndef PLAYGROUND_IMAGE_LOADER_HPP
#define PLAYGROUND_IMAGE_LOADER_HPP

#include "stb_image.h"

namespace Playground {
  struct ImageData {
    unsigned char* data;
    int width;
    int height;
    int nrChannels;
  };

  class ImageLoader {
  private:
    ImageData imgData{};
    void FreeImage();
  public:
    ImageData& loadTexture(const char* IMAGE);
    ~ImageLoader();
  };
};

#endif
