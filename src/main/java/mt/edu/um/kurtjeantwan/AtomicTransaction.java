package mt.edu.um.kurtjeantwan;
import java.util.List;
import java.util.ArrayList;
/**
 * This is the Atomic Transaction Class.
 * Handles the transfer of money between 2 accounts, whilst ensuring that
 * the source Account has enough money as well as that both accounts exist.
 */
public class AtomicTransaction extends Transaction implements Comparable<AtomicTransaction>
{
	private int sourceAccountNumber;
	private int destinationAccountNumber;
	private double amount;
    protected AccountDatabase accountDb;
	
	protected AtomicTransaction(int source, int destination, double amount, AccountDatabase database)
	{
		super("Atomic Transaction");
                this.accountDb = database;
		sourceAccountNumber = source;
		destinationAccountNumber = destination;
		this.amount = amount;
	}
        
    protected AtomicTransaction(String desc, int source, int destination, double amount, AccountDatabase database)
	{
        super(desc);
                this.accountDb = database;
		sourceAccountNumber = source;
		destinationAccountNumber = destination;
		this.amount = amount;
	}
	
    protected boolean process()
	{
		Account src = accountDb.getAccount(sourceAccountNumber);
		Account dst = accountDb.getAccount(destinationAccountNumber);
		// check if account exists
                
		if((src != null) && (dst != null))
		{
			while(!timeElapsed());
			// check if there is enough money in source
			if(src.adjustBalance(-amount))
			{
				dst.adjustBalance(amount);
                long currentTime = System.currentTimeMillis();
                // modify lastUsed
                src.setLastUsed(currentTime);
                dst.setLastUsed(currentTime);
                return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
             
	}
	
	protected boolean timeElapsed()
	{
		long currentTime = System.currentTimeMillis();
		if((Math.abs(currentTime - accountDb.getAccount(sourceAccountNumber).checkLastUsed()) >= 15000)
				&& (Math.abs(currentTime - accountDb.getAccount(destinationAccountNumber).checkLastUsed()) >= 15000))
			return true;
		else
			return false;
	}
        
    public List<AtomicTransaction> getTransaction()
    {
        List<AtomicTransaction> out = new ArrayList();
        out.add(this);
        return out;
    }
    
    public int getSource()
    {
        return this.sourceAccountNumber;
    }
    
    public int getDestination()
    {
        return this.destinationAccountNumber;
    }
    
    public double getAmount()
    {
        return this.amount;
    }
    
    @Override
    public int compareTo(AtomicTransaction other)
    {
         /*if((this.amount < other.amount)) return 1;
         if((this.amount > other.amount)) return -1;
         return 0;*/
        return Double.compare(this.amount, other.amount);
    }
}