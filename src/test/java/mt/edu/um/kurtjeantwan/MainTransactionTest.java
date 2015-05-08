/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mt.edu.um.kurtjeantwan;


import org.junit.Before;
import org.junit.Test;
import junit.framework.Assert;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * Test class used for MainTransaction
 */
public class MainTransactionTest {
    
    AccountDatabase accountDb;
    List<Integer> destinations;
    List<Double> amounts;
    
    @Before
    public void setup()
    {
        
        accountDb = new AccountDatabase();
        
        //Setting up Commision accounts
        accountDb.addAccount(6565, "High Risk Source");
        accountDb.getAccount(6565).adjustBalance(100);
        accountDb.addAccount(4444, "High Risk Destination");
        
        accountDb.addAccount(6588, "Low Risk Source");
        accountDb.getAccount(6588).adjustBalance(100);
        accountDb.addAccount(4445, "Low Risk Destination");
        
        
        //Setting up Deposit accounts
        accountDb.addAccount(3123, "High Risk Deposit Source");
	accountDb.getAccount(3123).adjustBalance(100);
	accountDb.addAccount(8665, "Low Risk Deposit Source");
	accountDb.getAccount(8665).adjustBalance(100);
        
        //Setting up Main accounts
        accountDb.addAccount(3143, "High Risk Main Source");
        accountDb.getAccount(3143).adjustBalance(100);
        accountDb.addAccount(3133, "Low Risk Main Source");
        accountDb.getAccount(3133).adjustBalance(100);
        
    
    
        //Setting up Testing accounts
        accountDb.addAccount(1, "Customer 1");
        accountDb.addAccount(2, "Customer 2");
       
        
        destinations = new ArrayList();
        destinations.add(1);
        destinations.add(2);
        
        amounts = new ArrayList();
        amounts.add(20.5);
        amounts.add(46.0);
        
        
    }
    
    
    @Test
    public void testAddTransactionsSuccess() throws Exception
    {
        MainTransaction trn1 = new MainTransaction(accountDb, Risk.high);
        
        Assert.assertTrue(trn1.addTransactions(destinations,amounts));
        
    }
    
    
    @Test
    public void testAddTransactionsFail() throws Exception
    {
        MainTransaction trn1 = new MainTransaction(accountDb, Risk.low);
        destinations.add(4); //added extra destination
        
        Assert.assertFalse(trn1.addTransactions(destinations,amounts));
        
    }
    
    
    
      
    @Test
    public void testDeposits() throws Exception
    {
    
    MainTransaction trn1 = new MainTransaction(accountDb, Risk.high);
    trn1.addTransactions(destinations,amounts);
    trn1.process();
    
    Assert.assertEquals(86.7, accountDb.getAccount(3123).checkBalance());
     
    
    }
    
    
    @Test
    public void testCommision() throws Exception
    {
        
        MainTransaction trn1 = new MainTransaction(accountDb, Risk.high);
        trn1.addTransactions(destinations,amounts);
        trn1.process();
        Assert.assertEquals(93.35, accountDb.getAccount(6565).checkBalance());
        
        
    }
    
    
    @Test
    public void testMainSource() throws Exception
    {
    
        MainTransaction trn1 = new MainTransaction(accountDb, Risk.high);
        trn1.addTransactions(destinations,amounts);
        trn1.process();
        Assert.assertEquals(46.8, Math.round(accountDb.getAccount(3143).checkBalance()*100.00)/100.00);
    
    }
    
    /*@Test
    public void testGetTransaction() throws Exception
    {
        
        destinations = new ArrayList();
        amounts = new ArrayList();
        
        destinations.add(4);
        amounts.add(50.0);
        
        destinations.add(3);
        amounts.add(10.5);
        
        destinations.add(5);
        amounts.add(33.5);
        
        
        MainTransaction main = new MainTransaction(accountDb, Risk.high);
        
        main.addTransactions(destinations, amounts);
        
        
        List<AtomicTransaction> test = new ArrayList();
        
        //Adding deposit and transaction to 4
        test.add(new Deposit(4,10.0,accountDb,Risk.high));
        test.add(new AtomicTransaction(main.getSource(), 4, 40, accountDb));
        
        //Adding deposit and transaction to 3
        test.add(new Deposit(3,2.1,accountDb,Risk.high));
        test.add(new AtomicTransaction(main.getSource(), 3, 8.4, accountDb));
        
        //Adding deposit and transaction to 5
        test.add(new Deposit(5,6.7,accountDb,Risk.high));
        test.add(new AtomicTransaction(main.getSource(), 5, 26.8, accountDb));
        
        
        //Adding Commision      
        test.add(new AtomicTransaction(6565, 4444, 9.4, accountDb)); //This is the commision
        
        
        
        
        Assert.assertEquals(test, main.getTransaction());
        
    
    
    }*/
    
}
