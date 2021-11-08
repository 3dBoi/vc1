import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL3;
import com.jogamp.opengl.util.PMVMatrix;
import com.jogamp.opengl.util.texture.Texture;

import java.util.Arrays;

import static com.jogamp.opengl.GL.*;

public class DisplayObject {

    public DisplayObject(){

    }

    public void displayObject(GL3 gl, ShaderProgram shaderProgram, float[] vertices, int[] vaoName, Material material, PMVMatrix pmvMatrix, LightSource light, int bufferIndex, Texture texture){

        // Bind Texture to correct object
        if(texture!=null) {
            gl.glBindTexture(GL_TEXTURE_2D, texture.getTextureObject(gl));
        }
        // BEGIN: Draw the second object (object 1)
        gl.glUseProgram(shaderProgram.getShaderProgramID());

        // Transfer the PVM-Matrix (model-view and projection matrix) to the vertex shader
        gl.glUniformMatrix4fv(0, 1, false, pmvMatrix.glGetPMatrixf());
        gl.glUniformMatrix4fv(1, 1, false, pmvMatrix.glGetMvMatrixf());
        gl.glUniformMatrix4fv(2, 1, false, pmvMatrix.glGetMvitMatrixf());
        // transfer parameters of light source
        gl.glUniform4fv(3, 1, light.getPosition(), 0);
        gl.glUniform4fv(5, 1, light.getAmbient(), 0);
        gl.glUniform4fv(6, 1, light.getDiffuse(), 0);
        gl.glUniform4fv(7, 1, light.getSpecular(), 0);
        // transfer material parameters
        gl.glUniform4fv(8, 1, material.getEmission(), 0);
        gl.glUniform4fv(9, 1, material.getAmbient(), 0);
        gl.glUniform4fv(10, 1, material.getDiffuse(), 0);
        gl.glUniform4fv(11, 1, material.getSpecular(), 0);
        gl.glUniform1f(12, material.getShininess());

        gl.glBindVertexArray(vaoName[bufferIndex]);

//        System.out.println("Vertices in display: "+ Arrays.toString(vertices));

        gl.glDrawArrays(GL.GL_TRIANGLES, 0, vertices.length);
    }

    public void displayObjectAnimation(GL3 gl, ShaderProgram shaderProgram, float[] vertices, int[] vaoName, Material material, PMVMatrix pmvMatrix, LightSource light, int bufferIndex, Texture texture, float tweenF){


        // Bind Texture to correct object
        if(texture!=null) {
            gl.glBindTexture(GL_TEXTURE_2D, texture.getTextureObject(gl));
        }
        // BEGIN: Draw the second object (object 1)
        gl.glUseProgram(shaderProgram.getShaderProgramID());

        // Transfer the PVM-Matrix (model-view and projection matrix) to the vertex shader
        gl.glUniformMatrix4fv(0, 1, false, pmvMatrix.glGetPMatrixf());
        gl.glUniformMatrix4fv(1, 1, false, pmvMatrix.glGetMvMatrixf());
        gl.glUniformMatrix4fv(2, 1, false, pmvMatrix.glGetMvitMatrixf());
        // transfer parameters of light source
        gl.glUniform4fv(3, 1, light.getPosition(), 0);

        // Tweenfactor for Animation (linear Interpolation)
        gl.glUniform1f(4, tweenF);

        gl.glUniform4fv(5, 1, light.getAmbient(), 0);
        gl.glUniform4fv(6, 1, light.getDiffuse(), 0);
        gl.glUniform4fv(7, 1, light.getSpecular(), 0);
        // transfer material parameters
        gl.glUniform4fv(8, 1, material.getEmission(), 0);
        gl.glUniform4fv(9, 1, material.getAmbient(), 0);
        gl.glUniform4fv(10, 1, material.getDiffuse(), 0);
        gl.glUniform4fv(11, 1, material.getSpecular(), 0);
        gl.glUniform1f(12, material.getShininess());

        gl.glBindVertexArray(vaoName[bufferIndex]);

        gl.glDrawArrays(GL.GL_TRIANGLES, 0, vertices.length);
    }


    public void displayObjectPoints(GL3 gl, ShaderProgram shaderProgram, float[] vertices, int[] vaoName, Material material, PMVMatrix pmvMatrix, LightSource light, int bufferIndex, Texture texture) {

        // Bind Texture to correct object
        if (texture != null) {
            gl.glBindTexture(GL_TEXTURE_2D, texture.getTextureObject(gl));
        }
        // BEGIN: Draw the second object (object 1)
        gl.glUseProgram(shaderProgram.getShaderProgramID());

        // Transfer the PVM-Matrix (model-view and projection matrix) to the vertex shader
        gl.glUniformMatrix4fv(0, 1, false, pmvMatrix.glGetPMatrixf());
        gl.glUniformMatrix4fv(1, 1, false, pmvMatrix.glGetMvMatrixf());
        gl.glUniformMatrix4fv(2, 1, false, pmvMatrix.glGetMvitMatrixf());
        // transfer parameters of light source
        gl.glUniform4fv(3, 1, light.getPosition(), 0);
        gl.glUniform4fv(5, 1, light.getAmbient(), 0);
        gl.glUniform4fv(6, 1, light.getDiffuse(), 0);
        gl.glUniform4fv(7, 1, light.getSpecular(), 0);
        // transfer material parameters
        gl.glUniform4fv(8, 1, material.getEmission(), 0);
        gl.glUniform4fv(9, 1, material.getAmbient(), 0);
        gl.glUniform4fv(10, 1, material.getDiffuse(), 0);
        gl.glUniform4fv(11, 1, material.getSpecular(), 0);
        gl.glUniform1f(12, material.getShininess());

        gl.glBindVertexArray(vaoName[bufferIndex]);

//        System.out.println("Vertices in display: "+ Arrays.toString(vertices));

        gl.glPointSize(1);
        gl.glDrawArrays(GL_POINTS, 0, vertices.length);
    }
}
