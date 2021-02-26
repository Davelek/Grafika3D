package renderer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;

public class RasteriserLine implements Rasteriser {

    BufferedImage img;


    public RasteriserLine(BufferedImage img) {
        this.img = img;
    }

    @Override
    public void drawLine(double x1, double y1, double x2, double y2, int color) {
        Graphics gr = img.getGraphics();
        double xH1,yH1;
        xH1 = pocitej(x1,img.getWidth());
        yH1 = pocitej(y1,img.getHeight());
        double xH2,yH2;
        xH2 = pocitej(x2,img.getWidth());
        yH2 = pocitej(y2,img.getHeight());




        gr.setColor(new Color(color));
        gr.drawLine((int)xH1,(int)yH1,(int)xH2,(int)yH2);
    }

    private double pocitej(double i, int second){

        return (i+1)/2*(second-1);

    }
}
