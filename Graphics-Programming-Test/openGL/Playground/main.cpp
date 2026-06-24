#include "engine.hpp"

int main(void)
{

  float position[] = {
    +0.5f, +0.5f,          // position
    +1.0f, +0.0f, +0.0f,   // color
    +2.0f, +2.0f,          // texture position  : top right

    +0.5f, -0.5f,          // position
    +0.0f, +1.0f, +0.0f,   // color
    +2.0f, +0.0f,          // texture position  : bottom right

    -0.5f, +0.5f,          // position
    +1.0f, +1.0f, +0.0f,   // colors
    +0.0f, +2.0f,          // texture position  : top left

    -0.5f, -0.5f,          // position
    +0.0f, +0.0f, +1.0f,   // colors
    +0.0f, +2.0f,          // texture position  : bottom left
  };

  unsigned short indices[] = { 0,1,3, 0,2,3 };
  const int numberOfVertices{ 4 };
  const unsigned int elementsPerVertex{ (sizeof(position)) / sizeof(position[0]) / numberOfVertices };
  const unsigned int numberOfIndices{ (sizeof(indices)) / sizeof(indices[0]) };


  Playground::Engine engine{ 800, 600, "title" };
  engine.draw(
    position,
    sizeof(position),
    indices,
    sizeof(indices),
    numberOfIndices,
    {
      "res/assets/container.jpg",
      "res/assets/awesomeface.png"
    },
    elementsPerVertex
  );

  return 0;
}
