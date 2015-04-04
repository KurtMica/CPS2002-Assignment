package mt.edu.um.kurtjeantwan;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

/**
 * This class is used to test the AccountDatabase Class.
 * Before each test, the setup function will be executed. 
 */
public class TransactionTest
{
	private AccountDatabase accountDb;
	
	@Before
	public void test()
	{
		accountDb = new AccountDatabase();
		accountDb.addAccount(1, "Mark");
		accountDb.getAccount(1).adjustBalance(5);
		accountDb.addAccount(2, "Mary");
	}
	
	/* process tests */
	
	@Test
	public void testProcessSucess()
	{
		Transaction trn = new Transaction(1, 2, 3, accountDb);
		Assert.assertTrue(trn.process());
	}
	
	@Test
	public void testProcessSucessBound()
	{
		Transaction trn = new Transaction(1, 2, 5, accountDb);
		Assert.assertTrue(trn.process());
	}
	
	@Test
	public void testProcessFailAmount()
	{
		Transaction trn = new Transaction(1, 2, 10, accountDb);
		Assert.assertFalse(trn.process());
	}
	
	@Test
	public void testProcessFailAmountBound()
	{
		Transaction trn = new Transaction(1, 2, 6, accountDb);
		Assert.assertFalse(trn.process());
	}
	
	@Test
	public void testProcessFailAccountSrc()
	{
		Transaction trn = new Transaction(3, 2, 5, accountDb);
		Assert.assertFalse(trn.process());
	}
	
	@Test
	public void testProcessFailAccountDst()
	{
		Transaction trn = new Transaction(1, 3, 5, accountDb);
		Assert.assertFalse(trn.process());
	}
	
	/* process tests with multiple transactions */
	
	@Test
	public void testTimeElapsedSuccessDifferentAccounts()
	{
		accountDb.addAccount(3, "Michael");
		accountDb.getAccount(3).adjustBalance(10);
		accountDb.addAccount(4, "Monica");
		Transaction trn1 = new Transaction(1, 2, 5, accountDb);
		Transaction trn2 = new Transaction(3, 4, 5, accountDb);
		trn1.process();
		Assert.assertTrue(trn2.process());
	}
	
	@Test
	public void testTimeElapsedFailAccountSrc()
	{
		accountDb.addAccount(3, "Michael");
		accountDb.getAccount(3).adjustBalance(10);
		Transaction trn1 = new Transaction(1, 2, 5, accountDb);
		Transaction trn2 = new Transaction(3, 2, 5, accountDb);
		trn1.process();
		Assert.assertFalse(trn2.process());
	}
	
	@Test
	public void testTimeElapsedFailAccountDst()
	{
		accountDb.addAccount(3, "Michael");
		accountDb.getAccount(3).adjustBalance(10);
		Transaction trn1 = new Transaction(1, 2, 5, accountDb);
		Transaction trn2 = new Transaction(1, 3, 5, accountDb);
		trn1.process();
		Assert.assertFalse(trn2.process());
	}
	
	@Test
	public void testProcesFailSmallDelay() throws InterruptedException
	{
		Transaction trn1 = new Transaction(1, 2, 2, accountDb);
		Thread.sleep(5000); // 5 seconds
		Transaction trn2 = new Transaction(1, 2, 3, accountDb);
		trn1.process();
		Assert.assertFalse(trn2.process());
	}
	
	@Test
	public void testProcessSucessLargeDelay() throws InterruptedException
	{
		Transaction trn1 = new Transaction(1, 2, 2, accountDb);
		Thread.sleep(20000); // 20 seconds
		Transaction trn2 = new Transaction(1, 2, 3, accountDb);
		trn1.process();
		Assert.assertTrue(trn2.process());
	}
	
	/* timeElapsed tests */
	
	@Test
	public void testTimeElapsedNoDelay()
	{
		Transaction trn = new Transaction(1, 2, 5, accountDb);
		Assert.assertFalse(trn.timeElapsed());
	}
	
	@Test
	public void testTimeElapsedSmallDelay() throws InterruptedException
	{
		Transaction trn = new Transaction(1, 2, 5, accountDb);
		Thread.sleep(5000); // 5 seconds
		Assert.assertFalse(trn.timeElapsed());
	}
	
	@Test
	public void testTimeElapsedExactDelay() throws InterruptedException
	{
		Transaction trn = new Transaction(1, 2, 5, accountDb);
		Thread.sleep(15000); // 15 seconds
		Assert.assertTrue(trn.timeElapsed());
	}
	
	@Test
	public void testTimeElapsedLargeDelay() throws InterruptedException
	{
		Transaction trn = new Transaction(1, 2, 5, accountDb);
		Thread.sleep(20000); // 20 seconds
		Assert.assertTrue(trn.timeElapsed());
	}
}