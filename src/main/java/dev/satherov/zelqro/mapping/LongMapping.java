package dev.satherov.zelqro.mapping;

import lombok.experimental.UtilityClass;

import dev.satherov.zelqro.function.LongFunction;
import dev.satherov.zelqro.predicate.LongPredicate;

import org.jspecify.annotations.Nullable;

import java.util.Objects;
import java.util.function.Supplier;

@UtilityClass
public class LongMapping {
    
    // ========== PREDICATE ==========
    
    ///
    /// Maps the given long value to a new value using the given mapper function if the predicate returns `true`.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value     The long value to map.
    /// @param predicate The predicate to check against.
    /// @param mapper    The mapper function.
    ///
    /// @return The mapped value, or `null` if the predicate returns `false`.
    ///
    /// @see #mapNonNullIf(long, LongPredicate, LongFunction)
    /// @see #mapIfOrElse(long, LongPredicate, LongFunction, Object)
    /// @see #mapNonNullIfOrElse(long, LongPredicate, LongFunction, Object)
    /// @see #mapIfOrElseGet(long, LongPredicate, LongFunction, Supplier)
    /// @see #mapNonNullIfOrElseGet(long, LongPredicate, LongFunction, Supplier)
    ///
    public static <R> @Nullable R mapIf(long value, LongPredicate predicate, LongFunction<@Nullable R> mapper) {
        return predicate.test(value) ? mapper.apply(value) : null;
    }
    
    ///
    /// Maps the given long value to a new value using the given mapper function if the predicate returns `true`.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value     The long value to map.
    /// @param predicate The predicate to check against.
    /// @param mapper    The mapper function.
    ///
    /// @return The mapped value, or `null` if the predicate returns `false`.
    ///
    /// @throws NullPointerException If the mapper function returns `null`.
    /// @see #mapIf(long, LongPredicate, LongFunction)
    /// @see #mapIfOrElse(long, LongPredicate, LongFunction, Object)
    /// @see #mapNonNullIfOrElse(long, LongPredicate, LongFunction, Object)
    /// @see #mapIfOrElseGet(long, LongPredicate, LongFunction, Supplier)
    /// @see #mapNonNullIfOrElseGet(long, LongPredicate, LongFunction, Supplier)
    ///
    public static <R> @Nullable R mapNonNullIf(long value, LongPredicate predicate, LongFunction<R> mapper) {
        return predicate.test(value) ? Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") : null;
    }
    
