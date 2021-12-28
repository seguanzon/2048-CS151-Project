//package Model;
//
//import static org.junit.Assert.*;
//
//import org.junit.Before;
//import org.junit.Test;
//
//public class BoardTest {
//	Board game;
//
//	@Before
//	public void setUp() throws Exception {
//		game = new Board();
//
//	}
//
//
//	@Test
//	public void testCombineMatch() {
//		Number tile = new Number(0, 0, "Blue");
//		assertEquals(tile.getNumValue(), 2);
//		int prevScore = game.getGameScore().getScore();
//		game.combineMatch(tile);
//		assertEquals(tile.getNumValue(), 4);
//		assertEquals(tile.getColor(), "Blue");
//		assertTrue(game.getGameScore().getScore()-prevScore == 4);
//
//	}
//
//	@Test
//	public void testMoveUp() {
//		int prevScore = game.getGameScore().getScore();
//		game.populateTiles();
//		game.populateTiles();
//		game.populateTiles();
//		game.populateTiles();
//		game.moveUp();
//		assertTrue(prevScore <= game.getGameScore().getScore());
//	}
//
//	@Test
//	public void testMoveDown() {
//		int prevScore = game.getGameScore().getScore();
//		game.populateTiles();
//		game.populateTiles();
//		game.populateTiles();
//		game.populateTiles();
//		game.moveDown();
//		assertTrue(prevScore <= game.getGameScore().getScore());
//	}
//
//	@Test
//	public void testMoveLeft() {
//		int prevScore = game.getGameScore().getScore();
//		game.populateTiles();
//		game.populateTiles();
//		game.populateTiles();
//		game.populateTiles();
//		game.moveLeft();
//		assertTrue(prevScore <= game.getGameScore().getScore());
//	}
//
//	@Test
//	public void testMoveRight() {
//		int prevScore = game.getGameScore().getScore();
//		game.populateTiles();
//		game.populateTiles();
//		game.populateTiles();
//		game.populateTiles();
//		game.moveRight();
//		assertTrue(prevScore <= game.getGameScore().getScore());
//	}
//
//
//
//}
