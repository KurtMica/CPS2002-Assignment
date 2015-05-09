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
    	transaction.isRoot = false;
    }
    
    protected boolean process() throws Exception
    {
    	// empty CompoundTransaction
    	if(this.subTransactions.isEmpty())
    		throw new Exception("Failed Sub-Transaction");
    	
    	// iterate all sub-transactions
    	for(Transaction trn : this.subTransactions)
    		// check if any of the sub-transactions & notify accordingly
    		if(!trn.process())
    			throw new Exception("Failed Sub-Transaction");
    	return true;
    }
    
    public List<AtomicTransaction> getTransaction(){
        
        List<AtomicTransaction> result = new ArrayList();
        for(Transaction i: this.subTransactions)
        {
            result.addAll(i.getTransaction());
        }
        return result;
    }
}