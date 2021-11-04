#version 430 core

//MACHT NOCH NICHTS

// Algorithm based on algorithm from
// Sellers, Graham, Wright, Richard S., Haemel, Nicholas (2014).
// OpenGL Super Bible. 6th edition. Addison Wesley.

// Point light source
// To use this shader set for generating a directional light source,
// put the light source very far away from the objects to be lit

// Puts a texture on the surfaces of the object

// Author: Karsten Lehn
// Version: 12.11.2017, 16.9.2019

// position, color, normal and texture coordinates of vertex as input vertex attribute
layout (location = 0) in vec3 vPosition;
layout (location = 1) in vec2 vInUV;
layout (location = 2) in vec3 vNormal;
layout (location = 3) in vec3 vPosition1;
layout (location = 4) in vec3 vNormal1;

// Projection and model-view matrix as input uniform variables
layout (location = 0) uniform mat4 pMatrix;     //Projection
layout (location = 1) uniform mat4 mvMatrix;    //ModelView
layout (location = 2) uniform mat4 nMatrix;     //
layout (location = 3) uniform vec4 lightPosition;
layout (location = 4) uniform float TweenFactor;

// Outputs from vertex shader
out VS_OUT
{
    vec3 N;     //normal in view-space
    vec3 L;     //light vector
    vec3 V;     //view vector
    vec2 vUV;
} vs_out;

void main(void)
{
//    vec3 eyeVertex;
//    vec3 lightVector, eyeVector;
//    float mysqrtdistance;
    vec4 newVertex;
    vec3 newNormal;
    //Animation
    newVertex.xyz = mix(vPosition, vPosition1, TweenFactor);
    newVertex.w = 1.0; // Make sure w is exactly 1.0

    newNormal = mix(vNormal, vNormal1, TweenFactor);

    // Calculate view-space coordinate
    vec4 P = mvMatrix * vec4(newVertex);

    gl_Position = pMatrix * P;

    // Calculate normal in view-space
    vs_out.N = (mat4(nMatrix) * vec4(newNormal, 0)).xyz;

    // Calculate light vector
    vs_out.L = lightPosition.xyz - P.xyz;

    // Calculate view vector
    vs_out.V = -P.xyz;

    vs_out.vUV = vInUV;

}
