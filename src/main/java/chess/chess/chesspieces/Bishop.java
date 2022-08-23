package chess.chess.chesspieces;

import chess.boardgame.Board;
import chess.boardgame.Position;
import chess.chess.ChessPiece;
import chess.chess.Color;

public class Bishop extends ChessPiece {

    public Bishop(Board board, Color color) { super(board, color); }

    @Override
    public String toString() { return "B"; }

    @Override
    public boolean[][] possibleMoves() { 
        
        boolean[][] booleanMatrix =  new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position auxiliarPosition = new Position(0, 0);

        //Diagonal Top Left Corner
        auxiliarPosition.setPosition(this.position.getRow() - 1, this.position.getColumn() - 1);

        while(getBoard().positionExists(auxiliarPosition) && !getBoard().thereIsAPiece(auxiliarPosition)) {

            booleanMatrix[auxiliarPosition.getRow()][auxiliarPosition.getColumn()] = true;

            auxiliarPosition.setPosition(auxiliarPosition.getRow() - 1, auxiliarPosition.getColumn() - 1);
        }

        if (getBoard().positionExists(auxiliarPosition) && isThereOpponentPiece(auxiliarPosition)) {
            
            booleanMatrix[auxiliarPosition.getRow()][auxiliarPosition.getColumn()] = true;
        }

        //Diagonal Top Right Corner
        auxiliarPosition.setPosition(this.position.getRow() - 1, this.position.getColumn() + 1);

        while(getBoard().positionExists(auxiliarPosition) && !getBoard().thereIsAPiece(auxiliarPosition)) {

            booleanMatrix[auxiliarPosition.getRow()][auxiliarPosition.getColumn()] = true;

            auxiliarPosition.setPosition(auxiliarPosition.getRow() - 1, auxiliarPosition.getColumn() + 1);
        }

        if (getBoard().positionExists(auxiliarPosition) && isThereOpponentPiece(auxiliarPosition)) {
            
            booleanMatrix[auxiliarPosition.getRow()][auxiliarPosition.getColumn()] = true;
        }

        //Diagonal Bottom Right Corner
        auxiliarPosition.setPosition(this.position.getRow() + 1, this.position.getColumn() + 1);

        while(getBoard().positionExists(auxiliarPosition) && !getBoard().thereIsAPiece(auxiliarPosition)) {

            booleanMatrix[auxiliarPosition.getRow()][auxiliarPosition.getColumn()] = true;

            auxiliarPosition.setPosition(auxiliarPosition.getRow() + 1, auxiliarPosition.getColumn() + 1);
        }

        if (getBoard().positionExists(auxiliarPosition) && isThereOpponentPiece(auxiliarPosition)) {
            
            booleanMatrix[auxiliarPosition.getRow()][auxiliarPosition.getColumn()] = true;
        }

        //Diagonal Bottom Left Corner
        auxiliarPosition.setPosition(this.position.getRow() + 1, this.position.getColumn() - 1);

        while(getBoard().positionExists(auxiliarPosition) && !getBoard().thereIsAPiece(auxiliarPosition)) {

            booleanMatrix[auxiliarPosition.getRow()][auxiliarPosition.getColumn()] = true;

            auxiliarPosition.setPosition(auxiliarPosition.getRow() + 1, auxiliarPosition.getColumn() - 1);
        }

        if (getBoard().positionExists(auxiliarPosition) && isThereOpponentPiece(auxiliarPosition)) {
            
            booleanMatrix[auxiliarPosition.getRow()][auxiliarPosition.getColumn()] = true;
        }
    
        return booleanMatrix;
    }

}
