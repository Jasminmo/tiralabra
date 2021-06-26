package datastructureproject.datastructures;

/** This enum represents Side to move in the chess board. */
public enum Side {
    /**
     * Black side of the board.
     */
    BLACK,
    /**
     * White side of the board.
     */
    WHITE;

    /**
     * Parse FEN-String and get the Side to move next.
     *
     * @param fen the FEN-String
     * @return the side
     */
    public static Side parseFen(String fen) {
        String letter = fen.split(" ")[1];
        if (letter.toLowerCase().charAt(0) == 'w')
            return WHITE;
        else if (letter.toLowerCase().charAt(0) == 'b')
            return BLACK;
        else
            throw new IllegalArgumentException("Incorrect fen format");
    }

    /**
     * Parse string as a Side.
     *
     * @param v the v
     * @return the side
     */
    public static Side parseString(String v) {
        return valueOf(v.toUpperCase());
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return name();
    }

    /**
     * Flip the side of the board.
     *
     * @return the side
     */
    public Side flip() {
        return BLACK.equals(this) ? WHITE : BLACK;
    }
}
