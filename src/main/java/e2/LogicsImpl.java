package e2;

import java.util.*;

public class LogicsImpl implements Logics {
	
	private final Pair<Integer,Integer> pawn;
	private Pair<Integer,Integer> knight;
	private final Random random = new Random();
	private final int size;
	 
    public LogicsImpl(final int size){
    	this.size = size;
        this.pawn = this.randomEmptyPosition();
        this.knight = this.randomEmptyPosition();	
    }

	public LogicsImpl(
			final int size,
			final int pawnRow,
			final int pawnColumn,
			final int knightRow,
			final int knightColumn
	) {
		this.size = size;
		this.pawn = randomEmptyPositionIfNotLegal(pawnRow, pawnColumn);
		this.knight = randomEmptyPositionIfNotLegal(knightRow, knightColumn);
	}

	private Pair<Integer, Integer> randomEmptyPositionIfNotLegal(final int row, final int column) {
		if (!isPositionLegal(row, column)) {
			return this.randomEmptyPosition();
		}
		return new Pair<>(row, column);
	}

	private boolean isPositionLegal(final int row, final int column) {
		return row >= 0 && row < this.size && column >= 0 && column < this.size;
	}
    
	private final Pair<Integer,Integer> randomEmptyPosition(){
    	Pair<Integer,Integer> pos = new Pair<>(this.random.nextInt(size),this.random.nextInt(size));
    	// the recursive call below prevents clash with an existing pawn
    	return this.pawn!=null && this.pawn.equals(pos) ? randomEmptyPosition() : pos;
    }
    
	@Override
	public boolean hit(final int row, final int col) {
		if (row<0 || col<0 || row >= this.size || col >= this.size) {
			throw new IndexOutOfBoundsException();
		}
		// Below a compact way to express allowed moves for the knight
		int x = row-this.knight.getX();
		int y = col-this.knight.getY();
		if (x!=0 && y!=0 && Math.abs(x)+Math.abs(y)==3) {
			this.knight = new Pair<>(row,col);
			return this.pawn.equals(this.knight);
		}
		return false;
	}

	@Override
	public boolean hasKnight(final int row, final int col) {
		return this.knight.equals(new Pair<>(row,col));
	}

	@Override
	public boolean hasPawn(final int row, final int col) {
		return this.pawn.equals(new Pair<>(row,col));
	}
}
