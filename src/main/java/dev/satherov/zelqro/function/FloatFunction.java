package dev.satherov.zelqro.function;

import org.jspecify.annotations.Nullable;

import java.util.function.Function;

///
/// Represents a function that accepts a `float` argument and produces a result.
///
/// @param <R> The type of the result of the function.
///
@FunctionalInterface
public interface FloatFunction<R extends @Nullable Object> {
    
    ///
    /// Applies this function to the given value.
    ///
    /// @param value The function argument.
    ///
    /// @return The function result.
    ///
    R apply(float value);
    
    ///
    /// @return A function that always returns its input argument.
    ///
    static FloatFunction<Float> identity() {
        return value -> value;
    }
    
    ///
    /// Returns a composed function that first applies this function to its input, and then applies the `after` function to the result.
    ///
    /// @param after The function to apply after this function has been applied.
    /// @param <V>   The type of output of the `after` function and of the composed function's return value.
    ///
    /// @return A composed function that first applies this function and then applies the `after` function.
    ///
    default <V> FloatFunction<V> andThen(Function<? super R, ? extends V> after) {
        return value -> after.apply(this.apply(value));
    }
    
    ///
    /// Returns a composed function that first applies the `before` function to its input, and then applies this function to the result.
    ///
    /// @param before The function to apply before this function is applied.
    /// @param <V>    The type of input to the `before` function and to the composed function's supplied value.
    ///
    /// @return A composed function that first applies the `before` function and then applies this function.
    ///
    default <V> Function<V, R> compose(ToFloatFunction<? super V> before) {
        return value -> this.apply(before.applyAsFloat(value));
    }
}
