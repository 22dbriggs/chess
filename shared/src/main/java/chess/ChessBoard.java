package chess;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * A chessboard that can hold and rearrange chess pieces.
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessBoard {

    private ChessPiece[][] board;
    private ChessPosition enPassantTarget = null;
    private boolean doPassant;

    public ChessBoard() {
        board = new ChessPiece[8][8];
    }
    public ChessBoard(ChessPiece[][] board){
        this.board = board;
    }

    /**
     * Adds a chess piece to the chessboard
     *
     * @param position where to add the piece to
     * @param piece    the piece to add
     */
    public void addPiece(ChessPosition position, ChessPiece piece) {
        this.board[position.getRow() - 1][position.getColumn() - 1] = piece;
    }

    /**
     * Gets a chess piece on the chessboard
     *
     * @param position The position to get the piece from
     * @return Either the piece at the position, or null if no piece is at that
     * position
     */
    public ChessPiece getPiece(ChessPosition position) {
        return this.board[position.getRow() - 1][position.getColumn() - 1];
    }

    public ChessGame.TeamColor getColor(ChessPosition pos){
        if(inBounds(pos)){
            if(getPiece(pos) == null){
                return null;
            } else {
                return getPiece(pos).getTeamColor();
            }
        } else {
            return null;
        }
    }

    public boolean inBounds(ChessPosition pos){
        return pos.getRow() >= 1 && pos.getColumn() >= 1 && pos.getRow() <= 8 && pos.getColumn() <= 8;
    }

    public ChessPosition findKing(ChessGame.TeamColor team){
        for(int r = 1; r <= 8; r++){
            for(int c = 1; c <= 8; c++){
                ChessPosition pos = new ChessPosition(r, c);
                if(getColor(pos) == team){
                    if(getPiece(pos).getPieceType() == ChessPiece.PieceType.KING){
                        return pos;
                    }
                }
            }
        }
        return new ChessPosition(0, 0);
    }

    public boolean kingsAdjacent(){
        var whitePos = findKing(ChessGame.TeamColor.WHITE);
        var blackPos = findKing(ChessGame.TeamColor.BLACK);
        return (Math.abs(whitePos.getRow() - blackPos.getRow()) <= 1 && Math.abs(whitePos.getColumn() - blackPos.getColumn()) <= 1);
    }
    /**
     * Sets the board to the default starting board
     * (How the game of chess normally starts)
     */
    public void resetBoard() {
        addPiece(new ChessPosition(1, 1), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK));
        addPiece(new ChessPosition(1, 2), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KNIGHT));
        addPiece(new ChessPosition(1, 3), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.BISHOP));
        addPiece(new ChessPosition(1, 4), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.QUEEN));
        addPiece(new ChessPosition(1, 5), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KING));
        addPiece(new ChessPosition(1, 6), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.BISHOP));
        addPiece(new ChessPosition(1, 7), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KNIGHT));
        addPiece(new ChessPosition(1, 8), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK));
        addPiece(new ChessPosition(2, 1), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN));
        addPiece(new ChessPosition(2, 2), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN));
        addPiece(new ChessPosition(2, 3), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN));
        addPiece(new ChessPosition(2, 4), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN));
        addPiece(new ChessPosition(2, 5), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN));
        addPiece(new ChessPosition(2, 6), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN));
        addPiece(new ChessPosition(2, 7), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN));
        addPiece(new ChessPosition(2, 8), new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN));
        addPiece(new ChessPosition(8, 1), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.ROOK));
        addPiece(new ChessPosition(8, 2), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KNIGHT));
        addPiece(new ChessPosition(8, 3), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.BISHOP));
        addPiece(new ChessPosition(8, 4), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.QUEEN));
        addPiece(new ChessPosition(8, 5), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KING));
        addPiece(new ChessPosition(8, 6), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.BISHOP));
        addPiece(new ChessPosition(8, 7), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KNIGHT));
        addPiece(new ChessPosition(8, 8), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.ROOK));
        addPiece(new ChessPosition(7, 1), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN));
        addPiece(new ChessPosition(7, 2), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN));
        addPiece(new ChessPosition(7, 3), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN));
        addPiece(new ChessPosition(7, 4), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN));
        addPiece(new ChessPosition(7, 5), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN));
        addPiece(new ChessPosition(7, 6), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN));
        addPiece(new ChessPosition(7, 7), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN));
        addPiece(new ChessPosition(7, 8), new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN));
    }

    public ChessPiece[][] getBoard() {
        return board;
    }

    public void setBoard(ChessPiece[][] board) {
        this.board = board;
    }

    public ChessPosition PassantPos(){
        return enPassantTarget;
    }

    public void setPassant(ChessPosition pos){
        this.enPassantTarget = pos;
    }

    public boolean isDoingPassant() {
        return doPassant;
    }

    public void setDoingPassant(boolean doPassant) {
        this.doPassant = doPassant;
    }

    @Override
    public String toString() {
        StringBuilder boardStr = new StringBuilder();
        for(int row = 1; row <= 8; row++){
            boardStr.append("|");
            for(int col = 1; col <= 8; col++){
                var piece = getPiece(new ChessPosition(row, col));
                if(piece != null){
                    switch(piece.getPieceType()){
                        case ChessPiece.PieceType.KING: boardStr.append(piece.getTeamColor() == ChessGame.TeamColor.WHITE ? "K" : "k"); break;
                        case ChessPiece.PieceType.QUEEN: boardStr.append(piece.getTeamColor() == ChessGame.TeamColor.WHITE ? "Q" : "q"); break;
                        case ChessPiece.PieceType.ROOK: boardStr.append(piece.getTeamColor() == ChessGame.TeamColor.WHITE ? "R" : "r"); break;
                        case ChessPiece.PieceType.BISHOP: boardStr.append(piece.getTeamColor() == ChessGame.TeamColor.WHITE ? "B" : "b"); break;
                        case ChessPiece.PieceType.KNIGHT: boardStr.append(piece.getTeamColor() == ChessGame.TeamColor.WHITE ? "N" : "n"); break;
                        case ChessPiece.PieceType.PAWN: boardStr.append(piece.getTeamColor() == ChessGame.TeamColor.WHITE ? "P" : "p"); break;
                    }
                } else {
                    boardStr.append(" ");
                }
                boardStr.append("|");
            }
            boardStr.append("\n");
        }
        return boardStr.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessBoard that = (ChessBoard) o;
        return Objects.deepEquals(board, that.board);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(board);
    }
}
