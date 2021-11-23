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

public class InteractionHandler implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener{

    // Constant for debugging purposes
    private static final boolean VERBOSE = false;

    // Variables for camera distance
    private float eyeZ = 2f;
    private float eyeZInc = 0.01f;
    // Variables for scene rotation
    private float angleXaxis = 0f;
    private float angleYaxis = 0f;
    private float angleXaxisInc = 1f;
    private float angleYaxisInc = 1f;
    // Variables for scene translation
    private float xPosition = 0f;
    private float yPosition = 0f;
    private float xPositionInc = 0.1f;
    private float yPositionInc= 0.1f;
    // Variables for keyboard control
    private boolean ctrlKeyPressed = false;
    // Variables for mouse control
    private boolean leftMouseButtonPressed = false;
    private boolean rightMouseButtonPressed = false;
    private Point lastMouseLocation;
    // Taking care of the screen size (mapping of mouse coordinates to angle/translation)
    private final float mouseRotationFactor = 0.1f;
    private final float mouseTranslationFactor = 0.1f;
    private final float mouseWheelScrollFactor = 10f;

    GL3 gl;
    PMVMatrix pmvMatrix;
    Entity[] entities;
    //Hier später löschen
    InitObject[] objArr;
    int[] vboName;
    boolean clicked;
    boolean fertig = false;
    float[] verticies;

    /**
     * Standard constructor for creation of the interaction handler.
     */
    public InteractionHandler(GL3 gl, PMVMatrix pmvMatrix, Entity[] entities, InitObject[] onjArr, int[] vboName, boolean clicked) {
        this. gl = gl;
        this.pmvMatrix = pmvMatrix;
        this.entities = entities;
        this.objArr = onjArr;
        this.vboName = vboName;
        this.clicked = clicked;
    }

    public float getEyeZ() {
        return eyeZ;
    }

    public void setEyeZ(float eyeZ) {
        this.eyeZ = eyeZ;
    }

    public float getEyeZInc() {
        return eyeZInc;
    }

    public void setEyeZInc(float eyeZInc) {
        this.eyeZInc = eyeZInc;
    }

    public float getAngleXaxis() {
        return angleXaxis;
    }

    public void setAngleXaxis(float angleXaxis) {
        this.angleXaxis = angleXaxis;
    }

    public float getAngleYaxis() {
        return angleYaxis;
    }

    public void setAngleYaxis(float angleYaxis) {
        this.angleYaxis = angleYaxis;
    }

    public float getAngleXaxisInc() {
        return angleXaxisInc;
    }

    public void setAngleXaxisInc(float angleXaxisInc) {
        this.angleXaxisInc = angleXaxisInc;
    }

    public float getAngleYaxisInc() {
        return angleYaxisInc;
    }

    public void setAngleYaxisInc(float angleYaxisInc) {
        this.angleYaxisInc = angleYaxisInc;
    }

    public float getxPosition() {
        return xPosition;
    }

    public void setxPosition(float xPosition) {
        this.xPosition = xPosition;
    }

    public float getyPosition() {
        return yPosition;
    }

    public void setyPosition(float yPosition) {
        this.yPosition = yPosition;
    }

    public float getxPositionInc() {
        return xPositionInc;
    }

    public void setxPositionInc(float xPositionInc) {
        this.xPositionInc = xPositionInc;
    }

    public float getyPositionInc() {
        return yPositionInc;
    }

    public void setyPositionInc(float yPositionInc) {
        this.yPositionInc = yPositionInc;
    }

    public float getMouseRotationFactor() {
        return mouseRotationFactor;
    }

    public float getMouseTranslationFactor() {
        return mouseTranslationFactor;
    }

    public float getMouseWheelScrollFactor() {
        return mouseWheelScrollFactor;
    }

