package e1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SilverBankAccountTest extends AbstractBankAccountTest {

    private static final int MAX_WITHDRAW_OVERDRAFT = 0;
    private static final int WITHDRAW_FEE_AMOUNT = 1;

    @Override
    protected BankAccount createBankAccount() {
        return bankAccountFactory.createSilverBankAccount(
                MAX_WITHDRAW_OVERDRAFT,
                WITHDRAW_FEE_AMOUNT
        );
    }

    @Test
    @Override
    public void testCanWithdraw() {
        final int withdrawAmount = 200;
        final int expectedBalanceAfterWithdraw = STANDARD_DEPOSIT_AMOUNT - withdrawAmount - WITHDRAW_FEE_AMOUNT;
        this.bankAccount.deposit(STANDARD_DEPOSIT_AMOUNT);
        this.bankAccount.withdraw(withdrawAmount);
        assertEquals(expectedBalanceAfterWithdraw, this.bankAccount.getBalance());
    }

    @Test
    @Override
    public void testCannotWithdrawMoreThanAvailable() {
        final int withdrawAmount = 1600;
        this.bankAccount.deposit(STANDARD_DEPOSIT_AMOUNT);
        assertThrows(IllegalStateException.class, () -> this.bankAccount.withdraw(withdrawAmount));
    }
}
