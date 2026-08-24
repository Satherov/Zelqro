package dev.satherov.zelqro.mapping;

import lombok.experimental.UtilityClass;

import dev.satherov.zelqro.function.ByteFunction;
import dev.satherov.zelqro.predicate.BytePredicate;

import org.jspecify.annotations.Nullable;

import java.util.Objects;
import java.util.function.Supplier;

@UtilityClass
public class ByteMapping {
    
    // ========== PREDICATE ==========
    
    ///
    /// Maps the given byte value to a new value using the given mapper function if the predicate returns `true`.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value     The byte value to map.
    /// @param predicate The predicate to check against.
    /// @param mapper    The mapper function.
    ///
    /// @return The mapped value, or `null` if the predicate returns `false`.
    ///
    /// @see #mapNonNullIf(byte, BytePredicate, ByteFunction)
    /// @see #mapIfOrElse(byte, BytePredicate, ByteFunction, Object)
    /// @see #mapNonNullIfOrElse(byte, BytePredicate, ByteFunction, Object)
    /// @see #mapIfOrElseGet(byte, BytePredicate, ByteFunction, Supplier)
    /// @see #mapNonNullIfOrElseGet(byte, BytePredicate, ByteFunction, Supplier)
    ///
    public static <R> @Nullable R mapIf(byte value, BytePredicate predicate, ByteFunction<@Nullable R> mapper) {
        return predicate.test(value) ? mapper.apply(value) : null;
    }
    
    ///
    /// Maps the given byte value to a new value using the given mapper function if the predicate returns `true`.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value     The byte value to map.
    /// @param predicate The predicate to check against.
    /// @param mapper    The mapper function.
    ///
    /// @return The mapped value, or `null` if the predicate returns `false`.
    ///
    /// @throws NullPointerException If the mapper function returns `null`.
    /// @see #mapIf(byte, BytePredicate, ByteFunction)
    /// @see #mapIfOrElse(byte, BytePredicate, ByteFunction, Object)
    /// @see #mapNonNullIfOrElse(byte, BytePredicate, ByteFunction, Object)
    /// @see #mapIfOrElseGet(byte, BytePredicate, ByteFunction, Supplier)
    /// @see #mapNonNullIfOrElseGet(byte, BytePredicate, ByteFunction, Supplier)
    ///
    public static <R> @Nullable R mapNonNullIf(byte value, BytePredicate predicate, ByteFunction<R> mapper) {
        return predicate.test(value) ? Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") : null;
    }
    
