package mt.edu.um.kurtjeantwan;

public abstract class Transaction
{
	private String description;
	
	public Transaction(String description)
	{
		this.description = description;
	}
	
	public abstract boolean process();
}