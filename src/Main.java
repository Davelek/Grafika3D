import model.Axis;
import model.Cube;
import model.Solid;
import model.Tetrahedron;
import renderer.Rasteriser;
import renderer.RasteriserLine;
import renderer.Renderer;
import transform.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.security.Key;
import java.util.ArrayList;

public class Main extends JFrame{
    private  int clickX,clickY,pohledX,pohledY;
    private Camera camera = new Camera().withAzimuth(-2.2).withPosition(new Vec3D(5,6,-5)).withZenith(0.5);
    private BufferedImage img;
    private JPanel panel;
    private Renderer renderer;
    private Mat4 solidTransform = new Mat4Identity();
    private Mat4 prespective = new Mat4Identity();
    private ArrayList<Solid> staticSolids = new ArrayList<>();
    private int klik = 0;




    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new Main(800, 600).start());
    }


    private Main(int width, int height){

        img = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        setLayout(new BorderLayout());
        setTitle("PGRF ukol 3");
     //   setFocusable(true);
       // requestFocusInWindow();
        setResizable(false);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        panel = new JPanel() {
            private static final long serialVersionUID = 1L;

            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                present(g);
            }
        };

        panel.setPreferredSize(new Dimension(width, height));


      // panel.requestFocus();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_W){
                  //  System.out.println("proč najednou nefunguješwwww");
                    camera = camera.forward(0.5);
                    draw();
                }
                if (e.getKeyCode() == KeyEvent.VK_S){
                    camera = camera.backward(0.5);
                    draw();
                }
                if (e.getKeyCode() == KeyEvent.VK_A){
                    camera = camera.left(0.5);
                    draw();
                }
                if (e.getKeyCode() == KeyEvent.VK_D){
                    camera = camera.right(0.5);
                    draw();
                }
                if (e.getKeyCode() == KeyEvent.VK_ADD){
                    camera = camera.up(0.5);
                    draw();
                }
                if (e.getKeyCode() == KeyEvent.VK_SUBTRACT){
                    camera = camera.down(0.5);
                    draw();
                }
                if (e.getKeyCode() == KeyEvent.VK_Q || e.getKeyCode() == KeyEvent.VK_LEFT){
                    camera = camera.addAzimuth(0.1);
                    draw();
                }
                if (e.getKeyCode() == KeyEvent.VK_UP){
                    camera = camera.addZenith(0.1);
                    draw();
                }

                if (e.getKeyCode() == KeyEvent.VK_DOWN){
                    camera = camera.addZenith(-0.1);
                    draw();
                }

                if (e.getKeyCode() == KeyEvent.VK_E || e.getKeyCode() == KeyEvent.VK_RIGHT){
                    camera = camera.addAzimuth(-0.1);
                    draw();
                }

                if (e.getKeyCode() == KeyEvent.VK_R){
                    camera = new Camera().withAzimuth(-2.2).withPosition(new Vec3D(5,6,-5)).withZenith(0.5);
                    draw();
                }


            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1){


                clickX = e.getX();
                clickY = e.getY();
                klik = 1;

                }
                if (e.getButton() == MouseEvent.BUTTON3){
                    pohledX = e.getX();
                    pohledY = e.getY();
                    klik = 2;



                }

            }
            @Override
            public void mouseReleased(MouseEvent e) {
                klik = 0;
                System.out.println("klik");
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent f) {

                if (klik == 1){


                for (Solid solid :staticSolids
                ) {
                    if (solid.isTransform())
                    solid.setTransform(new Mat4RotXYZ(0,-(f.getY()-clickY)*(0.02),-(clickX - f.getX())*0.02));
                    draw();
                }
            }if (klik == 2){
                    double azi,zeni;

                    camera = camera.addAzimuth((f.getX()-pohledX)*(0.002)).addZenith((f.getY()-pohledY)*(0.002));
                    pohledX = f.getX();
                    pohledY = f.getY();
                   /* if (pohledX > f.getX()){

                   //camera = camera.addAzimuth((0.01));
                        azi = 0.01;
                    }else {
                //        camera = camera.addAzimuth((-0.01));
                        azi = -0.01;
                    }
                    if (pohledY>f.getY()){
                 //       camera = camera.addZenith(-0.01);
                        zeni = -0.01;
                    }else {
                  //      camera = camera.addZenith(0.01);
                        zeni = 0.01;
                    }
                    camera = camera.addAzimuth(azi).addZenith(zeni);*/
                   draw();
                  /*  pohledX = f.getX();
                    pohledY = f.getY();*/
                }
            }


        });
        add(panel);
        setVisible(true);
        pack();

    }

    private void start() {
        renderer = new Renderer(new RasteriserLine(img));

        solidTransform = new Mat4RotX(Math.PI / 4).mul(new Mat4RotY(-Math.PI / 4)).mul(new Mat4Transl(2, 2, 0));
       // prespective = new Mat4PerspRH(0.7853981633974483D, (double) ((float) this.img.getHeight() / (float) this.img.getWidth()), 0.01D, 100.0D);
        prespective = new Mat4PerspRH(Math.PI/3, 4/3.0, 0.01, 25);
        Mat4 view = new Mat4ViewRH(new Vec3D(0, 10, 10), new Vec3D(-1, -1, -1), new Vec3D(0, 1, 0));

        renderer.setProj(prespective);
        renderer.setView(view);
        staticSolids.add(new Axis("x"));
        staticSolids.add(new Axis("y"));
        staticSolids.add(new Axis("z"));
        staticSolids.add(new Tetrahedron());
        staticSolids.add(new Cube());
       // addStaticSolid();
        draw();
        panel.repaint();
    }







    private void present(Graphics graphics) {
        graphics.drawImage(img, 0, 0, null);
    }

    private void draw() {

        clear();
        renderer.setView(camera.getViewMatrix());
     //   cube.setTrans(solidTransform);
      //  renderer.draw(cube);

        renderer.draw(staticSolids);

        panel.repaint();

    }





    private void clear() {
        Graphics gr = img.getGraphics();
        gr.setColor(new Color(0x2f2f2f));
        gr.fillRect(0, 0, img.getWidth(), img.getHeight());

    }

}
