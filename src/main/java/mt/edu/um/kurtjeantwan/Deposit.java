package mt.edu.um.kurtjeantwan;

public class Deposit extends AtomicTransaction
{
	private Risk risk;
	
	public Deposit(int destination, int amount, AccountDatabase database, Risk risk)
	{
		super("Deposit", getSource(risk), destination, amount, database);
		this.risk = risk;
	}
	
	public Deposit(String description, int destination, int amount, AccountDatabase database, Risk risk)
	{
		super("Deposit: "+description, getSource(risk), destination, amount, database);
		this.risk = risk;
	}

	private static int getSource(Risk risk)
	{
		if(risk == Risk.high)
			return 3123;
		else
			return 8665;
	}
}