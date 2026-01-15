import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;

class Personal_Account{
    String name;
    int account_no;
    private String pin;
    float balance;

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
        if ((pin_temp.length() > 5) || (pin_temp.length < 5)){
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
    
  public void withdraw(float amount){
        Scanner take_draw = new Scanner(System.in);
        System.out.println("Enter your pin");
        String pin_temp = takepin.nextLine();
        
        if ((pin_temp.length() > 5) || (pin_temp.length < 5)){
            System.err.println("Invalid pin");
        take_draw.close();
    }
}

public class Accounts {
    public static void main(String[] args) {
        System.out.println("Try programiz.pro");
    }
}
