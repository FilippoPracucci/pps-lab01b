package e2;

import java.util.*;

public class LogicsImpl implements Logics {
	
	private final PawnLogic pawn;
	private final KnightLogic knight;
	private final Random random = new Random();
	private final int size;
	 
    public LogicsImpl(final int size){
    	this.size = size;
        this.pawn = new PawnLogicImpl(this.randomEmptyPosition());
        this.knight = new KnightLogicImpl(this.randomEmptyPosition());
    }

	public LogicsImpl(
			final int size,
			final int pawnRow,
			final int pawnColumn,
			final int knightRow,
			final int knightColumn
	) {
		this.size = size;
		this.pawn = new PawnLogicImpl(setNewPositionRandomIfOutOfBounds(pawnRow, pawnColumn));
		this.knight = new KnightLogicImpl(setNewPositionRandomIfOutOfBounds(knightRow, knightColumn));
	}

	private Pair<Integer, Integer> setNewPositionRandomIfOutOfBounds(final int row, final int column) {
		if (isPositionOutOfBounds(row, column)) {
			return this.randomEmptyPosition();
		}
		return new Pair<>(row, column);
	}

	private boolean isPositionOutOfBounds(final int row, final int column) {
		return row < 0 || row >= this.size || column < 0 || column >= this.size;
	}
    
	private final Pair<Integer,Integer> randomEmptyPosition(){
    	Pair<Integer,Integer> pos = new Pair<>(this.random.nextInt(size),this.random.nextInt(size));
    	// the recursive call below prevents clash with an existing pawn
    	return this.pawn!=null && this.pawn.getPosition().equals(pos) ? randomEmptyPosition() : pos;
    }
    
	@Override
	public boolean hit(final int row, final int col) {
		if (this.isPositionOutOfBounds(row, col)) {
			throw new IndexOutOfBoundsException();
		}
		return this.knight.move(row, col) && this.pawn.hit(this.knight.getPosition());
	}

	@Override
	public boolean hasKnight(final int row, final int col) {
		return this.knight.getPosition().equals(new Pair<>(row,col));
	}

	@Override
	public boolean hasPawn(final int row, final int col) {
		return this.pawn.getPosition().equals(new Pair<>(row,col));
	}
}
