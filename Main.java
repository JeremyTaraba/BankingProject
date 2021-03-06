import java.io.File;
import java.util.*;


// now can start adding GUI stuff into it


public class Main{

    public static void main(String args[]) throws Exception { 
        File file = new File("C:\\Users\\jghos\\Documents\\GitHub\\BankingProject\\login.txt");
        Scanner sc = new Scanner(file); 
        Scanner input = new Scanner(System.in);
        String username = "";
        Login loginAttempt = new Login();
        Banking user1 = new Banking();
        

        Hashtable<String, String> logins = new Hashtable<String, String>();


        while(sc.hasNextLine()){
            logins.put(sc.nextLine(), sc.nextLine());
        }

        int startChoice = loginAttempt.start(input);
    
        if(startChoice == 1){
            username = loginAttempt.login(logins, input); 
            user1 = loginAttempt.loginSuccess(input, username);
            user1.enterBank(input);
        }
        else if(startChoice == 2){
            username = loginAttempt.createAccount(input, logins);
            System.out.println("Logging you into your account...\n");
            user1 = loginAttempt.loginSuccess(input, username);
            user1.enterBank(input);
        }
        else{
            //pass
        }


        input.close();
        sc.close();
    }
    
}
