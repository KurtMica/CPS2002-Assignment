/*
 This is the Transaction Manager Class. It handles the creation and processing
    of Transactions.
 */
package mt.edu.um.kurtjeantwan;

/**
 *
 * @author JeanT
 */
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
        Transaction t1 = new Transaction(src, dst, amount, accountDb);
        if(t1.process())
        {
            numTransactionsProcessed++;
            return true;
        }
        else
        {
            return false;
        }
        
    }
    
    public int getNum()
    {
        return numTransactionsProcessed;
    }
    
    
}
