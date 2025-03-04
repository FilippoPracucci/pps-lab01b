package e1;

import java.util.function.Predicate;

public class ConditionalFeeDecoratorBankAccount extends DecoratorBankAccount {

    private final Predicate<Integer> withdrawFeeCondition;
    private final int withdrawFeeAmountIfConditionTrue;
    private final int withdrawFeeAmountIfConditionFalse;

    public ConditionalFeeDecoratorBankAccount(
            final BankAccount bankAccount,
            final Predicate<Integer> withdrawFeeCondition,
            final int withdrawFeeAmountIfConditionTrue,
            final int withdrawFeeAmountIfConditionFalse
    ) {
        super(bankAccount);
        this.withdrawFeeCondition = withdrawFeeCondition;
        this.withdrawFeeAmountIfConditionTrue = withdrawFeeAmountIfConditionTrue;
        this.withdrawFeeAmountIfConditionFalse = withdrawFeeAmountIfConditionFalse;
    }

    @Override
    public void withdraw(final int amount) {
        final int withdrawFeeAmount;
        if (withdrawFeeCondition.test(amount)) {
            withdrawFeeAmount = withdrawFeeAmountIfConditionTrue;
        } else {
            withdrawFeeAmount = withdrawFeeAmountIfConditionFalse;
        }
        super.withdraw(amount + withdrawFeeAmount);
    }
}
