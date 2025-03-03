package e1;

public class DecoratorBankAccount implements BankAccount {

    private final BankAccount bankAccount;

    public DecoratorBankAccount(final BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Override
    public int getBalance() {
        return this.bankAccount.getBalance();
    }

    @Override
    public void deposit(final int amount) {
        this.bankAccount.deposit(amount);
    }

    @Override
    public void withdraw(int amount) {
        this.bankAccount.withdraw(amount);
    }
}
