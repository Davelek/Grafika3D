package model;

import transform.Mat4RotX;
import transform.Mat4RotY;
import transform.Point3D;

import java.util.Arrays;
import java.util.List;

public class Tetrahedron extends Solid {

    public Tetrahedron(){



    Integer[] indexes = {0, 1,0, 3,0, 2,1, 3,3, 2,1, 2};
    Point3D[] point3DS = {new Point3D(3, 0, 0),new Point3D(3, 0, -1),new Point3D(4, 0, 0),new Point3D(3, 1, 0)};
   /* vertexBuffer.add(new Point3D(0,0,0));
    vertexBuffer.add(new Point3D(0,0,1));
    vertexBuffer.add(new Point3D(1,0,0));
    vertexBuffer.add(new Point3D(0,1,0));
*/

   setVertexBuffer(Arrays.asList(point3DS));
   setIndexBuffer(Arrays.asList(indexes));
    // getIndexBuffer().addAll(Arrays.asList(ib));

    transform = new Mat4RotX(Math.PI/4).mul(new Mat4RotY(Math.PI/4));
   setTransform(transform);
    setColor(0xffffff);

/*
    indexBuffer.add(0);
    indexBuffer.add(1);
    indexBuffer.add(1);
    indexBuffer.add(2);*/
   /* indexBuffer.add(2);
    indexBuffer.add(3);
    indexBuffer.add(3);
    indexBuffer.add(4);
    indexBuffer.add(0);
    indexBuffer.add(2);
    indexBuffer.add(1);
    indexBuffer.add(3);*/


}

}





