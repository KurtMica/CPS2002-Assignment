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
	
	/* timeElapsed tests */
	
	@Test
	public void testTimeElapsedNoDelay()
	{
		Transaction trn = new Transaction(1, 2, 5, accountDb);
		Assert.assertFalse(trn.timeElapsed());
	}
	
	@Test
	public void testTimeElapsedSmallDelay()
	{
		Transaction trn = new Transaction(1, 2, 5, accountDb);
		Thread.sleep(5000); // 5 seconds
		Assert.assertFalse(trn.timeElapsed());
	}
	
	@Test
	public void testTimeElapsedExactDelay()
	{
		Transaction trn = new Transaction(1, 2, 5, accountDb);
		Thread.sleep(15000); // 15 seconds
		Assert.assertTrue(trn.timeElapsed());
	}
	
	@Test
	public void testTimeElapsedLargeDelay()
	{
		Transaction trn = new Transaction(1, 2, 5, accountDb);
		Thread.sleep(20000); // 20 seconds
		Assert.assertTrue(trn.timeElapsed());
	}
}