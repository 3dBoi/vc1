import com.jogamp.opengl.math.Ray;

public class Collision {

    public Collision(){

    }

    public boolean rayCollision(Ray ray, float[] verticies){

        // hinreichende Bedingung : Schnittpunkt Ebene Face und Ray
        // notwendige Bedingung: Schnittpunkt auf Face (in Boundingbox)

        int counter = 0;
        float x1, x2, x3, y1, y2, y3, z1, z2, z3;

        // Ebene
        x1 = verticies[counter];
        counter++;
        x2 = verticies[counter];
        counter++;
        x3 = verticies[counter];
        counter++;

        y1 = verticies[counter];
        counter++;
        y2 = verticies[counter];
        counter++;
        y3 = verticies[counter];
        counter++;

        z1 = verticies[counter];
        counter++;
        z2 = verticies[counter];
        counter++;
        z3 = verticies[counter];
        counter++;


        // Vektoren zwischen Punkten

        // Kreuzprodukt


        return false;
    }
}
