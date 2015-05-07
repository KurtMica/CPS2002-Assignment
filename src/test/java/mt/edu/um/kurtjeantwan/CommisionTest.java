
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
    public void testGetSourceHigh()
    {
        
        CompoundTransaction commision = new Commision(30, accountDb, Risk.high);
        
        Assert.assertEquals(6565, commision.getSource);
    
    
    }
    
    
    @Test
    public void testGetSourceLow()
    {
        
        CompoundTransaction commision = new Commision(30, accountDb, Risk.low);
        
        Assert.assertEquals(6588, commision.getSource);
        
    }
    
    @Test
    public void testGetDestinationHigh()
    {
        
        CompoundTransaction commision = new Commision(30, accountDb, Risk.high);
        
        Assert.assertEquals(4444, commision.getDestination);
    
    
    }
    
    @Test
    public void testGetDestinationLow()
    {
        
        CompoundTransaction commision = new Commision(30, accountDb, Risk.low);
        
        Assert.assertEquals(4445, commision.getDestination);
    
    }
    
    @Test
    public void computeAmountHigh()
    {
        
       CompoundTransaction commision = new Commision(30, accountDb, Risk.high);
       
       Assert.assertEquals(3, commision.computeAmount);    
    }
    
    
    @Test
    public void computeAmountLow()
    {
        
       CompoundTransaction commision = new Commision(30, accountDb, Risk.low);
       
       Assert.assertEquals(1.5, commision.computeAmount);    
    }
    
    
    @Test
    public void testProcessSuccessHigh() throws Exception
    {
    
        CompoundTransaction commision = new Commision(30, accountDb, Risk.high);
        commision.process();
        
        Assert.assertEquals(97, accountDb.getAccount(6565).checkBalance());
       
    }
    
    @Test
    public void testProcessSuccessLow() throws Exception
    {
    
        CompoundTransaction commision = new Commision(30, accountDb, Risk.high);
        commision.process();
        
        Assert.assertEquals(98.5, accountDb.getAccount(6588).checkBalance());
       
    }
            
    
    
    
    
    
    
    
}
