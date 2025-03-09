package e2;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public abstract class LogicTest {

    private static final int PAWN_ROW = 2;
    private static final int PAWN_COLUMN = 1;
    private static final int KNIGHT_ROW = 0;
    private static final int KNIGHT_COLUMN = 0;
    private static final int BOARD_SIZE = 5;
    private Logics logics;

    @BeforeEach
    void init() {
        this.logics = new LogicsImpl(BOARD_SIZE, PAWN_ROW, PAWN_COLUMN, KNIGHT_ROW, KNIGHT_COLUMN);
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

    @Test
    public void testPawnHitIfFixedPositionAndLegalMovement() {
        assertAll(
                () -> assertTrue(this.logics.hasPawn(PAWN_ROW, PAWN_COLUMN)),
                () -> assertTrue(this.logics.hasKnight(KNIGHT_ROW, KNIGHT_COLUMN)),
                () -> assertTrue(this.logics.hit(PAWN_ROW, PAWN_COLUMN))
        );
    }

    @Test
    protected abstract void testKnightIllegalMovement();

}
