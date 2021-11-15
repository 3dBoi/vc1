import com.jogamp.opengl.math.Ray;

public class Collision {

    public Collision(){

    }

    public boolean rayCollision(Ray ray, float[] verticies){

        // hinreichende Bedingung : Schnittpunkt Ebene Face und Ray
        // notwendige Bedingung: Schnittpunkt auf Face (in Boundingbox)

        int counter = 0;
        float x1, x2, x3, y1, y2, y3, z1, z2, z3;
        float vx12, vy12, vz12, vx13, vy13, vz13;
        float nx, ny, nz;
        float rayx, rayy, rayz, rayvx, rayvy, rayvz;
        float diffx, diffy, diffz;
        float length, raylength;
        float prod1, prod2, prod3;
        float rayvxtemp, rayvytemp, rayvztemp;
        float sx, sy, sz;

        rayx = ray.orig[0];
        rayy = ray.orig[1];
        rayz = ray.orig[2];

        rayvx = ray.dir[0];
        rayvy = ray.dir[1];
        rayvz = ray.dir[2];

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
        counter++;


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

        // Länge des Normalvektors
        length = (float) Math.sqrt( nx +  ny +  nz);

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

        raylength = (float) Math.sqrt( rayvxtemp +  rayvytemp +  rayvztemp);

        sx = rayx-rayvxtemp;
        sy = rayy-rayvytemp;
        sz = rayz-rayvztemp;






//        Vector3D(double x, double y, double z) {
//            this.x = x;
//            this.y = y;
//            this.z = z;
//        }
//
//        Vector3D plus(Vector3D v) {
//            return new Vector3D(x + v.x, y + v.y, z + v.z);
//        }
//
//        Vector3D minus(Vector3D v) {
//            return new Vector3D(x - v.x, y - v.y, z - v.z);
//        }
//
//        Vector3D times(double s) {
//            return new Vector3D(s * x, s * y, s * z);
//        }
//
//        double dot(Vector3D v) {
//            return x * v.x + y * v.y + z * v.z;
//        }
//
//        @Override
//        public String toString() {
//            return String.format("(%f, %f, %f)", x, y, z);
//        }
//    }
//
//    private static Vector3D intersectPoint(Vector3D rayVector, Vector3D rayPoint, Vector3D planeNormal, Vector3D planePoint) {
//        Vector3D diff = rayPoint.minus(planePoint);
//        double prod1 = diff.dot(planeNormal);
//        double prod2 = rayVector.dot(planeNormal);
//        double prod3 = prod1 / prod2;
//        return rayPoint.minus(rayVector.times(prod3));
//    }



        return false;
    }

    public boolean isInside(float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3, float x4, float y4, float z4){

        // Algorithm to test Normal Direction
        // translate Triangle so point is its origin
        // make triangles inside triangle
        // test normal directions of triangles
        // if the same => point is inside triangle

        return false;


    }

    public float[] crossProduct(float x1, float y1, float z1, float x2, float y2, float z2){

        float[] cross = new float[3];
        cross[0] = y1*z2-z1*y2;
        cross[1] = z1*x2-x1*z2;
        cross[2] = x1*y2-y1*x2;
        return cross;
    }

    static double area(int x1, int y1, int x2, int y2,
                       int x3, int y3)
    {
        return Math.abs((x1*(y2-y3) + x2*(y3-y1)+
                x3*(y1-y2))/2.0);
    }

}
