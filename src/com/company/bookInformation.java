package com.company;

public class bookInformation {
    private String bookName;
    private String bookAuthor;
    private int ISBN;
    private String genre;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public int getISBNnum() {
        return ISBN;
    }

    public void setISBNnum(int ISBNnum) {
        this.ISBN = ISBNnum;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public bookInformation(String bookName, String bookAuthor, int ISBN, String genre) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.ISBN = ISBN;
        this.genre = genre;
    }
    @Override
    public String toString() {
        return bookName + ", " + bookAuthor + ", " + ISBN + ", " + genre;
    }
}
