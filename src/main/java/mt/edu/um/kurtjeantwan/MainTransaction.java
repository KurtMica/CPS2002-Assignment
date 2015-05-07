
package mt.edu.um.kurtjeantwan;

import java.util.List;

public class MainTransaction extends CompoundTransaction {
    
    private AccountDatabase accountdB;
    private Risk risk;
    private Commision commision;
    
    public MainTransaction(AccountDatabase db, Risk risk)
    {
         
    
    }
    
    public boolean addTransactions(List<Integer> destinations, List<Double> amounts)
    {
    
        return false;
    
    }
    
    
    
}
