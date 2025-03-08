package e2;

public interface KnightLogic {

    boolean move(int destinationRow, int destinationColumn);

    boolean isMovementLegal(int destinationRow, int destinationColumn);

    Pair<Integer, Integer> getPosition();
}
