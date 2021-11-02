import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL3;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.nio.FloatBuffer;

import static com.jogamp.opengl.GL.*;

public class InitObject {

    private ShaderProgram shaderProgram;
    private Material material;
    private float[] vertices;
    private Texture texture;


    public InitObject(){

    }


    public void initObject(GL3 gl, String modelPath, String texturePath, String materialPath, String vertexShader, String fragmentShader, int[] vaoName, int[] vboName, int bufferIndex){

        gl.glBindVertexArray(vaoName[bufferIndex]);
        this.shaderProgram = new ShaderProgram(gl);
        shaderProgram.loadShaderAndCreateProgram(".\\resources\\",
                vertexShader, fragmentShader);

        //load Model as OBJ
        ModelLoader modelLoader = new ModelLoader(modelPath);

        //modelVertices
        this.vertices = modelLoader.getVerticies();


        // activate and initialize vertex buffer object (VBO)
        gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vboName[bufferIndex]);
        // floats use 4 bytes in Java
        gl.glBufferData(GL.GL_ARRAY_BUFFER, vertices.length * 4,
                FloatBuffer.wrap(vertices), GL.GL_STATIC_DRAW);

        // Activate and order vertex buffer object data for the vertex shader
        // The vertex buffer contains: position (3), UV (2), normals (3)
        // Defining input for vertex shader
        // Pointer for the vertex shader to the position information per vertex
        gl.glEnableVertexAttribArray(0);
        gl.glVertexAttribPointer(0, 3, GL.GL_FLOAT, false, 8*4, 0);
        // Pointer for the vertex shader to the texture coordinates information per vertex
        gl.glEnableVertexAttribArray(1);
        gl.glVertexAttribPointer(1, 2, GL.GL_FLOAT, false, 8*4, 3*4);
        // Pointer for the vertex shader to the normal information per vertex
        gl.glEnableVertexAttribArray(2);
        gl.glVertexAttribPointer(2, 3, GL.GL_FLOAT, false, 8*4, 5*4);


        // Metallic material
        float[] matEmission = {0.0f, 0.0f, 0.0f, 1.0f};
        float[] matAmbient =  {0.2f, 0.2f, 0.2f, 1.0f};
        float[] matDiffuse =  {0.5f, 0.5f, 0.5f, 1.0f};
        float[] matSpecular = {0.7f, 0.7f, 0.7f, 1.0f};
        float matShininess = 200.0f;

        this.material = new Material();
        this.material.loadMaterial(materialPath, 200.0f);



        // Load and prepare texture
        if(texturePath!=null){
            try {
                File textureFile = new File(".\\resources\\"+texturePath);
                texture = TextureIO.newTexture(textureFile, true);

                texture.setTexParameteri(gl, gl.GL_TEXTURE_MIN_FILTER, gl.GL_LINEAR);
                texture.setTexParameteri(gl, gl.GL_TEXTURE_MAG_FILTER, gl.GL_LINEAR);
                texture.setTexParameteri(gl, gl.GL_TEXTURE_WRAP_S, gl.GL_CLAMP_TO_EDGE);
                texture.setTexParameteri(gl, gl.GL_TEXTURE_WRAP_T, gl.GL_CLAMP_TO_EDGE);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (texture != null) {
                   System.out.println("Texture loaded successfully from: " + ".\\resources\\"+texturePath);
            }
            else
                System.err.println("Error loading textue.");
            System.out.println("  Texture height: " + texture.getImageHeight());
            System.out.println("  Texture width: " + texture.getImageWidth());
            System.out.println("  Texture object: " + texture.getTextureObject(gl));
            System.out.println("  Estimated memory size of texture: " + texture.getEstimatedMemorySize());

            texture.enable(gl);

            // Activate texture in slot 0 (might have to go to "display()")
            gl.glActiveTexture(GL_TEXTURE0);
        }


    }

    public ShaderProgram getShaderProgram(){
        return shaderProgram;
    }

    public Material getMaterial(){
        return material;
    }

    public float[] getVertices(){
        return vertices;
    }

    public Texture getTexture(){
        return texture;
    }
}
