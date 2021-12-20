import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.util.ArrayList;


import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.MatOfPoint;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.opencv.core.Core;

import org.opencv.core.Scalar;
import org.opencv.core.Size;

/**
 * Simple application, that opens a video stream
 * - either from the web cam or a video file -
 * grabs frames,
 * performs frame-wise image processing using OpenCV,
 * displays the video stream and the processed stream
 * and saves processed frames to an image file.
 *
 * @author Karsten Lehn
 * @version 4.9.2020
 */
public class VideoProcessing extends JFrame {

    private static JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
    List<MatOfPoint> contours = new ArrayList<>();
    List<MatOfPoint> contours1 = new ArrayList<>();

    private JButton confirmButton;
    private BufferedImagePanel imgPanel1;
    private BufferedImagePanel imgPanel2;
    private BufferedImagePanel imgPanel3;
    //Hier werden die Hues gesaved für die detection!
    private int firstHue;
    private int secondHue;
    private int firstSlow;
    private int secondSlow;
    private int firstSHigh;
    private int secondSHigh;
    private Point[] firstHuePoints = new Point[36];
    private Point[] secondHuePoints = new Point[36];
    private int iterator = 0;
    private int hLow = 0; //Max 180
    private int sLow = 1; //Max 255
    private int vLow = 20; //Max 255
    private int hHigh = 180; //Max 180
    private int sHigh = 255; //Max 255
    private int vHigh = 255; //Max 255
    private double resize = 2.1875;
    private boolean flip = false;

    /**
     * Create object and perform the processing by calling private member functions.
     */

    public VideoProcessing() {

        //Anzeige Fenster
        imgPanel1 = null;
        imgPanel2 = null;
        imgPanel3 = null;

        createFrame();
        processShowVideo();
    }


    /**
     * Returns the path and file of the video to be processed.
     *
     * @return path and file name in one string
     */
    private String getFilePathName() {
        // Begin: Get file path and name from "getRessource"
        // File name determination using getResource (seems to be buggy)
/*		String filePathName = getClass().getResource("./Landscape.avi").getPath();
		filePathName = filePathName.substring(1);  // remove the bug
*/        // End: Get file path and name from "getRessource"


        // Begin: Set relative path and file name
//		String filePathName = "videos\\Landscape.avi";
        // End: Set relative path and file name

        // Choose file path and file name via a file selector box
        int returnVal = fileChooser.showOpenDialog(this);
        if (returnVal != JFileChooser.APPROVE_OPTION) {
            return null;  // cancelled
        }
        File selectedFile = fileChooser.getSelectedFile();
        String filePathName = selectedFile.getPath();
        // End: Choose file path and file name via a file selector box

        System.out.println("Video file name: " + filePathName);
        return filePathName;
    }

    /**
     * @param imgMat   image matrix to be written to a file
     * @param filename name of the file to be created
     */
    private void writeImage(Mat imgMat, String filename) {
        String filePathName = "videos/" + filename;
        Imgcodecs.imwrite(filePathName, imgMat,
                new MatOfInt(Imgcodecs.IMWRITE_PNG_STRATEGY_HUFFMAN_ONLY,
                        Imgcodecs.IMWRITE_PNG_STRATEGY_FIXED));
    }

