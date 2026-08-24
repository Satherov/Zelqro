package dev.satherov.zelqro.mapping;

import lombok.experimental.UtilityClass;

import dev.satherov.zelqro.function.ShortFunction;
import dev.satherov.zelqro.predicate.ShortPredicate;

import org.jspecify.annotations.Nullable;

import java.util.Objects;
import java.util.function.Supplier;

@UtilityClass
public class ShortMapping {
    
    // ========== PREDICATE ==========
    
    ///
    /// Maps the given short value to a new value using the given mapper function if the predicate returns `true`.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value     The short value to map.
    /// @param predicate The predicate to check against.
    /// @param mapper    The mapper function.
    ///
    /// @return The mapped value, or `null` if the predicate returns `false`.
    ///
    /// @see #mapNonNullIf(short, ShortPredicate, ShortFunction)
    /// @see #mapIfOrElse(short, ShortPredicate, ShortFunction, Object)
    /// @see #mapNonNullIfOrElse(short, ShortPredicate, ShortFunction, Object)
    /// @see #mapIfOrElseGet(short, ShortPredicate, ShortFunction, Supplier)
    /// @see #mapNonNullIfOrElseGet(short, ShortPredicate, ShortFunction, Supplier)
    ///
    public static <R> @Nullable R mapIf(short value, ShortPredicate predicate, ShortFunction<@Nullable R> mapper) {
        return predicate.test(value) ? mapper.apply(value) : null;
    }
    
    ///
    /// Maps the given short value to a new value using the given mapper function if the predicate returns `true`.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value     The short value to map.
    /// @param predicate The predicate to check against.
    /// @param mapper    The mapper function.
    ///
    /// @return The mapped value, or `null` if the predicate returns `false`.
    ///
    /// @throws NullPointerException If the mapper function returns `null`.
    /// @see #mapIf(short, ShortPredicate, ShortFunction)
    /// @see #mapIfOrElse(short, ShortPredicate, ShortFunction, Object)
    /// @see #mapNonNullIfOrElse(short, ShortPredicate, ShortFunction, Object)
    /// @see #mapIfOrElseGet(short, ShortPredicate, ShortFunction, Supplier)
    /// @see #mapNonNullIfOrElseGet(short, ShortPredicate, ShortFunction, Supplier)
    ///
    public static <R> @Nullable R mapNonNullIf(short value, ShortPredicate predicate, ShortFunction<R> mapper) {
        return predicate.test(value) ? Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") : null;
    }
    
