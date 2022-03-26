import java.io.File;
import java.util.*;

public class Banking{


    public static void login(Scanner input, Hashtable<String, String> logins){
        //should put login stuff in its own function. so can loop over it easy if incorrect username/password
        String user;
        String pass;
        boolean failedLogin = true;

        
        
        while(failedLogin){
            System.out.println("Enter Your Username:");
            user = input.nextLine();
            System.out.println("Enter Your Password:");
            pass = input.nextLine();
            
            if (logins.containsKey(user)) {
                // Okay, there's a key
                if (logins.get(user).equals(pass)) {    
                    //value equals password
                    System.out.println("Login Successful!");
                    failedLogin = false;
                }
                else{
                    //Username exists but wrong Password
                    System.out.println("Incorrect Username/Password. Try Again");
                }
            } else {
                // Definitely no such key
                System.out.println("Incorrect Username/Password. Try Again");
            }

        }

        

    }

    public static void createAccount(){
        //check if username is already taken before accepting their account. Store their username and password into the txt file of logins.
    }

    public static int start(Scanner input){
        //Scanner input = new Scanner(System.in); // can't have a scanner here because when we close it, it also closes any inputs from system.in
        boolean validChoice = true;
        int choiceInt = -1;

        System.out.println("Welcome to the bank. Enter 1. for Login and 2. for New Account 3. for Quit");
        String choice;
        
        while(validChoice){
            choice = input.nextLine();
            validChoice = false;
            if(choice.equals("1")){
                choiceInt = 1;
            }
            else if(choice.equals("2")){
                choiceInt = 2;
            }
            else if(choice.equals("3")){
                choiceInt = 3;
            }
            else{
                //loop back
                System.out.println("Not a Valid Choice");
                validChoice = true;
                System.out.println("Choose Again");
            }
        }
        
        
        return choiceInt;
    }


    public static void main(String args[]) throws Exception { 
        File file = new File("C:\\Users\\jghos\\OneDrive\\Documents\\Code\\Java\\login.txt");
        Scanner sc = new Scanner(file);
        Scanner input = new Scanner(System.in); 
        

        Hashtable<String, String> logins = new Hashtable<String, String>();


        while(sc.hasNextLine()){
            logins.put(sc.nextLine(), sc.nextLine());
        }
        sc.close();

        int startChoice = start(input);


        if(startChoice == 1){
            login(input, logins);   //returns on successful login
            //create bank object using login info, retrieve things like account balance, bills, id, stuff like that.
            //once in their account we can have options like withdrawl and deposit and stuff.
        }
        else if(startChoice == 2){
            createAccount();
            //after account is successfully created, let them login, can also do start menu stuff again.
        }
        else if(startChoice == 3){
            return;
        }
        else{
            System.out.println("Should not reach here, something is wrong");
            return;
        }

// ideas: add something for when user/password is incorrect and maybe add limited attempts to login like 5 and then they have to wait to try again



        

        input.close();

    }

    
}