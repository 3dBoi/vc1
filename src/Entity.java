import com.jogamp.opengl.GL3;
import com.jogamp.opengl.util.PMVMatrix;

public class Entity {

    InitObject initObject = new InitObject();
    DisplayObject displayObject = new DisplayObject();
    AnimationHandler animationHandler = new AnimationHandler();

    GL3 gl;

    // Resource Paths
    String[] modelPath;
    String texturePath;
    String materialPath;

    // Shader Paths
    String vertexShader;
    String fragmentShader;

    // VertexArray
    int[] vaoName;
    int[] vboName;
    int index;

    PMVMatrix pmvMatrix;
    LightSource light;

    // Tween for Animation
    float tween;
    float tweenNextFrame = 1.0f;

    // Keyframeindex
    int keyframeIndex = 1;


    public Entity(GL3 gl, String[] modelPath, String texturePath, String materialPath, int[] vaoName, int[] vboName, int bufferIndex, PMVMatrix pmvMatrix, LightSource light){

        this.gl = gl;

        this.modelPath = modelPath;
        this.texturePath = texturePath;
        this.materialPath = materialPath;

        this.fragmentShader = "BlinnPhongPointTex.frag";

        this.vaoName = vaoName;
        this.vboName = vboName;
        this.index = bufferIndex;

        this.pmvMatrix = pmvMatrix;
        this.light = light;
    }

    // Standard Initialization without Keyframe
    public void initEntity(){
        if(modelPath.length>1){
            this.vertexShader = "BlinnPhongPointTexAnimation.vert";
            initObject.initAnimatedObject(gl, modelPath, texturePath, materialPath, vertexShader, fragmentShader, vaoName, vboName, index);
        }else{
            this.vertexShader = "BlinnPhongPointTex.vert";
            initObject.initObject(gl, modelPath, texturePath, materialPath, vertexShader, fragmentShader, vaoName, vboName, index);
        }
    }

    // Standard Display without Animation
    public void displayEntity(){
        displayObject.displayObject(gl, initObject.getShaderProgram(), initObject.getVertices(), vaoName, initObject.getMaterial(), pmvMatrix, light, index, initObject.getTexture());
    }

    // Update Entity for Animation
    public void updateEntity(int keyframeIndex){
        initObject.updateKeyframe(gl, modelPath, vboName, index, keyframeIndex);
    }

    // Display actual Animation
    public void playAnimation(){



        // if the Animation is going forward display it and update tween
        if(animationHandler.getDirection()){

            displayObject.displayObjectAnimation(gl, initObject.getShaderProgram(), initObject.getVertices(), vaoName, initObject.getMaterial(), pmvMatrix, light, index, initObject.getTexture(), tween);
            this.tween = animationHandler.animate();

        // if Animation is finished update the Keyframe and start Animation again
        }else{

            displayObject.displayObjectAnimation(gl, initObject.getShaderProgram(), initObject.getVertices(), vaoName, initObject.getMaterial(), pmvMatrix, light, index, initObject.getTexture(), 1.0f);
            animationHandler.setDirection(true);
            this.tween = animationHandler.animate();

            initObject.updateKeyframe(gl, modelPath, vboName, index, keyframeIndex);

            // If it is the last Keyframe, reset the Keyframindex, if not, add 1 to it
            if(keyframeIndex>=modelPath.length-1){
                keyframeIndex=0;
            }else{
                keyframeIndex++;
            }
        }
    }

    public AnimationHandler getAnimationHandler(){
        return animationHandler;
    }

    public void setTween(float tween){
        this.tween = tween;
    }

    public InitObject getInitObject(){
        return initObject;
    }
}
