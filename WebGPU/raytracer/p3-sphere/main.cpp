#include <iostream>
#include "ray.hpp"
#include "vec3.hpp"
#define PPM_TYPE "P3"

namespace P3 {
    /**
    * We want to know:
    *   - does the ray ever touch the sphere
    *       - So, we're trying to find a value of T such that:
    *           ray point at time of t is on sphere
    *               P(t) = A + tb
    *   - On the sphere
    *       - means: a point P is on the sphere IF
    *           distance(P, center) = radius
    *               - all points on a sphere are equal distance to the radius
    *               - so if the point we are touching at t, is equal distance to radius, then we are on sphere
    *   - Distance is equal to sqrt(P - C) = r
    *       -> square both sides to remove sqrt
    *           -> just makes life easier
    *       -> (P - C) * (P - C) = r²
    *           - p = point = A + tb
    *       -> Plug it in
    *           - (Q + tb - C) * (Q + tb - C) = r² 
    *           -> Let oc = (Q - C)
    *               = (oc + td) * (oc + td) = r²
    *               = oc·oc + 2t(oc·d) + t²(d·d)
    *               = t²(d·d) + 2t(oc·d) + (oc·oc - r²) = 0 (quadratic equation)
    * Term 1: t²(d·d)
    *    controls curvature of ray movement
    * Term 2: 2t(oc·d)
    *    does ray point toward sphere?
    * Term 3: oc·oc
    *    distance from camera to sphere center
    * Term 4: -r²
    *    subtract sphere size
    * 
    *       - D = b² - 4ac
    *           - Case 1: D < 0  -> no intersection
    *           - Case 1: D < 0  -> touches
    *           - Case 3: D > 0  -> hits sphere
    */
    // I still dont get it lmfao
    bool hit_sphere(const vec3& point, double radius, const Ray& ray) {
        // just lookup quadratic equation lil bro
        const vec3 oc  { point - ray.origin };

        const double a { ray.direction.dot(ray.direction) };
        const double b { -2.0     * ray.direction.dot(oc) };
        const double c { oc.dot(oc)     - radius * radius };

        const double discriminant { b*b - 4*a*c };
        return discriminant >= 0;   
    }

    vec3 ray_color(const Ray& ray) {
        // Lowkey still dunno this part, I dunnno this cheif
        if(hit_sphere(vec3(0,0,-1), 0.5, ray)) {
            return {1, 0, 0, vec3Type::COLOR};
        }

        const double a { (ray.direction.y + 1.0) * 0.5 };
        const vec3 color { vec3(1.0, 1.0, 1.0) * (1.0-a) + vec3(0.5, 0.7, 1.0) * a };

        return {color.x, color.y, color.z, vec3Type::COLOR};
    }
};

using namespace P3;
int main() {
    // Stage 1: define aspect ratio, width and height
    constexpr double aspect_ratio { 16.0 / 9.0 };
    constexpr int width { 800 }, 
        potential_height { static_cast<int>(width / aspect_ratio) }, 
        height { potential_height ? potential_height : 1 };

    // Stage 2: Camera
    vec3 camera { 0.5, -0.7, 0 };
    
    // Stage 3: Viewport Dimenions (init)
    constexpr double viewport_height { 2.0 }, 
        viewport_width { viewport_height * (static_cast<double>(width)) / height },
        focal_length { 1.0 };

    // Stage 4: Viewport Traversal
    const vec3 viewport_u { viewport_width, 0, 0 }, 
        viewport_v { 0, -viewport_height, 0 };
        
    // Stage 5: Pixel Spacing
    // Split evenly into pixel spacing
    const vec3 pixel_delta_u { viewport_u / width }, pixel_delta_v { viewport_v / height };

    // Stage 6: Getting the top left pixel & corner
    const vec3 upper_left_corner { 
        camera - vec3(0, 0, focal_length)
        - (viewport_u * (viewport_width / 2.0))
        - (viewport_v * (viewport_height / 2.0))
    };
    const vec3 pixel00_loc { upper_left_corner + ((pixel_delta_u + pixel_delta_v) * 0.5) };


    // Stage 7: Print out the data
    constexpr int maxColors {255};
    std::cout << PPM_TYPE << "\n" << width << " " << height << "\n" << maxColors << "\n";

    for(int y = 0; y < height; ++y) {
        for(int x = 0; x < width; ++x) {
            // where on the viewport does the pixel live
            const vec3 pixel_center { pixel00_loc + (pixel_delta_v * y) + (pixel_delta_u * x) };
            const vec3 ray_direction { pixel_center - camera };

            const Ray ray { camera, ray_direction.unit() };
            const vec3 color { ray_color(ray) };

            write_color(std::cout, color);
        }
    }
    return 0;
}