package chess.chess.chesspieces;

import chess.boardgame.Board;
import chess.boardgame.Position;
import chess.chess.ChessPiece;
import chess.chess.Color;

public class King extends ChessPiece{

    public King(Board board, Color color) { super(board, color); }
    
    @Override
    public String toString() { return "K"; }

    @Override
    public boolean[][] possibleMoves() { 
        
        boolean[][] booleanMatrix = new boolean[getBoard().getRows()][getBoard().getColumns()]; 

        Position auxiliarPosition = new Position(0, 0);

        //Above Screening
        auxiliarPosition.setPosition(this.position.getRow() - 1, this.position.getColumn());

        if (this.getBoard().positionExists(auxiliarPosition) && canMove(auxiliarPosition)) {
            booleanMatrix[auxiliarPosition.getRow()][auxiliarPosition.getColumn()] = true;
        }

        //Bellow Screening
        auxiliarPosition.setPosition(this.position.getRow() + 1, this.position.getColumn());

        if (this.getBoard().positionExists(auxiliarPosition) && canMove(auxiliarPosition)) {
            booleanMatrix[auxiliarPosition.getRow()][auxiliarPosition.getColumn()] = true;
        }

        //Left Screening
        auxiliarPosition.setPosition(this.position.getRow(), this.position.getColumn() - 1);

        if (this.getBoard().positionExists(auxiliarPosition) && canMove(auxiliarPosition)) {
            booleanMatrix[auxiliarPosition.getRow()][auxiliarPosition.getColumn()] = true;
        }

        //Right Screening
        auxiliarPosition.setPosition(this.position.getRow(), this.position.getColumn() + 1);

        if (this.getBoard().positionExists(auxiliarPosition) && canMove(auxiliarPosition)) {
            booleanMatrix[auxiliarPosition.getRow()][auxiliarPosition.getColumn()] = true;
        }

        //Top Left Diagonal
        auxiliarPosition.setPosition(this.position.getRow() - 1, this.position.getColumn() - 1);

        if (this.getBoard().positionExists(auxiliarPosition) && canMove(auxiliarPosition)) {
            booleanMatrix[auxiliarPosition.getRow()][auxiliarPosition.getColumn()] = true;
        }

        //Top Right Diagonal
        auxiliarPosition.setPosition(this.position.getRow() - 1, this.position.getColumn() + 1);

        if (this.getBoard().positionExists(auxiliarPosition) && canMove(auxiliarPosition)) {
            booleanMatrix[auxiliarPosition.getRow()][auxiliarPosition.getColumn()] = true;
        }

        //Bottom Left Diagonal
        auxiliarPosition.setPosition(this.position.getRow() + 1, this.position.getColumn() - 1);

        if (this.getBoard().positionExists(auxiliarPosition) && canMove(auxiliarPosition)) {
            booleanMatrix[auxiliarPosition.getRow()][auxiliarPosition.getColumn()] = true;
        }

        //Bottom Right Diagonal
        auxiliarPosition.setPosition(this.position.getRow() + 1, this.position.getColumn() + 1);

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
