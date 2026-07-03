#include "vec3.hpp"
#include <cmath>

namespace P3 {
    double vec3::dot(const vec3& vec) const {
        return { (this->x * vec.x) + (this->y * vec.y) + (this->z * vec.z) };
    }

    double vec3::length() const {
        return { std::sqrt( this->dot(*this) ) };
    }

    vec3 vec3::unit() const {
        return { *this / this->length() };
    }

    vec3 vec3::operator*(const double& scaler) const {
        return { this->x * scaler, this->y * scaler, this->z * scaler };
    }

    vec3 vec3::operator/(const double& scaler) const {
        return ( *this * ( 1.0 / scaler) );
    }

    vec3 vec3::operator+(const vec3& vec) const {
        return { this->x + vec.x, this->y + vec.y, this->z + vec.z };
    }

    vec3 vec3::operator-(const vec3& vec) const {
        return { this->x - vec.x, this->y - vec.y, this->z - vec.z };
    }

    void write_color(std::ostream& out, const vec3& vec) {
        if(!vec.supports(vec3Type::COLOR)) throw std::invalid_argument("Must be of type Color");

        const double r{vec.x}, g{vec.y}, b{vec.z};
        const int rbyte {int(255.999 * r)}, gbyte {int(255.999 * g)}, bbyte {int(255.999 * b)};

        out << rbyte << " " << gbyte << " " << bbyte << "\n";
    }
}