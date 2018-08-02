package eu.styko;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class WordSnakeTest {
	
	private WordSnake snake = new WordSnake();
	
	@Test
	public void createdSnakeShouldNotReturnNull() {
		assertNotNull(snake.execute("SHENANIGANS"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void createdSnakeShouldCheckEmptyInput(){
		snake.execute("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void createdSnakeShouldCheckNullInput(){
		snake.execute(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void createdSnakeShouldCheckEveryWordEndsSameLetterAsFirstLetterOfNextWord1(){
		snake.execute("SHENANIGANS BALTY");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void createdSnakeShouldCheckEveryWordEndsSameLetterAsFirstLetterOfNextWord2(){
		snake.execute("SALTY HOUNGSTER POSTER");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void createdSnakeShouldCheckEveryWordHasCapitalLetters(){
		snake.execute("SHEnANIGANS SaLTY");
	}
	
	/**
	 * Expected text
	 *      SHENANIGANS
			          A
			          L
			          T
			          Y
	 */
	@Test
	public void createdSnakeShouldReturnExpectedText2() {
		String expectedString = String.format("SHENANIGANS\r\n          A\r\n          L\r\n          T\r\n          Y");
		assertEquals(expectedString, snake.execute("SHENANIGANS SALTY"));
	}
	
	
	/**
	 * Expected text
	 *  SHENANIGANS
		          A
		          L
		          T
		          YOUNGSTER
		    
		          or
		          
		        SHENANIGANS
				          A
				          L
				          T
				  RETSGNUOY
	 */
	@Test
	public void createdSnakeShouldReturnExpectedText3() {
		String expectedString1 = String.format("SHENANIGANS\r\n          A\r\n          L\r\n          T\r\n  RETSGNUOY");
		String expectedString2 = String.format("SHENANIGANS\r\n          A\r\n          L\r\n          T\r\n          YOUNGSTER");
		//System.out.println(String.format("SHENANIGANS\r\n          A\r\n          L\r\n          T\r\n          YUNGSTER"));
		assertThat(snake.execute("SHENANIGANS SALTY YOUNGSTER"),  anyOf(is(expectedString2), is(expectedString1)));
	}
	
	@Test
	public void createdSnakeShouldRandomHorizontalDirectionOfEvenWords() {
		String expectedString1 = String.format("SHENANIGANS\r\n          A\r\n          L\r\n          T\r\n  RETSGNUOY");
		int counterExpectedString1 = 0;
		String expectedString2 = String.format("SHENANIGANS\r\n          A\r\n          L\r\n          T\r\n          YOUNGSTER");
		int counterExpectedString2 = 0;
		
		int loopCounter = 0;
		while (loopCounter < 10 && (counterExpectedString1 == 0 || counterExpectedString2 == 0)) {
			String result = snake.execute("SHENANIGANS SALTY YOUNGSTER");
			//System.out.println(result);
			if(result.equals(expectedString1)){
				counterExpectedString1++;
			} else if(result.equals(expectedString2)){
				counterExpectedString2++;
			}
			loopCounter++;
		}
		
		assertThat("Counters are increment", counterExpectedString1 > 0 && counterExpectedString2 > 0);
	}
}
