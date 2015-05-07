
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
    
        CompoundTransaction commision = new Commision(30, accountDb, Risk.high);
        commision.process();
        
        Assert.assertEquals(97, accountDb.getAccount(6565).checkBalance());
       
    }
    
    @Test
    public void testDestProcessSuccessHigh() throws Exception
    {
    
        CompoundTransaction commision = new Commision(30, accountDb, Risk.high);
        commision.process();
        
        Assert.assertEquals(3, accountDb.getAccount(4444).checkBalance());
       
    }
    
    
    
    @Test
    public void testSourceProcessSuccessLow() throws Exception
    {
    
        CompoundTransaction commision = new Commision(30, accountDb, Risk.high);
        commision.process();
        
        Assert.assertEquals(98.5, accountDb.getAccount(6588).checkBalance());
       
    }
    
    
    @Test
    public void testDestProcessSuccessLow() throws Exception
    {
    
        CompoundTransaction commision = new Commision(30, accountDb, Risk.high);
        commision.process();
        
        Assert.assertEquals(1.5, accountDb.getAccount(4444).checkBalance());
       
    }
    
    
    
    
    
    
    @Test
    public void testProcessMultipleSuccess() throws Exception
    {
    
        Transaction trn1 = new Commision(40, accountDb, Risk.high);
        trn1.process();
        Thread.sleep(20000);
        
        Transaction trn2 = new Commision(20, accountDb, Risk.high);
        Assert.assertTrue(trn2.process());
    
    
    }
    
    @Test
    public void testProcessMultipleFail() throws Exception
    {
    
        Transaction trn1 = new Commision(40, accountDb, Risk.high);
        trn1.process();
        
        Transaction trn2 = new Commision(20, accountDb, Risk.high);
        Assert.assertFalse(trn2.process());
    
    
    }
            
 
    
}
