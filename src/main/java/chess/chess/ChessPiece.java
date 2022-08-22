package chess.chess;

import chess.boardgame.Board;
import chess.boardgame.Piece;
import chess.boardgame.Position;

public abstract class ChessPiece extends Piece {
    
    private Color color;

    public ChessPiece(Board board, Color color) {
        super(board);
        this.color = color;
    }

    public Color getColor() { return color; }

    protected boolean isThereOpponentPiece(Position position) {
        
        ChessPiece piece = (ChessPiece)getBoard().piece(position);

        return piece != null && piece.getColor() != this.color;
    }

}
