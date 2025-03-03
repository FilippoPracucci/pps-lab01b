package e1;

public interface BankAccountFactory {

    /**
     * Create a Silver BankAccount
     *
     * @param maxWithdrawOverdraft The maximum amount of overdraft in case of withdraw.
     * @param withdrawFeeAmount The withdrawn fee amount.
     *
     * @return The Silver BankAccount created.
     */
    BankAccount createSilverBankAccount(int maxWithdrawOverdraft, int withdrawFeeAmount);

    /**
     * Create a Gold BankAccount
     *
     * @param maxWithdrawOverdraft The maximum amount of overdraft in case of withdraw.
     * @param withdrawFeeAmount The withdrawn fee amount.
     *
     * @return The Gold BankAccount created.
     */
    BankAccount createGoldBankAccount(int maxWithdrawOverdraft, int withdrawFeeAmount);

}
