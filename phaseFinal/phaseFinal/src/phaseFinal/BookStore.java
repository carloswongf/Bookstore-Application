package phaseFinal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Group 49
 */
public class BookStore {

    private static String bookFileName = "books.txt";
    ObservableList<books> books = FXCollections.observableArrayList();

    public BookStore(String list) {
        bookFileName = list;
    }

    public ObservableList<books> read() {
        try {
            BufferedReader reader = new BufferedReader(new java.io.FileReader("books.txt"));

            String line = reader.readLine();
            while (line != null) {
                String info[] = line.split(", ");
                books.add(new books(info[0], Double.parseDouble(info[1])));

                line = reader.readLine();
            }
            reader.close();

        } catch (Exception e) {
            System.out.println("Error");
        }
        return books;
    }

    public void write(String file, books newBook) {
        books.add(newBook);
        try {
            FileWriter fw = new FileWriter(bookFileName, true);
            fw.write(newBook.getBookName() + ", " + String.valueOf(newBook.getBookPrice()) + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    public void del(String file, books book) {
        File inFile = new File(file);
        File temp = new File("temp.txt");
        String line;

        BufferedReader br = null;
        FileWriter fw = null;
        FileReader fr = null;
        books.remove(book);
        try {
            br = new BufferedReader(new FileReader(inFile));
            fw = new FileWriter(temp, true);
            while ((line = br.readLine()) != null) {
                if (line.equals(book.getBookName() + ", " + String.valueOf(book.getBookPrice()))) {
                    System.out.println("Deleting");
                } else {
                    fw.write(line + "\n");
                    System.out.println("Printing");
                    System.out.println(book.getBookName() + ", " + String.valueOf(book.getBookPrice()));
                }
            }
            fw.close();
            br.close();

            inFile.delete();
            temp.renameTo(inFile);
            System.out.println(inFile.getName());
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}
