package mt.edu.um.kurtjeantwan;

/**
 * This is the Transaction Class.
 * Handles the transfer of money between 2 accounts, whilst ensuring that
 * the source Account has enough money as well as that both accounts exist.
 */
public class Transaction
{
	private int sourceAccountNumber;
	private int destinationAccountNumber;
	private long amount;
	private AccountDatabase accountDb;
	
	protected Transaction(int source, int destination, int amount, AccountDatabase database)
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
		if(src != null && dst != null)
		{
			// check if there is enough money in source
			if(src.adjustBalance(-amount))
			{
				dst.adjustBalance(amount);
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
		if((Math.abs(currentTime - accountDb.getAccount(sourceAccountNumber).checkLastUsed()) >= 15)
				&& (Math.abs(currentTime - accountDb.getAccount(destinationAccountNumber).checkLastUsed()) >= 15))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}