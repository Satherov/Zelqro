package dev.satherov.zelqro.mapping;

import lombok.experimental.UtilityClass;

import dev.satherov.zelqro.function.ToBooleanFunction;
import dev.satherov.zelqro.function.ToByteFunction;
import dev.satherov.zelqro.function.ToCharFunction;
import dev.satherov.zelqro.function.ToDoubleFunction;
import dev.satherov.zelqro.function.ToFloatFunction;
import dev.satherov.zelqro.function.ToIntFunction;
import dev.satherov.zelqro.function.ToLongFunction;
import dev.satherov.zelqro.function.ToShortFunction;

import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.Nullable;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@UtilityClass
public class ObjectMapping {
    
    // ========== DEFAULT ==========
    
    ///
    /// Maps the given value to a new value using the given mapper function.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value  The value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is `null`.
    ///
    /// @see #mapNonNull(Object, Function)
    /// @see #mapOrElse(Object, Function, Object)
    /// @see #mapNonNullOrElse(Object, Function, Object)
    /// @see #mapOrElseGet(Object, Function, Supplier)
    /// @see #mapNonNullOrElseGet(Object, Function, Supplier)
    ///
    @Contract("null, _ -> null")
    public static <T, R> @Nullable R map(@Nullable T value, Function<? super T, ? extends @Nullable R> mapper) {
        return value == null ? null : mapper.apply(value);
    }
    
    ///
    /// Maps the given value to a new value using the given mapper function.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value  The value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is `null`.
    ///
    /// @throws NullPointerException If the mapper function returns `null`.
    /// @see #map(Object, Function)
    /// @see #mapOrElse(Object, Function, Object)
    /// @see #mapNonNullOrElse(Object, Function, Object)
    /// @see #mapOrElseGet(Object, Function, Supplier)
    /// @see #mapNonNullOrElseGet(Object, Function, Supplier)
    ///
    @Contract("null, _ -> null; !null, _ -> !null")
    public static <T, R> @Nullable R mapNonNull(@Nullable T value, Function<? super T, ? extends R> mapper) {
        return value == null ? null : Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null");
    }
    
    ///
    /// Maps the given value to a new value using the given mapper function.
    /// If the `value` is `null`, the `orElse` value is returned.
    ///
    /// The mapper function and the `orElse` value may be `null`.
    ///
    /// @param value  The value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is `null`.
    ///
    /// @return The mapped value, or `orElse` if the `value` is `null`.
    ///
    /// @see #map(Object, Function)
    /// @see #mapNonNull(Object, Function)
    /// @see #mapNonNullOrElse(Object, Function, Object)
    /// @see #mapOrElseGet(Object, Function, Supplier)
    /// @see #mapNonNullOrElseGet(Object, Function, Supplier)
    ///
    @Contract("null, _, _ -> param3")
    public static <T, R> @Nullable R mapOrElse(@Nullable T value, Function<? super T, ? extends @Nullable R> mapper, @Nullable R orElse) {
        return value == null ? orElse : mapper.apply(value);
    }
    
    ///
    /// Maps the given value to a new value using the given mapper function.
    /// If the `value` is `null`, the `orElse` value is returned.
    ///
    /// Both the mapper function and the `orElse` value must not return `null`.
    ///
    /// @param value  The value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is `null`.
    ///
    /// @return The mapped value, or `orElse` if the `value` is `null`.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` value is `null`.
    /// @see #map(Object, Function)
    /// @see #mapNonNull(Object, Function)
    /// @see #mapOrElse(Object, Function, Object)
    /// @see #mapOrElseGet(Object, Function, Supplier)
    /// @see #mapNonNullOrElseGet(Object, Function, Supplier)
    ///
    @Contract("null, _, _ -> param3; !null, _, _ -> !null")
    public static <T, R> R mapNonNullOrElse(@Nullable T value, Function<? super T, ? extends R> mapper, R orElse) {
        return value == null ?
                Objects.requireNonNull(orElse) :
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null");
    }
    
