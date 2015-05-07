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
    public void testTransactionsSuccess()
    {
        MainTransaction trn1 = new MainTransaction(accountDb, Risk.high);
        trn1.addTransactions(destinations,amounts);
        Assert.assertTrue(trn1.process());
    
    
    }
    
    
    @Test
    public void testTransactionsFail()
    {
        MainTransaction trn1 = new MainTransaction(accountDb, Risk.high);
        destinations.add(4); //added extra destination
        trn1.addTransactions(destinations,amounts);
        Assert.assertFalse(trn1.process());
        
    }
    
      
    @Test
    public void testDeposits()
    {
    
    MainTransaction trn1 = new MainTransaction(accountDb, Risk.high);
    trn1.addTransactions(destinations,amounts);
    trn1.process();
    
    Assert.assertEquals(69.7, accountDb.getAccount(3123).checkBalance());
     
    
    }
    
    
    @Test
    public void testCommision()
    {
        
        MainTransaction trn1 = new MainTransaction(accountDb, Risk.high);
        trn1.addTransactions(destinations,amounts);
        trn1.process();
        Assert.assertEquals(93.89, accountDb.getAccount(6565).checkBalance());
        
        
    }
    
    
    @Test
    public void testMainSource()
    {
    
        MainTransaction trn1 = new MainTransaction(accountDb, Risk.high);
        trn1.addTransactions(destinations,amounts);
        trn1.process();
        Assert.assertEquals(26.85, accountDb.getAccount(3143).checkBalance());
    
    }
    
}
