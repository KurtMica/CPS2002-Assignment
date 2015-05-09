package mt.edu.um.kurtjeantwan;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public abstract class Transaction
{
	protected String description;
	protected boolean isRoot;
	
	public Transaction(String description)
	{
		this.description = description;
		this.isRoot = true;
	}
	
	protected abstract boolean process() throws Exception;
        
    protected abstract List<AtomicTransaction> getTransaction();
    
    public List<AtomicTransaction> getTransectionAscend()
    {
        List<AtomicTransaction> out = new ArrayList();
        
        out = this.getTransaction();
        Collections.sort(out);
        return out;
    }
    
    public List<AtomicTransaction> getTransectionDescend()
    {
        List<AtomicTransaction> out = new ArrayList();
        
        out = this.getTransectionAscend();
        Collections.reverse(out);
        
        return out;
    }
    
    public List<AtomicTransaction> getTransectionFilter(int src)
    {
        List<AtomicTransaction> out = new ArrayList();
        
        for(AtomicTransaction i : this.getTransectionAscend())
        {
            if(i.getSource() == src)
                out.add(i);
        }
        return out;       
    }
}