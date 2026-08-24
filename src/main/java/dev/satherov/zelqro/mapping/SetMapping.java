package dev.satherov.zelqro.mapping;

import lombok.experimental.UtilityClass;

import dev.satherov.zelqro.function.IntFunction;

import org.jetbrains.annotations.Unmodifiable;
import org.jspecify.annotations.Nullable;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

@UtilityClass
public class SetMapping {
    
    ///
    /// Maps every element of the given set to a new value using the given mapper function.
    ///
    /// Elements that map to the same value collapse into one, so the result may be smaller than the `value`.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value  The set whose elements to map.
    /// @param mapper The mapper function.
    ///
    /// @return A new unmodifiable set holding the mapped elements in encounter order.
    ///
    /// @see #mapEachNonNull(Set, Function)
    ///
    @Unmodifiable
    public static <T extends @Nullable Object, R> Set<@Nullable R> mapEach(Set<T> value, Function<? super T, ? extends @Nullable R> mapper) {
        Set<@Nullable R> result = new LinkedHashSet<>();
        for (T element : value) {
            result.add(mapper.apply(element));
        }
        return Collections.unmodifiableSet(result);
    }
    
    ///
    /// Maps every element of the given set to a new value using the given mapper function.
    ///
    /// Elements that map to the same value collapse into one, so the result may be smaller than the `value`.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value  The set whose elements to map.
    /// @param mapper The mapper function.
    ///
    /// @return A new unmodifiable set holding the mapped elements in encounter order.
    ///
    /// @throws NullPointerException If the mapper function returns `null` for any element.
    /// @see #mapEach(Set, Function)
    ///
    @Unmodifiable
    public static <T extends @Nullable Object, R> Set<R> mapEachNonNull(Set<T> value, Function<? super T, ? extends R> mapper) {
        Set<R> result = new LinkedHashSet<>();
        for (T element : value) {
            result.add(Objects.requireNonNull(mapper.apply(element), "Mapper function must not return null"));
        }
        return Collections.unmodifiableSet(result);
    }
    
    ///
    /// Maps every element of the given set to a new value using the given mapper function.
    ///
    /// Elements that map to the same value collapse into one, so the result may be smaller than the `value`.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value   The set whose elements to map.
    /// @param factory The factory for creating the new set.
    /// @param mapper  The mapper function.
    ///
    /// @return A new set holding the mapped elements in encounter order.
    ///
    /// @see #mapEachNonNull(Set, Function)
    ///
    public static <T extends @Nullable Object, R, C extends Set<@Nullable R>> C mapEach(Set<T> value, IntFunction<C> factory, Function<? super T, ? extends @Nullable R> mapper) {
        C result = factory.apply(value.size());
        for (T element : value) {
            result.add(mapper.apply(element));
        }
        return result;
    }
    
    ///
    /// Maps every element of the given set to a new value using the given mapper function.
    ///
    /// Elements that map to the same value collapse into one, so the result may be smaller than the `value`.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value   The set whose elements to map.
    /// @param factory The factory for creating the new set.
    /// @param mapper  The mapper function.
    ///
    /// @return A new set holding the mapped elements in encounter order.
    ///
    /// @throws NullPointerException If the mapper function returns `null` for any element.
    /// @see #mapEach(Set, Function)
    ///
    public static <T extends @Nullable Object, R, C extends Set<R>> C mapEachNonNull(Set<T> value, IntFunction<C> factory, Function<? super T, ? extends R> mapper) {
        C result = factory.apply(value.size());
        for (T element : value) {
            result.add(Objects.requireNonNull(mapper.apply(element), "Mapper function must not return null"));
        }
        return result;
    }
}
