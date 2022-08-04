package chess.chess;

import chess.boardgame.Board;
import chess.boardgame.Piece;
import chess.boardgame.Position;
import chess.chess.chesspieces.King;
import chess.chess.chesspieces.Rook;
import chess.errorhandling.ChessException;
import chess.errorhandling.ExceptionMessages;

public class ChessMatch {
    
    private Board board;
    
    private final int CHESS_BOARD_WIDTH = 8;

    public ChessMatch() { 
        this.board = new Board(CHESS_BOARD_WIDTH, CHESS_BOARD_WIDTH); 

        initialSetup();
    }

    public ChessPiece[][] getPieces() {
        ChessPiece[][] pieces = new ChessPiece[this.board.getRows()][this.board.getColumns()];

        for (int i = 0; i < this.board.getRows(); i++) {
            for (int j = 0; j < this.board.getColumns(); j++) {
                pieces[i][j] = (ChessPiece) board.piece(i, j);
            }
        }

        return pieces;
    }

    public ChessPiece performChessMove(ChessPosition originalPosition, ChessPosition newPosition) {

        Position from = originalPosition.toPosition();
        Position to = newPosition.toPosition();

        validateOriginalPosition(from);

        Piece pickedUpPiece = makeMove(from, to);
        
        return (ChessPiece)pickedUpPiece;
    }

    private void validateOriginalPosition(Position position) {
        
        if(!this.board.thereIsAPiece(position)) {
            throw new ChessException(ExceptionMessages.NO_PIECE_IN_POSITION);
        }

        if (!board.piece(position).isThereAnyPossibleMove()) {
            throw new ChessException(ExceptionMessages.NO_POSSIBLE_MOVES);
        }
    }

    private Piece makeMove(Position from, Position to) {

       Piece piece = this.board.removePiece(from);
       Piece pickedUpPiece = this.board.removePiece(to);

       this.board.placePiece(piece, to);

       return pickedUpPiece;
    }

    private void placeNewPiece(char column, int row, ChessPiece chessPiece) {
        
        this.board.placePiece(chessPiece, new ChessPosition(column, row).toPosition()); 
    }

    private void initialSetup() {
        placeNewPiece('a', 1, new Rook(board, Color.WHITE));
        /* placeNewPiece('b', 1, new Knight(board, Color.WHITE));
        placeNewPiece('c', 1, new Bishop(board, Color.WHITE));
        placeNewPiece('d', 1, new Queen(board, Color.WHITE)); */
        placeNewPiece('e', 1, new King(board, Color.WHITE));
        /* placeNewPiece('f', 1, new Bishop(board, Color.WHITE));
        placeNewPiece('g', 1, new Knight(board, Color.WHITE)); */
        placeNewPiece('h', 1, new Rook(board, Color.WHITE));
        /* placeNewPiece('a', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('b', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('c', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('d', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('e', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('f', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('g', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('h', 2, new Pawn(board, Color.WHITE, this)); */

        placeNewPiece('a', 8, new Rook(board, Color.BLACK));
        /* placeNewPiece('b', 8, new Knight(board, Color.BLACK));
        placeNewPiece('c', 8, new Bishop(board, Color.BLACK));
        placeNewPiece('d', 8, new Queen(board, Color.BLACK)); */
        placeNewPiece('e', 8, new King(board, Color.BLACK));
        /* placeNewPiece('f', 8, new Bishop(board, Color.BLACK));
        placeNewPiece('g', 8, new Knight(board, Color.BLACK)); */
        placeNewPiece('h', 8, new Rook(board, Color.BLACK));

        /* placeNewPiece('a', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('b', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('c', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('d', 7, new Pawn(board, Color.BL ACK, this));
        placeNewPiece('e', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('f', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('g', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('h', 7, new Pawn(board, Color.BLACK, this)); */
	}
}
