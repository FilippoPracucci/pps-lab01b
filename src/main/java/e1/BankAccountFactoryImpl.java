package e1;

import java.util.function.Predicate;

public class BankAccountFactoryImpl implements BankAccountFactory {

    @Override
    public BankAccount createSilverBankAccount() {
        final int maxWithdrawOverdraft = 0;
        final int withdrawFeeAmount = 1;
        return new FeeDecoratorBankAccount(
                new CanWithdrawDecoratorBankAccount(
                        new CoreBankAccount(),
                        maxWithdrawOverdraft
                ),
                withdrawFeeAmount
        );
    }

    @Override
    public BankAccount createGoldBankAccount() {
        final int maxWithdrawOverdraft = 500;
        final int withdrawFeeAmount = 0;
        return new FeeDecoratorBankAccount(
                new CanWithdrawDecoratorBankAccount(
                        new CoreBankAccount(),
                        maxWithdrawOverdraft
                ),
                withdrawFeeAmount
        );
    }

    @Override
    public BankAccount createBronzeBankAccount() {
        final int maxWithdrawOverdraft = 0;
        final int withdrawFeeTurningPoint = 100;
        final Predicate<Integer> withdrawFeeCondition = withdrawAmount -> (withdrawAmount < withdrawFeeTurningPoint);
        final int withdrawFeeAmountIfConditionTrue = 0;
        final int withdrawFeeAmountIfConditionFalse = 1;
        return new ConditionalFeeDecoratorBankAccount(
                new CanWithdrawDecoratorBankAccount(
                        new CoreBankAccount(),
                        maxWithdrawOverdraft
                ),
                withdrawFeeCondition,
                withdrawFeeAmountIfConditionTrue,
                withdrawFeeAmountIfConditionFalse
        );
    }

}
