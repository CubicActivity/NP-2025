import java.util.Arrays;
import java.util.Scanner;

enum TYPE {
    POINT,
    CIRCLE
}

enum DIRECTION {
    UP,
    DOWN,
    LEFT,
    RIGHT
}

interface Movable{
    public void moveUp();
    public void moveDown();
    public void moveRight();
    public void moveLeft();

    public int getCurrentXPosition();
    public int getCurrentYPosition();
}


class MovablesCollection{
    Movable[] movables;
    static int xMax, yMax; //min is 0

    public MovablesCollection(int xMax, int yMax) {
        setxMax(xMax);
        setyMax(yMax);
        movables = new Movable[0];
    }

    public static void setxMax(int xMax) {
        MovablesCollection.xMax = xMax;
    }

    public static void setyMax(int yMax) {
        MovablesCollection.yMax = yMax;
    }


    public void addMovableObject(Movable m){
        try {
            if (m instanceof MovablePoint) {
                MovablePoint mp = (MovablePoint) m;
                if (mp.x >= 0 && mp.x <= xMax && mp.y >= 0 && mp.y <= yMax) {
                    movables = Arrays.copyOf(movables, movables.length + 1);
                    movables[movables.length - 1] = m;
                } else {
                    throw new MovableObjectNotFittableException(m);
                }
            } else if (m instanceof MovableCircle) {
                MovableCircle mc = (MovableCircle) m;
                if (mc.centerPoint.x - mc.radius >= 0 && mc.centerPoint.x + mc.radius <= xMax
                        && mc.centerPoint.y - mc.radius >= 0 && mc.centerPoint.y + mc.radius <= yMax) {
                    movables = Arrays.copyOf(movables, movables.length + 1);
                    movables[movables.length - 1] = m;
                }else{
                    throw new MovableObjectNotFittableException(m);
                }
            }
        }catch (MovableObjectNotFittableException e){
            e.print();
        }
    }

    public void moveObjectsFromTypeWithDirection(TYPE t, DIRECTION d){
        for(Movable m : movables){
            if(t == TYPE.POINT){
                if(m instanceof MovablePoint){
                    if(d == DIRECTION.UP) m.moveUp();
                    else if(d== DIRECTION.DOWN) m.moveDown();
                    else if(d == DIRECTION.RIGHT) m.moveRight();
                    else if(d == DIRECTION.LEFT) m.moveLeft();
                }
            }else if(t == TYPE.CIRCLE){
                if(m instanceof MovableCircle) {
                    if (d == DIRECTION.UP) ((MovableCircle) m).centerPoint.moveUp();
                    else if (d == DIRECTION.DOWN) ((MovableCircle) m).centerPoint.moveDown();
                    else if (d == DIRECTION.RIGHT) ((MovableCircle) m).centerPoint.moveRight();
                    else if (d == DIRECTION.LEFT) ((MovableCircle) m).centerPoint.moveLeft();
                }
            }
        }
    }

    @Override
    public String toString() {
        String res= "Collection of movable objects with size " + movables.length+":\n";

        for(int i=0; i<movables.length; i++){
            res+=movables[i].toString();
            if(i+1<movables.length)res+="\n";
        }
        res+="\n";
        return res;
    }
}

class MovableObjectNotFittableException extends RuntimeException {
    Movable m;
    public MovableObjectNotFittableException(Movable m){
        this.m=m;
    }
    public void print() {
        if(m instanceof MovableCircle){
            System.out.println("Movable circle with center ("+((MovableCircle) m).centerPoint.x + ","+((MovableCircle) m).centerPoint.y+") and radius "+((MovableCircle) m).radius+" can not be fitted into the collection");
        }

    }
}

class ObjectCanNotBeMovedException extends RuntimeException {
    Movable m;
    public ObjectCanNotBeMovedException(Movable m){
        this.m = m;
    }
    public void print() {
        if(m instanceof MovablePoint){
            System.out.println("Point ("+((MovablePoint) m).x+","+((MovablePoint) m).y+") is out of bounds");
        }
    }
}




class MovablePoint implements Movable{
    int x,y;
    int xSpeed,ySpeed;


