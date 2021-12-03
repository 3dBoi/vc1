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

//        //Big Drum
//        objectArr[0]= new InitObject();
//        objectArr[0].initObject(gl, "Objs/drumsetV4_BigDrumBack.obj", "textures/drumsetV4_BigDrumBack_BaseColor.png", "Objs/drumsetV4_BigDrumBack.mtl", "BlinnPhongPointTex.vert", "BlinnPhongPointTex.frag", vaoName, vboName, 0);
//
//        objectArr[1]= new InitObject();
//        objectArr[1].initObject(gl, "Objs/drumsetV4_BigDrumBody.obj", "textures/drumsetV4_BigDrumBody_BaseColor.png", "Objs/drumsetV4_BigDrumBody.mtl", "BlinnPhongPointTex.vert", "BlinnPhongPointTex.frag", vaoName, vboName, 1);
//
//        objectArr[2]= new InitObject();
//        objectArr[2].initObject(gl, "Objs/drumsetV4_BigDrumRing.obj", "textures/drumsetV4_BigDrumRing_BaseColor.png", "Objs/drumsetV4_BigDrumRing.mtl", "BlinnPhongPointTex.vert", "BlinnPhongPointTex.frag", vaoName, vboName, 2);
//
//        objectArr[3]= new InitObject();
//        objectArr[3].initObject(gl, "Objs/drumsetV4_BigDrumFront.obj", "textures/drumsetV4_BigDrumFront_BaseColor.png", "Objs/drumsetV4_BigDrumFront.mtl", "BlinnPhongPointTex.vert", "BlinnPhongPointTex.frag", vaoName, vboName, 3);
//
//        objectArr[4]= new InitObject();
//        objectArr[4].initObject(gl, "Objs/drumsetV4_Detail.obj", "textures/drumsetV4_Detail_BaseColor.png", "Objs/drumsetV4_Detail.mtl", "BlinnPhongPointTex.vert", "BlinnPhongPointTex.frag", vaoName, vboName, 4);
//
//
//        //MediumDrum
//        objectArr[5]= new InitObject();
//        objectArr[5].initObject(gl, "Objs/drumsetV4_DrumTop.obj", "textures/drumsetV4_DrumTop_BaseColor.png", "Objs/drumsetV4_DrumTop.mtl", "BlinnPhongPointTex.vert", "BlinnPhongPointTex.frag", vaoName, vboName, 5);
//
//        objectArr[6]= new InitObject();
//        objectArr[6].initObject(gl, "Objs/drumsetV4_MidDrumBody.obj", "textures/drumsetV4_MidDrumBody_BaseColor.png", "Objs/drumsetV4_MidDrumBody.mtl", "BlinnPhongPointTex.vert", "BlinnPhongPointTex.frag", vaoName, vboName, 6);
//
//        objectArr[7]= new InitObject();
//        objectArr[7].initObject(gl, "Objs/drumsetV4_DrumRings.obj", "textures/drumsetV4_DrumRings_BaseColor.png", "Objs/drumsetV4_DrumRings.mtl", "BlinnPhongPointTex.vert", "BlinnPhongPointTex.frag", vaoName, vboName, 7);
//
//        objectArr[8]= new InitObject();
//        objectArr[8].initObject(gl, "Objs/drumsetV4_Detail003.obj", "textures/drumsetV4_Detail_BaseColor.png", "Objs/drumsetV4_Detail003.mtl", "BlinnPhongPointTex.vert", "BlinnPhongPointTex.frag", vaoName, vboName, 8);
//
//
//        //Stands
//        objectArr[9]= new InitObject();
//        objectArr[9].initObject(gl, "Objs/drumsetV4_OtherStands.obj", "textures/drumsetV4_OtherStands_BaseColor.png", "Objs/drumsetV4_OtherStands.mtl", "BlinnPhongPointTex.vert", "BlinnPhongPointTex.frag", vaoName, vboName, 9);
//        //Feet
//        objectArr[10]= new InitObject();
//        objectArr[10].initObject(gl, "Objs/drumsetV4_Feet.obj", "textures/drumsetV4_Feet_BaseColor.png", "Objs/drumsetV4_Feet.mtl", "BlinnPhongPointTex.vert", "BlinnPhongPointTex.frag", vaoName, vboName, 10);
//        //Bonker
//        objectArr[11]= new InitObject();
//        objectArr[11].initObject(gl, "Objs/drumsetV4_OtherStands001.obj", "textures/drumsetV4_OtherStands_BaseColor.png", "Objs/drumsetV4_OtherStands.mtl", "BlinnPhongPointTex.vert", "BlinnPhongPointTex.frag", vaoName, vboName, 11);
//
//        //RideSymabal
//        objectArr[12]= new InitObject();
//        objectArr[12].initObject(gl, "Objs/drumsetV4_RideSymbal.obj", "textures/drumsetV4_RideSymbal_BaseColor.png", "Objs/drumsetV4_RideSymbal.mtl", "BlinnPhongPointTex.vert", "BlinnPhongPointTex.frag", vaoName, vboName, 12);
//
//        objectArr[13]= new InitObject();
//        objectArr[13].initObject(gl, "Objs/drumsetV4_RideSymbalRest.obj", "textures/drumsetV4_RideSymbalRest_BaseColor.png", "Objs/drumsetV4_RideSymbalRest.mtl", "BlinnPhongPointTex.vert", "BlinnPhongPointTex.frag", vaoName, vboName, 13);
//
//        //SmollDrumm
//        //-->test für animation
//        objectArr[14]= new InitObject();
//        objectArr[14].initObject(gl, "Objs/drumsetV4_SmaolDrumBody.obj", "textures/drumsetV4_SmaolDrumBody_BaseColor.png", "Objs/drumsetV4_SmaolDrumBody.mtl", "BlinnPhongPointTex.vert", "BlinnPhongPointTex.frag", vaoName, vboName, 14);
//
//        objectArr[15]= new InitObject();
//        objectArr[15].initObject(gl, "Objs/drumsetV4_DrumRings001.obj", "textures/drumsetV4_DrumRings_BaseColor.png", "Objs/drumsetV4_DrumRings001.mtl", "BlinnPhongPointTex.vert", "BlinnPhongPointTex.frag", vaoName, vboName, 15);
//
//        objectArr[16]= new InitObject();
//        objectArr[16].initObject(gl, "Objs/drumsetV4_DrumTop002.obj", "textures/drumsetV4_DrumTop_BaseColor.png", "Objs/drumsetV4_DrumTop002.mtl", "BlinnPhongPointTex.vert", "BlinnPhongPointTex.frag", vaoName, vboName, 16);
//
//        objectArr[17]= new InitObject();
//        objectArr[17].initObject(gl, "Objs/drumsetV4_Detail002.obj", "textures/drumsetV4_Detail_BaseColor.png", "Objs/drumsetV4_Detail002.mtl", "BlinnPhongPointTex.vert", "BlinnPhongPointTex.frag", vaoName, vboName, 17);
//
//
//        //Small Drumm
//        objectArr[18]= new InitObject();
//        objectArr[18].initObject(gl, "Objs/drumsetV4_SmaolDrumBody001.obj", "textures/drumsetV4_SmaolDrumBody_BaseColor.png", "Objs/drumsetV4_SmaolDrumBody001.mtl", "BlinnPhongPointTex.vert", "BlinnPhongPointTex.frag", vaoName, vboName, 18);
//
//        objectArr[19]= new InitObject();
//        objectArr[19].initObject(gl, "Objs/drumsetV4_DrumTop001.obj", "textures/drumsetV4_DrumTop_BaseColor.png", "Objs/drumsetV4_DrumTop001.mtl", "BlinnPhongPointTex.vert", "BlinnPhongPointTex.frag", vaoName, vboName, 19);
//
//        objectArr[20]= new InitObject();
//        objectArr[20].initObject(gl, "Objs/drumsetV4_DrumRings002.obj", "textures/drumsetV4_DrumRings_BaseColor.png", "Objs/drumsetV4_DrumRings002.mtl", "BlinnPhongPointTex.vert", "BlinnPhongPointTex.frag", vaoName, vboName, 20);
//
//        objectArr[21]= new InitObject();
//        objectArr[21].initObject(gl, "Objs/drumsetV4_Detail001.obj", "textures/drumsetV4_Detail_BaseColor.png", "Objs/drumsetV4_Detail001.mtl", "BlinnPhongPointTex.vert", "BlinnPhongPointTex.frag", vaoName, vboName, 21);
//
//
//        //Hi Hat
//        objectArr[22]= new InitObject();
//        objectArr[22].initObject(gl, "Objs/drumsetV4_Hi-Hat.obj", "textures/drumsetV4_Hi-Hat_BaseColor.png", "Objs/drumsetV4_Hi-Hat.mtl", "BlinnPhongPointTex.vert", "BlinnPhongPointTex.frag", vaoName, vboName, 22);
//
//        objectArr[23]= new InitObject();
//        objectArr[23].initObject(gl, "Objs/drumsetV4_Hi-Hat001.obj", "textures/drumsetV4_Hi-Hat_BaseColor.png", "Objs/drumsetV4_Hi-Hat001.mtl", "BlinnPhongPointTex.vert", "BlinnPhongPointTex.frag", vaoName, vboName, 23);
//
//        objectArr[24]= new InitObject();
//        objectArr[24].initObject(gl, "Objs/drumsetV4_Hi-HatRest.obj", "textures/drumsetV4_Hi-HatRest_BaseColor.png", "Objs/drumsetV4_Hi-HatRest.mtl", "BlinnPhongPointTex.vert", "BlinnPhongPointTex.frag", vaoName, vboName, 24);
//
//
//        //Crash Symbal
//        objectArr[25]= new InitObject();
//        objectArr[25].initObject(gl, "Objs/drumsetV4_CrashSymbal.obj", "textures/drumsetV4_CrashSymbal_BaseColor.png", "Objs/drumsetV4_CrashSymbal.mtl", "BlinnPhongPointTex.vert", "BlinnPhongPointTex.frag", vaoName, vboName, 25);
//
//        objectArr[26]= new InitObject();
//        objectArr[26].initObject(gl, "Objs/drumsetV4_CrashSymbalRest.obj", "textures/drumsetV4_CrashSymbalRest_BaseColor.png", "Objs/drumsetV4_CrashSymbalRest.mtl", "BlinnPhongPointTex.vert", "BlinnPhongPointTex.frag", vaoName, vboName, 26);
//
//        this.modelPath = new String[]{"TestCube.obj", "TestCubeKeyframe1.obj", "TestCubeKeyframe2.obj"};
//        entities[0] = new Entity(gl, modelPath, "HSHLLogo1.jpg", "TestCube.mtl", vaoName, vboName, 1, pmvMatrix, light);
//        entities[0].initEntity();
//        i++;
//
//
//        // Hitboxloader
//        this.modelPath = new String[]{"TestCube.obj"};
//        entities[1] = new Entity(gl, modelPath, vaoName, vboName, 2);
//        entities[1].initEntity();
//        i++;

