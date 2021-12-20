import com.jogamp.opengl.GL3;
import com.jogamp.opengl.math.Ray;
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

    // Modelorigin (not used)
    float[] origin;
    boolean rotation = false;
    float[] texCoordinates;

    // boolean for Hitbox Entity
    boolean hitbox = false;
    Collision collision;


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

    // Initialization for rotatable Object (not used)
    public Entity(GL3 gl, String[] modelPath, String texturePath, String materialPath, int[] vaoName, int[] vboName, int bufferIndex, PMVMatrix pmvMatrix, LightSource light, float[] origin){

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

        this.origin = origin;

        this.rotation = true;
        ModelLoader modelLoader = new ModelLoader();
        this.texCoordinates = modelLoader.getTexCoordinates(modelPath[0]);
    }

    public Entity(GL3 gl, String[] modelPath, int[] vaoName, int[] vboName, int bufferIndex){

        this.gl = gl;
        this.modelPath = modelPath;
        this.fragmentShader = "BlinnPhongPointTex.frag";
        this.vaoName = vaoName;
        this.vboName = vboName;
        this.index = bufferIndex;
        this.hitbox = true;
        this.collision = new Collision();
    }

    // Standard Initialization without Keyframe
    public void initEntity(){

        if(hitbox){
            this.vertexShader = "BlinnPhongPointTex.vert";
            initObject.initHitbox(gl, modelPath, vertexShader, fragmentShader, vaoName, index);
        }else if(modelPath.length>1&&rotation){
            this.vertexShader = "BlinnPhongPointTex.vert";
            initObject.initAnimatedObject(gl, modelPath, texturePath, materialPath, vertexShader, fragmentShader, vaoName, vboName, index);
        }else if(modelPath.length>1){
            this.vertexShader = "BlinnPhongPointTexAnimation.vert";
            initObject.initAnimatedObject(gl, modelPath, texturePath, materialPath, vertexShader, fragmentShader, vaoName, vboName, index);
        }else{
            this.vertexShader = "BlinnPhongPointTex.vert";
            initObject.initObject(gl, modelPath, texturePath, materialPath, vertexShader, fragmentShader, vaoName, vboName, index);
        }
    }

    // Standard Display without Animation
    public void displayEntity(){
        if(!hitbox)
        displayObject.displayObject(gl, initObject.getShaderProgram(), initObject.getVertices(), vaoName, initObject.getMaterial(), pmvMatrix, light, index, initObject.getTexture());
    }

    // Update Entity for Animation (not used)
    public void updateEntity(int keyframeIndex){
        initObject.updateKeyframe(gl, modelPath, vboName, index, keyframeIndex);
    }

    // Display actual Animation
    public void playAnimation(){


        // if the Animation is going forward display it and update tween
        if(animationHandler.getDirection()){

            if(!hitbox){
                displayObject.displayObjectAnimation(gl, initObject.getShaderProgram(), initObject.getVertices(), vaoName, initObject.getMaterial(), pmvMatrix, light, index, initObject.getTexture(), tween);
            }

            this.tween = animationHandler.animate();

        // if Animation is finished update the Keyframe and start Animation again
        }else{

            if(!hitbox){
                displayObject.displayObjectAnimation(gl, initObject.getShaderProgram(), initObject.getVertices(), vaoName, initObject.getMaterial(), pmvMatrix, light, index, initObject.getTexture(), tween);
            }

            this.tween = animationHandler.animate();
            if(this.tween<=0){
                animationHandler.setDirection(true);
                animationHandler.setAnimationTrigger(false);
            }

            // if Object has more than 1 Keyframe (not used)
//            initObject.updateKeyframe(gl, modelPath, vboName, index, keyframeIndex);
//
//            // If it is the last Keyframe, reset the Keyframindex, if not, add 1 to it
//            if(keyframeIndex>=modelPath.length-1){
//                keyframeIndex=1;
//            }else{
//                keyframeIndex++;
//            }
        }
    }

    // Play rotationanimation (not used)
    public void playRotation(){

        if(animationHandler.getDirection()){

            if(!hitbox){
                initObject.updateRotation(gl, animationHandler.rotate(modelPath[keyframeIndex-1], modelPath[keyframeIndex], origin, tween), vboName, index);
                displayObject.displayObject(gl, initObject.getShaderProgram(), initObject.getVertices(), vaoName, initObject.getMaterial(), pmvMatrix, light, index, initObject.getTexture());
            }
            this.tween = animationHandler.animate();
        } else{

            if(!hitbox){
                initObject.updateRotation(gl, animationHandler.rotate(modelPath[keyframeIndex-1], modelPath[keyframeIndex], origin, 1.0f), vboName, index);
                displayObject.displayObject(gl, initObject.getShaderProgram(), initObject.getVertices(), vaoName, initObject.getMaterial(), pmvMatrix, light, index, initObject.getTexture());
            }
            animationHandler.setDirection(true);
            this.tween = animationHandler.animate();
            // If it is the last Keyframe, reset the Keyframindex, if not, add 1 to it
            if(keyframeIndex>=modelPath.length-1){
                keyframeIndex=1;
            }else{
                keyframeIndex++;
            }
        }
    }

    public boolean rayCollision(Ray ray){
        return collision.rayCollision(ray, initObject.getVertices());
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

    public Collision getCollision(){
        return collision;
    }

    public boolean getRotation(){
        return rotation;
    }
}
