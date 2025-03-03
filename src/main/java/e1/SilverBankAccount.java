package e1;

public class SilverBankAccount implements BankAccount {

    private static final int WITHDRAW_FEE_AMOUNT = 1;
    private final BankAccount bankAccount;

    public SilverBankAccount(final BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public int getBalance() {
        return this.bankAccount.getBalance();
    }

    public void deposit(int amount) {
        this.bankAccount.deposit(amount);
    }

    public void withdraw(int amount) {
        if (this.getBalance() < amount){
            throw new IllegalStateException();
        }
        this.bankAccount.withdraw(amount + WITHDRAW_FEE_AMOUNT);
    }
}
