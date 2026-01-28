package auditoriski.aud1;
import java.util.ArrayList;

abstract class Account {

    private String holderName;
    private int number;
    private double balance;

    public Account(String holderName, int number, double balance){
        this.holderName = holderName;
        this.number = number;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void addAmount(double amount){
        balance+= amount;
    }
    public void withdrawAmount(double amount){
        balance -= amount;
    }

}

class NonInterestCheckingAccount extends Account{
    public NonInterestCheckingAccount(String holderName, int number, double balance) {
        super(holderName, number, balance);
    }
}

interface InterestBearingAccount {
    public void addInterest();
}

class bank {
    ArrayList <Account> accounts;

    public bank() {
        this.accounts = new ArrayList<Account>();
    }

    public void addAccount(Account account){
        accounts.add(account);
    }

    public double totalAssets(){
        // returns the sum of all account balances
        double sum =0 ;
        for(Account account: accounts){
            sum+=account.getBalance();
        }
        return sum;
    }

    public void addInterest(){
        for(Account account: accounts){
            if(account instanceof InterestBearingAccount){
                ((InterestBearingAccount)account).addInterest();
            }
        }
    }
}


class InterestCheckingAccount extends Account implements InterestBearingAccount {
    public InterestCheckingAccount(String holderName, int number, double balance) {
        super(holderName, number, balance);
    }

    @Override
    public void addInterest() {
        this.addAmount((this.getBalance()*3)/100);
    }
}



class PlatinumCheckingAccount extends InterestCheckingAccount {

    public PlatinumCheckingAccount(String holderName, int number, double balance) {
        super(holderName, number, balance);
    }

    @Override
    public void addInterest(){
        this.addAmount((this.getBalance()*6)/100);
    }
}
