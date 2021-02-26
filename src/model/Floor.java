package model;

import transform.Point3D;

import java.util.ArrayList;
import java.util.List;

public class Floor extends Solid {

    public Floor(int x, int y){
        int n = x+1;
        int m = y+1;
        List<Point3D> vertexBuffer = new ArrayList<>();
        List<Integer> indexBuffer = new ArrayList<>();

        setColor(0xffffff);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                vertexBuffer.add(new Point3D(i,j,0));
            }

        }

        for (int i = 0; i < n; i++) {
            indexBuffer.add(i);
            indexBuffer.add(i + (n * (m - 1)));
        }
        for (int i = 0; i < m; i++) {
            indexBuffer.add(i * m);
            indexBuffer.add(i * m + n - 1);
        }
        setVertexBuffer(vertexBuffer);
        setIndexBuffer(indexBuffer);
    }

}
