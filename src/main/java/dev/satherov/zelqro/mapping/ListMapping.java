package dev.satherov.zelqro.mapping;

import lombok.experimental.UtilityClass;

import dev.satherov.zelqro.function.IntFunction;

import org.jetbrains.annotations.Unmodifiable;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

@UtilityClass
public class ListMapping {
    
    ///
    /// Maps every element of the given list to a new value using the given mapper function.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value  The list whose elements to map.
    /// @param mapper The mapper function.
    ///
    /// @return A new unmodifiable list holding the mapped elements in encounter order.
    ///
    /// @see #mapEachNonNull(List, Function)
    ///
    @Unmodifiable
    public static <T extends @Nullable Object, R> List<@Nullable R> mapEach(List<T> value, Function<? super T, ? extends @Nullable R> mapper) {
        List<@Nullable R> result = new ArrayList<>(value.size());
        for (T element : value) {
            result.add(mapper.apply(element));
        }
        return Collections.unmodifiableList(result);
    }
    
    ///
    /// Maps every element of the given list to a new value using the given mapper function.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value  The list whose elements to map.
    /// @param mapper The mapper function.
    ///
    /// @return A new unmodifiable list holding the mapped elements in encounter order.
    ///
    /// @throws NullPointerException If the mapper function returns `null` for any element.
    /// @see #mapEach(List, Function)
    ///
    @Unmodifiable
    public static <T extends @Nullable Object, R> List<R> mapEachNonNull(List<T> value, Function<? super T, ? extends R> mapper) {
        List<R> result = new ArrayList<>(value.size());
        for (T element : value) {
            result.add(Objects.requireNonNull(mapper.apply(element), "Mapper function must not return null"));
        }
        return Collections.unmodifiableList(result);
    }
    
    ///
    /// Maps every element of the given list to a new value using the given mapper function.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value   The list whose elements to map.
    /// @param factory The factory for creating the new list
    /// @param mapper  The mapper function.
    ///
    /// @return A new list holding the mapped elements in encounter order.
    ///
    /// @see #mapEachNonNull(List, Function)
    ///
    public static <T extends @Nullable Object, R, C extends List<@Nullable R>> C mapEach(List<T> value, IntFunction<C> factory, Function<? super T, ? extends @Nullable R> mapper) {
        C result = factory.apply(value.size());
        for (T element : value) {
            result.add(mapper.apply(element));
        }
        return result;
    }
    
    ///
    /// Maps every element of the given list to a new value using the given mapper function.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value   The list whose elements to map.
    /// @param factory The factory for creating the new list.
    /// @param mapper  The mapper function.
    ///
    /// @return An unmodifiable list holding the mapped elements in encounter order.
    ///
    /// @throws NullPointerException If the mapper function returns `null` for any element.
    /// @see #mapEach(List, Function)
    ///
    public static <T extends @Nullable Object, R, C extends List<R>> C mapEachNonNull(List<T> value, IntFunction<C> factory, Function<? super T, ? extends R> mapper) {
        C result = factory.apply(value.size());
        for (T element : value) {
            result.add(Objects.requireNonNull(mapper.apply(element), "Mapper function must not return null"));
        }
        return result;
    }
}
