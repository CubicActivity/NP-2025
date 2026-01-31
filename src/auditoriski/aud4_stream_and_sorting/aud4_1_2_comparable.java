package auditoriski.aud4_stream_and_sorting;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

class Book implements Comparable{
    private String title;
    private String category;
    private float price;

    public Book(String title, String category, float price) {
        this.title = title;
        this.category = category;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public float getPrice() {
        return price;
    }

    @Override
    public String toString(){
        return title + " ("+ category+ ") "+price +'\n';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Float.compare(price, book.price) == 0 && Objects.equals(category, book.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, price);
    }

    @Override
    public int compareTo(Object o) {
        return Integer.compare(hashCode(), o.hashCode());
    }
}



class BookCollection{
    ArrayList<Book> books;

    public BookCollection() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book){
        books.add(book);
    }

    public void printByCategory(String category){
        String cat = category.toLowerCase();
        System.out.println("Collection for category "+category+":");
        books.stream().sorted(Comparator.comparing(Book::getTitle).thenComparing(Book::getPrice)).forEach(book -> {
            if(book.getCategory().toLowerCase().equals(cat)) System.out.print(book);
        });
        System.out.println();
    }

    public List getCheapestN(int N){
        List result = new ArrayList();
        System.out.println("Cheapest "+N+" books in collection:");
        books.stream().sorted(Comparator.comparing(Book::getPrice)).limit(N).forEach(book -> {result.add(book);});
        return result;
    }
}


public class aud4_1_2_comparable {
    public static void main(String[] args){
        Book book1 = new Book("Art of war", "Strategy", 20);
        Book book2 = new Book("Star wars vol1", "Sci-fi", 30);
        Book book3 = new Book("Star wars vol2", "Sci-fi", 25);
        Book book4 = new Book("ABC", "Sci-fi", 35);


        BookCollection collection = new BookCollection();
        collection.addBook(book1);
        collection.addBook(book2);
        collection.addBook(book3);
        collection.addBook(book4);

        collection.printByCategory("Sci-fi");


        List cheapest = collection.getCheapestN(2);
        for(int i=0; i<cheapest.size();i++){
            System.out.print(cheapest.get(i));
        }

    }


}
