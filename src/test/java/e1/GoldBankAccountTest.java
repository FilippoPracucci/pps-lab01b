package e1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GoldBankAccountTest extends AbstractBankAccountTest {

    @Override
    protected BankAccount createBankAccount() {
        return this.bankAccountFactory.createGoldBankAccount();
    }

    @Test
    @Override
    public void testCanWithdraw() {
        final int withdrawAmount = 200;
        final int expectedBalanceAfterWithdrawGold = STANDARD_DEPOSIT_AMOUNT - withdrawAmount;
        this.bankAccount.deposit(STANDARD_DEPOSIT_AMOUNT);
        this.bankAccount.withdraw(withdrawAmount);
        assertEquals(expectedBalanceAfterWithdrawGold, this.bankAccount.getBalance());
    }

    @Test
    @Override
    public void testCannotWithdrawMoreThanAvailable() {
        final int withdrawAmount = 1600;
        this.bankAccount.deposit(STANDARD_DEPOSIT_AMOUNT);
        assertThrows(IllegalStateException.class, () -> this.bankAccount.withdraw(withdrawAmount));
    }

    @Test
    public void testWithdrawAllowsOverdraft() {
        final int withdrawAmount = 1200;
        this.bankAccount.deposit(STANDARD_DEPOSIT_AMOUNT);
        assertDoesNotThrow(() -> this.bankAccount.withdraw(withdrawAmount));
    }
}
