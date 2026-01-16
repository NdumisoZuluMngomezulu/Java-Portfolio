import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;

class PersonalAccount{
    String name;
    int account_no;
    private String pin;
    float balance;


    PersonalAccount(String igama, float bal){
        this.name = igama;
        float balance = bal;
    }
    
    public void set_account_no(){
    Random random = new Random();
    this.account_no = random.nextInt(101010101, 987654321);
    }
    
    public void set_pin(){
    Scanner takepin = new Scanner(System.in);
    System.out.println("Enter your pin. Pin must be 5 digits");
    boolean state = false;
    while (state = false){
        String pin_temp = takepin.nextLine();
        if (pin_temp.length() != 5){
            System.err.println("Invalid pin");
            state = false;
        } else {
            int check_count = 0;
            for (char c : pin_temp.toCharArray()){
                if (!Character.isDigit(c)){
                    check_count += 1;
                }
            }
            if (check_count > 0){
                System.err.println("Invalid pin. All digits must be characters");
                state = false;
            } else {
                this.pin = pin_temp;
                state = true;
            }
        }
    }
     
    takepin.close();
    }
    
    public void set_account_no(){
    Random random = new Random();
    this.account_no = random.nextInt(123456789, 987654321);
    }
    
    public void deposit(float amount){
        if (amount > 0){
            this.balance += amount;
        } else {
            System.err.println("Please enter valid amount");
        }
    }
    
    public float withdraw(){
        Scanner take_draw = new Scanner(System.in);
        System.out.println("Enter your pin");
        String pin_temp = takepin.nextLine();
        
        if (pin_temp != this.pin){
            System.err.println("Invalid pin");
            return 0.00
       } else {
           System.out.println("Enter withdrawal amount");
           float amount = take_draw.nextFloat();
           if (amount > this.balance){
               throw new ArithmeticException("Insufficient funds");
               
               return 0.00;
           }
           this.balance -= amount;
           return amount;
           
       }
       take_draw.close();
    }

    class SavingsAccount{
        float balance;
        int savingsAccountNumber;

        SavingsAccount(float bal){
            this.balance = bal;
        }

        public void setSavingsAccountNumber(){
            Random random = new Random();
            this.savingsAccountNumber = random.nextInt(10101010, 98765432);
        }

        public void deposit(float amount){
            this.balance += amount;
        }
    }

    public void transferToSavings(double amount, SavingsAccount targetSavings) {
        if (amount > 0 && this.balance >= amount) {
            this.balance -= amount; // Deduct from main
            targetSavings.deposit(amount); // Add to savings
            System.out.println("Transferred $" + amount + " from Main " + accountNumber + " to Savings " + targetSavings.savingsAccountNumber);
        } else {
            System.out.println("Transfer failed: Insufficient funds or invalid amount in main account.");
        }
    }
}

public class Accounts {
    public static void main(String[] args) {
        System.out.println("Try programiz.pro");
    }
}
