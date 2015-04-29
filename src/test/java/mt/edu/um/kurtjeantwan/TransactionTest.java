package mt.edu.um.kurtjeantwan;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class TransactionTest
{
	private AccountDatabase accountDb;
	
	@Before
	public void setup()
	{
		accountDb = new AccountDatabase();
		accountDb.addAccount(1, "Mark");
		accountDb.getAccount(1).adjustBalance(5);
		accountDb.addAccount(2, "Mary");
	}
	
	/* AtomicTransaction tests */
	
	@Test
	public void testProcessSucess()
	{
		Transaction trn = new AtomicTransaction(1, 2, 3, accountDb);
		Assert.assertTrue(trn.process());
	}
	
	@Test
	public void testProcessFailAmount()
	{
		Transaction trn = new AtomicTransaction(1, 2, 10, accountDb);
		Assert.assertFalse(trn.process());
	}
	
	@Test
	public void testProcessMultipleFailSmallDelay() throws InterruptedException
	{
		
		Transaction trn1 = new AtomicTransaction(1, 2, 2, accountDb);
                trn1.process();
		Thread.sleep(5000); // 5 seconds
		Transaction trn2 = new AtomicTransaction(1, 2, 3, accountDb);
		Assert.assertFalse(trn2.process());
	}
	
	@Test
	public void testProcessMultipleSucessLargeDelay() throws InterruptedException
	{
		
		Transaction trn1 = new AtomicTransaction(1, 2, 2, accountDb);
                trn1.process();
		Thread.sleep(20000); // 20 seconds
		Transaction trn2 = new AtomicTransaction(1, 2, 3, accountDb);
		Assert.assertTrue(trn2.process());
	}
	
}