package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import chess.chess.ChessMatch;
import chess.chess.ChessPiece;
import chess.chess.ChessPosition;

public class App {
    public static void main( String[] args ) {

        Scanner scanner = new Scanner(System.in);
        ChessMatch chessMatch = new ChessMatch();
        List<ChessPiece> capturedPieces = new ArrayList<>();

        while(!chessMatch.getCheckMate()) {
            
            try {

                UI.clearScreen();
                UI.printMatch(chessMatch, capturedPieces);

                System.out.println();
                System.out.print("Select a Piece: ");

                ChessPosition originalPosition = UI.readChessPosition(scanner);

                boolean[][] possibleMoves = chessMatch.possibleMoves(originalPosition);

                UI.clearScreen();
                UI.printBoard(chessMatch.getPieces(), possibleMoves);

                System.out.println();
                System.out.print("Destination: ");

                ChessPosition newPosition = UI.readChessPosition(scanner);

                ChessPiece pickedUpPiece = chessMatch.performChessMove(originalPosition, newPosition);

                if (pickedUpPiece != null) { capturedPieces.add(pickedUpPiece); }

                if (chessMatch.getPromoted() != null) {
                    System.out.print("Enter piece for promotion (B/N/R/Q): ");
                    String pieceType = scanner.nextLine().toUpperCase();

                    while (!pieceType.equals("B") && !pieceType.equals("N") && !pieceType.equals("R") && !pieceType.equals("Q")) {
                        System.out.print("Invalid value! Enter piece for promotion (B/N/R/Q): ");
                        pieceType = scanner.nextLine().toUpperCase();
                    }

                    chessMatch.replacePromotedPiece(pieceType);
                }

            } catch(RuntimeException exception) {

                System.out.println(exception.getMessage());
                scanner.nextLine();
            }
        }
        UI.clearScreen();
        UI.printMatch(chessMatch, capturedPieces);
    }
}