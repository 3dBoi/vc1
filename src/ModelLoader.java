import de.hshl.obj.loader.OBJLoader;
import de.hshl.obj.loader.Resource;
import de.hshl.obj.loader.objects.Mesh;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ModelLoader {

    OBJLoader objLoader = new OBJLoader();

    // contains the geometry of our OBJ file
    float[] verticies;
    float[] verticiesKeyframe;
    float[] combinedVertices;
    Mesh mesh;
    Mesh meshKeyframe;

    public ModelLoader(){
    }

    /**
     * load verticies of object from obj file
     */
    public float[] getVerticies(String path){
        Path objFile = Paths.get("resources/" +path);

        try{
            objLoader.setLoadNormals(true);
            objLoader.setLoadTextureCoordinates(true);
            mesh = objLoader.loadMesh(Resource.file(objFile));
            verticies = mesh.getVertices();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        return verticies;
    }

    public float[] getCombinedVerticies(String path, String keyframe){

        Path objFile = Paths.get("resources/" +path);
        Path keyframeObjFile = Paths.get("resources/" +keyframe);

        try{
            objLoader.setLoadNormals(true);

            //Load Vertices of Keyframe
            meshKeyframe = objLoader.loadMesh(Resource.file(keyframeObjFile));
            verticiesKeyframe = meshKeyframe.getVertices();
            objLoader.setLoadTextureCoordinates(true);
            //Load Vertices of Mesh
            mesh = objLoader.loadMesh(Resource.file(objFile));
            verticies = mesh.getVertices();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Combine VertexArrays into one
        this.combinedVertices = new float[verticies.length+verticiesKeyframe.length];
        //Counter for Index of Meshvertices
        int counter1 = 0;
        //Counter for Index of Keyframevertices
        int counter2 = 0;
        //Counter for Index of Combined Array
        int c = 0;

        //Temporary Array to sort in right order
        float[] tempVertecies = new float[14];
        for(int i=0; i<verticies.length/8; i++){
            for(int j=0; j<14;j++){
                if(j<8) {
                    //puts vertices of Mesh Array
                    tempVertecies[j] = verticies[counter1];
                    counter1++;
                }else{
                    //puts vertices of Keyframe Array
                    tempVertecies[j] = verticiesKeyframe[counter2];
                    counter2++;
                }
                //Copy Temporary Array into final Array
                combinedVertices[c] = tempVertecies[j];
                c++;
            }
        }
        return combinedVertices;
    }

    public float[] getHitboxVerticies(String path, String keyframe){

        Path objFile = Paths.get("resources/" +path);
        Path keyframeObjFile = Paths.get("resources/" +keyframe);

        try{
            //Load Vertices of Keyframe
            meshKeyframe = objLoader.loadMesh(Resource.file(keyframeObjFile));
            verticiesKeyframe = meshKeyframe.getVertices();

            //Load Vertices of Mesh
            mesh = objLoader.loadMesh(Resource.file(objFile));
            verticies = mesh.getVertices();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Combine VertexArrays into one
        this.combinedVertices = new float[verticies.length+verticiesKeyframe.length];
        //Counter for Index of Meshvertices
        int counter1 = 0;
        //Counter for Index of Keyframevertices
        int counter2 = 0;
        //Counter for Index of Combined Array
        int c = 0;

        //Temporary Array to sort in right order
        float[] tempVertecies = new float[14];

        for(int i=0; i<verticies.length/8; i++){
            for(int j=0; j<14;j++){
                if(j<8) {
                    //puts vertices of Mesh Array
                    tempVertecies[j] = verticies[counter1];
                    counter1++;
                }else{
                    //puts vertices of Keyframe Array
                    tempVertecies[j] = verticiesKeyframe[counter2];
                    counter2++;
                }
                //Copy Temporary Array into final Array
                combinedVertices[c] = tempVertecies[j];
                c++;
            }
        }
        return combinedVertices;
    }

    /**
     * Load Vertices of Hitbox from OBJ File
     */
    public float[] getHitboxVerticies(String path){

        Path objFile = Paths.get("resources/" +path);

        try{
            mesh = objLoader.loadMesh(Resource.file(objFile));
            verticies = mesh.getVertices();

        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        return verticies;
    }

    /**
     * loads only texture coordinates in an array
     */
    public float[] getTexCoordinates(String path){

        Path objFile = Paths.get("resources/" +path);

        try{
            objLoader.setLoadTextureCoordinates(true);

            mesh = objLoader.loadMesh(Resource.file(objFile));
            verticies = mesh.getVertices();

        }catch (IOException e) {
            throw new RuntimeException(e);
        }

        float[] texCoord = new float[(verticies.length/5)*2];
        int j = 0;

        for(int i = 3; i<verticies.length; i=i+3){

            texCoord[j]=verticies[i];
            j++;
            i++;
            texCoord[j]=verticies[i];
            j++;
            i++;
        }
        return texCoord;
    }
}