    @Override
    /**
     * Implements a method from the interface KeyListener
     * Handles all key input.
     */
    public void keyPressed(KeyEvent e) {

        // K to start stop animation
        if(e.getKeyCode()==KeyEvent.VK_K){
            if(entities[0].getAnimationHandler().getAnimationTrigger()){
                entities[0].getAnimationHandler().setAnimationTrigger(false);
            }else{
                entities[0].getAnimationHandler().setAnimationTrigger(true);
            }

            if(entities[1].getAnimationHandler().getAnimationTrigger()){
                entities[1].getAnimationHandler().setAnimationTrigger(false);
            }else{
                entities[1].getAnimationHandler().setAnimationTrigger(true);
            }
            System.out.println("K Pressed");
        }
//        int keyCode = e.getKeyCode();
//        switch (keyCode) {
//            case KeyEvent.VK_CONTROL:
//                ctrlKeyPressed = true;
//                break;
//            case KeyEvent.VK_LEFT:
//                if (ctrlKeyPressed) {
//                    xPosition += xPositionInc;
//                } else {
//                    angleYaxis += angleYaxisInc;
//                }
//                break;
//            case KeyEvent.VK_RIGHT:
//                if (ctrlKeyPressed) {
//                    xPosition -= xPositionInc;
//                } else {
//                    angleYaxis -= angleYaxisInc;
//                }
//                break;
//            case KeyEvent.VK_UP:
//                if (ctrlKeyPressed) {
//                    yPosition -= yPositionInc;
//                } else {
//                    angleXaxis += angleXaxisInc;
//                }
//                break;
//            case KeyEvent.VK_DOWN:
//                if (ctrlKeyPressed) {
//                    yPosition += yPositionInc;
//                } else {
//                    angleXaxis -= angleXaxisInc;
//                }
//                break;
//            case KeyEvent.VK_MINUS:
//                eyeZ = eyeZ - eyeZInc;
//                break;
//            case KeyEvent.VK_PLUS:
//                eyeZ = eyeZ + eyeZInc;
//                break;
//        }
    }