    /**
     * Create the JFrame to be displayed, displaying two images.
     */
    private void createFrame() {

        setTitle("Original and processed video stream");
        JPanel contentPane = (JPanel) getContentPane();

        contentPane.setLayout(new FlowLayout());

        //Organizer for Sliders and Button
        JPanel organizer = new JPanel();//new BoxLayout  );//(organizer, BoxLayout.Y_AXIS ));
        organizer.setLayout((new BoxLayout(organizer, BoxLayout.X_AXIS)));
        organizer.setPreferredSize(new Dimension(640, 480));

        JPanel boxPanel = new JPanel();
        JPanel boxPanel2 = new JPanel();
        JPanel boxPanel3 = new JPanel();
        JPanel boxPanel4 = new JPanel();

        boxPanel.setLayout(new BorderLayout());
        boxPanel2.setLayout(new BorderLayout());
        boxPanel3.setLayout(new BorderLayout());
        boxPanel4.setLayout(new BoxLayout(boxPanel4, BoxLayout.Y_AXIS));

//        contentPane.setLayout(new BoxLayout(contentPane,BoxLayout.PAGE_AXIS));
        //add webcam footage
        imgPanel1 = new BufferedImagePanel();
        contentPane.add(imgPanel1);

        //add Sliders and Labels
        Label hueLabel = new Label("        Hue   ", SwingConstants.CENTER);
        hueLabel.setFont(new Font("Serif", Font.BOLD, 14));
        boxPanel.add(hueLabel, BorderLayout.PAGE_START);
        boxPanel.add(initHueSlider(contentPane), BorderLayout.CENTER);

        Label satLowLabel = new Label("SaturationLow", SwingConstants.CENTER);
        satLowLabel.setFont(new Font("Serif", Font.BOLD, 14));
        boxPanel2.add(satLowLabel, BorderLayout.PAGE_START);
        boxPanel2.add(initSaturationSliderLow(contentPane), BorderLayout.CENTER);

        Label satHighLabel = new Label("SaturationHigh", SwingConstants.CENTER);
        satHighLabel.setFont(new Font("Serif", Font.BOLD, 14));
        boxPanel3.add(satHighLabel, BorderLayout.PAGE_START);
        boxPanel3.add(initSaturationSliderHigh((contentPane)));

        initButton();
        initFlipButton();
        initResetButton();

        boxPanel4.add(confirmButton);
        boxPanel4.add(flipButton);
        boxPanel4.add(resetButton);

        organizer.add(boxPanel);
        organizer.add(boxPanel2);
        organizer.add(boxPanel3);
        organizer.add(boxPanel4);

        contentPane.add(organizer);

        //displaying first hue filtered
        imgPanel2 = new BufferedImagePanel();
        contentPane.add(imgPanel2);

        //displaying second hue filtered
        imgPanel3 = new BufferedImagePanel();
        contentPane.add(imgPanel3);

        // place the frame at the center of the screen and show
        pack();


        MainWindow.jTabbedPane.add("Bildverarbeitung", contentPane);

//        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
//        setLocation(dim.width / 2 - getWidth() / 2, dim.height / 2 - getHeight() / 2);
//
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setVisible(true);
    }

