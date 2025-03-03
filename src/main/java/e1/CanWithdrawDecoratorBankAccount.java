package e1;

public class CanWithdrawDecoratorBankAccount extends DecoratorBankAccount {

    private final int maxWithdrawOverdraft;

    public CanWithdrawDecoratorBankAccount(final BankAccount bankAccount, final int maxWithdrawOverdraft) {
        super(bankAccount);
        this.maxWithdrawOverdraft = maxWithdrawOverdraft;
    }

    @Override
    public void withdraw(final int amount) {
        if (super.getBalance() < amount - maxWithdrawOverdraft) {
            throw new IllegalStateException();
        }
        super.withdraw(amount);
    }
}
