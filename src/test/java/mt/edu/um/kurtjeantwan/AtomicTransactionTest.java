package mt.edu.um.kurtjeantwan;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import java.util.List;
import java.util.ArrayList;

/**
 * This class is used to test the Atomic AtomicTransaction Class.
 * Before each test, the setup function will be executed. 
 */
public class AtomicTransactionTest
{
	private AccountDatabase accountDb;
	
	@Before
	public void setup()
	{
		accountDb = new AccountDatabase();
		accountDb.addAccount(1, "Mark");
		accountDb.getAccount(1).adjustBalance(50);
		accountDb.addAccount(2, "Mary");
	}
	
	/* process tests */
	
	@Test
	public void testProcessSucess() throws Exception
	{
		Transaction trn = new AtomicTransaction(1, 2, 3, accountDb);
		Assert.assertTrue(trn.process());
	}
	
	@Test
	public void testProcessSucessBound() throws Exception
	{
		Transaction trn = new AtomicTransaction(1, 2, 5, accountDb);
		Assert.assertTrue(trn.process());
	}
	
	@Test
	public void testProcessFailAmount() throws Exception
	{
		Transaction trn = new AtomicTransaction("Failing",1, 2, 60, accountDb);
		Assert.assertFalse(trn.process());
	}
	
	@Test
	public void testProcessFailAmountBound() throws Exception
	{
		Transaction trn = new AtomicTransaction(1, 2, 51, accountDb);
		Assert.assertFalse(trn.process());
	}
	
	@Test
	public void testProcessFailAccountSrc() throws Exception
	{
		Transaction trn = new AtomicTransaction("Failing Source",3, 2, 5, accountDb);
		Assert.assertFalse(trn.process());
	}
	
	@Test
	public void testProcessFailAccountDst() throws Exception
	{
		Transaction trn = new AtomicTransaction(1, 3, 5, accountDb);
		Assert.assertFalse(trn.process());
	}
	
	/* process tests with multiple transactions */
	
	@Test
	public void testProcessMultipleSuccessDifferentAccounts() throws Exception
	{
		
		accountDb.addAccount(3, "Michael");
		accountDb.getAccount(3).adjustBalance(10);
		accountDb.addAccount(4, "Monica");
		
		Transaction trn1 = new AtomicTransaction(1, 2, 5, accountDb);
                trn1.process();
		Transaction trn2 = new AtomicTransaction(3, 4, 5, accountDb);
		Assert.assertTrue(trn2.process());
	}
	
	@Test
	public void testProcessMultipleFailAccountSrc() throws Exception
	{
		
		accountDb.addAccount(3, "Michael");
		accountDb.getAccount(3).adjustBalance(10);
		
		Transaction trn1 = new AtomicTransaction(1, 2, 5, accountDb);
                trn1.process();
		Transaction trn2 = new AtomicTransaction(3, 2, 5, accountDb);
		Assert.assertTrue(trn2.process());
	}
	
	@Test
	public void testProcessMultipleFailAccountDst() throws Exception
	{
		
		accountDb.addAccount(3, "Michael");
		accountDb.getAccount(3).adjustBalance(10);
		
		Transaction trn1 = new AtomicTransaction(1, 2, 5, accountDb);
                trn1.process();
		Transaction trn2 = new AtomicTransaction(1, 3, 5, accountDb);
		Assert.assertTrue(trn2.process());
	}
	
	@Test
	public void testProcessMultipleFailSmallDelay() throws Exception
	{
		
		Transaction trn1 = new AtomicTransaction(1, 2, 2, accountDb);
                trn1.process();
		Thread.sleep(5000); // 5 seconds
		Transaction trn2 = new AtomicTransaction(1, 2, 3, accountDb);
		Assert.assertTrue(trn2.process());
	}
	
	@Test
	public void testProcessMultipleSucessLargeDelay() throws Exception
	{
		
		Transaction trn1 = new AtomicTransaction(1, 2, 2, accountDb);
                trn1.process();
		Thread.sleep(20000); // 20 seconds
		Transaction trn2 = new AtomicTransaction(1, 2, 3, accountDb);
		Assert.assertTrue(trn2.process());
	}
	
	/* timeElapsed tests */
	
	@Test
	public void testTimeElapsedNoDelay()
	{
		AtomicTransaction trn = new AtomicTransaction(1, 2, 5, accountDb);
                trn.process();
		Assert.assertFalse(trn.timeElapsed());
	}
	
	@Test
	public void testTimeElapsedSmallDelay() throws InterruptedException
	{
		
		AtomicTransaction trn = new AtomicTransaction(1, 2, 5, accountDb);
                trn.process();
		Thread.sleep(5000); // 5 seconds
		Assert.assertFalse(trn.timeElapsed());
	}
	
	@Test
	public void testTimeElapsedExactDelay() throws InterruptedException
	{
		
		AtomicTransaction trn = new AtomicTransaction(1, 2, 5, accountDb);
		Thread.sleep(15000); // 15 seconds
		Assert.assertTrue(trn.timeElapsed());
	}
	
	@Test
	public void testTimeElapsedLargeDelay() throws InterruptedException
	{
		
		AtomicTransaction trn = new AtomicTransaction(1, 2, 5, accountDb);
		Thread.sleep(20000); // 20 seconds
		Assert.assertTrue(trn.timeElapsed());
	}
        
        
        @Test
        public void testGetTransaction()
        {
        
            AtomicTransaction atrn1 = new AtomicTransaction("first",1,2,1,accountDb);
            
            List<AtomicTransaction> test = new ArrayList();
            test.add(atrn1);
            Assert.assertEquals(test, atrn1.getTransaction());
        
        }
}