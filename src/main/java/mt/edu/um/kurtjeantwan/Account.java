package mt.edu.um.kurtjeantwan;

/**
 * This is the Account class.
 *
 */

public class Account 
{
	private int accountNumber;
    private String accountName;
    private long accountBalance;
    
    public Account(int num, String Name)
    {
        accountNumber = num;
        accountName = Name;
        accountBalance = 0;
    }
    
    public int getNumber()
    {
    	return -1;
    }
    
    public String getName()
    {
    	return "";
    }
    
    public boolean adjustBalance(long amount)
    {
        if((amount + accountBalance >= 0))
        {
            accountBalance += amount;
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public long checkBalance()
    {
    	return accountBalance;
    }
}