package dev.satherov.zelqro.mapping;

import lombok.experimental.UtilityClass;

import dev.satherov.zelqro.function.IntFunction;
import dev.satherov.zelqro.predicate.IntPredicate;

import org.jspecify.annotations.Nullable;

import java.util.Objects;
import java.util.function.Supplier;

@UtilityClass
public class IntMapping {
    
    // ========== PREDICATE ==========
    
    ///
    /// Maps the given int value to a new value using the given mapper function if the predicate returns `true`.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value     The int value to map.
    /// @param predicate The predicate to check against.
    /// @param mapper    The mapper function.
    ///
    /// @return The mapped value, or `null` if the predicate returns `false`.
    ///
    /// @see #mapNonNullIf(int, IntPredicate, IntFunction)
    /// @see #mapIfOrElse(int, IntPredicate, IntFunction, Object)
    /// @see #mapNonNullIfOrElse(int, IntPredicate, IntFunction, Object)
    /// @see #mapIfOrElseGet(int, IntPredicate, IntFunction, Supplier)
    /// @see #mapNonNullIfOrElseGet(int, IntPredicate, IntFunction, Supplier)
    ///
    public static <R> @Nullable R mapIf(int value, IntPredicate predicate, IntFunction<@Nullable R> mapper) {
        return predicate.test(value) ? mapper.apply(value) : null;
    }
    
    ///
    /// Maps the given int value to a new value using the given mapper function if the predicate returns `true`.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value     The int value to map.
    /// @param predicate The predicate to check against.
    /// @param mapper    The mapper function.
    ///
    /// @return The mapped value, or `null` if the predicate returns `false`.
    ///
    /// @throws NullPointerException If the mapper function returns `null`.
    /// @see #mapIf(int, IntPredicate, IntFunction)
    /// @see #mapIfOrElse(int, IntPredicate, IntFunction, Object)
    /// @see #mapNonNullIfOrElse(int, IntPredicate, IntFunction, Object)
    /// @see #mapIfOrElseGet(int, IntPredicate, IntFunction, Supplier)
    /// @see #mapNonNullIfOrElseGet(int, IntPredicate, IntFunction, Supplier)
    ///
    public static <R> @Nullable R mapNonNullIf(int value, IntPredicate predicate, IntFunction<R> mapper) {
        return predicate.test(value) ? Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") : null;
    }
    
