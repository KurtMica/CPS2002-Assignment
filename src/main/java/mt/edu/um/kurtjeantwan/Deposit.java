package mt.edu.um.kurtjeantwan;

public class Deposit extends AtomicTransaction
{
	private Risk risk;
	
	protected Deposit(int destination, double amount, AccountDatabase database, Risk risk)
	{
		super("Deposit", getSource(risk), destination, amount, database);
		this.risk = risk;
	}
	
	protected Deposit(String description, int destination, double amount, AccountDatabase database, Risk risk)
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