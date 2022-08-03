package chess.boardgame;

import chess.errorhandling.BoardException;
import chess.errorhandling.ExceptionMessages;

public class Board {
    
    private int rows;
    private int columns;
    private Piece[][] pieces;

    public Board(int rows, int columns) { 

        if (rows < 1 || columns < 1) {
            throw new BoardException(ExceptionMessages.ERROR_CREATING_BOARD);
        }

        this.rows = rows; 
        this.columns = columns;
        pieces = new Piece[rows][columns];
    }

    public Piece piece(int row, int column) { 

        if (!positionExists(row, column)) {
            throw new BoardException(ExceptionMessages.OUT_OF_BOUNDS);
        }

        return pieces[row][column]; 
    }

    public Piece piece(Position position) { 

        if (!positionExists(position)) {
            throw new BoardException(ExceptionMessages.OUT_OF_BOUNDS);
        }

        return pieces[position.getRow()][position.getColumn()]; 
    }

    public void placePiece(Piece piece, Position position) {

        if (thereIsAPiece(position)) {
            throw new BoardException(ExceptionMessages.POSITION_OCCUPIED + position);
        }

        pieces[position.getRow()][position.getColumn()] = piece;

        piece.position = position;
    }

    /* public Piece removePiece(Position position) {
        if(!positionExists(position)) {
            throw new Bo
        }
    } */

    public boolean positionExists(Position position) {
        return positionExists(position.getRow(), position.getColumn());
    }

    private boolean positionExists(int row, int column) {
        return
        row >= 0 && 
        row < this.rows &&
        column >= 0 &&
        column < this.columns;
    }

    public boolean thereIsAPiece(Position position) {

        if (!positionExists(position)) {
            throw new BoardException(ExceptionMessages.OUT_OF_BOUNDS);
        }

        return this.piece(position) != null;
    }

    public int getRows() { return rows; }

    public int getColumns() { return columns; }
}
