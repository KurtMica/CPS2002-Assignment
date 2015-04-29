/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mt.edu.um.kurtjeantwan;


import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

/**
 * This class is used to test the Compound Transaction Class.
 * Before each test, the setup function will be executed. 
 */
public class CompoundTransactionTest 
{
    
    private AccountDatabase accountDb;
    private Transaction trn1;
    private Transaction trn2;
    
    @Before
    public void setup() 
    {
        accountDb = new AccountDatabase();
		accountDb.addAccount(1, "Mark");
		accountDb.getAccount(1).adjustBalance(10);
		accountDb.addAccount(2, "Mary");
        accountDb.getAccount(2).adjustBalance(5);
        accountDb.addAccount(3,"Joe");
        accountDb.addAccount(4, "Simon");
    }
    
    @Test
    public void testProcessSucess()
    {
        trn1 = new AtomicTransaction("first",1,3,4,accountDb);
        trn2 = new AtomicTransaction("second",2,4,1,accountDb);
        CompoundTransaction trnComp = new CompoundTransaction();
        trnComp.addTransaction(trn1);
        trnComp.addTransaction(trn2);
        Assert.assertTrue(trnComp.process());
    
    }
    
    @Test
    public void testProcessSuccessBound()
    {
        trn1 = new AtomicTransaction("first",1,3,10,accountDb);
        trn2 = new AtomicTransaction("second",2,4,5,accountDb);
        CompoundTransaction trnComp = new CompoundTransaction();
        trnComp.addTransaction(trn1);
        trnComp.addTransaction(trn2);
        Assert.assertTrue(trnComp.process());
    }
    
    @Test
    public void testProcessSingleTransFailAmount()
    {
        trn1 = new AtomicTransaction("first",1,3,13,accountDb);
        CompoundTransaction trnComp = new CompoundTransaction();
        trnComp.addTransaction(trn1);
        Assert.assertFalse(trnComp.process());
    }
    
    @Test
    public void testTimeElapsedNoDelay()
    {
        trn1 = new AtomicTransaction("first",1,3,10,accountDb);
        trn2 = new AtomicTransaction("second",3,4,5,accountDb);
        CompoundTransaction trnComp = new CompoundTransaction(); //First Compound Transaction
        CompoundTransaction trnComp2 = new CompoundTransaction(); //Second Compound Transaction
        trnComp.addTransaction(trn1);
        trnComp2.addTransaction(trn2);
        trnComp.process();
        Assert.assertFalse(trnComp2.process());
    }
    
    @Test
    public void testTimeElapsedExactDelay() throws InterruptedException
    {
        trn1 = new AtomicTransaction("first",1,3,10,accountDb);
        trn2 = new AtomicTransaction("second",2,4,5,accountDb);
        CompoundTransaction trnComp = new CompoundTransaction(); //First Compound Transaction
        CompoundTransaction trnComp2 = new CompoundTransaction(); //Second Compound Transaction
        trnComp.addTransaction(trn1);
        trnComp2.addTransaction(trn2);
        trnComp.process();
        Thread.sleep(15000);
        Assert.assertTrue(trnComp2.process());
    }
    
    @Test
    public void testProcessSuccessMultipleTrans()
    {
        accountDb.addAccount(5, "Saviour");
        accountDb.addAccount(6, "Nick");
        accountDb.getAccount(6).adjustBalance(20);
        trn1 = new AtomicTransaction("first",1,3,4,accountDb);
        trn2 = new AtomicTransaction("second",2,4,1,accountDb);
        Transaction trn3 = new AtomicTransaction("third",6,5,7,accountDb);
        CompoundTransaction trnComp = new CompoundTransaction();
        trnComp.addTransaction(trn1);
        trnComp.addTransaction(trn2);
        trnComp.addTransaction(trn3);
        Assert.assertTrue(trnComp.process());
    }
}
