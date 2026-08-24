package dev.satherov.zelqro.mapping;

import lombok.experimental.UtilityClass;

import dev.satherov.zelqro.function.FloatFunction;
import dev.satherov.zelqro.predicate.FloatPredicate;

import org.jspecify.annotations.Nullable;

import java.util.Objects;
import java.util.function.Supplier;

///
/// Every ordered comparison against `NaN` is `false`, so a `NaN` value satisfies none of these conditions
/// except the `NotZero` family, which it always satisfies.
///
/// Both `0.0F` and `-0.0F` count as zero.
///
@UtilityClass
public class FloatMapping {
    
    // ========== PREDICATE ==========
    
    ///
    /// Maps the given float value to a new value using the given mapper function if the predicate returns `true`.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value     The float value to map.
    /// @param predicate The predicate to check against.
    /// @param mapper    The mapper function.
    ///
    /// @return The mapped value, or `null` if the predicate returns `false`.
    ///
    /// @see #mapNonNullIf(float, FloatPredicate, FloatFunction)
    /// @see #mapIfOrElse(float, FloatPredicate, FloatFunction, Object)
    /// @see #mapNonNullIfOrElse(float, FloatPredicate, FloatFunction, Object)
    /// @see #mapIfOrElseGet(float, FloatPredicate, FloatFunction, Supplier)
    /// @see #mapNonNullIfOrElseGet(float, FloatPredicate, FloatFunction, Supplier)
    ///
    public static <R> @Nullable R mapIf(float value, FloatPredicate predicate, FloatFunction<@Nullable R> mapper) {
        return predicate.test(value) ? mapper.apply(value) : null;
    }
    
    ///
    /// Maps the given float value to a new value using the given mapper function if the predicate returns `true`.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value     The float value to map.
    /// @param predicate The predicate to check against.
    /// @param mapper    The mapper function.
    ///
    /// @return The mapped value, or `null` if the predicate returns `false`.
    ///
    /// @throws NullPointerException If the mapper function returns `null`.
    /// @see #mapIf(float, FloatPredicate, FloatFunction)
    /// @see #mapIfOrElse(float, FloatPredicate, FloatFunction, Object)
    /// @see #mapNonNullIfOrElse(float, FloatPredicate, FloatFunction, Object)
    /// @see #mapIfOrElseGet(float, FloatPredicate, FloatFunction, Supplier)
    /// @see #mapNonNullIfOrElseGet(float, FloatPredicate, FloatFunction, Supplier)
    ///
    public static <R> @Nullable R mapNonNullIf(float value, FloatPredicate predicate, FloatFunction<R> mapper) {
        return predicate.test(value) ? Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") : null;
    }
    
