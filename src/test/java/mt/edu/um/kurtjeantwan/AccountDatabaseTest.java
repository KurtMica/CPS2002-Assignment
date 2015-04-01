
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
   
    
    /* addAccount Tests*/
    
    @Test
    public void addAccountTestSuccess()
    {
        Assert.assertEquals(true, accountDb.addAccount(1, "Joe"));
    }
    
    @Test
    public void addAccountTestFail()
    {
        accountDb.addAccount(1, "Michael");
        Assert.assertEquals(false, accountDb.addAccount(1, "Mary"));
    }
    
    
    /* deleteAccount Tests*/
    
    @Test
    public void deleteAccountTestSuccess()
    {
        accountDb.addAccount(1, "Michael");
        Assert.assertEquals(true, accountDb.deleteAccount(1));    
    }
    
    @Test
    public void deleteAccountTestFail()
    {
        Assert.assertEquals(false, accountDb.deleteAccount(1));
    }
   
}
