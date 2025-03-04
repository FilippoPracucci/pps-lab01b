package e1;

import java.util.function.Predicate;

public interface BankAccountFactory {

    /**
     * Create a Silver BankAccount.
     *
     * @param maxWithdrawOverdraft The maximum amount of overdraft in case of withdraw.
     * @param withdrawFeeAmount The withdrawal fee amount.
     *
     * @return The Silver BankAccount created.
     */
    BankAccount createSilverBankAccount(int maxWithdrawOverdraft, int withdrawFeeAmount);

    /**
     * Create a Gold BankAccount.
     *
     * @param maxWithdrawOverdraft The maximum amount of overdraft in case of withdraw.
     * @param withdrawFeeAmount The withdrawal fee amount.
     *
     * @return The Gold BankAccount created.
     */
    BankAccount createGoldBankAccount(int maxWithdrawOverdraft, int withdrawFeeAmount);

    /**
     * Create a Bronze BankAccount.
     * The withdrawal fee amount depends on a condition.
     *
     * @param maxWithdrawOverdraft The maximum amount of overdraft in case of withdraw.
     * @param withdrawFeeCondition The condition to define the withdrawal fee amount.
     * @param withdrawFeeAmountIfConditionTrue The withdrawal fee amount if the condition is true.
     * @param withdrawFeeAmountIfConditionFalse The withdrawal fee amount if the condition is false.
     *
     * @return The Bronze BankAccount created.
     */
    BankAccount createBronzeBankAccount(
            int maxWithdrawOverdraft,
            Predicate<Integer> withdrawFeeCondition,
            int withdrawFeeAmountIfConditionTrue,
            int withdrawFeeAmountIfConditionFalse
    );

}
