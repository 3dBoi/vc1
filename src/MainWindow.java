/**
 * Copyright 2012-2013 JogAmp Community. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are
 * permitted provided that the following conditions are met:
 *
 *    1. Redistributions of source code must retain the above copyright notice, this list of
 *       conditions and the following disclaimer.
 *
 *    2. Redistributions in binary form must reproduce the above copyright notice, this list
 *       of conditions and the following disclaimer in the documentation and/or other materials
 *       provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY JogAmp Community ``AS IS'' AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL JogAmp Community OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * The views and conclusions contained in the software and documentation are those of the
 * authors and should not be interpreted as representing official policies, either expressed
 * or implied, of JogAmp Community.
 */


import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import org.opencv.core.Core;

/**
 * Top-level class of the application displaying the OpenGL component.
 * Uses the progammable pipeline.
 * Creates a Window (JFrame) where the OpenGL Canvas is displayed in.
 * Starts an animation loop.
 * Displays content defined in the renderer class
 *
 * Based on a tutorial by Chua Hock-Chuan
 * http://www3.ntu.edu.sg/home/ehchua/programming/opengl/JOGL2.0.html
 *
 * and on an example by Xerxes Rånby
 * http://jogamp.org/git/?p=jogl-demos.git;a=blob;f=src/demos/es2/RawGL2ES2demo.java;hb=HEAD
 *
 * @author Karsten Lehn
 * @version 12.11.2017, 19.9.2019
 *
 */
public class MainWindow extends JFrame {

    private static final long serialVersionUID = 1L;
    // Define constants for the top-level container
    private static String TITLE = "Box with texture - Main Window - Programmable Pipeline";
    private static final int CANVAS_WIDTH = 800;  // width of the drawable
    private static final int CANVAS_HEIGHT = 600; // height of the drawable
    private static final int FPS = 60; // animator's target frames per second
    public static MainWindow mainWindow;
    public static JLabel jLabelRED = new JLabel();
    public static JLabel jLabelGREEN = new JLabel();
    public static JLabel jLabelBLUE = new JLabel();


    public JLabel getjLabelRED() {
        return jLabelRED;
    }

    public MainWindow() {
        jLabelRED.setOpaque(true);
        jLabelRED.setBackground(new Color(0,0,0,0));
        jLabelRED.setBounds(-50,-50,15,15);

        jLabelBLUE.setOpaque(true);
        jLabelBLUE.setBackground(Color.BLUE);
        jLabelBLUE.setBounds(-50,-50,15,15);

        jLabelGREEN.setOpaque(true);
        jLabelGREEN.setBackground(Color.GREEN);
        jLabelGREEN.setBounds(-50,-50,15,15);


        // Setup an OpenGL context for the Canvas
        // Setup OpenGL to use the programmable pipeline
        // Setting to OpenGL 3
        GLProfile profile = GLProfile.get(GLProfile.GL3);
        GLCapabilities capabilities = new GLCapabilities(profile);
        // Enabling of multisampling
        capabilities.setSampleBuffers(true);
        capabilities.setNumSamples(8);
        // Create the OpenGL rendering canvas
        GLCanvas canvas = new Renderer(CANVAS_WIDTH, CANVAS_HEIGHT, capabilities);
        canvas.setBounds(0,0,CANVAS_WIDTH, CANVAS_HEIGHT);

        //redoing lost code

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0,0,CANVAS_WIDTH, CANVAS_HEIGHT);


        layeredPane.add(jLabelRED);
        layeredPane.add(jLabelGREEN);
        layeredPane.add(jLabelBLUE);

        layeredPane.add(canvas);




        // Create an animator that drives the canvas display() at the specified
        // frame rate.
        final FPSAnimator animator = new FPSAnimator(canvas, FPS, true);

        // Create the top-level container frame
        //this.getContentPane().setSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
//        this.getContentPane().setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
//        this.getContentPane().setBounds(0,0,CANVAS_WIDTH, CANVAS_HEIGHT);
        this.getContentPane().add(layeredPane);
        this.getContentPane().setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Use a dedicate thread to run stop() to ensure the
                // animator stops before program exit.
                new Thread() {
                    @Override
                    public void run() {
                        if (animator.isStarted()) animator.stop();
                        System.exit(0);
                    }
                    //Hai 8====D
                    // wololololololo
                    //asdgaskneicgggffsss
                }.start();
            }
        });
        this.setTitle(TITLE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        animator.start(); // start the animation loop	// TODO Auto-generated constructor stub

        // OpenGL: request focus for canvas
        canvas.requestFocusInWindow();
    }


    public static void moveBLUE(double x, double y){
        jLabelBLUE.setBackground(Color.BLUE);
        jLabelBLUE.setLocation((int) x,(int) y);
    }

    public static void moveGREEN(double x, double y){
        jLabelGREEN.setBackground(Color.GREEN);
        jLabelGREEN.setLocation((int) x,(int) y);
    }

    public static void moveRED(double x, double y){
        jLabelRED.setBackground(Color.RED);
        jLabelRED.setLocation((int) x,(int) y);
    }




    /**
     * Creates the main window and starts the program
     * @param args Arguments are not used
     */
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            Runtime.getRuntime().runFinalization();
            System.out.println("Give time to settle");
            Runtime.getRuntime().exit(1);
        }));
        new MainWindow();
        // Load OpenCV libraries and start program
        System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
        //System.loadLibrary( "opencv_videoio_ffmpeg440_64" );
        new VideoProcessing();
        //testhalo
    }

}
