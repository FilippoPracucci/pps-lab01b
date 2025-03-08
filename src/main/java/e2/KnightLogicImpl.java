package e2;

import java.util.function.Predicate;

public class KnightLogicImpl implements KnightLogic {

    private static final int UNIT_OF_MOVEMENT = 3;
    private Pair<Integer, Integer> knight;
    private final Predicate<Pair<Integer, Integer>> movementLimitation;

    public KnightLogicImpl(final Pair<Integer, Integer> knight) {
        this.knight = knight;
        this.movementLimitation = getMovementLimitation();
    }

    private Predicate<Pair<Integer, Integer>> getMovementLimitation() {
        return destination -> {
            final int deltaRow = destination.getX() - this.knight.getX();
            final int deltaColumn = destination.getY() - this.knight.getY();
            return deltaRow != 0 && deltaColumn != 0 &&
                    (Math.abs(deltaRow) + Math.abs(deltaColumn) == UNIT_OF_MOVEMENT);
        };
    }

    @Override
    public boolean move(final int destinationRow, final int destinationColumn) {
        if (this.isMovementLegal(destinationRow, destinationColumn)) {
            this.knight = new Pair<>(destinationRow, destinationColumn);
            return true;
        }
        return false;
    }

    @Override
    public boolean isMovementLegal(final int destinationRow, final int destinationColumn) {
        return this.movementLimitation.test(new Pair<>(destinationRow, destinationColumn));
    }

    @Override
    public Pair<Integer, Integer> getPosition() {
        return this.knight;
    }
}
