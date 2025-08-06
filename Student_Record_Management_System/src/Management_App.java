import java.util.ArrayList;
import java.util.Scanner;

public class Management_App {

    public static void main(String[] args) {
        ArrayList<Students_data> studentlist = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        boolean status = true;

        while (status) {
            System.out.print("1.ADD\n2.VIEW\n3.UPDATE\n4.DELETE\n5.EXIT\nEnter option : ");
            int n = sc.nextInt();
            switch (n) {
                case 1:
                    Students_data obj = new Students_data();
                    System.out.print("Enter Student id : ");
                    obj.setId(sc.nextInt());
                    System.out.print("Enter Student Name : ");
                    obj.setName(sc.next());
                    System.out.print("Enter Student Marks : ");
                    obj.setMarks(sc.nextFloat());
                    studentlist.add(obj);
                    System.out.println("Data Added Successfully");
                    break;

                case 2:
                    for (Students_data s : studentlist) {
                        System.out.println(s);
                    }
                    break;

                case 3:
                    System.out.print("Enter id of student to update : ");
                    int updateId = sc.nextInt();
                    boolean found = false;
                    for (Students_data s : studentlist) {
                        if (s.getId() == updateId) {
                            System.out.print("Enter new Name: ");
                            s.setName(sc.next());
                            System.out.print("Enter new Marks: ");
                            s.setMarks(sc.nextFloat());
                            System.out.println("Record Updated Successfully");
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Student with ID " + updateId + " not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter id of student to delete : ");
                    int deleteId = sc.nextInt();
                    found = false;
                    for (int i = 0; i < studentlist.size(); i++) {
                        if (studentlist.get(i).getId() == deleteId) {
                            studentlist.remove(i);
                            System.out.println("Record Deleted Successfully");
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Student with ID " + deleteId + " not found.");
                    }
                    break;

                case 5:
                    status = false;
                    System.out.println("Thank You!");
                    break;

                default:
                    System.out.println("Enter Valid Option");
                    break;
            }
        }
        sc.close();
    }
}
