import java.util.HashMap;
import java,util.Map;

public class Kata_Cash{
    public static void main(String[] args){
        
    }
    
    public static HashMap<Integer, String> change(int due, int paid){
        int change_due = due - paid;
        if (change_due < 0){
            throw new ArithmeticException("Error: Amount paid insufficient");
        }
        
        Map<Integer, String> Denominations = new HashMap<>(){{
           put(20000, "Two Hundred");
           put(10000, "Hundred");
           put(5000, "Fifty");
           put(2000, "Twenty");
           put(1000, "Ten");
           put(500, "Five");
           put(200, "Two");
           put(100, "One");
           put(50, "50c");
           put(25, "25c");
           put(10, "10c");
           put(5, "5c");
        }};
        
        for (Map.Entry <Integer, String> entry: Denominations.entrySet()){
            key = entry.getkey();
            value = entry.getValue();
        }
        
        Map<String, Integer> change_dict = new HashMap<>();
        
    }
}
