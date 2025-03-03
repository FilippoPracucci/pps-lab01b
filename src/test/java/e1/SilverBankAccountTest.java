package e1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SilverBankAccountTest {

    private static final int STANDARD_DEPOSIT_AMOUNT = 1000;
    private static final int MAX_WITHDRAW_OVERDRAFT = 0;
    private static final int WITHDRAW_FEE_AMOUNT = 1;
    private BankAccount account;

    @BeforeEach
    void init(){
        this.account = new FeeDecoratorBankAccount(
                new CanWithdrawDecoratorBankAccount(
                        new CoreBankAccount(),
                        MAX_WITHDRAW_OVERDRAFT
                ),
                WITHDRAW_FEE_AMOUNT
        );
    }

    @Test
    public void testInitiallyEmpty() {
        assertEquals(0, this.account.getBalance());
    }

    @Test
    public void testCanDeposit() {
        this.account.deposit(STANDARD_DEPOSIT_AMOUNT);
        assertEquals(STANDARD_DEPOSIT_AMOUNT, this.account.getBalance());
    }

    @Test
    public void testCanWithdraw() {
        final int withdrawAmount = 200;
        final int expectedBalanceAfterWithdraw = 799;
        this.account.deposit(STANDARD_DEPOSIT_AMOUNT);
        this.account.withdraw(withdrawAmount);
        assertEquals(expectedBalanceAfterWithdraw, this.account.getBalance());
    }

    @Test
    public void testCannotWithdrawMoreThanAvailable(){
        final int withdrawAmount = 1200;
        this.account.deposit(STANDARD_DEPOSIT_AMOUNT);
        assertThrows(IllegalStateException.class, () -> this.account.withdraw(withdrawAmount));
    }

}
