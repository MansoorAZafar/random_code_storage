#ifndef P3_VEC3_HPP
#define P3_VEC3_HPP
#include <iostream>
#include <concepts>

namespace P3 {
    template <class Target, class... Args>
    concept AllOfSpecificType = (std::same_as<Target, Args> && ...);

    enum class vec3Type {
        POINT,
        COLOR
    };

    struct vec3 {
        double x,y,z;
        vec3Type type;

        vec3(const double& x, const double& y, const double& z, const vec3Type type = vec3Type::POINT)
            : x(x), y(y), z(z), type(type) {}
        
        vec3 operator*(const double& scaler) const;
        vec3 operator/(const double& scaler) const;
        vec3 operator+(const vec3& vec) const;
        vec3 operator-(const vec3& vec) const;

        double dot(const vec3& vec) const;
        double length() const;
        vec3 unit() const;

        template <class... Args>
        requires AllOfSpecificType<vec3Type, Args...>
        bool supports(Args... args) const {
            return ((this->type == args) || ...);
        };

        friend void write_color(std::ostream& out, const vec3& vec);
    };
};

#endif