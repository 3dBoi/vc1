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
    Mesh mesh;

    public ModelLoader(String path){

        Path objFile = Paths.get("./resources/"+path);

        try{
            objLoader.setLoadNormals(true);
            objLoader.setLoadTextureCoordinates(true);
            mesh = objLoader.loadMesh(Resource.file(objFile));
            verticies = mesh.getVertices();
          //  System.out.println(Arrays.toString(verticies));

        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public float[] getVerticies(){
        return verticies;
    }

}
