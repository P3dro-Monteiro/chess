package chess.chess.chesspieces;

import chess.boardgame.Board;
import chess.boardgame.Position;
import chess.chess.ChessPiece;
import chess.chess.Color;

public class Rook extends ChessPiece {

    public Rook(Board board, Color color) { super(board, color); }
    
    @Override
    public String toString() { return "R"; }

    @Override
    public boolean[][] possibleMoves() { 
        
        boolean[][] booleanMatrix =  new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position auxiliarPosition = new Position(0, 0);

        //Above Screening
        auxiliarPosition.setPosition(this.position.getRow() - 1, this.position.getColumn());

        while(getBoard().positionExists(auxiliarPosition) && !getBoard().thereIsAPiece(auxiliarPosition)) {

            booleanMatrix[auxiliarPosition.getRow()][auxiliarPosition.getColumn()] = true;

            auxiliarPosition.setRow(auxiliarPosition.getRow() - 1);
        }

        if (getBoard().positionExists(auxiliarPosition) && isThereOpponentPiece(auxiliarPosition)) {
            
            booleanMatrix[auxiliarPosition.getRow()][auxiliarPosition.getColumn()] = true;
        }

        //Left Screening
        auxiliarPosition.setPosition(this.position.getRow(), this.position.getColumn() - 1);

        while(getBoard().positionExists(auxiliarPosition) && !getBoard().thereIsAPiece(auxiliarPosition)) {

            booleanMatrix[auxiliarPosition.getRow()][auxiliarPosition.getColumn()] = true;

            auxiliarPosition.setColumn(auxiliarPosition.getColumn() - 1);
        }

        if (getBoard().positionExists(auxiliarPosition) && isThereOpponentPiece(auxiliarPosition)) {
            
            booleanMatrix[auxiliarPosition.getRow()][auxiliarPosition.getColumn()] = true;
        }

        //Right Screening
        auxiliarPosition.setPosition(this.position.getRow(), this.position.getColumn() + 1);

        while(getBoard().positionExists(auxiliarPosition) && !getBoard().thereIsAPiece(auxiliarPosition)) {

            booleanMatrix[auxiliarPosition.getRow()][auxiliarPosition.getColumn()] = true;

            auxiliarPosition.setColumn(auxiliarPosition.getColumn() + 1);
        }

        if (getBoard().positionExists(auxiliarPosition) && isThereOpponentPiece(auxiliarPosition)) {
            
            booleanMatrix[auxiliarPosition.getRow()][auxiliarPosition.getColumn()] = true;
        }

        //Bellow Screening
        auxiliarPosition.setPosition(this.position.getRow() + 1, this.position.getColumn());

        while(getBoard().positionExists(auxiliarPosition) && !getBoard().thereIsAPiece(auxiliarPosition)) {

            booleanMatrix[auxiliarPosition.getRow()][auxiliarPosition.getColumn()] = true;

            auxiliarPosition.setRow(auxiliarPosition.getRow() + 1);
        }

        if (getBoard().positionExists(auxiliarPosition) && isThereOpponentPiece(auxiliarPosition)) {
            
            booleanMatrix[auxiliarPosition.getRow()][auxiliarPosition.getColumn()] = true;
        }
    
        return booleanMatrix;
    }
}