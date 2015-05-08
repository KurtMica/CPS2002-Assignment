
package mt.edu.um.kurtjeantwan;

import java.util.List;
import java.util.ArrayList;

public class MainTransaction extends CompoundTransaction {
    
    private AccountDatabase accountDb;
    private Risk risk;
    private Commision commision;
    
    public MainTransaction(AccountDatabase db, Risk risk)
    {
         super("Main Transaction");
         this.accountDb = db;
         this.risk = risk;
         this.commision = new Commision(this.accountDb, this.risk);
    
    }
    
    public boolean addTransactions(List<Integer> destinations, List<Double> amounts) throws Exception
    {
    
        if(destinations.size() != amounts.size())
        {
            return false;
        
        }
        
        double sum = 0;
        
        
        for(int i =0; i<destinations.size(); i++)
        {
            
            CompoundTransaction temp = new CompoundTransaction();
            temp.addTransaction(new Deposit(destinations.get(i), 0.20 * amounts.get(i), this.accountDb, this.risk));
            temp.addTransaction(new AtomicTransaction(getSource(), destinations.get(i), amounts.get(i) * 0.8, this.accountDb));
            
            this.subTransactions.add(temp);
            sum += amounts.get(i);
        
        
        }
        this.commision.addCommision(sum);
        return true;
    
    }
    
    
    public int getSource()
    {
        
        if(this.risk == Risk.high){
        
            return 3143;
        
        } else {
        
            return 3133;
        }
    
    
    
    }
    
    public boolean process() throws Exception
    {
        super.process();
        this.commision.process();
        return true;
    
    
    }
    
    public List<AtomicTransaction>getTransaction()
    {
        List<AtomicTransaction> out = new ArrayList();
        out = super.getTransaction();
        out.addAll(this.commision.getTransaction());
        return out;
    
    
    }
    
    
    
    
    
    
}