    ///
    /// Maps the given value to a new value using the given mapper function.
    /// If the `value` is `null`, the `orElse` supplier's value is returned.
    ///
    /// The mapper function and the `orElse` supplier may be `null`.
    ///
    /// @param value  The value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is `null`.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is `null`.
    ///
    /// @see #map(Object, Function)
    /// @see #mapNonNull(Object, Function)
    /// @see #mapOrElse(Object, Function, Object)
    /// @see #mapNonNullOrElse(Object, Function, Object)
    /// @see #mapNonNullOrElseGet(Object, Function, Supplier)
    ///
    public static <T, R> @Nullable R mapOrElseGet(@Nullable T value, Function<? super T, ? extends @Nullable R> mapper, Supplier<@Nullable R> orElse) {
        return value == null ? orElse.get() : mapper.apply(value);
    }
    
    ///
    /// Maps the given value to a new value using the given mapper function.
    /// If the `value` is `null`, the `orElse` supplier's value is returned.
    ///
    /// Both the mapper function and the `orElse` supplier must not return `null`.
    ///
    /// @param value  The value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is `null`.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is `null`.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` supplier returns `null`.
    /// @see #map(Object, Function)
    /// @see #mapNonNull(Object, Function)
    /// @see #mapOrElse(Object, Function, Object)
    /// @see #mapNonNullOrElse(Object, Function, Object)
    /// @see #mapOrElseGet(Object, Function, Supplier)
    ///
    @Contract("!null, _, _ -> !null")
    public static <T, R> R mapNonNullOrElseGet(@Nullable T value, Function<? super T, ? extends R> mapper, Supplier<R> orElse) {
        return value == null ?
                Objects.requireNonNull(orElse.get()) :
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null");
    }
    
    // ========== THROW ==========
    
    ///
    /// Maps the given value to a new value using the given mapper function.
    /// If the `value` is `null`, the `exception` is thrown.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value     The value to map.
    /// @param mapper    The mapper function.
    /// @param exception The exception to throw if the `value` is `null`.
    ///
    /// @return The mapped value.
    ///
    /// @throws X If the `value` is `null`.
    /// @see #mapNonNullOrThrow(Object, Function, Supplier)
    ///
    @Contract("null, _, _ -> fail")
    public static <T, R, X extends Throwable> @Nullable R mapOrThrow(@Nullable T value, Function<? super T, ? extends @Nullable R> mapper, Supplier<X> exception) throws X {
        if (value == null) throw exception.get();
        return mapper.apply(value);
    }
    
    ///
    /// Maps the given value to a new value using the given mapper function.
    /// If the `value` is `null`, the `exception` is thrown.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value     The value to map.
    /// @param mapper    The mapper function.
    /// @param exception The exception to throw if the `value` is `null`.
    ///
    /// @return The mapped value.
    ///
    /// @throws X                    If the `value` is `null`.
    /// @throws NullPointerException If the mapper function returns `null`.
    /// @see #mapOrThrow(Object, Function, Supplier)
    ///
    @Contract("null, _, _ -> fail; !null, _, _ -> !null")
    public static <T, R, X extends Throwable> R mapNonNullOrThrow(@Nullable T value, Function<? super T, ? extends R> mapper, Supplier<X> exception) throws X {
        if (value == null) throw exception.get();
        return Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null");
    }
    
    // ========== PREDICATE ==========
    
    ///
    /// Maps the given value to a new value using the given mapper function if the value is not `null` and the predicate returns `true`.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value     The value to map.
    /// @param predicate The predicate to check against.
    /// @param mapper    The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is `null` or the predicate returns `false`.
    ///
    /// @see #mapNonNullIf(Object, Predicate, Function)
    /// @see #mapIfOrElse(Object, Predicate, Function, Object)
    /// @see #mapNonNullIfOrElse(Object, Predicate, Function, Object)
    /// @see #mapIfOrElseGet(Object, Predicate, Function, Supplier)
    /// @see #mapNonNullIfOrElseGet(Object, Predicate, Function, Supplier)
    ///
    public static <T, R> @Nullable R mapIf(@Nullable T value, Predicate<? super T> predicate, Function<? super T, ? extends @Nullable R> mapper) {
        return (value != null && predicate.test(value)) ? mapper.apply(value) : null;
    }
    
