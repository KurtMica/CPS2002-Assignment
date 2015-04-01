package mt.edu.um.kurtjeantwan;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

/**
 * Test for shell code.
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
    
}
