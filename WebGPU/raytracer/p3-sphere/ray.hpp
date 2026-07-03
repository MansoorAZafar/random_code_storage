#ifndef P3_RAY_HPP
#define P3_RAY_HPP
#include "vec3.hpp"

namespace P3 {
    struct Ray {
        const vec3 origin, direction;
        Ray(const vec3& origin, const vec3& direction): origin(origin), direction(direction) {}
        
        vec3 at(const double& t) const;
    };
};

#endif