    ///
    /// Maps the given short value to a new value using the given mapper function if the predicate returns `true`.
    /// If the predicate returns `false`, the `orElse` value is returned.
    ///
    /// The mapper function and the `orElse` value may be `null`.
    ///
    /// @param value     The short value to map.
    /// @param predicate The predicate to check against.
    /// @param mapper    The mapper function.
    /// @param orElse    The value to return if the predicate returns `false`.
    ///
    /// @return The mapped value, or `orElse` if the predicate returns `false`.
    ///
    /// @see #mapIf(short, ShortPredicate, ShortFunction)
    /// @see #mapNonNullIf(short, ShortPredicate, ShortFunction)
    /// @see #mapNonNullIfOrElse(short, ShortPredicate, ShortFunction, Object)
    /// @see #mapIfOrElseGet(short, ShortPredicate, ShortFunction, Supplier)
    /// @see #mapNonNullIfOrElseGet(short, ShortPredicate, ShortFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfOrElse(short value, ShortPredicate predicate, ShortFunction<@Nullable R> mapper, @Nullable R orElse) {
        return predicate.test(value) ? mapper.apply(value) : orElse;
    }
    
    ///
    /// Maps the given short value to a new value using the given mapper function if the predicate returns `true`.
    /// If the predicate returns `false`, the `orElse` value is returned.
    ///
    /// Both the mapper function and the `orElse` value must not return `null`.
    ///
    /// @param value     The short value to map.
    /// @param predicate The predicate to check against.
    /// @param mapper    The mapper function.
    /// @param orElse    The value to return if the predicate returns `false`.
    ///
    /// @return The mapped value, or `orElse` if the predicate returns `false`.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` value is `null`.
    /// @see #mapIf(short, ShortPredicate, ShortFunction)
    /// @see #mapNonNullIf(short, ShortPredicate, ShortFunction)
    /// @see #mapIfOrElse(short, ShortPredicate, ShortFunction, Object)
    /// @see #mapIfOrElseGet(short, ShortPredicate, ShortFunction, Supplier)
    /// @see #mapNonNullIfOrElseGet(short, ShortPredicate, ShortFunction, Supplier)
    ///
    public static <R> R mapNonNullIfOrElse(short value, ShortPredicate predicate, ShortFunction<R> mapper, R orElse) {
        return predicate.test(value) ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse);
    }
    
    ///
    /// Maps the given short value to a new value using the given mapper function if the predicate returns `true`.
    /// If the predicate returns `false`, the `orElse` supplier's value is returned.
    ///
    /// The mapper function and the `orElse` supplier may be `null`.
    ///
    /// @param value     The short value to map.
    /// @param predicate The predicate to check against.
    /// @param mapper    The mapper function.
    /// @param orElse    The supplier of the value to return if the predicate returns `false`.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the predicate returns `false`.
    ///
    /// @see #mapIf(short, ShortPredicate, ShortFunction)
    /// @see #mapNonNullIf(short, ShortPredicate, ShortFunction)
    /// @see #mapIfOrElse(short, ShortPredicate, ShortFunction, Object)
    /// @see #mapNonNullIfOrElse(short, ShortPredicate, ShortFunction, Object)
    /// @see #mapNonNullIfOrElseGet(short, ShortPredicate, ShortFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfOrElseGet(short value, ShortPredicate predicate, ShortFunction<@Nullable R> mapper, Supplier<@Nullable R> orElse) {
        return predicate.test(value) ? mapper.apply(value) : orElse.get();
    }
    
    ///
    /// Maps the given short value to a new value using the given mapper function if the predicate returns `true`.
    /// If the predicate returns `false`, the `orElse` supplier's value is returned.
    ///
    /// Both the mapper function and the `orElse` supplier must not return `null`.
    ///
    /// @param value     The short value to map.
    /// @param predicate The predicate to check against.
    /// @param mapper    The mapper function.
    /// @param orElse    The supplier of the value to return if the predicate returns `false`.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the predicate returns `false`.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` supplier returns `null`.
    /// @see #mapIf(short, ShortPredicate, ShortFunction)
    /// @see #mapNonNullIf(short, ShortPredicate, ShortFunction)
    /// @see #mapIfOrElse(short, ShortPredicate, ShortFunction, Object)
    /// @see #mapNonNullIfOrElse(short, ShortPredicate, ShortFunction, Object)
    /// @see #mapIfOrElseGet(short, ShortPredicate, ShortFunction, Supplier)
    ///
    public static <R> R mapNonNullIfOrElseGet(short value, ShortPredicate predicate, ShortFunction<R> mapper, Supplier<R> orElse) {
        return predicate.test(value) ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse.get());
    }
    
    // ========== POSITIVE ==========
    
    ///
    /// Maps the given short value to a new value using the given mapper function if the value is positive.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value  The short value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is not positive.
    ///
    /// @see #mapNonNullIfPositive(short, ShortFunction)
    /// @see #mapIfPositiveOrElse(short, ShortFunction, Object)
    /// @see #mapNonNullIfPositiveOrElse(short, ShortFunction, Object)
    /// @see #mapIfPositiveOrElseGet(short, ShortFunction, Supplier)
    /// @see #mapNonNullIfPositiveOrElseGet(short, ShortFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfPositive(short value, ShortFunction<@Nullable R> mapper) {
        return value > 0 ? mapper.apply(value) : null;
    }
    
    ///
    /// Maps the given short value to a new value using the given mapper function if the value is positive.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value  The short value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is not positive.
    ///
    /// @throws NullPointerException If the mapper function returns `null`.
    /// @see #mapIfPositive(short, ShortFunction)
    /// @see #mapIfPositiveOrElse(short, ShortFunction, Object)
    /// @see #mapNonNullIfPositiveOrElse(short, ShortFunction, Object)
    /// @see #mapIfPositiveOrElseGet(short, ShortFunction, Supplier)
    /// @see #mapNonNullIfPositiveOrElseGet(short, ShortFunction, Supplier)
    ///
    public static <R> @Nullable R mapNonNullIfPositive(short value, ShortFunction<R> mapper) {
        return value > 0 ? Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") : null;
    }
    
    ///
    /// Maps the given short value to a new value using the given mapper function if the value is positive.
    /// If the `value` is not positive, the `orElse` value is returned.
    ///
    /// The mapper function and the `orElse` value may be `null`.
    ///
    /// @param value  The short value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is not positive.
    ///
    /// @return The mapped value, or `orElse` if the `value` is not positive.
    ///
    /// @see #mapIfPositive(short, ShortFunction)
    /// @see #mapNonNullIfPositive(short, ShortFunction)
    /// @see #mapNonNullIfPositiveOrElse(short, ShortFunction, Object)
    /// @see #mapIfPositiveOrElseGet(short, ShortFunction, Supplier)
    /// @see #mapNonNullIfPositiveOrElseGet(short, ShortFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfPositiveOrElse(short value, ShortFunction<@Nullable R> mapper, @Nullable R orElse) {
        return value > 0 ? mapper.apply(value) : orElse;
    }
    
    ///
    /// Maps the given short value to a new value using the given mapper function if the value is positive.
    /// If the `value` is not positive, the `orElse` value is returned.
    ///
    /// Both the mapper function and the `orElse` value must not return `null`.
    ///
    /// @param value  The short value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is not positive.
    ///
    /// @return The mapped value, or `orElse` if the `value` is not positive.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` value is `null`.
    /// @see #mapIfPositive(short, ShortFunction)
    /// @see #mapNonNullIfPositive(short, ShortFunction)
    /// @see #mapIfPositiveOrElse(short, ShortFunction, Object)
    /// @see #mapIfPositiveOrElseGet(short, ShortFunction, Supplier)
    /// @see #mapNonNullIfPositiveOrElseGet(short, ShortFunction, Supplier)
    ///
    public static <R> R mapNonNullIfPositiveOrElse(short value, ShortFunction<R> mapper, R orElse) {
        return value > 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse);
    }
    
    ///
    /// Maps the given short value to a new value using the given mapper function if the value is positive.
    /// If the `value` is not positive, the `orElse` supplier's value is returned.
    ///
    /// The mapper function and the `orElse` supplier may be `null`.
    ///
    /// @param value  The short value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is not positive.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is not positive.
    ///
    /// @see #mapIfPositive(short, ShortFunction)
    /// @see #mapNonNullIfPositive(short, ShortFunction)
    /// @see #mapIfPositiveOrElse(short, ShortFunction, Object)
    /// @see #mapNonNullIfPositiveOrElse(short, ShortFunction, Object)
    /// @see #mapNonNullIfPositiveOrElseGet(short, ShortFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfPositiveOrElseGet(short value, ShortFunction<@Nullable R> mapper, Supplier<@Nullable R> orElse) {
        return value > 0 ? mapper.apply(value) : orElse.get();
    }
    
    ///
    /// Maps the given short value to a new value using the given mapper function if the value is positive.
    /// If the `value` is not positive, the `orElse` supplier's value is returned.
    ///
    /// Both the mapper function and the `orElse` supplier must not return `null`.
    ///
    /// @param value  The short value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is not positive.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is not positive.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` supplier returns `null`.
    /// @see #mapIfPositive(short, ShortFunction)
    /// @see #mapNonNullIfPositive(short, ShortFunction)
    /// @see #mapIfPositiveOrElse(short, ShortFunction, Object)
    /// @see #mapNonNullIfPositiveOrElse(short, ShortFunction, Object)
    /// @see #mapIfPositiveOrElseGet(short, ShortFunction, Supplier)
    ///
    public static <R> R mapNonNullIfPositiveOrElseGet(short value, ShortFunction<R> mapper, Supplier<R> orElse) {
        return value > 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse.get());
    }
    
    // ========== NEGATIVE ==========
    
    ///
    /// Maps the given short value to a new value using the given mapper function if the value is negative.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value  The short value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is not negative.
    ///
    /// @see #mapNonNullIfNegative(short, ShortFunction)
    /// @see #mapIfNegativeOrElse(short, ShortFunction, Object)
    /// @see #mapNonNullIfNegativeOrElse(short, ShortFunction, Object)
    /// @see #mapIfNegativeOrElseGet(short, ShortFunction, Supplier)
    /// @see #mapNonNullIfNegativeOrElseGet(short, ShortFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNegative(short value, ShortFunction<@Nullable R> mapper) {
        return value < 0 ? mapper.apply(value) : null;
    }
    
    ///
    /// Maps the given short value to a new value using the given mapper function if the value is negative.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value  The short value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is not negative.
    ///
    /// @throws NullPointerException If the mapper function returns `null`.
    /// @see #mapIfNegative(short, ShortFunction)
    /// @see #mapIfNegativeOrElse(short, ShortFunction, Object)
    /// @see #mapNonNullIfNegativeOrElse(short, ShortFunction, Object)
    /// @see #mapIfNegativeOrElseGet(short, ShortFunction, Supplier)
    /// @see #mapNonNullIfNegativeOrElseGet(short, ShortFunction, Supplier)
    ///
    public static <R> @Nullable R mapNonNullIfNegative(short value, ShortFunction<R> mapper) {
        return value < 0 ? Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") : null;
    }
    
    ///
    /// Maps the given short value to a new value using the given mapper function if the value is negative.
    /// If the `value` is not negative, the `orElse` value is returned.
    ///
    /// The mapper function and the `orElse` value may be `null`.
    ///
    /// @param value  The short value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is not negative.
    ///
    /// @return The mapped value, or `orElse` if the `value` is not negative.
    ///
    /// @see #mapIfNegative(short, ShortFunction)
    /// @see #mapNonNullIfNegative(short, ShortFunction)
    /// @see #mapNonNullIfNegativeOrElse(short, ShortFunction, Object)
    /// @see #mapIfNegativeOrElseGet(short, ShortFunction, Supplier)
    /// @see #mapNonNullIfNegativeOrElseGet(short, ShortFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNegativeOrElse(short value, ShortFunction<@Nullable R> mapper, @Nullable R orElse) {
        return value < 0 ? mapper.apply(value) : orElse;
    }
    
    ///
    /// Maps the given short value to a new value using the given mapper function if the value is negative.
    /// If the `value` is not negative, the `orElse` value is returned.
    ///
    /// Both the mapper function and the `orElse` value must not return `null`.
    ///
    /// @param value  The short value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is not negative.
    ///
    /// @return The mapped value, or `orElse` if the `value` is not negative.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` value is `null`.
    /// @see #mapIfNegative(short, ShortFunction)
    /// @see #mapNonNullIfNegative(short, ShortFunction)
    /// @see #mapIfNegativeOrElse(short, ShortFunction, Object)
    /// @see #mapIfNegativeOrElseGet(short, ShortFunction, Supplier)
    /// @see #mapNonNullIfNegativeOrElseGet(short, ShortFunction, Supplier)
    ///
    public static <R> R mapNonNullIfNegativeOrElse(short value, ShortFunction<R> mapper, R orElse) {
        return value < 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse);
    }
    
    ///
    /// Maps the given short value to a new value using the given mapper function if the value is negative.
    /// If the `value` is not negative, the `orElse` supplier's value is returned.
    ///
    /// The mapper function and the `orElse` supplier may be `null`.
    ///
    /// @param value  The short value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is not negative.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is not negative.
    ///
    /// @see #mapIfNegative(short, ShortFunction)
    /// @see #mapNonNullIfNegative(short, ShortFunction)
    /// @see #mapIfNegativeOrElse(short, ShortFunction, Object)
    /// @see #mapNonNullIfNegativeOrElse(short, ShortFunction, Object)
    /// @see #mapNonNullIfNegativeOrElseGet(short, ShortFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNegativeOrElseGet(short value, ShortFunction<@Nullable R> mapper, Supplier<@Nullable R> orElse) {
        return value < 0 ? mapper.apply(value) : orElse.get();
    }
    
    ///
    /// Maps the given short value to a new value using the given mapper function if the value is negative.
    /// If the `value` is not negative, the `orElse` supplier's value is returned.
    ///
    /// Both the mapper function and the `orElse` supplier must not return `null`.
    ///
    /// @param value  The short value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is not negative.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is not negative.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` supplier returns `null`.
    /// @see #mapIfNegative(short, ShortFunction)
    /// @see #mapNonNullIfNegative(short, ShortFunction)
    /// @see #mapIfNegativeOrElse(short, ShortFunction, Object)
    /// @see #mapNonNullIfNegativeOrElse(short, ShortFunction, Object)
    /// @see #mapIfNegativeOrElseGet(short, ShortFunction, Supplier)
    ///
    public static <R> R mapNonNullIfNegativeOrElseGet(short value, ShortFunction<R> mapper, Supplier<R> orElse) {
        return value < 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse.get());
    }
    
    // ========== NOT POSITIVE ==========
    
    ///
    /// Maps the given short value to a new value using the given mapper function if the value is not positive.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value  The short value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is positive.
    ///
    /// @see #mapNonNullIfNotPositive(short, ShortFunction)
    /// @see #mapIfNotPositiveOrElse(short, ShortFunction, Object)
    /// @see #mapNonNullIfNotPositiveOrElse(short, ShortFunction, Object)
    /// @see #mapIfNotPositiveOrElseGet(short, ShortFunction, Supplier)
    /// @see #mapNonNullIfNotPositiveOrElseGet(short, ShortFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNotPositive(short value, ShortFunction<@Nullable R> mapper) {
        return value <= 0 ? mapper.apply(value) : null;
    }
    
    ///
    /// Maps the given short value to a new value using the given mapper function if the value is not positive.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value  The short value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is positive.
    ///
    /// @throws NullPointerException If the mapper function returns `null`.
    /// @see #mapIfNotPositive(short, ShortFunction)
    /// @see #mapIfNotPositiveOrElse(short, ShortFunction, Object)
    /// @see #mapNonNullIfNotPositiveOrElse(short, ShortFunction, Object)
    /// @see #mapIfNotPositiveOrElseGet(short, ShortFunction, Supplier)
    /// @see #mapNonNullIfNotPositiveOrElseGet(short, ShortFunction, Supplier)
    ///
    public static <R> @Nullable R mapNonNullIfNotPositive(short value, ShortFunction<R> mapper) {
        return value <= 0 ? Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") : null;
    }
    
    ///
    /// Maps the given short value to a new value using the given mapper function if the value is not positive.
    /// If the `value` is positive, the `orElse` value is returned.
    ///
    /// The mapper function and the `orElse` value may be `null`.
    ///
    /// @param value  The short value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is positive.
    ///
    /// @return The mapped value, or `orElse` if the `value` is positive.
    ///
    /// @see #mapIfNotPositive(short, ShortFunction)
    /// @see #mapNonNullIfNotPositive(short, ShortFunction)
    /// @see #mapNonNullIfNotPositiveOrElse(short, ShortFunction, Object)
    /// @see #mapIfNotPositiveOrElseGet(short, ShortFunction, Supplier)
    /// @see #mapNonNullIfNotPositiveOrElseGet(short, ShortFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNotPositiveOrElse(short value, ShortFunction<@Nullable R> mapper, @Nullable R orElse) {
        return value <= 0 ? mapper.apply(value) : orElse;
    }
    
    ///
    /// Maps the given short value to a new value using the given mapper function if the value is not positive.
    /// If the `value` is positive, the `orElse` value is returned.
    ///
    /// Both the mapper function and the `orElse` value must not return `null`.
    ///
    /// @param value  The short value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is positive.
    ///
    /// @return The mapped value, or `orElse` if the `value` is positive.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` value is `null`.
    /// @see #mapIfNotPositive(short, ShortFunction)
    /// @see #mapNonNullIfNotPositive(short, ShortFunction)
    /// @see #mapIfNotPositiveOrElse(short, ShortFunction, Object)
    /// @see #mapIfNotPositiveOrElseGet(short, ShortFunction, Supplier)
    /// @see #mapNonNullIfNotPositiveOrElseGet(short, ShortFunction, Supplier)
    ///
    public static <R> R mapNonNullIfNotPositiveOrElse(short value, ShortFunction<R> mapper, R orElse) {
        return value <= 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse);
    }
    
    ///
    /// Maps the given short value to a new value using the given mapper function if the value is not positive.
    /// If the `value` is positive, the `orElse` supplier's value is returned.
    ///
    /// The mapper function and the `orElse` supplier may be `null`.
    ///
    /// @param value  The short value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is positive.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is positive.
    ///
    /// @see #mapIfNotPositive(short, ShortFunction)
    /// @see #mapNonNullIfNotPositive(short, ShortFunction)
    /// @see #mapIfNotPositiveOrElse(short, ShortFunction, Object)
    /// @see #mapNonNullIfNotPositiveOrElse(short, ShortFunction, Object)
    /// @see #mapNonNullIfNotPositiveOrElseGet(short, ShortFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNotPositiveOrElseGet(short value, ShortFunction<@Nullable R> mapper, Supplier<@Nullable R> orElse) {
        return value <= 0 ? mapper.apply(value) : orElse.get();
    }
    
    ///
    /// Maps the given short value to a new value using the given mapper function if the value is not positive.
    /// If the `value` is positive, the `orElse` supplier's value is returned.
    ///
    /// Both the mapper function and the `orElse` supplier must not return `null`.
    ///
    /// @param value  The short value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is positive.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is positive.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` supplier returns `null`.
    /// @see #mapIfNotPositive(short, ShortFunction)
    /// @see #mapNonNullIfNotPositive(short, ShortFunction)
    /// @see #mapIfNotPositiveOrElse(short, ShortFunction, Object)
    /// @see #mapNonNullIfNotPositiveOrElse(short, ShortFunction, Object)
    /// @see #mapIfNotPositiveOrElseGet(short, ShortFunction, Supplier)
    ///
    public static <R> R mapNonNullIfNotPositiveOrElseGet(short value, ShortFunction<R> mapper, Supplier<R> orElse) {
        return value <= 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse.get());
    }
    
    // ========== NOT NEGATIVE ==========
    
    ///
    /// Maps the given short value to a new value using the given mapper function if the value is not negative.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value  The short value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is negative.
    ///
    /// @see #mapNonNullIfNotNegative(short, ShortFunction)
    /// @see #mapIfNotNegativeOrElse(short, ShortFunction, Object)
    /// @see #mapNonNullIfNotNegativeOrElse(short, ShortFunction, Object)
    /// @see #mapIfNotNegativeOrElseGet(short, ShortFunction, Supplier)
    /// @see #mapNonNullIfNotNegativeOrElseGet(short, ShortFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNotNegative(short value, ShortFunction<@Nullable R> mapper) {
        return value >= 0 ? mapper.apply(value) : null;
    }
    
    ///
    /// Maps the given short value to a new value using the given mapper function if the value is not negative.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value  The short value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is negative.
    ///
    /// @throws NullPointerException If the mapper function returns `null`.
    /// @see #mapIfNotNegative(short, ShortFunction)
    /// @see #mapIfNotNegativeOrElse(short, ShortFunction, Object)
    /// @see #mapNonNullIfNotNegativeOrElse(short, ShortFunction, Object)
    /// @see #mapIfNotNegativeOrElseGet(short, ShortFunction, Supplier)
    /// @see #mapNonNullIfNotNegativeOrElseGet(short, ShortFunction, Supplier)
    ///
    public static <R> @Nullable R mapNonNullIfNotNegative(short value, ShortFunction<R> mapper) {
        return value >= 0 ? Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") : null;
    }
    
    ///
    /// Maps the given short value to a new value using the given mapper function if the value is not negative.
    /// If the `value` is negative, the `orElse` value is returned.
    ///
    /// The mapper function and the `orElse` value may be `null`.
    ///
    /// @param value  The short value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is negative.
    ///
    /// @return The mapped value, or `orElse` if the `value` is negative.
    ///
    /// @see #mapIfNotNegative(short, ShortFunction)
    /// @see #mapNonNullIfNotNegative(short, ShortFunction)
    /// @see #mapNonNullIfNotNegativeOrElse(short, ShortFunction, Object)
    /// @see #mapIfNotNegativeOrElseGet(short, ShortFunction, Supplier)
    /// @see #mapNonNullIfNotNegativeOrElseGet(short, ShortFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNotNegativeOrElse(short value, ShortFunction<@Nullable R> mapper, @Nullable R orElse) {
        return value >= 0 ? mapper.apply(value) : orElse;
    }
    
    ///
    /// Maps the given short value to a new value using the given mapper function if the value is not negative.
    /// If the `value` is negative, the `orElse` value is returned.
    ///
    /// Both the mapper function and the `orElse` value must not return `null`.
    ///
    /// @param value  The short value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is negative.
    ///
    /// @return The mapped value, or `orElse` if the `value` is negative.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` value is `null`.
    /// @see #mapIfNotNegative(short, ShortFunction)
    /// @see #mapNonNullIfNotNegative(short, ShortFunction)
    /// @see #mapIfNotNegativeOrElse(short, ShortFunction, Object)
    /// @see #mapIfNotNegativeOrElseGet(short, ShortFunction, Supplier)
    /// @see #mapNonNullIfNotNegativeOrElseGet(short, ShortFunction, Supplier)
    ///
    public static <R> R mapNonNullIfNotNegativeOrElse(short value, ShortFunction<R> mapper, R orElse) {
        return value >= 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse);
    }
    
    ///
    /// Maps the given short value to a new value using the given mapper function if the value is not negative.
    /// If the `value` is negative, the `orElse` supplier's value is returned.
    ///
    /// The mapper function and the `orElse` supplier may be `null`.
    ///
    /// @param value  The short value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is negative.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is negative.
    ///
    /// @see #mapIfNotNegative(short, ShortFunction)
    /// @see #mapNonNullIfNotNegative(short, ShortFunction)
    /// @see #mapIfNotNegativeOrElse(short, ShortFunction, Object)
    /// @see #mapNonNullIfNotNegativeOrElse(short, ShortFunction, Object)
    /// @see #mapNonNullIfNotNegativeOrElseGet(short, ShortFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNotNegativeOrElseGet(short value, ShortFunction<@Nullable R> mapper, Supplier<@Nullable R> orElse) {
        return value >= 0 ? mapper.apply(value) : orElse.get();
    }
    
    ///
    /// Maps the given short value to a new value using the given mapper function if the value is not negative.
    /// If the `value` is negative, the `orElse` supplier's value is returned.
    ///
    /// Both the mapper function and the `orElse` supplier must not return `null`.
    ///
    /// @param value  The short value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is negative.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is negative.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` supplier returns `null`.
    /// @see #mapIfNotNegative(short, ShortFunction)
    /// @see #mapNonNullIfNotNegative(short, ShortFunction)
    /// @see #mapIfNotNegativeOrElse(short, ShortFunction, Object)
    /// @see #mapNonNullIfNotNegativeOrElse(short, ShortFunction, Object)
    /// @see #mapIfNotNegativeOrElseGet(short, ShortFunction, Supplier)
    ///
    public static <R> R mapNonNullIfNotNegativeOrElseGet(short value, ShortFunction<R> mapper, Supplier<R> orElse) {
        return value >= 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse.get());
    }
    
    // ========== ZERO ==========
    
    ///
    /// Maps the given short value to a new value using the given mapper function if the value is zero.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value  The short value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is not zero.
    ///
    /// @see #mapNonNullIfZero(short, ShortFunction)
    /// @see #mapIfZeroOrElse(short, ShortFunction, Object)
    /// @see #mapNonNullIfZeroOrElse(short, ShortFunction, Object)
    /// @see #mapIfZeroOrElseGet(short, ShortFunction, Supplier)
    /// @see #mapNonNullIfZeroOrElseGet(short, ShortFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfZero(short value, ShortFunction<@Nullable R> mapper) {
        return value == 0 ? mapper.apply(value) : null;
    }
    
    ///
    /// Maps the given short value to a new value using the given mapper function if the value is zero.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value  The short value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is not zero.
    ///
    /// @throws NullPointerException If the mapper function returns `null`.
    /// @see #mapIfZero(short, ShortFunction)
    /// @see #mapIfZeroOrElse(short, ShortFunction, Object)
    /// @see #mapNonNullIfZeroOrElse(short, ShortFunction, Object)
    /// @see #mapIfZeroOrElseGet(short, ShortFunction, Supplier)
    /// @see #mapNonNullIfZeroOrElseGet(short, ShortFunction, Supplier)
    ///
    public static <R> @Nullable R mapNonNullIfZero(short value, ShortFunction<R> mapper) {
        return value == 0 ? Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") : null;
    }
    
    ///
    /// Maps the given short value to a new value using the given mapper function if the value is zero.
    /// If the `value` is not zero, the `orElse` value is returned.
    ///
    /// The mapper function and the `orElse` value may be `null`.
    ///
    /// @param value  The short value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is not zero.
    ///
    /// @return The mapped value, or `orElse` if the `value` is not zero.
    ///
    /// @see #mapIfZero(short, ShortFunction)
    /// @see #mapNonNullIfZero(short, ShortFunction)
    /// @see #mapNonNullIfZeroOrElse(short, ShortFunction, Object)
    /// @see #mapIfZeroOrElseGet(short, ShortFunction, Supplier)
    /// @see #mapNonNullIfZeroOrElseGet(short, ShortFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfZeroOrElse(short value, ShortFunction<@Nullable R> mapper, @Nullable R orElse) {
        return value == 0 ? mapper.apply(value) : orElse;
    }
    
    ///
    /// Maps the given short value to a new value using the given mapper function if the value is zero.
    /// If the `value` is not zero, the `orElse` value is returned.
    ///
    /// Both the mapper function and the `orElse` value must not return `null`.
    ///
    /// @param value  The short value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is not zero.
    ///
    /// @return The mapped value, or `orElse` if the `value` is not zero.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` value is `null`.
    /// @see #mapIfZero(short, ShortFunction)
    /// @see #mapNonNullIfZero(short, ShortFunction)
    /// @see #mapIfZeroOrElse(short, ShortFunction, Object)
    /// @see #mapIfZeroOrElseGet(short, ShortFunction, Supplier)
    /// @see #mapNonNullIfZeroOrElseGet(short, ShortFunction, Supplier)
    ///
    public static <R> R mapNonNullIfZeroOrElse(short value, ShortFunction<R> mapper, R orElse) {
        return value == 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse);
    }
    
    ///
    /// Maps the given short value to a new value using the given mapper function if the value is zero.
    /// If the `value` is not zero, the `orElse` supplier's value is returned.
    ///
    /// The mapper function and the `orElse` supplier may be `null`.
    ///
    /// @param value  The short value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is not zero.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is not zero.
    ///
    /// @see #mapIfZero(short, ShortFunction)
    /// @see #mapNonNullIfZero(short, ShortFunction)
    /// @see #mapIfZeroOrElse(short, ShortFunction, Object)
    /// @see #mapNonNullIfZeroOrElse(short, ShortFunction, Object)
    /// @see #mapNonNullIfZeroOrElseGet(short, ShortFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfZeroOrElseGet(short value, ShortFunction<@Nullable R> mapper, Supplier<@Nullable R> orElse) {
        return value == 0 ? mapper.apply(value) : orElse.get();
    }
    
    ///
    /// Maps the given short value to a new value using the given mapper function if the value is zero.
    /// If the `value` is not zero, the `orElse` supplier's value is returned.
    ///
    /// Both the mapper function and the `orElse` supplier must not return `null`.
    ///
    /// @param value  The short value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is not zero.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is not zero.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` supplier returns `null`.
    /// @see #mapIfZero(short, ShortFunction)
    /// @see #mapNonNullIfZero(short, ShortFunction)
    /// @see #mapIfZeroOrElse(short, ShortFunction, Object)
    /// @see #mapNonNullIfZeroOrElse(short, ShortFunction, Object)
    /// @see #mapIfZeroOrElseGet(short, ShortFunction, Supplier)
    ///
    public static <R> R mapNonNullIfZeroOrElseGet(short value, ShortFunction<R> mapper, Supplier<R> orElse) {
        return value == 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse.get());
    }
    
    // ========== NOT ZERO ==========
    
    ///
    /// Maps the given short value to a new value using the given mapper function if the value is not zero.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value  The short value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is zero.
    ///
    /// @see #mapNonNullIfNotZero(short, ShortFunction)
    /// @see #mapIfNotZeroOrElse(short, ShortFunction, Object)
    /// @see #mapNonNullIfNotZeroOrElse(short, ShortFunction, Object)
    /// @see #mapIfNotZeroOrElseGet(short, ShortFunction, Supplier)
    /// @see #mapNonNullIfNotZeroOrElseGet(short, ShortFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNotZero(short value, ShortFunction<@Nullable R> mapper) {
        return value != 0 ? mapper.apply(value) : null;
    }
    
    ///
    /// Maps the given short value to a new value using the given mapper function if the value is not zero.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value  The short value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is zero.
    ///
    /// @throws NullPointerException If the mapper function returns `null`.
    /// @see #mapIfNotZero(short, ShortFunction)
    /// @see #mapIfNotZeroOrElse(short, ShortFunction, Object)
    /// @see #mapNonNullIfNotZeroOrElse(short, ShortFunction, Object)
    /// @see #mapIfNotZeroOrElseGet(short, ShortFunction, Supplier)
    /// @see #mapNonNullIfNotZeroOrElseGet(short, ShortFunction, Supplier)
    ///
    public static <R> @Nullable R mapNonNullIfNotZero(short value, ShortFunction<R> mapper) {
        return value != 0 ? Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") : null;
    }
    
    ///
    /// Maps the given short value to a new value using the given mapper function if the value is not zero.
    /// If the `value` is zero, the `orElse` value is returned.
    ///
    /// The mapper function and the `orElse` value may be `null`.
    ///
    /// @param value  The short value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is zero.
    ///
    /// @return The mapped value, or `orElse` if the `value` is zero.
    ///
    /// @see #mapIfNotZero(short, ShortFunction)
    /// @see #mapNonNullIfNotZero(short, ShortFunction)
    /// @see #mapNonNullIfNotZeroOrElse(short, ShortFunction, Object)
    /// @see #mapIfNotZeroOrElseGet(short, ShortFunction, Supplier)
    /// @see #mapNonNullIfNotZeroOrElseGet(short, ShortFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNotZeroOrElse(short value, ShortFunction<@Nullable R> mapper, @Nullable R orElse) {
        return value != 0 ? mapper.apply(value) : orElse;
    }
    
    ///
    /// Maps the given short value to a new value using the given mapper function if the value is not zero.
    /// If the `value` is zero, the `orElse` value is returned.
    ///
    /// Both the mapper function and the `orElse` value must not return `null`.
    ///
    /// @param value  The short value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is zero.
    ///
    /// @return The mapped value, or `orElse` if the `value` is zero.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` value is `null`.
    /// @see #mapIfNotZero(short, ShortFunction)
    /// @see #mapNonNullIfNotZero(short, ShortFunction)
    /// @see #mapIfNotZeroOrElse(short, ShortFunction, Object)
    /// @see #mapIfNotZeroOrElseGet(short, ShortFunction, Supplier)
    /// @see #mapNonNullIfNotZeroOrElseGet(short, ShortFunction, Supplier)
    ///
    public static <R> R mapNonNullIfNotZeroOrElse(short value, ShortFunction<R> mapper, R orElse) {
        return value != 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse);
    }
    
    ///
    /// Maps the given short value to a new value using the given mapper function if the value is not zero.
    /// If the `value` is zero, the `orElse` supplier's value is returned.
    ///
    /// The mapper function and the `orElse` supplier may be `null`.
    ///
    /// @param value  The short value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is zero.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is zero.
    ///
    /// @see #mapIfNotZero(short, ShortFunction)
    /// @see #mapNonNullIfNotZero(short, ShortFunction)
    /// @see #mapIfNotZeroOrElse(short, ShortFunction, Object)
    /// @see #mapNonNullIfNotZeroOrElse(short, ShortFunction, Object)
    /// @see #mapNonNullIfNotZeroOrElseGet(short, ShortFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNotZeroOrElseGet(short value, ShortFunction<@Nullable R> mapper, Supplier<@Nullable R> orElse) {
        return value != 0 ? mapper.apply(value) : orElse.get();
    }
    
    ///
    /// Maps the given short value to a new value using the given mapper function if the value is not zero.
    /// If the `value` is zero, the `orElse` supplier's value is returned.
    ///
    /// Both the mapper function and the `orElse` supplier must not return `null`.
    ///
    /// @param value  The short value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is zero.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is zero.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` supplier returns `null`.
    /// @see #mapIfNotZero(short, ShortFunction)
    /// @see #mapNonNullIfNotZero(short, ShortFunction)
    /// @see #mapIfNotZeroOrElse(short, ShortFunction, Object)
    /// @see #mapNonNullIfNotZeroOrElse(short, ShortFunction, Object)
    /// @see #mapIfNotZeroOrElseGet(short, ShortFunction, Supplier)
    ///
    public static <R> R mapNonNullIfNotZeroOrElseGet(short value, ShortFunction<R> mapper, Supplier<R> orElse) {
        return value != 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse.get());
    }
    
    // ========== IN RANGE ==========
    
    ///
    /// Maps the given short value to a new value using the given mapper function if the value is within the given range.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value  The short value to map.
    /// @param min    The lower bound of the range, inclusive.
    /// @param max    The upper bound of the range, inclusive.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is outside the given range.
    ///
    /// @see #mapNonNullIfInRange(short, short, short, ShortFunction)
    /// @see #mapIfInRangeOrElse(short, short, short, ShortFunction, Object)
    /// @see #mapNonNullIfInRangeOrElse(short, short, short, ShortFunction, Object)
    /// @see #mapIfInRangeOrElseGet(short, short, short, ShortFunction, Supplier)
    /// @see #mapNonNullIfInRangeOrElseGet(short, short, short, ShortFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfInRange(short value, short min, short max, ShortFunction<@Nullable R> mapper) {
        return (value >= min && value <= max) ? mapper.apply(value) : null;
    }
    
    ///
    /// Maps the given short value to a new value using the given mapper function if the value is within the given range.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value  The short value to map.
    /// @param min    The lower bound of the range, inclusive.
    /// @param max    The upper bound of the range, inclusive.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is outside the given range.
    ///
    /// @throws NullPointerException If the mapper function returns `null`.
    /// @see #mapIfInRange(short, short, short, ShortFunction)
    /// @see #mapIfInRangeOrElse(short, short, short, ShortFunction, Object)
    /// @see #mapNonNullIfInRangeOrElse(short, short, short, ShortFunction, Object)
    /// @see #mapIfInRangeOrElseGet(short, short, short, ShortFunction, Supplier)
    /// @see #mapNonNullIfInRangeOrElseGet(short, short, short, ShortFunction, Supplier)
    ///
    public static <R> @Nullable R mapNonNullIfInRange(short value, short min, short max, ShortFunction<R> mapper) {
        return (value >= min && value <= max) ? Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") : null;
    }
    
    ///
    /// Maps the given short value to a new value using the given mapper function if the value is within the given range.
    /// If the `value` is outside the given range, the `orElse` value is returned.
    ///
    /// The mapper function and the `orElse` value may be `null`.
    ///
    /// @param value  The short value to map.
    /// @param min    The lower bound of the range, inclusive.
    /// @param max    The upper bound of the range, inclusive.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is outside the given range.
    ///
    /// @return The mapped value, or `orElse` if the `value` is outside the given range.
    ///
    /// @see #mapIfInRange(short, short, short, ShortFunction)
    /// @see #mapNonNullIfInRange(short, short, short, ShortFunction)
    /// @see #mapNonNullIfInRangeOrElse(short, short, short, ShortFunction, Object)
    /// @see #mapIfInRangeOrElseGet(short, short, short, ShortFunction, Supplier)
    /// @see #mapNonNullIfInRangeOrElseGet(short, short, short, ShortFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfInRangeOrElse(short value, short min, short max, ShortFunction<@Nullable R> mapper, @Nullable R orElse) {
        return (value >= min && value <= max) ? mapper.apply(value) : orElse;
    }
    
    ///
    /// Maps the given short value to a new value using the given mapper function if the value is within the given range.
    /// If the `value` is outside the given range, the `orElse` value is returned.
    ///
    /// Both the mapper function and the `orElse` value must not return `null`.
    ///
    /// @param value  The short value to map.
    /// @param min    The lower bound of the range, inclusive.
    /// @param max    The upper bound of the range, inclusive.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is outside the given range.
    ///
    /// @return The mapped value, or `orElse` if the `value` is outside the given range.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` value is `null`.
    /// @see #mapIfInRange(short, short, short, ShortFunction)
    /// @see #mapNonNullIfInRange(short, short, short, ShortFunction)
    /// @see #mapIfInRangeOrElse(short, short, short, ShortFunction, Object)
    /// @see #mapIfInRangeOrElseGet(short, short, short, ShortFunction, Supplier)
    /// @see #mapNonNullIfInRangeOrElseGet(short, short, short, ShortFunction, Supplier)
    ///
    public static <R> R mapNonNullIfInRangeOrElse(short value, short min, short max, ShortFunction<R> mapper, R orElse) {
        return (value >= min && value <= max) ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse);
    }
    
    ///
    /// Maps the given short value to a new value using the given mapper function if the value is within the given range.
    /// If the `value` is outside the given range, the `orElse` supplier's value is returned.
    ///
    /// The mapper function and the `orElse` supplier may be `null`.
    ///
    /// @param value  The short value to map.
    /// @param min    The lower bound of the range, inclusive.
    /// @param max    The upper bound of the range, inclusive.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is outside the given range.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is outside the given range.
    ///
    /// @see #mapIfInRange(short, short, short, ShortFunction)
    /// @see #mapNonNullIfInRange(short, short, short, ShortFunction)
    /// @see #mapIfInRangeOrElse(short, short, short, ShortFunction, Object)
    /// @see #mapNonNullIfInRangeOrElse(short, short, short, ShortFunction, Object)
    /// @see #mapNonNullIfInRangeOrElseGet(short, short, short, ShortFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfInRangeOrElseGet(short value, short min, short max, ShortFunction<@Nullable R> mapper, Supplier<@Nullable R> orElse) {
        return (value >= min && value <= max) ? mapper.apply(value) : orElse.get();
    }
    
    ///
    /// Maps the given short value to a new value using the given mapper function if the value is within the given range.
    /// If the `value` is outside the given range, the `orElse` supplier's value is returned.
    ///
    /// Both the mapper function and the `orElse` supplier must not return `null`.
    ///
    /// @param value  The short value to map.
    /// @param min    The lower bound of the range, inclusive.
    /// @param max    The upper bound of the range, inclusive.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is outside the given range.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is outside the given range.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` supplier returns `null`.
    /// @see #mapIfInRange(short, short, short, ShortFunction)
    /// @see #mapNonNullIfInRange(short, short, short, ShortFunction)
    /// @see #mapIfInRangeOrElse(short, short, short, ShortFunction, Object)
    /// @see #mapNonNullIfInRangeOrElse(short, short, short, ShortFunction, Object)
    /// @see #mapIfInRangeOrElseGet(short, short, short, ShortFunction, Supplier)
    ///
    public static <R> R mapNonNullIfInRangeOrElseGet(short value, short min, short max, ShortFunction<R> mapper, Supplier<R> orElse) {
        return (value >= min && value <= max) ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse.get());
    }
}
