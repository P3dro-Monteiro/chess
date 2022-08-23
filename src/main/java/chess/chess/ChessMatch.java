package chess.chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import chess.boardgame.Board;
import chess.boardgame.Piece;
import chess.boardgame.Position;
import chess.chess.chesspieces.Bishop;
import chess.chess.chesspieces.King;
import chess.chess.chesspieces.Knight;
import chess.chess.chesspieces.Pawn;
import chess.chess.chesspieces.Queen;
import chess.chess.chesspieces.Rook;
import chess.errorhandling.ChessException;
import chess.errorhandling.ExceptionMessages;

public class ChessMatch {
    
    private Board board;
    private int turn;
    private Color currentPlayer;
    private boolean check;
    private boolean checkMate;
    private ChessPiece enPassantVulnarable;
    private List<ChessPiece> piecesOnTheBoard;
    private List<ChessPiece> capturedPieces;

    private final int CHESS_BOARD_WIDTH = 8;

    public ChessMatch() { 
        this.board = new Board(CHESS_BOARD_WIDTH, CHESS_BOARD_WIDTH);
        this.turn = 1;
        this.currentPlayer = Color.WHITE;
        this.piecesOnTheBoard = new ArrayList<>();
        this.capturedPieces = new ArrayList<>();
        this.check = false;

        initialSetup();
    }

    public int getTurn() { return this.turn; }

    public Color getCurrentPlayer() { return this.currentPlayer; }

    public ChessPiece[][] getPieces() {
        ChessPiece[][] pieces = new ChessPiece[this.board.getRows()][this.board.getColumns()];

        for (int i = 0; i < this.board.getRows(); i++) {
            for (int j = 0; j < this.board.getColumns(); j++) {
                pieces[i][j] = (ChessPiece) board.piece(i, j);
            }
        }
        return pieces;
    }

    public boolean getCheck() { return this.check; }

    public boolean getCheckMate() { return this.checkMate; }

    public boolean[][] possibleMoves(ChessPosition originalPosition) {
		Position position = originalPosition.toPosition();
		validateOriginalPosition(position);
		return board.piece(position).possibleMoves();
	}

    public ChessPiece getEnPassantVulnarable() { return this.enPassantVulnarable; }

    public ChessPiece performChessMove(ChessPosition originalPosition, ChessPosition newPosition) {

        Position from = originalPosition.toPosition();
        Position to = newPosition.toPosition();

        validateOriginalPosition(from);
        validateTargetPosition(from, to);

        Piece pickedUpPiece = makeMove(from, to);
        
        if (testCheck(currentPlayer)) {
            undoMove(from, to, pickedUpPiece);
            throw new ChessException(ExceptionMessages.CANT_PUT_YOURSELF_CHECK);
        }

        this.check = (testCheck(opponent(currentPlayer))) ? true : false;

        if (testCheckMate(opponent(currentPlayer))) {
            checkMate = true;
        } else {
            nextTurn();
        }

        //Special Move En Passant
        ChessPiece movedPiece = (ChessPiece)board.piece(to);

        if (movedPiece instanceof Pawn &&
        (to.getRow() == from.getRow() - 2) || to.getRow() == from.getRow() + 2) {
            enPassantVulnarable = movedPiece;            
        }else {
            enPassantVulnarable = null;
        }

        return (ChessPiece)pickedUpPiece;
    }

    private void validateOriginalPosition(Position position) {
        
        if(!this.board.thereIsAPiece(position)) {
            throw new ChessException(ExceptionMessages.NO_PIECE_IN_POSITION);
        }

        if (currentPlayer != ((ChessPiece)board.piece(position)).getColor()) {
            throw new ChessException(ExceptionMessages.PIECE_DOES_NOT_BELONG_TO_PLAYER);
        }

        if (!this.board.piece(position).isThereAnyPossibleMove()) {
            throw new ChessException(ExceptionMessages.NO_POSSIBLE_MOVES);
        }
    }

    private void validateTargetPosition(Position originalPosition, Position newPosition) {

        if(!this.board.piece(originalPosition).possibleMove(newPosition)) {
            throw new ChessException(ExceptionMessages.INVALID_MOVE);
        }
    }

