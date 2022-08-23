package chess.chess.chesspieces;

import chess.boardgame.Board;
import chess.boardgame.Position;
import chess.chess.ChessMatch;
import chess.chess.ChessPiece;
import chess.chess.Color;

public class King extends ChessPiece{

    private ChessMatch chessMatch;

    public King(Board board, Color color, ChessMatch chessMatch) { 
        super(board, color);
        this.chessMatch = chessMatch; 
    }
    
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

        //Special Move Castling
        if (this.getMoveCount() == 0 && !chessMatch.getCheck()) {
            //King Side Castling
            Position kingSideRookPosition = new Position(this.position.getRow(), this.position.getColumn() + 3);

            if (testCastling(kingSideRookPosition)) {
               Position auxiliarPosition1 =  new Position(this.position.getRow(), this.position.getColumn() + 1);
               Position auxiliarPosition2 =  new Position(this.position.getRow(), this.position.getColumn() + 2);

               if (getBoard().piece(auxiliarPosition1) == null && getBoard().piece(auxiliarPosition2) == null) {
                   booleanMatrix[this.position.getRow()][this.position.getColumn() + 2] = true;
               }
            }
            //Queen Side Castling
            Position queenSideRookPosition = new Position(this.position.getRow(), this.position.getColumn() - 4);

            if (testCastling(queenSideRookPosition)) {
               Position auxiliarPosition1 =  new Position(this.position.getRow(), this.position.getColumn() - 1);
               Position auxiliarPosition2 =  new Position(this.position.getRow(), this.position.getColumn() - 2);
               Position auxiliarPosition3 =  new Position(this.position.getRow(), this.position.getColumn() - 3);

               if (getBoard().piece(auxiliarPosition1) == null &&
               getBoard().piece(auxiliarPosition2) == null && getBoard().piece(auxiliarPosition3) == null) {
                   booleanMatrix[this.position.getRow()][this.position.getColumn() - 2] = true;
               }
            }
        }

        return booleanMatrix;
    }

    private boolean canMove(Position position) {
        ChessPiece piece = (ChessPiece)this.getBoard().piece(position);

        return piece == null || piece.getColor() != this.getColor();
    }

    private boolean testCastling(Position position) {
        ChessPiece piece = (ChessPiece)getBoard().piece(position);
        return piece != null &&
        piece instanceof Rook &&
        piece.getColor() == this.getColor() &&
        piece.getMoveCount() == 0;
    }
}
