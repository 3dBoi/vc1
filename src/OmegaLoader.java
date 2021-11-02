import com.jogamp.opengl.GL3;
import com.jogamp.opengl.util.PMVMatrix;

public class OmegaLoader {

    public OmegaLoader(){

    }

    public void omegaInit(GL3 gl, InitObject[] objectArr, int [] vaoName, int[] vboName){

        objectArr[0]= new InitObject();
        objectArr[0].initObject(gl, "greencube.obj", "HSHLLogo2.jpg", "greencube.mtl", "BlinnPhongPointTex.vert", "BlinnPhongPointTex.frag", vaoName, vboName, 0);

        objectArr[1]= new InitObject();
        objectArr[1].initObject(gl, "redcube.obj", "HSHLLogo1.jpg", "redcube.mtl", "BlinnPhongPointTex.vert", "BlinnPhongPointTex.frag", vaoName, vboName, 1);

        objectArr[2]= new InitObject();
        objectArr[2].initObject(gl, "cube.obj", "GelbGruenPalette.png", "redcube.mtl", "BlinnPhongPointTex.vert", "BlinnPhongPointTex.frag", vaoName, vboName, 2);
    }

    public void omegaDisplay(GL3 gl, InitObject[] objectArr, DisplayObject[] displayArr, int[] vaoName, PMVMatrix pmvMatrix, LightSource light){

        displayArr[0] = new DisplayObject();
        displayArr[0].displayObject(gl, objectArr[0].getShaderProgram(), objectArr[0].getVertices(), vaoName, objectArr[0].getMaterial(), pmvMatrix, light, 0, objectArr[0].getTexture());

        displayArr[1] = new DisplayObject();
        displayArr[1].displayObject(gl, objectArr[1].getShaderProgram(), objectArr[1].getVertices(), vaoName, objectArr[1].getMaterial(), pmvMatrix, light, 1, objectArr[1].getTexture());

        displayArr[2] = new DisplayObject();
        displayArr[2].displayObject(gl, objectArr[2].getShaderProgram(), objectArr[2].getVertices(), vaoName, objectArr[2].getMaterial(), pmvMatrix, light, 2, objectArr[2].getTexture());

    }
}
