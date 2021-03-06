package mt.edu.um.kurtjeantwan;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the AccountDatabase Class.
 * It contains a list of Accounts.
 */
public class AccountDatabase
{    
    private List<Account> database;
    
    public AccountDatabase()
    {
        database = new ArrayList<Account>();
    }
    
    public boolean addAccount(int accountNumber, String accountName)
    {
        if(validateNumber(accountNumber))
        {
            database.add(new Account(accountNumber, accountName));
            return true;
        }
        else
        {
            return false;            
        }   
        
    }
    
    public boolean deleteAccount(int accountNumber)
    {
        int i = 0;
        while(i < database.size())
        {
            if(accountNumber == database.get(i).getNumber())
            {
                database.remove(i);
                return true;
            }
            i++;
        }
        return false;
    }
    
    public boolean validateNumber(int accountNumber)
    {
    	if(getAccount(accountNumber) == null)
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
    
    public Account getAccount(int accountNumber)
    {
    	for(int i = 0; i < database.size(); i++)
    	{
    		if(accountNumber == database.get(i).getNumber())
    		{
    			return database.get(i);
    		}
    	}
    	return null;
    }
    
    public int getSize()
    {
    	return database.size();
    }
}
