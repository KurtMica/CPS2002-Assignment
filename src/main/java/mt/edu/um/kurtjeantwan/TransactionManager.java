/**
 This is the Transaction Manager Class. It handles the creation and processing
    of Transactions.
 */
package mt.edu.um.kurtjeantwan;

public class TransactionManager {
    
    private int numTransactionsProcessed;
    
    private AccountDatabase accountDb;
    
    protected TransactionManager(AccountDatabase database)
    {
        numTransactionsProcessed = 0;
        accountDb = database;
    }
    
    public boolean processTransaction(int src, int dst, int amount)
    {
        AtomicTransaction t1 = new AtomicTransaction(src, dst, amount, accountDb);
        if(t1.process())
        {
            this.numTransactionsProcessed++;
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public boolean processTransaction(Transaction transaction)
    {
    	if(!transaction.isRoot)
    		return false;
    	
    	try
    	{
			if(transaction.process())
			{
				this.numTransactionsProcessed++;
				return true;
    		}
			else
			{
				return false;
			}
		}
    	catch (Exception e)
    	{
			return false;
		}	
    }
    
    public int getNum()
    {
        return numTransactionsProcessed;
    }
}