    ///
    /// Maps the given byte value to a new value using the given mapper function if the predicate returns `true`.
    /// If the predicate returns `false`, the `orElse` value is returned.
    ///
    /// The mapper function and the `orElse` value may be `null`.
    ///
    /// @param value     The byte value to map.
    /// @param predicate The predicate to check against.
    /// @param mapper    The mapper function.
    /// @param orElse    The value to return if the predicate returns `false`.
    ///
    /// @return The mapped value, or `orElse` if the predicate returns `false`.
    ///
    /// @see #mapIf(byte, BytePredicate, ByteFunction)
    /// @see #mapNonNullIf(byte, BytePredicate, ByteFunction)
    /// @see #mapNonNullIfOrElse(byte, BytePredicate, ByteFunction, Object)
    /// @see #mapIfOrElseGet(byte, BytePredicate, ByteFunction, Supplier)
    /// @see #mapNonNullIfOrElseGet(byte, BytePredicate, ByteFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfOrElse(byte value, BytePredicate predicate, ByteFunction<@Nullable R> mapper, @Nullable R orElse) {
        return predicate.test(value) ? mapper.apply(value) : orElse;
    }
    
    ///
    /// Maps the given byte value to a new value using the given mapper function if the predicate returns `true`.
    /// If the predicate returns `false`, the `orElse` value is returned.
    ///
    /// Both the mapper function and the `orElse` value must not return `null`.
    ///
    /// @param value     The byte value to map.
    /// @param predicate The predicate to check against.
    /// @param mapper    The mapper function.
    /// @param orElse    The value to return if the predicate returns `false`.
    ///
    /// @return The mapped value, or `orElse` if the predicate returns `false`.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` value is `null`.
    /// @see #mapIf(byte, BytePredicate, ByteFunction)
    /// @see #mapNonNullIf(byte, BytePredicate, ByteFunction)
    /// @see #mapIfOrElse(byte, BytePredicate, ByteFunction, Object)
    /// @see #mapIfOrElseGet(byte, BytePredicate, ByteFunction, Supplier)
    /// @see #mapNonNullIfOrElseGet(byte, BytePredicate, ByteFunction, Supplier)
    ///
    public static <R> R mapNonNullIfOrElse(byte value, BytePredicate predicate, ByteFunction<R> mapper, R orElse) {
        return predicate.test(value) ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse);
    }
    
    ///
    /// Maps the given byte value to a new value using the given mapper function if the predicate returns `true`.
    /// If the predicate returns `false`, the `orElse` supplier's value is returned.
    ///
    /// The mapper function and the `orElse` supplier may be `null`.
    ///
    /// @param value     The byte value to map.
    /// @param predicate The predicate to check against.
    /// @param mapper    The mapper function.
    /// @param orElse    The supplier of the value to return if the predicate returns `false`.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the predicate returns `false`.
    ///
    /// @see #mapIf(byte, BytePredicate, ByteFunction)
    /// @see #mapNonNullIf(byte, BytePredicate, ByteFunction)
    /// @see #mapIfOrElse(byte, BytePredicate, ByteFunction, Object)
    /// @see #mapNonNullIfOrElse(byte, BytePredicate, ByteFunction, Object)
    /// @see #mapNonNullIfOrElseGet(byte, BytePredicate, ByteFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfOrElseGet(byte value, BytePredicate predicate, ByteFunction<@Nullable R> mapper, Supplier<@Nullable R> orElse) {
        return predicate.test(value) ? mapper.apply(value) : orElse.get();
    }
    
    ///
    /// Maps the given byte value to a new value using the given mapper function if the predicate returns `true`.
    /// If the predicate returns `false`, the `orElse` supplier's value is returned.
    ///
    /// Both the mapper function and the `orElse` supplier must not return `null`.
    ///
    /// @param value     The byte value to map.
    /// @param predicate The predicate to check against.
    /// @param mapper    The mapper function.
    /// @param orElse    The supplier of the value to return if the predicate returns `false`.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the predicate returns `false`.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` supplier returns `null`.
    /// @see #mapIf(byte, BytePredicate, ByteFunction)
    /// @see #mapNonNullIf(byte, BytePredicate, ByteFunction)
    /// @see #mapIfOrElse(byte, BytePredicate, ByteFunction, Object)
    /// @see #mapNonNullIfOrElse(byte, BytePredicate, ByteFunction, Object)
    /// @see #mapIfOrElseGet(byte, BytePredicate, ByteFunction, Supplier)
    ///
    public static <R> R mapNonNullIfOrElseGet(byte value, BytePredicate predicate, ByteFunction<R> mapper, Supplier<R> orElse) {
        return predicate.test(value) ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse.get());
    }
    
    // ========== POSITIVE ==========
    
    ///
    /// Maps the given byte value to a new value using the given mapper function if the value is positive.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value  The byte value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is not positive.
    ///
    /// @see #mapNonNullIfPositive(byte, ByteFunction)
    /// @see #mapIfPositiveOrElse(byte, ByteFunction, Object)
    /// @see #mapNonNullIfPositiveOrElse(byte, ByteFunction, Object)
    /// @see #mapIfPositiveOrElseGet(byte, ByteFunction, Supplier)
    /// @see #mapNonNullIfPositiveOrElseGet(byte, ByteFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfPositive(byte value, ByteFunction<@Nullable R> mapper) {
        return value > 0 ? mapper.apply(value) : null;
    }
    
    ///
    /// Maps the given byte value to a new value using the given mapper function if the value is positive.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value  The byte value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is not positive.
    ///
    /// @throws NullPointerException If the mapper function returns `null`.
    /// @see #mapIfPositive(byte, ByteFunction)
    /// @see #mapIfPositiveOrElse(byte, ByteFunction, Object)
    /// @see #mapNonNullIfPositiveOrElse(byte, ByteFunction, Object)
    /// @see #mapIfPositiveOrElseGet(byte, ByteFunction, Supplier)
    /// @see #mapNonNullIfPositiveOrElseGet(byte, ByteFunction, Supplier)
    ///
    public static <R> @Nullable R mapNonNullIfPositive(byte value, ByteFunction<R> mapper) {
        return value > 0 ? Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") : null;
    }
    
    ///
    /// Maps the given byte value to a new value using the given mapper function if the value is positive.
    /// If the `value` is not positive, the `orElse` value is returned.
    ///
    /// The mapper function and the `orElse` value may be `null`.
    ///
    /// @param value  The byte value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is not positive.
    ///
    /// @return The mapped value, or `orElse` if the `value` is not positive.
    ///
    /// @see #mapIfPositive(byte, ByteFunction)
    /// @see #mapNonNullIfPositive(byte, ByteFunction)
    /// @see #mapNonNullIfPositiveOrElse(byte, ByteFunction, Object)
    /// @see #mapIfPositiveOrElseGet(byte, ByteFunction, Supplier)
    /// @see #mapNonNullIfPositiveOrElseGet(byte, ByteFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfPositiveOrElse(byte value, ByteFunction<@Nullable R> mapper, @Nullable R orElse) {
        return value > 0 ? mapper.apply(value) : orElse;
    }
    
    ///
    /// Maps the given byte value to a new value using the given mapper function if the value is positive.
    /// If the `value` is not positive, the `orElse` value is returned.
    ///
    /// Both the mapper function and the `orElse` value must not return `null`.
    ///
    /// @param value  The byte value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is not positive.
    ///
    /// @return The mapped value, or `orElse` if the `value` is not positive.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` value is `null`.
    /// @see #mapIfPositive(byte, ByteFunction)
    /// @see #mapNonNullIfPositive(byte, ByteFunction)
    /// @see #mapIfPositiveOrElse(byte, ByteFunction, Object)
    /// @see #mapIfPositiveOrElseGet(byte, ByteFunction, Supplier)
    /// @see #mapNonNullIfPositiveOrElseGet(byte, ByteFunction, Supplier)
    ///
    public static <R> R mapNonNullIfPositiveOrElse(byte value, ByteFunction<R> mapper, R orElse) {
        return value > 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse);
    }
    
    ///
    /// Maps the given byte value to a new value using the given mapper function if the value is positive.
    /// If the `value` is not positive, the `orElse` supplier's value is returned.
    ///
    /// The mapper function and the `orElse` supplier may be `null`.
    ///
    /// @param value  The byte value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is not positive.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is not positive.
    ///
    /// @see #mapIfPositive(byte, ByteFunction)
    /// @see #mapNonNullIfPositive(byte, ByteFunction)
    /// @see #mapIfPositiveOrElse(byte, ByteFunction, Object)
    /// @see #mapNonNullIfPositiveOrElse(byte, ByteFunction, Object)
    /// @see #mapNonNullIfPositiveOrElseGet(byte, ByteFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfPositiveOrElseGet(byte value, ByteFunction<@Nullable R> mapper, Supplier<@Nullable R> orElse) {
        return value > 0 ? mapper.apply(value) : orElse.get();
    }
    
    ///
    /// Maps the given byte value to a new value using the given mapper function if the value is positive.
    /// If the `value` is not positive, the `orElse` supplier's value is returned.
    ///
    /// Both the mapper function and the `orElse` supplier must not return `null`.
    ///
    /// @param value  The byte value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is not positive.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is not positive.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` supplier returns `null`.
    /// @see #mapIfPositive(byte, ByteFunction)
    /// @see #mapNonNullIfPositive(byte, ByteFunction)
    /// @see #mapIfPositiveOrElse(byte, ByteFunction, Object)
    /// @see #mapNonNullIfPositiveOrElse(byte, ByteFunction, Object)
    /// @see #mapIfPositiveOrElseGet(byte, ByteFunction, Supplier)
    ///
    public static <R> R mapNonNullIfPositiveOrElseGet(byte value, ByteFunction<R> mapper, Supplier<R> orElse) {
        return value > 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse.get());
    }
    
    // ========== NEGATIVE ==========
    
    ///
    /// Maps the given byte value to a new value using the given mapper function if the value is negative.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value  The byte value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is not negative.
    ///
    /// @see #mapNonNullIfNegative(byte, ByteFunction)
    /// @see #mapIfNegativeOrElse(byte, ByteFunction, Object)
    /// @see #mapNonNullIfNegativeOrElse(byte, ByteFunction, Object)
    /// @see #mapIfNegativeOrElseGet(byte, ByteFunction, Supplier)
    /// @see #mapNonNullIfNegativeOrElseGet(byte, ByteFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNegative(byte value, ByteFunction<@Nullable R> mapper) {
        return value < 0 ? mapper.apply(value) : null;
    }
    
    ///
    /// Maps the given byte value to a new value using the given mapper function if the value is negative.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value  The byte value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is not negative.
    ///
    /// @throws NullPointerException If the mapper function returns `null`.
    /// @see #mapIfNegative(byte, ByteFunction)
    /// @see #mapIfNegativeOrElse(byte, ByteFunction, Object)
    /// @see #mapNonNullIfNegativeOrElse(byte, ByteFunction, Object)
    /// @see #mapIfNegativeOrElseGet(byte, ByteFunction, Supplier)
    /// @see #mapNonNullIfNegativeOrElseGet(byte, ByteFunction, Supplier)
    ///
    public static <R> @Nullable R mapNonNullIfNegative(byte value, ByteFunction<R> mapper) {
        return value < 0 ? Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") : null;
    }
    
    ///
    /// Maps the given byte value to a new value using the given mapper function if the value is negative.
    /// If the `value` is not negative, the `orElse` value is returned.
    ///
    /// The mapper function and the `orElse` value may be `null`.
    ///
    /// @param value  The byte value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is not negative.
    ///
    /// @return The mapped value, or `orElse` if the `value` is not negative.
    ///
    /// @see #mapIfNegative(byte, ByteFunction)
    /// @see #mapNonNullIfNegative(byte, ByteFunction)
    /// @see #mapNonNullIfNegativeOrElse(byte, ByteFunction, Object)
    /// @see #mapIfNegativeOrElseGet(byte, ByteFunction, Supplier)
    /// @see #mapNonNullIfNegativeOrElseGet(byte, ByteFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNegativeOrElse(byte value, ByteFunction<@Nullable R> mapper, @Nullable R orElse) {
        return value < 0 ? mapper.apply(value) : orElse;
    }
    
    ///
    /// Maps the given byte value to a new value using the given mapper function if the value is negative.
    /// If the `value` is not negative, the `orElse` value is returned.
    ///
    /// Both the mapper function and the `orElse` value must not return `null`.
    ///
    /// @param value  The byte value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is not negative.
    ///
    /// @return The mapped value, or `orElse` if the `value` is not negative.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` value is `null`.
    /// @see #mapIfNegative(byte, ByteFunction)
    /// @see #mapNonNullIfNegative(byte, ByteFunction)
    /// @see #mapIfNegativeOrElse(byte, ByteFunction, Object)
    /// @see #mapIfNegativeOrElseGet(byte, ByteFunction, Supplier)
    /// @see #mapNonNullIfNegativeOrElseGet(byte, ByteFunction, Supplier)
    ///
    public static <R> R mapNonNullIfNegativeOrElse(byte value, ByteFunction<R> mapper, R orElse) {
        return value < 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse);
    }
    
    ///
    /// Maps the given byte value to a new value using the given mapper function if the value is negative.
    /// If the `value` is not negative, the `orElse` supplier's value is returned.
    ///
    /// The mapper function and the `orElse` supplier may be `null`.
    ///
    /// @param value  The byte value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is not negative.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is not negative.
    ///
    /// @see #mapIfNegative(byte, ByteFunction)
    /// @see #mapNonNullIfNegative(byte, ByteFunction)
    /// @see #mapIfNegativeOrElse(byte, ByteFunction, Object)
    /// @see #mapNonNullIfNegativeOrElse(byte, ByteFunction, Object)
    /// @see #mapNonNullIfNegativeOrElseGet(byte, ByteFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNegativeOrElseGet(byte value, ByteFunction<@Nullable R> mapper, Supplier<@Nullable R> orElse) {
        return value < 0 ? mapper.apply(value) : orElse.get();
    }
    
    ///
    /// Maps the given byte value to a new value using the given mapper function if the value is negative.
    /// If the `value` is not negative, the `orElse` supplier's value is returned.
    ///
    /// Both the mapper function and the `orElse` supplier must not return `null`.
    ///
    /// @param value  The byte value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is not negative.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is not negative.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` supplier returns `null`.
    /// @see #mapIfNegative(byte, ByteFunction)
    /// @see #mapNonNullIfNegative(byte, ByteFunction)
    /// @see #mapIfNegativeOrElse(byte, ByteFunction, Object)
    /// @see #mapNonNullIfNegativeOrElse(byte, ByteFunction, Object)
    /// @see #mapIfNegativeOrElseGet(byte, ByteFunction, Supplier)
    ///
    public static <R> R mapNonNullIfNegativeOrElseGet(byte value, ByteFunction<R> mapper, Supplier<R> orElse) {
        return value < 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse.get());
    }
    
    // ========== NOT POSITIVE ==========
    
    ///
    /// Maps the given byte value to a new value using the given mapper function if the value is not positive.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value  The byte value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is positive.
    ///
    /// @see #mapNonNullIfNotPositive(byte, ByteFunction)
    /// @see #mapIfNotPositiveOrElse(byte, ByteFunction, Object)
    /// @see #mapNonNullIfNotPositiveOrElse(byte, ByteFunction, Object)
    /// @see #mapIfNotPositiveOrElseGet(byte, ByteFunction, Supplier)
    /// @see #mapNonNullIfNotPositiveOrElseGet(byte, ByteFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNotPositive(byte value, ByteFunction<@Nullable R> mapper) {
        return value <= 0 ? mapper.apply(value) : null;
    }
    
    ///
    /// Maps the given byte value to a new value using the given mapper function if the value is not positive.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value  The byte value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is positive.
    ///
    /// @throws NullPointerException If the mapper function returns `null`.
    /// @see #mapIfNotPositive(byte, ByteFunction)
    /// @see #mapIfNotPositiveOrElse(byte, ByteFunction, Object)
    /// @see #mapNonNullIfNotPositiveOrElse(byte, ByteFunction, Object)
    /// @see #mapIfNotPositiveOrElseGet(byte, ByteFunction, Supplier)
    /// @see #mapNonNullIfNotPositiveOrElseGet(byte, ByteFunction, Supplier)
    ///
    public static <R> @Nullable R mapNonNullIfNotPositive(byte value, ByteFunction<R> mapper) {
        return value <= 0 ? Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") : null;
    }
    
    ///
    /// Maps the given byte value to a new value using the given mapper function if the value is not positive.
    /// If the `value` is positive, the `orElse` value is returned.
    ///
    /// The mapper function and the `orElse` value may be `null`.
    ///
    /// @param value  The byte value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is positive.
    ///
    /// @return The mapped value, or `orElse` if the `value` is positive.
    ///
    /// @see #mapIfNotPositive(byte, ByteFunction)
    /// @see #mapNonNullIfNotPositive(byte, ByteFunction)
    /// @see #mapNonNullIfNotPositiveOrElse(byte, ByteFunction, Object)
    /// @see #mapIfNotPositiveOrElseGet(byte, ByteFunction, Supplier)
    /// @see #mapNonNullIfNotPositiveOrElseGet(byte, ByteFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNotPositiveOrElse(byte value, ByteFunction<@Nullable R> mapper, @Nullable R orElse) {
        return value <= 0 ? mapper.apply(value) : orElse;
    }
    
    ///
    /// Maps the given byte value to a new value using the given mapper function if the value is not positive.
    /// If the `value` is positive, the `orElse` value is returned.
    ///
    /// Both the mapper function and the `orElse` value must not return `null`.
    ///
    /// @param value  The byte value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is positive.
    ///
    /// @return The mapped value, or `orElse` if the `value` is positive.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` value is `null`.
    /// @see #mapIfNotPositive(byte, ByteFunction)
    /// @see #mapNonNullIfNotPositive(byte, ByteFunction)
    /// @see #mapIfNotPositiveOrElse(byte, ByteFunction, Object)
    /// @see #mapIfNotPositiveOrElseGet(byte, ByteFunction, Supplier)
    /// @see #mapNonNullIfNotPositiveOrElseGet(byte, ByteFunction, Supplier)
    ///
    public static <R> R mapNonNullIfNotPositiveOrElse(byte value, ByteFunction<R> mapper, R orElse) {
        return value <= 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse);
    }
    
    ///
    /// Maps the given byte value to a new value using the given mapper function if the value is not positive.
    /// If the `value` is positive, the `orElse` supplier's value is returned.
    ///
    /// The mapper function and the `orElse` supplier may be `null`.
    ///
    /// @param value  The byte value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is positive.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is positive.
    ///
    /// @see #mapIfNotPositive(byte, ByteFunction)
    /// @see #mapNonNullIfNotPositive(byte, ByteFunction)
    /// @see #mapIfNotPositiveOrElse(byte, ByteFunction, Object)
    /// @see #mapNonNullIfNotPositiveOrElse(byte, ByteFunction, Object)
    /// @see #mapNonNullIfNotPositiveOrElseGet(byte, ByteFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNotPositiveOrElseGet(byte value, ByteFunction<@Nullable R> mapper, Supplier<@Nullable R> orElse) {
        return value <= 0 ? mapper.apply(value) : orElse.get();
    }
    
    ///
    /// Maps the given byte value to a new value using the given mapper function if the value is not positive.
    /// If the `value` is positive, the `orElse` supplier's value is returned.
    ///
    /// Both the mapper function and the `orElse` supplier must not return `null`.
    ///
    /// @param value  The byte value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is positive.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is positive.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` supplier returns `null`.
    /// @see #mapIfNotPositive(byte, ByteFunction)
    /// @see #mapNonNullIfNotPositive(byte, ByteFunction)
    /// @see #mapIfNotPositiveOrElse(byte, ByteFunction, Object)
    /// @see #mapNonNullIfNotPositiveOrElse(byte, ByteFunction, Object)
    /// @see #mapIfNotPositiveOrElseGet(byte, ByteFunction, Supplier)
    ///
    public static <R> R mapNonNullIfNotPositiveOrElseGet(byte value, ByteFunction<R> mapper, Supplier<R> orElse) {
        return value <= 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse.get());
    }
    
    // ========== NOT NEGATIVE ==========
    
    ///
    /// Maps the given byte value to a new value using the given mapper function if the value is not negative.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value  The byte value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is negative.
    ///
    /// @see #mapNonNullIfNotNegative(byte, ByteFunction)
    /// @see #mapIfNotNegativeOrElse(byte, ByteFunction, Object)
    /// @see #mapNonNullIfNotNegativeOrElse(byte, ByteFunction, Object)
    /// @see #mapIfNotNegativeOrElseGet(byte, ByteFunction, Supplier)
    /// @see #mapNonNullIfNotNegativeOrElseGet(byte, ByteFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNotNegative(byte value, ByteFunction<@Nullable R> mapper) {
        return value >= 0 ? mapper.apply(value) : null;
    }
    
    ///
    /// Maps the given byte value to a new value using the given mapper function if the value is not negative.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value  The byte value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is negative.
    ///
    /// @throws NullPointerException If the mapper function returns `null`.
    /// @see #mapIfNotNegative(byte, ByteFunction)
    /// @see #mapIfNotNegativeOrElse(byte, ByteFunction, Object)
    /// @see #mapNonNullIfNotNegativeOrElse(byte, ByteFunction, Object)
    /// @see #mapIfNotNegativeOrElseGet(byte, ByteFunction, Supplier)
    /// @see #mapNonNullIfNotNegativeOrElseGet(byte, ByteFunction, Supplier)
    ///
    public static <R> @Nullable R mapNonNullIfNotNegative(byte value, ByteFunction<R> mapper) {
        return value >= 0 ? Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") : null;
    }
    
    ///
    /// Maps the given byte value to a new value using the given mapper function if the value is not negative.
    /// If the `value` is negative, the `orElse` value is returned.
    ///
    /// The mapper function and the `orElse` value may be `null`.
    ///
    /// @param value  The byte value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is negative.
    ///
    /// @return The mapped value, or `orElse` if the `value` is negative.
    ///
    /// @see #mapIfNotNegative(byte, ByteFunction)
    /// @see #mapNonNullIfNotNegative(byte, ByteFunction)
    /// @see #mapNonNullIfNotNegativeOrElse(byte, ByteFunction, Object)
    /// @see #mapIfNotNegativeOrElseGet(byte, ByteFunction, Supplier)
    /// @see #mapNonNullIfNotNegativeOrElseGet(byte, ByteFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNotNegativeOrElse(byte value, ByteFunction<@Nullable R> mapper, @Nullable R orElse) {
        return value >= 0 ? mapper.apply(value) : orElse;
    }
    
    ///
    /// Maps the given byte value to a new value using the given mapper function if the value is not negative.
    /// If the `value` is negative, the `orElse` value is returned.
    ///
    /// Both the mapper function and the `orElse` value must not return `null`.
    ///
    /// @param value  The byte value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is negative.
    ///
    /// @return The mapped value, or `orElse` if the `value` is negative.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` value is `null`.
    /// @see #mapIfNotNegative(byte, ByteFunction)
    /// @see #mapNonNullIfNotNegative(byte, ByteFunction)
    /// @see #mapIfNotNegativeOrElse(byte, ByteFunction, Object)
    /// @see #mapIfNotNegativeOrElseGet(byte, ByteFunction, Supplier)
    /// @see #mapNonNullIfNotNegativeOrElseGet(byte, ByteFunction, Supplier)
    ///
    public static <R> R mapNonNullIfNotNegativeOrElse(byte value, ByteFunction<R> mapper, R orElse) {
        return value >= 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse);
    }
    
    ///
    /// Maps the given byte value to a new value using the given mapper function if the value is not negative.
    /// If the `value` is negative, the `orElse` supplier's value is returned.
    ///
    /// The mapper function and the `orElse` supplier may be `null`.
    ///
    /// @param value  The byte value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is negative.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is negative.
    ///
    /// @see #mapIfNotNegative(byte, ByteFunction)
    /// @see #mapNonNullIfNotNegative(byte, ByteFunction)
    /// @see #mapIfNotNegativeOrElse(byte, ByteFunction, Object)
    /// @see #mapNonNullIfNotNegativeOrElse(byte, ByteFunction, Object)
    /// @see #mapNonNullIfNotNegativeOrElseGet(byte, ByteFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNotNegativeOrElseGet(byte value, ByteFunction<@Nullable R> mapper, Supplier<@Nullable R> orElse) {
        return value >= 0 ? mapper.apply(value) : orElse.get();
    }
    
    ///
    /// Maps the given byte value to a new value using the given mapper function if the value is not negative.
    /// If the `value` is negative, the `orElse` supplier's value is returned.
    ///
    /// Both the mapper function and the `orElse` supplier must not return `null`.
    ///
    /// @param value  The byte value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is negative.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is negative.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` supplier returns `null`.
    /// @see #mapIfNotNegative(byte, ByteFunction)
    /// @see #mapNonNullIfNotNegative(byte, ByteFunction)
    /// @see #mapIfNotNegativeOrElse(byte, ByteFunction, Object)
    /// @see #mapNonNullIfNotNegativeOrElse(byte, ByteFunction, Object)
    /// @see #mapIfNotNegativeOrElseGet(byte, ByteFunction, Supplier)
    ///
    public static <R> R mapNonNullIfNotNegativeOrElseGet(byte value, ByteFunction<R> mapper, Supplier<R> orElse) {
        return value >= 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse.get());
    }
    
    // ========== ZERO ==========
    
    ///
    /// Maps the given byte value to a new value using the given mapper function if the value is zero.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value  The byte value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is not zero.
    ///
    /// @see #mapNonNullIfZero(byte, ByteFunction)
    /// @see #mapIfZeroOrElse(byte, ByteFunction, Object)
    /// @see #mapNonNullIfZeroOrElse(byte, ByteFunction, Object)
    /// @see #mapIfZeroOrElseGet(byte, ByteFunction, Supplier)
    /// @see #mapNonNullIfZeroOrElseGet(byte, ByteFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfZero(byte value, ByteFunction<@Nullable R> mapper) {
        return value == 0 ? mapper.apply(value) : null;
    }
    
    ///
    /// Maps the given byte value to a new value using the given mapper function if the value is zero.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value  The byte value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is not zero.
    ///
    /// @throws NullPointerException If the mapper function returns `null`.
    /// @see #mapIfZero(byte, ByteFunction)
    /// @see #mapIfZeroOrElse(byte, ByteFunction, Object)
    /// @see #mapNonNullIfZeroOrElse(byte, ByteFunction, Object)
    /// @see #mapIfZeroOrElseGet(byte, ByteFunction, Supplier)
    /// @see #mapNonNullIfZeroOrElseGet(byte, ByteFunction, Supplier)
    ///
    public static <R> @Nullable R mapNonNullIfZero(byte value, ByteFunction<R> mapper) {
        return value == 0 ? Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") : null;
    }
    
    ///
    /// Maps the given byte value to a new value using the given mapper function if the value is zero.
    /// If the `value` is not zero, the `orElse` value is returned.
    ///
    /// The mapper function and the `orElse` value may be `null`.
    ///
    /// @param value  The byte value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is not zero.
    ///
    /// @return The mapped value, or `orElse` if the `value` is not zero.
    ///
    /// @see #mapIfZero(byte, ByteFunction)
    /// @see #mapNonNullIfZero(byte, ByteFunction)
    /// @see #mapNonNullIfZeroOrElse(byte, ByteFunction, Object)
    /// @see #mapIfZeroOrElseGet(byte, ByteFunction, Supplier)
    /// @see #mapNonNullIfZeroOrElseGet(byte, ByteFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfZeroOrElse(byte value, ByteFunction<@Nullable R> mapper, @Nullable R orElse) {
        return value == 0 ? mapper.apply(value) : orElse;
    }
    
    ///
    /// Maps the given byte value to a new value using the given mapper function if the value is zero.
    /// If the `value` is not zero, the `orElse` value is returned.
    ///
    /// Both the mapper function and the `orElse` value must not return `null`.
    ///
    /// @param value  The byte value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is not zero.
    ///
    /// @return The mapped value, or `orElse` if the `value` is not zero.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` value is `null`.
    /// @see #mapIfZero(byte, ByteFunction)
    /// @see #mapNonNullIfZero(byte, ByteFunction)
    /// @see #mapIfZeroOrElse(byte, ByteFunction, Object)
    /// @see #mapIfZeroOrElseGet(byte, ByteFunction, Supplier)
    /// @see #mapNonNullIfZeroOrElseGet(byte, ByteFunction, Supplier)
    ///
    public static <R> R mapNonNullIfZeroOrElse(byte value, ByteFunction<R> mapper, R orElse) {
        return value == 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse);
    }
    
    ///
    /// Maps the given byte value to a new value using the given mapper function if the value is zero.
    /// If the `value` is not zero, the `orElse` supplier's value is returned.
    ///
    /// The mapper function and the `orElse` supplier may be `null`.
    ///
    /// @param value  The byte value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is not zero.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is not zero.
    ///
    /// @see #mapIfZero(byte, ByteFunction)
    /// @see #mapNonNullIfZero(byte, ByteFunction)
    /// @see #mapIfZeroOrElse(byte, ByteFunction, Object)
    /// @see #mapNonNullIfZeroOrElse(byte, ByteFunction, Object)
    /// @see #mapNonNullIfZeroOrElseGet(byte, ByteFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfZeroOrElseGet(byte value, ByteFunction<@Nullable R> mapper, Supplier<@Nullable R> orElse) {
        return value == 0 ? mapper.apply(value) : orElse.get();
    }
    
    ///
    /// Maps the given byte value to a new value using the given mapper function if the value is zero.
    /// If the `value` is not zero, the `orElse` supplier's value is returned.
    ///
    /// Both the mapper function and the `orElse` supplier must not return `null`.
    ///
    /// @param value  The byte value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is not zero.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is not zero.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` supplier returns `null`.
    /// @see #mapIfZero(byte, ByteFunction)
    /// @see #mapNonNullIfZero(byte, ByteFunction)
    /// @see #mapIfZeroOrElse(byte, ByteFunction, Object)
    /// @see #mapNonNullIfZeroOrElse(byte, ByteFunction, Object)
    /// @see #mapIfZeroOrElseGet(byte, ByteFunction, Supplier)
    ///
    public static <R> R mapNonNullIfZeroOrElseGet(byte value, ByteFunction<R> mapper, Supplier<R> orElse) {
        return value == 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse.get());
    }
    
    // ========== NOT ZERO ==========
    
    ///
    /// Maps the given byte value to a new value using the given mapper function if the value is not zero.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value  The byte value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is zero.
    ///
    /// @see #mapNonNullIfNotZero(byte, ByteFunction)
    /// @see #mapIfNotZeroOrElse(byte, ByteFunction, Object)
    /// @see #mapNonNullIfNotZeroOrElse(byte, ByteFunction, Object)
    /// @see #mapIfNotZeroOrElseGet(byte, ByteFunction, Supplier)
    /// @see #mapNonNullIfNotZeroOrElseGet(byte, ByteFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNotZero(byte value, ByteFunction<@Nullable R> mapper) {
        return value != 0 ? mapper.apply(value) : null;
    }
    
    ///
    /// Maps the given byte value to a new value using the given mapper function if the value is not zero.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value  The byte value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is zero.
    ///
    /// @throws NullPointerException If the mapper function returns `null`.
    /// @see #mapIfNotZero(byte, ByteFunction)
    /// @see #mapIfNotZeroOrElse(byte, ByteFunction, Object)
    /// @see #mapNonNullIfNotZeroOrElse(byte, ByteFunction, Object)
    /// @see #mapIfNotZeroOrElseGet(byte, ByteFunction, Supplier)
    /// @see #mapNonNullIfNotZeroOrElseGet(byte, ByteFunction, Supplier)
    ///
    public static <R> @Nullable R mapNonNullIfNotZero(byte value, ByteFunction<R> mapper) {
        return value != 0 ? Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") : null;
    }
    
    ///
    /// Maps the given byte value to a new value using the given mapper function if the value is not zero.
    /// If the `value` is zero, the `orElse` value is returned.
    ///
    /// The mapper function and the `orElse` value may be `null`.
    ///
    /// @param value  The byte value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is zero.
    ///
    /// @return The mapped value, or `orElse` if the `value` is zero.
    ///
    /// @see #mapIfNotZero(byte, ByteFunction)
    /// @see #mapNonNullIfNotZero(byte, ByteFunction)
    /// @see #mapNonNullIfNotZeroOrElse(byte, ByteFunction, Object)
    /// @see #mapIfNotZeroOrElseGet(byte, ByteFunction, Supplier)
    /// @see #mapNonNullIfNotZeroOrElseGet(byte, ByteFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNotZeroOrElse(byte value, ByteFunction<@Nullable R> mapper, @Nullable R orElse) {
        return value != 0 ? mapper.apply(value) : orElse;
    }
    
    ///
    /// Maps the given byte value to a new value using the given mapper function if the value is not zero.
    /// If the `value` is zero, the `orElse` value is returned.
    ///
    /// Both the mapper function and the `orElse` value must not return `null`.
    ///
    /// @param value  The byte value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is zero.
    ///
    /// @return The mapped value, or `orElse` if the `value` is zero.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` value is `null`.
    /// @see #mapIfNotZero(byte, ByteFunction)
    /// @see #mapNonNullIfNotZero(byte, ByteFunction)
    /// @see #mapIfNotZeroOrElse(byte, ByteFunction, Object)
    /// @see #mapIfNotZeroOrElseGet(byte, ByteFunction, Supplier)
    /// @see #mapNonNullIfNotZeroOrElseGet(byte, ByteFunction, Supplier)
    ///
    public static <R> R mapNonNullIfNotZeroOrElse(byte value, ByteFunction<R> mapper, R orElse) {
        return value != 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse);
    }
    
    ///
    /// Maps the given byte value to a new value using the given mapper function if the value is not zero.
    /// If the `value` is zero, the `orElse` supplier's value is returned.
    ///
    /// The mapper function and the `orElse` supplier may be `null`.
    ///
    /// @param value  The byte value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is zero.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is zero.
    ///
    /// @see #mapIfNotZero(byte, ByteFunction)
    /// @see #mapNonNullIfNotZero(byte, ByteFunction)
    /// @see #mapIfNotZeroOrElse(byte, ByteFunction, Object)
    /// @see #mapNonNullIfNotZeroOrElse(byte, ByteFunction, Object)
    /// @see #mapNonNullIfNotZeroOrElseGet(byte, ByteFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNotZeroOrElseGet(byte value, ByteFunction<@Nullable R> mapper, Supplier<@Nullable R> orElse) {
        return value != 0 ? mapper.apply(value) : orElse.get();
    }
    
    ///
    /// Maps the given byte value to a new value using the given mapper function if the value is not zero.
    /// If the `value` is zero, the `orElse` supplier's value is returned.
    ///
    /// Both the mapper function and the `orElse` supplier must not return `null`.
    ///
    /// @param value  The byte value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is zero.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is zero.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` supplier returns `null`.
    /// @see #mapIfNotZero(byte, ByteFunction)
    /// @see #mapNonNullIfNotZero(byte, ByteFunction)
    /// @see #mapIfNotZeroOrElse(byte, ByteFunction, Object)
    /// @see #mapNonNullIfNotZeroOrElse(byte, ByteFunction, Object)
    /// @see #mapIfNotZeroOrElseGet(byte, ByteFunction, Supplier)
    ///
    public static <R> R mapNonNullIfNotZeroOrElseGet(byte value, ByteFunction<R> mapper, Supplier<R> orElse) {
        return value != 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse.get());
    }
    
    // ========== IN RANGE ==========
    
    ///
    /// Maps the given byte value to a new value using the given mapper function if the value is within the given range.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value  The byte value to map.
    /// @param min    The lower bound of the range, inclusive.
    /// @param max    The upper bound of the range, inclusive.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is outside the given range.
    ///
    /// @see #mapNonNullIfInRange(byte, byte, byte, ByteFunction)
    /// @see #mapIfInRangeOrElse(byte, byte, byte, ByteFunction, Object)
    /// @see #mapNonNullIfInRangeOrElse(byte, byte, byte, ByteFunction, Object)
    /// @see #mapIfInRangeOrElseGet(byte, byte, byte, ByteFunction, Supplier)
    /// @see #mapNonNullIfInRangeOrElseGet(byte, byte, byte, ByteFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfInRange(byte value, byte min, byte max, ByteFunction<@Nullable R> mapper) {
        return (value >= min && value <= max) ? mapper.apply(value) : null;
    }
    
    ///
    /// Maps the given byte value to a new value using the given mapper function if the value is within the given range.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value  The byte value to map.
    /// @param min    The lower bound of the range, inclusive.
    /// @param max    The upper bound of the range, inclusive.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is outside the given range.
    ///
    /// @throws NullPointerException If the mapper function returns `null`.
    /// @see #mapIfInRange(byte, byte, byte, ByteFunction)
    /// @see #mapIfInRangeOrElse(byte, byte, byte, ByteFunction, Object)
    /// @see #mapNonNullIfInRangeOrElse(byte, byte, byte, ByteFunction, Object)
    /// @see #mapIfInRangeOrElseGet(byte, byte, byte, ByteFunction, Supplier)
    /// @see #mapNonNullIfInRangeOrElseGet(byte, byte, byte, ByteFunction, Supplier)
    ///
    public static <R> @Nullable R mapNonNullIfInRange(byte value, byte min, byte max, ByteFunction<R> mapper) {
        return (value >= min && value <= max) ? Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") : null;
    }
    
    ///
    /// Maps the given byte value to a new value using the given mapper function if the value is within the given range.
    /// If the `value` is outside the given range, the `orElse` value is returned.
    ///
    /// The mapper function and the `orElse` value may be `null`.
    ///
    /// @param value  The byte value to map.
    /// @param min    The lower bound of the range, inclusive.
    /// @param max    The upper bound of the range, inclusive.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is outside the given range.
    ///
    /// @return The mapped value, or `orElse` if the `value` is outside the given range.
    ///
    /// @see #mapIfInRange(byte, byte, byte, ByteFunction)
    /// @see #mapNonNullIfInRange(byte, byte, byte, ByteFunction)
    /// @see #mapNonNullIfInRangeOrElse(byte, byte, byte, ByteFunction, Object)
    /// @see #mapIfInRangeOrElseGet(byte, byte, byte, ByteFunction, Supplier)
    /// @see #mapNonNullIfInRangeOrElseGet(byte, byte, byte, ByteFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfInRangeOrElse(byte value, byte min, byte max, ByteFunction<@Nullable R> mapper, @Nullable R orElse) {
        return (value >= min && value <= max) ? mapper.apply(value) : orElse;
    }
    
    ///
    /// Maps the given byte value to a new value using the given mapper function if the value is within the given range.
    /// If the `value` is outside the given range, the `orElse` value is returned.
    ///
    /// Both the mapper function and the `orElse` value must not return `null`.
    ///
    /// @param value  The byte value to map.
    /// @param min    The lower bound of the range, inclusive.
    /// @param max    The upper bound of the range, inclusive.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is outside the given range.
    ///
    /// @return The mapped value, or `orElse` if the `value` is outside the given range.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` value is `null`.
    /// @see #mapIfInRange(byte, byte, byte, ByteFunction)
    /// @see #mapNonNullIfInRange(byte, byte, byte, ByteFunction)
    /// @see #mapIfInRangeOrElse(byte, byte, byte, ByteFunction, Object)
    /// @see #mapIfInRangeOrElseGet(byte, byte, byte, ByteFunction, Supplier)
    /// @see #mapNonNullIfInRangeOrElseGet(byte, byte, byte, ByteFunction, Supplier)
    ///
    public static <R> R mapNonNullIfInRangeOrElse(byte value, byte min, byte max, ByteFunction<R> mapper, R orElse) {
        return (value >= min && value <= max) ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse);
    }
    
    ///
    /// Maps the given byte value to a new value using the given mapper function if the value is within the given range.
    /// If the `value` is outside the given range, the `orElse` supplier's value is returned.
    ///
    /// The mapper function and the `orElse` supplier may be `null`.
    ///
    /// @param value  The byte value to map.
    /// @param min    The lower bound of the range, inclusive.
    /// @param max    The upper bound of the range, inclusive.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is outside the given range.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is outside the given range.
    ///
    /// @see #mapIfInRange(byte, byte, byte, ByteFunction)
    /// @see #mapNonNullIfInRange(byte, byte, byte, ByteFunction)
    /// @see #mapIfInRangeOrElse(byte, byte, byte, ByteFunction, Object)
    /// @see #mapNonNullIfInRangeOrElse(byte, byte, byte, ByteFunction, Object)
    /// @see #mapNonNullIfInRangeOrElseGet(byte, byte, byte, ByteFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfInRangeOrElseGet(byte value, byte min, byte max, ByteFunction<@Nullable R> mapper, Supplier<@Nullable R> orElse) {
        return (value >= min && value <= max) ? mapper.apply(value) : orElse.get();
    }
    
    ///
    /// Maps the given byte value to a new value using the given mapper function if the value is within the given range.
    /// If the `value` is outside the given range, the `orElse` supplier's value is returned.
    ///
    /// Both the mapper function and the `orElse` supplier must not return `null`.
    ///
    /// @param value  The byte value to map.
    /// @param min    The lower bound of the range, inclusive.
    /// @param max    The upper bound of the range, inclusive.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is outside the given range.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is outside the given range.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` supplier returns `null`.
    /// @see #mapIfInRange(byte, byte, byte, ByteFunction)
    /// @see #mapNonNullIfInRange(byte, byte, byte, ByteFunction)
    /// @see #mapIfInRangeOrElse(byte, byte, byte, ByteFunction, Object)
    /// @see #mapNonNullIfInRangeOrElse(byte, byte, byte, ByteFunction, Object)
    /// @see #mapIfInRangeOrElseGet(byte, byte, byte, ByteFunction, Supplier)
    ///
    public static <R> R mapNonNullIfInRangeOrElseGet(byte value, byte min, byte max, ByteFunction<R> mapper, Supplier<R> orElse) {
        return (value >= min && value <= max) ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse.get());
    }
}
