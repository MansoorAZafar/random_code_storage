/**
 * This covers p2, the basic vec3 class, rays, simple camera and background
 * 
 * Job of the raytracer:
 *  1. Find where the pixel exists in 3D space
 *  2. Shoot a ray from the camera (eye) through that pixel
 *      - Pixels are like a window pane not a wall or destination target
 *      - imagines a screen as a transparent window infront of a camera
 *          - i.e: looking at a tree through a window
 *              - You don't look at the glass, don't need to look at glass and stop
 *              - Start at eye -> pass through the glass and continue until it hits the tree
 *                  - Computer only knows where pixel is and eye
 *                  - it shoots ray through pixel 
 *                      - it may hit nothing and or it may hit a wall, or N
 *                      - we need to see along whats happening any 
 *                  - We NEED a window pane since the computer has to paint a flat 2D
 *                      picture for your monitor
 *                  - Computer needs a canvas to draw on, the window pane is that
 *                      - the pane also tells how much to paint
 *                  - we don't need a pane since our eyes natrually can absorb the light
 *                      - but a computer isn't our eyes
 * 
 *  3. Determine what color can be seen along that ray
 *  4. Write that color into an image
*/
#include <iostream>
#include "vec3.hpp"
#include "ray.h"
#define PPM_TYPE "P3"
#define DESIRED_ASPECT_RATIO 16.0 / 9.0

namespace p2 {
    vec3 ray_color(const ray& ray) {
        const double a { (ray.direction.y + 1.0) * 0.5 };
        const vec3 resColor { vec3(1.0, 1.0, 1.0) * (1.0-a) + vec3(0.5, 0.7, 1.0) * a };

        return { resColor.x, resColor.y, resColor.z, vec3Type::COLOR };
    }
};

