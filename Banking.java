import java.io.File;
import java.util.*;

public class Banking{
    private boolean accountActive;//you can't change this outside of this file
    private String name;
    private int ID; //set these to private, so nothing can mess it up
    private int accountBalance;

    public Banking(boolean status, String username, int id, int balance) //only construcotrs and setter methods can change these variables
    {
        this.accountActive = status;
        this.name = username;
        this.ID = id;
        this.accountBalance = balance;
    }


    public static String login(Hashtable<String, String> logins, Scanner input){
        //should put login stuff in its own function. so can loop over it easy if incorrect username/password
        String user = "";
        String pass;
        boolean failLogin = true;



        while(failLogin)
        {
            System.out.println("Enter your username: ");
            user = input.nextLine();
            System.out.println("Enter your password: ");
            pass = input.nextLine();
            if (logins.containsKey(user))
            {
                if (logins.get(user).equals(pass))
                {
                    System.out.println("login succesful");
                    failLogin = false;
                }
                else
                {
                    System.out.println("Incorrect Username or Password");
                }
            }
            else
            {
                System.out.println("Incorrect Username or Password");
            }
        }
        
       return user;
    }

    public static void createAccount(){
        //should put login stuff in its own function. so can loop over it easy if incorrect username/password
    }

    public static int start(Scanner input){
       
        boolean validChoice = true;

        System.out.println("Welcome to the bank. Enter 1. for Login and 2. for New Account 3. for Quit");
        String choice = input.nextLine();
       
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
        File file = new File("C:\\Users\\jghos\\Documents\\GitHub\\BankingProject\\login.txt");
        Scanner sc = new Scanner(file);
        Scanner input = new Scanner(System.in);
        String username = "";
        

        Hashtable<String, String> logins = new Hashtable<String, String>();


        while(sc.hasNextLine()){
            logins.put(sc.nextLine(), sc.nextLine());
        }

        int startChoice = start(input);

        if(startChoice == 1){
            username = login(logins, input);

            Boolean accountStatus = false;
            String accountName = "";
            int accountID = -1;
            int accountBalance = -1;

            File accountFile = new File("C:\\Users\\jghos\\Documents\\GitHub\\BankingProject\\account_details\\" + username + ".txt");
            Scanner scAccount = new Scanner(accountFile);
            if(scAccount.hasNextLine()){    
                if(scAccount.nextLine().equals("true")){
                    accountStatus = true;
                }
                if(scAccount.hasNextLine()){
                    accountName = scAccount.nextLine();
                }
                if(scAccount.hasNextLine()){
                    accountID = Integer.parseInt(scAccount.nextLine());
                }
                if(scAccount.hasNextLine()){
                    accountBalance = Integer.parseInt(scAccount.nextLine());
                }
            }
            Banking user1 = new Banking(accountStatus, accountName, accountID, accountBalance);
            
            //check if account is active or not and if not do something



            scAccount.close();
            System.out.println("Hello, " + user1.name + " What would you like to do today? 1. Check Balance 2. Deposit Money 3. Withdrawal 4. Logout");




        }
        else if(startChoice == 2){
            createAccount();
        }
        else{
            //pass
        }

        


        // ideas: add something for when user/password is incorrect and maybe add limited attempts to login like 5 and then they have to wait to try again


        

        input.close();
        sc.close();
    }

    
}