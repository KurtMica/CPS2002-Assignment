package mt.edu.um.kurtjeantwan;

public abstract class Transaction
{
	protected String description;
	protected boolean isRoot;
	
	public Transaction(String description)
	{
		this.description = description;
		this.isRoot = true;
	}
	
	public abstract boolean process() throws Exception;
}