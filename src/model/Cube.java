package model;

import transform.Point3D;

import java.util.Arrays;

public class Cube extends Solid{

    public Cube(){
        Point3D[] point3DS = {new Point3D(-1, -1, 0),
                new Point3D(-1, -1, -2),
                new Point3D(1, -1, 0),
                new Point3D(-1, 1, 0),
                new Point3D(1, 1, 0),
                new Point3D(1, -1, -2),
                new Point3D(-1, 1, -2),
                new Point3D(1, 1, -2),
        };
        setVertexBuffer(Arrays.asList(point3DS));
        Integer[] indexes = {
                0, 1,
                0, 3,
                0, 2,
                3, 6,
                3, 4,
                2, 4,
                2, 5,
                1, 6,
                1, 5,
                5, 7,
                6, 7,
                4, 7,
        };

    setVertexBuffer(Arrays.asList(point3DS));
    setIndexBuffer(Arrays.asList(indexes));
    setColor(0xFF00FF);
}
}
