package phaseFinal;

/**
 *
 * @author Group 49
 */
import javafx.scene.control.CheckBox;

public class books {

    private String bookName;
    private double bookPrice;
    private CheckBox select;

    public books(String bookName, double bookPrice) {
        this.bookName = bookName;
        this.bookPrice = bookPrice;

        select = new CheckBox();
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public CheckBox getSelect() {
        return select;
    }

    public void setSelect(CheckBox select) {
        this.select = select;
    }
}
