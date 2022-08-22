package chess.errorhandling;

public class ExceptionMessages {
    
    public static final String ERROR_CREATING_BOARD = "Board needs to be at least 1 x 1";
    public static final String OUT_OF_BOUNDS = "Position out of bounds - Valid balues from a1 to h8";
    public static final String POSITION_OCCUPIED = "There is already an piece on ";
    public static final String NO_PIECE_IN_POSITION = "There is no piece in that position!";
    public static final String NO_POSSIBLE_MOVES = "There is no possible moves for that piece!";
    public static final String INVALID_MOVE = "Invalid Move!";
    public static final String PIECE_DOES_NOT_BELONG_TO_PLAYER = "Chosen piece is not yours!";
    public static final String NO_KING_AT_BOARD = "There is no King on the board of ";
    public static final String CANT_PUT_YOURSELF_CHECK = "You can't put yourself at check";

}
