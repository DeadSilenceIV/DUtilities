package us.lynuxcraft.deadsilenceiv.dutilities;

import java.util.LinkedList;
import java.util.Queue;

public class StringFinder {

    public static Queue<Range> find(String sentence, String word) {
        Queue<Range> positions = new LinkedList<>();
        int index = sentence.indexOf(word);

        // Continue searching for the word until there are no more matches
        while (index >= 0) {
            int start = index;
            int end = start + word.length(); // Calculate the end position

            // Add the start and end positions to the list
            positions.add(new Range(start, end));

            // Move the index to search for the next occurrence of the word
            index = sentence.indexOf(word, start + 1);
        }

        return positions;
    }
}
