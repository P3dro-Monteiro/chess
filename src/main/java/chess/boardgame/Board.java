package chess.boardgame;

public class Board {
    
    private int CHESS_BOARD_WIDTH = 8;
    private int CHESS_BOARD_LENGTH = 8;

    private int rows;
    private int columns;
    private Piece[][] pieces;

    public Board() {

        this.rows = CHESS_BOARD_WIDTH;
        this.columns = CHESS_BOARD_LENGTH;

        pieces = new Piece[this.rows][this.columns];
    }

    public int getRows() { return rows; }

    public void setRows(int rows) { this.rows = rows; }

    public int getColumns() { return columns; }

    public void setColumns(int columns) { this.columns = columns; }

}
