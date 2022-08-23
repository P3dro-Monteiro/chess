package chess.chess.chesspieces;

import chess.boardgame.Board;
import chess.boardgame.Position;
import chess.chess.ChessPiece;
import chess.chess.Color;

public class Knight extends ChessPiece {

    public Knight(Board board, Color color) { super(board, color); }

    @Override
    public String toString() { return "N"; }

    @Override
    public boolean[][] possibleMoves() { 
        
        boolean[][] booleanMatrix = new boolean[getBoard().getRows()][getBoard().getColumns()]; 

        Position auxiliarPosition = new Position(0, 0);

        auxiliarPosition.setPosition(this.position.getRow() - 1, this.position.getColumn() - 2);

        if (this.getBoard().positionExists(auxiliarPosition) && canMove(auxiliarPosition)) {
            booleanMatrix[auxiliarPosition.getRow()][auxiliarPosition.getColumn()] = true;
        }

        auxiliarPosition.setPosition(this.position.getRow() - 2, this.position.getColumn() - 1);

        if (this.getBoard().positionExists(auxiliarPosition) && canMove(auxiliarPosition)) {
            booleanMatrix[auxiliarPosition.getRow()][auxiliarPosition.getColumn()] = true;
        }

        auxiliarPosition.setPosition(this.position.getRow() - 2, this.position.getColumn() + 1);

        if (this.getBoard().positionExists(auxiliarPosition) && canMove(auxiliarPosition)) {
            booleanMatrix[auxiliarPosition.getRow()][auxiliarPosition.getColumn()] = true;
        }

        auxiliarPosition.setPosition(this.position.getRow() - 1, this.position.getColumn() + 2);

        if (this.getBoard().positionExists(auxiliarPosition) && canMove(auxiliarPosition)) {
            booleanMatrix[auxiliarPosition.getRow()][auxiliarPosition.getColumn()] = true;
        }

        auxiliarPosition.setPosition(this.position.getRow() + 1, this.position.getColumn() + 2);

        if (this.getBoard().positionExists(auxiliarPosition) && canMove(auxiliarPosition)) {
            booleanMatrix[auxiliarPosition.getRow()][auxiliarPosition.getColumn()] = true;
        }

        auxiliarPosition.setPosition(this.position.getRow() + 2, this.position.getColumn() + 1);

        if (this.getBoard().positionExists(auxiliarPosition) && canMove(auxiliarPosition)) {
            booleanMatrix[auxiliarPosition.getRow()][auxiliarPosition.getColumn()] = true;
        }

        auxiliarPosition.setPosition(this.position.getRow() + 2, this.position.getColumn() - 1);

        if (this.getBoard().positionExists(auxiliarPosition) && canMove(auxiliarPosition)) {
            booleanMatrix[auxiliarPosition.getRow()][auxiliarPosition.getColumn()] = true;
        }

        auxiliarPosition.setPosition(this.position.getRow() + 1, this.position.getColumn() - 2);

        if (this.getBoard().positionExists(auxiliarPosition) && canMove(auxiliarPosition)) {
            booleanMatrix[auxiliarPosition.getRow()][auxiliarPosition.getColumn()] = true;
        }

        return booleanMatrix;
    }

    private boolean canMove(Position position) {
        ChessPiece piece = (ChessPiece)this.getBoard().piece(position);

        return piece == null || piece.getColor() != this.getColor();
    }

    
    
}
