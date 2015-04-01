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
    
    @Test
    public void testAccountBalancePositive()
    {
    	Assert.assertEquals(true, acc1.adjustBalance(5));
    }
    
    @Test
    public void testAccountBalanceNegative()
    {
        Assert.assertEquals(false, acc1.adjustBalance(-2));
    }
    
    @Test
    public void testAccountBalanceZero()
    {
    	Assert.assertEquals(true, acc.adjustBalance(0));
    }
    
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