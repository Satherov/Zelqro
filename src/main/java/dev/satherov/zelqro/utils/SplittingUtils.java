package dev.satherov.zelqro.utils;

import lombok.experimental.UtilityClass;

import dev.satherov.zelqro.predicate.CharBiPredicate;
import dev.satherov.zelqro.predicate.CharPredicate;

import org.jetbrains.annotations.Range;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

///
/// Splits strings on delimiter characters and at boundaries between neighboring characters.
///
/// A delimiter split drops the delimiters together with every empty segment they would produce, so repeated,
/// leading and trailing delimiters collapse away. A boundary split keeps every character and only decides
/// where one segment ends and the next one begins.
///
@UtilityClass
public class SplittingUtils {
    
    private static final String[] EMPTY = new String[0];
    
    ///
    /// Splits the given string on every occurrence of the given delimiter.
    ///
    /// ```
    /// SplittingUtils.on("a,b,,c", ',') -> ["a", "b", "c"]
    /// ```
    ///
    /// @param value     The string to split.
    /// @param delimiter The character to split on.
    ///
    /// @return The segments between the delimiters.
    ///
    /// @see #on(String, String)
    /// @see #on(String, CharPredicate)
    /// @see #onAny(String, char[])
    ///
    public static String[] on(String value, char delimiter) {
        return SplittingUtils.on(value, character -> character == delimiter);
    }
    
    ///
    /// Splits the given string on every occurrence of the given delimiter.
    /// The delimiter is matched literally, it is not a regular expression, use [String#split(String)] for that instead
    ///
    /// ```
    /// SplittingUtils.on("a::b::c", "::") -> ["a", "b", "c"]
    /// ```
    ///
    /// @param value     The string to split.
    /// @param delimiter The sequence to split on.
    ///
    /// @return The segments between the delimiters.
    ///
    /// @throws IllegalArgumentException When the delimiter is empty.
    ///
    /// @see #on(String, char)
    /// @see #on(String, CharPredicate)
    /// @see #onAny(String, char[])
    ///
    public static String[] on(String value, String delimiter) {
        if (delimiter.isEmpty()) throw new IllegalArgumentException("Delimiter must not be empty");
        
        List<String> parts = new ArrayList<>();
        int start = 0;
        for (int match = value.indexOf(delimiter); match >= 0; match = value.indexOf(delimiter, start)) {
            if (match > start) {
                parts.add(value.substring(start, match));
            }
            
            start = match + delimiter.length();
        }
        
        if (start < value.length()) {
            parts.add(value.substring(start));
        }
        
        return parts.toArray(String[]::new);
    }
    
    ///
    /// Splits the given string on every character that matches the given predicate.
    ///
    /// ```
    /// SplittingUtils.on("a1b22c", Character::isDigit) -> ["a", "b", "c"]
    /// ```
    ///
    /// @param value     The string to split.
    /// @param delimiter The predicate that decides which characters split the string.
    ///
    /// @return The segments between the delimiters.
    ///
    /// @see #on(String, char)
    /// @see #on(String, String)
    /// @see #onAny(String, char[])
    ///
    public static String[] on(String value, CharPredicate delimiter) {
        List<String> parts = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < value.length(); i++) {
            if (!delimiter.test(value.charAt(i))) continue;
            
            if (i > start) {
                parts.add(value.substring(start, i));
            }
            
            start = i + 1;
        }
        
        if (start < value.length()) {
            parts.add(value.substring(start));
        }
        
