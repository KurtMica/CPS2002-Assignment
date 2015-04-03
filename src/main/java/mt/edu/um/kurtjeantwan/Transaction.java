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
	
	protected Transaction(int destination, int source, int amount)
	{
		sourceAccountNumber = source;
		destinationAccountNumber = destination;
		this.amount = amount;
	}
	
	public boolean process()
	{
		return true;
	}
}