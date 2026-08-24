package dev.satherov.zelqro.utils;

import lombok.experimental.UtilityClass;

import org.intellij.lang.annotations.PrintFormat;
import org.jspecify.annotations.Nullable;

import java.util.Locale;
import java.util.Objects;

@UtilityClass
public class StringUtils {
    
    ///
    /// Formats the given string to uppercase with [Locale#ROOT] applied.
    ///
    /// @param value The string to format.
    ///
    /// @return The formatted string.
    ///
    public static String upper(String value) {
        return value.toUpperCase(Locale.ROOT);
    }
    
    ///
    /// Formats the given string to lowercase with [Locale#ROOT] applied.
    ///
    /// @param value The string to format.
    ///
    /// @return The formatted string.
    ///
    public static String lower(String value) {
        return value.toLowerCase(Locale.ROOT);
    }
    
    ///
    /// Formats the given string using the given arguments with [Locale#ROOT] applied.
    ///
    /// @param value The string to format.
    /// @param args  The arguments to format the string with.
    ///
    /// @return The formatted string.
    ///
    public static String format(@PrintFormat String value, Object... args) {
        return String.format(Locale.ROOT, value, args);
    }
    
    ///
    /// Capitalizes the given string by capitalizing the first letter and lowercasing the rest.
    ///
    /// @param value The string to capitalize.
    ///
    /// @return The capitalized string.
    ///
    public static String capitalize(String value) {
        return StringUtils.upper(value.substring(0, 1)) + StringUtils.lower(value.substring(1));
    }
    
    ///
    /// Checks if the given string is `null`, empty all whitespaces.
    ///
    /// @param value The string to check.
    ///
    /// @return `true` if the string is `null`, empty or all whitespaces, `false` otherwise.
    ///
    public static boolean isNullOrBlank(@Nullable String value) {
        return value == null || value.isBlank();
    }
    
    ///
    /// Converts the given sentence to one camel-case string.
    /// Splits words by spaces only.
    ///
    /// ```
    /// StringUtils.toCamelCase("This is a sentence") -> "thisIsASentence"
    /// ```
    ///
    /// @param sentence The sentence to convert.
    ///
    /// @return The camel-case string.
    ///
    /// @see #toCamelCase(String...)
    ///
    public static String toCamelCase(String sentence) {
        String[] words = sentence.split("\\s+");
        return StringUtils.toCamelCase(words);
    }
    
    ///
    /// Converts the given array of words to one camel-case string.
    ///
    /// ```
    /// StringUtils.toCamelCase("This", "is", "a", "sentence") -> "thisIsASentence"
    /// ```
    ///
    /// @param words The words to convert.
    ///
    /// @return The camel-case string.
    ///
    /// @see #toCamelCase(String)
    ///
    public static String toCamelCase(String... words) {
        StringBuilder builder = new StringBuilder(StringUtils.lower(words[0].trim()));
        for (int i = 1; i < words.length; i++) {
            builder.append(StringUtils.capitalize(words[i].trim()));
        }
        return builder.toString();
    }
    
    ///
    /// Converts the given sentence to one pascal-case string.
    /// Splits words by spaces only.
    ///
    /// ```
    /// StringUtils.toPascalCase("This is a sentence") -> "ThisIsASentence"
    /// ```
    ///
    /// @param sentence The sentence to convert.
    ///
    /// @return The pascal-case string.
    ///
    /// @see #toPascalCase(String...)
    ///
    public static String toPascalCase(String sentence) {
        String[] words = sentence.split("\\s+");
        return StringUtils.toPascalCase(words);
    }
    
    ///
    /// Converts the given array of words to one pascal-case string.
    ///
    /// ```
    /// StringUtils.toPascalCase("This", "is", "a", "sentence") -> "ThisIsASentence"
    /// ```
    ///
    /// @param words The words to convert.
    ///
    /// @return The pascal-case string.
    ///
    /// @see #toPascalCase(String)
    ///
    public static String toPascalCase(String... words) {
        StringBuilder builder = new StringBuilder();
        for (String word : words) {
            builder.append(StringUtils.capitalize(word.trim()));
        }
        return builder.toString();
    }
    
    ///
    /// Converts the given sentence to one snake-case string.
    /// Splits words by spaces only.
    ///
    /// ```
    /// StringUtils.toSnakeCase("This is a sentence") -> "this_is_a_sentence"
    /// ```
    ///
    /// @param sentence The sentence to convert.
    ///
    /// @return The snake-case string.
    ///
    /// @see #toSnakeCase(String...)
    ///
    public static String toSnakeCase(String sentence) {
        String[] words = sentence.split("\\s+");
        return StringUtils.toSnakeCase(words);
    }
    
