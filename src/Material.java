import de.hshl.obj.loader.MTLLoader;
import de.hshl.obj.loader.Resource;
import de.hshl.obj.loader.materials.RGB;
import de.hshl.obj.wavefront.mtl.MTL;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * Class holding material parameters for surfaces
 *  @author Karsten Lehn
 *  @version 23.10.2017
 */
public class Material {
    private float[] emission;
    private float[] ambient;
    private float[] diffuse;
    private float[] specular;
    private float shininess;

    public Material() {
    }

    public Material(float[] emission, float[] ambient, float[] diffuse, float[] specular, float shininess) {
        this.emission = emission;
        this.ambient = ambient;
        this.diffuse = diffuse;
        this.specular = specular;
        this.shininess = shininess;
    }

    public float[] getEmission() {
        return emission;
    }

    public void setEmission(float[] emission) {
        this.emission = emission;
    }

    public float[] getAmbient() {
        return ambient;
    }

    public void setAmbient(float[] ambient) {
        this.ambient = ambient;
    }

    public float[] getDiffuse() {
        return diffuse;
    }

    public void setDiffuse(float[] diffuse) {
        this.diffuse = diffuse;
    }

    public float[] getSpecular() {
        return specular;
    }

    public void setSpecular(float[] specular) {
        this.specular = specular;
    }

    public float getShininess() {
        return shininess;
    }

    public void setShininess(float shininess) {
        this.shininess = shininess;
    }

    //Load Material from mtl file
    public void loadMaterial(String path, float shininess){

        Path mtlFile = Paths.get("./resources/"+path);

        try{
            MTLLoader mtlLoader = new MTLLoader();
            MTL mtl = mtlLoader.load(Resource.file(mtlFile));
            List<de.hshl.obj.loader.materials.Material> material = mtl.getMaterials();

            RGB emissionRGB = material.get(0).getEmissionColor();
            this.emission = emissionRGB.toFloatArray();

            RGB ambientRGB = material.get(0).getAmbientColor();
            this.ambient = ambientRGB.toFloatArray();

            RGB diffuseRGB = material.get(0).getDiffuseColor();
            this.diffuse = diffuseRGB.toFloatArray();

            RGB specularRGB = material.get(0).getSpecularColor();
            this.specular = specularRGB.toFloatArray();

            this.shininess = shininess;

            System.out.println(Arrays.toString(emission));
            System.out.println(Arrays.toString(ambient));
            System.out.println(Arrays.toString(diffuse));
            System.out.println(Arrays.toString(specular));
            System.out.println(shininess);

        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
