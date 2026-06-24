#include "imageLoader.hpp"

void Playground::ImageLoader::FreeImage() {
  if (this->imgData.data) {
    stbi_image_free(this->imgData.data);
    // delete this->imgData.data;
  }
}


Playground::ImageData& Playground::ImageLoader::loadTexture(const char* IMAGE) {
  stbi_set_flip_vertically_on_load(true);
  this->imgData.data = stbi_load(IMAGE,
    &this->imgData.width,
    &this->imgData.height,
    &this->imgData.nrChannels,
    0
  );

  return this->imgData;
}

Playground::ImageLoader::~ImageLoader() {
  this->FreeImage();
}
