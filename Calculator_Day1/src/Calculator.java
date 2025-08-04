import java.util.Scanner;

interface Cal {
	int addition(int a, int b);

	int subtraction(int a, int b);

	int multiplication(int a, int b);

	int division(int a, int b);
}

public class Calculator implements Cal {

	@Override
	public int addition(int a, int b) {
		return a + b;
	}

	@Override
	public int subtraction(int a, int b) {
		return a - b;
	}

	@Override
	public int multiplication(int a, int b) {
		return a * b;
	}

	@Override
	public int division(int a, int b) {
		if (b == 0) {
			System.out.println("Can't divisible with Zero");
		}
		return a / b;
	}

	public static void main(String[] args) {
		Calculator obj = new Calculator();
		Scanner sc = new Scanner(System.in);
		boolean status = true;
		while (status) {
			System.out.print(
					"---MENU---\n1.Addition\n2.Subtraction\n3.Multiplication\n4.Division\n5.Exit\nEnter option(1-5) : ");
			int n = sc.nextInt();
			System.out.print("Enter a and b values : ");
			int a = sc.nextInt();
			int b = sc.nextInt();
			switch (n) {
			case 1:
				System.out.println("Addition : " + obj.addition(a, b));
				break;
			case 2:
				System.out.println("Subtraction : " + obj.subtraction(a, b));
				break;
			case 3:
				System.out.println("Multiplication : " + obj.multiplication(a, b));
				break;
			case 4:
				if (b == 0) {
					System.out.println("Can't divisible by Zero");
				} else {
					System.out.println("Division : " + obj.division(a, b));
				}
				break;
			case 5:
				System.out.println("Thank you!");
				status = false;
				break;
			default:
				System.out.println("Choose correct option between 1-5");

			}
		}
	}

}
