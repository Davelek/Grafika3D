package renderer;

import model.Solid;
import transform.Mat4;
import transform.Mat4Identity;
import transform.Point3D;
import transform.Vec3D;

import java.util.ArrayList;
import java.util.List;

public class Renderer {


    private Mat4 view = new Mat4Identity();
    private Mat4 proj = new Mat4Identity();
    private List<Point3D> points = new ArrayList<>();
    private Rasteriser rasteriser;


    public Renderer(Rasteriser rasteriser) {
        this.rasteriser = rasteriser;
    }

    public void setRasteriser(Rasteriser rasteriser) {
        this.rasteriser = rasteriser;
    }

    public void setView(Mat4 view) {
        this.view = view;
    }

    public void setProj(Mat4 proj) {
        this.proj = proj;
    }

    /*public void drawSolid(Solid solid){
        Mat4 transform = solid.getTransform().mul(view).mul(proj);
        for (Point3D solidHelp:solid.getVertexBuffer()
        ) {
            points.add(solidHelp.mul(transform));
        }

        for (int i = 0; i < solid.getIndexBuffer().size()-1; i=i+2) {
            int i1 = solid.getIndexBuffer().get(i);
            int i2 = solid.getIndexBuffer().get(i+1);
            renderLine(points.get(i1),points.get(i2));
        }

    }*/


    private void renderLine(Point3D a, Point3D b, int color){
        //ořezání v homohennich souradnicich
        Vec3D va ;
        Vec3D vb ;
        if(!a.dehomog().isPresent() || !b.dehomog().isPresent())
            return;
        va = a.dehomog().get();
        vb = b.dehomog().get();

        double x1 = va.getX();
        double y1 = va.getY();
        double x2 = vb.getX();
        double y2 = vb.getY();


        rasteriser.drawLine(x1,y1,x2,y2, color);

    }

    public void draw(List<Solid> solids){
        solids.forEach(this::draw);
    }
    public void draw(Solid solid) {
        List<Point3D> vertexes = new ArrayList<>();
        Mat4 transform = solid.getTransform().mul(view).mul(proj);
        for (Point3D point : solid.getVertexBuffer()) {
            vertexes.add(point.mul(transform));
        }
        List<Integer> indexBuffer = solid.getIndexBuffer();
        for (int i = 0; i <= indexBuffer.size() - 1; i = i + 2) {
            Point3D a = vertexes.get(indexBuffer.get(i));
            Point3D b = vertexes.get(indexBuffer.get(i + 1));
            renderLine(a, b, solid.getColor());
        }
    }

}
