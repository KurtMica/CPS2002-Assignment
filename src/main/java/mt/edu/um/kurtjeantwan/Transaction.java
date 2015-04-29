package mt.edu.um.kurtjeantwan;

public abstract class Transaction
{
	protected String description;
	
	
	public Transaction(String description)
	{
		this.description = description;
	}
	
	public abstract boolean process();
}