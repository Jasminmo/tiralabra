package datastructureproject.chess;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SideTest {
    private static String startingFen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1\n";
    private static String nextMoveFen = "rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1\n";

    @Test
    public void testWhiteStartsInTheStartingPosition () {
        assertEquals("white starts", Side.WHITE, Side.parseFen(startingFen));
    }

    @Test
    public void testBlacksTurnInTheNextPosition () {
        assertEquals("black moves next", Side.BLACK, Side.parseFen(nextMoveFen));
    }

    @Test
    public void testParseString () {
        assertEquals(Side.BLACK, Side.parseString(Side.BLACK.toString()));
        assertEquals(Side.WHITE, Side.parseString(Side.WHITE.toString()));
    }

    @Test
    public void testParseStringIgnoresCase () {
        assertEquals(Side.BLACK, Side.parseString(Side.BLACK.toString().toLowerCase()));
        assertEquals(Side.WHITE, Side.parseString(Side.WHITE.toString().toLowerCase()));
    }

    @Test
    public void testFlip () {
        assertEquals(Side.BLACK, Side.WHITE.flip());
        assertEquals(Side.WHITE, Side.BLACK.flip());
    }

}
