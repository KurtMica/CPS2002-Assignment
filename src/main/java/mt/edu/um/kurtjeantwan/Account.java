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
    private int lastUsed;
    
    protected Account(int num, String Name)
    {
        accountNumber = num;
        accountName = Name;
        accountBalance = 0;
        lastUsed = 0;
    }
    
    public int getNumber()
    {
    	return accountNumber;
    }
    
    public String getName()
    {
    	return accountName;
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