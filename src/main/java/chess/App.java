package chess;

import java.util.Scanner;

import chess.chess.ChessMatch;
import chess.chess.ChessPiece;
import chess.chess.ChessPosition;
import chess.errorhandling.ChessException;

public class App {
    public static void main( String[] args ) {

        Scanner scanner = new Scanner(System.in);
        ChessMatch chessMatch = new ChessMatch();

        while(true) {
            
            try {

                UI.clearScreen();
                UI.printBoard(chessMatch.getPieces());

                System.out.println();
                System.out.println("Source: ");

                ChessPosition originalPosition = UI.readChessPosition(scanner);

                System.out.println();
                System.out.println("Target: ");

                ChessPosition newPosition = UI.readChessPosition(scanner);

                ChessPiece pickedUpPiece = chessMatch.performChessMove(originalPosition, newPosition);

            } catch(RuntimeException exception) {

                System.out.println(exception.getMessage());
                scanner.nextLine();
            }
        }
        
    }
}
