/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mt.edu.um.kurtjeantwan;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author JeanT
 */
public class DepositTransactionTest {
    
    private AccountDatabase accountDb;
    
    @Before
    public void setup() {
        
        accountDb = new AccountDatabase();
        accountDb.addAccount(3123, "High Risk");
        accountDb.getAccount(3123).adjustBalance(1000);
        accountDb.addAccount(8665, "Low Risk");
        accountDb.getAccount(8665).adjustBalance(1000);
        accountDb.addAccount(1, "Test Destination");
              
    }
    
    @Test
    public void testHighRisk(){
    
        AtomicTransaction t1 = new DepositTransaction(1, 30, accountDb, Risk.HIGH);
        assert.Equals(3123, t1.getSource());
    
    }
    
    @Test
    public void testLowRisk(){
    
        AtomicTransaction t1 = new DepositTransaction(1, 40, accountDb, Risk.LOW);
        assert.Equals(8665, t1.getSource());
    }
    
    @Test
    public void testProcessSucess(){
        
         AtomicTransaction t1 = new DepositTransaction(1, 40, accountDb, Risk.LOW);
         assertTrue(t1.process());
       
    }
    
    
    
}
