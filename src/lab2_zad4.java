import java.util.Arrays;
import java.util.Scanner;
import java.util.LinkedList;


class ResizableArray <T>{
    private T [] elements;

    @SuppressWarnings("unchecked")
    public ResizableArray() {
        elements = (T []) new Object [0];
    }

    public void addElement(T element){
        elements = Arrays.copyOf(elements, elements.length+1);
        elements[elements.length-1] = element;
    }

    @SuppressWarnings("unchecked")
    public boolean removeElement(T element){
        int id = this.findId(element);
        if( id == -1)return false;

        // replaces undesired element with the last element
        elements[id] = elements[elements.length-1];
        elements= Arrays.copyOf(elements, elements.length-1);

        return true;

    }
    public int findId(T element){
        for(int i=0;i<elements.length;i++){
            if (elements[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    public boolean contains(T element){
        for(T elem : elements){
            if (element.equals(elem)) {
                return true;
            }
        }
        return false;
    }

    public Object [] toArray(){
        return elements;
    }

    public boolean isEmpty(){

        return elements.length==0;
    }

    public int count(){
        return elements.length;
    }

    public T elementAt(int idx){
        try {
            if(elements.length-1 >= idx){
                return elements[idx];
            }else throw new ArrayIndexOutOfBoundsException(elements.length);
        }catch (ArrayIndexOutOfBoundsException e){
            e.print();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static <T> void copyAll(ResizableArray<? super T> dest, ResizableArray<? extends T> src) {
        T [] arr = (T [])Arrays.copyOf(src.toArray(), src.count());
        for(T element : arr) {
            dest.addElement(element);
        }
    }

    public T[] getElements() {
        return elements;
    }

    public void setElements(T[] elements) {
        this.elements = elements;
    }
}

class IntegerArray extends ResizableArray<Integer>{
    public double sum(){
        double summation = 0;

        for(int i=0; i<count(); i++){
            summation += elementAt(i);
        }
        return summation;
    }
    public double mean(){
        return sum()/count();
    }

    public int countNonZero(){
        int ct=0;
        for(int i=0;i<count(); i++){
            if (elementAt(i) != 0) {
                ct++;
            }
        }
        return ct;
    }

    public IntegerArray distinct(){
        //no duplicates
        IntegerArray newarr = new IntegerArray();
        for(int i=0; i<count();i++){
            if(!newarr.contains(elementAt(i)))newarr.addElement(elementAt(i));
        }
        return newarr;
    }

    public IntegerArray increment(int offset){
        IntegerArray newarr = new IntegerArray();
        for(int i=0; i<count(); i++){
            newarr.addElement(elementAt(i)+offset);
        }
        return newarr;
    }

}


class ArrayIndexOutOfBoundsException extends RuntimeException {
    int size;

    public ArrayIndexOutOfBoundsException(int size) {
        this.size = size;
    }

    public void print() {
        System.out.println("елементите во полето се наоѓаат на позиции [0, "+(size-1)+"]");
    }
}



public class lab2_zad4 {
    public static void main(String[] args) {
        Scanner jin = new Scanner(System.in);
        int test = jin.nextInt();
        if ( test == 0 ) { //test ResizableArray on ints
            ResizableArray<Integer> a = new ResizableArray<Integer>();
            System.out.println(a.count());
            int first = jin.nextInt();
            a.addElement(first);
            System.out.println(a.count());
            int last = first;
            while ( jin.hasNextInt() ) {
                last = jin.nextInt();
                a.addElement(last);
            }
            System.out.println(a.count());
            System.out.println(a.contains(first));
            System.out.println(a.contains(last));
            System.out.println(a.removeElement(first));
            System.out.println(a.contains(first));
            System.out.println(a.count());
        }
        if ( test == 1 ) { //test ResizableArray on strings
            ResizableArray<String> a = new ResizableArray<String>();
            System.out.println(a.count());
            String first = jin.next();
            a.addElement(first);
            System.out.println(a.count());
            String last = first;
            for ( int i = 0 ; i < 4 ; ++i ) {
                last = jin.next();
                a.addElement(last);
            }
            System.out.println(a.count());
            System.out.println(a.contains(first));
            System.out.println(a.contains(last));
            System.out.println(a.removeElement(first));
            System.out.println(a.contains(first));
            System.out.println(a.count());
            ResizableArray<String> b = new ResizableArray<String>();
            ResizableArray.copyAll(b, a);
            System.out.println(b.count());
            System.out.println(a.count());
            System.out.println(a.contains(first));
            System.out.println(a.contains(last));
            System.out.println(b.contains(first));
            System.out.println(b.contains(last));
            ResizableArray.copyAll(b, a);
            System.out.println(b.count());
            System.out.println(a.count());
            System.out.println(a.contains(first));
            System.out.println(a.contains(last));
            System.out.println(b.contains(first));
            System.out.println(b.contains(last));
            System.out.println(b.removeElement(first));
            System.out.println(b.contains(first));
            System.out.println(b.removeElement(first));
            System.out.println(b.contains(first));

            System.out.println(a.removeElement(first));
            ResizableArray.copyAll(b, a);
            System.out.println(b.count());
            System.out.println(a.count());
            System.out.println(a.contains(first));
            System.out.println(a.contains(last));
            System.out.println(b.contains(first));
            System.out.println(b.contains(last));
        }
        if ( test == 2 ) { //test IntegerArray
            IntegerArray a = new IntegerArray();
            System.out.println(a.isEmpty());
            while ( jin.hasNextInt() ) {
                a.addElement(jin.nextInt());
            }
            jin.next();
            System.out.println(a.sum());
            System.out.println(a.mean());
            System.out.println(a.countNonZero());
            System.out.println(a.count());
            IntegerArray b = a.distinct();
            System.out.println(b.sum());
            IntegerArray c = a.increment(5);
            System.out.println(c.sum());
            if ( a.sum() > 100 )
                ResizableArray.copyAll(a, a);
            else
                ResizableArray.copyAll(a, b);
            System.out.println(a.sum());
            System.out.println(a.removeElement(jin.nextInt()));
            System.out.println(a.sum());
            System.out.println(a.removeElement(jin.nextInt()));
            System.out.println(a.sum());
            System.out.println(a.removeElement(jin.nextInt()));
            System.out.println(a.sum());
            System.out.println(a.contains(jin.nextInt()));
            System.out.println(a.contains(jin.nextInt()));
        }
        if ( test == 3 ) { //test insanely large arrays
            LinkedList<ResizableArray<Integer>> resizable_arrays = new LinkedList<ResizableArray<Integer>>();
            for ( int w = 0 ; w < 500 ; ++w ) {
                ResizableArray<Integer> a = new ResizableArray<Integer>();
                int k =  2000;
                int t =  1000;
                for ( int i = 0 ; i < k ; ++i ) {
                    a.addElement(i);
                }

                a.removeElement(0);
                for ( int i = 0 ; i < t ; ++i ) {
                    a.removeElement(k-i-1);
                }
                resizable_arrays.add(a);
            }
            System.out.println("You implementation finished in less then 3 seconds, well done!");
        }
    }

}

