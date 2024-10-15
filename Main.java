import java.io.*;
import java.util.*;
import java.time.LocalDate;  
import java.time.Period;  
import java.util.concurrent.ThreadLocalRandom;


public class Main 
{  
    static int registrationCount = 1;
    public static void printRegisteredInformation(String[] name, String[] date, String[] address, String[] contact, int[] age, int[] arrVoterID, String[] arrPassword)
    {
        for(int i=0; i<registrationCount; i++)
        {
            System.out.println("REGISTERED INFORMATION");
            System.out.println("Name: " + name[i]);
            System.out.println("Date of Birth: " + date[i]);
            System.out.println("Address: " + address[i]);
            System.out.println("Contact: " + contact[i]); 
            System.out.println("Age: " + age[i]);
            System.out.println("VoterID: " + arrVoterID[i]);
            System.out.println("Password: " + arrPassword[i]);
            System.out.println();   
        }
        
    }
    
    public static String generatePassword() 
    {
        int passwordLength = 10;
        String randomString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(passwordLength);
        for (int i = 0; i < passwordLength; i++)
        {
    	    int randomIndex = (int) (randomString.length() * Math.random());
    	    sb.append (randomString.charAt (randomIndex));
        } 
        return sb.toString ();
    }
  
    public static int generateVoterID()
    {
        int id = ThreadLocalRandom.current().nextInt(91000000,110000001);
        return id;
    }
        
    public static int calculateAge(LocalDate dob)   
    {       
        LocalDate curDate = LocalDate.now(); 
        if ((dob != null) && (curDate != null))   
        {  
            return Period.between(dob, curDate).getYears();  
        }  
        else  
        {  
            return 0;  
        }
    }
    
    public static void main(String args[])   
    {  
        /* Initialisation */
        String[] name = new String[registrationCount];  
        String[] date = new String[registrationCount];
        String[] address = new String[registrationCount];
        String[] contact = new String[registrationCount];
        
        int[] age = new int[registrationCount];
        int[] arrVoterID = new int[registrationCount];
        String[] arrPassword = new String[registrationCount];

        Scanner sc = new Scanner(System.in);

        /* Voter Registration */
        System.out.println("VOTER REGISTRATION");
        for (int i=0; i<registrationCount; i++)
        {
            System.out.print("Enter name: ");
            name[i] = sc.nextLine();
            System.out.print("Enter date of birth in YYYY-MM-DD format: ");  
            date[i] = sc.nextLine();
            System.out.print("Enter city: ");
            address[i] = sc.nextLine();
            System.out.print("Enter contact: ");
            contact[i] = sc.nextLine();
            System.out.println();
        }
        
        
        /* Age Calculation */
        for (int i=0; i<registrationCount; i++) 
        {
            String input = (String)date[i];
            LocalDate dob = LocalDate.parse(input); 
            int userAge = calculateAge(dob);
            age[i] = userAge;
            
        }

        /* VoterID and Password generation */        
        for (int i=0; i<registrationCount; i++) 
        {
            if(age[i]<18)
            {
                arrVoterID[i]=0;
                arrPassword[i]=null;
            }
            else 
            {
                int voterID = generateVoterID();
                arrVoterID[i] = voterID;
                
                String password = generatePassword();
                arrPassword[i] = password;
            }
        }
        
        
        printRegisteredInformation(name, date, address, contact, age, arrVoterID, arrPassword);
    
        /* Voting Process */
        System.out.println("VOTING PROCESS");
        System.out.print("Enter Name: ");
		String userName = sc.nextLine();
		System.out.print("Enter Voter ID: ");
		int userVoterID = sc.nextInt();
		
		/* Voting Categories */
		int c1=0, c2=0, c3=0, c4=0, c5=0;
		int choice;
		
		for(int i=0; i<registrationCount; i++)
		{
            String iter_name = name[i];
    		if( iter_name.equals(userName) && arrVoterID[i]!=0 ) 
    		{
    		    if (arrVoterID[i] == userVoterID)
    		    {
        			System.out.print("Enter Password: ");
        			char [] userPassword = System.console().readPassword();
        			String userPasswordValue = String.valueOf(userPassword);
        
                    String password = arrPassword[i];
        			if(password.equals(userPasswordValue))
        			{
        				System.out.println("Cast your vote by choosing the number against your preferred candidate: ");
        				System.out.println("\t1-Candidate A" + "\n\t2-Candidate B" + "\n\t3-Candidate C" + "\n\t4-Candidate D" + "\n\t5-Candidate E");
    			        
    			        System.out.print("Your choice: ");
    			        choice = sc.nextInt();
        				switch(choice) 
        				{
        					case 1:
        					c1++;
        					break;
        
        					case 2:
        					c2++;
        					break;
        
        					case 3:
        					c3++;
        					break;
        			
        					case 4:
        					c4++;
        					break;
        			
        					case 5:
        					c5++;
        					break;
        				}
    			    }
    		    }
                else
                {
    		        System.out.println("Voter ID doesn't match. You can't vote.");
                    
                }
    		}
		}
		
		/* Votes Casted */
        System.out.println("\n\nCASTED VOTES SUMMARY");
        System.out.println(c1+ " "+c2+" "+c3+" "+c4+" "+c5);
        
        
        
        
    } // End of Main 
} // End of Class
