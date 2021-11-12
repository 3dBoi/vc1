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

        System.out.println("X: "+e.getX());
        System.out.println("Y: "+e.getY());

        float mouseX = e.getX();
        float mouseY = e.getY();

//        int[] viewport = new int[4];
        int[] viewport = {0,0,800,600};
        float winX, winY, winZ;
        FloatBuffer WinZ = FloatBuffer.allocate(1);
        float[] pos1 = new float[3];// posX, posY, posZ;

        Ray ray = new Ray();

        float[] pos2 = new float[3];

        float[] posfinal = new float[3];
//        gl.glGetIntegerv(GL.GL_VIEWPORT, viewport, 0);

        System.out.println("Viewport: "+Arrays.toString(viewport));
//        double objX, objY, objZ;//holder for world coordinates
//        int view[4];//viewport dimensions+pos
//        double  p[16];//projection matrix
//        double  m[16];//modelview matrix
//        double z;//Z-Buffer Value?


//        gl.glGetDoublev( GL.GL_MODELVIEW_MATRIX, modelview, 0 );
//        gl.glGetDoublev( GL.GL_PROJECTION_MATRIX, projection, 0 );
//        gl.glGetIntegerv( GL.GL_VIEWPORT, viewport, 0 );

        winX = mouseX;
        winY = viewport[3] - mouseY;
//        gl. glReadPixels((int) mouseX, (int) winY, 1, 1, gl.GL_DEPTH_COMPONENT, gl.GL_FLOAT, WinZ);
//        winZ = WinZ.array()[0];
//        pmvMatrix.gluUnProject(winX, winY, 0.0f, viewport, 0, pos1, 0);
//        pmvMatrix.gluUnProject(winX, winY, 1.0f, viewport, 0, pos2, 0);

        pmvMatrix.gluUnProjectRay(winX, winY, 0.0f, 1.0f, viewport, 0, ray);

        pos1 = ray.orig;
        pos2[0] = ray.orig[0]+ray.dir[0]*10;
        pos2[1] = ray.orig[1]+ray.dir[1]*10;
        pos2[2] = ray.orig[2]+ray.dir[2]*10;

        posfinal[0] = pos2[0]-pos1[0];
        posfinal[1] = pos2[1]-pos1[1];
        posfinal[2] = pos2[2]-pos1[2];


        this.verticies = new float[]{pos1[0], pos1[1], pos1[2], 0.0f, 0.0f, 1.0f, 0.0f, 0.0f,
                                     pos2[0], pos2[1], pos2[2], 0.0f, 0.0f, 1.0f, 0.0f, 0.0f};

        System.out.println("pos:  " +Arrays.toString(pos2));


        clicked = true;
        fertig = true;



//        float[] proj = {45.0f, 800.0f/600.0f, 0.1f, 10000.0f};
//        float[] view = {0.0f, 0.0f, 800.0f, 600.0f};

//        float[] invVP = {(proj[0]*view[0])+(proj[1]*view[2]), (proj[0]*view[1])+(proj[1]*view[3]),
//                         (proj[2]*view[0])+(proj[3]*view[2]), (proj[2]*view[1])+(proj[3]*view[3])}

//        float a = (proj[0]*view[0])+(proj[1]*view[2]);
//        float b = (proj[0]*view[1])+(proj[1]*view[3]);
//        float c = (proj[2]*view[0])+(proj[3]*view[2]);
//        float d = (proj[2]*view[1])+(proj[3]*view[3]);
//
//        float bruch = a*d-b*c;
//        float[] invVP = {(d/bruch), -1*(b/bruch),
//                          -1*(c/bruch), (a/bruch)};
//
//        float[] screenPos = {mouseX, -mouseY, 1.0f, 1.0f};
//
//        float[] worldPos = {(invVP[0]*screenPos[0])+(invVP[1]*screenPos[2]), (invVP[0]*screenPos[1])+(invVP[1]*screenPos[3]),
//                            (invVP[2]*screenPos[0])+(invVP[3]*screenPos[2]), (invVP[2]*screenPos[1])+(invVP[3]*screenPos[3])};
//
//        worldPos[3] = 1.0f / worldPos[3];
//        worldPos[0] *= worldPos[3];
//        worldPos[1] *= worldPos[3];
//        worldPos[2] *= worldPos[3];
//        //System.out.println(Arrays.toString(worldPos));
//
//        if(counter<25){
//            testVertices[counter]=worldPos[0];
//            testVertices[counter+1]=worldPos[1];
//            testVertices[counter+2]=worldPos[2];
//            testVertices[counter+3]=0.0f;
//            testVertices[counter+4]=0.0f;
//            testVertices[counter+5]=-1.0f;
//            testVertices[counter+6]=0.0f;
//            testVertices[counter+7]=0.0f;
//
//            //System.out.println("Vertices: "+ Arrays.toString(testVertices));
//
//            counter=counter+8;
        }



//
//        glm::mat4 proj = glm::perspective(FoV, AspectRatio, Near, Far);
//        glm::mat4 view = glm::lookAt(glm::vec3(0.0f), CameraDirection, CameraUpVector);
//
//        glm::mat4 invVP = glm::inverse(proj * view);
//        glm::vec4 screenPos = glm::vec4(mouseX, -mouseY, 1.0f, 1.0f);
//        glm::vec4 worldPos = invVP * screenPos;
//
//        glm::vec3 dir = glm::normalize(glm::vec3(worldPos));
//
//        return dir;
//    }

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
