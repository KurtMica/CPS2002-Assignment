
package mt.edu.um.kurtjeantwan;

import java.util.ArrayList;
import java.util.List;

/**
 This is the AccountDatabase Class. It contains a list of Accounts
 */
public class AccountDatabase {
    
    private List<Account> database;
   
    
    public AccountDatabase()
    {
        database = new ArrayList<Account>();
    }
    
    public boolean addAccount(int accNum, String accName)
    {
         return false;   
        
    }
    
    public boolean deleteAccount(int accNum)
    {
        return false;
    }
    
    
    public boolean validateNumber(int accNum)
    {
        int i =0;
        while(i<database.size())
        {
            if(accNum == database.get(i).getNumber())
            {
                return false;
            }
        }
        return true;
    }
    
}
