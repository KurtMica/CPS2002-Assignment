package mt.edu.um.kurtjeantwan;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

/**
 *This class is used to test the TransactionManager Class.
  Before each test, the setup function will be executed.
 * 
 */
public class TransactionManagerTest {
    
    private AccountDatabase accountDb;
    private TransactionManager tm1;
    
    @Before
    public void setup()
    {
        accountDb = new AccountDatabase();
        accountDb.addAccount(1, "Mark");
	accountDb.getAccount(1).adjustBalance(5);
	accountDb.addAccount(2, "Mary");
        tm1 = new TransactionManager(accountDb);   
    }
    
    @Test
    public void testgetNum()
    {
        Assert.assertEquals(0,tm1.getNum());
    }
    
    @Test
    public void testProcessTransactionSuccess()
    {
        Assert.assertTrue(tm1.processTransaction(1,2,5));   
    }
    
    @Test
    public void testProcessTransactionFail()
    {
        Assert.assertFalse(tm1.processTransaction(1,2,10));
    }
   
    
}
