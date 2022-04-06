import java.io.File;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

public class Banking{
    private boolean accountActive;//you can't change this outside of this file
    private String name;
    private String ID; //set these to private, so nothing can mess it up
    private int accountBalance;

    public Banking(boolean status, String username, String id, int balance) //only construcotrs and setter methods can change these variables
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
                    System.out.println("login succesful\n");
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

    public int getBalance(){
        return this.accountBalance;
    }

    public void deposit(int depositAmount){
        int total = this.accountBalance + depositAmount;
        try{
            FileWriter myWriter = new FileWriter("C:\\Users\\jghos\\Documents\\GitHub\\BankingProject\\account_details\\" + this.ID + ".txt");
            myWriter.write(this.accountActive + "\n" + this.name + "\n" + total);
            this.accountBalance = total;
            myWriter.close();

        }
        catch(IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void withdrawal(){
        
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
            int accountBalance = -1;
            //technically all of this should be in a try catch incase the file does not exists
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
                    accountBalance = Integer.parseInt(scAccount.nextLine());
                }
            }
            Banking user1 = new Banking(accountStatus, accountName, username, accountBalance);
            scAccount.close();
            
            //check if account is active or not and if not do something
            if(user1.accountActive != true){
                System.out.println("ERROR: This account is Inactive. Call our number for help in activating your account");
            }
            else{
                System.out.println("Hello, " + user1.name + " What would you like to do today? 1. Check Balance 2. Deposit 3. Withdrawal 4. Logout");
                String loginChoice = "";
                boolean loginChoiceBool = true;

                while(loginChoiceBool){
                    
                    loginChoice = input.nextLine();
                    if(loginChoice.equals("1")){
                        System.out.println("Your current balance is: " + user1.getBalance() + "\n");
                    }
                    else if(loginChoice.equals("2")){
                        System.out.println("How much would you like to deposit?");
                        int depositAmount = Integer.parseInt(input.nextLine());
                        user1.deposit(depositAmount);
                        System.out.println("Successfully depositted. Your new balance is: " + user1.getBalance() + "\n");
                    }
                    else if(loginChoice.equals("3")){
                        user1.withdrawal();
                    }
                    else if(loginChoice.equals("4")){
                        System.out.println("Logout Successful");
                        loginChoiceBool = false;    //doesn't do anything since we break out of the loop
                        break;
                    }
                    else{
                        System.out.println("Invalid choice. Choose again");
                    }
                    System.out.println("1. Check Balance 2. Deposit Money 3. Withdrawal 4. Logout");

                }
            }
      
            




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