#include "ray.hpp"
#include "vec3.hpp"

namespace P3 {
    vec3 Ray::at(const double& t) const {
        // P(t) = A + tb
        return (this->origin + (this->direction * t));
    }
};