
package mt.edu.um.kurtjeantwan;

/**
 *
 * @author JeanT
 */
public class Commision extends CompoundTransaction{
    
    private AccountDatabase database;
    private Risk risk;
    
    protected Commision( AccountDatabase database,Risk risk )
    {
    
        super("Commision");
        this.risk = risk;
        this.database = database;
        
    
    }
    
 
    public int getSource()
    {
        
        if(this.risk == Risk.high){
        
            return 6565;
        
        } else {
        
            return 6588;
        }
    
        
    }
    
    public int getDestination()
    {
        
        if(this.risk == Risk.high){
        
            return 4444;
        
        } else {
        
            return 4445;
        }
    }
    
    public double computeAmount(double amount)
    {
    
        if(this.risk == Risk.high){
        
            return 0.1*amount;
        
        } else {
        
            return 0.05*amount;
        }
    }
    
    public void addCommision(double amount)
    {
        this.subTransactions.add(new AtomicTransaction(getSource(),getDestination(),computeAmount(amount),database));    
    
    }
    
}
