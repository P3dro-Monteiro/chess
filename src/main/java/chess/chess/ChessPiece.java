package chess.chess;

import chess.boardgame.Board;
import chess.boardgame.Piece;
import chess.boardgame.Position;

public abstract class ChessPiece extends Piece {
    
    private Color color;
    private int moveCount;

    public ChessPiece(Board board, Color color) {
        super(board);
        this.color = color;
    }

    public Color getColor() { return color; }

    public ChessPosition getChessPosition() { return ChessPosition.fromPositon(position); }

    public int getMoveCount() { return this.moveCount; }

    public void increaseMoveCount() { this.moveCount++; }

    public void decreaseMoveCount() { this.moveCount--; }

    protected boolean isThereOpponentPiece(Position position) {
        
        ChessPiece piece = (ChessPiece)getBoard().piece(position);

        return piece != null && piece.getColor() != this.color;
    }

}