    ///
    /// Converts the given array of words to one snake-case string.
    ///
    /// ```
    /// StringUtils.toSnakeCase("This", "is", "a", "sentence") -> "this_is_a_sentence"
    /// ```
    ///
    /// @param words The words to convert.
    ///
    /// @return The snake-case string.
    ///
    /// @see #toSnakeCase(String)
    ///
    public static String toSnakeCase(String... words) {
        StringBuilder builder = new StringBuilder();
        for (String word : words) {
            builder.append(StringUtils.lower(word.trim())).append("_");
        }
        return builder.toString();
    }
    
    ///
    /// Converts the given sentence to one kebab-case string.
    /// Splits words by spaces only.
    ///
    /// ```
    /// StringUtils.toKebabCase("This is a sentence") -> "this-is-a-sentence"
    /// ```
    ///
    /// @param sentence The sentence to convert.
    ///
    /// @return The kebab-case string.
    ///
    /// @see #toKebabCase(String...)
    ///
    public static String toKebabCase(String sentence) {
        String[] words = sentence.split("\\s+");
        return StringUtils.toKebabCase(words);
    }
    
    ///
    /// Converts the given sentence to one kebab-case string.
    ///
    /// ```
    /// StringUtils.toKebabCase("This is a sentence") -> "this-is-a-sentence"
    /// ```
    ///
    /// @param words The words to convert.
    ///
    /// @return The kebab-case string.
    ///
    /// @see #toKebabCase(String)
    ///
    public static String toKebabCase(String... words) {
        StringBuilder builder = new StringBuilder();
        for (String word : words) {
            builder.append(StringUtils.lower(word.trim())).append("-");
        }
        return builder.toString();
    }
    
    ///
    /// Converts the given sentence to one train-case string.
    /// Splits words by spaces only.
    ///
    /// ```
    /// StringUtils.toTrainCase("This is a sentence") -> "This-Is-A-Sentence"
    /// ```
    ///
    /// @param sentence The sentence to convert.
    ///
    /// @return The train-case string.
    ///
    /// @see #toTrainCase(String...)
    ///
    public static String toTrainCase(String sentence) {
        String[] words = sentence.split("\\s+");
        return StringUtils.toTrainCase(words);
    }
    
    ///
    /// Converts the given sentence to one train-case string.
    ///
    /// ```
    /// StringUtils.toTrainCase("This is a sentence") -> "This-Is-A-Sentence"
    /// ```
    ///
    /// @param words The words to convert.
    ///
    /// @return The train-case string.
    ///
    /// @see #toTrainCase(String)
    ///
    public static String toTrainCase(String... words) {
        StringBuilder builder = new StringBuilder();
        for (String word : words) {
            builder.append(StringUtils.capitalize(word.trim())).append("-");
        }
        return builder.toString();
    }
    
    ///
    /// Converts the given sentence to one title-case string.
    /// Splits words by spaces only.
    ///
    /// ```
    /// StringUtils.toTitleCase("This is a sentence") -> "This Is A Sentence"
    /// ```
    ///
    /// @param sentence The sentence to convert.
    ///
    /// @return The title-case string.
    ///
    /// @see #toTitleCase(String...)
    ///
    public static String toTitleCase(String sentence) {
        String[] words = sentence.split("\\s+");
        return StringUtils.toTitleCase(words);
    }
    
    ///
    /// Converts the given sentence to one title-case string.
    ///
    /// ```
    /// StringUtils.toTitleCase("This is a sentence") -> "This Is A Sentence"
    /// ```
    ///
    /// @param words The words to convert.
    ///
    /// @return The title-case string.
    ///
    /// @see #toTitleCase(String)
    ///
    public static String toTitleCase(String... words) {
        StringBuilder builder = new StringBuilder();
        for (String word : words) {
            builder.append(StringUtils.capitalize(word.trim())).append(" ");
        }
        return builder.toString();
    }
    
    ///
    /// Converts the given sentence to one sentence-case string.
    ///
    /// ```
    /// StringUtils.toSentenceCase("This is a sentence") -> "This is a sentence"
    /// ```
    ///
    /// @param sentence The sentence to convert.
    ///
    /// @return The sentence-case string.
    ///
    /// @see #toSentenceCase(String...)
    ///
    public static String toSentenceCase(String sentence) {
        String[] words = sentence.split("\\s+");
        return StringUtils.toSentenceCase(words);
    }
    
    ///
    /// Converts the given sentence to one sentence-case string.
    ///
    /// ```
    /// StringUtils.toSentenceCase("This", "is", "a", "sentence") -> "This is a sentence"
    /// ```
    ///
    /// @param words The words to convert.
    ///
    /// @return The sentence-case string.
    ///
    /// @see #toSentenceCase(String)
    ///
    public static String toSentenceCase(String... words) {
        StringBuilder builder = new StringBuilder(StringUtils.lower(words[0].trim()));
        for (int i = 1; i < words.length; i++) {
            builder.append(StringUtils.lower(words[i].trim()));
        }
        return builder.toString();
    }
}
