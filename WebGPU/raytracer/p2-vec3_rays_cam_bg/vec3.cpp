#include "vec3.hpp"
#include <cmath>

namespace p2 {
    double vec3::dot(const vec3& vec) const {
        return (
            (this->x * vec.x)
          + (this->y * vec.y)
          + (this->z * vec.z)
        );
    }
    
    // Length is just projecting onto itself (dot) then sqrt()
    double vec3::length() const { return std::sqrt(this->dot(*this)); }    
    
    // Returns a vector of len 1 max value (can be negative, positive ...)
    // aka, the direction
    // can also be called normalize()
    vec3 vec3::unit() const {
        return ( *this * (1.0 / this->length()) );
    }
    
    vec3 vec3::operator*(const double& scaler) const {
        return { this->x * scaler, this->y * scaler, this->z * scaler };
    }

    vec3 vec3::operator+(const vec3& vec) const {
        return { this->x + vec.x, this->y + vec.y, this->z + vec.z };
    }

    vec3 vec3::operator-(const vec3& vec) const {
        return { this->x - vec.x, this->y - vec.y, this->z - vec.z };
    }

    std::ostream& operator<<(std::ostream& ostr, const vec3& vec) {
        return ostr << vec.x << " " << vec.y << vec.z;
    }

    void write_color(std::ostream& out, const vec3& pixelColor) {
        if(!pixelColor.supports(vec3Type::COLOR)) {
            throw std::invalid_argument("Must be of type Color");
        } 

        const double r {pixelColor.x}, g {pixelColor.y}, b{pixelColor.z};
        
        const int rbyte = int(255.999 * r);
        const int gbyte = int(255.999 * g);
        const int bbyte = int(255.999 * b);

        out << rbyte << " " << gbyte << " " << bbyte << "\n";
    }
};