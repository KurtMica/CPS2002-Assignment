package mt.edu.um.kurtjeantwan;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

/**
 * This class is used to test the AccountDatabase Class.
 * Before each test, the setup function will be executed. 
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
        accountDb.addAccount(1, "Michael");
        Assert.assertFalse(accountDb.deleteAccount(2));
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
    
    /* getAccount tests */
    
    @Test
    public void testGetAccountFail()
    {
    	Assert.assertNull(accountDb.getAccount(1));
    }
    
    @Test
    public void testGetAccountSucess()
    {
    	accountDb.addAccount(1, "Mark");
    	Assert.assertNotNull(accountDb.getAccount(1));
    }
    
    @Test
    public void testGetAccountFail2()
    {
    	accountDb.addAccount(1, "Mark");
    	accountDb.deleteAccount(1);
    	Assert.assertNull(accountDb.getAccount(1));
    }
    
    @Test
    public void testGetAccountSucess2()
    {
    	accountDb.addAccount(1, "Mark");
    	accountDb.addAccount(2, "Mary");
    	Assert.assertNotNull(accountDb.getAccount(2));
    }
    
    @Test
    public void testGetAccountFail3()
    {
    	accountDb.addAccount(1, "Mark");
    	Assert.assertNull(accountDb.getAccount(2));
    }
    
    /* getSize tests */
    
    @Test
    public void testGetSizeEmpty()
    {
    	Assert.assertEquals(0, accountDb.getSize());
    }
    
    @Test
    public void testGetSizeAdded()
    {
    	accountDb.addAccount(1, "Mark");
    	Assert.assertEquals(1, accountDb.getSize());
    }
    
    @Test
    public void testGetSizeZero()
    {
    	accountDb.addAccount(1, "Mark");
    	accountDb.deleteAccount(1);
    	Assert.assertEquals(0, accountDb.getSize());
    }
}