
package mt.edu.um.kurtjeantwan;


import org.junit.Before;
import org.junit.Test;
import junit.framework.Assert;

/**
 *
 * This class is used to test the commision class
 */
public class CommisionTest {
    
    private AccountDatabase accountDb;
    
    @Before
    public void setup() 
    {
        
        accountDb = new AccountDatabase();
        
        accountDb.addAccount(6565, "High Risk Source");
        accountDb.getAccount(6565).adjustBalance(100);
        accountDb.addAccount(4444, "High Risk Destination");
        
        accountDb.addAccount(6588, "Low Risk Source");
        accountDb.getAccount(6588).adjustBalance(100);
        accountDb.addAccount(4445, "Low Risk Destination");
        
        
    }
    
        
    @Test
    public void testSourceProcessSuccessHigh() throws Exception
    {
    
        Commision commision = new Commision(accountDb, Risk.high);
        commision.addCommision(30.0);
        commision.process();
        
        Assert.assertEquals(97.0, accountDb.getAccount(6565).checkBalance());
       
    }
    
    @Test
    public void testDestProcessSuccessHigh() throws Exception
    {
    
        Commision commision = new Commision(accountDb, Risk.high);
        commision.addCommision(30.0);
        commision.process();
        
        Assert.assertEquals(3., accountDb.getAccount(4444).checkBalance());
       
    }
    
    
    
    @Test
    public void testSourceProcessSuccessLow() throws Exception
    {
    
        Commision commision = new Commision(accountDb, Risk.low);
        commision.addCommision(30.0);
        commision.process();
        
        Assert.assertEquals(98.5, accountDb.getAccount(6588).checkBalance());
       
    }
    
    
    @Test
    public void testDestProcessSuccessLow() throws Exception
    {
    
        Commision commision = new Commision(accountDb, Risk.low);
        commision.addCommision(30.0);
        commision.process();
        
        Assert.assertEquals(1.5, accountDb.getAccount(4445).checkBalance());
       
    }
    
    
    
    
    
    
            
 
    
}