    private Piece makeMove(Position from, Position to) {

       ChessPiece piece = (ChessPiece)this.board.removePiece(from);

       piece.increaseMoveCount();

       Piece pickedUpPiece = this.board.removePiece(to);

       this.board.placePiece(piece, to);

       if (pickedUpPiece != null) {
        piecesOnTheBoard.remove(pickedUpPiece);
        capturedPieces.add((ChessPiece)pickedUpPiece);
       }

       //Special Move King Side Castling
       if (piece instanceof King && to.getColumn() == from.getColumn() + 2) {
            Position rookOriginalPosition = new Position(from.getRow(), from.getColumn() + 3);
            Position rookNewPosition = new Position(from.getRow(), from.getColumn() + 1);

            ChessPiece rook = (ChessPiece)board.removePiece(rookOriginalPosition);
            board.placePiece(rook, rookNewPosition);
            rook.increaseMoveCount();
       }

       //Special Move Queen Side Castling
       if (piece instanceof King && to.getColumn() == from.getColumn() - 2) {
            Position rookOriginalPosition = new Position(from.getRow(), from.getColumn() -4);
            Position rookNewPosition = new Position(from.getRow(), from.getColumn() - 1);

            ChessPiece rook = (ChessPiece)board.removePiece(rookOriginalPosition);
            board.placePiece(rook, rookNewPosition);
            rook.increaseMoveCount();
        }

       //Special Move En Passant
       if (piece instanceof Pawn) {
           if (from.getColumn() != to.getColumn() && pickedUpPiece == null) {
                Position pawnPosition;
                
                if (piece.getColor() == Color.WHITE) {
                    pawnPosition = new Position(to.getRow() + 1, to.getColumn());
                }else {
                    pawnPosition = new Position(to.getRow() - 1, to.getColumn());
                }

                pickedUpPiece = board.removePiece(pawnPosition);
                capturedPieces.add((ChessPiece)pickedUpPiece);
                piecesOnTheBoard.remove(pickedUpPiece);
           } 
       }

       return pickedUpPiece;
    }

    private void undoMove(Position originalPosition, Position newPosition, Piece capturedPiece) {
        ChessPiece piece = (ChessPiece)board.removePiece(newPosition);

        piece.decreaseMoveCount();

        board.placePiece(piece, originalPosition);

        if (capturedPiece != null) {
            board.placePiece(capturedPiece, newPosition);
            capturedPieces.remove(capturedPiece);
            piecesOnTheBoard.add((ChessPiece)capturedPiece);
        }

        //Special Move King Side Castling
       if (piece instanceof King && newPosition.getColumn() == originalPosition.getColumn() + 2) {
            Position rookOriginalPosition = new Position(originalPosition.getRow(), originalPosition.getColumn() + 3);
            Position rookNewPosition = new Position(originalPosition.getRow(), originalPosition.getColumn() + 1);

            ChessPiece rook = (ChessPiece)board.removePiece(rookNewPosition);
            board.placePiece(rook, rookOriginalPosition);
            rook.decreaseMoveCount();
        }

        //Special Move Queen Side Castling
        if (piece instanceof King && newPosition.getColumn() == originalPosition.getColumn() - 2) {
            Position rookOriginalPosition = new Position(originalPosition.getRow(), originalPosition.getColumn() -4);
            Position rookNewPosition = new Position(originalPosition.getRow(), originalPosition.getColumn() - 1);

            ChessPiece rook = (ChessPiece)board.removePiece(rookNewPosition);
            board.placePiece(rook, rookOriginalPosition);
            rook.decreaseMoveCount();
        }

        //Special Move En Passant
       if (piece instanceof Pawn) {
        if (originalPosition.getColumn() != newPosition.getColumn() && capturedPiece == enPassantVulnarable) {
             Position pawnPosition;
             ChessPiece pawn = (ChessPiece)board.removePiece(newPosition);

             if (piece.getColor() == Color.WHITE) {
                 pawnPosition = new Position(3, newPosition.getColumn());
             }else {
                 pawnPosition = new Position(4, newPosition.getColumn());
             }

             board.placePiece(pawn, pawnPosition);
        } 
    }
    }

