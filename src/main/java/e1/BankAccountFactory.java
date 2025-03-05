package e1;

public interface BankAccountFactory {

    /**
     * Create a Silver BankAccount.
     *
     * @return The Silver BankAccount created.
     */
    BankAccount createSilverBankAccount();

    /**
     * Create a Gold BankAccount.
     *
     * @return The Gold BankAccount created.
     */
    BankAccount createGoldBankAccount();

    /**
     * Create a Bronze BankAccount.
     *
     * @return The Bronze BankAccount created.
     */
    BankAccount createBronzeBankAccount();

}