    ///
    /// Maps the given value to a new value using the given mapper function if the value is not `null` and the predicate returns `true`.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value     The value to map.
    /// @param predicate The predicate to check against.
    /// @param mapper    The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is `null` or the predicate returns `false`.
    ///
    /// @throws NullPointerException If the mapper function returns `null`.
    /// @see #mapIf(Object, Predicate, Function)
    /// @see #mapIfOrElse(Object, Predicate, Function, Object)
    /// @see #mapNonNullIfOrElse(Object, Predicate, Function, Object)
    /// @see #mapIfOrElseGet(Object, Predicate, Function, Supplier)
    /// @see #mapNonNullIfOrElseGet(Object, Predicate, Function, Supplier)
    ///
    public static <T, R> @Nullable R mapNonNullIf(@Nullable T value, Predicate<? super T> predicate, Function<? super T, ? extends R> mapper) {
        return (value != null && predicate.test(value)) ? Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") : null;
    }
    
    ///
    /// Maps the given value to a new value using the given mapper function if the value is not `null` and the predicate returns `true`.
    /// If the `value` is `null` or the predicate returns `false`, the `orElse` value is returned.
    ///
    /// The mapper function and the `orElse` value may be `null`.
    ///
    /// @param value     The value to map.
    /// @param predicate The predicate to check against.
    /// @param mapper    The mapper function.
    /// @param orElse    The value to return if the `value` is `null` or the predicate returns `false`.
    ///
    /// @return The mapped value, or `orElse` if the `value` is `null` or the predicate returns `false`.
    ///
    /// @see #mapIf(Object, Predicate, Function)
    /// @see #mapNonNullIf(Object, Predicate, Function)
    /// @see #mapNonNullIfOrElse(Object, Predicate, Function, Object)
    /// @see #mapIfOrElseGet(Object, Predicate, Function, Supplier)
    /// @see #mapNonNullIfOrElseGet(Object, Predicate, Function, Supplier)
    ///
    public static <T, R> @Nullable R mapIfOrElse(@Nullable T value, Predicate<? super T> predicate, Function<? super T, ? extends @Nullable R> mapper, @Nullable R orElse) {
        return (value != null && predicate.test(value)) ? mapper.apply(value) : orElse;
    }
    