using namespace p2;
int main() {

    // width/height = 16.9 / 9.0
    //      -> width/height * height = aspect_ratio * height
    //      -> width*height / height = aspect_ratio * height
    //      -> width = aspect_ratio * height
    //      -> width / aspect_ratio = aspect_ratio * height / aspect_ratio
    //      -> width / aspect_ratio = height
    //
    // height = width / aspect_ratio
    constexpr double aspect_ratio { DESIRED_ASPECT_RATIO };
    
    // Have to ensure height is at least 1
    constexpr int width { 800 }, potential_height { static_cast<int>(width / aspect_ratio) },
        height { potential_height ? potential_height : 1 };
    

    // Camera
    //  - the point which all rays originate (eye point)
    //  - distance between viewport center and camera center is the focal length
    
    // We want camera at center looking forward
    //  - we'll use a right-handed coordinate system
    //      - -Z goes into the scene (zoom in)
    //      - +Z goes out to the viewer
    //          - Going "Forward" means going closer to the scene, away from me
    //             and closer to the monitor/scene
    //          +Z goes towards you, so therefore, towards the screen must be -Z
    //          forward is (0, 0, -1)
    vec3 camera { 0, 0, 0};

    // Viewport
    // - a window infront of the camera
    // - represents your screen in the world
    //  - converting pixel (i,j) from the img into a 3D space point,
    //      - helps define virtual 2D points in a 3D space
    //      - we trace the rays back, send out rays and trace them back
    //          - each pixel is sent out, and returns a color of what it should be
    //          - each pixel:
    //              - if my eye is here, and I look through this pixel, what do I see
    //              - instead of light coming from outside and into a lense
    //                  - we shoot the light from the lense into the world
    //
    //                  - We have 
    //                      - a 2D image (pixels on your screen)
    //                      - a 3D world (scene with lights, spheres, boxes ...)
    //                  - We need
    //                      - a rule that answers: 
    //                          - for each pixel, what does it see in 3D
    //                          for each pixel (i,j), which direction do they go
    //                  - The viewport defines that rule
    //
    //  - find a pixel in this viewport, and shoot a ray through it
    //     - remember, need to map 3D to a 2D monitor
    //      - imagine shooting a single beam through a window, but all you know are 
    //          the windows coordinates, you shoot through this window and into the 
    //          space that has the stuff
    

    /**
    * Viewport
    *   - We have 
    *       - a 2D image (pixels)
    *       - a 3D world (scene)
    *   - We want
    *       - for each pixel, what does it "see" in 3D
    *   - Each pixel NEEDS a line going out into the 3D world
    *       - this "line" is called a RAY
    *   - The viewport, tells us where each pixel is "aiming" at in 3D space
    *       - Give each pixel a specific 3D point to look through
    *       -> Draw a line from the camera to that point
    *       -> for each pixel
    *           - direction = pixel_point_on_viewpoint - camera_position
    *                   - we want a ray from camera to a pixel position in 3D space
    *                   - direction = destination - start
    *                       - how can I get from start to destination
    *                       - EX:
    *                           - 1, 2, 3, 4, 5, 6, 7
    *                           - start = 2
    *                           - end = 7
    *                               - end - start = 5
    *                               - have to move 5 units forward to get to 7
    *                           - assume now end = 1
    *                           - 1 - 2 = -1
    *                               - have to move -1 units forward (move back 1) to get to 1
    *               - this makes an arrow, from your eye -> through pixel -> into world
    *               - the arrow is the direction
    *   TLDR:
    *       - defines where each pixel sits in the 3D space
    *       - we need it since pixels are just (i,j), mean nothing in 3D space
    *           - pixels now become real points in 3D, which gives us a direction
    * pixel (i,j)
    *    → point on viewport (3D position)
    *    → direction = camera → that point
    *    → ray into scene
    *    → hit object
    *    → color
    * 
    * 
    * Viewport is not REAL, just a virtual mapper
    *   - combined w focal_length define cameras FOV, how wide your vision is
    *   - 2.0 and 1.0 are just convienent to use, to make coordinate system clean
    * - Viewport height of 2 means
    *   top = +1
    *   center = 0
    *   bottom = -1
    *       - symmetric at 0
    *       - 2 makes the math easier
    * 
    * - focal length = 1.0
    *   - how far the image plane is from your eye
    *       - how far view is from camera
    *   - 1 unit away from your eye
    *       - why 1?
    *           - makes direction math easy
    * 
    * Imagine a window, if your face is against it, you see less but more zoomed in,
    *   the further you go away from it, the more you see but also the smaller everything is
    */ 

    constexpr double viewport_height { 2.0 }, 
        viewport_width { viewport_height * ((static_cast<double>(width)) / height) },
        focal_length { 1.0 };
    
    // The viewport is just a rectangle in the 3D space
    //  - we need 3 things to describe it
    //  1. a starting corner (top left)
    //  2. A way to move right
    //  3. A way to move down
    //  ( we're inverting our viewport, the image is inheritly inverted
    //     so, for the img (monitor), start top left and as y moves down it increases)
    // but for our viewport, by normal conventions, as y increases, it goes up, but we 
    // need the opposite. so we'll define a way to move up and down
    
    const vec3 viewport_u { viewport_width, 0, 0 }, 
        viewport_v { 0, -viewport_height, 0 };
    // The viewport is just a coordinate system in 3D space.

    // We want to evenly space out our pixels, so we'll define the pixel spacing
    // the gap between each pixel horiontally and vertically

    // total screen width in 3D --> how much a pixel represents
    // viewport_u = full width of screen in 3D space
    //  - how wide the image is in 3D units
    // image_width = number of pixels horizontally
    //  - how many steps across the screen
    // SPLIT THE VIEWPORT WIDTH EVENLY ACROSS ALL PIXELS

    // img width is 400 discrete steps (num of pixels, count)
    // viewport width is 3.56 units (physical size in 3D, distance)
    //  - so we need to build a mapping, 
    //  split 3.56 into 400 units (EX), aka split vw into n pixel units
    const vec3 pixel_spacing_u { viewport_u * (1.0/width) },
        pixel_spacing_v { viewport_v * (1.0/height) };    

    // We want two things
    //  1. viewport_upper_left
    //  2. pixel00_loc 
    /**
    * top left corner of the whole viewport (big rectangle)
    *  and the center of pixel (0,0)
    * 
    * Calculating Upper Left   
    *   1. move the camera forward by focal length
    *       - remember, since -Z is "forward" towards the camera, we need to do subtraction
    *       - move along negative z axis
    *           - substraction is just move in opposite of other vec
    *   2. Using the center of the viewport (step 1), move to top left
    *       - remember, subtraction is moving
    *   TLDR of #1
    *       - from the camera, get the point to the center of the viewport
    *       -> get the point to the upperleft corner
    */

    // top left corner of the viewport
    //  - edge of the first pixel, not inside it
    //      - pixel_spacing_u & v are the pixel grid
    //          - we have to move the pixel half right and half down (center of a pixel)
    //          - pixel is not a corner, we have the corner
    const vec3 viewport_upper_left { 
        camera - vec3(0, 0, focal_length) 
            - (viewport_u * (1.0/2.0)) - (viewport_v * (1.0/2.0))
    }; 

    // we have the corner,
    /*
        •----+  the dot is viewport_upper_left
        |    |
        +----+
        - move to the center of this
            - start at top left corner, move towards the middle (add)
    */

    const vec3 pixel00_loc {viewport_upper_left + ((pixel_spacing_u + pixel_spacing_v) * 0.5)};

    // Now time to render the pixels
    constexpr int maxColor {255};
    std::cout << PPM_TYPE << "\n" << width << " " << height << "\n" << maxColor << "\n";
    
    for(int y = 0; y < height; ++y) {
        for(int x = 0; x < width; ++x) {
            const vec3 pixel_center {pixel00_loc + ( pixel_spacing_u * x) + (pixel_spacing_v * y)};

            // What direction do I need to take to get here
            // direction = destination - end
            const vec3 ray_direction {pixel_center - camera};
            const ray _ray { camera, ray_direction.unit() };

            const vec3 pixel_color { ray_color(_ray) };
            write_color(std::cout, pixel_color);
        }
    }

        // const int WIDTH{256}, HEIGHT{256}, MAXCOLORS{255};
    // std::cout << PPM_TYPE << "\n" << WIDTH << " " << HEIGHT << "\n" << MAXCOLORS << "\n";

    // for(int y = 0; y < HEIGHT; ++y) {
    //     for(int x = 0; x < WIDTH; ++x) {
    //         const vec3 pixelColor {
    //             double(x) / (WIDTH - 1), 
    //             0.2, 
    //             double(y) / (HEIGHT - 1), 
    //             vec3Type::COLOR
    //         };
            
    //         write_color(std::cout, pixelColor);
    //     }
    // }

    return 0;
}