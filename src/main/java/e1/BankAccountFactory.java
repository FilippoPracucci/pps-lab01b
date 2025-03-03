package e1;

public interface BankAccountFactory {

    /**
     * Create a SilverBankAccount
     *
     * @param maxWithdrawOverdraft The maximum amount of overdraft in case of withdraw.
     * @param withdrawFeeAmount The withdrawn fee amount.
     *
     * @return The SilverBankAccount created.
     */
    BankAccount createSilverBankAccount(int maxWithdrawOverdraft, int withdrawFeeAmount);

}