        return parts.toArray(String[]::new);
    }
    
    ///
    /// Splits the given string on every occurrence of any of the given delimiters.
    ///
    /// ```
    /// SplittingUtils.onAny("a,b;c d", ',', ';', ' ') -> ["a", "b", "c", "d"]
    /// ```
    ///
    /// @param value      The string to split.
    /// @param delimiters The characters to split on.
    ///
    /// @return The segments between the delimiters.
    ///
    /// @see #on(String, char)
    /// @see #on(String, String)
    /// @see #on(String, CharPredicate)
    ///
    public static String[] onAny(String value, char... delimiters) {
        String set = String.valueOf(delimiters);
        return SplittingUtils.on(value, character -> set.indexOf(character) >= 0);
    }
    
    ///
    /// Splits the given string on every run of whitespace.
    ///
    /// ```
    /// SplittingUtils.onWhitespace("  this is\ta sentence ") -> ["this", "is", "a", "sentence"]
    /// ```
    ///
    /// @param value The string to split.
    ///
    /// @return The words of the string.
    ///
    public static String[] onWhitespace(String value) {
        return SplittingUtils.on(value, Character::isWhitespace);
    }
    
    ///
    /// Splits the given string into its lines, recognizing `\n`, `\r` and `\r\n` as terminators.
    /// Empty lines are kept, unlike with the delimiter splits.
    ///
    /// @param value The string to split.
    ///
    /// @return The lines of the string.
    ///
    public static String[] onLines(String value) {
        return value.lines().toArray(String[]::new);
    }
    
    ///
    /// Splits the given string into chunks of the given length, the last one holding whatever is left over.
    ///
    /// ```
    /// SplittingUtils.onLength("abcdefg", 3) -> ["abc", "def", "g"]
    /// ```
    ///
    /// @param value  The string to split.
    /// @param length The length of every chunk but the last.
    ///
    /// @return The chunks of the string.
    ///
    /// @throws IllegalArgumentException When the length is smaller than one.
    ///
    public static String[] onLength(String value, @Range(from = 1, to = Integer.MAX_VALUE) int length) {
        String[] chunks = new String[(value.length() + length - 1) / length];
        for (int i = 0; i < chunks.length; i++) {
            chunks[i] = value.substring(i * length, Math.min((i + 1) * length, value.length()));
        }
        return chunks;
    }
    
    ///
    /// Splits the given string wherever the predicate matches a pair of neighboring characters.
    /// The break falls between the two, no character is dropped.
    ///
    /// ```
    /// SplittingUtils.atBoundary("aabba", (previous, current) -> previous != current) -> ["aa", "bb", "a"]
    /// ```
    ///
    /// @param value    The string to split.
    /// @param boundary The predicate that decides whether two neighboring characters belong to different segments.
    ///
    /// @return The segments of the string.
    ///
    /// @see #atCaseBoundary(String)
    /// @see #atDigitBoundary(String)
    ///
    public static String[] atBoundary(String value, CharBiPredicate boundary) {
        if (value.isEmpty()) return SplittingUtils.EMPTY;
        
        List<String> parts = new ArrayList<>();
        int start = 0;
        for (int i = 1; i < value.length(); i++) {
            if (!boundary.test(value.charAt(i - 1), value.charAt(i))) continue;
            
            parts.add(value.substring(start, i));
            start = i;
        }
        
        parts.add(value.substring(start));
        return parts.toArray(String[]::new);
    }
    
    ///
    /// Splits the given string at every boundary between a lowercase and an uppercase character.
    /// A run of uppercase characters stays together, the break falls before its last character when a
    /// lowercase one follows.
    ///
    /// ```
    /// SplittingUtils.atCaseBoundary("helloWorld") -> ["hello", "World"]
    /// SplittingUtils.atCaseBoundary("HTTPServer") -> ["HTTP", "Server"]
    /// ```
    ///
    /// @param value The string to split.
    ///
    /// @return The segments of the string.
    ///
    /// @see #atBoundary(String, CharBiPredicate)
    /// @see #atDigitBoundary(String)
    ///
    public static String[] atCaseBoundary(String value) {
        if (value.isEmpty()) return SplittingUtils.EMPTY;
        
        List<String> parts = new ArrayList<>();
        int start = 0;
        for (int i = 1; i < value.length(); i++) {
            char previous = value.charAt(i - 1);
            char current = value.charAt(i);
            boolean hump = Character.isLowerCase(previous) && Character.isUpperCase(current);
            boolean acronym = Character.isUpperCase(previous) && Character.isUpperCase(current) && i + 1 < value.length() && Character.isLowerCase(value.charAt(i + 1));
            
            if (!hump && !acronym) continue;
            
            parts.add(value.substring(start, i));
            start = i;
        }
        
        parts.add(value.substring(start));
        return parts.toArray(String[]::new);
    }
    
    ///
    /// Splits the given string at every boundary between a digit and a character that is not one.
    ///
    /// ```
    /// SplittingUtils.atDigitBoundary("abc123def") -> ["abc", "123", "def"]
    /// ```
    ///
    /// @param value The string to split.
    ///
    /// @return The segments of the string.
    ///
    /// @see #atBoundary(String, CharBiPredicate)
    /// @see #atCaseBoundary(String)
    ///
    public static String[] atDigitBoundary(String value) {
        return SplittingUtils.atBoundary(value, (prev, cur) -> Character.isDigit(prev) != Character.isDigit(cur));
    }
    
    ///
    /// Splits the given string into the words it is built from, whichever convention wrote it.
    /// Everything that is not a letter or a digit separates two words, and so does a case or a digit boundary.
    ///
    /// ```
    /// SplittingUtils.intoWords("parseHTTPResponse_v2") -> ["parse", "HTTP", "Response", "v", "2"]
    /// ```
    ///
    /// @param value The string to split.
    ///
    /// @return The words of the string.
    ///
    /// @see StringUtils
    ///
    public static String[] intoWords(String value) {
        return Arrays.stream(SplittingUtils.on(value, character -> !Character.isLetterOrDigit(character)))
                .flatMap(part -> Arrays.stream(SplittingUtils.atDigitBoundary(part)))
                .flatMap(part -> Arrays.stream(SplittingUtils.atCaseBoundary(part)))
                .toArray(String[]::new);
    }
}