    private void placeNewPiece(char column, int row, ChessPiece chessPiece) {
        this.board.placePiece(chessPiece, new ChessPosition(column, row).toPosition()); 
        piecesOnTheBoard.add(chessPiece);
    }

    private Color opponent(Color color) { return (color == Color.WHITE) ? Color.BLACK : Color.WHITE; }

    private ChessPiece king(Color color) {
        List<Piece> list = piecesOnTheBoard.stream()
            .filter(piece -> ((ChessPiece)piece).getColor() == color).collect(Collectors.toList());
        
        for (Piece piece : list)     {
            if (piece instanceof King) {
                return (ChessPiece)piece;
            }
        }
        throw new IllegalStateException(ExceptionMessages.NO_KING_AT_BOARD + color);
    }

    private boolean testCheck(Color color) {
        Position kingPosition = king(color).getChessPosition().toPosition();
        List<Piece> opponentPieces = piecesOnTheBoard.stream()
        .filter(piece -> ((ChessPiece)piece).getColor() == opponent(color)).collect(Collectors.toList());

        for (Piece piece : opponentPieces) {
            boolean[][] booleanMatrix = piece.possibleMoves();
            if (booleanMatrix[kingPosition.getRow()][kingPosition.getColumn()]) {
                return true;     
            }
        }
        return false;
    }

    private boolean testCheckMate(Color color) {
        if (!testCheck(color)) { return false; }

        List<Piece> list = piecesOnTheBoard.stream()
        .filter(piece -> ((ChessPiece)piece).getColor() == color)
        .collect(Collectors.toList());

        for (Piece piece : list) {
            boolean[][] booleanMatrix = piece.possibleMoves();

            for (int i = 0; i < booleanMatrix.length; i++) {
                for (int j = 0; j < booleanMatrix.length; j++) {

                    if (booleanMatrix[i][j]) {
                        
                        Position originalPosition = 
                        ((ChessPiece)piece).getChessPosition().toPosition();

                        Position newPosition = new Position(i, j);

                        Piece capturedPiece = makeMove(originalPosition, newPosition);

                        boolean isChecked =  testCheck(color);
                        
                        undoMove(originalPosition, newPosition, capturedPiece);

                        if (!isChecked) { return false; }

                    }
                }
            }
        }
        return true;
    }

    private void nextTurn() {
        turn++;
        currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    private void initialSetup() {
        
        placeNewPiece('a', 1, new Rook(board, Color.WHITE));
        placeNewPiece('b', 1, new Knight(board, Color.WHITE));
        placeNewPiece('c', 1, new Bishop(board, Color.WHITE));
        placeNewPiece('d', 1, new Queen(board, Color.WHITE));
        placeNewPiece('e', 1, new King(board, Color.WHITE, this));
        placeNewPiece('f', 1, new Bishop(board, Color.WHITE));
        placeNewPiece('g', 1, new Knight(board, Color.WHITE));
        placeNewPiece('h', 1, new Rook(board, Color.WHITE));
        placeNewPiece('a', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('b', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('c', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('d', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('e', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('f', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('g', 2, new Pawn(board, Color.WHITE, this));
        placeNewPiece('h', 2, new Pawn(board, Color.WHITE, this));

        placeNewPiece('a', 8, new Rook(board, Color.BLACK));
        placeNewPiece('b', 8, new Knight(board, Color.BLACK));
        placeNewPiece('c', 8, new Bishop(board, Color.BLACK));
        placeNewPiece('d', 8, new Queen(board, Color.BLACK));
        placeNewPiece('e', 8, new King(board, Color.BLACK, this));
        placeNewPiece('f', 8, new Bishop(board, Color.BLACK));
        placeNewPiece('g', 8, new Knight(board, Color.BLACK));
        placeNewPiece('h', 8, new Rook(board, Color.BLACK));
        placeNewPiece('a', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('b', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('c', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('d', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('e', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('f', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('g', 7, new Pawn(board, Color.BLACK, this));
        placeNewPiece('h', 7, new Pawn(board, Color.BLACK, this));
	}
}
