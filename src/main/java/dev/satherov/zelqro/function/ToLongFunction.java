package dev.satherov.zelqro.function;

///
/// Represents a function that accepts one argument and produces a `long` result.
///
/// @param <T> The type of the input to the function.
///
@FunctionalInterface
public interface ToLongFunction<T> {
    
    ///
    /// Applies this function to the given value.
    ///
    /// @param value The function argument.
    ///
    /// @return The function result.
    ///
    long applyAsLong(T value);
}
