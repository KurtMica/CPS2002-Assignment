package mt.edu.um.kurtjeantwan;

public abstract class Transaction
{
	protected String description;
	protected AccountDatabase accountDb;
	
	public Transaction(String description, AccountDatabase database)
	{
		this.description = description;
		this.accountDb = database;
	}
	
	public abstract boolean process();
}