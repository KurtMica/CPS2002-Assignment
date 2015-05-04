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
    
    @Test
    public void testProcessTransactionAtomicNotRoot() throws Exception
    {
    	AtomicTransaction trn1 = new AtomicTransaction("first",1,3,10,accountDb);
    	AtomicTransaction trn2 = new AtomicTransaction("second",2,4,5,accountDb);
        CompoundTransaction trnComp = new CompoundTransaction();
        trnComp.addTransaction(trn1);
        trnComp.addTransaction(trn2);
        Assert.assertFalse(trn1.process());
    }
    
    @Test
    public void testProcessTransactionCompundNotRoot() throws Exception
    {
		accountDb.addAccount(3, "Michael");
		accountDb.addAccount(4, "Monica");
        AtomicTransaction trn1 = new AtomicTransaction("first",1,3,10,accountDb);
        AtomicTransaction trn2 = new AtomicTransaction("second",2,4,5,accountDb);
        CompoundTransaction trnCompChild = new CompoundTransaction();
        trnCompChild.addTransaction(trn1);
        trnCompChild.addTransaction(trn2);
        CompoundTransaction trnComp = new CompoundTransaction();
        trnComp.addTransaction(trnCompChild);
        Assert.assertFalse(tm1.processTransaction(trnCompChild));
    }
    
    public void testProcessTransactionObjectFail() throws Exception
    {
    	Transaction trn = new AtomicTransaction(1, 2, 10, accountDb);
    	Assert.assertFalse(tm1.processTransaction(trn));
    }
    
    public void testProcessTransactionObjectSuccess() throws Exception
    {
    	AtomicTransaction trn = new AtomicTransaction(2, 1, 5, accountDb);
    	Assert.assertFalse(tm1.processTransaction(trn));
    }
}