    /**
     * Reades a video stream from a file or camera, displays the original frames,
     * processes the frames and displays the result.
     */
    private void processShowVideo() {

        // BEGIN: Prepare streaming from internal web cam
        //NOTE: "To open default camera using default backend just pass 0."
        //https://docs.opencv.org/3.4/d8/dfe/classcv_1_1VideoCapture.html#a57c0e81e83e60f36c83027dc2a188e80
        VideoCapture cap = new VideoCapture(0, Videoio.CAP_ANY);
        // END: Prepare streaming from internal web cam

        // BEGIN: Prepare streaming from video file
        //String filePathName = getFilePathName();
        //VideoCapture cap = new VideoCapture(filePathName);
        // END: Prepare streaming from video file

        Mat frame = new Mat();
        Mat grey = new Mat();
        Mat circles = new Mat();
        // Check of file or camera can be opened
        if (!cap.isOpened())
            throw new CvException("The Video File or the Camera could not be opened!");
        cap.read(frame);

        //Count Frames
        int i = 1;
//        System.out.print("Frame count: (" + i + ")");

        // loop for grabbing frames
        while (cap.read(frame)) {

            // Gaussian Blur for smoother detection
            Imgproc.GaussianBlur(frame, frame, new Size(11, 11), 4);
            Imgproc.cvtColor(frame, grey, Imgproc.COLOR_BGR2GRAY);

            if(flip){
            Core.flip(frame,frame,1);}

            // convert the frame to HSV, output processedImage
            Imgproc.cvtColor(frame, processedImage, Imgproc.COLOR_BGR2HSV);

            // apply HSV threshold and output binary image
            //Core.inRange(processedImage, new Scalar(hLow,sLow,vLow), new Scalar(hHigh,sHigh,vHigh), processedImage);
            if (firstHue == 0) {
                Core.inRange(processedImage, new Scalar(hLow, sLow, vLow), new Scalar(hLow + 15, sHigh, vHigh), processedImage1);
            } else {
                Core.inRange(processedImage, new Scalar(firstHue, firstSlow, vLow), new Scalar(firstHue + 20, firstSHigh, vHigh), processedImage1);
            }

            //Median Blur for filtering out additional noise
            Imgproc.medianBlur(processedImage1, processedImage1, 15);
            if (secondHue != 0) {
                Core.inRange(processedImage, new Scalar(secondHue, secondSlow, vLow), new Scalar(secondHue + 20, secondSHigh, vHigh), processedImage2);
            } else {
                Core.inRange(processedImage, new Scalar(hLow, sLow, vLow), new Scalar(hLow + 20, sHigh, vHigh), processedImage2);
            }
            Imgproc.medianBlur(processedImage2, processedImage2, 15);


            //Circle detection
//			Imgproc.HoughCircles(processedImage, circles, Imgproc.HOUGH_GRADIENT, 1.0,
//					(double)grey.rows()/16, // change this value to detect circles with different distances to each other
//					100.0, 30.0, 0, 0); // change the last two parameters
//			// (min_radius & max_radius) to detect larger circles
//			for (int x = 0; x < circles.cols(); x++) {
//				double[] c = circles.get(0, x);
//				Point center = new Point(Math.round(c[0]), Math.round(c[1]));
//				// circle center
//				Imgproc.circle(processedImage, center, 1, new Scalar(0,100,100), 3, 8, 0 );
//				// circle outline
//				int radius = (int) Math.round(c[2]);
//				Imgproc.circle(processedImage, center, radius, new Scalar(255,0,255), 3, 8, 0 );
//			}

            //Find Contours


            if (secondHue != 0) {


                Imgproc.findContours(processedImage2, contours1, new Mat(), Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_NONE);
                double maxArea2 = 100;
                float[] radius2 = new float[1];
                Point center1 = new Point();
                for (MatOfPoint c : contours1) {
                    if (Imgproc.contourArea(c) > maxArea2) {
                        MatOfPoint2f c2f = new MatOfPoint2f(c.toArray());
                        Imgproc.minEnclosingCircle(c2f, center1, radius2);
                    }
                }
                MainWindow.moveGREEN(center1.x * resize, center1.y * resize);
                secondHuePoints[iterator] = center1;
                System.out.println("GREEN SECOND HUE: " + center1);

                //Draw circle
                Imgproc.circle(frame, center1, (int) radius2[0], new Scalar(0, 255, 0), 3);


            }

            Imgproc.findContours(processedImage1, contours, new Mat(), Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_NONE);
            double maxArea = 100;
            float[] radius = new float[1];
            Point center = new Point();
            for (MatOfPoint c : contours) {
                if (Imgproc.contourArea(c) > maxArea) {
                    MatOfPoint2f c2f = new MatOfPoint2f(c.toArray());
                    Imgproc.minEnclosingCircle(c2f, center, radius);
                }
            }

            //move cursor
            MainWindow.moveBLUE(center.x * resize, center.y * resize);

            //draw circle
            firstHuePoints[iterator] = center;
            Imgproc.circle(frame, center, (int) radius[0], new Scalar(255, 0, 0), 4);


//            if (secondHue != 0) {
//                Imgproc.findContours(processedImage2, contours1, new Mat(), Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_NONE);
//                double maxArea2 = 100;
//                float[] radius2 = new float[1];
//                Point center1 = new Point();
//                for (MatOfPoint c : contours1) {
//                    if (Imgproc.contourArea(c) > maxArea2) {
//                        MatOfPoint2f c2f = new MatOfPoint2f(c.toArray());
//                        Imgproc.minEnclosingCircle(c2f, center1, radius2);
//                    }
//                }
//
//                MainWindow.moveGREEN(center1.x,center1.y);
//                secondHuePoints[iterator] = center1;
//                System.out.println("GREEN SECOND HUE: " +center1);
//
//                //Draw circle
//                Imgproc.circle(frame, center1, (int) radius2[0], new Scalar(0, 255, 0), 3);
//                center1=null;
//
//                System.out.println(center1);
//
//            }

//			//Close Hue Contures
//			for(Point fCenter : firstHuePoints){
//				if (fCenter!=null){
//					for(Point sCenter :secondHuePoints){
//						if(sCenter != null) {
//
//							if (fCenter!=null && sCenter!=null && Math.abs(fCenter.x - sCenter.x )<=80 && Math.abs(fCenter.y - sCenter.y) <= 80) {
//
//								Imgproc.circle(frame, fCenter, (int) radius[0], new Scalar(0, 0, 255), 5);
//
//								firstHuePoints = null;
//								fCenter = null;
//
//							}
//
//						}
//					}
//				}
//
//			}

            //Close Contours rework
            for (int arr = 0; arr < Objects.requireNonNull(firstHuePoints).length - 1; arr++) {
                if (firstHuePoints[arr] != null) {
                    for (int arr2 = 0; arr2 < secondHuePoints.length - 1; arr2++) {
                        if (firstHuePoints[arr] != null && secondHuePoints[arr2] != null &&
                                firstHuePoints[arr].x != 0.0 && secondHuePoints[arr2].x != 0.0 &&
//                                firstHuePoints[arr].x!=secondHuePoints[arr2].x &&
//                                firstHuePoints[arr].y!=secondHuePoints[arr2].y &&
                                Math.abs(firstHuePoints[arr].x - secondHuePoints[arr2].x) <= 60 &&
                                Math.abs(firstHuePoints[arr].y - secondHuePoints[arr2].y) <= 60) {

                            assert center != null;
//                            MainWindow.moveRED(center.x*resize,center.y*resize);
                            MainWindow.moveRED((firstHuePoints[arr].x + secondHuePoints[arr2].x) / 2 * resize, (firstHuePoints[arr].y + secondHuePoints[arr2].y) / 2 * resize);
                            Imgproc.circle(frame, firstHuePoints[arr], (int) radius[0], new Scalar(0, 0, 255), 2);

//                            Renderer.interactionHandler.imageProcessResult((float)(center.x*resize),(float) (center.y*resize));
                            Renderer.interactionHandler.imageProcessResult((float) ((firstHuePoints[arr].x + secondHuePoints[arr2].x) / 2 * resize), (float) ((firstHuePoints[arr].y + secondHuePoints[arr2].y) / 2 * resize));
                            firstHuePoints[arr] = null;
                            secondHuePoints[arr2] = null;
                            center = null;

                        }
                    }
                }
            }

            // display original frame from the video stream
            imgPanel1.setImage(Mat2BufferedImage(frame));

            // Show processed image
            if (firstHue == 0) {
                imgPanel2.setImage(Mat2BufferedImage(processedImage1));
            } else {
                imgPanel2.setImage(Mat2BufferedImage(processedImage1));
                imgPanel3.setImage(Mat2BufferedImage(processedImage2));
            }
            pack();

            iterator++;
            if (iterator == 36) {
                iterator = 0;
            }
            contours.clear();

            //Write unprocessed and processed frame successively to files
            // writeImage(frame, "unprocessedImage.png");
            // writeImage(processedImage, "processedImage.png");
        } // END for loop
        System.out.println(".(" + i + ")");

        cap.release();
    }

