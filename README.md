# WordSnake
Interview assigment - Word snake which goes just down and randomly left or right on even words. Added test suite for candidates.

Original assigment is taken from:
https://www.reddit.com/r/dailyprogrammer/comments/3bi5na/20150629_challenge_221_easy_word_snake/

Description
A word snake is (unsurprisingly) a snake made up of a sequence of words.

For instance, take this sequence of words:

SHENANIGANS SALTY YOUNGSTER ROUND DOUBLET TERABYTE ESSENCE
Notice that the last letter in each word is the same as the first letter in the next word. In order to make this into a word snake, you simply snake it across the screen

SHENANIGANS        
          A        
          L        
          T        
          YOUNGSTER
                  O
                  U
                  N
            TELBUOD
            E      
            R      
            A      
            B      
            Y      
            T      
            ESSENCE
Your task today is to take an input word sequence and turn it into a word snake. Here are the rules for the snake:

It has to start in the top left corner

Each word has to turn 90 degrees left or right to the previous word

The snake can't intersect itself

Other than that, you're free to decide how the snake should "snake around". If you want to make it easy for yourself and simply have it alternate between going right and going down, that's perfectly fine. If you want to make more elaborate shapes, that's fine too.

Formal inputs & outputs
Input
The input will be a single line of words (written in ALL CAPS). The last letter of each word will be the first letter in the next.

Output
Your word snake! Make it look however you like, as long as it follows the rules.
