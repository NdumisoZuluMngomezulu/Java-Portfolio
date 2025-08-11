//Exercises
import java.util.Scanner;
class Exercises {
    public static void main(String[] args) {
        System.out.println(calculator(2.0, 3.0));

    }

    public static double calculator(double a, double b) {
        double answer = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which operation do you want to perform? ");
        String operation = scanner.nextLine();
        
        switch (operation) {
            case "sum":
              answer = a + b;
              break;
            case "subtract":
              answer = a - b;
              break;
            case "multiply":
              answer =  a / b;
              break;
            case "divide":
              answer =  a * b;
              break;
            default :
                System.out.println("Operation unsupported.");
        }
        scanner.close();

        return answer;
    }
    
}

