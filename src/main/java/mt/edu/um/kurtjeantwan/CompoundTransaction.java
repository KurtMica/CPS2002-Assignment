package mt.edu.um.kurtjeantwan;

import java.util.List;
import java.util.ArrayList;

public class CompoundTransaction extends Transaction
{
	List<Transaction> subTransaction;
	
	public CompoundTransaction(AccountDatabase database)
	{
		super("Compound Transaction", database);
		subTransaction = new ArrayList<Transaction>();
	}
        
    public CompoundTransaction(String desc, AccountDatabase database)
	{
		super(desc, database);
		subTransaction = new ArrayList<Transaction>();
	}
    
    public void addChild(Transaction transaction)
    {
    	
    }
    
    public boolean process()
    {
    	return (Boolean) null;
    }
}