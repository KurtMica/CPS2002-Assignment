package mt.edu.um.kurtjeantwan;

/**
 * This is the Account class.
 *
 */

public class Account 
{
    private int accountNumber;
    private String accountName;
    private double accountBalance;
    private long lastUsed;
    
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
    
    public void setLastUsed(long time)
    {
        lastUsed = time;
    }
    
    public long checkLastUsed()
    {
        return lastUsed;
    }
    
    public boolean adjustBalance(double amount)
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
    
    public double checkBalance()
    {
    	return accountBalance;
    }
}