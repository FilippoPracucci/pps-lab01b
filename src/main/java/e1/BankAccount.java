package e1;

public interface BankAccount {

    /**
     * Gets the balance of the BankAccount.
     *
     * @return The actual balance.
     */
    int getBalance();

    /**
     * Deposit the amount given into the BankAccount.
     *
     * @param amount The amount to deposit.
     */
    void deposit(int amount);

    /**
     * Withdraw the given amount from the BankAccount.
     *
     * @param amount The amount to withdraw.
     * @throws IllegalStateException if the withdrawn is not possible following the account policies.
     */
    void withdraw(int amount);
}
