#include "ray.h"

namespace p2 {
    vec3 ray::at(const double& t) const {
        // we need to do vec3 * t not t * vec3 since vec3 has * overload, not int
        return (this->origin + (this->direction * t));
    }
};