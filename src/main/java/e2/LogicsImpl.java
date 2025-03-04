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

	public LogicsImpl(final int size, final int pawnRow, final int pawnColumn) {
		this.size = size;
		if (this.isPawnPositionLegal(pawnRow, pawnColumn)) {
			this.pawn = new Pair<>(pawnRow, pawnColumn);
		} else {
			this.pawn = this.randomEmptyPosition();
		}
		this.knight = this.randomEmptyPosition();
	}
	
	private boolean isPawnPositionLegal(final int pawnRow, final int pawnColumn) {
		return pawnRow >= 0 && pawnRow < this.size && pawnColumn >= 0 && pawnColumn < this.size;
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