    @Override
    /**
     * Implements one method of the interface KeyListener
     */
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
            ctrlKeyPressed = false;
        }
    }

    @Override
    /**
     * Implements one method of the interface KeyListener
     */
    public void keyTyped(KeyEvent e) {}


    @Override
    /**
     * Implements one method of the interface MouseListener
     */
    public void mouseClicked(MouseEvent e) {

        System.out.println("X: " + e.getX());
        System.out.println("Y: " + e.getY());

        float mouseX = e.getX();
        float mouseY = e.getY();

        int[] viewport = {0, 0, 800, 600};
        float winX, winY;
        float[] pos1 = new float[3];// posX, posY, posZ;

        Ray ray = new Ray();

        float[] pos2 = new float[3];

        winX = mouseX;
        winY = viewport[3] - mouseY;


        pmvMatrix.gluUnProjectRay(winX, winY, 0.0f, 1.0f, viewport, 0, ray);

        pos1 = ray.orig;
        pos2[0] = ray.orig[0] + ray.dir[0] * 10;
        pos2[1] = ray.orig[1] + ray.dir[1] * 10;
        pos2[2] = ray.orig[2] + ray.dir[2] * 10;

        this.verticies = new float[]{pos1[0], pos1[1], pos1[2], 0.0f, 0.0f, 1.0f, 0.0f, 0.0f,
                pos2[0], pos2[1], pos2[2], 0.0f, 0.0f, 1.0f, 0.0f, 0.0f};

        System.out.println("pos:  " + Arrays.toString(pos2));

        float raylength = 99999;
        int i = 0;

        System.out.println("Cube Left");
        if(entities[1].rayCollision(ray)){
            if(entities[1].getCollision().getRayLength()<raylength){
                raylength=entities[1].getCollision().getRayLength();
                i=1;
            }
            entities[1].getCollision().resetRayLength();
            entities[1].getCollision().resetCollision();
        }

        System.out.println("Cube Right");
        if(entities[3].rayCollision(ray)){
            if(entities[3].getCollision().getRayLength()<raylength){
                raylength=entities[3].getCollision().getRayLength();
                i=2;
            }
            entities[3].getCollision().resetRayLength();
            entities[3].getCollision().resetCollision();
        }

        System.out.println("Cube Center");
        if(entities[5].rayCollision(ray)){
            if(entities[5].getCollision().getRayLength()<raylength){
                raylength=entities[5].getCollision().getRayLength();
                i=3;
            }
            entities[5].getCollision().resetRayLength();
            entities[5].getCollision().resetCollision();
        }

        switch (i){
            case 1:
                if(entities[0].getAnimationHandler().getAnimationTrigger()){
                    entities[0].getAnimationHandler().setAnimationTrigger(false);
                }else{
                    entities[0].getAnimationHandler().setAnimationTrigger(true);
                }
                break;
            case 2:
                if(entities[2].getAnimationHandler().getAnimationTrigger()){
                    entities[2].getAnimationHandler().setAnimationTrigger(false);
                }else{
                    entities[2].getAnimationHandler().setAnimationTrigger(true);
                }
                break;
            case 3:
                if(entities[4].getAnimationHandler().getAnimationTrigger()){
                    entities[4].getAnimationHandler().setAnimationTrigger(false);
                }else{
                    entities[4].getAnimationHandler().setAnimationTrigger(true);
                }
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
        int pressedButton = e.getButton();
        lastMouseLocation = e.getLocationOnScreen();
        if (VERBOSE) {
            System.out.print("Mouse pressed event. ");
            switch (pressedButton) {
                case MouseEvent.BUTTON1:
                    System.out.print("Left mouse button pressed.");
                    break;
                case MouseEvent.BUTTON2:
                    System.out.print("Mouse wheel or middle button pressed.");
                    break;
                case MouseEvent.BUTTON3:
                    System.out.print("Right mouse button pressed.");
                    break;
                case MouseEvent.NOBUTTON:
                    System.out.print(" No button detected.");
                    break;
                default:
                    System.out.print("Unknown button pressed.");
            }
           // System.out.println(" At location: " + lastMouseLocation);
        }
        switch (pressedButton) {
            case MouseEvent.BUTTON1:
                leftMouseButtonPressed = true;
                break;
            case MouseEvent.BUTTON3:
                rightMouseButtonPressed = true;
                break;
        }
    }

    @Override
    /**
     * Implements one method of the interface MouseListener
     */
    public void mouseReleased(MouseEvent e) {
        int releasedButton = e.getButton();
        if (VERBOSE) {
            System.out.print("Mouse pressed event. ");
            switch (releasedButton) {
                case MouseEvent.BUTTON1:
                    System.out.println("Left mouse button released.");
                    break;
                case MouseEvent.BUTTON2:
                    System.out.println("Mouse wheel or middle button released.");
                    break;
                case MouseEvent.BUTTON3:
                    System.out.println("Right mouse button released.");
                    break;
                case MouseEvent.NOBUTTON:
                    System.out.println(" No button detected.");
                    break;
                default:
                    System.out.println("Unknow button pressed.");
            }
        }
        switch (releasedButton) {
            case MouseEvent.BUTTON1:
                leftMouseButtonPressed = false;
                break;
            case MouseEvent.BUTTON3:
                rightMouseButtonPressed = false;
                break;
        }
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

    /**
     * Implements one method of the interface MouseMotionListener
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        Point currentMouseLocation = e.getLocationOnScreen();
        if (VERBOSE) {
            System.out.print("Mouse dragged event.");
            System.out.println(" At mouse location: " + currentMouseLocation);
        }
        double deltaX = currentMouseLocation.getX() - lastMouseLocation.getX();
        double deltaY = currentMouseLocation.getY() - lastMouseLocation.getY();
        lastMouseLocation = currentMouseLocation;
        // holding the left mouse button rotates the scene
        if (leftMouseButtonPressed) {
            angleYaxis += angleYaxisInc * mouseRotationFactor * -deltaX;
            angleXaxis += angleXaxisInc * mouseRotationFactor * -deltaY;
        }
        // holding the right mouse button translates the scene
        if (rightMouseButtonPressed) {
            xPosition += xPositionInc * mouseTranslationFactor * -deltaX;
            yPosition += yPositionInc * mouseTranslationFactor * +deltaY;
        }
    }

    /**
     * Implements one method of the interface MouseMotionListener
     */
    @Override
    public void mouseMoved(MouseEvent e) {
    }

    /**
     * Implements one method of the interface MouseMWheelMovedListener
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (VERBOSE) {
            System.out.print("Mouse wheel moved event.");
            System.out.println(" Wheel rotation: " + e.getPreciseWheelRotation());
        }
        eyeZ += eyeZInc * mouseWheelScrollFactor * e.getPreciseWheelRotation();
    }
}
