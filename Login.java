import java.io.File;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;


public class Login{


    public int start(Scanner input){
       
        boolean validChoice = true;

        System.out.println("Welcome to the bank. Enter 1. for Login and 2. for New Account 3. for Quit");
        String choice = input.nextLine();
       
        while(validChoice){
            validChoice = false;
            
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
                System.out.println("Not a Valid Choice\nEnter 1. for Login and 2. for New Account 3. for Quit");
                validChoice = true;
            }
        }
        
        return 0;
    }


    public String login(Hashtable<String, String> logins, Scanner input){
        //should put login stuff in its own function. so can loop over it easy if incorrect username/password
        String user = "";
        String pass;
        boolean failLogin = true;



        while(failLogin){
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



    public String createAccount(Scanner input, Hashtable<String, String> logins){
        ArrayList<Character> captcha = new ArrayList<Character>();
        Random random = new Random();
        String inputCaptcha = "";
        String correctCaptcha = "";
        Boolean captchaSuccess = false;

        for(int i = 0; i < 5; i++){ //for loop is used to initilize the arraylist to size 5
            captcha.add('a');
        } 

        while(!captchaSuccess){
            for(int i = 0; i < 5; i++){ //used to randomize the captcha every time
                captcha.set(i, (char)(random.nextInt(26)+'a'));
            }

        
            System.out.println("Prove you are not a bot by correctly typing the characters shown\n");
            correctCaptcha = "";
            for(int i = 0; i < 5; i++){
                System.out.print(captcha.get(i));
                correctCaptcha += captcha.get(i);   //used to store the captcha into a string
            }
            System.out.println();
            
            inputCaptcha = input.nextLine();
            if(inputCaptcha.equals(correctCaptcha)){
                System.out.println("Correct! You are a human\n");
                captchaSuccess = true;
            }
            else{
                System.out.println("Incorrect! Try again");
            }
        }


        System.out.println("Welcome, please enter your new username: \n");
        String username = "";
        boolean usernameExists = true;

        while(usernameExists){
            usernameExists = false;
            username = input.nextLine();
            if(logins.containsKey(username)){   //hashtables: <key> : <value>    -->  <username> : <password>
                usernameExists = true;
                System.out.println("Username is already taken, please choose another");
            }
        }

        String password = "";
        String password2 = "";
        boolean passwordMatch = false;

        while(!passwordMatch){
            System.out.println("Please enter your new password: \n");
            password = input.nextLine();
            System.out.println("Re-enter your password: \n");
            password2 = input.nextLine();
            if(password.equals(password2)){
                System.out.println("Congratulations your account has been created");
                passwordMatch = true;
            }
            else{
                System.out.println("Error. Passwords do not match. Please enter them again");
            }
        }

        System.out.println("What do you want your display name to be?");
        String name = input.nextLine();


        try{
            FileWriter myWriter = new FileWriter("C:\\Users\\jghos\\Documents\\GitHub\\BankingProject\\login.txt", true);
            myWriter.write("\n" + username + "\n" + password);
            myWriter.close();

            FileWriter myWriter2 = new FileWriter("C:\\Users\\jghos\\Documents\\GitHub\\BankingProject\\account_details\\" + username + ".txt");
            myWriter2.write("true\n" + name + "\n0");
            myWriter2.close();
        }
        catch(IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        return username;
    }

    public Banking loginSuccess(Scanner input, String username){
        Boolean accountStatus = false;
        String accountName = "";
        BigDecimal accountBalance = BigDecimal.valueOf(-1.00);
        
        try{
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
                    accountBalance = BigDecimal.valueOf(Double.valueOf(scAccount.nextLine()));
                }
            }  
            scAccount.close();
        }
        catch(IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        Banking user1 = new Banking(accountStatus, accountName, username, accountBalance);
        return user1;
    }



    
}