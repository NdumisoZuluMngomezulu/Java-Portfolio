import java.util.HashMap;
import java.util.Map;

class Kata_Cash {
    public static void main(String[] args) {
        HashMap<Integer, String> output = new HashMap<>();
        
        output = get_change(500, 600);
        System.out.println(output);
    }
    
    public static HashMap<Integer, String> get_change(int due, int paid){
        int change_due = (paid*100) - (due*100);
        if (change_due < 0){
            throw new ArithmeticException("Error: Insufficient funds");
        }
        
        HashMap<Integer, String> notes = new HashMap<>(){{
           put(20000, "Two Hundred");
           put(10000, "One Hundred");
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
        
        HashMap<Integer, String> change_map = new HashMap<>();
        int count;
        
        for (Map.Entry <Integer, String> entry : notes.entrySet()){
            count = change_due / entry.getKey();
            change_due = change_due % entry.getKey();
            if (count > 0){
                change_map.put(count, entry.getValue());
            }
        }
        
        return change_map;
    }
}
