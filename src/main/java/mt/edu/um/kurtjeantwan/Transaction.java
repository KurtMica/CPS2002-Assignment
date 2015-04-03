package mt.edu.um.kurtjeantwan;

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
}