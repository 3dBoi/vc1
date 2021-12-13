import com.jogamp.opengl.math.Quaternion;

public class AnimationHandler {

    boolean animationTrigger = false;
    float tweenF = 0.0f;
    boolean direction = true;

    public AnimationHandler(){

    }

    // Tween gets increased until it reaches 1 then it goes back to 0
    public float animate(){

        if(tweenF<1&&direction){
            tweenF=tweenF+0.3f;
        }else if(tweenF>=1||!direction) {
            direction = false;
            tweenF=tweenF-0.3f;
        }

        return tweenF;
    }

    // builds rotationquaternions from Vertexquaternion and interpolates for every vertex
    public float[] rotate(String modelPath, String keyFramePath, float[] origin, float tween){

        ModelLoader modelLoader = new ModelLoader();
        float[] verticies1 = modelLoader.getHitboxVerticies(modelPath);
        float[] verticies2 = modelLoader.getHitboxVerticies(keyFramePath);

        float[] verticiesInterpolated = new float[verticies1.length];
        float[] normals = new float[verticiesInterpolated.length];
        float[] texCoordinates = modelLoader.getTexCoordinates(modelPath);


        // Move Object Origin to Worldorigin
        for(int i = 0; i<verticies1.length; i++){

            verticies1[i] = verticies1[i]-origin[0];
            verticies2[i] = verticies1[i]-origin[0];
            i++;
            verticies1[i] = verticies1[i]-origin[1];
            verticies2[i] = verticies1[i]-origin[1];
            i++;
            verticies1[i] = verticies1[i]-origin[2];
            verticies2[i] = verticies1[i]-origin[2];
        }



        // builds quaternions from vertexquaternions
        for(int i = 0; i<verticies1.length; i++){

            Quaternion quatA = new Quaternion(verticies1[i], verticies1[i+1], verticies1[i+2], 0);
            Quaternion quatB = new Quaternion(verticies2[i], verticies2[i+1], verticies2[i+2], 0);

            Quaternion quat = new Quaternion().setSlerp(quatA, quatB, tween);

            // interpolates rotation
            verticiesInterpolated[i] = quat.getX()+origin[0];
            verticiesInterpolated[i+1] = quat.getY()+origin[1];
            verticiesInterpolated[i+2] = quat.getZ()+origin[2];

            i=i+2;
        }

        normals = calcNormals(verticiesInterpolated);

        return combineVerticies(verticiesInterpolated, texCoordinates, normals);

    }

    // calc new normals for interpolated Vertex
    public float[] calcNormals(float[] verticies){

        float[] normals = new float[verticies.length];

        float[] a = new float[3];
        float[] b = new float[3];
        float[] c = new float[3];

        float[] vAB = new float[3];
        float[] vCA = new float[3];

        float[] dir = new float[3];
        float vectorLength;

        int counter = 0;

        // facevectors
        for(int i = 0; i<verticies.length; i++){

            a[0] = verticies[i];
            i++;
            a[1] = verticies[i];
            i++;
            a[2] = verticies[i];
            i++;

            b[0] = verticies[i];
            i++;
            b[1] = verticies[i];
            i++;
            b[2] = verticies[i];
            i++;

            c[0] = verticies[i];
            i++;
            c[1] = verticies[i];
            i++;
            c[2] = verticies[i];

            vAB[0] = b[0]-a[0];
            vAB[1] = b[1]-a[1];
            vAB[2] = b[2]-a[2];

            vCA[0] = c[0]-a[0];
            vCA[1] = c[1]-a[1];
            vCA[2] = c[2]-a[2];

            // normal direction and length
            dir = crossProduct(vAB, vCA);
            vectorLength = vectorLength(dir);

            // update normals for every Point
            normals[counter] = dir[0]/vectorLength;
            counter++;
            normals[counter] = dir[1]/vectorLength;
            counter++;
            normals[counter] = dir[2]/vectorLength;
            counter++;

            normals[counter] = dir[0]/vectorLength;
            counter++;
            normals[counter] = dir[1]/vectorLength;
            counter++;
            normals[counter] = dir[2]/vectorLength;
            counter++;

            normals[counter] = dir[0]/vectorLength;
            counter++;
            normals[counter] = dir[1]/vectorLength;
            counter++;
            normals[counter] = dir[2]/vectorLength;
            counter++;
        }

        return normals;
    }

    // combines new verticies with normals and texturecooridnates to make them shadercompatible
    public float[] combineVerticies(float[] a, float[] b, float[] c){

        float[] combined = new float[a.length+b.length+c.length];

        //Counter for Index of Meshvertices
        int counter1 = 0;
        //Counter for Index of Texturecoordinates
        int counter2 = 0;
        //Counter for Index of Normals
        int counter3 = 0;

        for(int i=0; i<combined.length; i++){

            // Add Pointcoords to array
            combined[i] = a[counter1];
            i++;
            counter1++;
            combined[i] = a[counter1];
            i++;
            counter1++;
            combined[i] = a[counter1];
            i++;
            counter1++;

            // Add TexCoords to array
            combined[i] = b[counter2];
            i++;
            counter2++;
            combined[i] = b[counter2];
            i++;
            counter2++;

            // Add Normals to array
            combined[i] = c[counter3];
            i++;
            counter3++;
            combined[i] = c[counter3];
            i++;
            counter3++;
            combined[i] = c[counter3];
            counter3++;

        }

        return combined;
    }

    public void setAnimationTrigger(boolean animationTrigger) {
        this.animationTrigger = animationTrigger;
    }

    public void setDirection(boolean direction) {
        this.direction = direction;
    }

    public boolean getAnimationTrigger() {
        return animationTrigger;
    }

    public boolean getDirection() {
        return direction;
    }

    public void setTweenF(float tweenF) {
        this.tweenF = tweenF;
    }

    public float dotProduct(float[] a, float[] b){

        return a[0]*b[0]+a[1]*b[1]+a[2]*b[2];
    }

    public float[] crossProduct(float[] a, float[] b){

        float[] cross = new float[3];
        cross[0] = a[1]*b[2]-a[2]*b[1];
        cross[1] = a[2]*b[0]-a[0]*b[2];
        cross[2] = a[0]*b[1]-a[1]*b[0];
        return cross;

    }

    public float vectorLength(float[] v){

        return (float) Math.sqrt(Math.pow(v[0],2)+Math.pow(v[1],2)+Math.pow(v[2],2));
    }
}
