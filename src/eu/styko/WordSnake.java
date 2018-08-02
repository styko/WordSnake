package eu.styko;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class WordSnake {

	private static final String NEWLINE = "\r\n";
	private List<String> words;
	private int horizontalIndex;

	private enum HorizontalDirection {
		LEFT, RIGHT
	};

	public String execute(String input) {
		validateIfInputIsNotEmpty(input);
		splitInputToWords(input);
		validateWords();

		int wordIndex = 0;
		horizontalIndex = 0;
		StringBuilder wordSnake = new StringBuilder();
		for (String currentWord : words) {
			if (isEvenWord(wordIndex)) {
				if (isFirstWord(wordIndex)) {
					wordSnake.append(currentWord);
					horizontalIndex = horizontalIndex + currentWord.length();
				} else {
					wordSnake.append(createEvenWord(currentWord, horizontalIndex));
				}
			} else {
				wordSnake.append(createOddWord(currentWord, horizontalIndex, shouldLastLetterBeRemoved(wordIndex)));
			}

			wordIndex++;
		}

		return wordSnake.toString();
	}

	private boolean shouldLastLetterBeRemoved(int wordIndex) {
		return (wordIndex + 1) != words.size();
	}

	private String createEvenWord(String currentWord, int countOfSpaces) {
		if (forceRightHorizontalDirection(currentWord) || HorizontalDirection.RIGHT.equals(getRandomHorizontalDirection())) {
			return createEvenWordRightToHorizontalIndex(currentWord, countOfSpaces);
		} else {
			return createEvenWordLeftToHorizontalIndex(currentWord, countOfSpaces);
		}
	}

	private String createEvenWordLeftToHorizontalIndex(String currentWord, int countOfSpaces) {
		String evenWord = NEWLINE + horizontalAllignInput(countOfSpaces, reverse(currentWord));
		horizontalIndex = horizontalIndex - currentWord.length() + 1;
		return evenWord;
	}

	private String createEvenWordRightToHorizontalIndex(String currentWord, int countOfSpaces) {
		int horizontalAlignIndex = getRightHorizontalAlignIndex(currentWord.length(), countOfSpaces);
		String evenWord = NEWLINE + horizontalAllignInput(horizontalAlignIndex, currentWord);
		horizontalIndex = horizontalIndex + currentWord.length() - 1;
		return evenWord;
	}

	private boolean forceRightHorizontalDirection(String currentWord) {
		return horizontalIndex < currentWord.length() - 1;
	}

	private String reverse(String currentWord) {
		return new StringBuilder().append(currentWord).reverse().toString();
	}

	private int getRightHorizontalAlignIndex(int currentWordLenght, int countOfSpaces) {
		return countOfSpaces + currentWordLenght - 1;
	}

	private HorizontalDirection getRandomHorizontalDirection() {
		int randomInt = new Random().nextInt(2);

		if (randomInt == 1) {
			return HorizontalDirection.LEFT;
		} else {
			return HorizontalDirection.RIGHT;
		}
	}

	private boolean isFirstWord(int wordIndex) {
		return wordIndex == 0;
	}

	private String createOddWord(String word, int countOfSpaces, boolean removeLastLetter) {
		StringBuilder oddWordBuilder = new StringBuilder();

		int letterIndex = 0;
		List<String> letters = splitToLetters(word);
		for (String letter : letters) {
			if (!(isFirstLetter(letterIndex) || removeLastLetter && isLastLetter(letterIndex, letters))) {
				appendNewLine(oddWordBuilder);
				appendAlignedLetter(countOfSpaces, oddWordBuilder, letter);
			}

			letterIndex++;
		}

		return oddWordBuilder.toString();
	}

	private boolean isLastLetter(int letterIndex, List<String> letters) {
		return (letterIndex + 1) == letters.size();
	}

	private boolean isFirstLetter(int letterIndex) {
		return isFirstWord(letterIndex);
	}

	private void appendAlignedLetter(int countOfSpaces, StringBuilder verticalWord, String letter) {
		verticalWord.append(horizontalAllignInput(countOfSpaces, letter));
	}

	private String horizontalAllignInput(int countOfSpaces, String input) {
		return String.format("%" + countOfSpaces + "s", input);
	}

	private void appendNewLine(StringBuilder verticalWord) {
		verticalWord.append(NEWLINE);
	}

	private List<String> splitToLetters(String word) {
		return Arrays.asList(word.split(""));
	}

	private boolean isEvenWord(int index) {
		return index % 2 == 0;
	}

	private void splitInputToWords(String input) {
		words = Arrays.asList(input.split("\\s"));
	}

	private void validateWords() {
		String previusWord = "";
		for (String currentWord : words) {
			validateLettersOfWords(previusWord, currentWord);
			validateEachLetterIsCapital(currentWord);
			previusWord = currentWord;
		}
	}

	private void validateEachLetterIsCapital(String currentWord) {
		if (!currentWord.toUpperCase().equals(currentWord)) {
			throw new IllegalArgumentException("Each word has to have capital letters");
		}
	}

	private void validateLettersOfWords(String previusWord, String currentWord) {
		String lastLetterOfCurrentWord = getFirstLetter(currentWord);
		if (!previusWord.isEmpty() && !lastLetterOfCurrentWord.equalsIgnoreCase(getLastLetter(previusWord))) {
			throw new IllegalArgumentException("First and last letters dont match currentWord=" + currentWord + " previusWord=" + previusWord);
		}
	}

	private void validateIfInputIsNotEmpty(String input) {
		if (input == null || input.isEmpty()) {
			throw new IllegalArgumentException();
		}
	}

	private String getLastLetter(String word) {
		return word.substring(word.length() - 1);
	}

	private String getFirstLetter(String word) {
		return word.substring(0, 1);
	}
}
