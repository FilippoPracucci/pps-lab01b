package e1;

public class FeeDecoratorBankAccount extends DecoratorBankAccount {

    private final int withdrawFeeAmount;

    public FeeDecoratorBankAccount(final BankAccount bankAccount, final int feeAmount) {
        super(bankAccount);
        this.withdrawFeeAmount = feeAmount;
    }

    @Override
    public void withdraw(final int amount) {
        super.withdraw(amount + this.withdrawFeeAmount);
    }
}
