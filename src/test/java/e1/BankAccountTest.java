package e1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {

    private static final int STANDARD_DEPOSIT_AMOUNT = 1000;
    private static final int MAX_WITHDRAW_OVERDRAFT_SILVER = 0;
    private static final int WITHDRAW_FEE_AMOUNT_SILVER = 1;
    private static final int MAX_WITHDRAW_OVERDRAFT_GOLD = 500;
    private static final int WITHDRAW_FEE_AMOUNT_GOLD = 0;
    private BankAccountFactory bankAccountFactory;
    private BankAccount silverBankAccount;
    private BankAccount goldBankAccount;

    @BeforeEach
    void init(){
        this.bankAccountFactory = new BankAccountFactoryImpl();
        this.silverBankAccount = this.bankAccountFactory.createSilverBankAccount(MAX_WITHDRAW_OVERDRAFT_SILVER, WITHDRAW_FEE_AMOUNT_SILVER);
        this.goldBankAccount = this.bankAccountFactory.createGoldBankAccount(MAX_WITHDRAW_OVERDRAFT_GOLD, WITHDRAW_FEE_AMOUNT_GOLD);
    }

    @Test
    public void testInitiallyEmpty() {
        assertAll(
            () ->assertEquals(0, this.silverBankAccount.getBalance()),
            () -> assertEquals(0, this.goldBankAccount.getBalance())
        );
    }

    @Test
    public void testCanDeposit() {
        this.silverBankAccount.deposit(STANDARD_DEPOSIT_AMOUNT);
        this.goldBankAccount.deposit(STANDARD_DEPOSIT_AMOUNT);
        assertAll(
            () -> assertEquals(STANDARD_DEPOSIT_AMOUNT, this.silverBankAccount.getBalance()),
            () -> assertEquals(STANDARD_DEPOSIT_AMOUNT, this.goldBankAccount.getBalance())
        );
    }

    @Test
    public void testCanWithdraw() {
        final int withdrawAmount = 200;
        final int expectedBalanceAfterWithdrawSilver = 799;
        final int expectedBalanceAfterWithdrawGold = 800;
        this.silverBankAccount.deposit(STANDARD_DEPOSIT_AMOUNT);
        this.silverBankAccount.withdraw(withdrawAmount);
        this.goldBankAccount.deposit(STANDARD_DEPOSIT_AMOUNT);
        this.goldBankAccount.withdraw(withdrawAmount);
        assertAll(
            () -> assertEquals(expectedBalanceAfterWithdrawSilver, this.silverBankAccount.getBalance()),
            () -> assertEquals(expectedBalanceAfterWithdrawGold, this.goldBankAccount.getBalance())
        );
    }

    @Test
    public void testCannotWithdrawMoreThanAvailable(){
        final int withdrawAmount = 1600;
        this.silverBankAccount.deposit(STANDARD_DEPOSIT_AMOUNT);
        this.goldBankAccount.deposit(STANDARD_DEPOSIT_AMOUNT);
        assertAll(
                () -> assertThrows(IllegalStateException.class, () -> this.silverBankAccount.withdraw(withdrawAmount)),
                () -> assertThrows(IllegalStateException.class, () -> this.silverBankAccount.withdraw(withdrawAmount))
        );
    }

    @Test
    public void testWithdrawAllowsOverdraft() {
        final int withdrawAmount = 1200;
        this.goldBankAccount.deposit(STANDARD_DEPOSIT_AMOUNT);
        assertDoesNotThrow(() -> this.goldBankAccount.withdraw(withdrawAmount));
    }

}
