import org.apache.commons.lang3.StringUtils;
import java.net.*;
import java.io.*;

public class GetBooks {
    private static final String START_BOOKS = "<tr valign=top bgcolor=#C6DEFF>";
    private static final String BOOK_HEADER = "<tr valign=top bgcolor=";
    private static int instances = 0;
    private static String html="";
    private static String bookHtml="";
    private static Book[] books;

    private static void getInstances(){
        instances = StringUtils.countMatches(html, BOOK_HEADER);
        createBooks();
    }

    private static void createBooks(){
        books = new Book[instances+1];
        for(int i=0; i<=instances; i++){
            Book b = new Book();
            bookHtml = html.substring(0,html.indexOf("</tr>"));
            b.setId(bookHtml.substring(bookHtml.indexOf("<td>")+4, bookHtml.indexOf("</td>")));
            bookHtml = cropHtml(bookHtml);
            b.setAuthor(bookHtml.substring(bookHtml.indexOf("author'>")+8,bookHtml.indexOf("</a>")));
            bookHtml = cropHtml(bookHtml);
            bookHtml = bookHtml.substring(bookHtml.indexOf("id="+b.getId()+">")+(4+b.getId().length()));
            b.setTitle(bookHtml.substring(0,bookHtml.indexOf("<")));
            bookHtml = cropHtml(bookHtml);
            b.setPublisher(bookHtml.substring(bookHtml.indexOf("<td>")+4, bookHtml.indexOf("</td>")));
            bookHtml = cropHtml(bookHtml);
            b.setYear(bookHtml.substring(bookHtml.indexOf("<td nowrap>")+11, bookHtml.indexOf("</td>")));
            bookHtml = cropHtml(bookHtml);
            b.setPages(bookHtml.substring(bookHtml.indexOf("<td>")+4, bookHtml.indexOf("</td>")));
            bookHtml = cropHtml(bookHtml);
            b.setLanguage(bookHtml.substring(bookHtml.indexOf("<td nowrap>")+11, bookHtml.indexOf("</td>")));
            bookHtml = cropHtml(bookHtml);
            b.setSize(bookHtml.substring(bookHtml.indexOf("<td nowrap>")+11, bookHtml.indexOf("</td>")));
            bookHtml = cropHtml(bookHtml);
            b.setExtension(bookHtml.substring(bookHtml.indexOf("<td nowrap>")+11, bookHtml.indexOf("</td>")));
            bookHtml = cropHtml(bookHtml).trim();
            try {
                String[] mirrors = new String[StringUtils.countMatches(bookHtml, "href='http") - 1];
                for (int links = 0; links < mirrors.length; links++) {
                    String mirror;
                    if(bookHtml.startsWith("<td><a style='color: grey'"))
                        mirror = "-----DELETED-----";
                    else
                        mirror = bookHtml.substring(bookHtml.indexOf("href='http") + 6, bookHtml.indexOf("' title='"));
                    mirrors[links] = mirror;
                    bookHtml = cropHtml(bookHtml);
                }
                b.setMirrors(mirrors);
            }catch(Exception e){
                System.out.println(bookHtml);
                System.exit(0);
            }
            books[i] = b;
            html = html.substring(html.indexOf(BOOK_HEADER)+ BOOK_HEADER.length());
        }
    }

    private static String cropHtml(String string){
        string = string.substring(string.indexOf("</td>")+5);
        return string;
    }

    static Book[] scrapPage(String link){
        String thisHtml = "";
        try{
            URL url = new URL(link);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String inputLine;
            while((inputLine = reader.readLine()) != null) {
                thisHtml += inputLine;
            }
            reader.close();
        }catch(MalformedURLException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        html = thisHtml.substring(thisHtml.indexOf(START_BOOKS) + START_BOOKS.length());
        getInstances();
        return books;
    }
}
