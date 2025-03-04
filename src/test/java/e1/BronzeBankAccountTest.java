package e1;

import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BronzeBankAccountTest extends AbstractBankAccountTest {

    private static final int MAX_WITHDRAW_OVERDRAFT = 0;
    private static final int WITHDRAW_FEE_AMOUNT_IF_CONDITION_TRUE = 0;
    private static final int WITHDRAW_FEE_AMOUNT_IF_CONDITION_FALSE = 1;
    private static final int WITHDRAW_FEE_TURNING_POINT = 100;
    private final Predicate<Integer> withdrawFeeCondition = withdrawAmount -> (withdrawAmount < WITHDRAW_FEE_TURNING_POINT);

    @Override
    protected BankAccount createBankAccount() {
        return this.bankAccountFactory.createBronzeBankAccount(
                MAX_WITHDRAW_OVERDRAFT,
                withdrawFeeCondition,
                WITHDRAW_FEE_AMOUNT_IF_CONDITION_TRUE,
                WITHDRAW_FEE_AMOUNT_IF_CONDITION_FALSE
        );
    }

    @Test
    @Override
    public void testCanWithdraw() {
        final int withdrawAmount = 200;
        final int expectedBalanceAfterWithdraw = getExpectedBalanceAfterWithdraw(withdrawAmount);
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

    private int getExpectedBalanceAfterWithdraw(final int withdrawAmount) {
        int expectedBalanceAfterWithdraw = STANDARD_DEPOSIT_AMOUNT - withdrawAmount;
        if (withdrawFeeCondition.test(withdrawAmount)) {
            expectedBalanceAfterWithdraw -= WITHDRAW_FEE_AMOUNT_IF_CONDITION_TRUE;
        } else {
            expectedBalanceAfterWithdraw -= WITHDRAW_FEE_AMOUNT_IF_CONDITION_FALSE;
        }
        return expectedBalanceAfterWithdraw;
    }
}
