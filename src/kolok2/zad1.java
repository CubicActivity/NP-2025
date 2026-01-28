package kolok2;//Implement a class Bucket that represents a “bucket” for storing objects in the cloud. Each object is defined by a key (e.g., photos/2024/12/image.jpg). The object key consists of a prefix (photos/2024/12) and the object/file name (e.g., image.jpg). The prefix defines a logical directory and subdirectory structure in which the object is located.
//
//For the class, implement the following:
//
//    constructor Bucket(String name)
//
//    method void addObject(String key) – adds a new object to the bucket with the given key. When adding objects, objects with the same prefixes are grouped (see example test cases)
//
//    method void removeObject(String key) – removes an existing object from the bucket with the given key
//
//        When removing an object, the corresponding prefixes must also be cleaned up (i.e., no prefixes without files should remain in the bucket)
//
//    method String toString() – prints information about all objects in the bucket. Indentation should be used when printing objects whose prefixes contain several parts delimited by /.


import java.util.*;
import java.util.stream.Collectors;

class Bucket {
    private String prefix;
    private ArrayList<String> objects;

    public Bucket(String prefix) {
        this.prefix = prefix;
        this.objects = new ArrayList<>();
    }

    public void addObject(String obj){
        objects.add(obj);
    }

    public void removeObject(String obj){
        objects.remove(obj);
    }

    @Override
    public String toString(){
        String res = prefix;
        int count = 1;
        if(objects.isEmpty()) res+='/';
        for(int i=0;i<objects.size();i++){
            for(int j=0; j<objects.get(i).length(); j++){
                if(objects.get(i).charAt(j) == '/' || j==0){
                    res+="/\n";
                    for(int l=0;l<count;l++)res+="    ";
                    count++;
                    if(j==0)res+=objects.get(i).charAt(j);
                    if(j==objects.get(i).length()-1)res+="\n";
                }else res+=objects.get(i).charAt(j);
                objects.get(i).contains(".");

            }

        }
        return res;
    }

}

public class zad1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // bucket name is fixed
        Bucket bucket = new Bucket("bucket");

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split("\\s+", 2);
            String command = parts[0];

            if (command.equalsIgnoreCase("ADD")) {
                bucket.addObject(parts[1]);
            } else if (command.equalsIgnoreCase("REMOVE")) {
                bucket.removeObject(parts[1]);
            } else if (command.equalsIgnoreCase("PRINT")) {
                System.out.print(bucket);
            }
        }
    }
}

