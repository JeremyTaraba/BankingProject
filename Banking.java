import java.io.File;
import java.util.*;

public class Banking{


    public void login(){
        //should put login stuff in its own function. so can loop over it easy if incorrect username/password
    }

    public void createAccount(){
        //should put login stuff in its own function. so can loop over it easy if incorrect username/password
    }

    public static int start(){
        Scanner input = new Scanner(System.in);
        boolean validChoice = true;

        System.out.println("Welcome to the bank. Enter 1. for Login and 2. for New Account 3. for Quit");
        String choice = input.nextLine();
        input.close();
        while(validChoice){
            validChoice = true;
            if(choice.equals("1")){
                return 1;
            }
            else if(choice.equals("2")){
                return 2;
            }
            else if(choice.equals("3")){
                System.exit(0);
            }
            else{
                //loop back
                System.out.println("Not a Valid Choice");
                validChoice = false;
            }
        }
        
        return 0;
    }


    public static void main(String args[]) throws Exception { 
        File file = new File("C:\\Users\\jghos\\OneDrive\\Documents\\Code\\Java\\login.txt");
        Scanner sc = new Scanner(file);
        

        Hashtable<String, String> logins = new Hashtable<String, String>();


        while(sc.hasNextLine()){
            logins.put(sc.nextLine(), sc.nextLine());
        }

        int startChoice = start();

        

        

        String user = input.nextLine();
        System.out.println("Password: ");
        String pass = input.nextLine();



        // ideas: add something for when user/password is incorrect and maybe add limited attempts to login like 5 and then they have to wait to try again


        if (logins.containsKey(user)) {
            // Okay, there's a key but the value is null
            if (logins.get(user).equals(pass)) {
                System.out.println("Login Successful!");
            }
        } else {
            // Definitely no such key
        }
    

        

        
        sc.close();
    }

    
}