package model;

import transform.Mat4;
import transform.Mat4Identity;
import transform.Point3D;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Solid {


    protected List<Point3D> vertexBuffer = new ArrayList<>();
    protected List<Integer> indexBuffer = new ArrayList<>();
    protected Mat4 transform = new Mat4Identity();
    protected int color = 0xffffff;
    protected boolean isTransform = true;

    public void setTransform(boolean transform) {
        isTransform = transform;
    }

    public boolean isTransform() {
        return isTransform;
    }

    public Solid(){


    }

    public void setVertexBuffer(List<Point3D> vertexBuffer) {
        this.vertexBuffer = vertexBuffer;
    }

    public void setIndexBuffer(List<Integer> indexBuffer) {
        this.indexBuffer = indexBuffer;
    }

    public void setTransform(Mat4 transform) {
        this.transform = transform;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public List<Point3D> getVertexBuffer() {
        return vertexBuffer;
    }

    public List<Integer> getIndexBuffer() {
        return indexBuffer;
    }

    public Mat4 getTransform() {
        return transform;
    }

    public int getColor() {
        return color;
    }
}
