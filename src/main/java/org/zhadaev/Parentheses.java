package org.zhadaev;

import java.util.HashSet;
import java.util.Optional;
import java.util.PrimitiveIterator;
import java.util.Set;
import java.util.stream.IntStream;

public class Parentheses {

    public static void main(String... args) {
        if (args.length == 0) {
            System.out.println(0);
            return;
        }

        String inputStr = args[0];
        StringBuilder stringBuilder = new StringBuilder();
        Set<Integer> indexesOfClosingParentheses = new HashSet<>();
        IntStream.range(0, inputStr.length()).forEach(i -> {
            char ch = inputStr.charAt(i);
            if (ch == '(') {
                Optional<Integer> closingParenthesisIndex = getClosingParenthesisIndex(inputStr, i);
                if (closingParenthesisIndex.isPresent()) {
                    indexesOfClosingParentheses.add(closingParenthesisIndex.get());
                    stringBuilder.append(ch);
                }
            } else if (ch == ')' && indexesOfClosingParentheses.contains(i)) {
                stringBuilder.append(ch);
            }
        });

        int parenthesesCount = stringBuilder.length();
        String result = Integer.toString(parenthesesCount);
        if (parenthesesCount > 0) {
            result += " - " + stringBuilder.toString();
        }
        System.out.println(result);
    }

    private static Optional<Integer> getClosingParenthesisIndex(String inputStr, int charIndex) {
        int openingLevel = 0;
        PrimitiveIterator.OfInt iterator = IntStream.range(charIndex + 1, inputStr.length()).iterator();
        while (iterator.hasNext()) {
            int index = iterator.next();
            if (inputStr.charAt(index) == ')') {
                if (openingLevel == 0) {
                    return Optional.of(index);
                } else {
                    openingLevel--;
                }
            } else {
                openingLevel++;
            }
        }

        return Optional.empty();
    }
}
