package mt.edu.um.kurtjeantwan;
import java.util.List;

public abstract class Transaction
{
	protected String description;
	protected boolean isRoot;
	
	public Transaction(String description)
	{
		this.description = description;
		this.isRoot = true;
	}
	
	protected abstract boolean process() throws Exception;
        
        protected abstract List<AtomicTransaction> getTransaction();
}