    ///
    /// Maps the given long value to a new value using the given mapper function if the predicate returns `true`.
    /// If the predicate returns `false`, the `orElse` value is returned.
    ///
    /// The mapper function and the `orElse` value may be `null`.
    ///
    /// @param value     The long value to map.
    /// @param predicate The predicate to check against.
    /// @param mapper    The mapper function.
    /// @param orElse    The value to return if the predicate returns `false`.
    ///
    /// @return The mapped value, or `orElse` if the predicate returns `false`.
    ///
    /// @see #mapIf(long, LongPredicate, LongFunction)
    /// @see #mapNonNullIf(long, LongPredicate, LongFunction)
    /// @see #mapNonNullIfOrElse(long, LongPredicate, LongFunction, Object)
    /// @see #mapIfOrElseGet(long, LongPredicate, LongFunction, Supplier)
    /// @see #mapNonNullIfOrElseGet(long, LongPredicate, LongFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfOrElse(long value, LongPredicate predicate, LongFunction<@Nullable R> mapper, @Nullable R orElse) {
        return predicate.test(value) ? mapper.apply(value) : orElse;
    }
    
    ///
    /// Maps the given long value to a new value using the given mapper function if the predicate returns `true`.
    /// If the predicate returns `false`, the `orElse` value is returned.
    ///
    /// Both the mapper function and the `orElse` value must not return `null`.
    ///
    /// @param value     The long value to map.
    /// @param predicate The predicate to check against.
    /// @param mapper    The mapper function.
    /// @param orElse    The value to return if the predicate returns `false`.
    ///
    /// @return The mapped value, or `orElse` if the predicate returns `false`.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` value is `null`.
    /// @see #mapIf(long, LongPredicate, LongFunction)
    /// @see #mapNonNullIf(long, LongPredicate, LongFunction)
    /// @see #mapIfOrElse(long, LongPredicate, LongFunction, Object)
    /// @see #mapIfOrElseGet(long, LongPredicate, LongFunction, Supplier)
    /// @see #mapNonNullIfOrElseGet(long, LongPredicate, LongFunction, Supplier)
    ///
    public static <R> R mapNonNullIfOrElse(long value, LongPredicate predicate, LongFunction<R> mapper, R orElse) {
        return predicate.test(value) ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse);
    }
    
    ///
    /// Maps the given long value to a new value using the given mapper function if the predicate returns `true`.
    /// If the predicate returns `false`, the `orElse` supplier's value is returned.
    ///
    /// The mapper function and the `orElse` supplier may be `null`.
    ///
    /// @param value     The long value to map.
    /// @param predicate The predicate to check against.
    /// @param mapper    The mapper function.
    /// @param orElse    The supplier of the value to return if the predicate returns `false`.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the predicate returns `false`.
    ///
    /// @see #mapIf(long, LongPredicate, LongFunction)
    /// @see #mapNonNullIf(long, LongPredicate, LongFunction)
    /// @see #mapIfOrElse(long, LongPredicate, LongFunction, Object)
    /// @see #mapNonNullIfOrElse(long, LongPredicate, LongFunction, Object)
    /// @see #mapNonNullIfOrElseGet(long, LongPredicate, LongFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfOrElseGet(long value, LongPredicate predicate, LongFunction<@Nullable R> mapper, Supplier<@Nullable R> orElse) {
        return predicate.test(value) ? mapper.apply(value) : orElse.get();
    }
    
    ///
    /// Maps the given long value to a new value using the given mapper function if the predicate returns `true`.
    /// If the predicate returns `false`, the `orElse` supplier's value is returned.
    ///
    /// Both the mapper function and the `orElse` supplier must not return `null`.
    ///
    /// @param value     The long value to map.
    /// @param predicate The predicate to check against.
    /// @param mapper    The mapper function.
    /// @param orElse    The supplier of the value to return if the predicate returns `false`.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the predicate returns `false`.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` supplier returns `null`.
    /// @see #mapIf(long, LongPredicate, LongFunction)
    /// @see #mapNonNullIf(long, LongPredicate, LongFunction)
    /// @see #mapIfOrElse(long, LongPredicate, LongFunction, Object)
    /// @see #mapNonNullIfOrElse(long, LongPredicate, LongFunction, Object)
    /// @see #mapIfOrElseGet(long, LongPredicate, LongFunction, Supplier)
    ///
    public static <R> R mapNonNullIfOrElseGet(long value, LongPredicate predicate, LongFunction<R> mapper, Supplier<R> orElse) {
        return predicate.test(value) ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse.get());
    }
    
    // ========== POSITIVE ==========
    
    ///
    /// Maps the given long value to a new value using the given mapper function if the value is positive.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value  The long value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is not positive.
    ///
    /// @see #mapNonNullIfPositive(long, LongFunction)
    /// @see #mapIfPositiveOrElse(long, LongFunction, Object)
    /// @see #mapNonNullIfPositiveOrElse(long, LongFunction, Object)
    /// @see #mapIfPositiveOrElseGet(long, LongFunction, Supplier)
    /// @see #mapNonNullIfPositiveOrElseGet(long, LongFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfPositive(long value, LongFunction<@Nullable R> mapper) {
        return value > 0 ? mapper.apply(value) : null;
    }
    
    ///
    /// Maps the given long value to a new value using the given mapper function if the value is positive.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value  The long value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is not positive.
    ///
    /// @throws NullPointerException If the mapper function returns `null`.
    /// @see #mapIfPositive(long, LongFunction)
    /// @see #mapIfPositiveOrElse(long, LongFunction, Object)
    /// @see #mapNonNullIfPositiveOrElse(long, LongFunction, Object)
    /// @see #mapIfPositiveOrElseGet(long, LongFunction, Supplier)
    /// @see #mapNonNullIfPositiveOrElseGet(long, LongFunction, Supplier)
    ///
    public static <R> @Nullable R mapNonNullIfPositive(long value, LongFunction<R> mapper) {
        return value > 0 ? Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") : null;
    }
    
    ///
    /// Maps the given long value to a new value using the given mapper function if the value is positive.
    /// If the `value` is not positive, the `orElse` value is returned.
    ///
    /// The mapper function and the `orElse` value may be `null`.
    ///
    /// @param value  The long value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is not positive.
    ///
    /// @return The mapped value, or `orElse` if the `value` is not positive.
    ///
    /// @see #mapIfPositive(long, LongFunction)
    /// @see #mapNonNullIfPositive(long, LongFunction)
    /// @see #mapNonNullIfPositiveOrElse(long, LongFunction, Object)
    /// @see #mapIfPositiveOrElseGet(long, LongFunction, Supplier)
    /// @see #mapNonNullIfPositiveOrElseGet(long, LongFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfPositiveOrElse(long value, LongFunction<@Nullable R> mapper, @Nullable R orElse) {
        return value > 0 ? mapper.apply(value) : orElse;
    }
    
    ///
    /// Maps the given long value to a new value using the given mapper function if the value is positive.
    /// If the `value` is not positive, the `orElse` value is returned.
    ///
    /// Both the mapper function and the `orElse` value must not return `null`.
    ///
    /// @param value  The long value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is not positive.
    ///
    /// @return The mapped value, or `orElse` if the `value` is not positive.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` value is `null`.
    /// @see #mapIfPositive(long, LongFunction)
    /// @see #mapNonNullIfPositive(long, LongFunction)
    /// @see #mapIfPositiveOrElse(long, LongFunction, Object)
    /// @see #mapIfPositiveOrElseGet(long, LongFunction, Supplier)
    /// @see #mapNonNullIfPositiveOrElseGet(long, LongFunction, Supplier)
    ///
    public static <R> R mapNonNullIfPositiveOrElse(long value, LongFunction<R> mapper, R orElse) {
        return value > 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse);
    }
    
    ///
    /// Maps the given long value to a new value using the given mapper function if the value is positive.
    /// If the `value` is not positive, the `orElse` supplier's value is returned.
    ///
    /// The mapper function and the `orElse` supplier may be `null`.
    ///
    /// @param value  The long value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is not positive.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is not positive.
    ///
    /// @see #mapIfPositive(long, LongFunction)
    /// @see #mapNonNullIfPositive(long, LongFunction)
    /// @see #mapIfPositiveOrElse(long, LongFunction, Object)
    /// @see #mapNonNullIfPositiveOrElse(long, LongFunction, Object)
    /// @see #mapNonNullIfPositiveOrElseGet(long, LongFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfPositiveOrElseGet(long value, LongFunction<@Nullable R> mapper, Supplier<@Nullable R> orElse) {
        return value > 0 ? mapper.apply(value) : orElse.get();
    }
    
    ///
    /// Maps the given long value to a new value using the given mapper function if the value is positive.
    /// If the `value` is not positive, the `orElse` supplier's value is returned.
    ///
    /// Both the mapper function and the `orElse` supplier must not return `null`.
    ///
    /// @param value  The long value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is not positive.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is not positive.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` supplier returns `null`.
    /// @see #mapIfPositive(long, LongFunction)
    /// @see #mapNonNullIfPositive(long, LongFunction)
    /// @see #mapIfPositiveOrElse(long, LongFunction, Object)
    /// @see #mapNonNullIfPositiveOrElse(long, LongFunction, Object)
    /// @see #mapIfPositiveOrElseGet(long, LongFunction, Supplier)
    ///
    public static <R> R mapNonNullIfPositiveOrElseGet(long value, LongFunction<R> mapper, Supplier<R> orElse) {
        return value > 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse.get());
    }
    
    // ========== NEGATIVE ==========
    
    ///
    /// Maps the given long value to a new value using the given mapper function if the value is negative.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value  The long value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is not negative.
    ///
    /// @see #mapNonNullIfNegative(long, LongFunction)
    /// @see #mapIfNegativeOrElse(long, LongFunction, Object)
    /// @see #mapNonNullIfNegativeOrElse(long, LongFunction, Object)
    /// @see #mapIfNegativeOrElseGet(long, LongFunction, Supplier)
    /// @see #mapNonNullIfNegativeOrElseGet(long, LongFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNegative(long value, LongFunction<@Nullable R> mapper) {
        return value < 0 ? mapper.apply(value) : null;
    }
    
    ///
    /// Maps the given long value to a new value using the given mapper function if the value is negative.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value  The long value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is not negative.
    ///
    /// @throws NullPointerException If the mapper function returns `null`.
    /// @see #mapIfNegative(long, LongFunction)
    /// @see #mapIfNegativeOrElse(long, LongFunction, Object)
    /// @see #mapNonNullIfNegativeOrElse(long, LongFunction, Object)
    /// @see #mapIfNegativeOrElseGet(long, LongFunction, Supplier)
    /// @see #mapNonNullIfNegativeOrElseGet(long, LongFunction, Supplier)
    ///
    public static <R> @Nullable R mapNonNullIfNegative(long value, LongFunction<R> mapper) {
        return value < 0 ? Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") : null;
    }
    
    ///
    /// Maps the given long value to a new value using the given mapper function if the value is negative.
    /// If the `value` is not negative, the `orElse` value is returned.
    ///
    /// The mapper function and the `orElse` value may be `null`.
    ///
    /// @param value  The long value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is not negative.
    ///
    /// @return The mapped value, or `orElse` if the `value` is not negative.
    ///
    /// @see #mapIfNegative(long, LongFunction)
    /// @see #mapNonNullIfNegative(long, LongFunction)
    /// @see #mapNonNullIfNegativeOrElse(long, LongFunction, Object)
    /// @see #mapIfNegativeOrElseGet(long, LongFunction, Supplier)
    /// @see #mapNonNullIfNegativeOrElseGet(long, LongFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNegativeOrElse(long value, LongFunction<@Nullable R> mapper, @Nullable R orElse) {
        return value < 0 ? mapper.apply(value) : orElse;
    }
    
    ///
    /// Maps the given long value to a new value using the given mapper function if the value is negative.
    /// If the `value` is not negative, the `orElse` value is returned.
    ///
    /// Both the mapper function and the `orElse` value must not return `null`.
    ///
    /// @param value  The long value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is not negative.
    ///
    /// @return The mapped value, or `orElse` if the `value` is not negative.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` value is `null`.
    /// @see #mapIfNegative(long, LongFunction)
    /// @see #mapNonNullIfNegative(long, LongFunction)
    /// @see #mapIfNegativeOrElse(long, LongFunction, Object)
    /// @see #mapIfNegativeOrElseGet(long, LongFunction, Supplier)
    /// @see #mapNonNullIfNegativeOrElseGet(long, LongFunction, Supplier)
    ///
    public static <R> R mapNonNullIfNegativeOrElse(long value, LongFunction<R> mapper, R orElse) {
        return value < 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse);
    }
    
    ///
    /// Maps the given long value to a new value using the given mapper function if the value is negative.
    /// If the `value` is not negative, the `orElse` supplier's value is returned.
    ///
    /// The mapper function and the `orElse` supplier may be `null`.
    ///
    /// @param value  The long value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is not negative.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is not negative.
    ///
    /// @see #mapIfNegative(long, LongFunction)
    /// @see #mapNonNullIfNegative(long, LongFunction)
    /// @see #mapIfNegativeOrElse(long, LongFunction, Object)
    /// @see #mapNonNullIfNegativeOrElse(long, LongFunction, Object)
    /// @see #mapNonNullIfNegativeOrElseGet(long, LongFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNegativeOrElseGet(long value, LongFunction<@Nullable R> mapper, Supplier<@Nullable R> orElse) {
        return value < 0 ? mapper.apply(value) : orElse.get();
    }
    
    ///
    /// Maps the given long value to a new value using the given mapper function if the value is negative.
    /// If the `value` is not negative, the `orElse` supplier's value is returned.
    ///
    /// Both the mapper function and the `orElse` supplier must not return `null`.
    ///
    /// @param value  The long value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is not negative.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is not negative.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` supplier returns `null`.
    /// @see #mapIfNegative(long, LongFunction)
    /// @see #mapNonNullIfNegative(long, LongFunction)
    /// @see #mapIfNegativeOrElse(long, LongFunction, Object)
    /// @see #mapNonNullIfNegativeOrElse(long, LongFunction, Object)
    /// @see #mapIfNegativeOrElseGet(long, LongFunction, Supplier)
    ///
    public static <R> R mapNonNullIfNegativeOrElseGet(long value, LongFunction<R> mapper, Supplier<R> orElse) {
        return value < 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse.get());
    }
    
    // ========== NOT POSITIVE ==========
    
    ///
    /// Maps the given long value to a new value using the given mapper function if the value is not positive.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value  The long value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is positive.
    ///
    /// @see #mapNonNullIfNotPositive(long, LongFunction)
    /// @see #mapIfNotPositiveOrElse(long, LongFunction, Object)
    /// @see #mapNonNullIfNotPositiveOrElse(long, LongFunction, Object)
    /// @see #mapIfNotPositiveOrElseGet(long, LongFunction, Supplier)
    /// @see #mapNonNullIfNotPositiveOrElseGet(long, LongFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNotPositive(long value, LongFunction<@Nullable R> mapper) {
        return value <= 0 ? mapper.apply(value) : null;
    }
    
    ///
    /// Maps the given long value to a new value using the given mapper function if the value is not positive.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value  The long value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is positive.
    ///
    /// @throws NullPointerException If the mapper function returns `null`.
    /// @see #mapIfNotPositive(long, LongFunction)
    /// @see #mapIfNotPositiveOrElse(long, LongFunction, Object)
    /// @see #mapNonNullIfNotPositiveOrElse(long, LongFunction, Object)
    /// @see #mapIfNotPositiveOrElseGet(long, LongFunction, Supplier)
    /// @see #mapNonNullIfNotPositiveOrElseGet(long, LongFunction, Supplier)
    ///
    public static <R> @Nullable R mapNonNullIfNotPositive(long value, LongFunction<R> mapper) {
        return value <= 0 ? Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") : null;
    }
    
    ///
    /// Maps the given long value to a new value using the given mapper function if the value is not positive.
    /// If the `value` is positive, the `orElse` value is returned.
    ///
    /// The mapper function and the `orElse` value may be `null`.
    ///
    /// @param value  The long value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is positive.
    ///
    /// @return The mapped value, or `orElse` if the `value` is positive.
    ///
    /// @see #mapIfNotPositive(long, LongFunction)
    /// @see #mapNonNullIfNotPositive(long, LongFunction)
    /// @see #mapNonNullIfNotPositiveOrElse(long, LongFunction, Object)
    /// @see #mapIfNotPositiveOrElseGet(long, LongFunction, Supplier)
    /// @see #mapNonNullIfNotPositiveOrElseGet(long, LongFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNotPositiveOrElse(long value, LongFunction<@Nullable R> mapper, @Nullable R orElse) {
        return value <= 0 ? mapper.apply(value) : orElse;
    }
    
    ///
    /// Maps the given long value to a new value using the given mapper function if the value is not positive.
    /// If the `value` is positive, the `orElse` value is returned.
    ///
    /// Both the mapper function and the `orElse` value must not return `null`.
    ///
    /// @param value  The long value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is positive.
    ///
    /// @return The mapped value, or `orElse` if the `value` is positive.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` value is `null`.
    /// @see #mapIfNotPositive(long, LongFunction)
    /// @see #mapNonNullIfNotPositive(long, LongFunction)
    /// @see #mapIfNotPositiveOrElse(long, LongFunction, Object)
    /// @see #mapIfNotPositiveOrElseGet(long, LongFunction, Supplier)
    /// @see #mapNonNullIfNotPositiveOrElseGet(long, LongFunction, Supplier)
    ///
    public static <R> R mapNonNullIfNotPositiveOrElse(long value, LongFunction<R> mapper, R orElse) {
        return value <= 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse);
    }
    
    ///
    /// Maps the given long value to a new value using the given mapper function if the value is not positive.
    /// If the `value` is positive, the `orElse` supplier's value is returned.
    ///
    /// The mapper function and the `orElse` supplier may be `null`.
    ///
    /// @param value  The long value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is positive.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is positive.
    ///
    /// @see #mapIfNotPositive(long, LongFunction)
    /// @see #mapNonNullIfNotPositive(long, LongFunction)
    /// @see #mapIfNotPositiveOrElse(long, LongFunction, Object)
    /// @see #mapNonNullIfNotPositiveOrElse(long, LongFunction, Object)
    /// @see #mapNonNullIfNotPositiveOrElseGet(long, LongFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNotPositiveOrElseGet(long value, LongFunction<@Nullable R> mapper, Supplier<@Nullable R> orElse) {
        return value <= 0 ? mapper.apply(value) : orElse.get();
    }
    
    ///
    /// Maps the given long value to a new value using the given mapper function if the value is not positive.
    /// If the `value` is positive, the `orElse` supplier's value is returned.
    ///
    /// Both the mapper function and the `orElse` supplier must not return `null`.
    ///
    /// @param value  The long value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is positive.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is positive.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` supplier returns `null`.
    /// @see #mapIfNotPositive(long, LongFunction)
    /// @see #mapNonNullIfNotPositive(long, LongFunction)
    /// @see #mapIfNotPositiveOrElse(long, LongFunction, Object)
    /// @see #mapNonNullIfNotPositiveOrElse(long, LongFunction, Object)
    /// @see #mapIfNotPositiveOrElseGet(long, LongFunction, Supplier)
    ///
    public static <R> R mapNonNullIfNotPositiveOrElseGet(long value, LongFunction<R> mapper, Supplier<R> orElse) {
        return value <= 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse.get());
    }
    
    // ========== NOT NEGATIVE ==========
    
    ///
    /// Maps the given long value to a new value using the given mapper function if the value is not negative.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value  The long value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is negative.
    ///
    /// @see #mapNonNullIfNotNegative(long, LongFunction)
    /// @see #mapIfNotNegativeOrElse(long, LongFunction, Object)
    /// @see #mapNonNullIfNotNegativeOrElse(long, LongFunction, Object)
    /// @see #mapIfNotNegativeOrElseGet(long, LongFunction, Supplier)
    /// @see #mapNonNullIfNotNegativeOrElseGet(long, LongFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNotNegative(long value, LongFunction<@Nullable R> mapper) {
        return value >= 0 ? mapper.apply(value) : null;
    }
    
    ///
    /// Maps the given long value to a new value using the given mapper function if the value is not negative.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value  The long value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is negative.
    ///
    /// @throws NullPointerException If the mapper function returns `null`.
    /// @see #mapIfNotNegative(long, LongFunction)
    /// @see #mapIfNotNegativeOrElse(long, LongFunction, Object)
    /// @see #mapNonNullIfNotNegativeOrElse(long, LongFunction, Object)
    /// @see #mapIfNotNegativeOrElseGet(long, LongFunction, Supplier)
    /// @see #mapNonNullIfNotNegativeOrElseGet(long, LongFunction, Supplier)
    ///
    public static <R> @Nullable R mapNonNullIfNotNegative(long value, LongFunction<R> mapper) {
        return value >= 0 ? Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") : null;
    }
    
    ///
    /// Maps the given long value to a new value using the given mapper function if the value is not negative.
    /// If the `value` is negative, the `orElse` value is returned.
    ///
    /// The mapper function and the `orElse` value may be `null`.
    ///
    /// @param value  The long value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is negative.
    ///
    /// @return The mapped value, or `orElse` if the `value` is negative.
    ///
    /// @see #mapIfNotNegative(long, LongFunction)
    /// @see #mapNonNullIfNotNegative(long, LongFunction)
    /// @see #mapNonNullIfNotNegativeOrElse(long, LongFunction, Object)
    /// @see #mapIfNotNegativeOrElseGet(long, LongFunction, Supplier)
    /// @see #mapNonNullIfNotNegativeOrElseGet(long, LongFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNotNegativeOrElse(long value, LongFunction<@Nullable R> mapper, @Nullable R orElse) {
        return value >= 0 ? mapper.apply(value) : orElse;
    }
    
    ///
    /// Maps the given long value to a new value using the given mapper function if the value is not negative.
    /// If the `value` is negative, the `orElse` value is returned.
    ///
    /// Both the mapper function and the `orElse` value must not return `null`.
    ///
    /// @param value  The long value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is negative.
    ///
    /// @return The mapped value, or `orElse` if the `value` is negative.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` value is `null`.
    /// @see #mapIfNotNegative(long, LongFunction)
    /// @see #mapNonNullIfNotNegative(long, LongFunction)
    /// @see #mapIfNotNegativeOrElse(long, LongFunction, Object)
    /// @see #mapIfNotNegativeOrElseGet(long, LongFunction, Supplier)
    /// @see #mapNonNullIfNotNegativeOrElseGet(long, LongFunction, Supplier)
    ///
    public static <R> R mapNonNullIfNotNegativeOrElse(long value, LongFunction<R> mapper, R orElse) {
        return value >= 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse);
    }
    
    ///
    /// Maps the given long value to a new value using the given mapper function if the value is not negative.
    /// If the `value` is negative, the `orElse` supplier's value is returned.
    ///
    /// The mapper function and the `orElse` supplier may be `null`.
    ///
    /// @param value  The long value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is negative.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is negative.
    ///
    /// @see #mapIfNotNegative(long, LongFunction)
    /// @see #mapNonNullIfNotNegative(long, LongFunction)
    /// @see #mapIfNotNegativeOrElse(long, LongFunction, Object)
    /// @see #mapNonNullIfNotNegativeOrElse(long, LongFunction, Object)
    /// @see #mapNonNullIfNotNegativeOrElseGet(long, LongFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNotNegativeOrElseGet(long value, LongFunction<@Nullable R> mapper, Supplier<@Nullable R> orElse) {
        return value >= 0 ? mapper.apply(value) : orElse.get();
    }
    
    ///
    /// Maps the given long value to a new value using the given mapper function if the value is not negative.
    /// If the `value` is negative, the `orElse` supplier's value is returned.
    ///
    /// Both the mapper function and the `orElse` supplier must not return `null`.
    ///
    /// @param value  The long value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is negative.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is negative.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` supplier returns `null`.
    /// @see #mapIfNotNegative(long, LongFunction)
    /// @see #mapNonNullIfNotNegative(long, LongFunction)
    /// @see #mapIfNotNegativeOrElse(long, LongFunction, Object)
    /// @see #mapNonNullIfNotNegativeOrElse(long, LongFunction, Object)
    /// @see #mapIfNotNegativeOrElseGet(long, LongFunction, Supplier)
    ///
    public static <R> R mapNonNullIfNotNegativeOrElseGet(long value, LongFunction<R> mapper, Supplier<R> orElse) {
        return value >= 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse.get());
    }
    
    // ========== ZERO ==========
    
    ///
    /// Maps the given long value to a new value using the given mapper function if the value is zero.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value  The long value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is not zero.
    ///
    /// @see #mapNonNullIfZero(long, LongFunction)
    /// @see #mapIfZeroOrElse(long, LongFunction, Object)
    /// @see #mapNonNullIfZeroOrElse(long, LongFunction, Object)
    /// @see #mapIfZeroOrElseGet(long, LongFunction, Supplier)
    /// @see #mapNonNullIfZeroOrElseGet(long, LongFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfZero(long value, LongFunction<@Nullable R> mapper) {
        return value == 0 ? mapper.apply(value) : null;
    }
    
    ///
    /// Maps the given long value to a new value using the given mapper function if the value is zero.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value  The long value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is not zero.
    ///
    /// @throws NullPointerException If the mapper function returns `null`.
    /// @see #mapIfZero(long, LongFunction)
    /// @see #mapIfZeroOrElse(long, LongFunction, Object)
    /// @see #mapNonNullIfZeroOrElse(long, LongFunction, Object)
    /// @see #mapIfZeroOrElseGet(long, LongFunction, Supplier)
    /// @see #mapNonNullIfZeroOrElseGet(long, LongFunction, Supplier)
    ///
    public static <R> @Nullable R mapNonNullIfZero(long value, LongFunction<R> mapper) {
        return value == 0 ? Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") : null;
    }
    
    ///
    /// Maps the given long value to a new value using the given mapper function if the value is zero.
    /// If the `value` is not zero, the `orElse` value is returned.
    ///
    /// The mapper function and the `orElse` value may be `null`.
    ///
    /// @param value  The long value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is not zero.
    ///
    /// @return The mapped value, or `orElse` if the `value` is not zero.
    ///
    /// @see #mapIfZero(long, LongFunction)
    /// @see #mapNonNullIfZero(long, LongFunction)
    /// @see #mapNonNullIfZeroOrElse(long, LongFunction, Object)
    /// @see #mapIfZeroOrElseGet(long, LongFunction, Supplier)
    /// @see #mapNonNullIfZeroOrElseGet(long, LongFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfZeroOrElse(long value, LongFunction<@Nullable R> mapper, @Nullable R orElse) {
        return value == 0 ? mapper.apply(value) : orElse;
    }
    
    ///
    /// Maps the given long value to a new value using the given mapper function if the value is zero.
    /// If the `value` is not zero, the `orElse` value is returned.
    ///
    /// Both the mapper function and the `orElse` value must not return `null`.
    ///
    /// @param value  The long value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is not zero.
    ///
    /// @return The mapped value, or `orElse` if the `value` is not zero.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` value is `null`.
    /// @see #mapIfZero(long, LongFunction)
    /// @see #mapNonNullIfZero(long, LongFunction)
    /// @see #mapIfZeroOrElse(long, LongFunction, Object)
    /// @see #mapIfZeroOrElseGet(long, LongFunction, Supplier)
    /// @see #mapNonNullIfZeroOrElseGet(long, LongFunction, Supplier)
    ///
    public static <R> R mapNonNullIfZeroOrElse(long value, LongFunction<R> mapper, R orElse) {
        return value == 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse);
    }
    
    ///
    /// Maps the given long value to a new value using the given mapper function if the value is zero.
    /// If the `value` is not zero, the `orElse` supplier's value is returned.
    ///
    /// The mapper function and the `orElse` supplier may be `null`.
    ///
    /// @param value  The long value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is not zero.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is not zero.
    ///
    /// @see #mapIfZero(long, LongFunction)
    /// @see #mapNonNullIfZero(long, LongFunction)
    /// @see #mapIfZeroOrElse(long, LongFunction, Object)
    /// @see #mapNonNullIfZeroOrElse(long, LongFunction, Object)
    /// @see #mapNonNullIfZeroOrElseGet(long, LongFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfZeroOrElseGet(long value, LongFunction<@Nullable R> mapper, Supplier<@Nullable R> orElse) {
        return value == 0 ? mapper.apply(value) : orElse.get();
    }
    
    ///
    /// Maps the given long value to a new value using the given mapper function if the value is zero.
    /// If the `value` is not zero, the `orElse` supplier's value is returned.
    ///
    /// Both the mapper function and the `orElse` supplier must not return `null`.
    ///
    /// @param value  The long value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is not zero.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is not zero.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` supplier returns `null`.
    /// @see #mapIfZero(long, LongFunction)
    /// @see #mapNonNullIfZero(long, LongFunction)
    /// @see #mapIfZeroOrElse(long, LongFunction, Object)
    /// @see #mapNonNullIfZeroOrElse(long, LongFunction, Object)
    /// @see #mapIfZeroOrElseGet(long, LongFunction, Supplier)
    ///
    public static <R> R mapNonNullIfZeroOrElseGet(long value, LongFunction<R> mapper, Supplier<R> orElse) {
        return value == 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse.get());
    }
    
    // ========== NOT ZERO ==========
    
    ///
    /// Maps the given long value to a new value using the given mapper function if the value is not zero.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value  The long value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is zero.
    ///
    /// @see #mapNonNullIfNotZero(long, LongFunction)
    /// @see #mapIfNotZeroOrElse(long, LongFunction, Object)
    /// @see #mapNonNullIfNotZeroOrElse(long, LongFunction, Object)
    /// @see #mapIfNotZeroOrElseGet(long, LongFunction, Supplier)
    /// @see #mapNonNullIfNotZeroOrElseGet(long, LongFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNotZero(long value, LongFunction<@Nullable R> mapper) {
        return value != 0 ? mapper.apply(value) : null;
    }
    
    ///
    /// Maps the given long value to a new value using the given mapper function if the value is not zero.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value  The long value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is zero.
    ///
    /// @throws NullPointerException If the mapper function returns `null`.
    /// @see #mapIfNotZero(long, LongFunction)
    /// @see #mapIfNotZeroOrElse(long, LongFunction, Object)
    /// @see #mapNonNullIfNotZeroOrElse(long, LongFunction, Object)
    /// @see #mapIfNotZeroOrElseGet(long, LongFunction, Supplier)
    /// @see #mapNonNullIfNotZeroOrElseGet(long, LongFunction, Supplier)
    ///
    public static <R> @Nullable R mapNonNullIfNotZero(long value, LongFunction<R> mapper) {
        return value != 0 ? Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") : null;
    }
    
    ///
    /// Maps the given long value to a new value using the given mapper function if the value is not zero.
    /// If the `value` is zero, the `orElse` value is returned.
    ///
    /// The mapper function and the `orElse` value may be `null`.
    ///
    /// @param value  The long value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is zero.
    ///
    /// @return The mapped value, or `orElse` if the `value` is zero.
    ///
    /// @see #mapIfNotZero(long, LongFunction)
    /// @see #mapNonNullIfNotZero(long, LongFunction)
    /// @see #mapNonNullIfNotZeroOrElse(long, LongFunction, Object)
    /// @see #mapIfNotZeroOrElseGet(long, LongFunction, Supplier)
    /// @see #mapNonNullIfNotZeroOrElseGet(long, LongFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNotZeroOrElse(long value, LongFunction<@Nullable R> mapper, @Nullable R orElse) {
        return value != 0 ? mapper.apply(value) : orElse;
    }
    
    ///
    /// Maps the given long value to a new value using the given mapper function if the value is not zero.
    /// If the `value` is zero, the `orElse` value is returned.
    ///
    /// Both the mapper function and the `orElse` value must not return `null`.
    ///
    /// @param value  The long value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is zero.
    ///
    /// @return The mapped value, or `orElse` if the `value` is zero.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` value is `null`.
    /// @see #mapIfNotZero(long, LongFunction)
    /// @see #mapNonNullIfNotZero(long, LongFunction)
    /// @see #mapIfNotZeroOrElse(long, LongFunction, Object)
    /// @see #mapIfNotZeroOrElseGet(long, LongFunction, Supplier)
    /// @see #mapNonNullIfNotZeroOrElseGet(long, LongFunction, Supplier)
    ///
    public static <R> R mapNonNullIfNotZeroOrElse(long value, LongFunction<R> mapper, R orElse) {
        return value != 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse);
    }
    
    ///
    /// Maps the given long value to a new value using the given mapper function if the value is not zero.
    /// If the `value` is zero, the `orElse` supplier's value is returned.
    ///
    /// The mapper function and the `orElse` supplier may be `null`.
    ///
    /// @param value  The long value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is zero.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is zero.
    ///
    /// @see #mapIfNotZero(long, LongFunction)
    /// @see #mapNonNullIfNotZero(long, LongFunction)
    /// @see #mapIfNotZeroOrElse(long, LongFunction, Object)
    /// @see #mapNonNullIfNotZeroOrElse(long, LongFunction, Object)
    /// @see #mapNonNullIfNotZeroOrElseGet(long, LongFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNotZeroOrElseGet(long value, LongFunction<@Nullable R> mapper, Supplier<@Nullable R> orElse) {
        return value != 0 ? mapper.apply(value) : orElse.get();
    }
    
    ///
    /// Maps the given long value to a new value using the given mapper function if the value is not zero.
    /// If the `value` is zero, the `orElse` supplier's value is returned.
    ///
    /// Both the mapper function and the `orElse` supplier must not return `null`.
    ///
    /// @param value  The long value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is zero.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is zero.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` supplier returns `null`.
    /// @see #mapIfNotZero(long, LongFunction)
    /// @see #mapNonNullIfNotZero(long, LongFunction)
    /// @see #mapIfNotZeroOrElse(long, LongFunction, Object)
    /// @see #mapNonNullIfNotZeroOrElse(long, LongFunction, Object)
    /// @see #mapIfNotZeroOrElseGet(long, LongFunction, Supplier)
    ///
    public static <R> R mapNonNullIfNotZeroOrElseGet(long value, LongFunction<R> mapper, Supplier<R> orElse) {
        return value != 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse.get());
    }
    
    // ========== IN RANGE ==========
    
    ///
    /// Maps the given long value to a new value using the given mapper function if the value is within the given range.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value  The long value to map.
    /// @param min    The lower bound of the range, inclusive.
    /// @param max    The upper bound of the range, inclusive.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is outside the given range.
    ///
    /// @see #mapNonNullIfInRange(long, long, long, LongFunction)
    /// @see #mapIfInRangeOrElse(long, long, long, LongFunction, Object)
    /// @see #mapNonNullIfInRangeOrElse(long, long, long, LongFunction, Object)
    /// @see #mapIfInRangeOrElseGet(long, long, long, LongFunction, Supplier)
    /// @see #mapNonNullIfInRangeOrElseGet(long, long, long, LongFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfInRange(long value, long min, long max, LongFunction<@Nullable R> mapper) {
        return (value >= min && value <= max) ? mapper.apply(value) : null;
    }
    
    ///
    /// Maps the given long value to a new value using the given mapper function if the value is within the given range.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value  The long value to map.
    /// @param min    The lower bound of the range, inclusive.
    /// @param max    The upper bound of the range, inclusive.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is outside the given range.
    ///
    /// @throws NullPointerException If the mapper function returns `null`.
    /// @see #mapIfInRange(long, long, long, LongFunction)
    /// @see #mapIfInRangeOrElse(long, long, long, LongFunction, Object)
    /// @see #mapNonNullIfInRangeOrElse(long, long, long, LongFunction, Object)
    /// @see #mapIfInRangeOrElseGet(long, long, long, LongFunction, Supplier)
    /// @see #mapNonNullIfInRangeOrElseGet(long, long, long, LongFunction, Supplier)
    ///
    public static <R> @Nullable R mapNonNullIfInRange(long value, long min, long max, LongFunction<R> mapper) {
        return (value >= min && value <= max) ? Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") : null;
    }
    
    ///
    /// Maps the given long value to a new value using the given mapper function if the value is within the given range.
    /// If the `value` is outside the given range, the `orElse` value is returned.
    ///
    /// The mapper function and the `orElse` value may be `null`.
    ///
    /// @param value  The long value to map.
    /// @param min    The lower bound of the range, inclusive.
    /// @param max    The upper bound of the range, inclusive.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is outside the given range.
    ///
    /// @return The mapped value, or `orElse` if the `value` is outside the given range.
    ///
    /// @see #mapIfInRange(long, long, long, LongFunction)
    /// @see #mapNonNullIfInRange(long, long, long, LongFunction)
    /// @see #mapNonNullIfInRangeOrElse(long, long, long, LongFunction, Object)
    /// @see #mapIfInRangeOrElseGet(long, long, long, LongFunction, Supplier)
    /// @see #mapNonNullIfInRangeOrElseGet(long, long, long, LongFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfInRangeOrElse(long value, long min, long max, LongFunction<@Nullable R> mapper, @Nullable R orElse) {
        return (value >= min && value <= max) ? mapper.apply(value) : orElse;
    }
    
    ///
    /// Maps the given long value to a new value using the given mapper function if the value is within the given range.
    /// If the `value` is outside the given range, the `orElse` value is returned.
    ///
    /// Both the mapper function and the `orElse` value must not return `null`.
    ///
    /// @param value  The long value to map.
    /// @param min    The lower bound of the range, inclusive.
    /// @param max    The upper bound of the range, inclusive.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is outside the given range.
    ///
    /// @return The mapped value, or `orElse` if the `value` is outside the given range.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` value is `null`.
    /// @see #mapIfInRange(long, long, long, LongFunction)
    /// @see #mapNonNullIfInRange(long, long, long, LongFunction)
    /// @see #mapIfInRangeOrElse(long, long, long, LongFunction, Object)
    /// @see #mapIfInRangeOrElseGet(long, long, long, LongFunction, Supplier)
    /// @see #mapNonNullIfInRangeOrElseGet(long, long, long, LongFunction, Supplier)
    ///
    public static <R> R mapNonNullIfInRangeOrElse(long value, long min, long max, LongFunction<R> mapper, R orElse) {
        return (value >= min && value <= max) ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse);
    }
    
    ///
    /// Maps the given long value to a new value using the given mapper function if the value is within the given range.
    /// If the `value` is outside the given range, the `orElse` supplier's value is returned.
    ///
    /// The mapper function and the `orElse` supplier may be `null`.
    ///
    /// @param value  The long value to map.
    /// @param min    The lower bound of the range, inclusive.
    /// @param max    The upper bound of the range, inclusive.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is outside the given range.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is outside the given range.
    ///
    /// @see #mapIfInRange(long, long, long, LongFunction)
    /// @see #mapNonNullIfInRange(long, long, long, LongFunction)
    /// @see #mapIfInRangeOrElse(long, long, long, LongFunction, Object)
    /// @see #mapNonNullIfInRangeOrElse(long, long, long, LongFunction, Object)
    /// @see #mapNonNullIfInRangeOrElseGet(long, long, long, LongFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfInRangeOrElseGet(long value, long min, long max, LongFunction<@Nullable R> mapper, Supplier<@Nullable R> orElse) {
        return (value >= min && value <= max) ? mapper.apply(value) : orElse.get();
    }
    
    ///
    /// Maps the given long value to a new value using the given mapper function if the value is within the given range.
    /// If the `value` is outside the given range, the `orElse` supplier's value is returned.
    ///
    /// Both the mapper function and the `orElse` supplier must not return `null`.
    ///
    /// @param value  The long value to map.
    /// @param min    The lower bound of the range, inclusive.
    /// @param max    The upper bound of the range, inclusive.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is outside the given range.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is outside the given range.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` supplier returns `null`.
    /// @see #mapIfInRange(long, long, long, LongFunction)
    /// @see #mapNonNullIfInRange(long, long, long, LongFunction)
    /// @see #mapIfInRangeOrElse(long, long, long, LongFunction, Object)
    /// @see #mapNonNullIfInRangeOrElse(long, long, long, LongFunction, Object)
    /// @see #mapIfInRangeOrElseGet(long, long, long, LongFunction, Supplier)
    ///
    public static <R> R mapNonNullIfInRangeOrElseGet(long value, long min, long max, LongFunction<R> mapper, Supplier<R> orElse) {
        return (value >= min && value <= max) ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse.get());
    }
}
