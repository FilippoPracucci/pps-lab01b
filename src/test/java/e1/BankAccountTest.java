package e1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BankAccountTest {

    private static final int STANDARD_DEPOSIT_AMOUNT = 1000;
    private static final int MAX_WITHDRAW_OVERDRAFT_SILVER = 0;
    private static final int WITHDRAW_FEE_AMOUNT_SILVER = 1;
    private BankAccountFactory bankAccountFactory;
    private BankAccount silverBankAccount;

    @BeforeEach
    void init(){
        this.bankAccountFactory = new BankAccountFactoryImpl();
        this.silverBankAccount = this.bankAccountFactory.createSilverBankAccount(MAX_WITHDRAW_OVERDRAFT_SILVER, WITHDRAW_FEE_AMOUNT_SILVER);
    }

    @Test
    public void testInitiallyEmpty() {
        assertEquals(0, this.silverBankAccount.getBalance());
    }

    @Test
    public void testCanDeposit() {
        this.silverBankAccount.deposit(STANDARD_DEPOSIT_AMOUNT);
        assertEquals(STANDARD_DEPOSIT_AMOUNT, this.silverBankAccount.getBalance());
    }

    @Test
    public void testCanWithdraw() {
        final int withdrawAmount = 200;
        final int expectedBalanceAfterWithdraw = 799;
        this.silverBankAccount.deposit(STANDARD_DEPOSIT_AMOUNT);
        this.silverBankAccount.withdraw(withdrawAmount);
        assertEquals(expectedBalanceAfterWithdraw, this.silverBankAccount.getBalance());
    }

    @Test
    public void testCannotWithdrawMoreThanAvailable(){
        final int withdrawAmount = 1200;
        this.silverBankAccount.deposit(STANDARD_DEPOSIT_AMOUNT);
        assertThrows(IllegalStateException.class, () -> this.silverBankAccount.withdraw(withdrawAmount));
    }

}
