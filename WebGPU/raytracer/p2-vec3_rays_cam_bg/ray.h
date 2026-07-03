#ifndef P2_RAY
#define P2_RAY
#include "vec3.hpp"

/**
 * A Ray is jus a starting point and a direction
 *  - camera shoots rays into a scene to "see" what it hits
 *  - The base equation is P(t) = A + tb
 *      - P(t) = the point (position) for a given t
 *          - given a direction, origin and amount, what it all equates to
 *      - A = ray origin (starting point, where it came from)
 *      - b = ray direction vector (which way it points)
 *          - which way to move along
 *      - t = how far along the ray we move 
 *          - position = start_point + (direction * amount)  
 *  Ex.
 *      Start: Toronto
 *      Direction: North
 *      Distance: 10 km
 *          - all together = P(t), where you end up
 * 
 *  Note: about direction(b)
 *      - can always be a unit vector but doesn't have to be
 *      - the actual value of b doesn't matter, we just look at it for direction
 *      so
 *          (57, 0, 0) and (1, 0, 0) are equal
 *          - they both represent the up direction positive, 
 *              the 57 andd 1 aren't being used for amount, only ( t ) is
 *          - so even if dir has a value > 1, it doesn't matter 
 *      - IF you decide not to "normalize" ( change b to a unit vector (only 1s) ), 
 *          then t needs to be scaled 
 *          i.e 
 *              (1,   0,  0), t = 5  --> (5, 0, 0)
 *              (100, 0 , 0), t NEEDS to be 0.05 for --> (5, 0, 0)
 *                  - so if you don't normalize b, need to scale t down
 *                  - so we use unit() from the vec3 
*/

namespace p2 {
    struct ray {
        const vec3 origin, direction;

        ray(const vec3& origin, const vec3& direction)
            : origin(origin), direction(direction) {}

        // Gives the vector at that point
        // If you want to move n from a point or get vector at T
        //  - returns this
        vec3 at(const double& t) const;
    };
};

#endif