//        // Cube Left
//        this.modelPath = new String[]{"CubeLeft.obj", "CubeLeftKeyframe1.obj"};
//        entities[0] = new Entity(gl, modelPath, "HSHLLogo1.jpg", "CubeLeft.mtl", vaoName, vboName, 1, pmvMatrix, light);
//        entities[0].initEntity();
//        i++;
//
//        // Hitboxloader Cube Left
//        this.modelPath = new String[]{"CubeLeft.obj"};
//        entities[1] = new Entity(gl, modelPath, vaoName, vboName, 2);
//        entities[1].initEntity();
//        i++;
//
//        // Cube Right
//        this.modelPath = new String[]{"CubeRight.obj", "CubeRightKeyframe1.obj"};
//        entities[2] = new Entity(gl, modelPath, "HSHLLogo2.jpg", "CubeRight.mtl", vaoName, vboName, 3, pmvMatrix, light);
//        entities[2].initEntity();
//        i++;
//
//        // Hitboxloader Cube Right
//        this.modelPath = new String[]{"CubeRight.obj"};
//        entities[3] = new Entity(gl, modelPath, vaoName, vboName, 4);
//        entities[3].initEntity();
//        i++;
//
//        // Cube Center
//        this.modelPath = new String[]{"CubeCenter.obj", "CubeCenterKeyframe1.obj"};
//        entities[4] = new Entity(gl, modelPath, "GelbGruenPalette.png", "CubeCenter.mtl", vaoName, vboName, 5, pmvMatrix, light);
//        entities[4].initEntity();
//        i++;
//
//        // Hitboxloader Cube Center
//        this.modelPath = new String[]{"CubeCenter.obj"};
//        entities[5] = new Entity(gl, modelPath, vaoName, vboName, 6);
//        entities[5].initEntity();
//        i++;
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