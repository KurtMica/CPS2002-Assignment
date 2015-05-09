package mt.edu.um.kurtjeantwan;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

public class DepositTest
{
	private AccountDatabase accountDb;
	
	@Before
	public void setup()
	{
		accountDb = new AccountDatabase();
		
		accountDb.addAccount(3123, "High Risk Deposit Source");
		accountDb.getAccount(3123).adjustBalance(100);
		accountDb.addAccount(8665, "Low Risk Deposit Source");
		accountDb.getAccount(8665).adjustBalance(100);
		
		accountDb.addAccount(0, "test");
	}
	
	@Test
	public void testProcessSuccessHigh()
	{
		AtomicTransaction deposit = new Deposit(0, 10, accountDb, Risk.high);
		deposit.process();
		
		Assert.assertEquals(90.0, accountDb.getAccount(3123).checkBalance());
	}
	
	@Test
	public void testProcessSuccessLow()
	{
		AtomicTransaction deposit = new Deposit(0, 20, accountDb, Risk.low);
		deposit.process();
		
		Assert.assertEquals(80.0, accountDb.getAccount(8665).checkBalance());
	}
	
	@Test
	public void testProcessMultipleSucess() throws Exception
	{
		Transaction trn1 = new Deposit(0, 2, accountDb, Risk.low);
        trn1.process();
		Thread.sleep(20000); // 20 seconds
		Transaction trn2 = new Deposit(0, 2, accountDb, Risk.low);
		Assert.assertTrue(trn2.process());
	}
	
	@Test
	public void testProcessMultipleFail() throws Exception
	{
		Transaction trn1 = new Deposit("Failing Source", 0, 5, accountDb, Risk.high);
		Transaction trn2 = new Deposit("Failing Source", 0, 5, accountDb, Risk.high);
		trn1.process();
		Assert.assertTrue(trn2.process());
	}
}