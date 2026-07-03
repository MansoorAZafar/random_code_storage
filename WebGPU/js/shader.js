export const shader = `
struct Uniforms {
    canvasSize : vec2<f32>,
    _pad : vec2<f32>,
    cameraPos : vec4<f32>,
};

struct Ray {
    origin : vec3<f32>,
    direction : vec3<f32>,
};

@group(0) @binding(0)
var<uniform> uniforms : Uniforms;

@vertex
fn vs_main(
    @builtin(vertex_index) idx : u32
) -> @builtin(position) vec4<f32> {

    var pos = array<vec2<f32>, 3>(
        vec2<f32>(0.0, 1.0),
        vec2<f32>(-1.0, -1.0),
        vec2<f32>(1.0, -1.0)       

        // vec2<f32>(-1.0, -1.0),
        // vec2<f32>( 1.0, -1.0),
        // vec2<f32>(-1.0,  1.0)

        // vec2<f32>(-1.0,  1.0),
        // vec2<f32>( 1.0, -1.0),
        // vec2<f32>( 1.0,  1.0)
    );

    return vec4<f32>(pos[idx], 0.0, 1.0);
}

// given this pixel, which way should the ray go (direction)
fn get_ray(
    uv : vec2<f32>,
    cameraPos : vec3<f32>
) -> Ray {

    let aspect =
        uniforms.canvasSize.x /
        uniforms.canvasSize.y;

    // find, where x and y are in 3D space

    // uv is naturally from [0, N]
    //  - we now change it to [-1, 1]
    let x =
        (uv.x * 2.0 - 1.0) * aspect;

    // same as x but flips y 
    //  - remember, y-axis increases by going down
    let y =
        1.0 - uv.y * 2.0;

    // point on the viewport that the ray is aiming through
    let pixel =
        cameraPos + 
        vec3<f32>(
            x,
            y,
            -1.0
        );

    return Ray(
        cameraPos,
        normalize(pixel - cameraPos)
    );
}

fn hit_sphere(
    center : vec3<f32>,
    radius : f32,
    ray : Ray
) -> bool {

    let oc = center - ray.origin;

    let a =
        dot(ray.direction, ray.direction);

    let b =
        -2.0 * dot(ray.direction, oc);

    let c =
        dot(oc, oc) - radius * radius;

    let discriminant =
        b * b - 4.0 * a * c;

    return discriminant >= 0.0;
}

fn ray_color(
    ray : Ray
) -> vec3<f32> {

    if (
        hit_sphere(
            vec3<f32>(0.0, 0.0, -3.0),
            0.5,
            ray
        )
    ) {
        return vec3<f32>(1.0, 0.0, 0.0);
    }

    let a =
        (ray.direction.y + 1.0) * 0.5;

    return
        vec3<f32>(1.0, 1.0, 1.0) * (1.0 - a)
        +
        vec3<f32>(0.5, 0.7, 1.0) * a;
}

@fragment
fn fs_main(
    @builtin(position) fragCoord : vec4<f32>
) -> @location(0) vec4<f32> {
    // .xy acts as the viewport
    
    // pixel coord into normalized screen coordinate
    // [0, 1] instead of [0, 1920] (for example)
    //  - where on the screen is the pixel as a %
    let uv =
        fragCoord.xy /
        uniforms.canvasSize;

    let ray =
        get_ray(
            uv,
            uniforms.cameraPos.xyz
        );

    let color =
        ray_color(ray);

    return vec4<f32>(color, 1.0);
}
`