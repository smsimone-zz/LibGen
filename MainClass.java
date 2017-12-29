import java.util.Scanner;

public class MainClass {
    private final static String START_QUERY = "http://gen.lib.rus.ec/search.php?req=";
    private final static String END_QUERY =  "&lg_topic=libgen&open=0&view=simple&res=25&phrase=0&column=def";
    public static void main(String[] args){
        System.out.print("Scrivi il libro che ti interessa: ");
        String libro = new Scanner(System.in).nextLine().replace(" ", "+");
        Book[] books = GetBooks.scrapPage(START_QUERY+libro+END_QUERY);
        for(Book b : books){
            System.out.println(b+"\n");
        }
        System.out.print("Scrivi l'id del libro che ti interessa: ");
        String id = new Scanner(System.in).nextLine();
        Boolean found = false;
        int i=0;
        for(; i<books.length && !found; i++){
            if(books[i].getId().equals(id))
                break;
        }
        String[] mirrors = books[i].getMirrors();
        for(int f=0; f<mirrors.length; f++){
            System.out.println("["+f+"] "+ mirrors[f]);
        }
    }
}
