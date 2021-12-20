import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL3;
import com.jogamp.opengl.math.Matrix4;
import com.jogamp.opengl.math.Ray;
import com.jogamp.opengl.util.PMVMatrix;

import java.awt.*;
import java.awt.event.*;
import java.nio.FloatBuffer;
import java.util.Arrays;

/**
 * Java class for handling the keyboard and mouse interaction.
 * Intended to be used for an OpenGL scene renderer.
 * @author Karsten Lehn
 * @version 23.8.2017, 10.9.2017
 */

public class InteractionHandler implements MouseListener{

    // Constant for debugging purposes
    private static final boolean VERBOSE = false;

    // Variables for scene rotation
    private float angleXaxis = 0f;
    private float angleYaxis = 0f;
    // Variables for scene translation
    private float xPosition = 0f;
    private float yPosition = 0f;
    private float yPositionInc= 0.1f;

    private long elapseNanos1;
    private long elapseNanos2;
    private long elapseNanos3;
    private long elapseNanos4;
    private long elapseNanos5;
    private long elapseNanos6;
    private long elapseNanos7;

    PMVMatrix pmvMatrix;
    Entity[] entities;
    boolean clicked;
    boolean fertig = false;
    float[] verticies;
    Audio audio = new Audio();

    /**
     * Standard constructor for creation of the interaction handler.
     */
    public InteractionHandler(Entity[] entities, boolean clicked, PMVMatrix pmvMatrix) {
        this.entities = entities;
        this.clicked = clicked;
        this.pmvMatrix = pmvMatrix;
    }

    public float getAngleXaxis() {
        return angleXaxis;
    }

    public float getAngleYaxis() {
        return angleYaxis;
    }

    public float getxPosition() {
        return xPosition;
    }

    public float getyPosition() {
        return yPosition;
    }

    public float getyPositionInc() {
        return yPositionInc;
    }


    @Override
    /**
     * Implements one method of the interface MouseListener
     */
    public void mouseClicked(MouseEvent e) {

        float mouseX = e.getX();
        float mouseY = e.getY();

        int[] viewport = {0, 0, 1400, 1050};
        float winX, winY;
        float[] pos1 = new float[3];// posX, posY, posZ;
        Ray ray = new Ray();
        float[] pos2 = new float[3];
        winX = mouseX;
        winY = viewport[3] - mouseY;

        // Casts Ray at mouseposition into the scene
        pmvMatrix.gluUnProjectRay(winX, winY, 0.0f, 1.0f, viewport, 0, ray);

        pos1 = ray.orig;
        pos2[0] = ray.orig[0] + ray.dir[0] * 10;
        pos2[1] = ray.orig[1] + ray.dir[1] * 10;
        pos2[2] = ray.orig[2] + ray.dir[2] * 10;

        this.verticies = new float[]{pos1[0], pos1[1], pos1[2], 0.0f, 0.0f, 1.0f, 0.0f, 0.0f,
                pos2[0], pos2[1], pos2[2], 0.0f, 0.0f, 1.0f, 0.0f, 0.0f};
        float raylength = 99999;
        int i = 0;

        // Calcs raycollision with hitbox of animated objects and returns the shortest length of the ray to pick only the closest obj
        System.out.println("BigDrum");
        if(entities[6].rayCollision(ray)){
            if(entities[6].getCollision().getRayLength()<raylength){
                raylength=entities[6].getCollision().getRayLength();
                i=1;
            }
            entities[6].getCollision().resetRayLength();
            entities[6].getCollision().resetCollision();
        }

        System.out.println("CrashSymbal");
        if(entities[8].rayCollision(ray)){
            if(entities[8].getCollision().getRayLength()<raylength){
                raylength=entities[8].getCollision().getRayLength();
                i=2;
            }
            entities[8].getCollision().resetRayLength();
            entities[8].getCollision().resetCollision();
        }

        System.out.println("SmallDrum");
        if(entities[13].rayCollision(ray)){
            if(entities[13].getCollision().getRayLength()<raylength){
                raylength=entities[13].getCollision().getRayLength();
                i=3;
            }
            entities[13].getCollision().resetRayLength();
            entities[13].getCollision().resetCollision();
        }

        System.out.println("SmollDrum");
        if(entities[18].rayCollision(ray)){
            if(entities[18].getCollision().getRayLength()<raylength){
                raylength=entities[18].getCollision().getRayLength();
                i=4;
            }
            entities[18].getCollision().resetRayLength();
            entities[18].getCollision().resetCollision();
        }

        System.out.println("MidDrum");
        if(entities[23].rayCollision(ray)){
            if(entities[23].getCollision().getRayLength()<raylength){
                raylength=entities[23].getCollision().getRayLength();
                i=5;
            }
            entities[23].getCollision().resetRayLength();
            entities[23].getCollision().resetCollision();
        }

        System.out.println("RideSymbal");
        if(entities[25].rayCollision(ray)){
            if(entities[25].getCollision().getRayLength()<raylength){
                raylength=entities[25].getCollision().getRayLength();
                i=6;
            }
            entities[25].getCollision().resetRayLength();
            entities[25].getCollision().resetCollision();
        }

        System.out.println("Hi-Hat");
        if(entities[28].rayCollision(ray)){
            if(entities[28].getCollision().getRayLength()<raylength){
                raylength=entities[28].getCollision().getRayLength();
                i=7;
            }
            entities[28].getCollision().resetRayLength();
            entities[28].getCollision().resetCollision();
        }

        // play Animation of selected obj
        switch (i){

            // BigDrum
            case 1:
                audio.playAudio("BassDrum.wav");
                entities[0].getAnimationHandler().setAnimationTrigger(true);
                entities[1].getAnimationHandler().setAnimationTrigger(true);
                entities[2].getAnimationHandler().setAnimationTrigger(true);
                entities[3].getAnimationHandler().setAnimationTrigger(true);
                entities[4].getAnimationHandler().setAnimationTrigger(true);
                entities[5].getAnimationHandler().setAnimationTrigger(true);
                break;

            // Crash Symbal
            case 2:
                audio.playAudio("Crash.wav");
                entities[7].getAnimationHandler().setAnimationTrigger(true);
                break;

            // SmallDrum
            case 3:
                audio.playAudio("Snare.wav");
                entities[9].getAnimationHandler().setAnimationTrigger(true);
                entities[10].getAnimationHandler().setAnimationTrigger(true);
                entities[11].getAnimationHandler().setAnimationTrigger(true);
                entities[12].getAnimationHandler().setAnimationTrigger(true);
                break;

            // SmollDrum
            case 4:
                audio.playAudio("TomTom.wav");
                entities[14].getAnimationHandler().setAnimationTrigger(true);
                entities[15].getAnimationHandler().setAnimationTrigger(true);
                entities[16].getAnimationHandler().setAnimationTrigger(true);
                entities[17].getAnimationHandler().setAnimationTrigger(true);
                break;

            // MidDrum
            case 5:
                audio.playAudio("StandTom.wav");
                entities[19].getAnimationHandler().setAnimationTrigger(true);
                entities[20].getAnimationHandler().setAnimationTrigger(true);
                entities[21].getAnimationHandler().setAnimationTrigger(true);
                entities[22].getAnimationHandler().setAnimationTrigger(true);
                break;

            // Ride Symbal
            case 6:
                audio.playAudio("Ride.wav");
                entities[24].getAnimationHandler().setAnimationTrigger(true);
                break;

            // Hi-Hat
            case 7:
                audio.playAudio("Hi-Hat.wav");
                entities[26].getAnimationHandler().setAnimationTrigger(true);
                entities[27].getAnimationHandler().setAnimationTrigger(true);
                break;
            default:
                break;
        }
        i=0;
        clicked = true;
        fertig = true;
    }

