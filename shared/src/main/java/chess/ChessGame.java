package chess;

import java.util.ArrayList;
import java.util.Collection;

/**
 * For a class that can manage a chess game, making moves on a board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessGame {

    private TeamColor turn;
    private ChessBoard board;

    public ChessGame() {
        this.setTeamTurn(TeamColor.WHITE);
        this.board = new ChessBoard();
        this.board.resetBoard();
    }
    public void p(String s){
        System.out.println(s);
    }
    /**
     * @return Which team's turn it is
     */
    public TeamColor getTeamTurn() {
        return this.turn;
    }

    /**
     * Set's which teams turn it is
     *
     * @param team the team whose turn it is
     */
    public void setTeamTurn(TeamColor team) {
        this.turn = team;
    }

    /**
     * Enum identifying the 2 possible teams in a chess game
     */
    public enum TeamColor {
        WHITE,
        BLACK
    }

    /**
     * Gets a valid moves for a piece at the given location
     *
     * @param startPosition the piece to get valid moves for
     * @return Set of valid moves for requested piece, or null if no piece at
     * startPosition
     */
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {
        if(board.getColor(startPosition) == null){
            return null;
        }
        var piece = board.getPiece(startPosition);
        var moves = piece.pieceMoves(this.board, startPosition);
        Collection<ChessMove> newMoves = new ArrayList<>();
        var team = piece.getTeamColor();
        for(ChessMove move : moves){
            board.addPiece(startPosition, null);
            var deadPiece = board.getPiece(move.getEndPosition());
            board.addPiece(move.getEndPosition(), piece);
            if(!this.isInCheck(team) && !board.kingsAdjacent()){
                newMoves.add(move);
            }
            board.addPiece(startPosition, piece);
            board.addPiece(move.getEndPosition(), deadPiece);
        }
        return newMoves;
    }

    /**
     * Makes a move in a chess game
     *
     * @param move chess move to preform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {
        if(board.getColor(move.getStartPosition()) != getTeamTurn()){
            throw new InvalidMoveException("Invalid Move");
        }
        Collection<ChessMove> moves = validMoves(move.getStartPosition());
        if(moves.isEmpty()){
            throw new InvalidMoveException("Invalid Move");
        }
        if(!moves.contains(move)){
            throw new InvalidMoveException("Invalid Move");
        }

        throw new RuntimeException("Not implemented");
    }

    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) {
        var kingPos = board.findKing(teamColor);
        for(int r = 1; r <= 8; r++){
            for(int c = 1; c <= 8; c++){
                ChessPosition pos = new ChessPosition(r, c);
                if(board.getColor(pos) != teamColor && board.getColor(pos) != null){
                    var piece = board.getPiece(pos);
                    Collection<ChessMove> moves = piece.pieceMoves(this.board, pos);
                    for(ChessMove move : moves){
                        if(move.getEndPosition().equals(kingPos)){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    public boolean isInCheckmate(TeamColor teamColor) {
        if(!isInCheck(teamColor)){
            return false;
        }
        for(int r = 1; r <= 8; r++){
            for(int c = 1; c <= 8; c++){
                var pos = new ChessPosition(r, c);
                if(board.getColor(pos) == teamColor){
                    if(!validMoves(pos).isEmpty()){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {
        if(isInCheck(teamColor)){
            return false;
        }
        for(int r = 1; r <= 8; r++){
            for(int c = 1; c <= 8; c++){
                var pos = new ChessPosition(r, c);
                if(board.getColor(pos) == teamColor){
                    if(!validMoves(pos).isEmpty()){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Sets this game's chessboard with a given board
     *
     * @param board the new board to use
     */
    public void setBoard(ChessBoard board) {
        this.board = board;
    }

    /**
     * Gets the current chessboard
     *
     * @return the chessboard
     */
    public ChessBoard getBoard() {
        return this.board;
    }
}
