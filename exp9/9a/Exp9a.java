import java.util.Scanner;

class InvalidNumber extends Exception {
    InvalidNumber(String message) {
        super(message);
    }
}

class MathFunctions {
    float getMean(int arr[]) throws InvalidNumber {
        int sum = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 0 || arr[i] > 100) {
                throw new InvalidNumber("Invalid number found: " + arr[i]);
            }
            sum += arr[i];
        }

        return (float) sum / arr.length;
    }

    float divideNum(int a, int b) {
        return (float) a / b;
    }
}

class Exp9a {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MathFunctions obj = new MathFunctions();

        System.out.println("1. Mean Calculation");
        System.out.println("2. Division");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();

        try {
            if (choice == 1) {
                System.out.print("Enter size of array: ");
                int n = sc.nextInt();

                int arr[] = new int[n];

                System.out.println("Enter array elements:");
                for (int i = 0; i < n; i++) {
                    arr[i] = sc.nextInt();
                }

                float mean = obj.getMean(arr);
                System.out.println("Mean = " + mean);

            } else if (choice == 2) {
                System.out.print("Enter first number: ");
                int num1 = sc.nextInt();

                System.out.print("Enter second number: ");
                int num2 = sc.nextInt();

                if (num2 == 0) {
                    throw new ArithmeticException("Cannot divide by zero");
                }

                float result = obj.divideNum(num1, num2);
                System.out.println("Result = " + result);

            } else {
                System.out.println("Invalid choice");
            }

        } catch (InvalidNumber e) {
            System.out.println("Custom Exception: " + e.getMessage());
        } catch (ArithmeticException e) {
            System.out.println("Built-in Exception: " + e.getMessage());
        }

        sc.close();
    }
}