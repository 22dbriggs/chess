package chess;

import javax.swing.text.Position;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {

    private ChessGame.TeamColor pieceColor;
    private ChessPiece.PieceType type;


    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        this.pieceColor = pieceColor;
        this.type = type;
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return this.pieceColor;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return this.type;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> moves = new ArrayList<>();
        int r = myPosition.getRow();
        int c = myPosition.getColumn();
        var team = pieceColor;
        var otherTeam = team == ChessGame.TeamColor.WHITE ? ChessGame.TeamColor.WHITE : ChessGame.TeamColor.BLACK;
        ChessPosition newPos;
        int moveC, moveR = 0;
        switch(this.type){
            case KING: {
                newPos = new ChessPosition(r + 1, c);
                if(board.getColor(newPos) != team && board.inBounds(newPos)){
                    moves.add(new ChessMove(myPosition, newPos, null));
                }
                newPos = new ChessPosition(r - 1, c);
                if(board.getColor(newPos) != team && board.inBounds(newPos)){
                    moves.add(new ChessMove(myPosition, newPos, null));
                }
                newPos = new ChessPosition(r, c + 1);
                if(board.getColor(newPos) != team && board.inBounds(newPos)){
                    moves.add(new ChessMove(myPosition, newPos, null));
                }
                newPos = new ChessPosition(r, c - 1);
                if(board.getColor(newPos) != team && board.inBounds(newPos)){
                    moves.add(new ChessMove(myPosition, newPos, null));
                }
                newPos = new ChessPosition(r + 1, c + 1);
                if(board.getColor(newPos) != team && board.inBounds(newPos)){
                    moves.add(new ChessMove(myPosition, newPos, null));
                }
                newPos = new ChessPosition(r - 1, c + 1);
                if(board.getColor(newPos) != team && board.inBounds(newPos)){
                    moves.add(new ChessMove(myPosition, newPos, null));
                }
                newPos = new ChessPosition(r + 1, c - 1);
                if(board.getColor(newPos) != team && board.inBounds(newPos)){
                    moves.add(new ChessMove(myPosition, newPos, null));
                }
                newPos = new ChessPosition(r - 1, c - 1);
                if(board.getColor(newPos) != team && board.inBounds(newPos)){
                    moves.add(new ChessMove(myPosition, newPos, null));
                }
            } break;
            case QUEEN: {
                // go upleft
                moveR = 1;
                moveC = -1;
                newPos = new ChessPosition(r + moveR, c + moveC);
                while(board.getColor(newPos) != team && board.inBounds(newPos)){
                    moves.add(new ChessMove(myPosition, newPos, null));
                    if(board.getColor(newPos) != null){
                        break;
                    }
                    moveC--;
                    moveR++;
                    newPos = new ChessPosition(r + moveR, c + moveC);
                }
                // go upright
                moveR = 1;
                moveC = 1;
                newPos = new ChessPosition(r + moveR, c + moveC);
                while(board.getColor(newPos) != team && board.inBounds(newPos)){
                    moves.add(new ChessMove(myPosition, newPos, null));
                    if(board.getColor(newPos) != null){
                        break;
                    }
                    moveC++;
                    moveR++;
                    newPos = new ChessPosition(r + moveR, c + moveC);
                }
                // go downleft
                moveR = -1;
                moveC = -1;
                newPos = new ChessPosition(r + moveR, c + moveC);
                while(board.getColor(newPos) != team && board.inBounds(newPos)){
                    moves.add(new ChessMove(myPosition, newPos, null));
                    if(board.getColor(newPos) != null){
                        break;
                    }
                    moveC--;
                    moveR--;
                    newPos = new ChessPosition(r + moveR, c + moveC);
                }
                // go downright
                moveR = -1;
                moveC = 1;
                newPos = new ChessPosition(r + moveR, c + moveC);
                while(board.getColor(newPos) != team && board.inBounds(newPos)){
                    moves.add(new ChessMove(myPosition, newPos, null));
                    if(board.getColor(newPos) != null){
                        break;
                    }
                    moveC++;
                    moveR--;
                    newPos = new ChessPosition(r + moveR, c + moveC);
                }
                // Go right
                moveR = 0;
                moveC = 1;
                newPos = new ChessPosition(r + moveR, c + moveC);
                while(board.getColor(newPos) != team && board.inBounds(newPos)){
                    moves.add(new ChessMove(myPosition, newPos, null));
                    if(board.getColor(newPos) != null){
                        break;
                    }
                    moveC++;
                    newPos = new ChessPosition(r + moveR, c + moveC);
                }
                // Go left
                moveR = 0;
                moveC = -1;
                newPos = new ChessPosition(r + moveR, c + moveC);
                while(board.getColor(newPos) != team && board.inBounds(newPos)){
                    moves.add(new ChessMove(myPosition, newPos, null));
                    if(board.getColor(newPos) != null){
                        break;
                    }
                    moveC--;
                    newPos = new ChessPosition(r + moveR, c + moveC);
                }
                // Go up
                moveR = 1;
                moveC = 0;
                newPos = new ChessPosition(r + moveR, c + moveC);
                while(board.getColor(newPos) != team && board.inBounds(newPos)){
                    moves.add(new ChessMove(myPosition, newPos, null));
                    if(board.getColor(newPos) != null){
                        break;
                    }
                    moveR++;
                    newPos = new ChessPosition(r + moveR, c + moveC);
                }
                // Go down
                moveR = -1;
                moveC = 0;
                newPos = new ChessPosition(r + moveR, c + moveC);
                while(board.getColor(newPos) != team && board.inBounds(newPos)){
                    moves.add(new ChessMove(myPosition, newPos, null));
                    if(board.getColor(newPos) != null){
                        break;
                    }
                    moveR--;
                    newPos = new ChessPosition(r + moveR, c + moveC);
                }
            } break;
            case ROOK: {
                // Go right
                moveR = 0;
                moveC = 1;
                newPos = new ChessPosition(r + moveR, c + moveC);
                while(board.getColor(newPos) != team && board.inBounds(newPos)){
                    moves.add(new ChessMove(myPosition, newPos, null));
                    if(board.getColor(newPos) != null){
                        break;
                    }
                    moveC++;
                    newPos = new ChessPosition(r + moveR, c + moveC);
                }
                // Go left
                moveR = 0;
                moveC = -1;
                newPos = new ChessPosition(r + moveR, c + moveC);
                while(board.getColor(newPos) != team && board.inBounds(newPos)){
                    moves.add(new ChessMove(myPosition, newPos, null));
                    if(board.getColor(newPos) != null){
                        break;
                    }
                    moveC--;
                    newPos = new ChessPosition(r + moveR, c + moveC);
                }
                // Go up
                moveR = 1;
                moveC = 0;
                newPos = new ChessPosition(r + moveR, c + moveC);
                while(board.getColor(newPos) != team && board.inBounds(newPos)){
                    moves.add(new ChessMove(myPosition, newPos, null));
                    if(board.getColor(newPos) != null){
                        break;
                    }
                    moveR++;
                    newPos = new ChessPosition(r + moveR, c + moveC);
                }
                // Go down
                moveR = -1;
                moveC = 0;
                newPos = new ChessPosition(r + moveR, c + moveC);
                while(board.getColor(newPos) != team && board.inBounds(newPos)){
                    moves.add(new ChessMove(myPosition, newPos, null));
                    if(board.getColor(newPos) != null){
                        break;
                    }
                    moveR--;
                    newPos = new ChessPosition(r + moveR, c + moveC);
                }

            } break;
            case BISHOP: {
                // go upleft
                moveR = 1;
                moveC = -1;
                newPos = new ChessPosition(r + moveR, c + moveC);
                while(board.getColor(newPos) != team && board.inBounds(newPos)){
                    moves.add(new ChessMove(myPosition, newPos, null));
                    if(board.getColor(newPos) != null){
                        break;
                    }
                    moveC--;
                    moveR++;
                    newPos = new ChessPosition(r + moveR, c + moveC);
                }
                // go upright
                moveR = 1;
                moveC = 1;
                newPos = new ChessPosition(r + moveR, c + moveC);
                while(board.getColor(newPos) != team && board.inBounds(newPos)){
                    moves.add(new ChessMove(myPosition, newPos, null));
                    if(board.getColor(newPos) != null){
                        break;
                    }
                    moveC++;
                    moveR++;
                    newPos = new ChessPosition(r + moveR, c + moveC);
                }
                // go downleft
                moveR = -1;
                moveC = -1;
                newPos = new ChessPosition(r + moveR, c + moveC);
                while(board.getColor(newPos) != team && board.inBounds(newPos)){
                    moves.add(new ChessMove(myPosition, newPos, null));
                    if(board.getColor(newPos) != null){
                        break;
                    }
                    moveC--;
                    moveR--;
                    newPos = new ChessPosition(r + moveR, c + moveC);
                }
                // go downright
                moveR = -1;
                moveC = 1;
                newPos = new ChessPosition(r + moveR, c + moveC);
                while(board.getColor(newPos) != team && board.inBounds(newPos)){
                    moves.add(new ChessMove(myPosition, newPos, null));
                    if(board.getColor(newPos) != null){
                        break;
                    }
                    moveC++;
                    moveR--;
                    newPos = new ChessPosition(r + moveR, c + moveC);
                }
            } break;
            case KNIGHT: {
                newPos = new ChessPosition(r + 2, c + 1);
                if(board.getColor(newPos) != team && board.inBounds(newPos)){
                    moves.add(new ChessMove(myPosition, newPos, null));
                }
                newPos = new ChessPosition(r + 2, c - 1);
                if(board.getColor(newPos) != team && board.inBounds(newPos)){
                    moves.add(new ChessMove(myPosition, newPos, null));
                }
                newPos = new ChessPosition(r - 2, c + 1);
                if(board.getColor(newPos) != team && board.inBounds(newPos)){
                    moves.add(new ChessMove(myPosition, newPos, null));
                }
                newPos = new ChessPosition(r - 2, c - 1);
                if(board.getColor(newPos) != team && board.inBounds(newPos)){
                    moves.add(new ChessMove(myPosition, newPos, null));
                }
                newPos = new ChessPosition(r + 1, c + 2);
                if(board.getColor(newPos) != team && board.inBounds(newPos)){
                    moves.add(new ChessMove(myPosition, newPos, null));
                }
                newPos = new ChessPosition(r - 1, c + 2);
                if(board.getColor(newPos) != team && board.inBounds(newPos)){
                    moves.add(new ChessMove(myPosition, newPos, null));
                }
                newPos = new ChessPosition(r + 1, c - 2);
                if(board.getColor(newPos) != team && board.inBounds(newPos)){
                    moves.add(new ChessMove(myPosition, newPos, null));
                }
                newPos = new ChessPosition(r - 1, c - 2);
                if(board.getColor(newPos) != team && board.inBounds(newPos)){
                    moves.add(new ChessMove(myPosition, newPos, null));
                }
            } break;
            case PAWN: {
                if(this.pieceColor == ChessGame.TeamColor.WHITE){
                    // Forward 1
                    newPos = new ChessPosition(r + 1, c);
                    if(board.getPiece(newPos) == null && board.inBounds(newPos)){
                        if(r == 7){
                            // promotion
                            moves.add(new ChessMove(myPosition, newPos, ChessPiece.PieceType.QUEEN));
                            moves.add(new ChessMove(myPosition, newPos, ChessPiece.PieceType.ROOK));
                            moves.add(new ChessMove(myPosition, newPos, ChessPiece.PieceType.BISHOP));
                            moves.add(new ChessMove(myPosition, newPos, ChessPiece.PieceType.KNIGHT));
                        } else {
                            moves.add(new ChessMove(myPosition, newPos, null));
                        }
                    }
                    // Left Diagonal
                    newPos = new ChessPosition(r + 1, c - 1);
                    if(board.getColor(newPos) != null && board.inBounds(newPos)){
                        if(board.getColor(newPos) == ChessGame.TeamColor.BLACK){
                            if(r == 7){
                                // promotion

                                moves.add(new ChessMove(myPosition, newPos, PieceType.QUEEN));
                                moves.add(new ChessMove(myPosition, newPos, PieceType.ROOK));
                                moves.add(new ChessMove(myPosition, newPos, PieceType.BISHOP));
                                moves.add(new ChessMove(myPosition, newPos, PieceType.KNIGHT));
                            } else {
                                moves.add(new ChessMove(myPosition, newPos, null));
                            }
                        }
                    }
                    // Right Diagonal
                    newPos = new ChessPosition(r + 1, c + 1);
                    if(board.getColor(newPos) != null && board.inBounds(newPos)){
                        if(board.getColor(newPos) == ChessGame.TeamColor.BLACK){
                            if(r == 7){
                                // promotion
                                moves.add(new ChessMove(myPosition, newPos, PieceType.QUEEN));
                                moves.add(new ChessMove(myPosition, newPos, PieceType.ROOK));
                                moves.add(new ChessMove(myPosition, newPos, PieceType.BISHOP));
                                moves.add(new ChessMove(myPosition, newPos, PieceType.KNIGHT));
                            } else {
                                moves.add(new ChessMove(myPosition, newPos, null));
                            }
                        }
                    }
                    // Forward 2
                    newPos = new ChessPosition(r + 2, c);
                    if(r == 2){
                        System.out.println("WHITE move 2");
                        if(board.getPiece(new ChessPosition(r + 1, c)) == null && board.getPiece(newPos) == null){
                            moves.add(new ChessMove(myPosition, newPos, null, true, false));
                        }
                    }
                    // En Passant
                    if(board.PassantPos() != null){
                        newPos = new ChessPosition(board.PassantPos().getRow(), board.PassantPos().getColumn());
                        if(r == 5 && (c == newPos.getColumn() - 1 || c == newPos.getColumn() + 1)){
                            System.out.println("WHITE WHAAA");
                            moves.add(new ChessMove(myPosition, newPos, null, false, true));
                        }
                    }
                }
                if(this.pieceColor == ChessGame.TeamColor.BLACK){
                    // Forward 1
                    newPos = new ChessPosition(r - 1, c);
                    if(board.getPiece(newPos) == null && board.inBounds(newPos)){
                        if(r == 2){
                            // promotion
                            moves.add(new ChessMove(myPosition, newPos, PieceType.QUEEN));
                            moves.add(new ChessMove(myPosition, newPos, PieceType.ROOK));
                            moves.add(new ChessMove(myPosition, newPos, PieceType.BISHOP));
                            moves.add(new ChessMove(myPosition, newPos, PieceType.KNIGHT));

                        } else {
                            moves.add(new ChessMove(myPosition, newPos, null));
                        }
                    }
                    // Left Diagonal
                    newPos = new ChessPosition(r - 1, c - 1);
                    if(board.getColor(newPos) != null && board.inBounds(newPos)){
                        if(board.getColor(newPos) == ChessGame.TeamColor.WHITE){
                            if(r == 2){
                                // promotion
                                moves.add(new ChessMove(myPosition, newPos, PieceType.QUEEN));
                                moves.add(new ChessMove(myPosition, newPos, PieceType.ROOK));
                                moves.add(new ChessMove(myPosition, newPos, PieceType.BISHOP));
                                moves.add(new ChessMove(myPosition, newPos, PieceType.KNIGHT));
                            } else {
                                moves.add(new ChessMove(myPosition, newPos, null));
                            }
                        }
                    }
                    // Right Diagonal
                    newPos = new ChessPosition(r - 1, c + 1);
                    if(board.getColor(newPos) != null && board.inBounds(newPos)){
                        if(board.getColor(newPos) == ChessGame.TeamColor.WHITE){
                            if(r == 2){
                                // promotion
                                moves.add(new ChessMove(myPosition, newPos, PieceType.QUEEN));
                                moves.add(new ChessMove(myPosition, newPos, PieceType.ROOK));
                                moves.add(new ChessMove(myPosition, newPos, PieceType.BISHOP));
                                moves.add(new ChessMove(myPosition, newPos, PieceType.KNIGHT));
                            } else {
                                moves.add(new ChessMove(myPosition, newPos, null));
                            }
                        }
                    }
                    // Forward 2
                    newPos = new ChessPosition(r - 2, c);
                    if(r == 7){
                        if(board.getPiece(new ChessPosition(r - 1, c)) == null && board.getPiece(newPos) == null){
                            moves.add(new ChessMove(myPosition, newPos, null, true, false));
                        }
                    }
                    // En Passant
                    if(board.PassantPos() != null){
                        newPos = new ChessPosition(board.PassantPos().getRow(), board.PassantPos().getColumn());
                        if(r == 4 && (c == newPos.getColumn() - 1 || c == newPos.getColumn() + 1)){
                            System.out.println("WHAAAA");
                            moves.add(new ChessMove(myPosition, newPos, null, false, true));
                        }
                    }
                }
            } break;
        }
        return moves;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessPiece that = (ChessPiece) o;
        return pieceColor == that.pieceColor && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceColor, type);
    }
}
