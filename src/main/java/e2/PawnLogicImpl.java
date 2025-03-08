package e2;

public class PawnLogicImpl implements PawnLogic {

    private final Pair<Integer, Integer> pawn;

    public PawnLogicImpl(final Pair<Integer, Integer> pawn) {
        this.pawn = pawn;
    }

    @Override
    public boolean hit(final Pair<Integer, Integer> position) {
        return this.pawn.equals(position);
    }

    @Override
    public Pair<Integer, Integer> getPosition() {
        return this.pawn;
    }
}
