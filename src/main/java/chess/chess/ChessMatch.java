package chess.chess;

import chess.boardgame.Board;

public class ChessMatch {
    
    private Board board;
    
    private final int CHESS_BOARD_WIDTH = 8;

    public ChessMatch() { this.board = new Board(CHESS_BOARD_WIDTH, CHESS_BOARD_WIDTH); }

    public ChessPiece[][] getPieces() {
        ChessPiece[][] pieces = new ChessPiece[board.getRows()][board.getColumns()];

        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                pieces[i][j] = (ChessPiece) board.piece(i, j);
            }
        }

        return pieces;
    }

}
