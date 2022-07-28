package chess.chess;

import chess.boardgame.Board;
import chess.boardgame.Position;
import chess.chess.chesspieces.King;
import chess.chess.chesspieces.Rook;

public class ChessMatch {
    
    private Board board;
    
    private final int CHESS_BOARD_WIDTH = 8;

    public ChessMatch() { 
        this.board = new Board(CHESS_BOARD_WIDTH, CHESS_BOARD_WIDTH); 

        initialSetup();
    }

    public ChessPiece[][] getPieces() {
        ChessPiece[][] pieces = new ChessPiece[board.getRows()][board.getColumns()];

        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                pieces[i][j] = (ChessPiece) board.piece(i, j);
            }
        }

        return pieces;
    }

    private void initialSetup() {
        this.board.placePiece(new Rook(this.board, Color.WHITE), new Position(2, 1));
        this.board.placePiece(new King(this.board, Color.BLACK), new Position(0, 4));
    }

}
