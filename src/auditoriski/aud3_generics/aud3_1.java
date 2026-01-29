package auditoriski.aud3_generics;


import java.util.ArrayList;
import java.util.Random;

class Box<T> {
    private ArrayList<T> items;

    public Box(){
        items = new ArrayList<>();
    }

    public void add(T item){
        items.add(item);
    }

    public boolean isEmpty(){
        return  items.size() == 0;
    }

    public T drawItem(){
        if(isEmpty())return null;
        Random random = new Random();
        return items.get(random.nextInt(items.size()));
    }
}


public class aud3_1 {
    public static void main (String[] args){
        Box<String> stringBox = new Box<>();
        stringBox.add("Dexter");
        stringBox.add("Batman");
        stringBox.add("Spiderman");
        stringBox.add("Ironman");
        stringBox.add("Robin");
        stringBox.add("Hank");
        System.out.println(stringBox.drawItem());
        Box<Integer> intBox = new Box<>();
        intBox.add(11);
        intBox.add(22);
        intBox.add(33);
        intBox.add(44);
        intBox.add(55);
        System.out.println(intBox.drawItem());
    }

}