    ///
    /// Maps the given value to a new value using the given mapper function if the value is not `null` and the predicate returns `true`.
    /// If the `value` is `null` or the predicate returns `false`, the `orElse` value is returned.
    ///
    /// Both the mapper function and the `orElse` value must not return `null`.
    ///
    /// @param value     The value to map.
    /// @param predicate The predicate to check against.
    /// @param mapper    The mapper function.
    /// @param orElse    The value to return if the `value` is `null` or the predicate returns `false`.
    ///
    /// @return The mapped value, or `orElse` if the `value` is `null` or the predicate returns `false`.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` value is `null`.
    /// @see #mapIf(Object, Predicate, Function)
    /// @see #mapNonNullIf(Object, Predicate, Function)
    /// @see #mapIfOrElse(Object, Predicate, Function, Object)
    /// @see #mapIfOrElseGet(Object, Predicate, Function, Supplier)
    /// @see #mapNonNullIfOrElseGet(Object, Predicate, Function, Supplier)
    ///
    public static <T, R> R mapNonNullIfOrElse(@Nullable T value, Predicate<? super T> predicate, Function<? super T, ? extends R> mapper, R orElse) {
        return (value != null && predicate.test(value)) ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse);
    }
    
    ///
    /// Maps the given value to a new value using the given mapper function if the value is not `null` and the predicate returns `true`.
    /// If the `value` is `null` or the predicate returns `false`, the `orElse` supplier's value is returned.
    ///
    /// The mapper function and the `orElse` supplier may be `null`.
    ///
    /// @param value     The value to map.
    /// @param predicate The predicate to check against.
    /// @param mapper    The mapper function.
    /// @param orElse    The supplier of the value to return if the `value` is `null` or the predicate returns `false`.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is `null` or the predicate returns `false`.
    ///
    /// @see #mapIf(Object, Predicate, Function)
    /// @see #mapNonNullIf(Object, Predicate, Function)
    /// @see #mapIfOrElse(Object, Predicate, Function, Object)
    /// @see #mapNonNullIfOrElse(Object, Predicate, Function, Object)
    /// @see #mapNonNullIfOrElseGet(Object, Predicate, Function, Supplier)
    ///
    public static <T, R> @Nullable R mapIfOrElseGet(
            @Nullable T value,
            Predicate<? super T> predicate,
            Function<? super T, ? extends @Nullable R> mapper,
            Supplier<@Nullable R> orElse
    ) {
        return (value != null && predicate.test(value)) ? mapper.apply(value) : orElse.get();
    }
    
    ///
    /// Maps the given value to a new value using the given mapper function if the value is not `null` and the predicate returns `true`.
    /// If the `value` is `null` or the predicate returns `false`, the `orElse` supplier's value is returned.
    ///
    /// Both the mapper function and the `orElse` supplier must not return `null`.
    ///
    /// @param value     The value to map.
    /// @param predicate The predicate to check against.
    /// @param mapper    The mapper function.
    /// @param orElse    The supplier of the value to return if the `value` is `null` or the predicate returns `false`.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is `null` or the predicate returns `false`.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` supplier returns `null`.
    /// @see #mapIf(Object, Predicate, Function)
    /// @see #mapNonNullIf(Object, Predicate, Function)
    /// @see #mapIfOrElse(Object, Predicate, Function, Object)
    /// @see #mapNonNullIfOrElse(Object, Predicate, Function, Object)
    /// @see #mapIfOrElseGet(Object, Predicate, Function, Supplier)
    ///
    public static <T, R> R mapNonNullIfOrElseGet(@Nullable T value, Predicate<? super T> predicate, Function<? super T, ? extends R> mapper, Supplier<R> orElse) {
        return (value != null && predicate.test(value)) ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse.get());
    }
    
    // ========== OPTIONAL ==========
    
    ///
    /// Maps the given value to an `Optional` using the given mapper function.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value  The value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value as an [Optional#ofNullable(Object)] or [Optional#empty()] if the `value` is `null`.
    ///
    public static <T, R> Optional<R> mapToOptional(@Nullable T value, Function<? super T, ? extends @Nullable R> mapper) {
        return value == null ? Optional.empty() : Optional.ofNullable(mapper.apply(value));
    }
    
    // ========== INSTANCE OF ==========
    
    ///
    /// Maps the given value to a new value using the given mapper function if the value is an instance of the given type.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value  The value to map.
    /// @param type   The type to check against.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is not an instance of the `type`.
    ///
    /// @see #mapNonNullIfInstanceOf(Object, Class, Function)
    ///
    public static <T, R> @Nullable R mapIfInstanceOf(@Nullable Object value, Class<T> type, Function<? super T, ? extends @Nullable R> mapper) {
        return type.isInstance(value) ? mapper.apply(type.cast(value)) : null;
    }
    
    ///
    /// Maps the given value to a new value using the given mapper function if the value is an instance of the given type.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value  The value to map.
    /// @param type   The type to check against.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is not an instance of the `type`.
    ///
    /// @throws NullPointerException If the mapper function returns `null`.
    /// @see #mapIfInstanceOf(Object, Class, Function)
    ///
    public static <T, R> @Nullable R mapNonNullIfInstanceOf(@Nullable Object value, Class<T> type, Function<? super T, ? extends R> mapper) {
        return type.isInstance(value) ? Objects.requireNonNull(mapper.apply(type.cast(value)), "Mapper function must not return null") : null;
    }
    
    // ========== PRIMITIVE ==========
    
    ///
    /// Maps the given value to a boolean using the given mapper function.
    /// If the `value` is `null`, the `orElse` value is returned.
    ///
    /// @param value  The value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is `null`.
    ///
    /// @return The mapped value, or `orElse` if the `value` is `null`.
    ///
    public static <T> boolean mapToBooleanOrElse(@Nullable T value, ToBooleanFunction<? super T> mapper, boolean orElse) {
        return value == null ? orElse : mapper.applyAsBoolean(value);
    }
    
    ///
    /// Maps the given value to a byte using the given mapper function.
    /// If the `value` is `null`, the `orElse` value is returned.
    ///
    /// @param value  The value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is `null`.
    ///
    /// @return The mapped value, or `orElse` if the `value` is `null`.
    ///
    public static <T> byte mapToByteOrElse(@Nullable T value, ToByteFunction<? super T> mapper, byte orElse) {
        return value == null ? orElse : mapper.applyAsByte(value);
    }
    
    ///
    /// Maps the given value to a short using the given mapper function.
    /// If the `value` is `null`, the `orElse` value is returned.
    ///
    /// @param value  The value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is `null`.
    ///
    /// @return The mapped value, or `orElse` if the `value` is `null`.
    ///
    public static <T> short mapToShortOrElse(@Nullable T value, ToShortFunction<? super T> mapper, short orElse) {
        return value == null ? orElse : mapper.applyAsShort(value);
    }
    
    ///
    /// Maps the given value to a char using the given mapper function.
    /// If the `value` is `null`, the `orElse` value is returned.
    ///
    /// @param value  The value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is `null`.
    ///
    /// @return The mapped value, or `orElse` if the `value` is `null`.
    ///
    public static <T> char mapToCharOrElse(@Nullable T value, ToCharFunction<? super T> mapper, char orElse) {
        return value == null ? orElse : mapper.applyAsChar(value);
    }
    
    ///
    /// Maps the given value to an int using the given mapper function.
    /// If the `value` is `null`, the `orElse` value is returned.
    ///
    /// @param value  The value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is `null`.
    ///
    /// @return The mapped value, or `orElse` if the `value` is `null`.
    ///
    public static <T> int mapToIntOrElse(@Nullable T value, ToIntFunction<? super T> mapper, int orElse) {
        return value == null ? orElse : mapper.applyAsInt(value);
    }
    
    ///
    /// Maps the given value to a long using the given mapper function.
    /// If the `value` is `null`, the `orElse` value is returned.
    ///
    /// @param value  The value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is `null`.
    ///
    /// @return The mapped value, or `orElse` if the `value` is `null`.
    ///
    public static <T> long mapToLongOrElse(@Nullable T value, ToLongFunction<? super T> mapper, long orElse) {
        return value == null ? orElse : mapper.applyAsLong(value);
    }
    
    ///
    /// Maps the given value to a float using the given mapper function.
    /// If the `value` is `null`, the `orElse` value is returned.
    ///
    /// @param value  The value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is `null`.
    ///
    /// @return The mapped value, or `orElse` if the `value` is `null`.
    ///
    public static <T> float mapToFloatOrElse(@Nullable T value, ToFloatFunction<? super T> mapper, float orElse) {
        return value == null ? orElse : mapper.applyAsFloat(value);
    }
    
    ///
    /// Maps the given value to a double using the given mapper function.
    /// If the `value` is `null`, the `orElse` value is returned.
    ///
    /// @param value  The value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is `null`.
    ///
    /// @return The mapped value, or `orElse` if the `value` is `null`.
    ///
    public static <T> double mapToDoubleOrElse(@Nullable T value, ToDoubleFunction<? super T> mapper, double orElse) {
        return value == null ? orElse : mapper.applyAsDouble(value);
    }
}
