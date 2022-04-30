import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.io.File;
 

public class Banking{
    private boolean accountActive;//you can't change this outside of this file
    private String name;
    private String ID; //set these to private, so nothing can mess it up
    private BigDecimal accountBalance;

    public Banking(){
        this.accountActive = false;;
        this.name = "";
        this.ID = "";
        this.accountBalance = BigDecimal.valueOf(-1.00);
    }

    public Banking(boolean status, String username, String id, BigDecimal balance) //only construcotrs and setter methods can change these variables
    {
        this.accountActive = status;
        this.name = username;
        this.ID = id;
        this.accountBalance = balance;
    }

    private static boolean isNumber(String str){
        try{
            Double.parseDouble(str);
            return true;
        }
        catch(NumberFormatException e){
            return false;
        }
    }


   

    public void enterBank(Scanner input){
        
        //check if account is active or not and if not do something
        if(this.accountActive != true){
            System.out.println("ERROR: This account is Inactive. Call our number for help with activating your account");
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
                    String depositStr = input.nextLine();
                    if(isNumber(depositStr)){
                        BigDecimal depositAmount = BigDecimal.valueOf(Double.valueOf(depositStr));
                        if(depositAmount.scale() > 2){
                            System.out.println("Please only input up to 2 decimal points");
                        }
                        else{
                            this.deposit(depositAmount);
                        }
                    }
                    else{
                        System.out.println("Please enter a number");
                    }
                    
                                        
                    
                }
                else if(loginChoice.equals("3")){
                    System.out.println("How much would you like to withdrawal?");
                    String withdrawalStr = input.nextLine();
                    if(isNumber(withdrawalStr)){
                        BigDecimal withdrawalAmount = BigDecimal.valueOf(Double.valueOf(withdrawalStr));
                        if(withdrawalAmount.scale() > 2){
                            System.out.println("Please only input up to 2 decimal points");
                        }
                        else{
                            this.withdrawal(withdrawalAmount);
                        }
                    }
                    else{
                        System.out.println("Please enter a number");
                    }
                    
                    
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

    

    

    public BigDecimal getBalance(){
        return this.accountBalance;
    }

    private void deposit(BigDecimal depositAmount){
        BigDecimal total = this.accountBalance.add(depositAmount);
        try{
            FileWriter myWriter = new FileWriter("C:\\Users\\jghos\\Documents\\GitHub\\BankingProject\\account_details\\" + this.ID + ".txt");
            myWriter.write(this.accountActive + "\n" + this.name + "\n" + total);
            this.accountBalance = total;
            myWriter.close();
            System.out.println("Successfully depositted. Your new balance is: " + this.getBalance() + "\n");
        }
        catch(IOException e){
            System.out.println("An error occurred while depositting.");
            e.printStackTrace();
        }
    }

    private void withdrawal(BigDecimal withdrawalAmount){
        BigDecimal total = this.accountBalance.subtract(withdrawalAmount);
        int result = total.compareTo(BigDecimal.valueOf(0.00));
        if(result == -1 ){
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
            System.out.println("An error occurred while withdrawaling.");
            e.printStackTrace();
        }
    }

    
}