    ///
    /// Maps the given int value to a new value using the given mapper function if the predicate returns `true`.
    /// If the predicate returns `false`, the `orElse` value is returned.
    ///
    /// The mapper function and the `orElse` value may be `null`.
    ///
    /// @param value     The int value to map.
    /// @param predicate The predicate to check against.
    /// @param mapper    The mapper function.
    /// @param orElse    The value to return if the predicate returns `false`.
    ///
    /// @return The mapped value, or `orElse` if the predicate returns `false`.
    ///
    /// @see #mapIf(int, IntPredicate, IntFunction)
    /// @see #mapNonNullIf(int, IntPredicate, IntFunction)
    /// @see #mapNonNullIfOrElse(int, IntPredicate, IntFunction, Object)
    /// @see #mapIfOrElseGet(int, IntPredicate, IntFunction, Supplier)
    /// @see #mapNonNullIfOrElseGet(int, IntPredicate, IntFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfOrElse(int value, IntPredicate predicate, IntFunction<@Nullable R> mapper, @Nullable R orElse) {
        return predicate.test(value) ? mapper.apply(value) : orElse;
    }
    
    ///
    /// Maps the given int value to a new value using the given mapper function if the predicate returns `true`.
    /// If the predicate returns `false`, the `orElse` value is returned.
    ///
    /// Both the mapper function and the `orElse` value must not return `null`.
    ///
    /// @param value     The int value to map.
    /// @param predicate The predicate to check against.
    /// @param mapper    The mapper function.
    /// @param orElse    The value to return if the predicate returns `false`.
    ///
    /// @return The mapped value, or `orElse` if the predicate returns `false`.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` value is `null`.
    /// @see #mapIf(int, IntPredicate, IntFunction)
    /// @see #mapNonNullIf(int, IntPredicate, IntFunction)
    /// @see #mapIfOrElse(int, IntPredicate, IntFunction, Object)
    /// @see #mapIfOrElseGet(int, IntPredicate, IntFunction, Supplier)
    /// @see #mapNonNullIfOrElseGet(int, IntPredicate, IntFunction, Supplier)
    ///
    public static <R> R mapNonNullIfOrElse(int value, IntPredicate predicate, IntFunction<R> mapper, R orElse) {
        return predicate.test(value) ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse);
    }
    
    ///
    /// Maps the given int value to a new value using the given mapper function if the predicate returns `true`.
    /// If the predicate returns `false`, the `orElse` supplier's value is returned.
    ///
    /// The mapper function and the `orElse` supplier may be `null`.
    ///
    /// @param value     The int value to map.
    /// @param predicate The predicate to check against.
    /// @param mapper    The mapper function.
    /// @param orElse    The supplier of the value to return if the predicate returns `false`.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the predicate returns `false`.
    ///
    /// @see #mapIf(int, IntPredicate, IntFunction)
    /// @see #mapNonNullIf(int, IntPredicate, IntFunction)
    /// @see #mapIfOrElse(int, IntPredicate, IntFunction, Object)
    /// @see #mapNonNullIfOrElse(int, IntPredicate, IntFunction, Object)
    /// @see #mapNonNullIfOrElseGet(int, IntPredicate, IntFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfOrElseGet(int value, IntPredicate predicate, IntFunction<@Nullable R> mapper, Supplier<@Nullable R> orElse) {
        return predicate.test(value) ? mapper.apply(value) : orElse.get();
    }
    
    ///
    /// Maps the given int value to a new value using the given mapper function if the predicate returns `true`.
    /// If the predicate returns `false`, the `orElse` supplier's value is returned.
    ///
    /// Both the mapper function and the `orElse` supplier must not return `null`.
    ///
    /// @param value     The int value to map.
    /// @param predicate The predicate to check against.
    /// @param mapper    The mapper function.
    /// @param orElse    The supplier of the value to return if the predicate returns `false`.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the predicate returns `false`.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` supplier returns `null`.
    /// @see #mapIf(int, IntPredicate, IntFunction)
    /// @see #mapNonNullIf(int, IntPredicate, IntFunction)
    /// @see #mapIfOrElse(int, IntPredicate, IntFunction, Object)
    /// @see #mapNonNullIfOrElse(int, IntPredicate, IntFunction, Object)
    /// @see #mapIfOrElseGet(int, IntPredicate, IntFunction, Supplier)
    ///
    public static <R> R mapNonNullIfOrElseGet(int value, IntPredicate predicate, IntFunction<R> mapper, Supplier<R> orElse) {
        return predicate.test(value) ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse.get());
    }
    
    // ========== POSITIVE ==========
    
    ///
    /// Maps the given int value to a new value using the given mapper function if the value is positive.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value  The int value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is not positive.
    ///
    /// @see #mapNonNullIfPositive(int, IntFunction)
    /// @see #mapIfPositiveOrElse(int, IntFunction, Object)
    /// @see #mapNonNullIfPositiveOrElse(int, IntFunction, Object)
    /// @see #mapIfPositiveOrElseGet(int, IntFunction, Supplier)
    /// @see #mapNonNullIfPositiveOrElseGet(int, IntFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfPositive(int value, IntFunction<@Nullable R> mapper) {
        return value > 0 ? mapper.apply(value) : null;
    }
    
    ///
    /// Maps the given int value to a new value using the given mapper function if the value is positive.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value  The int value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is not positive.
    ///
    /// @throws NullPointerException If the mapper function returns `null`.
    /// @see #mapIfPositive(int, IntFunction)
    /// @see #mapIfPositiveOrElse(int, IntFunction, Object)
    /// @see #mapNonNullIfPositiveOrElse(int, IntFunction, Object)
    /// @see #mapIfPositiveOrElseGet(int, IntFunction, Supplier)
    /// @see #mapNonNullIfPositiveOrElseGet(int, IntFunction, Supplier)
    ///
    public static <R> @Nullable R mapNonNullIfPositive(int value, IntFunction<R> mapper) {
        return value > 0 ? Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") : null;
    }
    
    ///
    /// Maps the given int value to a new value using the given mapper function if the value is positive.
    /// If the `value` is not positive, the `orElse` value is returned.
    ///
    /// The mapper function and the `orElse` value may be `null`.
    ///
    /// @param value  The int value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is not positive.
    ///
    /// @return The mapped value, or `orElse` if the `value` is not positive.
    ///
    /// @see #mapIfPositive(int, IntFunction)
    /// @see #mapNonNullIfPositive(int, IntFunction)
    /// @see #mapNonNullIfPositiveOrElse(int, IntFunction, Object)
    /// @see #mapIfPositiveOrElseGet(int, IntFunction, Supplier)
    /// @see #mapNonNullIfPositiveOrElseGet(int, IntFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfPositiveOrElse(int value, IntFunction<@Nullable R> mapper, @Nullable R orElse) {
        return value > 0 ? mapper.apply(value) : orElse;
    }
    
    ///
    /// Maps the given int value to a new value using the given mapper function if the value is positive.
    /// If the `value` is not positive, the `orElse` value is returned.
    ///
    /// Both the mapper function and the `orElse` value must not return `null`.
    ///
    /// @param value  The int value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is not positive.
    ///
    /// @return The mapped value, or `orElse` if the `value` is not positive.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` value is `null`.
    /// @see #mapIfPositive(int, IntFunction)
    /// @see #mapNonNullIfPositive(int, IntFunction)
    /// @see #mapIfPositiveOrElse(int, IntFunction, Object)
    /// @see #mapIfPositiveOrElseGet(int, IntFunction, Supplier)
    /// @see #mapNonNullIfPositiveOrElseGet(int, IntFunction, Supplier)
    ///
    public static <R> R mapNonNullIfPositiveOrElse(int value, IntFunction<R> mapper, R orElse) {
        return value > 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse);
    }
    
    ///
    /// Maps the given int value to a new value using the given mapper function if the value is positive.
    /// If the `value` is not positive, the `orElse` supplier's value is returned.
    ///
    /// The mapper function and the `orElse` supplier may be `null`.
    ///
    /// @param value  The int value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is not positive.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is not positive.
    ///
    /// @see #mapIfPositive(int, IntFunction)
    /// @see #mapNonNullIfPositive(int, IntFunction)
    /// @see #mapIfPositiveOrElse(int, IntFunction, Object)
    /// @see #mapNonNullIfPositiveOrElse(int, IntFunction, Object)
    /// @see #mapNonNullIfPositiveOrElseGet(int, IntFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfPositiveOrElseGet(int value, IntFunction<@Nullable R> mapper, Supplier<@Nullable R> orElse) {
        return value > 0 ? mapper.apply(value) : orElse.get();
    }
    
    ///
    /// Maps the given int value to a new value using the given mapper function if the value is positive.
    /// If the `value` is not positive, the `orElse` supplier's value is returned.
    ///
    /// Both the mapper function and the `orElse` supplier must not return `null`.
    ///
    /// @param value  The int value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is not positive.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is not positive.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` supplier returns `null`.
    /// @see #mapIfPositive(int, IntFunction)
    /// @see #mapNonNullIfPositive(int, IntFunction)
    /// @see #mapIfPositiveOrElse(int, IntFunction, Object)
    /// @see #mapNonNullIfPositiveOrElse(int, IntFunction, Object)
    /// @see #mapIfPositiveOrElseGet(int, IntFunction, Supplier)
    ///
    public static <R> R mapNonNullIfPositiveOrElseGet(int value, IntFunction<R> mapper, Supplier<R> orElse) {
        return value > 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse.get());
    }
    
    // ========== NEGATIVE ==========
    
    ///
    /// Maps the given int value to a new value using the given mapper function if the value is negative.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value  The int value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is not negative.
    ///
    /// @see #mapNonNullIfNegative(int, IntFunction)
    /// @see #mapIfNegativeOrElse(int, IntFunction, Object)
    /// @see #mapNonNullIfNegativeOrElse(int, IntFunction, Object)
    /// @see #mapIfNegativeOrElseGet(int, IntFunction, Supplier)
    /// @see #mapNonNullIfNegativeOrElseGet(int, IntFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNegative(int value, IntFunction<@Nullable R> mapper) {
        return value < 0 ? mapper.apply(value) : null;
    }
    
    ///
    /// Maps the given int value to a new value using the given mapper function if the value is negative.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value  The int value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is not negative.
    ///
    /// @throws NullPointerException If the mapper function returns `null`.
    /// @see #mapIfNegative(int, IntFunction)
    /// @see #mapIfNegativeOrElse(int, IntFunction, Object)
    /// @see #mapNonNullIfNegativeOrElse(int, IntFunction, Object)
    /// @see #mapIfNegativeOrElseGet(int, IntFunction, Supplier)
    /// @see #mapNonNullIfNegativeOrElseGet(int, IntFunction, Supplier)
    ///
    public static <R> @Nullable R mapNonNullIfNegative(int value, IntFunction<R> mapper) {
        return value < 0 ? Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") : null;
    }
    
    ///
    /// Maps the given int value to a new value using the given mapper function if the value is negative.
    /// If the `value` is not negative, the `orElse` value is returned.
    ///
    /// The mapper function and the `orElse` value may be `null`.
    ///
    /// @param value  The int value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is not negative.
    ///
    /// @return The mapped value, or `orElse` if the `value` is not negative.
    ///
    /// @see #mapIfNegative(int, IntFunction)
    /// @see #mapNonNullIfNegative(int, IntFunction)
    /// @see #mapNonNullIfNegativeOrElse(int, IntFunction, Object)
    /// @see #mapIfNegativeOrElseGet(int, IntFunction, Supplier)
    /// @see #mapNonNullIfNegativeOrElseGet(int, IntFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNegativeOrElse(int value, IntFunction<@Nullable R> mapper, @Nullable R orElse) {
        return value < 0 ? mapper.apply(value) : orElse;
    }
    
    ///
    /// Maps the given int value to a new value using the given mapper function if the value is negative.
    /// If the `value` is not negative, the `orElse` value is returned.
    ///
    /// Both the mapper function and the `orElse` value must not return `null`.
    ///
    /// @param value  The int value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is not negative.
    ///
    /// @return The mapped value, or `orElse` if the `value` is not negative.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` value is `null`.
    /// @see #mapIfNegative(int, IntFunction)
    /// @see #mapNonNullIfNegative(int, IntFunction)
    /// @see #mapIfNegativeOrElse(int, IntFunction, Object)
    /// @see #mapIfNegativeOrElseGet(int, IntFunction, Supplier)
    /// @see #mapNonNullIfNegativeOrElseGet(int, IntFunction, Supplier)
    ///
    public static <R> R mapNonNullIfNegativeOrElse(int value, IntFunction<R> mapper, R orElse) {
        return value < 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse);
    }
    
    ///
    /// Maps the given int value to a new value using the given mapper function if the value is negative.
    /// If the `value` is not negative, the `orElse` supplier's value is returned.
    ///
    /// The mapper function and the `orElse` supplier may be `null`.
    ///
    /// @param value  The int value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is not negative.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is not negative.
    ///
    /// @see #mapIfNegative(int, IntFunction)
    /// @see #mapNonNullIfNegative(int, IntFunction)
    /// @see #mapIfNegativeOrElse(int, IntFunction, Object)
    /// @see #mapNonNullIfNegativeOrElse(int, IntFunction, Object)
    /// @see #mapNonNullIfNegativeOrElseGet(int, IntFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNegativeOrElseGet(int value, IntFunction<@Nullable R> mapper, Supplier<@Nullable R> orElse) {
        return value < 0 ? mapper.apply(value) : orElse.get();
    }
    
    ///
    /// Maps the given int value to a new value using the given mapper function if the value is negative.
    /// If the `value` is not negative, the `orElse` supplier's value is returned.
    ///
    /// Both the mapper function and the `orElse` supplier must not return `null`.
    ///
    /// @param value  The int value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is not negative.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is not negative.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` supplier returns `null`.
    /// @see #mapIfNegative(int, IntFunction)
    /// @see #mapNonNullIfNegative(int, IntFunction)
    /// @see #mapIfNegativeOrElse(int, IntFunction, Object)
    /// @see #mapNonNullIfNegativeOrElse(int, IntFunction, Object)
    /// @see #mapIfNegativeOrElseGet(int, IntFunction, Supplier)
    ///
    public static <R> R mapNonNullIfNegativeOrElseGet(int value, IntFunction<R> mapper, Supplier<R> orElse) {
        return value < 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse.get());
    }
    
    // ========== NOT POSITIVE ==========
    
    ///
    /// Maps the given int value to a new value using the given mapper function if the value is not positive.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value  The int value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is positive.
    ///
    /// @see #mapNonNullIfNotPositive(int, IntFunction)
    /// @see #mapIfNotPositiveOrElse(int, IntFunction, Object)
    /// @see #mapNonNullIfNotPositiveOrElse(int, IntFunction, Object)
    /// @see #mapIfNotPositiveOrElseGet(int, IntFunction, Supplier)
    /// @see #mapNonNullIfNotPositiveOrElseGet(int, IntFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNotPositive(int value, IntFunction<@Nullable R> mapper) {
        return value <= 0 ? mapper.apply(value) : null;
    }
    
    ///
    /// Maps the given int value to a new value using the given mapper function if the value is not positive.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value  The int value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is positive.
    ///
    /// @throws NullPointerException If the mapper function returns `null`.
    /// @see #mapIfNotPositive(int, IntFunction)
    /// @see #mapIfNotPositiveOrElse(int, IntFunction, Object)
    /// @see #mapNonNullIfNotPositiveOrElse(int, IntFunction, Object)
    /// @see #mapIfNotPositiveOrElseGet(int, IntFunction, Supplier)
    /// @see #mapNonNullIfNotPositiveOrElseGet(int, IntFunction, Supplier)
    ///
    public static <R> @Nullable R mapNonNullIfNotPositive(int value, IntFunction<R> mapper) {
        return value <= 0 ? Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") : null;
    }
    
    ///
    /// Maps the given int value to a new value using the given mapper function if the value is not positive.
    /// If the `value` is positive, the `orElse` value is returned.
    ///
    /// The mapper function and the `orElse` value may be `null`.
    ///
    /// @param value  The int value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is positive.
    ///
    /// @return The mapped value, or `orElse` if the `value` is positive.
    ///
    /// @see #mapIfNotPositive(int, IntFunction)
    /// @see #mapNonNullIfNotPositive(int, IntFunction)
    /// @see #mapNonNullIfNotPositiveOrElse(int, IntFunction, Object)
    /// @see #mapIfNotPositiveOrElseGet(int, IntFunction, Supplier)
    /// @see #mapNonNullIfNotPositiveOrElseGet(int, IntFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNotPositiveOrElse(int value, IntFunction<@Nullable R> mapper, @Nullable R orElse) {
        return value <= 0 ? mapper.apply(value) : orElse;
    }
    
    ///
    /// Maps the given int value to a new value using the given mapper function if the value is not positive.
    /// If the `value` is positive, the `orElse` value is returned.
    ///
    /// Both the mapper function and the `orElse` value must not return `null`.
    ///
    /// @param value  The int value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is positive.
    ///
    /// @return The mapped value, or `orElse` if the `value` is positive.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` value is `null`.
    /// @see #mapIfNotPositive(int, IntFunction)
    /// @see #mapNonNullIfNotPositive(int, IntFunction)
    /// @see #mapIfNotPositiveOrElse(int, IntFunction, Object)
    /// @see #mapIfNotPositiveOrElseGet(int, IntFunction, Supplier)
    /// @see #mapNonNullIfNotPositiveOrElseGet(int, IntFunction, Supplier)
    ///
    public static <R> R mapNonNullIfNotPositiveOrElse(int value, IntFunction<R> mapper, R orElse) {
        return value <= 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse);
    }
    
    ///
    /// Maps the given int value to a new value using the given mapper function if the value is not positive.
    /// If the `value` is positive, the `orElse` supplier's value is returned.
    ///
    /// The mapper function and the `orElse` supplier may be `null`.
    ///
    /// @param value  The int value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is positive.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is positive.
    ///
    /// @see #mapIfNotPositive(int, IntFunction)
    /// @see #mapNonNullIfNotPositive(int, IntFunction)
    /// @see #mapIfNotPositiveOrElse(int, IntFunction, Object)
    /// @see #mapNonNullIfNotPositiveOrElse(int, IntFunction, Object)
    /// @see #mapNonNullIfNotPositiveOrElseGet(int, IntFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNotPositiveOrElseGet(int value, IntFunction<@Nullable R> mapper, Supplier<@Nullable R> orElse) {
        return value <= 0 ? mapper.apply(value) : orElse.get();
    }
    
    ///
    /// Maps the given int value to a new value using the given mapper function if the value is not positive.
    /// If the `value` is positive, the `orElse` supplier's value is returned.
    ///
    /// Both the mapper function and the `orElse` supplier must not return `null`.
    ///
    /// @param value  The int value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is positive.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is positive.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` supplier returns `null`.
    /// @see #mapIfNotPositive(int, IntFunction)
    /// @see #mapNonNullIfNotPositive(int, IntFunction)
    /// @see #mapIfNotPositiveOrElse(int, IntFunction, Object)
    /// @see #mapNonNullIfNotPositiveOrElse(int, IntFunction, Object)
    /// @see #mapIfNotPositiveOrElseGet(int, IntFunction, Supplier)
    ///
    public static <R> R mapNonNullIfNotPositiveOrElseGet(int value, IntFunction<R> mapper, Supplier<R> orElse) {
        return value <= 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse.get());
    }
    
    // ========== NOT NEGATIVE ==========
    
    ///
    /// Maps the given int value to a new value using the given mapper function if the value is not negative.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value  The int value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is negative.
    ///
    /// @see #mapNonNullIfNotNegative(int, IntFunction)
    /// @see #mapIfNotNegativeOrElse(int, IntFunction, Object)
    /// @see #mapNonNullIfNotNegativeOrElse(int, IntFunction, Object)
    /// @see #mapIfNotNegativeOrElseGet(int, IntFunction, Supplier)
    /// @see #mapNonNullIfNotNegativeOrElseGet(int, IntFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNotNegative(int value, IntFunction<@Nullable R> mapper) {
        return value >= 0 ? mapper.apply(value) : null;
    }
    
    ///
    /// Maps the given int value to a new value using the given mapper function if the value is not negative.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value  The int value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is negative.
    ///
    /// @throws NullPointerException If the mapper function returns `null`.
    /// @see #mapIfNotNegative(int, IntFunction)
    /// @see #mapIfNotNegativeOrElse(int, IntFunction, Object)
    /// @see #mapNonNullIfNotNegativeOrElse(int, IntFunction, Object)
    /// @see #mapIfNotNegativeOrElseGet(int, IntFunction, Supplier)
    /// @see #mapNonNullIfNotNegativeOrElseGet(int, IntFunction, Supplier)
    ///
    public static <R> @Nullable R mapNonNullIfNotNegative(int value, IntFunction<R> mapper) {
        return value >= 0 ? Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") : null;
    }
    
    ///
    /// Maps the given int value to a new value using the given mapper function if the value is not negative.
    /// If the `value` is negative, the `orElse` value is returned.
    ///
    /// The mapper function and the `orElse` value may be `null`.
    ///
    /// @param value  The int value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is negative.
    ///
    /// @return The mapped value, or `orElse` if the `value` is negative.
    ///
    /// @see #mapIfNotNegative(int, IntFunction)
    /// @see #mapNonNullIfNotNegative(int, IntFunction)
    /// @see #mapNonNullIfNotNegativeOrElse(int, IntFunction, Object)
    /// @see #mapIfNotNegativeOrElseGet(int, IntFunction, Supplier)
    /// @see #mapNonNullIfNotNegativeOrElseGet(int, IntFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNotNegativeOrElse(int value, IntFunction<@Nullable R> mapper, @Nullable R orElse) {
        return value >= 0 ? mapper.apply(value) : orElse;
    }
    
    ///
    /// Maps the given int value to a new value using the given mapper function if the value is not negative.
    /// If the `value` is negative, the `orElse` value is returned.
    ///
    /// Both the mapper function and the `orElse` value must not return `null`.
    ///
    /// @param value  The int value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is negative.
    ///
    /// @return The mapped value, or `orElse` if the `value` is negative.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` value is `null`.
    /// @see #mapIfNotNegative(int, IntFunction)
    /// @see #mapNonNullIfNotNegative(int, IntFunction)
    /// @see #mapIfNotNegativeOrElse(int, IntFunction, Object)
    /// @see #mapIfNotNegativeOrElseGet(int, IntFunction, Supplier)
    /// @see #mapNonNullIfNotNegativeOrElseGet(int, IntFunction, Supplier)
    ///
    public static <R> R mapNonNullIfNotNegativeOrElse(int value, IntFunction<R> mapper, R orElse) {
        return value >= 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse);
    }
    
    ///
    /// Maps the given int value to a new value using the given mapper function if the value is not negative.
    /// If the `value` is negative, the `orElse` supplier's value is returned.
    ///
    /// The mapper function and the `orElse` supplier may be `null`.
    ///
    /// @param value  The int value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is negative.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is negative.
    ///
    /// @see #mapIfNotNegative(int, IntFunction)
    /// @see #mapNonNullIfNotNegative(int, IntFunction)
    /// @see #mapIfNotNegativeOrElse(int, IntFunction, Object)
    /// @see #mapNonNullIfNotNegativeOrElse(int, IntFunction, Object)
    /// @see #mapNonNullIfNotNegativeOrElseGet(int, IntFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNotNegativeOrElseGet(int value, IntFunction<@Nullable R> mapper, Supplier<@Nullable R> orElse) {
        return value >= 0 ? mapper.apply(value) : orElse.get();
    }
    
    ///
    /// Maps the given int value to a new value using the given mapper function if the value is not negative.
    /// If the `value` is negative, the `orElse` supplier's value is returned.
    ///
    /// Both the mapper function and the `orElse` supplier must not return `null`.
    ///
    /// @param value  The int value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is negative.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is negative.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` supplier returns `null`.
    /// @see #mapIfNotNegative(int, IntFunction)
    /// @see #mapNonNullIfNotNegative(int, IntFunction)
    /// @see #mapIfNotNegativeOrElse(int, IntFunction, Object)
    /// @see #mapNonNullIfNotNegativeOrElse(int, IntFunction, Object)
    /// @see #mapIfNotNegativeOrElseGet(int, IntFunction, Supplier)
    ///
    public static <R> R mapNonNullIfNotNegativeOrElseGet(int value, IntFunction<R> mapper, Supplier<R> orElse) {
        return value >= 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse.get());
    }
    
    // ========== ZERO ==========
    
    ///
    /// Maps the given int value to a new value using the given mapper function if the value is zero.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value  The int value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is not zero.
    ///
    /// @see #mapNonNullIfZero(int, IntFunction)
    /// @see #mapIfZeroOrElse(int, IntFunction, Object)
    /// @see #mapNonNullIfZeroOrElse(int, IntFunction, Object)
    /// @see #mapIfZeroOrElseGet(int, IntFunction, Supplier)
    /// @see #mapNonNullIfZeroOrElseGet(int, IntFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfZero(int value, IntFunction<@Nullable R> mapper) {
        return value == 0 ? mapper.apply(value) : null;
    }
    
    ///
    /// Maps the given int value to a new value using the given mapper function if the value is zero.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value  The int value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is not zero.
    ///
    /// @throws NullPointerException If the mapper function returns `null`.
    /// @see #mapIfZero(int, IntFunction)
    /// @see #mapIfZeroOrElse(int, IntFunction, Object)
    /// @see #mapNonNullIfZeroOrElse(int, IntFunction, Object)
    /// @see #mapIfZeroOrElseGet(int, IntFunction, Supplier)
    /// @see #mapNonNullIfZeroOrElseGet(int, IntFunction, Supplier)
    ///
    public static <R> @Nullable R mapNonNullIfZero(int value, IntFunction<R> mapper) {
        return value == 0 ? Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") : null;
    }
    
    ///
    /// Maps the given int value to a new value using the given mapper function if the value is zero.
    /// If the `value` is not zero, the `orElse` value is returned.
    ///
    /// The mapper function and the `orElse` value may be `null`.
    ///
    /// @param value  The int value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is not zero.
    ///
    /// @return The mapped value, or `orElse` if the `value` is not zero.
    ///
    /// @see #mapIfZero(int, IntFunction)
    /// @see #mapNonNullIfZero(int, IntFunction)
    /// @see #mapNonNullIfZeroOrElse(int, IntFunction, Object)
    /// @see #mapIfZeroOrElseGet(int, IntFunction, Supplier)
    /// @see #mapNonNullIfZeroOrElseGet(int, IntFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfZeroOrElse(int value, IntFunction<@Nullable R> mapper, @Nullable R orElse) {
        return value == 0 ? mapper.apply(value) : orElse;
    }
    
    ///
    /// Maps the given int value to a new value using the given mapper function if the value is zero.
    /// If the `value` is not zero, the `orElse` value is returned.
    ///
    /// Both the mapper function and the `orElse` value must not return `null`.
    ///
    /// @param value  The int value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is not zero.
    ///
    /// @return The mapped value, or `orElse` if the `value` is not zero.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` value is `null`.
    /// @see #mapIfZero(int, IntFunction)
    /// @see #mapNonNullIfZero(int, IntFunction)
    /// @see #mapIfZeroOrElse(int, IntFunction, Object)
    /// @see #mapIfZeroOrElseGet(int, IntFunction, Supplier)
    /// @see #mapNonNullIfZeroOrElseGet(int, IntFunction, Supplier)
    ///
    public static <R> R mapNonNullIfZeroOrElse(int value, IntFunction<R> mapper, R orElse) {
        return value == 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse);
    }
    
    ///
    /// Maps the given int value to a new value using the given mapper function if the value is zero.
    /// If the `value` is not zero, the `orElse` supplier's value is returned.
    ///
    /// The mapper function and the `orElse` supplier may be `null`.
    ///
    /// @param value  The int value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is not zero.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is not zero.
    ///
    /// @see #mapIfZero(int, IntFunction)
    /// @see #mapNonNullIfZero(int, IntFunction)
    /// @see #mapIfZeroOrElse(int, IntFunction, Object)
    /// @see #mapNonNullIfZeroOrElse(int, IntFunction, Object)
    /// @see #mapNonNullIfZeroOrElseGet(int, IntFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfZeroOrElseGet(int value, IntFunction<@Nullable R> mapper, Supplier<@Nullable R> orElse) {
        return value == 0 ? mapper.apply(value) : orElse.get();
    }
    
    ///
    /// Maps the given int value to a new value using the given mapper function if the value is zero.
    /// If the `value` is not zero, the `orElse` supplier's value is returned.
    ///
    /// Both the mapper function and the `orElse` supplier must not return `null`.
    ///
    /// @param value  The int value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is not zero.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is not zero.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` supplier returns `null`.
    /// @see #mapIfZero(int, IntFunction)
    /// @see #mapNonNullIfZero(int, IntFunction)
    /// @see #mapIfZeroOrElse(int, IntFunction, Object)
    /// @see #mapNonNullIfZeroOrElse(int, IntFunction, Object)
    /// @see #mapIfZeroOrElseGet(int, IntFunction, Supplier)
    ///
    public static <R> R mapNonNullIfZeroOrElseGet(int value, IntFunction<R> mapper, Supplier<R> orElse) {
        return value == 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse.get());
    }
    
    // ========== NOT ZERO ==========
    
    ///
    /// Maps the given int value to a new value using the given mapper function if the value is not zero.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value  The int value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is zero.
    ///
    /// @see #mapNonNullIfNotZero(int, IntFunction)
    /// @see #mapIfNotZeroOrElse(int, IntFunction, Object)
    /// @see #mapNonNullIfNotZeroOrElse(int, IntFunction, Object)
    /// @see #mapIfNotZeroOrElseGet(int, IntFunction, Supplier)
    /// @see #mapNonNullIfNotZeroOrElseGet(int, IntFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNotZero(int value, IntFunction<@Nullable R> mapper) {
        return value != 0 ? mapper.apply(value) : null;
    }
    
    ///
    /// Maps the given int value to a new value using the given mapper function if the value is not zero.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value  The int value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is zero.
    ///
    /// @throws NullPointerException If the mapper function returns `null`.
    /// @see #mapIfNotZero(int, IntFunction)
    /// @see #mapIfNotZeroOrElse(int, IntFunction, Object)
    /// @see #mapNonNullIfNotZeroOrElse(int, IntFunction, Object)
    /// @see #mapIfNotZeroOrElseGet(int, IntFunction, Supplier)
    /// @see #mapNonNullIfNotZeroOrElseGet(int, IntFunction, Supplier)
    ///
    public static <R> @Nullable R mapNonNullIfNotZero(int value, IntFunction<R> mapper) {
        return value != 0 ? Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") : null;
    }
    
    ///
    /// Maps the given int value to a new value using the given mapper function if the value is not zero.
    /// If the `value` is zero, the `orElse` value is returned.
    ///
    /// The mapper function and the `orElse` value may be `null`.
    ///
    /// @param value  The int value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is zero.
    ///
    /// @return The mapped value, or `orElse` if the `value` is zero.
    ///
    /// @see #mapIfNotZero(int, IntFunction)
    /// @see #mapNonNullIfNotZero(int, IntFunction)
    /// @see #mapNonNullIfNotZeroOrElse(int, IntFunction, Object)
    /// @see #mapIfNotZeroOrElseGet(int, IntFunction, Supplier)
    /// @see #mapNonNullIfNotZeroOrElseGet(int, IntFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNotZeroOrElse(int value, IntFunction<@Nullable R> mapper, @Nullable R orElse) {
        return value != 0 ? mapper.apply(value) : orElse;
    }
    
    ///
    /// Maps the given int value to a new value using the given mapper function if the value is not zero.
    /// If the `value` is zero, the `orElse` value is returned.
    ///
    /// Both the mapper function and the `orElse` value must not return `null`.
    ///
    /// @param value  The int value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is zero.
    ///
    /// @return The mapped value, or `orElse` if the `value` is zero.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` value is `null`.
    /// @see #mapIfNotZero(int, IntFunction)
    /// @see #mapNonNullIfNotZero(int, IntFunction)
    /// @see #mapIfNotZeroOrElse(int, IntFunction, Object)
    /// @see #mapIfNotZeroOrElseGet(int, IntFunction, Supplier)
    /// @see #mapNonNullIfNotZeroOrElseGet(int, IntFunction, Supplier)
    ///
    public static <R> R mapNonNullIfNotZeroOrElse(int value, IntFunction<R> mapper, R orElse) {
        return value != 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse);
    }
    
    ///
    /// Maps the given int value to a new value using the given mapper function if the value is not zero.
    /// If the `value` is zero, the `orElse` supplier's value is returned.
    ///
    /// The mapper function and the `orElse` supplier may be `null`.
    ///
    /// @param value  The int value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is zero.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is zero.
    ///
    /// @see #mapIfNotZero(int, IntFunction)
    /// @see #mapNonNullIfNotZero(int, IntFunction)
    /// @see #mapIfNotZeroOrElse(int, IntFunction, Object)
    /// @see #mapNonNullIfNotZeroOrElse(int, IntFunction, Object)
    /// @see #mapNonNullIfNotZeroOrElseGet(int, IntFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNotZeroOrElseGet(int value, IntFunction<@Nullable R> mapper, Supplier<@Nullable R> orElse) {
        return value != 0 ? mapper.apply(value) : orElse.get();
    }
    
    ///
    /// Maps the given int value to a new value using the given mapper function if the value is not zero.
    /// If the `value` is zero, the `orElse` supplier's value is returned.
    ///
    /// Both the mapper function and the `orElse` supplier must not return `null`.
    ///
    /// @param value  The int value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is zero.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is zero.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` supplier returns `null`.
    /// @see #mapIfNotZero(int, IntFunction)
    /// @see #mapNonNullIfNotZero(int, IntFunction)
    /// @see #mapIfNotZeroOrElse(int, IntFunction, Object)
    /// @see #mapNonNullIfNotZeroOrElse(int, IntFunction, Object)
    /// @see #mapIfNotZeroOrElseGet(int, IntFunction, Supplier)
    ///
    public static <R> R mapNonNullIfNotZeroOrElseGet(int value, IntFunction<R> mapper, Supplier<R> orElse) {
        return value != 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse.get());
    }
    
    // ========== IN RANGE ==========
    
    ///
    /// Maps the given int value to a new value using the given mapper function if the value is within the given range.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value  The int value to map.
    /// @param min    The lower bound of the range, inclusive.
    /// @param max    The upper bound of the range, inclusive.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is outside the given range.
    ///
    /// @see #mapNonNullIfInRange(int, int, int, IntFunction)
    /// @see #mapIfInRangeOrElse(int, int, int, IntFunction, Object)
    /// @see #mapNonNullIfInRangeOrElse(int, int, int, IntFunction, Object)
    /// @see #mapIfInRangeOrElseGet(int, int, int, IntFunction, Supplier)
    /// @see #mapNonNullIfInRangeOrElseGet(int, int, int, IntFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfInRange(int value, int min, int max, IntFunction<@Nullable R> mapper) {
        return (value >= min && value <= max) ? mapper.apply(value) : null;
    }
    
    ///
    /// Maps the given int value to a new value using the given mapper function if the value is within the given range.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value  The int value to map.
    /// @param min    The lower bound of the range, inclusive.
    /// @param max    The upper bound of the range, inclusive.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is outside the given range.
    ///
    /// @throws NullPointerException If the mapper function returns `null`.
    /// @see #mapIfInRange(int, int, int, IntFunction)
    /// @see #mapIfInRangeOrElse(int, int, int, IntFunction, Object)
    /// @see #mapNonNullIfInRangeOrElse(int, int, int, IntFunction, Object)
    /// @see #mapIfInRangeOrElseGet(int, int, int, IntFunction, Supplier)
    /// @see #mapNonNullIfInRangeOrElseGet(int, int, int, IntFunction, Supplier)
    ///
    public static <R> @Nullable R mapNonNullIfInRange(int value, int min, int max, IntFunction<R> mapper) {
        return (value >= min && value <= max) ? Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") : null;
    }
    
    ///
    /// Maps the given int value to a new value using the given mapper function if the value is within the given range.
    /// If the `value` is outside the given range, the `orElse` value is returned.
    ///
    /// The mapper function and the `orElse` value may be `null`.
    ///
    /// @param value  The int value to map.
    /// @param min    The lower bound of the range, inclusive.
    /// @param max    The upper bound of the range, inclusive.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is outside the given range.
    ///
    /// @return The mapped value, or `orElse` if the `value` is outside the given range.
    ///
    /// @see #mapIfInRange(int, int, int, IntFunction)
    /// @see #mapNonNullIfInRange(int, int, int, IntFunction)
    /// @see #mapNonNullIfInRangeOrElse(int, int, int, IntFunction, Object)
    /// @see #mapIfInRangeOrElseGet(int, int, int, IntFunction, Supplier)
    /// @see #mapNonNullIfInRangeOrElseGet(int, int, int, IntFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfInRangeOrElse(int value, int min, int max, IntFunction<@Nullable R> mapper, @Nullable R orElse) {
        return (value >= min && value <= max) ? mapper.apply(value) : orElse;
    }
    
    ///
    /// Maps the given int value to a new value using the given mapper function if the value is within the given range.
    /// If the `value` is outside the given range, the `orElse` value is returned.
    ///
    /// Both the mapper function and the `orElse` value must not return `null`.
    ///
    /// @param value  The int value to map.
    /// @param min    The lower bound of the range, inclusive.
    /// @param max    The upper bound of the range, inclusive.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is outside the given range.
    ///
    /// @return The mapped value, or `orElse` if the `value` is outside the given range.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` value is `null`.
    /// @see #mapIfInRange(int, int, int, IntFunction)
    /// @see #mapNonNullIfInRange(int, int, int, IntFunction)
    /// @see #mapIfInRangeOrElse(int, int, int, IntFunction, Object)
    /// @see #mapIfInRangeOrElseGet(int, int, int, IntFunction, Supplier)
    /// @see #mapNonNullIfInRangeOrElseGet(int, int, int, IntFunction, Supplier)
    ///
    public static <R> R mapNonNullIfInRangeOrElse(int value, int min, int max, IntFunction<R> mapper, R orElse) {
        return (value >= min && value <= max) ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse);
    }
    
    ///
    /// Maps the given int value to a new value using the given mapper function if the value is within the given range.
    /// If the `value` is outside the given range, the `orElse` supplier's value is returned.
    ///
    /// The mapper function and the `orElse` supplier may be `null`.
    ///
    /// @param value  The int value to map.
    /// @param min    The lower bound of the range, inclusive.
    /// @param max    The upper bound of the range, inclusive.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is outside the given range.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is outside the given range.
    ///
    /// @see #mapIfInRange(int, int, int, IntFunction)
    /// @see #mapNonNullIfInRange(int, int, int, IntFunction)
    /// @see #mapIfInRangeOrElse(int, int, int, IntFunction, Object)
    /// @see #mapNonNullIfInRangeOrElse(int, int, int, IntFunction, Object)
    /// @see #mapNonNullIfInRangeOrElseGet(int, int, int, IntFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfInRangeOrElseGet(int value, int min, int max, IntFunction<@Nullable R> mapper, Supplier<@Nullable R> orElse) {
        return (value >= min && value <= max) ? mapper.apply(value) : orElse.get();
    }
    
    ///
    /// Maps the given int value to a new value using the given mapper function if the value is within the given range.
    /// If the `value` is outside the given range, the `orElse` supplier's value is returned.
    ///
    /// Both the mapper function and the `orElse` supplier must not return `null`.
    ///
    /// @param value  The int value to map.
    /// @param min    The lower bound of the range, inclusive.
    /// @param max    The upper bound of the range, inclusive.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is outside the given range.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is outside the given range.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` supplier returns `null`.
    /// @see #mapIfInRange(int, int, int, IntFunction)
    /// @see #mapNonNullIfInRange(int, int, int, IntFunction)
    /// @see #mapIfInRangeOrElse(int, int, int, IntFunction, Object)
    /// @see #mapNonNullIfInRangeOrElse(int, int, int, IntFunction, Object)
    /// @see #mapIfInRangeOrElseGet(int, int, int, IntFunction, Supplier)
    ///
    public static <R> R mapNonNullIfInRangeOrElseGet(int value, int min, int max, IntFunction<R> mapper, Supplier<R> orElse) {
        return (value >= min && value <= max) ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse.get());
    }
}
