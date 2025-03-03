package e1;

public class BankAccountFactoryImpl implements BankAccountFactory {

    @Override
    public BankAccount createSilverBankAccount(final int maxWithdrawOverdraft, final int withdrawFeeAmount) {
        return new FeeDecoratorBankAccount(
                new CanWithdrawDecoratorBankAccount(
                        new CoreBankAccount(),
                        maxWithdrawOverdraft
                ),
                withdrawFeeAmount
        );
    }

    @Override
    public BankAccount createGoldBankAccount(final int maxWithdrawOverdraft, final int withdrawFeeAmount) {
        return new FeeDecoratorBankAccount(
                new CanWithdrawDecoratorBankAccount(
                        new CoreBankAccount(),
                        maxWithdrawOverdraft
                ),
                withdrawFeeAmount
        );
    }

}
