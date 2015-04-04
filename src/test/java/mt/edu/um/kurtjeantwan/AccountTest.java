package mt.edu.um.kurtjeantwan;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

/**
 * This class is used to test the Account Class.
 * Before each test, the setup function will be executed.
 */
public class AccountTest 
{
    private Account acc1;
    
    @Before
    public void setup()
    {
        acc1 = new Account(1, "First");   
    }
    
    /* getters tests */
    
    @Test
    public void testGetNumber()
    {
    	Assert.assertEquals(1, acc1.getNumber());
    }
    
    @Test
    public void testGetName()
    {
    	Assert.assertEquals("First", acc1.getName());
    }
    
    @Test
    public void testCheckLastUsed()
    {
        
        Assert.assertEquals(0,acc1.checkLastUsed());
    }
    
    @Test
    public void testSetLastUsedOne()
    {
        long currentTime = System.currentTimeMillis();
        acc1.setLastUsed(currentTime);
        Assert.assertEquals(currentTime,acc1.checkLastUsed());
    }
    
    @Test
    public void testSetLastUsedTwo() throws InterruptedException
    {
        long currentTime = System.currentTimeMillis();
        acc1.setLastUsed(currentTime);
        Thread.sleep(1000);
        currentTime = System.currentTimeMillis();
        Assert.assertEquals(currentTime,acc1.checkLastUsed());
        
    }
    
    /* adjustBalance() tests */
    
    @Test
    public void testAdjustBalancePositive()
    {
    	Assert.assertTrue(acc1.adjustBalance(5));
    }
    
    @Test
    public void testAdjustBalanceNegative()
    {
        Assert.assertFalse(acc1.adjustBalance(-2));
    }
    
    @Test
    public void testAdjustBalanceZero()
    {
    	Assert.assertTrue(acc1.adjustBalance(0));
    }
    
    /* checkBalance() tests */
    
    @Test
    public void testCheckBalance()
    {
    	Assert.assertEquals(0, acc1.checkBalance());
    }
    
    @Test
    public void testCheckBalanceChangedUp()
    {
    	acc1.adjustBalance(5);
    	Assert.assertEquals(5, acc1.checkBalance());
    }
    
    @Test
    public void testCheckBalanceChangedUpDown()
    {
    	acc1.adjustBalance(5);
    	acc1.adjustBalance(-2);
    	Assert.assertEquals(3, acc1.checkBalance());
    }
    
    @Test
    public void testCheckBalanceChangedUpUp()
    {
    	acc1.adjustBalance(5);
    	acc1.adjustBalance(2);
    	Assert.assertEquals(7, acc1.checkBalance());
    }
}