    ///
    /// Maps the given float value to a new value using the given mapper function if the predicate returns `true`.
    /// If the predicate returns `false`, the `orElse` value is returned.
    ///
    /// The mapper function and the `orElse` value may be `null`.
    ///
    /// @param value     The float value to map.
    /// @param predicate The predicate to check against.
    /// @param mapper    The mapper function.
    /// @param orElse    The value to return if the predicate returns `false`.
    ///
    /// @return The mapped value, or `orElse` if the predicate returns `false`.
    ///
    /// @see #mapIf(float, FloatPredicate, FloatFunction)
    /// @see #mapNonNullIf(float, FloatPredicate, FloatFunction)
    /// @see #mapNonNullIfOrElse(float, FloatPredicate, FloatFunction, Object)
    /// @see #mapIfOrElseGet(float, FloatPredicate, FloatFunction, Supplier)
    /// @see #mapNonNullIfOrElseGet(float, FloatPredicate, FloatFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfOrElse(float value, FloatPredicate predicate, FloatFunction<@Nullable R> mapper, @Nullable R orElse) {
        return predicate.test(value) ? mapper.apply(value) : orElse;
    }
    
    ///
    /// Maps the given float value to a new value using the given mapper function if the predicate returns `true`.
    /// If the predicate returns `false`, the `orElse` value is returned.
    ///
    /// Both the mapper function and the `orElse` value must not return `null`.
    ///
    /// @param value     The float value to map.
    /// @param predicate The predicate to check against.
    /// @param mapper    The mapper function.
    /// @param orElse    The value to return if the predicate returns `false`.
    ///
    /// @return The mapped value, or `orElse` if the predicate returns `false`.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` value is `null`.
    /// @see #mapIf(float, FloatPredicate, FloatFunction)
    /// @see #mapNonNullIf(float, FloatPredicate, FloatFunction)
    /// @see #mapIfOrElse(float, FloatPredicate, FloatFunction, Object)
    /// @see #mapIfOrElseGet(float, FloatPredicate, FloatFunction, Supplier)
    /// @see #mapNonNullIfOrElseGet(float, FloatPredicate, FloatFunction, Supplier)
    ///
    public static <R> R mapNonNullIfOrElse(float value, FloatPredicate predicate, FloatFunction<R> mapper, R orElse) {
        return predicate.test(value) ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse);
    }
    
    ///
    /// Maps the given float value to a new value using the given mapper function if the predicate returns `true`.
    /// If the predicate returns `false`, the `orElse` supplier's value is returned.
    ///
    /// The mapper function and the `orElse` supplier may be `null`.
    ///
    /// @param value     The float value to map.
    /// @param predicate The predicate to check against.
    /// @param mapper    The mapper function.
    /// @param orElse    The supplier of the value to return if the predicate returns `false`.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the predicate returns `false`.
    ///
    /// @see #mapIf(float, FloatPredicate, FloatFunction)
    /// @see #mapNonNullIf(float, FloatPredicate, FloatFunction)
    /// @see #mapIfOrElse(float, FloatPredicate, FloatFunction, Object)
    /// @see #mapNonNullIfOrElse(float, FloatPredicate, FloatFunction, Object)
    /// @see #mapNonNullIfOrElseGet(float, FloatPredicate, FloatFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfOrElseGet(float value, FloatPredicate predicate, FloatFunction<@Nullable R> mapper, Supplier<@Nullable R> orElse) {
        return predicate.test(value) ? mapper.apply(value) : orElse.get();
    }
    
    ///
    /// Maps the given float value to a new value using the given mapper function if the predicate returns `true`.
    /// If the predicate returns `false`, the `orElse` supplier's value is returned.
    ///
    /// Both the mapper function and the `orElse` supplier must not return `null`.
    ///
    /// @param value     The float value to map.
    /// @param predicate The predicate to check against.
    /// @param mapper    The mapper function.
    /// @param orElse    The supplier of the value to return if the predicate returns `false`.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the predicate returns `false`.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` supplier returns `null`.
    /// @see #mapIf(float, FloatPredicate, FloatFunction)
    /// @see #mapNonNullIf(float, FloatPredicate, FloatFunction)
    /// @see #mapIfOrElse(float, FloatPredicate, FloatFunction, Object)
    /// @see #mapNonNullIfOrElse(float, FloatPredicate, FloatFunction, Object)
    /// @see #mapIfOrElseGet(float, FloatPredicate, FloatFunction, Supplier)
    ///
    public static <R> R mapNonNullIfOrElseGet(float value, FloatPredicate predicate, FloatFunction<R> mapper, Supplier<R> orElse) {
        return predicate.test(value) ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse.get());
    }
    
    // ========== POSITIVE ==========
    
    ///
    /// Maps the given float value to a new value using the given mapper function if the value is positive.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value  The float value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is not positive.
    ///
    /// @see #mapNonNullIfPositive(float, FloatFunction)
    /// @see #mapIfPositiveOrElse(float, FloatFunction, Object)
    /// @see #mapNonNullIfPositiveOrElse(float, FloatFunction, Object)
    /// @see #mapIfPositiveOrElseGet(float, FloatFunction, Supplier)
    /// @see #mapNonNullIfPositiveOrElseGet(float, FloatFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfPositive(float value, FloatFunction<@Nullable R> mapper) {
        return value > 0 ? mapper.apply(value) : null;
    }
    
    ///
    /// Maps the given float value to a new value using the given mapper function if the value is positive.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value  The float value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is not positive.
    ///
    /// @throws NullPointerException If the mapper function returns `null`.
    /// @see #mapIfPositive(float, FloatFunction)
    /// @see #mapIfPositiveOrElse(float, FloatFunction, Object)
    /// @see #mapNonNullIfPositiveOrElse(float, FloatFunction, Object)
    /// @see #mapIfPositiveOrElseGet(float, FloatFunction, Supplier)
    /// @see #mapNonNullIfPositiveOrElseGet(float, FloatFunction, Supplier)
    ///
    public static <R> @Nullable R mapNonNullIfPositive(float value, FloatFunction<R> mapper) {
        return value > 0 ? Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") : null;
    }
    
    ///
    /// Maps the given float value to a new value using the given mapper function if the value is positive.
    /// If the `value` is not positive, the `orElse` value is returned.
    ///
    /// The mapper function and the `orElse` value may be `null`.
    ///
    /// @param value  The float value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is not positive.
    ///
    /// @return The mapped value, or `orElse` if the `value` is not positive.
    ///
    /// @see #mapIfPositive(float, FloatFunction)
    /// @see #mapNonNullIfPositive(float, FloatFunction)
    /// @see #mapNonNullIfPositiveOrElse(float, FloatFunction, Object)
    /// @see #mapIfPositiveOrElseGet(float, FloatFunction, Supplier)
    /// @see #mapNonNullIfPositiveOrElseGet(float, FloatFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfPositiveOrElse(float value, FloatFunction<@Nullable R> mapper, @Nullable R orElse) {
        return value > 0 ? mapper.apply(value) : orElse;
    }
    
    ///
    /// Maps the given float value to a new value using the given mapper function if the value is positive.
    /// If the `value` is not positive, the `orElse` value is returned.
    ///
    /// Both the mapper function and the `orElse` value must not return `null`.
    ///
    /// @param value  The float value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is not positive.
    ///
    /// @return The mapped value, or `orElse` if the `value` is not positive.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` value is `null`.
    /// @see #mapIfPositive(float, FloatFunction)
    /// @see #mapNonNullIfPositive(float, FloatFunction)
    /// @see #mapIfPositiveOrElse(float, FloatFunction, Object)
    /// @see #mapIfPositiveOrElseGet(float, FloatFunction, Supplier)
    /// @see #mapNonNullIfPositiveOrElseGet(float, FloatFunction, Supplier)
    ///
    public static <R> R mapNonNullIfPositiveOrElse(float value, FloatFunction<R> mapper, R orElse) {
        return value > 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse);
    }
    
    ///
    /// Maps the given float value to a new value using the given mapper function if the value is positive.
    /// If the `value` is not positive, the `orElse` supplier's value is returned.
    ///
    /// The mapper function and the `orElse` supplier may be `null`.
    ///
    /// @param value  The float value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is not positive.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is not positive.
    ///
    /// @see #mapIfPositive(float, FloatFunction)
    /// @see #mapNonNullIfPositive(float, FloatFunction)
    /// @see #mapIfPositiveOrElse(float, FloatFunction, Object)
    /// @see #mapNonNullIfPositiveOrElse(float, FloatFunction, Object)
    /// @see #mapNonNullIfPositiveOrElseGet(float, FloatFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfPositiveOrElseGet(float value, FloatFunction<@Nullable R> mapper, Supplier<@Nullable R> orElse) {
        return value > 0 ? mapper.apply(value) : orElse.get();
    }
    
    ///
    /// Maps the given float value to a new value using the given mapper function if the value is positive.
    /// If the `value` is not positive, the `orElse` supplier's value is returned.
    ///
    /// Both the mapper function and the `orElse` supplier must not return `null`.
    ///
    /// @param value  The float value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is not positive.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is not positive.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` supplier returns `null`.
    /// @see #mapIfPositive(float, FloatFunction)
    /// @see #mapNonNullIfPositive(float, FloatFunction)
    /// @see #mapIfPositiveOrElse(float, FloatFunction, Object)
    /// @see #mapNonNullIfPositiveOrElse(float, FloatFunction, Object)
    /// @see #mapIfPositiveOrElseGet(float, FloatFunction, Supplier)
    ///
    public static <R> R mapNonNullIfPositiveOrElseGet(float value, FloatFunction<R> mapper, Supplier<R> orElse) {
        return value > 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse.get());
    }
    
    // ========== NEGATIVE ==========
    
    ///
    /// Maps the given float value to a new value using the given mapper function if the value is negative.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value  The float value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is not negative.
    ///
    /// @see #mapNonNullIfNegative(float, FloatFunction)
    /// @see #mapIfNegativeOrElse(float, FloatFunction, Object)
    /// @see #mapNonNullIfNegativeOrElse(float, FloatFunction, Object)
    /// @see #mapIfNegativeOrElseGet(float, FloatFunction, Supplier)
    /// @see #mapNonNullIfNegativeOrElseGet(float, FloatFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNegative(float value, FloatFunction<@Nullable R> mapper) {
        return value < 0 ? mapper.apply(value) : null;
    }
    
    ///
    /// Maps the given float value to a new value using the given mapper function if the value is negative.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value  The float value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is not negative.
    ///
    /// @throws NullPointerException If the mapper function returns `null`.
    /// @see #mapIfNegative(float, FloatFunction)
    /// @see #mapIfNegativeOrElse(float, FloatFunction, Object)
    /// @see #mapNonNullIfNegativeOrElse(float, FloatFunction, Object)
    /// @see #mapIfNegativeOrElseGet(float, FloatFunction, Supplier)
    /// @see #mapNonNullIfNegativeOrElseGet(float, FloatFunction, Supplier)
    ///
    public static <R> @Nullable R mapNonNullIfNegative(float value, FloatFunction<R> mapper) {
        return value < 0 ? Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") : null;
    }
    
    ///
    /// Maps the given float value to a new value using the given mapper function if the value is negative.
    /// If the `value` is not negative, the `orElse` value is returned.
    ///
    /// The mapper function and the `orElse` value may be `null`.
    ///
    /// @param value  The float value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is not negative.
    ///
    /// @return The mapped value, or `orElse` if the `value` is not negative.
    ///
    /// @see #mapIfNegative(float, FloatFunction)
    /// @see #mapNonNullIfNegative(float, FloatFunction)
    /// @see #mapNonNullIfNegativeOrElse(float, FloatFunction, Object)
    /// @see #mapIfNegativeOrElseGet(float, FloatFunction, Supplier)
    /// @see #mapNonNullIfNegativeOrElseGet(float, FloatFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNegativeOrElse(float value, FloatFunction<@Nullable R> mapper, @Nullable R orElse) {
        return value < 0 ? mapper.apply(value) : orElse;
    }
    
    ///
    /// Maps the given float value to a new value using the given mapper function if the value is negative.
    /// If the `value` is not negative, the `orElse` value is returned.
    ///
    /// Both the mapper function and the `orElse` value must not return `null`.
    ///
    /// @param value  The float value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is not negative.
    ///
    /// @return The mapped value, or `orElse` if the `value` is not negative.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` value is `null`.
    /// @see #mapIfNegative(float, FloatFunction)
    /// @see #mapNonNullIfNegative(float, FloatFunction)
    /// @see #mapIfNegativeOrElse(float, FloatFunction, Object)
    /// @see #mapIfNegativeOrElseGet(float, FloatFunction, Supplier)
    /// @see #mapNonNullIfNegativeOrElseGet(float, FloatFunction, Supplier)
    ///
    public static <R> R mapNonNullIfNegativeOrElse(float value, FloatFunction<R> mapper, R orElse) {
        return value < 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse);
    }
    
    ///
    /// Maps the given float value to a new value using the given mapper function if the value is negative.
    /// If the `value` is not negative, the `orElse` supplier's value is returned.
    ///
    /// The mapper function and the `orElse` supplier may be `null`.
    ///
    /// @param value  The float value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is not negative.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is not negative.
    ///
    /// @see #mapIfNegative(float, FloatFunction)
    /// @see #mapNonNullIfNegative(float, FloatFunction)
    /// @see #mapIfNegativeOrElse(float, FloatFunction, Object)
    /// @see #mapNonNullIfNegativeOrElse(float, FloatFunction, Object)
    /// @see #mapNonNullIfNegativeOrElseGet(float, FloatFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNegativeOrElseGet(float value, FloatFunction<@Nullable R> mapper, Supplier<@Nullable R> orElse) {
        return value < 0 ? mapper.apply(value) : orElse.get();
    }
    
    ///
    /// Maps the given float value to a new value using the given mapper function if the value is negative.
    /// If the `value` is not negative, the `orElse` supplier's value is returned.
    ///
    /// Both the mapper function and the `orElse` supplier must not return `null`.
    ///
    /// @param value  The float value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is not negative.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is not negative.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` supplier returns `null`.
    /// @see #mapIfNegative(float, FloatFunction)
    /// @see #mapNonNullIfNegative(float, FloatFunction)
    /// @see #mapIfNegativeOrElse(float, FloatFunction, Object)
    /// @see #mapNonNullIfNegativeOrElse(float, FloatFunction, Object)
    /// @see #mapIfNegativeOrElseGet(float, FloatFunction, Supplier)
    ///
    public static <R> R mapNonNullIfNegativeOrElseGet(float value, FloatFunction<R> mapper, Supplier<R> orElse) {
        return value < 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse.get());
    }
    
    // ========== NOT POSITIVE ==========
    
    ///
    /// Maps the given float value to a new value using the given mapper function if the value is not positive.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value  The float value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is positive.
    ///
    /// @see #mapNonNullIfNotPositive(float, FloatFunction)
    /// @see #mapIfNotPositiveOrElse(float, FloatFunction, Object)
    /// @see #mapNonNullIfNotPositiveOrElse(float, FloatFunction, Object)
    /// @see #mapIfNotPositiveOrElseGet(float, FloatFunction, Supplier)
    /// @see #mapNonNullIfNotPositiveOrElseGet(float, FloatFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNotPositive(float value, FloatFunction<@Nullable R> mapper) {
        return value <= 0 ? mapper.apply(value) : null;
    }
    
    ///
    /// Maps the given float value to a new value using the given mapper function if the value is not positive.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value  The float value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is positive.
    ///
    /// @throws NullPointerException If the mapper function returns `null`.
    /// @see #mapIfNotPositive(float, FloatFunction)
    /// @see #mapIfNotPositiveOrElse(float, FloatFunction, Object)
    /// @see #mapNonNullIfNotPositiveOrElse(float, FloatFunction, Object)
    /// @see #mapIfNotPositiveOrElseGet(float, FloatFunction, Supplier)
    /// @see #mapNonNullIfNotPositiveOrElseGet(float, FloatFunction, Supplier)
    ///
    public static <R> @Nullable R mapNonNullIfNotPositive(float value, FloatFunction<R> mapper) {
        return value <= 0 ? Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") : null;
    }
    
    ///
    /// Maps the given float value to a new value using the given mapper function if the value is not positive.
    /// If the `value` is positive, the `orElse` value is returned.
    ///
    /// The mapper function and the `orElse` value may be `null`.
    ///
    /// @param value  The float value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is positive.
    ///
    /// @return The mapped value, or `orElse` if the `value` is positive.
    ///
    /// @see #mapIfNotPositive(float, FloatFunction)
    /// @see #mapNonNullIfNotPositive(float, FloatFunction)
    /// @see #mapNonNullIfNotPositiveOrElse(float, FloatFunction, Object)
    /// @see #mapIfNotPositiveOrElseGet(float, FloatFunction, Supplier)
    /// @see #mapNonNullIfNotPositiveOrElseGet(float, FloatFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNotPositiveOrElse(float value, FloatFunction<@Nullable R> mapper, @Nullable R orElse) {
        return value <= 0 ? mapper.apply(value) : orElse;
    }
    
    ///
    /// Maps the given float value to a new value using the given mapper function if the value is not positive.
    /// If the `value` is positive, the `orElse` value is returned.
    ///
    /// Both the mapper function and the `orElse` value must not return `null`.
    ///
    /// @param value  The float value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is positive.
    ///
    /// @return The mapped value, or `orElse` if the `value` is positive.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` value is `null`.
    /// @see #mapIfNotPositive(float, FloatFunction)
    /// @see #mapNonNullIfNotPositive(float, FloatFunction)
    /// @see #mapIfNotPositiveOrElse(float, FloatFunction, Object)
    /// @see #mapIfNotPositiveOrElseGet(float, FloatFunction, Supplier)
    /// @see #mapNonNullIfNotPositiveOrElseGet(float, FloatFunction, Supplier)
    ///
    public static <R> R mapNonNullIfNotPositiveOrElse(float value, FloatFunction<R> mapper, R orElse) {
        return value <= 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse);
    }
    
    ///
    /// Maps the given float value to a new value using the given mapper function if the value is not positive.
    /// If the `value` is positive, the `orElse` supplier's value is returned.
    ///
    /// The mapper function and the `orElse` supplier may be `null`.
    ///
    /// @param value  The float value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is positive.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is positive.
    ///
    /// @see #mapIfNotPositive(float, FloatFunction)
    /// @see #mapNonNullIfNotPositive(float, FloatFunction)
    /// @see #mapIfNotPositiveOrElse(float, FloatFunction, Object)
    /// @see #mapNonNullIfNotPositiveOrElse(float, FloatFunction, Object)
    /// @see #mapNonNullIfNotPositiveOrElseGet(float, FloatFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNotPositiveOrElseGet(float value, FloatFunction<@Nullable R> mapper, Supplier<@Nullable R> orElse) {
        return value <= 0 ? mapper.apply(value) : orElse.get();
    }
    
    ///
    /// Maps the given float value to a new value using the given mapper function if the value is not positive.
    /// If the `value` is positive, the `orElse` supplier's value is returned.
    ///
    /// Both the mapper function and the `orElse` supplier must not return `null`.
    ///
    /// @param value  The float value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is positive.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is positive.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` supplier returns `null`.
    /// @see #mapIfNotPositive(float, FloatFunction)
    /// @see #mapNonNullIfNotPositive(float, FloatFunction)
    /// @see #mapIfNotPositiveOrElse(float, FloatFunction, Object)
    /// @see #mapNonNullIfNotPositiveOrElse(float, FloatFunction, Object)
    /// @see #mapIfNotPositiveOrElseGet(float, FloatFunction, Supplier)
    ///
    public static <R> R mapNonNullIfNotPositiveOrElseGet(float value, FloatFunction<R> mapper, Supplier<R> orElse) {
        return value <= 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse.get());
    }
    
    // ========== NOT NEGATIVE ==========
    
    ///
    /// Maps the given float value to a new value using the given mapper function if the value is not negative.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value  The float value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is negative.
    ///
    /// @see #mapNonNullIfNotNegative(float, FloatFunction)
    /// @see #mapIfNotNegativeOrElse(float, FloatFunction, Object)
    /// @see #mapNonNullIfNotNegativeOrElse(float, FloatFunction, Object)
    /// @see #mapIfNotNegativeOrElseGet(float, FloatFunction, Supplier)
    /// @see #mapNonNullIfNotNegativeOrElseGet(float, FloatFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNotNegative(float value, FloatFunction<@Nullable R> mapper) {
        return value >= 0 ? mapper.apply(value) : null;
    }
    
    ///
    /// Maps the given float value to a new value using the given mapper function if the value is not negative.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value  The float value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is negative.
    ///
    /// @throws NullPointerException If the mapper function returns `null`.
    /// @see #mapIfNotNegative(float, FloatFunction)
    /// @see #mapIfNotNegativeOrElse(float, FloatFunction, Object)
    /// @see #mapNonNullIfNotNegativeOrElse(float, FloatFunction, Object)
    /// @see #mapIfNotNegativeOrElseGet(float, FloatFunction, Supplier)
    /// @see #mapNonNullIfNotNegativeOrElseGet(float, FloatFunction, Supplier)
    ///
    public static <R> @Nullable R mapNonNullIfNotNegative(float value, FloatFunction<R> mapper) {
        return value >= 0 ? Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") : null;
    }
    
    ///
    /// Maps the given float value to a new value using the given mapper function if the value is not negative.
    /// If the `value` is negative, the `orElse` value is returned.
    ///
    /// The mapper function and the `orElse` value may be `null`.
    ///
    /// @param value  The float value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is negative.
    ///
    /// @return The mapped value, or `orElse` if the `value` is negative.
    ///
    /// @see #mapIfNotNegative(float, FloatFunction)
    /// @see #mapNonNullIfNotNegative(float, FloatFunction)
    /// @see #mapNonNullIfNotNegativeOrElse(float, FloatFunction, Object)
    /// @see #mapIfNotNegativeOrElseGet(float, FloatFunction, Supplier)
    /// @see #mapNonNullIfNotNegativeOrElseGet(float, FloatFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNotNegativeOrElse(float value, FloatFunction<@Nullable R> mapper, @Nullable R orElse) {
        return value >= 0 ? mapper.apply(value) : orElse;
    }
    
    ///
    /// Maps the given float value to a new value using the given mapper function if the value is not negative.
    /// If the `value` is negative, the `orElse` value is returned.
    ///
    /// Both the mapper function and the `orElse` value must not return `null`.
    ///
    /// @param value  The float value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is negative.
    ///
    /// @return The mapped value, or `orElse` if the `value` is negative.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` value is `null`.
    /// @see #mapIfNotNegative(float, FloatFunction)
    /// @see #mapNonNullIfNotNegative(float, FloatFunction)
    /// @see #mapIfNotNegativeOrElse(float, FloatFunction, Object)
    /// @see #mapIfNotNegativeOrElseGet(float, FloatFunction, Supplier)
    /// @see #mapNonNullIfNotNegativeOrElseGet(float, FloatFunction, Supplier)
    ///
    public static <R> R mapNonNullIfNotNegativeOrElse(float value, FloatFunction<R> mapper, R orElse) {
        return value >= 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse);
    }
    
    ///
    /// Maps the given float value to a new value using the given mapper function if the value is not negative.
    /// If the `value` is negative, the `orElse` supplier's value is returned.
    ///
    /// The mapper function and the `orElse` supplier may be `null`.
    ///
    /// @param value  The float value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is negative.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is negative.
    ///
    /// @see #mapIfNotNegative(float, FloatFunction)
    /// @see #mapNonNullIfNotNegative(float, FloatFunction)
    /// @see #mapIfNotNegativeOrElse(float, FloatFunction, Object)
    /// @see #mapNonNullIfNotNegativeOrElse(float, FloatFunction, Object)
    /// @see #mapNonNullIfNotNegativeOrElseGet(float, FloatFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNotNegativeOrElseGet(float value, FloatFunction<@Nullable R> mapper, Supplier<@Nullable R> orElse) {
        return value >= 0 ? mapper.apply(value) : orElse.get();
    }
    
    ///
    /// Maps the given float value to a new value using the given mapper function if the value is not negative.
    /// If the `value` is negative, the `orElse` supplier's value is returned.
    ///
    /// Both the mapper function and the `orElse` supplier must not return `null`.
    ///
    /// @param value  The float value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is negative.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is negative.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` supplier returns `null`.
    /// @see #mapIfNotNegative(float, FloatFunction)
    /// @see #mapNonNullIfNotNegative(float, FloatFunction)
    /// @see #mapIfNotNegativeOrElse(float, FloatFunction, Object)
    /// @see #mapNonNullIfNotNegativeOrElse(float, FloatFunction, Object)
    /// @see #mapIfNotNegativeOrElseGet(float, FloatFunction, Supplier)
    ///
    public static <R> R mapNonNullIfNotNegativeOrElseGet(float value, FloatFunction<R> mapper, Supplier<R> orElse) {
        return value >= 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse.get());
    }
    
    // ========== ZERO ==========
    
    ///
    /// Maps the given float value to a new value using the given mapper function if the value is zero.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value  The float value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is not zero.
    ///
    /// @see #mapNonNullIfZero(float, FloatFunction)
    /// @see #mapIfZeroOrElse(float, FloatFunction, Object)
    /// @see #mapNonNullIfZeroOrElse(float, FloatFunction, Object)
    /// @see #mapIfZeroOrElseGet(float, FloatFunction, Supplier)
    /// @see #mapNonNullIfZeroOrElseGet(float, FloatFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfZero(float value, FloatFunction<@Nullable R> mapper) {
        return value == 0 ? mapper.apply(value) : null;
    }
    
    ///
    /// Maps the given float value to a new value using the given mapper function if the value is zero.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value  The float value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is not zero.
    ///
    /// @throws NullPointerException If the mapper function returns `null`.
    /// @see #mapIfZero(float, FloatFunction)
    /// @see #mapIfZeroOrElse(float, FloatFunction, Object)
    /// @see #mapNonNullIfZeroOrElse(float, FloatFunction, Object)
    /// @see #mapIfZeroOrElseGet(float, FloatFunction, Supplier)
    /// @see #mapNonNullIfZeroOrElseGet(float, FloatFunction, Supplier)
    ///
    public static <R> @Nullable R mapNonNullIfZero(float value, FloatFunction<R> mapper) {
        return value == 0 ? Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") : null;
    }
    
    ///
    /// Maps the given float value to a new value using the given mapper function if the value is zero.
    /// If the `value` is not zero, the `orElse` value is returned.
    ///
    /// The mapper function and the `orElse` value may be `null`.
    ///
    /// @param value  The float value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is not zero.
    ///
    /// @return The mapped value, or `orElse` if the `value` is not zero.
    ///
    /// @see #mapIfZero(float, FloatFunction)
    /// @see #mapNonNullIfZero(float, FloatFunction)
    /// @see #mapNonNullIfZeroOrElse(float, FloatFunction, Object)
    /// @see #mapIfZeroOrElseGet(float, FloatFunction, Supplier)
    /// @see #mapNonNullIfZeroOrElseGet(float, FloatFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfZeroOrElse(float value, FloatFunction<@Nullable R> mapper, @Nullable R orElse) {
        return value == 0 ? mapper.apply(value) : orElse;
    }
    
    ///
    /// Maps the given float value to a new value using the given mapper function if the value is zero.
    /// If the `value` is not zero, the `orElse` value is returned.
    ///
    /// Both the mapper function and the `orElse` value must not return `null`.
    ///
    /// @param value  The float value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is not zero.
    ///
    /// @return The mapped value, or `orElse` if the `value` is not zero.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` value is `null`.
    /// @see #mapIfZero(float, FloatFunction)
    /// @see #mapNonNullIfZero(float, FloatFunction)
    /// @see #mapIfZeroOrElse(float, FloatFunction, Object)
    /// @see #mapIfZeroOrElseGet(float, FloatFunction, Supplier)
    /// @see #mapNonNullIfZeroOrElseGet(float, FloatFunction, Supplier)
    ///
    public static <R> R mapNonNullIfZeroOrElse(float value, FloatFunction<R> mapper, R orElse) {
        return value == 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse);
    }
    
    ///
    /// Maps the given float value to a new value using the given mapper function if the value is zero.
    /// If the `value` is not zero, the `orElse` supplier's value is returned.
    ///
    /// The mapper function and the `orElse` supplier may be `null`.
    ///
    /// @param value  The float value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is not zero.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is not zero.
    ///
    /// @see #mapIfZero(float, FloatFunction)
    /// @see #mapNonNullIfZero(float, FloatFunction)
    /// @see #mapIfZeroOrElse(float, FloatFunction, Object)
    /// @see #mapNonNullIfZeroOrElse(float, FloatFunction, Object)
    /// @see #mapNonNullIfZeroOrElseGet(float, FloatFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfZeroOrElseGet(float value, FloatFunction<@Nullable R> mapper, Supplier<@Nullable R> orElse) {
        return value == 0 ? mapper.apply(value) : orElse.get();
    }
    
    ///
    /// Maps the given float value to a new value using the given mapper function if the value is zero.
    /// If the `value` is not zero, the `orElse` supplier's value is returned.
    ///
    /// Both the mapper function and the `orElse` supplier must not return `null`.
    ///
    /// @param value  The float value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is not zero.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is not zero.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` supplier returns `null`.
    /// @see #mapIfZero(float, FloatFunction)
    /// @see #mapNonNullIfZero(float, FloatFunction)
    /// @see #mapIfZeroOrElse(float, FloatFunction, Object)
    /// @see #mapNonNullIfZeroOrElse(float, FloatFunction, Object)
    /// @see #mapIfZeroOrElseGet(float, FloatFunction, Supplier)
    ///
    public static <R> R mapNonNullIfZeroOrElseGet(float value, FloatFunction<R> mapper, Supplier<R> orElse) {
        return value == 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse.get());
    }
    
    // ========== NOT ZERO ==========
    
    ///
    /// Maps the given float value to a new value using the given mapper function if the value is not zero.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value  The float value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is zero.
    ///
    /// @see #mapNonNullIfNotZero(float, FloatFunction)
    /// @see #mapIfNotZeroOrElse(float, FloatFunction, Object)
    /// @see #mapNonNullIfNotZeroOrElse(float, FloatFunction, Object)
    /// @see #mapIfNotZeroOrElseGet(float, FloatFunction, Supplier)
    /// @see #mapNonNullIfNotZeroOrElseGet(float, FloatFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNotZero(float value, FloatFunction<@Nullable R> mapper) {
        return value != 0 ? mapper.apply(value) : null;
    }
    
    ///
    /// Maps the given float value to a new value using the given mapper function if the value is not zero.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value  The float value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is zero.
    ///
    /// @throws NullPointerException If the mapper function returns `null`.
    /// @see #mapIfNotZero(float, FloatFunction)
    /// @see #mapIfNotZeroOrElse(float, FloatFunction, Object)
    /// @see #mapNonNullIfNotZeroOrElse(float, FloatFunction, Object)
    /// @see #mapIfNotZeroOrElseGet(float, FloatFunction, Supplier)
    /// @see #mapNonNullIfNotZeroOrElseGet(float, FloatFunction, Supplier)
    ///
    public static <R> @Nullable R mapNonNullIfNotZero(float value, FloatFunction<R> mapper) {
        return value != 0 ? Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") : null;
    }
    
    ///
    /// Maps the given float value to a new value using the given mapper function if the value is not zero.
    /// If the `value` is zero, the `orElse` value is returned.
    ///
    /// The mapper function and the `orElse` value may be `null`.
    ///
    /// @param value  The float value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is zero.
    ///
    /// @return The mapped value, or `orElse` if the `value` is zero.
    ///
    /// @see #mapIfNotZero(float, FloatFunction)
    /// @see #mapNonNullIfNotZero(float, FloatFunction)
    /// @see #mapNonNullIfNotZeroOrElse(float, FloatFunction, Object)
    /// @see #mapIfNotZeroOrElseGet(float, FloatFunction, Supplier)
    /// @see #mapNonNullIfNotZeroOrElseGet(float, FloatFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNotZeroOrElse(float value, FloatFunction<@Nullable R> mapper, @Nullable R orElse) {
        return value != 0 ? mapper.apply(value) : orElse;
    }
    
    ///
    /// Maps the given float value to a new value using the given mapper function if the value is not zero.
    /// If the `value` is zero, the `orElse` value is returned.
    ///
    /// Both the mapper function and the `orElse` value must not return `null`.
    ///
    /// @param value  The float value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is zero.
    ///
    /// @return The mapped value, or `orElse` if the `value` is zero.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` value is `null`.
    /// @see #mapIfNotZero(float, FloatFunction)
    /// @see #mapNonNullIfNotZero(float, FloatFunction)
    /// @see #mapIfNotZeroOrElse(float, FloatFunction, Object)
    /// @see #mapIfNotZeroOrElseGet(float, FloatFunction, Supplier)
    /// @see #mapNonNullIfNotZeroOrElseGet(float, FloatFunction, Supplier)
    ///
    public static <R> R mapNonNullIfNotZeroOrElse(float value, FloatFunction<R> mapper, R orElse) {
        return value != 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse);
    }
    
    ///
    /// Maps the given float value to a new value using the given mapper function if the value is not zero.
    /// If the `value` is zero, the `orElse` supplier's value is returned.
    ///
    /// The mapper function and the `orElse` supplier may be `null`.
    ///
    /// @param value  The float value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is zero.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is zero.
    ///
    /// @see #mapIfNotZero(float, FloatFunction)
    /// @see #mapNonNullIfNotZero(float, FloatFunction)
    /// @see #mapIfNotZeroOrElse(float, FloatFunction, Object)
    /// @see #mapNonNullIfNotZeroOrElse(float, FloatFunction, Object)
    /// @see #mapNonNullIfNotZeroOrElseGet(float, FloatFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNotZeroOrElseGet(float value, FloatFunction<@Nullable R> mapper, Supplier<@Nullable R> orElse) {
        return value != 0 ? mapper.apply(value) : orElse.get();
    }
    
    ///
    /// Maps the given float value to a new value using the given mapper function if the value is not zero.
    /// If the `value` is zero, the `orElse` supplier's value is returned.
    ///
    /// Both the mapper function and the `orElse` supplier must not return `null`.
    ///
    /// @param value  The float value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is zero.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is zero.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` supplier returns `null`.
    /// @see #mapIfNotZero(float, FloatFunction)
    /// @see #mapNonNullIfNotZero(float, FloatFunction)
    /// @see #mapIfNotZeroOrElse(float, FloatFunction, Object)
    /// @see #mapNonNullIfNotZeroOrElse(float, FloatFunction, Object)
    /// @see #mapIfNotZeroOrElseGet(float, FloatFunction, Supplier)
    ///
    public static <R> R mapNonNullIfNotZeroOrElseGet(float value, FloatFunction<R> mapper, Supplier<R> orElse) {
        return value != 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse.get());
    }
    
    // ========== IN RANGE ==========
    
    ///
    /// Maps the given float value to a new value using the given mapper function if the value is within the given range.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value  The float value to map.
    /// @param min    The lower bound of the range, inclusive.
    /// @param max    The upper bound of the range, inclusive.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is outside the given range.
    ///
    /// @see #mapNonNullIfInRange(float, float, float, FloatFunction)
    /// @see #mapIfInRangeOrElse(float, float, float, FloatFunction, Object)
    /// @see #mapNonNullIfInRangeOrElse(float, float, float, FloatFunction, Object)
    /// @see #mapIfInRangeOrElseGet(float, float, float, FloatFunction, Supplier)
    /// @see #mapNonNullIfInRangeOrElseGet(float, float, float, FloatFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfInRange(float value, float min, float max, FloatFunction<@Nullable R> mapper) {
        return (value >= min && value <= max) ? mapper.apply(value) : null;
    }
    
    ///
    /// Maps the given float value to a new value using the given mapper function if the value is within the given range.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value  The float value to map.
    /// @param min    The lower bound of the range, inclusive.
    /// @param max    The upper bound of the range, inclusive.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is outside the given range.
    ///
    /// @throws NullPointerException If the mapper function returns `null`.
    /// @see #mapIfInRange(float, float, float, FloatFunction)
    /// @see #mapIfInRangeOrElse(float, float, float, FloatFunction, Object)
    /// @see #mapNonNullIfInRangeOrElse(float, float, float, FloatFunction, Object)
    /// @see #mapIfInRangeOrElseGet(float, float, float, FloatFunction, Supplier)
    /// @see #mapNonNullIfInRangeOrElseGet(float, float, float, FloatFunction, Supplier)
    ///
    public static <R> @Nullable R mapNonNullIfInRange(float value, float min, float max, FloatFunction<R> mapper) {
        return (value >= min && value <= max) ? Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") : null;
    }
    
    ///
    /// Maps the given float value to a new value using the given mapper function if the value is within the given range.
    /// If the `value` is outside the given range, the `orElse` value is returned.
    ///
    /// The mapper function and the `orElse` value may be `null`.
    ///
    /// @param value  The float value to map.
    /// @param min    The lower bound of the range, inclusive.
    /// @param max    The upper bound of the range, inclusive.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is outside the given range.
    ///
    /// @return The mapped value, or `orElse` if the `value` is outside the given range.
    ///
    /// @see #mapIfInRange(float, float, float, FloatFunction)
    /// @see #mapNonNullIfInRange(float, float, float, FloatFunction)
    /// @see #mapNonNullIfInRangeOrElse(float, float, float, FloatFunction, Object)
    /// @see #mapIfInRangeOrElseGet(float, float, float, FloatFunction, Supplier)
    /// @see #mapNonNullIfInRangeOrElseGet(float, float, float, FloatFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfInRangeOrElse(float value, float min, float max, FloatFunction<@Nullable R> mapper, @Nullable R orElse) {
        return (value >= min && value <= max) ? mapper.apply(value) : orElse;
    }
    
    ///
    /// Maps the given float value to a new value using the given mapper function if the value is within the given range.
    /// If the `value` is outside the given range, the `orElse` value is returned.
    ///
    /// Both the mapper function and the `orElse` value must not return `null`.
    ///
    /// @param value  The float value to map.
    /// @param min    The lower bound of the range, inclusive.
    /// @param max    The upper bound of the range, inclusive.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is outside the given range.
    ///
    /// @return The mapped value, or `orElse` if the `value` is outside the given range.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` value is `null`.
    /// @see #mapIfInRange(float, float, float, FloatFunction)
    /// @see #mapNonNullIfInRange(float, float, float, FloatFunction)
    /// @see #mapIfInRangeOrElse(float, float, float, FloatFunction, Object)
    /// @see #mapIfInRangeOrElseGet(float, float, float, FloatFunction, Supplier)
    /// @see #mapNonNullIfInRangeOrElseGet(float, float, float, FloatFunction, Supplier)
    ///
    public static <R> R mapNonNullIfInRangeOrElse(float value, float min, float max, FloatFunction<R> mapper, R orElse) {
        return (value >= min && value <= max) ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse);
    }
    
    ///
    /// Maps the given float value to a new value using the given mapper function if the value is within the given range.
    /// If the `value` is outside the given range, the `orElse` supplier's value is returned.
    ///
    /// The mapper function and the `orElse` supplier may be `null`.
    ///
    /// @param value  The float value to map.
    /// @param min    The lower bound of the range, inclusive.
    /// @param max    The upper bound of the range, inclusive.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is outside the given range.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is outside the given range.
    ///
    /// @see #mapIfInRange(float, float, float, FloatFunction)
    /// @see #mapNonNullIfInRange(float, float, float, FloatFunction)
    /// @see #mapIfInRangeOrElse(float, float, float, FloatFunction, Object)
    /// @see #mapNonNullIfInRangeOrElse(float, float, float, FloatFunction, Object)
    /// @see #mapNonNullIfInRangeOrElseGet(float, float, float, FloatFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfInRangeOrElseGet(float value, float min, float max, FloatFunction<@Nullable R> mapper, Supplier<@Nullable R> orElse) {
        return (value >= min && value <= max) ? mapper.apply(value) : orElse.get();
    }
    
    ///
    /// Maps the given float value to a new value using the given mapper function if the value is within the given range.
    /// If the `value` is outside the given range, the `orElse` supplier's value is returned.
    ///
    /// Both the mapper function and the `orElse` supplier must not return `null`.
    ///
    /// @param value  The float value to map.
    /// @param min    The lower bound of the range, inclusive.
    /// @param max    The upper bound of the range, inclusive.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is outside the given range.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is outside the given range.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` supplier returns `null`.
    /// @see #mapIfInRange(float, float, float, FloatFunction)
    /// @see #mapNonNullIfInRange(float, float, float, FloatFunction)
    /// @see #mapIfInRangeOrElse(float, float, float, FloatFunction, Object)
    /// @see #mapNonNullIfInRangeOrElse(float, float, float, FloatFunction, Object)
    /// @see #mapIfInRangeOrElseGet(float, float, float, FloatFunction, Supplier)
    ///
    public static <R> R mapNonNullIfInRangeOrElseGet(float value, float min, float max, FloatFunction<R> mapper, Supplier<R> orElse) {
        return (value >= min && value <= max) ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse.get());
    }
}