    public void imageProcessResult(float x, float y) {
        int[] viewport = {0, 0, 1400, 1050};
        float winX, winY;
        float[] pos1 = new float[3];// posX, posY, posZ;
        Ray ray = new Ray();
        float[] pos2 = new float[3];
        winX = x;
        winY = viewport[3] - y;
        pmvMatrix.gluUnProjectRay(winX, winY, 0.0f, 1.0f, viewport, 0, ray);

        pos1 = ray.orig;
        pos2[0] = ray.orig[0] + ray.dir[0] * 10;
        pos2[1] = ray.orig[1] + ray.dir[1] * 10;
        pos2[2] = ray.orig[2] + ray.dir[2] * 10;

        this.verticies = new float[]{pos1[0], pos1[1], pos1[2], 0.0f, 0.0f, 1.0f, 0.0f, 0.0f,
                pos2[0], pos2[1], pos2[2], 0.0f, 0.0f, 1.0f, 0.0f, 0.0f};

        float raylength = 99999;
        int i = 0;

        System.out.println("BigDrum");
        if(entities[6].rayCollision(ray)){
            if(entities[6].getCollision().getRayLength()<raylength){
                raylength=entities[6].getCollision().getRayLength();
                i=1;
            }
            entities[6].getCollision().resetRayLength();
            entities[6].getCollision().resetCollision();
        }

        System.out.println("CrashSymbal");
        if(entities[8].rayCollision(ray)){
            if(entities[8].getCollision().getRayLength()<raylength){
                raylength=entities[8].getCollision().getRayLength();
                i=2;
            }
            entities[8].getCollision().resetRayLength();
            entities[8].getCollision().resetCollision();
        }

        System.out.println("SmallDrum");
        if(entities[13].rayCollision(ray)){
            if(entities[13].getCollision().getRayLength()<raylength){
                raylength=entities[13].getCollision().getRayLength();
                i=3;
            }
            entities[13].getCollision().resetRayLength();
            entities[13].getCollision().resetCollision();
        }

        System.out.println("SmollDrum");
        if(entities[18].rayCollision(ray)){
            if(entities[18].getCollision().getRayLength()<raylength){
                raylength=entities[18].getCollision().getRayLength();
                i=4;
            }
            entities[18].getCollision().resetRayLength();
            entities[18].getCollision().resetCollision();
        }

        System.out.println("MidDrum");
        if(entities[23].rayCollision(ray)){
            if(entities[23].getCollision().getRayLength()<raylength){
                raylength=entities[23].getCollision().getRayLength();
                i=5;
            }
            entities[23].getCollision().resetRayLength();
            entities[23].getCollision().resetCollision();
        }

        System.out.println("RideSymbal");
        if(entities[25].rayCollision(ray)){
            if(entities[25].getCollision().getRayLength()<raylength){
                raylength=entities[25].getCollision().getRayLength();
                i=6;
            }
            entities[25].getCollision().resetRayLength();
            entities[25].getCollision().resetCollision();
        }

        System.out.println("Hi-Hat");
        if(entities[28].rayCollision(ray)){
            if(entities[28].getCollision().getRayLength()<raylength){
                raylength=entities[28].getCollision().getRayLength();
                i=7;
            }
            entities[28].getCollision().resetRayLength();
            entities[28].getCollision().resetCollision();
        }

        switch (i){
            // BigDrum
            case 1:
                if(elapseNanos1+ 1500000000 < System.nanoTime()){

                    audio.playAudio("BassDrum.wav");

                    entities[0].getAnimationHandler().setAnimationTrigger(true);

                    entities[1].getAnimationHandler().setAnimationTrigger(true);

                    entities[2].getAnimationHandler().setAnimationTrigger(true);

                    entities[3].getAnimationHandler().setAnimationTrigger(true);

                    entities[4].getAnimationHandler().setAnimationTrigger(true);

                    entities[5].getAnimationHandler().setAnimationTrigger(true);

                    elapseNanos1 = System.nanoTime();}
                break;
            // Crash Symbal
            case 2:
                if(elapseNanos2+ 1500000000 < System.nanoTime()){
                    audio.playAudio("Crash.wav");
                    entities[7].getAnimationHandler().setAnimationTrigger(true);
                    elapseNanos2 = System.nanoTime();}
                break;

            // SmallDrum
            case 3:
                if(elapseNanos3+ 1500000000 < System.nanoTime()){
                    audio.playAudio("Snare.wav");
                    entities[9].getAnimationHandler().setAnimationTrigger(true);
                    entities[10].getAnimationHandler().setAnimationTrigger(true);
                    entities[11].getAnimationHandler().setAnimationTrigger(true);
                    entities[12].getAnimationHandler().setAnimationTrigger(true);
                    elapseNanos3 = System.nanoTime();}
                break;

            // SmollDrum
            case 4:
                if(elapseNanos4+ 1500000000 < System.nanoTime()){
                    audio.playAudio("TomTom.wav");
                    entities[14].getAnimationHandler().setAnimationTrigger(true);
                    entities[15].getAnimationHandler().setAnimationTrigger(true);
                    entities[16].getAnimationHandler().setAnimationTrigger(true);
                    entities[17].getAnimationHandler().setAnimationTrigger(true);
                    elapseNanos4 = System.nanoTime();}
                break;

            // MidDrum
            case 5:
                if(elapseNanos5+ 1500000000 < System.nanoTime()){
                    audio.playAudio("StandTom.wav");
                    entities[19].getAnimationHandler().setAnimationTrigger(true);
                    entities[20].getAnimationHandler().setAnimationTrigger(true);
                    entities[21].getAnimationHandler().setAnimationTrigger(true);
                    entities[22].getAnimationHandler().setAnimationTrigger(true);
                    elapseNanos5 = System.nanoTime();}
                break;

            // Ride Symbal
            case 6:
                if(elapseNanos6+ 1500000000 < System.nanoTime()){
                    audio.playAudio("Ride.wav");
                    entities[24].getAnimationHandler().setAnimationTrigger(true);
                    elapseNanos6 = System.nanoTime();}
                break;

            // Hi-Hat
            case 7:
                if(elapseNanos7+ 1500000000 < System.nanoTime()){
                    audio.playAudio("Hi-Hat.wav");
                    entities[26].getAnimationHandler().setAnimationTrigger(true);
                    entities[27].getAnimationHandler().setAnimationTrigger(true);
                    elapseNanos7 = System.nanoTime();}
                break;
            default:
                break;
        }
        i=0;
        clicked = true;
        fertig = true;
    }




    @Override
    /**
     * Implements one method of the interface MouseListener
     */
    public void mousePressed(MouseEvent e) {
    }

    @Override
    /**
     * Implements one method of the interface MouseListener
     */
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    /**
     * Implements one method of the interface MouseListener
     */
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    /**
     * Implements one method of the interface MouseListener
     */
    public void mouseExited(MouseEvent e) {
    }
}
