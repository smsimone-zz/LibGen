public class Book {
    private String id;
    private String author;
    private String title;
    private String publisher;
    private String year;
    private String pages;
    private String language;
    private String size;
    private String extension;
    private String[] mirrors;

    public String toString(){
        String book= "Id: " + id +
                "\nAuthor: " + author +
                "\nTitle: " + title +
                "\nPublisher: " + publisher +
                "\nYear: " + year +
                "\nPages: " + pages +
                "\nLanguage: " + language +
                "\nSize: " + size +
                "\nExtension: " + extension;
        return book;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPages() { return pages; }

    public void setPages(String pages) { this.pages = pages; }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String[] getMirrors() {
        return mirrors;
    }

    public void setMirrors(String[] mirrors) {
        this.mirrors = mirrors;
    }
}
