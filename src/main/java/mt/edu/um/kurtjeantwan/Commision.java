
package mt.edu.um.kurtjeantwan;

/**
 *
 * @author JeanT
 */
public class Commision extends CompoundTransaction{
    
    private Risk risk;
    
    public Commision(double amount, AccountDatabase database,Risk ris ){
    
        super("Commision");
        
    
    }
    
 
    public int getSource(){
    
        return 1;
    }
    
    public int getDestination(){
        return 1;
    }
    
    public double computeAmount(){
    
        return 1;
    }
    
}
