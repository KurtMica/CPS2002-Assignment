package mt.edu.um.kurtjeantwan;

import java.util.List;
import java.util.ArrayList;

public class CompoundTransaction extends Transaction
{
	List<Transaction> subTransaction;
	
	public CompoundTransaction(AccountDatabase database)
	{
		super("Compound Transaction", database);
		this.subTransaction = new ArrayList<Transaction>();
	}
        
    public CompoundTransaction(String desc, AccountDatabase database)
	{
		super(desc, database);
		this.subTransaction = new ArrayList<Transaction>();
	}
    
    public void addTransaction(Transaction transaction)
    {
    	this.subTransaction.add(transaction);
    }
    
    public boolean process()
    {
    	return (Boolean) null;
    }
}