    /**
     * Converts an OpenCV matrix into a BufferedImage.
     * <p>
     * Inspired by
     * http://answers.opencv.org/question/10344/opencv-java-load-image-to-gui/
     * Fastest code
     * <p>
     * //@param OpenCV Matrix to be converted must be a one channel (grayscale) or
     * three channel (BGR) matrix, i.e. one or three byte(s) per pixel.
     *
     * @return converted image as BufferedImage
     */
    public BufferedImage Mat2BufferedImage(Mat imgMat) {
        int bufferedImageType = 0;
        switch (imgMat.channels()) {
            case 1:
                bufferedImageType = BufferedImage.TYPE_BYTE_GRAY;
                break;
            case 3:
                bufferedImageType = BufferedImage.TYPE_3BYTE_BGR;
                break;
            default:
                throw new IllegalArgumentException("Unknown matrix type. Only one byte per pixel (one channel) or three bytes pre pixel (three channels) are allowed.");
        }
        BufferedImage bufferedImage = new BufferedImage(imgMat.cols(), imgMat.rows(), bufferedImageType);
        final byte[] bufferedImageBuffer = ((DataBufferByte) bufferedImage.getRaster().getDataBuffer()).getData();
        imgMat.get(0, 0, bufferedImageBuffer);
        return bufferedImage;
    }

