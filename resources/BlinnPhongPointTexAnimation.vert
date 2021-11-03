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
layout (location = 4) in vec2 vInUV1;
layout (location = 5) in vec3 vNormal1;

// Projection and model-view matrix as input uniform variables
layout (location = 0) uniform mat4 pMatrix;
layout (location = 1) uniform mat4 mvMatrix;
layout (location = 2) uniform mat4 nMatrix;
layout (location = 3) uniform vec4 lightPosition;

uniform float TweenFactor;

// Outputs from vertex shader
out VS_OUT
{
    vec3 N;
    vec3 L;
    vec3 V;
    vec2 vUV;
    //Animation
    vec4 newVertex;
    vec3 newNormal;
} vs_out;

void main(void)
{
    //Animation
    newVertex.xyz = mix(vPosition, vPosition1, TweenFactor);
    newVertex.w = 1.0; // Make sure w is exactly 1.0

    // Calculate view-space coordinate
    vec4 P = mvMatrix * vec4(vPosition, 1.0);

    // Calculate normal in view-space
    vs_out.N = (mat4(nMatrix) * vec4(vNormal, 0)).xyz;

    // Calculate light vector
    vs_out.L = lightPosition.xyz - P.xyz;

    // Calculate view vector
    vs_out.V = -P.xyz;

    vs_out.vUV = vInUV;

    //ANIMATION
    gl_Position = ProjectionModelviewMatrix * newVertex;

    //ANIMATION
    newNormal = mix(vNormal, vNormal1, TweenFactor);
    EyeNormal = vec3(mvMatrix * vec4(newNormal, 0.0));
}


//// Vertex Shader
//// ATTRIBUTES
//attribute vec3 InVertex;
//attribute vec3 InNormal;
//attribute vec2 InMultiTexCoord0;
//attribute vec3 InVertex1;   // The Next keyframe vertex
//attribute vec3 InNormal1;   // The Next keyframe normal
//// UNIFORM
//uniform vec4 LightPosition0;
//uniform mat4 ModelviewMatrix;
//uniform float TweenFactor;
//uniform mat4 ProjectionModelviewMatrix;
//// --------------------
//// VARYING
//varying vec2 TexCoord0;
//varying vec4 LightVector0; // xyz is lightvector and w is light distance from vertex
//varying vec3 HalfVector0;
//varying vec3 EyeNormal;
//// --------------------
//void main()
//{
//    vec3 eyeVertex;
//    vec3 lightVector, eyeVector;
//    float mysqrtdistance;
//    vec4 newVertex;
//    vec3 newNormal;
//    // --------------------
//    newVertex.xyz = mix(InVertex, InVertex1, TweenFactor);
//    newVertex.w = 1.0; // Make sure w is exactly 1.0
//    // --------------------
//    gl_Position = ProjectionModelviewMatrix * newVertex;
//    TexCoord0 = InMultiTexCoord0;
//    // --------------------
//    eyeVertex = vec3(ModelviewMatrix * newVertex);
//    eyeVector = normalize(-eyeVertex);
//    lightVector = normalize(LightPosition0.xyz - eyeVertex);
//    LightVector0.xyz = lightVector;
//    mysqrtdistance = sqrt(distance(LightPosition0.xyz, eyeVertex));
//    LightVector0.w = mysqrtdistance * sqrt(mysqrtdistance);
//    HalfVector0 = lightVector + eyeVector; // No need to normalize the sum
//    // --------------------
//    newNormal = mix(InNormal, InNormal1, TweenFactor);
//    EyeNormal = vec3(ModelviewMatrix * vec4(newNormal, 0.0));
//}

