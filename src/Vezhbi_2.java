import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Canvas2{
    String canvasId;
    int [] canvasSquareSizes;
    char [] canvasSquareTypes;
    double squaresArea;

    int totalCircles=0;
    int totalSquares=0;

    double min_area=0;
    double max_area=0;
    double average_area=0;

    public Canvas2(String canvasId) {
        this.canvasId = canvasId;
        this.canvasSquareSizes = new int[0];
        this.canvasSquareTypes = new char[0];
    }

    public void addSize(char t, int c){
        canvasSquareSizes = Arrays.copyOf(canvasSquareSizes, canvasSquareSizes.length+1);
        canvasSquareSizes[canvasSquareSizes.length-1]=c;

        canvasSquareTypes = Arrays.copyOf(canvasSquareTypes, canvasSquareTypes.length+1);
        canvasSquareTypes[canvasSquareTypes.length-1]=t;
    }

    public double Calculate(){
        squaresArea=0;
        average_area=0;
        min_area=0;
        max_area=0;
        totalCircles=0;
        totalSquares=0;
        for(int i=0;i<canvasSquareSizes.length;i++){
            if(canvasSquareTypes[i]=='S') {
                squaresArea += canvasSquareSizes[i] * canvasSquareSizes[i];
                totalSquares++;

                if(canvasSquareSizes[i] * canvasSquareSizes[i]>max_area)max_area=canvasSquareSizes[i] * canvasSquareSizes[i];
                if(canvasSquareSizes[i] * canvasSquareSizes[i]<min_area || min_area==0)min_area=canvasSquareSizes[i] * canvasSquareSizes[i];
                average_area+=canvasSquareSizes[i] * canvasSquareSizes[i];

            }else {
                squaresArea += canvasSquareSizes[i]*canvasSquareSizes[i]*Math.PI;
                totalCircles++;

                if(canvasSquareSizes[i]*canvasSquareSizes[i]*Math.PI > max_area) max_area=canvasSquareSizes[i]*canvasSquareSizes[i]*Math.PI;
                if(canvasSquareSizes[i]*canvasSquareSizes[i]*Math.PI < min_area || min_area==0) min_area= canvasSquareSizes[i]*canvasSquareSizes[i]*Math.PI;
                average_area+=canvasSquareSizes[i]*canvasSquareSizes[i]*Math.PI;
            }
        }
        average_area /= (totalSquares+totalCircles);
        return squaresArea;
    }

    @Override
    public String toString() {
        double refresh = Calculate();
        return String.format("%s %d %d %d %.2f %.2f %.2f", canvasId, canvasSquareSizes.length,  totalCircles, totalSquares, min_area, max_area, average_area);
    }
}

class ShapesApplication2{
    double maxArea;
    ArrayList<Canvas2> canvases;
    public ShapesApplication2(double maxArea){
        this.maxArea=maxArea;
        canvases= new ArrayList<>();
    }

    public void add(Canvas2 c){
        canvases.add(c);
    }

    public void readCanvases (InputStream is) {
        Scanner sc = new Scanner(is);

        while (sc.hasNextLine()) {
            String[] parts = sc.nextLine().split(" ");

            if (parts.length < 2) break;
            String id = parts[0];
            Canvas2 c = new Canvas2(id);
            try {
                for (int i = 1; i < parts.length; i += 2) {
                    if (Integer.parseInt(parts[i + 1])*Integer.parseInt(parts[i + 1]) > maxArea) throw new IrregularCanvasException(c, maxArea);
                    else {
                        c.addSize(parts[i].charAt(0), Integer.parseInt(parts[i + 1]));
                    }
                }
                add(c);
            } catch (IrregularCanvasException e) {
                e.print();
            }
        }
    }

    public void printCanvases(OutputStream os){
        canvases.stream().sorted(Comparator.comparing(Canvas2::Calculate).reversed()).forEach(System.out::println);
    }
}

class IrregularCanvasException extends RuntimeException{
    Canvas2 c;
    double maxArea;
    public IrregularCanvasException(Canvas2 c, double maxArea){
        this.c=c;
        this.maxArea = maxArea;
    }
    public void print(){
        System.out.printf("Canvas %s has a shape with area larger than %.2f%n",c.canvasId,maxArea);
    }
}

public class Vezhbi_2 {

    public static void main(String[] args) {

        ShapesApplication2 shapesApplication = new ShapesApplication2(10000);

        System.out.println("===READING CANVASES AND SHAPES FROM INPUT STREAM===");
        shapesApplication.readCanvases(System.in);

        System.out.println("===PRINTING SORTED CANVASES TO OUTPUT STREAM===");
        shapesApplication.printCanvases(System.out);


    }
}