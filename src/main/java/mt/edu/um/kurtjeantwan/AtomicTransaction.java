package mt.edu.um.kurtjeantwan;

/**
 * This is the Atomic Transaction Class.
 * Handles the transfer of money between 2 accounts, whilst ensuring that
 * the source Account has enough money as well as that both accounts exist.
 */
public class AtomicTransaction extends Transaction
{
	private int sourceAccountNumber;
	private int destinationAccountNumber;
	private long amount;
	private AccountDatabase accountDb;
	
	public AtomicTransaction(int source, int destination, int amount, AccountDatabase database)
	{
		sourceAccountNumber = source;
		destinationAccountNumber = destination;
		this.amount = amount;
		accountDb = database;
	}
        
        public AtomicTransaction(String desc,int source, int destination, int amount, AccountDatabase database)
	{
                
		sourceAccountNumber = source;
		destinationAccountNumber = destination;
		this.amount = amount;
		accountDb = database;
	}
	
	public boolean process()
	{
		Account src = accountDb.getAccount(sourceAccountNumber);
		Account dst = accountDb.getAccount(destinationAccountNumber);
		// check if account exists
		if((src != null && dst != null) && timeElapsed())
		{
			// check if there is enough money in source
			if(src.adjustBalance(-amount))
			{
				dst.adjustBalance(amount);
                                long currentTime = System.currentTimeMillis();
                                //Modify LastUsed
                                src.setLastUsed(currentTime);
                                dst.setLastUsed(currentTime);
                                return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	
	public boolean timeElapsed()
	{
		long currentTime = System.currentTimeMillis();
		if((Math.abs(currentTime - accountDb.getAccount(sourceAccountNumber).checkLastUsed()) >= 15000)
				&& (Math.abs(currentTime - accountDb.getAccount(destinationAccountNumber).checkLastUsed()) >= 15000))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}