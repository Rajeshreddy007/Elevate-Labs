import java.util.ArrayList;
import java.util.Scanner;

interface Functions {
	void returnBook(ArrayList<Book> bookslist, ArrayList<Library> library, int userId, int bookId);
	void issueBook(ArrayList<Book> bookslist, ArrayList<Library> library, User user, int bookId);
}

public class Main_App implements Functions {

	@Override
	public void issueBook(ArrayList<Book> bookslist, ArrayList<Library> library, User user, int bookId) {
		for (Book book : bookslist) {
			if (book.getBookid() == bookId && book.isStatus()) {
				book.setStatus(false);

				Library entry = new Library();
				entry.setBookid(book.getBookid());
				entry.setTittle(book.getTittle());
				entry.setAuthor(book.getAuthor());
				entry.setUid(user.getUid());
				entry.setName(user.getName());

				library.add(entry);
				System.out.println("--- Book issued successfully to " + user.getName() + " ---");
				return;
			}
		}
		System.out.println("Book not available or invalid Book ID.");
	}

	@Override
	public void returnBook(ArrayList<Book> bookslist, ArrayList<Library> library, int userId, int bookId) {
		for (int i = 0; i < library.size(); i++) {
			Library entry = library.get(i);
			if (entry.getBookid() == bookId && entry.getUid() == userId) {
				for (Book book : bookslist) {
					if (book.getBookid() == bookId) {
						book.setStatus(true);
						break;
					}
				}
				library.remove(i);
				System.out.println("--- Book returned successfully ---");
				return;
			}
		}
		System.out.println("No such issued book found for return.");
	}

	public static void main(String[] args) {
		Main_App app = new Main_App();
		ArrayList<Book> bookslist = new ArrayList<>();
		ArrayList<User> Userslist = new ArrayList<>();
		ArrayList<Library> library = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		boolean status1 = true;

		while (status1) {
			System.out.print("\n1. Admin\n2. User\n3. Exit\nEnter option: ");
			int n = sc.nextInt();
			sc.nextLine(); // Consume newline
			switch (n) {
			case 1:
				boolean status2 = true;
				while (status2) {
					System.out.print("\n1. Add Book\n2. View Books\n3. View Issued Books\n4. Exit\nEnter option: ");
					int m = sc.nextInt();
					sc.nextLine(); // Consume newline
					switch (m) {
					case 1:
						Book bobj = new Book();
						System.out.print("Enter Book ID: ");
						bobj.setBookid(sc.nextInt());
						sc.nextLine(); // Consume newline

						System.out.print("Enter Book Title: ");
						bobj.setTittle(sc.nextLine());

						System.out.print("Enter Book Author: ");
						bobj.setAuthor(sc.nextLine());

						bobj.setStatus(true);
						bookslist.add(bobj);
						System.out.println("--- Book added successfully ---");
						break;
					case 2:
						System.out.println("--- Book List ---");
						for (Book book : bookslist) {
							System.out.println(book);
						}
						break;
					case 3:
						System.out.println("--- Issued Books ---");
						for (Library issuedbook : library) {
							System.out.println(issuedbook);
						}
						break;
					case 4:
						status2 = false;
						System.out.println("--- Returning to Main Menu ---");
						break;
					default:
						System.out.println("Choose correct option!");
					}
				}
				break;

			case 2:
				User uobj = new User();
				System.out.print("Enter User ID: ");
				uobj.setUid(sc.nextInt());
				sc.nextLine(); // Consume newline

				System.out.print("Enter User Name: ");
				uobj.setName(sc.nextLine());

				Userslist.add(uobj);

				boolean userMenu = true;
				while (userMenu) {
					System.out.print("\n1. View Available Books\n2. Take Book\n3. Return Book\n4. Exit\nEnter option: ");
					int option = sc.nextInt();
					switch (option) {
					case 1:
						System.out.println("--- Available Books ---");
						for (Book book : bookslist) {
							if (book.isStatus()) {
								System.out.println(book);
							}
						}
						break;
					case 2:
						System.out.print("Enter Book ID to issue: ");
						int bookIdToIssue = sc.nextInt();
						app.issueBook(bookslist, library, uobj, bookIdToIssue);
						break;
					case 3:
						System.out.print("Enter Book ID to return: ");
						int bookIdToReturn = sc.nextInt();
						app.returnBook(bookslist, library, uobj.getUid(), bookIdToReturn);
						break;
					case 4:
						userMenu = false;
						System.out.println("--- Returning to Main Menu ---");
						break;
					default:
						System.out.println("Invalid option. Try again.");
					}
				}
				break;

			case 3:
				status1 = false;
				System.out.println("Exiting Application...");
				break;

			default:
				System.out.println("Enter valid option.");
			}
		}

		sc.close();
	}
}
