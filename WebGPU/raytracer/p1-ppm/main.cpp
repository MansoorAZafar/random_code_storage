/**
 * This is v1 at a raytracer, It will cover PPM
 * PPM is a basic img format for storing colors for an image (wow)
 * generally follows:
 * 
 * TYPE (P3 or P6 (binary))
 * WIDTH HEIGHT
 * MAXCOLOR
 * R G B
 * R G B
 * . . .
 * 
 * Can also do R G B  R G B  R G B ...
 *  - the newlines and whitespace are treated the same
 *  - max color is just the maximum color value it can be
 *      - we generally use 255 since thats max value of 1 byte (8 bits)
*/

#include <iostream>
#include <random>
#define PPM_TYPE "P3"

int main() {
    std::random_device rd;
    std::mt19937 gen(rd()); 
    std::uniform_int_distribution<> random(0, 255); 


    const int WIDTH = 256, HEIGHT = 256, maxColor {255};
    std::cout << PPM_TYPE << "\n" << WIDTH << " " << HEIGHT << "\n" << maxColor << "\n";

    for(int y = 0; y < HEIGHT; ++y) {
        for(int x = 0; x < WIDTH; ++x) {
            // OUTPUT a random color to stdout and pipe it into a ppm file
            const int r {random(gen)}, g {random(gen)}, b {random(gen)};
            std::cout << r << " " << g << " " << b << "\n";
        }
    }

    return 0;
}