package dev.satherov.zelqro.mapping;

import lombok.experimental.UtilityClass;

import dev.satherov.zelqro.function.IntFunction;

import org.jspecify.annotations.Nullable;

import java.util.Objects;
import java.util.function.Function;

@UtilityClass
public class ArrayMapping {
    
    ///
    /// Maps every element of the given array to a new value using the given mapper function.
    ///
    /// The `factory` builds the result array, which is a fresh array the caller owns.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value   The array whose elements to map.
    /// @param factory The factory of the result array, taking its length.
    /// @param mapper  The mapper function.
    ///
    /// @return A new array holding the mapped elements.
    ///
    /// @see #mapEachNonNull(Object[], IntFunction, Function)
    ///
    public static <T extends @Nullable Object, R> @Nullable R[] mapEach(T[] value, IntFunction<@Nullable R[]> factory, Function<? super T, ? extends @Nullable R> mapper) {
        @Nullable R[] result = factory.apply(value.length);
        for (int i = 0; i < value.length; i++) {
            result[i] = mapper.apply(value[i]);
        }
        return result;
    }
    
    ///
    /// Maps every element of the given array to a new value using the given mapper function.
    ///
    /// The `factory` builds the result array, which is a fresh array the caller owns.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value   The array whose elements to map.
    /// @param factory The factory of the result array, taking its length.
    /// @param mapper  The mapper function.
    ///
    /// @return A new array holding the mapped elements.
    ///
    /// @throws NullPointerException If the mapper function returns `null` for any element.
    /// @see #mapEach(Object[], IntFunction, Function)
    ///
    public static <T, R> R[] mapEachNonNull(T[] value, IntFunction<R[]> factory, Function<? super T, ? extends R> mapper) {
        R[] result = factory.apply(value.length);
        for (int i = 0; i < value.length; i++) {
            result[i] = Objects.requireNonNull(mapper.apply(value[i]), "Mapper function must not return null");
        }
        return result;
    }
}
