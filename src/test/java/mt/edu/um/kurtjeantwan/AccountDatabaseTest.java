
package mt.edu.um.kurtjeantwan;


import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

/**
This class is used to test the AccountDatabase Class.
 Before each test, the setup function will be executed. 
 */
public class AccountDatabaseTest {
    
    private AccountDatabase accountDb;
    
    @Before
    public void setup()
    {
        accountDb = new AccountDatabase();
    }
   
    /* addAccount tests */
    
    @Test
    public void testAddAccountSuccess()
    {
        Assert.assertTrue(accountDb.addAccount(1, "Joe"));
    }
    
    @Test
    public void testAddAccountFail()
    {
        accountDb.addAccount(1, "Michael");
        Assert.assertFalse(accountDb.addAccount(1, "Mary"));
    }
    
    /* deleteAccount tests */
    
    @Test
    public void testDeleteAccountSuccess()
    {
        accountDb.addAccount(1, "Michael");
        Assert.assertTrue(accountDb.deleteAccount(1));    
    }
    
    @Test
    public void testDeleteAccountFail()
    {
        Assert.assertFalse(accountDb.deleteAccount(1));
    }
   
    /* validateNumber tests */
    
    @Test
    public void testValidateNumberSuccess()
    {
    	Assert.assertTrue(accountDb.validateNumber(1));
    }
    
    @Test
    public void testValidateNumberFail()
    {
    	accountDb.addAccount(1, "Mark");
    	Assert.assertFalse(accountDb.validateNumber(1));
    }
    
    @Test
    public void testValidateNumberSuccess2()
    {
    	accountDb.addAccount(1, "Mark");
    	accountDb.deleteAccount(1);
    	Assert.assertEquals(true, accountDb.validateNumber(1));
    }
}
