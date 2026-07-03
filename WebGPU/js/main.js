import { shader } from "./shader.js";

const adapter = await navigator.gpu.requestAdapter();
const device = await adapter.requestDevice();

const canvas = document.querySelector("canvas");
canvas.width = window.innerWidth;
canvas.height = window.innerHeight;

const context = canvas.getContext("webgpu");
const format = navigator.gpu.getPreferredCanvasFormat();
context.configure({
    device,
    format,
    alphaMode: "opaque"
});

const shaderModule = device.createShaderModule({
    code: shader
});

const uniformBuffer = device.createBuffer({
    size: 32,
    usage:
        GPUBufferUsage.UNIFORM |
        GPUBufferUsage.COPY_DST
});

const bindGroupLayout = device.createBindGroupLayout({
    entries: [{
        binding: 0,
        visibility: GPUShaderStage.FRAGMENT,
        buffer: {}
    }]
});

const bindGroup = device.createBindGroup({
    layout: bindGroupLayout,
    entries: [{
        binding: 0,
        resource: {
            buffer: uniformBuffer
        }
    }]
});

const pipeline = device.createRenderPipeline({
    layout: device.createPipelineLayout({
        bindGroupLayouts: [
            bindGroupLayout
        ]
    }),

    vertex: {
        module: shaderModule,
        entryPoint: "vs_main"
    },

    fragment: {
        module: shaderModule,
        entryPoint: "fs_main",
        targets: [{
            format
        }]
    },

    primitive: {
        topology: "triangle-list"
    }
});

const camera = {
    x: 0,
    y: 0,
    z: 0
};

const keys = {};
window.addEventListener("keydown", e => {
    keys[e.key.toLowerCase()] = true;
});

window.addEventListener("keyup", e => {
    keys[e.key.toLowerCase()] = false;
});

function updateCamera() {
    const speed = 0.05;
    if (keys["w"])
        camera.z -= speed;

    if (keys["s"])
        camera.z += speed;

    if (keys["a"])
        camera.x -= speed;

    if (keys["d"])
        camera.x += speed;

    if (keys["q"])
        camera.y -= speed;

    if (keys["e"])
        camera.y += speed;
}

function updateUniforms() {
    const data = new Float32Array([
        canvas.width,
        canvas.height,

        0, // padding
        0,

        camera.x,
        camera.y,
        camera.z,
        0
    ]);

    device.queue.writeBuffer(
        uniformBuffer,
        0,
        data
    );
}

function render() {

    updateCamera();
    updateUniforms();

    const encoder = device.createCommandEncoder();

    const pass = encoder.beginRenderPass({
        colorAttachments: [{
            view: context
                .getCurrentTexture()
                .createView(),

            clearValue: {
                r: 0,
                g: 0,
                b: 0,
                a: 1
            },

            loadOp: "clear",
            storeOp: "store"
        }]
    });

    pass.setPipeline(pipeline);
    pass.setBindGroup(
        0,
        bindGroup
    );

    pass.draw(3);
    pass.end();

    device.queue.submit([
        encoder.finish()
    ]);

    requestAnimationFrame(render);
}

render();