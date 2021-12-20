import com.jogamp.opengl.math.Ray;

import java.util.Arrays;

public class Collision {

    float raylength = 99999;
    boolean collision = false;

    public Collision(){

    }

    public boolean rayCollision(Ray ray, float[] verticies){

        // hinreichende Bedingung : Schnittpunkt Ebene Face und Ray
        // notwendige Bedingung: Schnittpunkt auf Face (in Boundingbox)

        float x1, x2, x3, y1, y2, y3, z1, z2, z3;
        float vx12, vy12, vz12, vx13, vy13, vz13;
        float nx, ny, nz;
        float rayx, rayy, rayz, rayvx, rayvy, rayvz;
        float diffx, diffy, diffz;
        float length, raylengthTemp;
        float prod1, prod2, prod3;
        float rayvxtemp, rayvytemp, rayvztemp;
        float sx, sy, sz;

        rayx = ray.orig[0];
        rayy = ray.orig[1];
        rayz = ray.orig[2];

        rayvx = ray.dir[0];
        rayvy = ray.dir[1];
        rayvz = ray.dir[2];

        for(int counter=0; counter< verticies.length; counter++){
            // Ebene
            x1 = verticies[counter];
            counter++;
            y1 = verticies[counter];
            counter++;
            z1 = verticies[counter];
            counter++;

            x2 = verticies[counter];
            counter++;
            y2 = verticies[counter];
            counter++;
            z2 = verticies[counter];
            counter++;

            x3 = verticies[counter];
            counter++;
            y3 = verticies[counter];
            counter++;
            z3 = verticies[counter];

            // Vektoren zwischen Punkten
            vx12 = x2-x1;
            vy12 = y2-y1;
            vz12 = z2-z1;

            vx13 = x3-x1;
            vy13 = y3-y1;
            vz13 = z3-z1;

            // Kreuzprodukt
            float[] cross = crossProduct(vx12, vy12, vz12, vx13, vy13, vz13);
            nx = cross[0];
            ny = cross[1];
            nz = cross[2];

            // Test if ray is parallel to plane
            float dotProduct = nx*rayvx+ny*rayvy+nz*rayvz;

            if(dotProduct!=0){
                // Länge des Normalvektors
                length = (float) Math.sqrt( Math.pow(nx, 2)+Math.pow(ny, 2)+Math.pow(nz, 2));

                // Einheitsform Normalvektor
                nx = nx/length;
                ny = ny/length;
                nz = nz/length;

                // Vektor Stützpunkt Ray und Ebene
                diffx = rayx-x1;
                diffy = rayy-y1;
                diffz = rayz-z1;

                prod1 = diffx*nx + diffy*ny + diffz*nz;

                prod2 = rayvx*nx + rayvy*ny + rayvz*nz;

                prod3 = prod1/prod2;

                rayvxtemp = rayvx*prod3;
                rayvytemp = rayvy*prod3;
                rayvztemp = rayvz*prod3;

                raylengthTemp = (float) Math.sqrt( Math.pow(rayvxtemp, 2)+Math.pow(rayvytemp, 2)+Math.pow(rayvztemp, 2));

                // is new ray shorter than shortest?
                if(raylengthTemp<raylength){
                    raylength=raylengthTemp;
                }

                sx = rayx-rayvxtemp;
                sy = rayy-rayvytemp;
                sz = rayz-rayvztemp;

                if(isInside(x1, y1, z1, x2, y2, z2, x3, y3, z3, sx, sy, sz)){
                    collision=true;
                }
            }
        }

        return collision;
    }

    public boolean isInside(float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3, float x4, float y4, float z4){

        // Algorithm to check if Point is inside facetriangle
        // calculates new triangles with 2 points of old triangle and point in question
        // checks if angles of triangles adds up to 1

        // vectors
        float abx = x2-x1;
        float aby = y2-y1;
        float abz = z2-z1;

        float acx = x3-x1;
        float acy = y3-y1;
        float acz = z3-z1;

        // area of triangle
        float[] crossABC = crossProduct(abx, aby, abz, acx, acy, acz);

        float absABC = (float) Math.sqrt(Math.pow(crossABC[0],2)+Math.pow(crossABC[1],2)+Math.pow(crossABC[2],2));

        float areaABC = absABC/2;

        // points
        float pax = x1-x4;
        float pay = y1-y4;
        float paz = z1-z4;

        float pbx = x2-x4;
        float pby = y2-y4;
        float pbz = z2-z4;

        float pcx = x3-x4;
        float pcy = y3-y4;
        float pcz = z3-z4;


        float crossPAB[] = crossProduct(pax, pay, paz, pbx, pby, pbz);
        float crossPBC[] = crossProduct(pbx, pby, pbz, pcx, pcy, pcz);
        float crossPAC[] = crossProduct(pax, pay, paz, pcx, pcy, pcz);

        float absPAB = (float) Math.sqrt(Math.pow(crossPAB[0],2)+Math.pow(crossPAB[1],2)+Math.pow(crossPAB[2],2));
        float absPBC = (float) Math.sqrt(Math.pow(crossPBC[0],2)+Math.pow(crossPBC[1],2)+Math.pow(crossPBC[2],2));
        float absPAC = (float) Math.sqrt(Math.pow(crossPAC[0],2)+Math.pow(crossPAC[1],2)+Math.pow(crossPAC[2],2));

        // angles of triangle
        float alpha = absPBC/(2*areaABC);
        float beta = absPAB/(2*areaABC);
        float gamma = absPAC/(2*areaABC);

        // add all angles
        float ges = alpha+beta+gamma;

        if((ges)>=0.99&&(ges)<=1.01){
            return true;
        }

        return false;

    }

    public float[] crossProduct(float x1, float y1, float z1, float x2, float y2, float z2){

        float[] cross = new float[3];
        cross[0] = y1*z2-z1*y2;
        cross[1] = z1*x2-x1*z2;
        cross[2] = x1*y2-y1*x2;
        return cross;
    }

    public float getRayLength(){
        return raylength;
    }

    public void resetRayLength(){
        raylength=99999;
    }

    public void resetCollision(){
        collision=false;
    }

}
