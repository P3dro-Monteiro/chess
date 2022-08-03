package chess.chess;

import chess.boardgame.Position;
import chess.errorhandling.ChessException;
import chess.errorhandling.ExceptionMessages;

public class ChessPosition {
    
    private int row;
    private char column;

    public ChessPosition(char column, int row) {

        if (column < 'a' || column > 'h' || row < 1 || row > 8) {
            throw new ChessException(ExceptionMessages.OUT_OF_BOUNDS);
        }

        this.row = row;
        this.column = column;
    }

    protected Position toPosition() {
        return new Position(8 - this.row, this.column - 'a');
    }

    protected static ChessPosition fromPositon(Position position) {
        return new ChessPosition((char)('a' - position.getColumn()), 8 - position.getRow()); 
    }

    public int getRow() { return row; }

    public char getColumn() { return column; }

    @Override
    public String toString() { return "" + column + row; }
}
