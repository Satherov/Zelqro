package dev.satherov.zelqro.function;

///
/// Represents a function that accepts one argument and produces a `double` result.
///
/// @param <T> The type of the input to the function.
///
@FunctionalInterface
public interface ToDoubleFunction<T> {
    
    ///
    /// Applies this function to the given value.
    ///
    /// @param value The function argument.
    ///
    /// @return The function result.
    ///
    double applyAsDouble(T value);
}
