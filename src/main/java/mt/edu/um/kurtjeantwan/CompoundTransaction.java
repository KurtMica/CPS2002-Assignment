package mt.edu.um.kurtjeantwan;

import java.util.List;
import java.util.ArrayList;

public class CompoundTransaction extends Transaction
{
	List<Transaction> subTransactions;
	
	public CompoundTransaction()
	{
		super("Compound Transaction");
		this.subTransactions = new ArrayList<Transaction>();
	}
        
    public CompoundTransaction(String desc)
	{
		super(desc);
		this.subTransactions = new ArrayList<Transaction>();
	}
    
    public void addTransaction(Transaction transaction)
    {
    	this.subTransactions.add(transaction);
    }
    
    public boolean process() throws Exception
    {
    	// empty CompoundTransaction
    	if(this.subTransactions.isEmpty())
    		return false;
    	
    	// iterate all sub-transactions
    	for(Transaction trn : this.subTransactions)
    		// check if any of the sub-transactions & notify accordingly
    		if(!trn.process())
    			throw new Exception("Failed Atomic Transaction");
    	return true;
    }
}