    public void initButton() {

        confirmButton = new JButton();
        confirmButton.setPreferredSize(new Dimension(90,30));
        confirmButton.setBackground(Color.RED);
        confirmButton.addActionListener(e -> actionConfirm(
        ));
        confirmButton.setText("Confirm");
    }

    public void actionConfirm() {
        if (firstHue == 0) {
            firstHue = hLow;
            firstSlow = sLow;
            firstSHigh = sHigh;
            System.out.println("First Hue: " + firstHue);
            System.out.println("First SatL: " + firstSlow);
            System.out.println("First SatH: " + firstSHigh);
        } else {
            secondHue = hLow;
            secondSlow = sLow;
            secondSHigh = sHigh;
            System.out.println("Second Hue: " + secondHue);
            System.out.println("Second SatL: " + secondSlow);
            System.out.println("Second SatH: " + secondSHigh);
        }
    }

    //Initialise Button
    public void initFlipButton() {
        flipButton = new JButton();
        flipButton.setPreferredSize(new Dimension(90,30));
        flipButton.addActionListener(e -> actionConfirm2(
        ));
        flipButton.setText("Flip");
    }

    //saving hue values when confirming
    public void actionConfirm2() {
        if (flip) {
            flip = false;
        } else {
            flip = true;
        }
    }

    //Initialise Button
    public void initResetButton() {
        resetButton = new JButton();
        resetButton.setPreferredSize(new Dimension(90,30));
        resetButton.addActionListener(e -> actionConfirm3(
        ));
        resetButton.setText("Reset");
    }

    //saving hue values when confirming
    public void actionConfirm3() {
        firstHue=0;
        secondHue=0;
    }

    public JSlider initHueSlider(JPanel panel) {
        int min = 1;
        int max = 160; //war mal 255
        int init = 10;

        //Erstellung Slider mit Position, Min, Max, Aktuell
        JSlider slider = new JSlider(JSlider.VERTICAL, min, max, init);
        Dimension d = slider.getPreferredSize();

        slider.setPreferredSize(new Dimension(d.width + 100, d.height + 100));
        slider.setMajorTickSpacing(50);
        slider.setMinorTickSpacing(25);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(e -> {
            JSlider source = (JSlider) e.getSource();
            hLow = (source.getValue());
            Color c = new Color(Color.HSBtoRGB(slider.getValue() / 165f, 1, 1));
            confirmButton.setBackground(c);
        });
        return slider;
    }

    public JSlider initSaturationSliderLow(JPanel panel) {
        int min = 1;
        int max = 255;
        int init = 1;
        //Erstellung Slider mit Position, Min, Max, Aktuell
        JSlider slider = new JSlider(JSlider.VERTICAL, min, max, init);
        Dimension d = slider.getPreferredSize();
        slider.setPreferredSize(new Dimension(d.width + 100, d.height + 100));
        slider.setMajorTickSpacing(50);
        slider.setMinorTickSpacing(25);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(e -> {
            JSlider source = (JSlider) e.getSource();
            if (firstHue == 0) {
                sLow = (source.getValue());
            } else {
                secondSlow = source.getValue();
                sLow = (source.getValue());
            }
        });
        return slider;
    }

    public JSlider initSaturationSliderHigh(JPanel panel) {
        int min = 1;
        int max = 255;
        int init = 255;
        //Erstellung Slider mit Position, Min, Max, Aktuell
        JSlider slider = new JSlider(JSlider.VERTICAL, min, max, init);
        Dimension d = slider.getPreferredSize();
        slider.setPreferredSize(new Dimension(d.width + 100, d.height + 100));
        slider.setMajorTickSpacing(50);
        slider.setMinorTickSpacing(25);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(e -> {
            JSlider source = (JSlider) e.getSource();
            sHigh = (source.getValue());

            if (firstHue == 0) {
                sHigh = (source.getValue());
            } else {
                secondSHigh = source.getValue();
                sHigh = (source.getValue());
            }

        });
        return slider;
    }


    public JButton getConfirmButton() {
        return confirmButton;
    }
}
