#ifndef P2_VEC3
#define P2_VEC3
#include <iostream>
#include <concepts>

namespace p2 {
    
    template <class Target, class... Args>
    concept AllOfSpecificType = (std::same_as<Target, Args> && ...);

    /**
     * Basic vec3tor 3 Class
     */
    enum class vec3Type {
        COLOR,
        POINT
    };

    struct vec3 { 
        double x, y, z; 
        vec3Type type; // discriminator

        vec3() = default;
        vec3(double x, double y, double z, vec3Type type = vec3Type::POINT)
            : x(x), y(y), z(z), type(type) {}

        vec3 operator+(const vec3& vec) const;
        vec3 operator-(const vec3& vec) const;
        vec3 operator*(const double& scaler) const;

        vec3 unit() const;
        double length() const;
        double dot(const vec3& vec) const;

        friend std::ostream& operator<<(std::ostream& ostr, const vec3& vec);
        
        template<typename... Args>
        requires AllOfSpecificType<vec3Type, Args...>
        bool supports(Args... args) const {
            return ((this->type == args) || ...);
        }

        // COLOR ONLY
        friend void write_color(std::ostream& out, const vec3& pixelColor);
    };
};

#endif