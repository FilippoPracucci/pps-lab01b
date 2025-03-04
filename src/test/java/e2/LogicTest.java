package e2;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class LogicTest {

    private static final int BOARD_SIZE = 5;
    private Logics logics;

    @BeforeEach
    void init() {
        this.logics = new LogicsImpl(BOARD_SIZE);
    }

    @Test
    public void testIfPawnHasBeenHit() {
        final int rowToHit = 2;
        final int columnToHit = 2;
        final boolean isPawInPositionToHit = this.logics.hasPawn(rowToHit, columnToHit);
        assertEquals(isPawInPositionToHit, this.logics.hit(rowToHit, columnToHit));
    }

    @Test
    public void testKnightMovementOutOfBounds() {
        final int rowOutOfBounds = -1;
        final int columnOutOfBounds = -1;
        assertAll(
            () -> assertThrows(IndexOutOfBoundsException.class, () -> this.logics.hit(BOARD_SIZE, BOARD_SIZE)),
            () -> assertThrows(IndexOutOfBoundsException.class, () -> this.logics.hit(rowOutOfBounds, columnOutOfBounds))
        );
    }

}
