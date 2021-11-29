import java.sql.*;
import java.util.Scanner;

public class CrudJava {
    public static void main(String[] args) {
        try {
            // use Scanner Class to get input from the user
            Scanner s = new Scanner(System.in);
            // define the url
            String url = "jdbc:mysql://localhost:3306/java_demo";
            String user = "root";
            String password = "";
            // make connection
            Connection mycon = DriverManager.getConnection(url, user, password);
            // create a statement
            Statement mystate = mycon.createStatement();

            // create a loop
            while (true) {
                System.out
                        .print("Enter your Choice Insert (1) Show (2) ShowById (3) Update(4) Delete (5) Search (6) > ");
                int choice = s.nextInt();
                switch (choice) {
                // insert data
                case 1:
                    System.out.print("Enter the name of student : ");
                    String name = s.next();
                    System.out.print("Enter the age of student : ");
                    int age = s.nextInt();
                    String sql = "INSERT INTO students (name,age) VALUES ('" + name + "', " + age + ") ";

                    var success = mystate.executeUpdate(sql);
                    if (success == 1) {
                        System.out.println("Data add Successfully");
                    } else {
                        System.out.println("Data Not add Successfully");
                    }
                    break;
                // show data
                case 2:
                    String select_sql = "SELECT * FROM students ORDER BY id DESC";
                    ResultSet rs = mystate.executeQuery(select_sql);
                    while (rs.next()) {
                        System.out.println(
                                rs.getString("id") + "  " + rs.getString("name") + "   " + rs.getString("age"));
                    }
                    break;

                // edit data
                case 3:
                    System.out.print("Enter the id of student : ");
                    int id = s.nextInt();
                    String show_by_id = "SELECT * FROM students WHERE id=" + id;
                    ResultSet show = mystate.executeQuery(show_by_id);
                    while (show.next()) {
                        System.out.println(
                                show.getString("id") + "  " + show.getString("name") + "   " + show.getString("age"));
                    }
                    break;

                // update data
                case 4:
                    System.out.print("Enter the id of student : ");
                    int uid = s.nextInt();
                    System.out.print("Enter the name of student : ");
                    String update_name = s.next();
                    System.out.print("Enter the age of student : ");
                    int update_age = s.nextInt();
                    String update_sql = "UPDATE students SET name = '" + update_name + "',age=" + update_age
                            + " WHERE id=" + uid;
                    mystate.executeUpdate(update_sql);
                    System.out.println("Data Update Successfully");
                    break;

                // Delete data
                case 5:
                    System.out.print("Enter the id of student : ");
                    int did = s.nextInt();
                    String delete_by_id = "DELETE FROM students WHERE id=" + did;
                    mystate.executeUpdate(delete_by_id);
                    System.out.println("Data Delete Successfully");
                    break;

                // search data

                case 6:
                    System.out.print("Enter the name of student : ");
                    String search_name = s.next();
                    String search = "SELECT * FROM students WHERE name LIKE '%" + search_name + "%'";
                    ResultSet search_data = mystate.executeQuery(search);
                    while (search_data.next()) {
                        System.out.println(search_data.getString("id") + "  " + search_data.getString("name") + "   "
                                + search_data.getString("age"));
                    }
                    break;
                }
            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }
}