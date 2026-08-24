package dev.satherov.zelqro.mapping;

import lombok.experimental.UtilityClass;

import dev.satherov.zelqro.function.IntFunction;

import org.jetbrains.annotations.Unmodifiable;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

@UtilityClass
public class MapMapping {
    
    ///
    /// Maps every key of the given map to a new key using the given mapper function.
    ///
    /// Keys that map to the same value collapse into one, keeping the last entry in encounter order.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value  The map whose keys to map.
    /// @param mapper The mapper function.
    ///
    /// @return An unmodifiable map holding the mapped keys.
    ///
    /// @see #mapEachKeyNonNull(Map, Function)
    ///
    @Unmodifiable
    public static <K extends @Nullable Object, V extends @Nullable Object, R> Map<@Nullable R, V> mapEachKey(Map<K, V> value, Function<? super K, ? extends @Nullable R> mapper) {
        Map<@Nullable R, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : value.entrySet()) {
            result.put(mapper.apply(entry.getKey()), entry.getValue());
        }
        return Collections.unmodifiableMap(result);
    }
    
    ///
    /// Maps every key of the given map to a new key using the given mapper function.
    ///
    /// Keys that map to the same value collapse into one, keeping the last entry in encounter order.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value  The map whose keys to map.
    /// @param mapper The mapper function.
    ///
    /// @return An unmodifiable map holding the mapped keys.
    ///
    /// @throws NullPointerException If the mapper function returns `null` for any key.
    /// @see #mapEachKey(Map, Function)
    ///
    @Unmodifiable
    public static <K extends @Nullable Object, V extends @Nullable Object, R> Map<R, V> mapEachKeyNonNull(Map<K, V> value, Function<? super K, ? extends R> mapper) {
        Map<R, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : value.entrySet()) {
            result.put(Objects.requireNonNull(mapper.apply(entry.getKey()), "Mapper function must not return null"), entry.getValue());
        }
        return Collections.unmodifiableMap(result);
    }
    
    ///
    /// Maps every key of the given map to a new key using the given mapper function.
    ///
    /// Keys that map to the same value collapse into one, keeping the last entry in encounter order.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value   The map whose keys to map.
    /// @param factory The factory for creating the new map.
    /// @param mapper  The mapper function.
    ///
    /// @return A new map holding the mapped keys.
    ///
    /// @see #mapEachKeyNonNull(Map, Function)
    ///
    public static <K extends @Nullable Object, V extends @Nullable Object, R, M extends Map<@Nullable R, V>> M mapEachKey(
            Map<K, V> value,
            IntFunction<M> factory,
            Function<? super K, ? extends @Nullable R> mapper
    ) {
        M result = factory.apply(value.size());
        for (Map.Entry<K, V> entry : value.entrySet()) {
            result.put(mapper.apply(entry.getKey()), entry.getValue());
        }
        return result;
    }
    
    ///
    /// Maps every key of the given map to a new key using the given mapper function.
    ///
    /// Keys that map to the same value collapse into one, keeping the last entry in encounter order.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value   The map whose keys to map.
    /// @param factory The factory for creating the new map.
    /// @param mapper  The mapper function.
    ///
    /// @return A new map holding the mapped keys.
    ///
    /// @throws NullPointerException If the mapper function returns `null` for any key.
    /// @see #mapEachKey(Map, Function)
    ///
    public static <K extends @Nullable Object, V extends @Nullable Object, R, M extends Map<R, V>> M mapEachKeyNonNull(
            Map<K, V> value,
            IntFunction<M> factory,
            Function<? super K, ? extends R> mapper
    ) {
        M result = factory.apply(value.size());
        for (Map.Entry<K, V> entry : value.entrySet()) {
            result.put(Objects.requireNonNull(mapper.apply(entry.getKey()), "Mapper function must not return null"), entry.getValue());
        }
        return result;
    }
    
    ///
    /// Maps every value of the given map to a new value using the given mapper function.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value  The map whose values to map.
    /// @param mapper The mapper function.
    ///
    /// @return An unmodifiable map holding the mapped values.
    ///
    /// @see #mapEachValueNonNull(Map, Function)
    ///
    @Unmodifiable
    public static <K extends @Nullable Object, V extends @Nullable Object, R> Map<K, @Nullable R> mapEachValue(Map<K, V> value, Function<? super V, ? extends @Nullable R> mapper) {
        Map<K, @Nullable R> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : value.entrySet()) {
            result.put(entry.getKey(), mapper.apply(entry.getValue()));
        }
        return Collections.unmodifiableMap(result);
    }
    
    ///
    /// Maps every value of the given map to a new value using the given mapper function.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value  The map whose values to map.
    /// @param mapper The mapper function.
    ///
    /// @return An unmodifiable map holding the mapped values.
    ///
    /// @throws NullPointerException If the mapper function returns `null` for any value.
    /// @see #mapEachValue(Map, Function)
    ///
    @Unmodifiable
    public static <K extends @Nullable Object, V extends @Nullable Object, R> Map<K, R> mapEachValueNonNull(Map<K, V> value, Function<? super V, ? extends R> mapper) {
        Map<K, R> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : value.entrySet()) {
            result.put(entry.getKey(), Objects.requireNonNull(mapper.apply(entry.getValue()), "Mapper function must not return null"));
        }
        return Collections.unmodifiableMap(result);
    }
    
    ///
    /// Maps every value of the given map to a new value using the given mapper function.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value   The map whose values to map.
    /// @param factory The factory for creating the new map.
    /// @param mapper  The mapper function.
    ///
    /// @return A new map holding the mapped values.
    ///
    /// @see #mapEachValueNonNull(Map, Function)
    ///
    public static <K extends @Nullable Object, V extends @Nullable Object, R, M extends Map<K, @Nullable R>> M mapEachValue(
            Map<K, V> value,
            IntFunction<M> factory,
            Function<? super V, ? extends @Nullable R> mapper
    ) {
        M result = factory.apply(value.size());
        for (Map.Entry<K, V> entry : value.entrySet()) {
            result.put(entry.getKey(), mapper.apply(entry.getValue()));
        }
        return result;
    }
    
    ///
    /// Maps every value of the given map to a new value using the given mapper function.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value   The map whose values to map.
    /// @param factory The factory for creating the new map.
    /// @param mapper  The mapper function.
    ///
    /// @return A new map holding the mapped values.
    ///
    /// @throws NullPointerException If the mapper function returns `null` for any value.
    /// @see #mapEachValue(Map, Function)
    ///
    public static <K extends @Nullable Object, V extends @Nullable Object, R, M extends Map<K, R>> M mapEachValueNonNull(
            Map<K, V> value,
            IntFunction<M> factory,
            Function<? super V, ? extends R> mapper
    ) {
        M result = factory.apply(value.size());
        for (Map.Entry<K, V> entry : value.entrySet()) {
            result.put(entry.getKey(), Objects.requireNonNull(mapper.apply(entry.getValue()), "Mapper function must not return null"));
        }
        return result;
    }
    
    ///
    /// Maps every entry of the given map to a new value using the given mapper function.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value  The map whose entries to map.
    /// @param mapper The mapper function, taking the key and the value of an entry.
    ///
    /// @return An unmodifiable list holding the mapped entries in encounter order.
    ///
    /// @see #mapEachEntryNonNull(Map, BiFunction)
    ///
    @Unmodifiable
    public static <K extends @Nullable Object, V extends @Nullable Object, R> List<@Nullable R> mapEachEntry(Map<K, V> value, BiFunction<? super K, ? super V, ? extends @Nullable R> mapper) {
        List<@Nullable R> result = new ArrayList<>(value.size());
        for (Map.Entry<K, V> entry : value.entrySet()) {
            result.add(mapper.apply(entry.getKey(), entry.getValue()));
        }
        return Collections.unmodifiableList(result);
    }
    
    ///
    /// Maps every entry of the given map to a new value using the given mapper function.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value  The map whose entries to map.
    /// @param mapper The mapper function, taking the key and the value of an entry.
    ///
    /// @return An unmodifiable list holding the mapped entries in encounter order.
    ///
    /// @throws NullPointerException If the mapper function returns `null` for any entry.
    /// @see #mapEachEntry(Map, BiFunction)
    ///
    @Unmodifiable
    public static <K extends @Nullable Object, V extends @Nullable Object, R> List<R> mapEachEntryNonNull(Map<K, V> value, BiFunction<? super K, ? super V, ? extends R> mapper) {
        List<R> result = new ArrayList<>(value.size());
        for (Map.Entry<K, V> entry : value.entrySet()) {
            result.add(Objects.requireNonNull(mapper.apply(entry.getKey(), entry.getValue()), "Mapper function must not return null"));
        }
        return Collections.unmodifiableList(result);
    }
    
    ///
    /// Maps every entry of the given map to a new value using the given mapper function.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value   The map whose entries to map.
    /// @param factory The factory for creating the new list.
    /// @param mapper  The mapper function, taking the key and the value of an entry.
    ///
    /// @return A new list holding the mapped entries in encounter order.
    ///
    /// @see #mapEachEntryNonNull(Map, BiFunction)
    ///
    public static <K extends @Nullable Object, V extends @Nullable Object, R, C extends List<@Nullable R>> C mapEachEntry(
            Map<K, V> value,
            IntFunction<C> factory,
            BiFunction<? super K, ? super V, ? extends @Nullable R> mapper
    ) {
        C result = factory.apply(value.size());
        for (Map.Entry<K, V> entry : value.entrySet()) {
            result.add(mapper.apply(entry.getKey(), entry.getValue()));
        }
        return result;
    }
    
    ///
    /// Maps every entry of the given map to a new value using the given mapper function.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value   The map whose entries to map.
    /// @param factory The factory for creating the new list.
    /// @param mapper  The mapper function, taking the key and the value of an entry.
    ///
    /// @return A new list holding the mapped entries in encounter order.
    ///
    /// @throws NullPointerException If the mapper function returns `null` for any entry.
    /// @see #mapEachEntry(Map, BiFunction)
    ///
    public static <K extends @Nullable Object, V extends @Nullable Object, R, C extends List<R>> C mapEachEntryNonNull(
            Map<K, V> value,
            IntFunction<C> factory,
            BiFunction<? super K, ? super V, ? extends R> mapper
    ) {
        C result = factory.apply(value.size());
        for (Map.Entry<K, V> entry : value.entrySet()) {
            result.add(Objects.requireNonNull(mapper.apply(entry.getKey(), entry.getValue()), "Mapper function must not return null"));
        }
        return result;
    }
}
