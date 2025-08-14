import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Employees {

    public static void main(String[] args) throws SQLException {
        Connection connect = JDBC_Util.getConnection();
        Scanner sc = new Scanner(System.in);
        boolean status = true;

        while (status) {
            System.out.println("\n---Employee Menu---");
            System.out.print("1. Add Employee\n2. View Employee\n3. Delete Employee\n4. Exit\nEnter Option: ");
            int n = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (n) {
                case 1:
                    Add(connect, sc);
                    break;
                case 2:
                    view(connect, sc);
                    break;
                case 3:
                    delete(connect, sc);
                    break;
                case 4:
                    status = false;
                    System.out.println("Thank You!");
                    break;
                default:
                    System.out.println("Please Enter Valid Input");
                    break;
            }
        }
        sc.close();
        connect.close();
    }

    private static void delete(Connection connect, Scanner sc) throws SQLException {
        System.out.print("Enter Employee ID to delete: ");
        int id = sc.nextInt();
        sc.nextLine();

        String sql = "DELETE FROM employees WHERE empid = ?";
        PreparedStatement pst = connect.prepareStatement(sql);
        pst.setInt(1, id);

        int result = pst.executeUpdate();
        if (result > 0) {
            System.out.println("Employee Deleted Successfully");
        } else {
            System.out.println("Employee Not Found");
        }
    }

    private static void view(Connection connect, Scanner sc) throws SQLException {
        boolean viewStatus = true;
        while (viewStatus) {
            System.out.print("\n1. All Employees\n2. One Employee\n3. Exit View\nEnter Option: ");
            int n = sc.nextInt();
            sc.nextLine();

            switch (n) {
                case 1:
                    String sqlAll = "SELECT * FROM employees";
                    PreparedStatement pstAll = connect.prepareStatement(sqlAll);
                    ResultSet rsAll = pstAll.executeQuery();

                    System.out.println("\nID | Name | Gender | Department | Salary | City");
                    while (rsAll.next()) {
                        System.out.println(
                                rsAll.getInt("empid") + " | " +
                                rsAll.getString("name") + " | " +
                                rsAll.getString("gender") + " | " +
                                rsAll.getString("department") + " | " +
                                rsAll.getInt("salary") + " | " +
                                rsAll.getString("city")
                        );
                    }
                    break;

                case 2:
                    System.out.print("Enter Employee ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    String sqlOne = "SELECT * FROM employees WHERE empid = ?";
                    PreparedStatement pstOne = connect.prepareStatement(sqlOne);
                    pstOne.setInt(1, id);
                    ResultSet rsOne = pstOne.executeQuery();
                    System.out.println("\nID | Name | Gender | Department | Salary | City");
                    if (rsOne.next()) {
                        System.out.println(
                                rsOne.getInt("empid") + " | " +
                                rsOne.getString("name") + " | " +
                                rsOne.getString("gender") + " | " +
                                rsOne.getString("department") + " | " +
                                rsOne.getInt("salary") + " | " +
                                rsOne.getString("city")
                        );
                    } else {
                        System.out.println("Employee Not Found");
                    }
                    break;

                case 3:
                    viewStatus = false;
                    break;

                default:
                    System.out.println("Please Enter Valid Input");
                    break;
            }
        }
    }

    private static void Add(Connection connect, Scanner sc) throws SQLException {
        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter gender: ");
        String gender = sc.nextLine();

        System.out.print("Enter department: ");
        String department = sc.nextLine();

        System.out.print("Enter Salary: ");
        int salary = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter City: ");
        String city = sc.nextLine();

        String sql = "INSERT INTO employees (name, gender, department, salary, city) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pst = connect.prepareStatement(sql);
        pst.setString(1, name);
        pst.setString(2, gender);
        pst.setString(3, department);
        pst.setInt(4, salary);
        pst.setString(5, city);

        int result = pst.executeUpdate();
        if (result > 0) {
            System.out.println("Data Inserted Successfully");
        } else {
            System.out.println("Data Insertion Failed, Please try again later");
        }
    }
}
