import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

class Canvas{
    String canvas_id;
    int [] canvasSize;
    int total_squares_perimeter=0;

    public Canvas(String canvas_id) {
        this.canvas_id = canvas_id;
        this.canvasSize = new int[0];
    }

    public void addSize(int c){
        canvasSize = Arrays.copyOf(canvasSize, canvasSize.length+1);
        canvasSize[canvasSize.length-1] = c;
    }

    public String getCanvas_id() {
        return canvas_id;
    }

    public int[] getCanvasSizes() {
        return canvasSize;
    }

    public int calculate(){
        total_squares_perimeter=0;
        for(int i=0;i<canvasSize.length;i++){
            total_squares_perimeter += canvasSize[i];
        }
        return total_squares_perimeter*4;
    }
}

class ShapesApplication{

    Canvas [] canvases;

    public ShapesApplication() {
        canvases = new Canvas[0];
    }

    public void addCanvas(Canvas c){
        canvases = Arrays.copyOf(canvases, canvases.length+1);
        canvases[canvases.length-1] = c;
    }

    public int readCanvases (InputStream inputStream) throws IOException {
        Scanner r = new Scanner(System.in);
        int counter=0;
        while(r.hasNextLine()) {
            String[] parts = r.nextLine().split(" ");
            if(parts.length<2)break;
            String canvasId = parts[0];
            Canvas temp = new Canvas(canvasId);
            for(int i=1; i< parts.length;i++){
                temp.addSize(Integer.parseInt(parts[i]));
                counter++;
            }
            this.addCanvas(temp);

        }
        return counter;
    }

    public void printLargestCanvasTo(OutputStream os) throws IOException {
        Canvas largest = new Canvas("");

        for(int i=0;i<canvases.length;i++){
            if(canvases[i].calculate()>largest.calculate())largest=canvases[i];
        }

        System.out.println((largest.canvas_id+" "+largest.canvasSize.length+" "+largest.calculate()));
//        os.write();
    }

}
public class Vezhbi_1 {

    public static void main(String[] args) throws IOException {
        ShapesApplication shapesApplication = new ShapesApplication();

        System.out.println("===READING SQUARES FROM INPUT STREAM===");
        System.out.println(shapesApplication.readCanvases(System.in));
        System.out.println("===PRINTING LARGEST CANVAS TO OUTPUT STREAM===");
        shapesApplication.printLargestCanvasTo(System.out);

    }
}