    public MovablePoint(int x, int y, int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    @Override
    public void moveUp() {
        try{
            y+=ySpeed;
            if(y> MovablesCollection.yMax)throw new ObjectCanNotBeMovedException(this);
        }catch (ObjectCanNotBeMovedException e){
            e.print();
            y-=ySpeed;
        }
    }

    @Override
    public void moveDown() {
        try {
            y -= ySpeed;
            if (y < 0)throw new ObjectCanNotBeMovedException(this);
        }catch (ObjectCanNotBeMovedException e){
            e.print();
            y += ySpeed;
        }
    }

    @Override
    public void moveRight() {
        try {
            x += xSpeed;
            if (x > MovablesCollection.xMax)throw new ObjectCanNotBeMovedException(this);
        }catch (ObjectCanNotBeMovedException e){
            e.print();
            x -= xSpeed;
        }
    }

    @Override
    public void moveLeft() {
        try {
            x -= xSpeed;
            if(x <0) {
                throw new ObjectCanNotBeMovedException(this);
            }
        }catch (ObjectCanNotBeMovedException e){
            e.print();
            x+= xSpeed;
        }
    }

    @Override
    public int getCurrentXPosition() {
        return x;
    }

    @Override
    public int getCurrentYPosition() {
        return y;
    }

    @Override
    public String toString() {
        return "Movable point with coordinates (" + x +"," + y +")";
    }
}


class MovableCircle implements Movable{
    int radius;
    MovablePoint centerPoint;

    public MovableCircle(int radius, MovablePoint centerPoint) {
        this.radius = radius;
        this.centerPoint = centerPoint;
    }

    @Override
    public void moveUp() {
        try{
            centerPoint.y+=centerPoint.ySpeed;
            if(centerPoint.y> MovablesCollection.yMax)throw new ObjectCanNotBeMovedException(this);
        }catch (ObjectCanNotBeMovedException e){
            e.print();
            centerPoint.y-=centerPoint.ySpeed;
        }
    }

    @Override
    public void moveDown() {
        try {
            centerPoint.y -= centerPoint.ySpeed;
            if (centerPoint.y < 0)throw new ObjectCanNotBeMovedException(this);
        }catch (ObjectCanNotBeMovedException e){
            e.print();
            centerPoint.y += centerPoint.ySpeed;
        }
    }

    @Override
    public void moveRight() {
        try {
            centerPoint.x += centerPoint.xSpeed;
            if (centerPoint.x > MovablesCollection.xMax)throw new ObjectCanNotBeMovedException(this);
        }catch (ObjectCanNotBeMovedException e){
            e.print();
            centerPoint.x -= centerPoint.xSpeed;
        }
    }

    @Override
    public void moveLeft() {
        try {
            centerPoint.x -= centerPoint.xSpeed;
            if(centerPoint.x <0) {
                throw new ObjectCanNotBeMovedException(this);
            }
        }catch (ObjectCanNotBeMovedException e){
            e.print();
            centerPoint.x+= centerPoint.xSpeed;
        }
    }

    @Override
    public int getCurrentXPosition() {
        return this.centerPoint.getCurrentXPosition();
    }

    @Override
    public int getCurrentYPosition() {
        return this.centerPoint.getCurrentYPosition();
    }

    @Override
    public String toString() {
        return "Movable circle with center coordinates ("+this.centerPoint.x+","+this.centerPoint.y +") and radius " + radius;
    }
}


public class lab1_zad2 {

    public static void main(String[] args) {

        System.out.println("===COLLECTION CONSTRUCTOR AND ADD METHOD TEST===");
        MovablesCollection collection = new MovablesCollection(100, 100);
        Scanner sc = new Scanner(System.in);
        int samples = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < samples; i++) {
            String inputLine = sc.nextLine();
            String[] parts = inputLine.split(" ");

            int x = Integer.parseInt(parts[1]);
            int y = Integer.parseInt(parts[2]);
            int xSpeed = Integer.parseInt(parts[3]);
            int ySpeed = Integer.parseInt(parts[4]);

            if (Integer.parseInt(parts[0]) == 0) { //point
                collection.addMovableObject(new MovablePoint(x, y, xSpeed, ySpeed));
            } else { //circle
                int radius = Integer.parseInt(parts[5]);
                collection.addMovableObject(new MovableCircle(radius, new MovablePoint(x, y, xSpeed, ySpeed)));
            }

        }
        System.out.println(collection.toString());

        System.out.println("MOVE POINTS TO THE LEFT");
        collection.moveObjectsFromTypeWithDirection(TYPE.POINT, DIRECTION.LEFT);
        System.out.println(collection.toString());

        System.out.println("MOVE CIRCLES DOWN");
        collection.moveObjectsFromTypeWithDirection(TYPE.CIRCLE, DIRECTION.DOWN);
        System.out.println(collection.toString());

        System.out.println("CHANGE X_MAX AND Y_MAX");
        MovablesCollection.setxMax(90);
        MovablesCollection.setyMax(90);

        System.out.println("MOVE POINTS TO THE RIGHT");
        collection.moveObjectsFromTypeWithDirection(TYPE.POINT, DIRECTION.RIGHT);
        System.out.println(collection.toString());

        System.out.println("MOVE CIRCLES UP");
        collection.moveObjectsFromTypeWithDirection(TYPE.CIRCLE, DIRECTION.UP);
        System.out.println(collection.toString());


    }


}
