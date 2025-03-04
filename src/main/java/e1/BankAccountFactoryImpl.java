package e1;

import java.util.function.Predicate;

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

    @Override
    public BankAccount createBronzeBankAccount(
            final int maxWithdrawOverdraft,
            final Predicate<Integer> withdrawFeeCondition,
            final int withdrawFeeAmountIfConditionTrue,
            final int withdrawFeeAmountIfConditionFalse
    ) {
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
