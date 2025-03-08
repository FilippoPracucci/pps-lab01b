package e2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KnightLogicTest extends LogicTest {

    private final int row = 0;
    private final int column = 0;
    private final KnightLogic knightLogic = new KnightLogicImpl(new Pair<>(this.row, this.column));

    @Test
    public void testKnightCanMove() {
        final int rowToHit = 1;
        final int columnToHit = 2;
        assertDoesNotThrow(() -> this.knightLogic.move(rowToHit, columnToHit));
    }

    @Test
    @Override
    public void testKnightIllegalMovement() {
        final int rowToHit = 0;
        final int columnToHit = 2;
        assertFalse(this.knightLogic.isMovementLegal(rowToHit, columnToHit));
    }

}
