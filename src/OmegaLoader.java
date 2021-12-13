import com.jogamp.opengl.GL3;
import com.jogamp.opengl.util.PMVMatrix;

public class OmegaLoader {

    Entity[] entities;
    String[] modelPath;

    public OmegaLoader(){

    }

    public void omegaInit(GL3 gl, Entity[] entities, int [] vaoName, int[] vboName, PMVMatrix pmvMatrix, LightSource light){

        this.entities = entities;
        int i=0;



        // Animated Objects

        // BigDrum Model
        this.modelPath = new String[]{"Objs/drumsetV4_BigDrumBack.obj", "Objs/drumsetV4_BigDrumBack_Keyframe.obj"};
        entities[i] = new Entity(gl, modelPath, "textures/drumsetV4_BigDrumBack_BaseColor.png", "Objs/drumsetV4_BigDrumBack.mtl", vaoName, vboName, i, pmvMatrix, light);
        entities[i].initEntity();
        i++;

        this.modelPath = new String[]{"Objs/drumsetV4_BigDrumBody.obj", "Objs/drumsetV4_BigDrumBody_Keyframe.obj"};
        entities[i] = new Entity(gl, modelPath, "textures/drumsetV4_BigDrumBody_BaseColor.png", "Objs/drumsetV4_BigDrumBody.mtl", vaoName, vboName, i, pmvMatrix, light);
        entities[i].initEntity();
        i++;

        this.modelPath = new String[]{"Objs/drumsetV4_BigDrumFront.obj", "Objs/drumsetV4_BigDrumFront_Keyframe.obj"};
        entities[i] = new Entity(gl, modelPath, "textures/drumsetV4_BigDrumFront_BaseColor.png", "Objs/drumsetV4_BigDrumFront.mtl", vaoName, vboName, i, pmvMatrix, light);
        entities[i].initEntity();
        i++;

        this.modelPath = new String[]{"Objs/drumsetV4_BigDrumRing.obj", "Objs/drumsetV4_BigDrumRing_Keyframe.obj"};
        entities[i] = new Entity(gl, modelPath, "textures/drumsetV4_BigDrumRing_BaseColor.png", "Objs/drumsetV4_BigDrumRing.mtl", vaoName, vboName, i, pmvMatrix, light);
        entities[i].initEntity();
        i++;

        this.modelPath = new String[]{"Objs/drumsetV4_Detail.obj", "Objs/drumsetV4_Detail_Keyframe.obj"};
        entities[i] = new Entity(gl, modelPath, "textures/drumsetV4_Detail_BaseColor.png", "Objs/drumsetV4_Detail.mtl", vaoName, vboName, i, pmvMatrix, light);
        entities[i].initEntity();
        i++;

        this.modelPath = new String[]{"Objs/drumsetV4_OtherStands001.obj", "Objs/drumsetV4_OtherStands001_Keyframe.obj"};
        entities[i] = new Entity(gl, modelPath, "textures/drumsetV4_OtherStands_BaseColor.png", "Objs/drumsetV4_OtherStands.mtl", vaoName, vboName, i, pmvMatrix, light);
        entities[i].initEntity();
        i++;

        // BigDrum Hitbox
        this.modelPath = new String[]{"Objs/drumsetV4_BigDrum_Hitbox.obj"};
        entities[i] = new Entity(gl, modelPath, vaoName, vboName, i);
        entities[i].initEntity();
        i++;



        // CrashSymbal Model
        this.modelPath = new String[]{"Objs/drumsetV4_CrashSymbal.obj", "Objs/drumsetV4_CrashSymbal_Keyframe.obj"};
        entities[i] = new Entity(gl, modelPath, "textures/drumsetV4_CrashSymbal_BaseColor.png", "Objs/drumsetV4_CrashSymbal.mtl", vaoName, vboName, i, pmvMatrix, light);
        entities[i].initEntity();
        i++;

        //CrashSymbal Hitbox
        this.modelPath = new String[]{"Objs/drumsetV4_CrashSymbal_Hitbox.obj"};
        entities[i] = new Entity(gl, modelPath, vaoName, vboName, i);
        entities[i].initEntity();
        i++;



        // SmallDrum Model
        this.modelPath = new String[]{"Objs/drumsetV4_Detail001.obj", "Objs/drumsetV4_Detail001_Keyframe.obj"};
        entities[i] = new Entity(gl, modelPath, "textures/drumsetV4_Detail_BaseColor.png", "Objs/drumsetV4_Detail001.mtl", vaoName, vboName, i, pmvMatrix, light);
        entities[i].initEntity();
        i++;

        this.modelPath = new String[]{"Objs/drumsetV4_DrumRings002.obj", "Objs/drumsetV4_DrumRings002_Keyframe.obj"};
        entities[i] = new Entity(gl, modelPath, "textures/drumsetV4_DrumRings_BaseColor.png", "Objs/drumsetV4_DrumRings002.mtl", vaoName, vboName, i, pmvMatrix, light);
        entities[i].initEntity();
        i++;

        this.modelPath = new String[]{"Objs/drumsetV4_DrumTop001.obj", "Objs/drumsetV4_DrumTop001_Keyframe.obj"};
        entities[i] = new Entity(gl, modelPath, "textures/drumsetV4_DrumTop_BaseColor.png", "Objs/drumsetV4_DrumTop001.mtl", vaoName, vboName, i, pmvMatrix, light);
        entities[i].initEntity();
        i++;

        this.modelPath = new String[]{"Objs/drumsetV4_SmaolDrumBody001.obj", "Objs/drumsetV4_SmaolDrumBody001_Keyframe.obj"};
        entities[i] = new Entity(gl, modelPath, "textures/drumsetV4_SmaolDrumBody_BaseColor.png", "Objs/drumsetV4_SmaolDrumBody001.mtl", vaoName, vboName, i, pmvMatrix, light);
        entities[i].initEntity();
        i++;

        // SmallDrum Hitbox
        this.modelPath = new String[]{"Objs/drumsetV4_SmaolDrum001_Hitbox.obj"};
        entities[i] = new Entity(gl, modelPath, vaoName, vboName, i);
        entities[i].initEntity();
        i++;



        // SmollDrum Model
        this.modelPath = new String[]{"Objs/drumsetV4_Detail002.obj", "Objs/drumsetV4_Detail002_Keyframe.obj"};
        entities[i] = new Entity(gl, modelPath, "textures/drumsetV4_Detail_BaseColor.png", "Objs/drumsetV4_Detail002.mtl", vaoName, vboName, i, pmvMatrix, light);
        entities[i].initEntity();
        i++;

        this.modelPath = new String[]{"Objs/drumsetV4_DrumRings001.obj", "Objs/drumsetV4_DrumRings001_Keyframe.obj"};
        entities[i] = new Entity(gl, modelPath, "textures/drumsetV4_DrumRings_BaseColor.png", "Objs/drumsetV4_DrumRings001.mtl", vaoName, vboName, i, pmvMatrix, light);
        entities[i].initEntity();
        i++;

        this.modelPath = new String[]{"Objs/drumsetV4_DrumTop002.obj", "Objs/drumsetV4_DrumTop002_Keyframe.obj"};
        entities[i] = new Entity(gl, modelPath, "textures/drumsetV4_DrumTop_BaseColor.png", "Objs/drumsetV4_DrumTop002.mtl", vaoName, vboName, i, pmvMatrix, light);
        entities[i].initEntity();
        i++;

        this.modelPath = new String[]{"Objs/drumsetV4_SmaolDrumBody.obj", "Objs/drumsetV4_SmaolDrumBody_Keyframe.obj"};
        entities[i] = new Entity(gl, modelPath, "textures/drumsetV4_SmaolDrumBody_BaseColor.png", "Objs/drumsetV4_SmaolDrumBody.mtl", vaoName, vboName, i, pmvMatrix, light);
        entities[i].initEntity();
        i++;

        // SmollDrum Hitbox
        this.modelPath = new String[]{"Objs/drumsetV4_SmaolDrum_Hitbox.obj"};
        entities[i] = new Entity(gl, modelPath, vaoName, vboName, i);
        entities[i].initEntity();
        i++;



        // MidDrum Model
        this.modelPath = new String[]{"Objs/drumsetV4_Detail003.obj", "Objs/drumsetV4_Detail003_Keyframe.obj"};
        entities[i] = new Entity(gl, modelPath, "textures/drumsetV4_Detail_BaseColor.png", "Objs/drumsetV4_Detail003.mtl", vaoName, vboName, i, pmvMatrix, light);
        entities[i].initEntity();
        i++;

        this.modelPath = new String[]{"Objs/drumsetV4_DrumRings.obj", "Objs/drumsetV4_DrumRings_Keyframe.obj"};
        entities[i] = new Entity(gl, modelPath, "textures/drumsetV4_DrumRings_BaseColor.png", "Objs/drumsetV4_DrumRings.mtl", vaoName, vboName, i, pmvMatrix, light);
        entities[i].initEntity();
        i++;

        this.modelPath = new String[]{"Objs/drumsetV4_DrumTop.obj", "Objs/drumsetV4_DrumTop_Keyframe.obj"};
        entities[i] = new Entity(gl, modelPath, "textures/drumsetV4_DrumTop_BaseColor.png", "Objs/drumsetV4_DrumTop.mtl", vaoName, vboName, i, pmvMatrix, light);
        entities[i].initEntity();
        i++;

        this.modelPath = new String[]{"Objs/drumsetV4_MidDrumBody.obj", "Objs/drumsetV4_MidDrumBody_Keyframe.obj"};
        entities[i] = new Entity(gl, modelPath, "textures/drumsetV4_MidDrumBody_BaseColor.png", "Objs/drumsetV4_MidDrumBody.mtl", vaoName, vboName, i, pmvMatrix, light);
        entities[i].initEntity();
        i++;

        // MidDrum Hitbox
        this.modelPath = new String[]{"Objs/drumsetV4_MidDrum_Hitbox.obj"};
        entities[i] = new Entity(gl, modelPath, vaoName, vboName, i);
        entities[i].initEntity();
        i++;



        // RideSymbal Model
        this.modelPath = new String[]{"Objs/drumsetV4_RideSymbal.obj", "Objs/drumsetV4_RideSymbal_Keyframe.obj"};
        entities[i] = new Entity(gl, modelPath, "textures/drumsetV4_RideSymbal_BaseColor.png", "Objs/drumsetV4_RideSymbal.mtl", vaoName, vboName, i, pmvMatrix, light);
        entities[i].initEntity();
        i++;

        // RideSymbal Hitbox
        this.modelPath = new String[]{"Objs/drumsetV4_RideSymbal_Hitbox.obj"};
        entities[i] = new Entity(gl, modelPath, vaoName, vboName, i);
        entities[i].initEntity();
        i++;



        // Hi-Hat Model
        this.modelPath = new String[]{"Objs/drumsetV4_Hi-Hat.obj", "Objs/drumsetV4_Hi-Hat_Keyframe.obj"};
        entities[i] = new Entity(gl, modelPath, "textures/drumsetV4_Hi-Hat_BaseColor.png", "Objs/drumsetV4_Hi-Hat.mtl", vaoName, vboName, i, pmvMatrix, light);
        entities[i].initEntity();
        i++;

        this.modelPath = new String[]{"Objs/drumsetV4_Hi-Hat001.obj", "Objs/drumsetV4_Hi-Hat001_Keyframe.obj"};
        entities[i] = new Entity(gl, modelPath, "textures/drumsetV4_Hi-Hat_BaseColor.png", "Objs/drumsetV4_Hi-Hat001.mtl", vaoName, vboName, i, pmvMatrix, light);
        entities[i].initEntity();
        i++;

        // Hi-Hat Hitbox
        this.modelPath = new String[]{"Objs/drumsetV4_Hi-Hat_Hitbox.obj"};
        entities[i] = new Entity(gl, modelPath, vaoName, vboName, i);
        entities[i].initEntity();
        i++;



        // Unanimated Objects
        this.modelPath = new String[]{"Objs/drumsetV4_CrashSymbalRest.obj"};
        entities[i] = new Entity(gl, modelPath, "textures/drumsetV4_CrashSymbalRest_BaseColor.png", "Objs/drumsetV4_CrashSymbalRest.mtl", vaoName, vboName, i, pmvMatrix, light);
        entities[i].initEntity();
        i++;


        this.modelPath = new String[]{"Objs/drumsetV4_Feet.obj"};
        entities[i] = new Entity(gl, modelPath, "textures/drumsetV4_Feet_BaseColor.png", "Objs/drumsetV4_Feet.mtl", vaoName, vboName, i, pmvMatrix, light);
        entities[i].initEntity();
        i++;


        this.modelPath = new String[]{"Objs/drumsetV4_Hi-HatRest.obj"};
        entities[i] = new Entity(gl, modelPath, "textures/drumsetV4_Hi-HatRest_BaseColor.png", "Objs/drumsetV4_Hi-HatRest.mtl", vaoName, vboName, i, pmvMatrix, light);
        entities[i].initEntity();
        i++;


        this.modelPath = new String[]{"Objs/drumsetV4_OtherStands.obj"};
        entities[i] = new Entity(gl, modelPath, "textures/drumsetV4_OtherStands_BaseColor.png", "Objs/drumsetV4_OtherStands.mtl", vaoName, vboName, i, pmvMatrix, light);
        entities[i].initEntity();
        i++;


        this.modelPath = new String[]{"Objs/drumsetV4_RideSymbalRest.obj"};
        entities[i] = new Entity(gl, modelPath, "textures/drumsetV4_RideSymbalRest_BaseColor.png", "Objs/drumsetV4_RideSymbalRest.mtl", vaoName, vboName, i, pmvMatrix, light);
        entities[i].initEntity();
        i++;

    }

    public void omegaDisplay(){

        for(int i= 0; i< entities.length; i++){
            if(entities[i].getAnimationHandler().getAnimationTrigger()&&entities[i].getRotation()){
                entities[i].playRotation();
            }else if(entities[i].getAnimationHandler().getAnimationTrigger()) {
                entities[i].playAnimation();
            }else{
                entities[i].displayEntity();
            }
        }
    }
}