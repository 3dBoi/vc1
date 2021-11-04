import de.hshl.obj.loader.OBJLoader;
import de.hshl.obj.loader.Resource;
import de.hshl.obj.loader.objects.Mesh;
import de.hshl.obj.loader.objects.Surface;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import com.jogamp.opengl.*;

public class ModelLoader {

    OBJLoader objLoader = new OBJLoader();

    // contains the geometry of our OBJ file
    float[] verticies;
    float[] verticiesKeyframe;
    float[] combinedVertices;
    Mesh mesh;
    Mesh meshKeyframe;

    public ModelLoader(String path){

        Path objFile = Paths.get("./resources/"+path);

        try{
            objLoader.setLoadNormals(true);
            objLoader.setLoadTextureCoordinates(true);
            mesh = objLoader.loadMesh(Resource.file(objFile));
            verticies = mesh.getVertices();

        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ModelLoader(String path, String keyframe){

        Path objFile = Paths.get("./resources/"+path);
        Path keyframeObjFile = Paths.get("./resources/"+keyframe);

        try{
            objLoader.setLoadNormals(true);

            //Load Vertices of Keyframe
            meshKeyframe = objLoader.loadMesh(Resource.file(keyframeObjFile));
            verticiesKeyframe = meshKeyframe.getVertices();

            objLoader.setLoadTextureCoordinates(true);

            //Load Vertices of Mesh
            mesh = objLoader.loadMesh(Resource.file(objFile));
            verticies = mesh.getVertices();

            //  System.out.println(Arrays.toString(verticies));

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

    }

    public float[] getVerticies(){
        return verticies;
    }

    public float[] getCombinedVerticies(){
        return combinedVertices;
    }

}
