import java.util.*;
import java.io.FileWriter;
import java.io.IOException;


public class Banking{
    private boolean accountActive;//you can't change this outside of this file
    private String name;
    private String ID; //set these to private, so nothing can mess it up
    private float accountBalance;

    public Banking(){
        this.accountActive = false;;
        this.name = "";
        this.ID = "";
        this.accountBalance = -1;
    }

    public Banking(boolean status, String username, String id, int balance) //only construcotrs and setter methods can change these variables
    {
        this.accountActive = status;
        this.name = username;
        this.ID = id;
        this.accountBalance = balance;
    }


   

    public void enterBank(Scanner input){
        
        //check if account is active or not and if not do something
        if(this.accountActive != true){
            System.out.println("ERROR: This account is Inactive. Call our number for help in activating your account");
        }
        else{
            System.out.println("Hello, " + this.name + " What would you like to do today? 1. Check Balance 2. Deposit 3. Withdrawal 4. Logout");
            String loginChoice = "";
            boolean loginChoiceBool = true;

            while(loginChoiceBool){
                
                loginChoice = input.nextLine();
                if(loginChoice.equals("1")){
                    System.out.println("Your current balance is: " + this.getBalance() + "\n");
                }
                else if(loginChoice.equals("2")){
                    System.out.println("How much would you like to deposit?");
                    int depositAmount = Integer.parseInt(input.nextLine());
                    this.deposit(depositAmount);
                    
                }
                else if(loginChoice.equals("3")){
                    System.out.println("How much would you like to withdrawal?");
                    int withdrawalAmount = Integer.parseInt(input.nextLine());
                    this.withdrawal(withdrawalAmount);
                    
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
            System.out.println("Successfully depositted. Your new balance is: " + this.getBalance() + "\n");
        }
        catch(IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void withdrawal(int withdrawalAmount){
        int total = this.accountBalance - withdrawalAmount;
        if(total < 0){
            System.out.println("Error. Cannot withdrawal more than your current balance\n");
            System.out.println("Your current balance is: " + this.getBalance() + "\n");
            return;
        }
        try{
            FileWriter myWriter = new FileWriter("C:\\Users\\jghos\\Documents\\GitHub\\BankingProject\\account_details\\" + this.ID + ".txt");
            myWriter.write(this.accountActive + "\n" + this.name + "\n" + total);
            this.accountBalance = total;
            myWriter.close();
            System.out.println("Successfully withdrew. Your new balance is: " + this.getBalance() + "\n");
        }
        catch(IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    
}