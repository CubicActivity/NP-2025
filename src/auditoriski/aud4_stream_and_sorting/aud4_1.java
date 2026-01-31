package auditoriski.aud4_stream_and_sorting;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Book{
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

}



class BookCollection{
    ArrayList<auditoriski.primer.Book> books;

    public BookCollection() {
        this.books = new ArrayList<>();
    }

    public void addBook(auditoriski.primer.Book book){
        books.add(book);
    }

    public void printByCategory(String category){
        String cat = category.toLowerCase();
        System.out.println("Collection for category "+category+":");
        books.stream().sorted(Comparator.comparing(auditoriski.primer.Book::getTitle).thenComparing(auditoriski.primer.Book::getPrice)).forEach(book -> {
            if(book.getCategory().toLowerCase().equals(cat)) System.out.print(book);
        });
        System.out.println();
    }

    public List getCheapestN(int N){
        List result = new ArrayList();
        System.out.println("Cheapest "+N+" books in collection:");
        books.stream().sorted(Comparator.comparing(auditoriski.primer.Book::getPrice)).limit(N).forEach(book -> {result.add(book);});
        return result;
    }
}


public class aud4_1 {
    public static void main(String[] args){
        auditoriski.primer.Book book1 = new auditoriski.primer.Book("Art of war", "Strategy", 20);
        auditoriski.primer.Book book2 = new auditoriski.primer.Book("Star wars vol1", "Sci-fi", 30);
        auditoriski.primer.Book book3 = new auditoriski.primer.Book("Star wars vol2", "Sci-fi", 25);
        auditoriski.primer.Book book4 = new auditoriski.primer.Book("ABC", "Sci-fi", 35);


        auditoriski.primer.BookCollection collection = new auditoriski.primer.BookCollection();
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
