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
    
    
}
