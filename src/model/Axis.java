package model;

import transform.Point3D;

import java.awt.*;
import java.util.Arrays;

public class Axis extends Solid {


        public Axis(String type){

            switch (type){
                case "x":
                    Point3D[] vertexesForX = {new Point3D(0, 0, 0),
                            new Point3D(1, 0, 0),
                            new Point3D(0.5, 0.1, 0),
                            new Point3D(0.5, -0.1, 0),
                            new Point3D(0.5, 0, 0.1),
                            new Point3D(0.5, 0, -0.1)
                    };
                    Integer[] indexesForX = {0, 1,1, 2,1, 3,1, 4,1, 5,5, 3,2,4,4, 3,5, 2};

                    setVertexBuffer(Arrays.asList(vertexesForX));
                    setIndexBuffer(Arrays.asList(indexesForX));
                    setTransform(false);
                    setColor(0xFF0000);
                    break;
                case "y":
                    Point3D[] vertexesForY = {new Point3D(0, 0, 0),
                            new Point3D(0, 1, 0),
                            new Point3D(0.1, 0.5, 0),
                            new Point3D(-0.1, 0.5, 0),
                            new Point3D(0, 0.5, 0.1),
                            new Point3D(0, 0.5, -0.1)
                    };
                    Integer[] indexesForY = {0,1,1,2,1,3,1,4,1,5,5,3,2,4,4,3,5,2};
                    setVertexBuffer(Arrays.asList(vertexesForY));
                    setIndexBuffer(Arrays.asList(indexesForY));
                    setTransform(false);
                    setColor(0x00FF00);
                    break;
                case "z":
                    Point3D[] vertexes = {new Point3D(0, 0, 0),
                            new Point3D(0, 0, -1),
                            new Point3D(0.1, 0, -0.5),
                            new Point3D(-0.1, 0, -0.5),
                            new Point3D(0, 0.1, -0.5),
                            new Point3D(0, -0.1, -0.5)
                    };
                    Integer[] indexes = {0,1,1,2,1,3,1,4,1,5,5,3,2,4,4,3,5,2};
                    setVertexBuffer(Arrays.asList(vertexes));
                    setIndexBuffer(Arrays.asList(indexes));
                    setTransform(false);
                    setColor(0x0000FF);
                    break;
            }

        }




}
