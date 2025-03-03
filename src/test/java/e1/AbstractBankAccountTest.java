package e1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractBankAccountTest {

    protected static final int STANDARD_DEPOSIT_AMOUNT = 1000;
    protected BankAccount bankAccount;
    protected BankAccountFactory bankAccountFactory;

    protected abstract BankAccount createBankAccount();

    @BeforeEach
    void init(){
        this.bankAccountFactory = new BankAccountFactoryImpl();
        this.bankAccount = this.createBankAccount();
    }

    @Test
    public void testInitiallyEmpty() {
        assertEquals(0, this.bankAccount.getBalance());
    }

    @Test
    public void testCanDeposit() {
        this.bankAccount.deposit(STANDARD_DEPOSIT_AMOUNT);
        assertEquals(STANDARD_DEPOSIT_AMOUNT, this.bankAccount.getBalance());
    }

    @Test
    public abstract void testCanWithdraw();

    @Test
    public abstract void testCannotWithdrawMoreThanAvailable();

}
