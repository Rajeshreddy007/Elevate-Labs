import java.sql.*;
import java.util.Scanner;

public class Bank_App {

	private static Connection connect;

	public static void main(String[] args) throws SQLException {
		try {
			connect = JDBC_Util.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}

		Scanner sc = new Scanner(System.in);
		boolean mainMenu = true;

		while (mainMenu) {
			System.out.println("\n--- Welcome to Bank Management System ---");
			System.out.println("1. Create New Bank Account");
			System.out.println("2. Login");
			System.out.println("3. Exit");
			System.out.print("Enter option: ");
			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				createAccount(sc);
				break;
			case 2:
				login(sc);
				break;
			case 3:
				System.out.println("Thank you for using Bank Management System.");
				mainMenu = false;
				break;
			default:
				System.out.println("Invalid choice. Try again.");
			}
		}

		sc.close();
		connect.close();
	}

	// ---------------- CREATE ACCOUNT ----------------
	private static void createAccount(Scanner sc) throws SQLException {
		sc.nextLine();

		System.out.print("Enter name: ");
		String name = sc.nextLine();

		System.out.print("Enter Age: ");
		int age = sc.nextInt();

		System.out.print("Enter initial Deposit Amount: ");
		double amount = sc.nextDouble();

		sc.nextLine();
		System.out.print("Enter password: ");
		String password = sc.nextLine();

		String sql = "INSERT INTO customer (name, age, amount, password) VALUES (?, ?, ?, ?)";
		PreparedStatement pst = connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		pst.setString(1, name);
		pst.setInt(2, age);
		pst.setDouble(3, amount);
		pst.setString(4, password);

		int result = pst.executeUpdate();
		if (result > 0) {
			ResultSet keys = pst.getGeneratedKeys();
			if (keys.next()) {
				int accountId = keys.getInt(1);
				System.out.println("Account created successfully! Please save your Account Number: " + accountId);
			}
		} else {
			System.out.println("Unable to create account.");
		}
	}

	// ---------------- LOGIN ----------------
	private static void login(Scanner sc) throws SQLException {
		System.out.print("Enter Account Number: ");
		int acc = sc.nextInt();
		sc.nextLine();

		System.out.print("Enter password: ");
		String pass = sc.nextLine();

		String sql = "SELECT * FROM customer WHERE account_id = ? AND password = ?";
		PreparedStatement pst = connect.prepareStatement(sql);
		pst.setInt(1, acc);
		pst.setString(2, pass);
		ResultSet rs = pst.executeQuery();

		if (rs.next()) {
			System.out.println("Login successful! Welcome, " + rs.getString("name"));
			accountMenu(sc, acc);
		} else {
			System.out.println("Invalid account number or password.");
		}
	}

	// ---------------- ACCOUNT MENU ----------------
	private static void accountMenu(Scanner sc, int acc) throws SQLException {
		boolean loggedIn = true;

		while (loggedIn) {
			System.out.println("\n--- Account Menu ---");
			System.out.println("1. View Balance");
			System.out.println("2. Deposit");
			System.out.println("3. Withdraw");
			System.out.println("4. Logout");
			System.out.print("Enter option: ");
			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				viewBalance(acc);
				break;
			case 2:
				deposit(sc, acc);
				break;
			case 3:
				withdraw(sc, acc);
				break;
			case 4:
				System.out.println("Logging out...");
				loggedIn = false;
				break;
			default:
				System.out.println("Invalid choice. Try again.");
			}
		}
	}

	// ---------------- VIEW BALANCE ----------------
	private static void viewBalance(int acc) throws SQLException {
		String sql = "SELECT amount FROM customer WHERE account_id = ?";
		PreparedStatement pst = connect.prepareStatement(sql);
		pst.setInt(1, acc);
		ResultSet rs = pst.executeQuery();

		if (rs.next()) {
			System.out.println("Current Balance: ₹" + rs.getDouble("amount"));
		} else {
			System.out.println("Account not found.");
		}
	}

	// ---------------- DEPOSIT ----------------
	private static void deposit(Scanner sc, int acc) throws SQLException {
		System.out.print("Enter deposit amount: ");
		double amount = sc.nextDouble();

		String sql = "UPDATE customer SET amount = amount + ? WHERE account_id = ?";
		PreparedStatement pst = connect.prepareStatement(sql);
		pst.setDouble(1, amount);
		pst.setInt(2, acc);
		int updated = pst.executeUpdate();

		if (updated > 0) {
			System.out.println("Deposit successful!");
			viewBalance(acc);
		} else {
			System.out.println("Deposit failed.");
		}
	}

	// ---------------- WITHDRAW ----------------
	private static void withdraw(Scanner sc, int acc) throws SQLException {
		System.out.print("Enter withdrawal amount: ");
		double amount = sc.nextDouble();

		String checkSql = "SELECT amount FROM customer WHERE account_id = ?";
		PreparedStatement checkPst = connect.prepareStatement(checkSql);
		checkPst.setInt(1, acc);
		ResultSet rs = checkPst.executeQuery();

		if (rs.next()) {
			double currentBalance = rs.getDouble("amount");
			if (amount > currentBalance) {
				System.out.println("Insufficient balance.");
				return;
			}
		}

		String sql = "UPDATE customer SET amount = amount - ? WHERE account_id = ?";
		PreparedStatement pst = connect.prepareStatement(sql);
		pst.setDouble(1, amount);
		pst.setInt(2, acc);
		int updated = pst.executeUpdate();

		if (updated > 0) {
			System.out.println("Withdrawal successful!");
			viewBalance(acc);
		} else {
			System.out.println("Withdrawal failed.");
		}
	}
}
