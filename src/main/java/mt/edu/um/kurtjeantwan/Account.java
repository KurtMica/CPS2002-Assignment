package mt.edu.um.kurtjeantwan;

/**
 * This is the Account class.
 *
 */

public class Account 
{
	private int accountNumber;
        private String accountName;
        private long accountBalance;
        
        public Account(int num, String Name)
        {
            accountNumber = num;
            accountName = Name;
            accountBalance = 0;
        }
        
        public boolean adjustBalance(long amount)
        {
            if(amount >= 0)
            {
                accountBalance += amount;
                return true;
            }
            if(amount < 0 && (Math.abs(amount) <= accountBalance))
            {
                accountBalance -= Math.abs(amount);
                return true;
            }
            else
            {
                return false;
            }
            
        
        }
}
