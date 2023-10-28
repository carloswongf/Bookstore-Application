package phaseFinal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Group 49
 */
public class customer {

    private String username;
    private String password;
    private int points;
    protected status Status;

    final int rewardPoints = 10;
    final int payPoints = 100;

    public customer(String username, String password, int points) {
        this.username = username;
        this.password = password;
        this.points = points;

    }

    public ObservableList<books> selectedBooks(ObservableList<books> books) {
        ObservableList<books> selectBooks = FXCollections.observableArrayList();
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getSelect().isSelected()) {
                selectBooks.add(books.get(i));
            }
        }
        return selectBooks;
    }

    public double payCash(ObservableList<books> selectBooks) {
        double transactionCost = 0;

        System.out.println(selectBooks.size());
        for (int i = 0; i < selectBooks.size(); i++) {
            System.out.println(selectBooks.get(i).getBookPrice());
            transactionCost = transactionCost + selectBooks.get(i).getBookPrice();
        }
        points = points + (int) transactionCost * rewardPoints;
        return transactionCost;
    }

    public double payPoints(ObservableList<books> selectBooks) {
        double transactionCost = 0;

        System.out.println(selectBooks.size());
        for (int i = 0; i < selectBooks.size(); i++) {
            System.out.println(selectBooks.get(i).getBookPrice());
            transactionCost = transactionCost + selectBooks.get(i).getBookPrice();
        }

        if (points >= (int) transactionCost * payPoints) {
            points = points - (int) transactionCost * payPoints;
            transactionCost = 0;

        } else {
            transactionCost = transactionCost - points / 100;
            points = points % 100;
            points = points + (int) transactionCost * rewardPoints;
        }

        return transactionCost;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getStatus() {
        if (points < 1000) {
            Status = new silverMember();
            return "Silver";
        }
        else{
            Status = new goldMember();
        }
        return "Gold";
    }
}
