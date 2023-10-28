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
 * @author author Group 49
 */
public class owner {

    private static String customersFileName = "customers.txt";
    ObservableList<customer> customers = FXCollections.observableArrayList();

    public boolean verify(String user, String pw) {
        boolean verification = false;
        String username, password;
        int points;
        customer c1, current;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("customers.txt"));

            String line = reader.readLine();
            while (line != null) {
                String info[] = line.split(", ");
                username = info[0];
                password = info[1];
                points = Integer.parseInt(info[2]);
                c1 = new customer(username, password, points);
                if ((user.equals(username)) && (pw.equals(password))) {
                    verification = true;
                    current = c1;
                    System.out.println(current.getUsername());
                    System.out.println(current.getPassword());
                    System.out.println(current.getPoints());
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("User doesn't exist");
        }
        return verification;

    }

    public void modifyCustomers(String oldString, String newString) {
        File fileToBeModified = new File("customers.txt");

        String oldContent = "";

        BufferedReader reader = null;

        FileWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(fileToBeModified));

            //Reading all the lines of input text file into oldContent
            String line = reader.readLine();

            while (line != null) {
                oldContent = oldContent + line + System.lineSeparator();

                line = reader.readLine();
            }

            //Replacing oldString with newString in the oldContent
            String newContent = oldContent.replaceAll(oldString, newString);

            //Rewriting the input text file with newContent
            writer = new FileWriter(fileToBeModified);

            writer.write(newContent);
        } catch (IOException e) {
            e.printStackTrace();
        } 
            try {
                //Closing the resources

                reader.close();

                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        
    }

    public owner(String list) {
        customersFileName = list;
    }

    public void write(String file, customer newCustomer) {
        customers.add(newCustomer);
        try {
            FileWriter fw = new FileWriter(customersFileName, true);
            fw.write(newCustomer.getUsername() + ", " + newCustomer.getPassword() + ", " + newCustomer.getPoints() + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    public ObservableList<customer> getUser() {
        String username, password;
        int points;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("customers.txt"));

            String line = reader.readLine();
            while (line != null) {
                String info[] = line.split(", ");
                username = info[0];
                password = info[1];
                points = Integer.parseInt(info[2]);

                customers.add(new customer(username, password, points));
                line = reader.readLine();
            }
            reader.close();

        } catch (Exception e) {
            System.out.println("Invalid");
        }

        return customers;
    }

    public void del(String file, customer customer) {
        File inFile = new File(file);
        File temp = new File("temp.txt");
        String line;

        BufferedReader br = null;
        FileWriter fw = null;
        FileReader fr = null;
        customers.remove(customer);
        try {
            br = new BufferedReader(new FileReader(inFile));
            fw = new FileWriter(temp, true);
            while ((line = br.readLine()) != null) {
                if (line.equals(customer.getUsername() + ", " + customer.getPassword() + ", " + customer.getPoints())) {
                    System.out.println("Deleting");
                } else {
                    fw.write(line + "\n");
                    System.out.println(customer.getUsername() + ", " + customer.getPassword() + ", " + customer.getPoints() + "\n");
                }
            }
            fw.close();
            br.close();

            inFile.delete();
            temp.renameTo(inFile);
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}
