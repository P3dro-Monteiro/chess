package chess.chess.chesspieces;

import chess.boardgame.Board;
import chess.boardgame.Position;
import chess.chess.ChessPiece;
import chess.chess.Color;

public class Pawn extends ChessPiece {

    public Pawn(Board board, Color color) { super(board, color); }

    @Override
    public String toString() { return "P"; }

    @Override
    public boolean[][] possibleMoves() {
        
        boolean[][] booleanMatrix = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position auxiliarPosition = new Position(0, 0);

        if (this.getColor() == Color.WHITE) {
            //Move Forward
            auxiliarPosition.setPosition(this.position.getRow() - 1, this.position.getColumn());

            if(getBoard().positionExists(auxiliarPosition) && !getBoard().thereIsAPiece(auxiliarPosition)) {
                booleanMatrix[auxiliarPosition.getRow()][auxiliarPosition.getColumn()] = true;
            }

            //Move Forward 2 Times
            auxiliarPosition.setPosition(this.position.getRow() - 2, this.position.getColumn());

            Position auxiliarPosition2 = new Position(this.position.getRow() - 1, this.position.getColumn());

            if(getBoard().positionExists(auxiliarPosition) && 
                !getBoard().thereIsAPiece(auxiliarPosition) &&
                getBoard().positionExists(auxiliarPosition2) && 
                !getBoard().thereIsAPiece(auxiliarPosition2) &&
                this.getMoveCount() == 0) {

                booleanMatrix[auxiliarPosition.getRow()][auxiliarPosition.getColumn()] = true;
            }

            //Diagonal Top Left Corner
            auxiliarPosition.setPosition(this.position.getRow() - 1, this.position.getColumn() - 1);

            if(getBoard().positionExists(auxiliarPosition) && isThereOpponentPiece(auxiliarPosition)) {
                booleanMatrix[auxiliarPosition.getRow()][auxiliarPosition.getColumn()] = true;
            }

            //Diagonal Top Right Corner
            auxiliarPosition.setPosition(this.position.getRow() - 1, this.position.getColumn() + 1);

            if(getBoard().positionExists(auxiliarPosition) && isThereOpponentPiece(auxiliarPosition)) {
                booleanMatrix[auxiliarPosition.getRow()][auxiliarPosition.getColumn()] = true;
            }
        }

        if (this.getColor() == Color.BLACK) {
            //Move Forward
            auxiliarPosition.setPosition(this.position.getRow() + 1, this.position.getColumn());

            if(getBoard().positionExists(auxiliarPosition) && !getBoard().thereIsAPiece(auxiliarPosition)) {
                booleanMatrix[auxiliarPosition.getRow()][auxiliarPosition.getColumn()] = true;
            }

            //Move Forward 2 Times
            auxiliarPosition.setPosition(this.position.getRow() + 2, this.position.getColumn());

            Position auxiliarPosition2 = new Position(this.position.getRow() + 1, this.position.getColumn());

            if(getBoard().positionExists(auxiliarPosition) && 
                !getBoard().thereIsAPiece(auxiliarPosition) &&
                getBoard().positionExists(auxiliarPosition2) && 
                !getBoard().thereIsAPiece(auxiliarPosition2) &&
                this.getMoveCount() == 0) {

                booleanMatrix[auxiliarPosition.getRow()][auxiliarPosition.getColumn()] = true;
            }

            //Diagonal Top Left Corner
            auxiliarPosition.setPosition(this.position.getRow() + 1, this.position.getColumn() - 1);

            if(getBoard().positionExists(auxiliarPosition) && isThereOpponentPiece(auxiliarPosition)) {
                booleanMatrix[auxiliarPosition.getRow()][auxiliarPosition.getColumn()] = true;
            }

            //Diagonal Top Right Corner
            auxiliarPosition.setPosition(this.position.getRow() + 1, this.position.getColumn() + 1);

            if(getBoard().positionExists(auxiliarPosition) && isThereOpponentPiece(auxiliarPosition)) {
                booleanMatrix[auxiliarPosition.getRow()][auxiliarPosition.getColumn()] = true;
            }
        }
        
        return booleanMatrix;
    } 
}
