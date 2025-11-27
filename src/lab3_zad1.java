import java.io.*;
import java.util.*;

class Movie{
    String title;
    String genre;
    int year;
    double avgRating;

    public Movie(String title, String genre, int year, double avgRating) {
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.avgRating = avgRating;
    }
    @Override
    public String toString() {
        return String.format(title + ", " + genre + ", "+ year +", %.2f", avgRating);
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getYear() {
        return year;
    }

    public double getAvgRating() {
        return avgRating;
    }
}

class MovieTheater{
    ArrayList<Movie> movies;

    public MovieTheater() {
        movies= new ArrayList<>();
    }

    public void readMovies(InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        int n = Integer.parseInt(reader.readLine());
        for(int i=0;i<n;i++){
            String name = reader.readLine();
            String genre = reader.readLine();
            int year = Integer.parseInt(reader.readLine());
            String grades = reader.readLine();

            double avgRating=0;
            String [] parts = grades.split(" ");
            for(int j=0; j< parts.length; j++){
                avgRating+= Integer.parseInt(parts[j]);
            }
            avgRating/=parts.length;

            Movie mv = new Movie(name,genre,year,avgRating);
            movies.add(mv);
        }
    }

    public void printByGenreAndTitle(){
        movies.stream().sorted(Comparator
                .comparing(Movie::getGenre)
                .thenComparing(Movie::getTitle))
                .forEach(System.out::println);
    }

    public void printByYearAndTitle(){
        movies.stream().sorted(Comparator
                .comparing(Movie::getYear)
                .thenComparing(Movie::getTitle))
                .forEach(System.out::println);
    }

    public void printByRatingAndTitle(){
        movies.stream().sorted(Comparator
                .comparing(Movie::getAvgRating).reversed()
                .thenComparing(Movie::getTitle))
                .forEach(System.out::println);
    }

}

public class lab3_zad1 {
    public static void main(String[] args) {
        MovieTheater mt = new MovieTheater();
        try {
            mt.readMovies(System.in);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("SORTING BY RATING");
        mt.printByRatingAndTitle();
        System.out.println("\nSORTING BY GENRE");
        mt.printByGenreAndTitle();
        System.out.println("\nSORTING BY YEAR");
        mt.printByYearAndTitle();
    }
}