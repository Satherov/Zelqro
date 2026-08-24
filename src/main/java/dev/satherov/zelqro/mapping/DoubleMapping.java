package dev.satherov.zelqro.mapping;

import lombok.experimental.UtilityClass;

import dev.satherov.zelqro.function.DoubleFunction;
import dev.satherov.zelqro.predicate.DoublePredicate;

import org.jspecify.annotations.Nullable;

import java.util.Objects;
import java.util.function.Supplier;

///
/// Every ordered comparison against `NaN` is `false`, so a `NaN` value satisfies none of these conditions
/// except the `NotZero` family, which it always satisfies.
///
/// Both `0.0D` and `-0.0D` count as zero.
///
@UtilityClass
public class DoubleMapping {
    
    // ========== PREDICATE ==========
    
    ///
    /// Maps the given double value to a new value using the given mapper function if the predicate returns `true`.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value     The double value to map.
    /// @param predicate The predicate to check against.
    /// @param mapper    The mapper function.
    ///
    /// @return The mapped value, or `null` if the predicate returns `false`.
    ///
    /// @see #mapNonNullIf(double, DoublePredicate, DoubleFunction)
    /// @see #mapIfOrElse(double, DoublePredicate, DoubleFunction, Object)
    /// @see #mapNonNullIfOrElse(double, DoublePredicate, DoubleFunction, Object)
    /// @see #mapIfOrElseGet(double, DoublePredicate, DoubleFunction, Supplier)
    /// @see #mapNonNullIfOrElseGet(double, DoublePredicate, DoubleFunction, Supplier)
    ///
    public static <R> @Nullable R mapIf(double value, DoublePredicate predicate, DoubleFunction<@Nullable R> mapper) {
        return predicate.test(value) ? mapper.apply(value) : null;
    }
    
    ///
    /// Maps the given double value to a new value using the given mapper function if the predicate returns `true`.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value     The double value to map.
    /// @param predicate The predicate to check against.
    /// @param mapper    The mapper function.
    ///
    /// @return The mapped value, or `null` if the predicate returns `false`.
    ///
    /// @throws NullPointerException If the mapper function returns `null`.
    /// @see #mapIf(double, DoublePredicate, DoubleFunction)
    /// @see #mapIfOrElse(double, DoublePredicate, DoubleFunction, Object)
    /// @see #mapNonNullIfOrElse(double, DoublePredicate, DoubleFunction, Object)
    /// @see #mapIfOrElseGet(double, DoublePredicate, DoubleFunction, Supplier)
    /// @see #mapNonNullIfOrElseGet(double, DoublePredicate, DoubleFunction, Supplier)
    ///
    public static <R> @Nullable R mapNonNullIf(double value, DoublePredicate predicate, DoubleFunction<R> mapper) {
        return predicate.test(value) ? Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") : null;
    }
    
    ///
    /// Maps the given double value to a new value using the given mapper function if the predicate returns `true`.
    /// If the predicate returns `false`, the `orElse` value is returned.
    ///
    /// The mapper function and the `orElse` value may be `null`.
    ///
    /// @param value     The double value to map.
    /// @param predicate The predicate to check against.
    /// @param mapper    The mapper function.
    /// @param orElse    The value to return if the predicate returns `false`.
    ///
    /// @return The mapped value, or `orElse` if the predicate returns `false`.
    ///
    /// @see #mapIf(double, DoublePredicate, DoubleFunction)
    /// @see #mapNonNullIf(double, DoublePredicate, DoubleFunction)
    /// @see #mapNonNullIfOrElse(double, DoublePredicate, DoubleFunction, Object)
    /// @see #mapIfOrElseGet(double, DoublePredicate, DoubleFunction, Supplier)
    /// @see #mapNonNullIfOrElseGet(double, DoublePredicate, DoubleFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfOrElse(double value, DoublePredicate predicate, DoubleFunction<@Nullable R> mapper, @Nullable R orElse) {
        return predicate.test(value) ? mapper.apply(value) : orElse;
    }
    
    ///
    /// Maps the given double value to a new value using the given mapper function if the predicate returns `true`.
    /// If the predicate returns `false`, the `orElse` value is returned.
    ///
    /// Both the mapper function and the `orElse` value must not return `null`.
    ///
    /// @param value     The double value to map.
    /// @param predicate The predicate to check against.
    /// @param mapper    The mapper function.
    /// @param orElse    The value to return if the predicate returns `false`.
    ///
    /// @return The mapped value, or `orElse` if the predicate returns `false`.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` value is `null`.
    /// @see #mapIf(double, DoublePredicate, DoubleFunction)
    /// @see #mapNonNullIf(double, DoublePredicate, DoubleFunction)
    /// @see #mapIfOrElse(double, DoublePredicate, DoubleFunction, Object)
    /// @see #mapIfOrElseGet(double, DoublePredicate, DoubleFunction, Supplier)
    /// @see #mapNonNullIfOrElseGet(double, DoublePredicate, DoubleFunction, Supplier)
    ///
    public static <R> R mapNonNullIfOrElse(double value, DoublePredicate predicate, DoubleFunction<R> mapper, R orElse) {
        return predicate.test(value) ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse);
    }
    
    ///
    /// Maps the given double value to a new value using the given mapper function if the predicate returns `true`.
    /// If the predicate returns `false`, the `orElse` supplier's value is returned.
    ///
    /// The mapper function and the `orElse` supplier may be `null`.
    ///
    /// @param value     The double value to map.
    /// @param predicate The predicate to check against.
    /// @param mapper    The mapper function.
    /// @param orElse    The supplier of the value to return if the predicate returns `false`.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the predicate returns `false`.
    ///
    /// @see #mapIf(double, DoublePredicate, DoubleFunction)
    /// @see #mapNonNullIf(double, DoublePredicate, DoubleFunction)
    /// @see #mapIfOrElse(double, DoublePredicate, DoubleFunction, Object)
    /// @see #mapNonNullIfOrElse(double, DoublePredicate, DoubleFunction, Object)
    /// @see #mapNonNullIfOrElseGet(double, DoublePredicate, DoubleFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfOrElseGet(double value, DoublePredicate predicate, DoubleFunction<@Nullable R> mapper, Supplier<@Nullable R> orElse) {
        return predicate.test(value) ? mapper.apply(value) : orElse.get();
    }
    
    ///
    /// Maps the given double value to a new value using the given mapper function if the predicate returns `true`.
    /// If the predicate returns `false`, the `orElse` supplier's value is returned.
    ///
    /// Both the mapper function and the `orElse` supplier must not return `null`.
    ///
    /// @param value     The double value to map.
    /// @param predicate The predicate to check against.
    /// @param mapper    The mapper function.
    /// @param orElse    The supplier of the value to return if the predicate returns `false`.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the predicate returns `false`.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` supplier returns `null`.
    /// @see #mapIf(double, DoublePredicate, DoubleFunction)
    /// @see #mapNonNullIf(double, DoublePredicate, DoubleFunction)
    /// @see #mapIfOrElse(double, DoublePredicate, DoubleFunction, Object)
    /// @see #mapNonNullIfOrElse(double, DoublePredicate, DoubleFunction, Object)
    /// @see #mapIfOrElseGet(double, DoublePredicate, DoubleFunction, Supplier)
    ///
    public static <R> R mapNonNullIfOrElseGet(double value, DoublePredicate predicate, DoubleFunction<R> mapper, Supplier<R> orElse) {
        return predicate.test(value) ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse.get());
    }
    
    // ========== POSITIVE ==========
    
    ///
    /// Maps the given double value to a new value using the given mapper function if the value is positive.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value  The double value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is not positive.
    ///
    /// @see #mapNonNullIfPositive(double, DoubleFunction)
    /// @see #mapIfPositiveOrElse(double, DoubleFunction, Object)
    /// @see #mapNonNullIfPositiveOrElse(double, DoubleFunction, Object)
    /// @see #mapIfPositiveOrElseGet(double, DoubleFunction, Supplier)
    /// @see #mapNonNullIfPositiveOrElseGet(double, DoubleFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfPositive(double value, DoubleFunction<@Nullable R> mapper) {
        return value > 0 ? mapper.apply(value) : null;
    }
    
    ///
    /// Maps the given double value to a new value using the given mapper function if the value is positive.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value  The double value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is not positive.
    ///
    /// @throws NullPointerException If the mapper function returns `null`.
    /// @see #mapIfPositive(double, DoubleFunction)
    /// @see #mapIfPositiveOrElse(double, DoubleFunction, Object)
    /// @see #mapNonNullIfPositiveOrElse(double, DoubleFunction, Object)
    /// @see #mapIfPositiveOrElseGet(double, DoubleFunction, Supplier)
    /// @see #mapNonNullIfPositiveOrElseGet(double, DoubleFunction, Supplier)
    ///
    public static <R> @Nullable R mapNonNullIfPositive(double value, DoubleFunction<R> mapper) {
        return value > 0 ? Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") : null;
    }
    
    ///
    /// Maps the given double value to a new value using the given mapper function if the value is positive.
    /// If the `value` is not positive, the `orElse` value is returned.
    ///
    /// The mapper function and the `orElse` value may be `null`.
    ///
    /// @param value  The double value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is not positive.
    ///
    /// @return The mapped value, or `orElse` if the `value` is not positive.
    ///
    /// @see #mapIfPositive(double, DoubleFunction)
    /// @see #mapNonNullIfPositive(double, DoubleFunction)
    /// @see #mapNonNullIfPositiveOrElse(double, DoubleFunction, Object)
    /// @see #mapIfPositiveOrElseGet(double, DoubleFunction, Supplier)
    /// @see #mapNonNullIfPositiveOrElseGet(double, DoubleFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfPositiveOrElse(double value, DoubleFunction<@Nullable R> mapper, @Nullable R orElse) {
        return value > 0 ? mapper.apply(value) : orElse;
    }
    
    ///
    /// Maps the given double value to a new value using the given mapper function if the value is positive.
    /// If the `value` is not positive, the `orElse` value is returned.
    ///
    /// Both the mapper function and the `orElse` value must not return `null`.
    ///
    /// @param value  The double value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is not positive.
    ///
    /// @return The mapped value, or `orElse` if the `value` is not positive.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` value is `null`.
    /// @see #mapIfPositive(double, DoubleFunction)
    /// @see #mapNonNullIfPositive(double, DoubleFunction)
    /// @see #mapIfPositiveOrElse(double, DoubleFunction, Object)
    /// @see #mapIfPositiveOrElseGet(double, DoubleFunction, Supplier)
    /// @see #mapNonNullIfPositiveOrElseGet(double, DoubleFunction, Supplier)
    ///
    public static <R> R mapNonNullIfPositiveOrElse(double value, DoubleFunction<R> mapper, R orElse) {
        return value > 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse);
    }
    
    ///
    /// Maps the given double value to a new value using the given mapper function if the value is positive.
    /// If the `value` is not positive, the `orElse` supplier's value is returned.
    ///
    /// The mapper function and the `orElse` supplier may be `null`.
    ///
    /// @param value  The double value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is not positive.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is not positive.
    ///
    /// @see #mapIfPositive(double, DoubleFunction)
    /// @see #mapNonNullIfPositive(double, DoubleFunction)
    /// @see #mapIfPositiveOrElse(double, DoubleFunction, Object)
    /// @see #mapNonNullIfPositiveOrElse(double, DoubleFunction, Object)
    /// @see #mapNonNullIfPositiveOrElseGet(double, DoubleFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfPositiveOrElseGet(double value, DoubleFunction<@Nullable R> mapper, Supplier<@Nullable R> orElse) {
        return value > 0 ? mapper.apply(value) : orElse.get();
    }
    
    ///
    /// Maps the given double value to a new value using the given mapper function if the value is positive.
    /// If the `value` is not positive, the `orElse` supplier's value is returned.
    ///
    /// Both the mapper function and the `orElse` supplier must not return `null`.
    ///
    /// @param value  The double value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is not positive.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is not positive.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` supplier returns `null`.
    /// @see #mapIfPositive(double, DoubleFunction)
    /// @see #mapNonNullIfPositive(double, DoubleFunction)
    /// @see #mapIfPositiveOrElse(double, DoubleFunction, Object)
    /// @see #mapNonNullIfPositiveOrElse(double, DoubleFunction, Object)
    /// @see #mapIfPositiveOrElseGet(double, DoubleFunction, Supplier)
    ///
    public static <R> R mapNonNullIfPositiveOrElseGet(double value, DoubleFunction<R> mapper, Supplier<R> orElse) {
        return value > 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse.get());
    }
    
    // ========== NEGATIVE ==========
    
    ///
    /// Maps the given double value to a new value using the given mapper function if the value is negative.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value  The double value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is not negative.
    ///
    /// @see #mapNonNullIfNegative(double, DoubleFunction)
    /// @see #mapIfNegativeOrElse(double, DoubleFunction, Object)
    /// @see #mapNonNullIfNegativeOrElse(double, DoubleFunction, Object)
    /// @see #mapIfNegativeOrElseGet(double, DoubleFunction, Supplier)
    /// @see #mapNonNullIfNegativeOrElseGet(double, DoubleFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNegative(double value, DoubleFunction<@Nullable R> mapper) {
        return value < 0 ? mapper.apply(value) : null;
    }
    
    ///
    /// Maps the given double value to a new value using the given mapper function if the value is negative.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value  The double value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is not negative.
    ///
    /// @throws NullPointerException If the mapper function returns `null`.
    /// @see #mapIfNegative(double, DoubleFunction)
    /// @see #mapIfNegativeOrElse(double, DoubleFunction, Object)
    /// @see #mapNonNullIfNegativeOrElse(double, DoubleFunction, Object)
    /// @see #mapIfNegativeOrElseGet(double, DoubleFunction, Supplier)
    /// @see #mapNonNullIfNegativeOrElseGet(double, DoubleFunction, Supplier)
    ///
    public static <R> @Nullable R mapNonNullIfNegative(double value, DoubleFunction<R> mapper) {
        return value < 0 ? Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") : null;
    }
    
    ///
    /// Maps the given double value to a new value using the given mapper function if the value is negative.
    /// If the `value` is not negative, the `orElse` value is returned.
    ///
    /// The mapper function and the `orElse` value may be `null`.
    ///
    /// @param value  The double value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is not negative.
    ///
    /// @return The mapped value, or `orElse` if the `value` is not negative.
    ///
    /// @see #mapIfNegative(double, DoubleFunction)
    /// @see #mapNonNullIfNegative(double, DoubleFunction)
    /// @see #mapNonNullIfNegativeOrElse(double, DoubleFunction, Object)
    /// @see #mapIfNegativeOrElseGet(double, DoubleFunction, Supplier)
    /// @see #mapNonNullIfNegativeOrElseGet(double, DoubleFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNegativeOrElse(double value, DoubleFunction<@Nullable R> mapper, @Nullable R orElse) {
        return value < 0 ? mapper.apply(value) : orElse;
    }
    
    ///
    /// Maps the given double value to a new value using the given mapper function if the value is negative.
    /// If the `value` is not negative, the `orElse` value is returned.
    ///
    /// Both the mapper function and the `orElse` value must not return `null`.
    ///
    /// @param value  The double value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is not negative.
    ///
    /// @return The mapped value, or `orElse` if the `value` is not negative.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` value is `null`.
    /// @see #mapIfNegative(double, DoubleFunction)
    /// @see #mapNonNullIfNegative(double, DoubleFunction)
    /// @see #mapIfNegativeOrElse(double, DoubleFunction, Object)
    /// @see #mapIfNegativeOrElseGet(double, DoubleFunction, Supplier)
    /// @see #mapNonNullIfNegativeOrElseGet(double, DoubleFunction, Supplier)
    ///
    public static <R> R mapNonNullIfNegativeOrElse(double value, DoubleFunction<R> mapper, R orElse) {
        return value < 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse);
    }
    
    ///
    /// Maps the given double value to a new value using the given mapper function if the value is negative.
    /// If the `value` is not negative, the `orElse` supplier's value is returned.
    ///
    /// The mapper function and the `orElse` supplier may be `null`.
    ///
    /// @param value  The double value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is not negative.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is not negative.
    ///
    /// @see #mapIfNegative(double, DoubleFunction)
    /// @see #mapNonNullIfNegative(double, DoubleFunction)
    /// @see #mapIfNegativeOrElse(double, DoubleFunction, Object)
    /// @see #mapNonNullIfNegativeOrElse(double, DoubleFunction, Object)
    /// @see #mapNonNullIfNegativeOrElseGet(double, DoubleFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNegativeOrElseGet(double value, DoubleFunction<@Nullable R> mapper, Supplier<@Nullable R> orElse) {
        return value < 0 ? mapper.apply(value) : orElse.get();
    }
    
    ///
    /// Maps the given double value to a new value using the given mapper function if the value is negative.
    /// If the `value` is not negative, the `orElse` supplier's value is returned.
    ///
    /// Both the mapper function and the `orElse` supplier must not return `null`.
    ///
    /// @param value  The double value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is not negative.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is not negative.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` supplier returns `null`.
    /// @see #mapIfNegative(double, DoubleFunction)
    /// @see #mapNonNullIfNegative(double, DoubleFunction)
    /// @see #mapIfNegativeOrElse(double, DoubleFunction, Object)
    /// @see #mapNonNullIfNegativeOrElse(double, DoubleFunction, Object)
    /// @see #mapIfNegativeOrElseGet(double, DoubleFunction, Supplier)
    ///
    public static <R> R mapNonNullIfNegativeOrElseGet(double value, DoubleFunction<R> mapper, Supplier<R> orElse) {
        return value < 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse.get());
    }
    
    // ========== NOT POSITIVE ==========
    
    ///
    /// Maps the given double value to a new value using the given mapper function if the value is not positive.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value  The double value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is positive.
    ///
    /// @see #mapNonNullIfNotPositive(double, DoubleFunction)
    /// @see #mapIfNotPositiveOrElse(double, DoubleFunction, Object)
    /// @see #mapNonNullIfNotPositiveOrElse(double, DoubleFunction, Object)
    /// @see #mapIfNotPositiveOrElseGet(double, DoubleFunction, Supplier)
    /// @see #mapNonNullIfNotPositiveOrElseGet(double, DoubleFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNotPositive(double value, DoubleFunction<@Nullable R> mapper) {
        return value <= 0 ? mapper.apply(value) : null;
    }
    
    ///
    /// Maps the given double value to a new value using the given mapper function if the value is not positive.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value  The double value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is positive.
    ///
    /// @throws NullPointerException If the mapper function returns `null`.
    /// @see #mapIfNotPositive(double, DoubleFunction)
    /// @see #mapIfNotPositiveOrElse(double, DoubleFunction, Object)
    /// @see #mapNonNullIfNotPositiveOrElse(double, DoubleFunction, Object)
    /// @see #mapIfNotPositiveOrElseGet(double, DoubleFunction, Supplier)
    /// @see #mapNonNullIfNotPositiveOrElseGet(double, DoubleFunction, Supplier)
    ///
    public static <R> @Nullable R mapNonNullIfNotPositive(double value, DoubleFunction<R> mapper) {
        return value <= 0 ? Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") : null;
    }
    
    ///
    /// Maps the given double value to a new value using the given mapper function if the value is not positive.
    /// If the `value` is positive, the `orElse` value is returned.
    ///
    /// The mapper function and the `orElse` value may be `null`.
    ///
    /// @param value  The double value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is positive.
    ///
    /// @return The mapped value, or `orElse` if the `value` is positive.
    ///
    /// @see #mapIfNotPositive(double, DoubleFunction)
    /// @see #mapNonNullIfNotPositive(double, DoubleFunction)
    /// @see #mapNonNullIfNotPositiveOrElse(double, DoubleFunction, Object)
    /// @see #mapIfNotPositiveOrElseGet(double, DoubleFunction, Supplier)
    /// @see #mapNonNullIfNotPositiveOrElseGet(double, DoubleFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNotPositiveOrElse(double value, DoubleFunction<@Nullable R> mapper, @Nullable R orElse) {
        return value <= 0 ? mapper.apply(value) : orElse;
    }
    
    ///
    /// Maps the given double value to a new value using the given mapper function if the value is not positive.
    /// If the `value` is positive, the `orElse` value is returned.
    ///
    /// Both the mapper function and the `orElse` value must not return `null`.
    ///
    /// @param value  The double value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is positive.
    ///
    /// @return The mapped value, or `orElse` if the `value` is positive.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` value is `null`.
    /// @see #mapIfNotPositive(double, DoubleFunction)
    /// @see #mapNonNullIfNotPositive(double, DoubleFunction)
    /// @see #mapIfNotPositiveOrElse(double, DoubleFunction, Object)
    /// @see #mapIfNotPositiveOrElseGet(double, DoubleFunction, Supplier)
    /// @see #mapNonNullIfNotPositiveOrElseGet(double, DoubleFunction, Supplier)
    ///
    public static <R> R mapNonNullIfNotPositiveOrElse(double value, DoubleFunction<R> mapper, R orElse) {
        return value <= 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse);
    }
    
    ///
    /// Maps the given double value to a new value using the given mapper function if the value is not positive.
    /// If the `value` is positive, the `orElse` supplier's value is returned.
    ///
    /// The mapper function and the `orElse` supplier may be `null`.
    ///
    /// @param value  The double value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is positive.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is positive.
    ///
    /// @see #mapIfNotPositive(double, DoubleFunction)
    /// @see #mapNonNullIfNotPositive(double, DoubleFunction)
    /// @see #mapIfNotPositiveOrElse(double, DoubleFunction, Object)
    /// @see #mapNonNullIfNotPositiveOrElse(double, DoubleFunction, Object)
    /// @see #mapNonNullIfNotPositiveOrElseGet(double, DoubleFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNotPositiveOrElseGet(double value, DoubleFunction<@Nullable R> mapper, Supplier<@Nullable R> orElse) {
        return value <= 0 ? mapper.apply(value) : orElse.get();
    }
    
    ///
    /// Maps the given double value to a new value using the given mapper function if the value is not positive.
    /// If the `value` is positive, the `orElse` supplier's value is returned.
    ///
    /// Both the mapper function and the `orElse` supplier must not return `null`.
    ///
    /// @param value  The double value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is positive.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is positive.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` supplier returns `null`.
    /// @see #mapIfNotPositive(double, DoubleFunction)
    /// @see #mapNonNullIfNotPositive(double, DoubleFunction)
    /// @see #mapIfNotPositiveOrElse(double, DoubleFunction, Object)
    /// @see #mapNonNullIfNotPositiveOrElse(double, DoubleFunction, Object)
    /// @see #mapIfNotPositiveOrElseGet(double, DoubleFunction, Supplier)
    ///
    public static <R> R mapNonNullIfNotPositiveOrElseGet(double value, DoubleFunction<R> mapper, Supplier<R> orElse) {
        return value <= 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse.get());
    }
    
    // ========== NOT NEGATIVE ==========
    
    ///
    /// Maps the given double value to a new value using the given mapper function if the value is not negative.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value  The double value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is negative.
    ///
    /// @see #mapNonNullIfNotNegative(double, DoubleFunction)
    /// @see #mapIfNotNegativeOrElse(double, DoubleFunction, Object)
    /// @see #mapNonNullIfNotNegativeOrElse(double, DoubleFunction, Object)
    /// @see #mapIfNotNegativeOrElseGet(double, DoubleFunction, Supplier)
    /// @see #mapNonNullIfNotNegativeOrElseGet(double, DoubleFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNotNegative(double value, DoubleFunction<@Nullable R> mapper) {
        return value >= 0 ? mapper.apply(value) : null;
    }
    
    ///
    /// Maps the given double value to a new value using the given mapper function if the value is not negative.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value  The double value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is negative.
    ///
    /// @throws NullPointerException If the mapper function returns `null`.
    /// @see #mapIfNotNegative(double, DoubleFunction)
    /// @see #mapIfNotNegativeOrElse(double, DoubleFunction, Object)
    /// @see #mapNonNullIfNotNegativeOrElse(double, DoubleFunction, Object)
    /// @see #mapIfNotNegativeOrElseGet(double, DoubleFunction, Supplier)
    /// @see #mapNonNullIfNotNegativeOrElseGet(double, DoubleFunction, Supplier)
    ///
    public static <R> @Nullable R mapNonNullIfNotNegative(double value, DoubleFunction<R> mapper) {
        return value >= 0 ? Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") : null;
    }
    
    ///
    /// Maps the given double value to a new value using the given mapper function if the value is not negative.
    /// If the `value` is negative, the `orElse` value is returned.
    ///
    /// The mapper function and the `orElse` value may be `null`.
    ///
    /// @param value  The double value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is negative.
    ///
    /// @return The mapped value, or `orElse` if the `value` is negative.
    ///
    /// @see #mapIfNotNegative(double, DoubleFunction)
    /// @see #mapNonNullIfNotNegative(double, DoubleFunction)
    /// @see #mapNonNullIfNotNegativeOrElse(double, DoubleFunction, Object)
    /// @see #mapIfNotNegativeOrElseGet(double, DoubleFunction, Supplier)
    /// @see #mapNonNullIfNotNegativeOrElseGet(double, DoubleFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNotNegativeOrElse(double value, DoubleFunction<@Nullable R> mapper, @Nullable R orElse) {
        return value >= 0 ? mapper.apply(value) : orElse;
    }
    
    ///
    /// Maps the given double value to a new value using the given mapper function if the value is not negative.
    /// If the `value` is negative, the `orElse` value is returned.
    ///
    /// Both the mapper function and the `orElse` value must not return `null`.
    ///
    /// @param value  The double value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is negative.
    ///
    /// @return The mapped value, or `orElse` if the `value` is negative.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` value is `null`.
    /// @see #mapIfNotNegative(double, DoubleFunction)
    /// @see #mapNonNullIfNotNegative(double, DoubleFunction)
    /// @see #mapIfNotNegativeOrElse(double, DoubleFunction, Object)
    /// @see #mapIfNotNegativeOrElseGet(double, DoubleFunction, Supplier)
    /// @see #mapNonNullIfNotNegativeOrElseGet(double, DoubleFunction, Supplier)
    ///
    public static <R> R mapNonNullIfNotNegativeOrElse(double value, DoubleFunction<R> mapper, R orElse) {
        return value >= 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse);
    }
    
    ///
    /// Maps the given double value to a new value using the given mapper function if the value is not negative.
    /// If the `value` is negative, the `orElse` supplier's value is returned.
    ///
    /// The mapper function and the `orElse` supplier may be `null`.
    ///
    /// @param value  The double value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is negative.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is negative.
    ///
    /// @see #mapIfNotNegative(double, DoubleFunction)
    /// @see #mapNonNullIfNotNegative(double, DoubleFunction)
    /// @see #mapIfNotNegativeOrElse(double, DoubleFunction, Object)
    /// @see #mapNonNullIfNotNegativeOrElse(double, DoubleFunction, Object)
    /// @see #mapNonNullIfNotNegativeOrElseGet(double, DoubleFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNotNegativeOrElseGet(double value, DoubleFunction<@Nullable R> mapper, Supplier<@Nullable R> orElse) {
        return value >= 0 ? mapper.apply(value) : orElse.get();
    }
    
    ///
    /// Maps the given double value to a new value using the given mapper function if the value is not negative.
    /// If the `value` is negative, the `orElse` supplier's value is returned.
    ///
    /// Both the mapper function and the `orElse` supplier must not return `null`.
    ///
    /// @param value  The double value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is negative.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is negative.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` supplier returns `null`.
    /// @see #mapIfNotNegative(double, DoubleFunction)
    /// @see #mapNonNullIfNotNegative(double, DoubleFunction)
    /// @see #mapIfNotNegativeOrElse(double, DoubleFunction, Object)
    /// @see #mapNonNullIfNotNegativeOrElse(double, DoubleFunction, Object)
    /// @see #mapIfNotNegativeOrElseGet(double, DoubleFunction, Supplier)
    ///
    public static <R> R mapNonNullIfNotNegativeOrElseGet(double value, DoubleFunction<R> mapper, Supplier<R> orElse) {
        return value >= 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse.get());
    }
    
    // ========== ZERO ==========
    
    ///
    /// Maps the given double value to a new value using the given mapper function if the value is zero.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value  The double value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is not zero.
    ///
    /// @see #mapNonNullIfZero(double, DoubleFunction)
    /// @see #mapIfZeroOrElse(double, DoubleFunction, Object)
    /// @see #mapNonNullIfZeroOrElse(double, DoubleFunction, Object)
    /// @see #mapIfZeroOrElseGet(double, DoubleFunction, Supplier)
    /// @see #mapNonNullIfZeroOrElseGet(double, DoubleFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfZero(double value, DoubleFunction<@Nullable R> mapper) {
        return value == 0 ? mapper.apply(value) : null;
    }
    
    ///
    /// Maps the given double value to a new value using the given mapper function if the value is zero.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value  The double value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is not zero.
    ///
    /// @throws NullPointerException If the mapper function returns `null`.
    /// @see #mapIfZero(double, DoubleFunction)
    /// @see #mapIfZeroOrElse(double, DoubleFunction, Object)
    /// @see #mapNonNullIfZeroOrElse(double, DoubleFunction, Object)
    /// @see #mapIfZeroOrElseGet(double, DoubleFunction, Supplier)
    /// @see #mapNonNullIfZeroOrElseGet(double, DoubleFunction, Supplier)
    ///
    public static <R> @Nullable R mapNonNullIfZero(double value, DoubleFunction<R> mapper) {
        return value == 0 ? Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") : null;
    }
    
    ///
    /// Maps the given double value to a new value using the given mapper function if the value is zero.
    /// If the `value` is not zero, the `orElse` value is returned.
    ///
    /// The mapper function and the `orElse` value may be `null`.
    ///
    /// @param value  The double value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is not zero.
    ///
    /// @return The mapped value, or `orElse` if the `value` is not zero.
    ///
    /// @see #mapIfZero(double, DoubleFunction)
    /// @see #mapNonNullIfZero(double, DoubleFunction)
    /// @see #mapNonNullIfZeroOrElse(double, DoubleFunction, Object)
    /// @see #mapIfZeroOrElseGet(double, DoubleFunction, Supplier)
    /// @see #mapNonNullIfZeroOrElseGet(double, DoubleFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfZeroOrElse(double value, DoubleFunction<@Nullable R> mapper, @Nullable R orElse) {
        return value == 0 ? mapper.apply(value) : orElse;
    }
    
    ///
    /// Maps the given double value to a new value using the given mapper function if the value is zero.
    /// If the `value` is not zero, the `orElse` value is returned.
    ///
    /// Both the mapper function and the `orElse` value must not return `null`.
    ///
    /// @param value  The double value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is not zero.
    ///
    /// @return The mapped value, or `orElse` if the `value` is not zero.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` value is `null`.
    /// @see #mapIfZero(double, DoubleFunction)
    /// @see #mapNonNullIfZero(double, DoubleFunction)
    /// @see #mapIfZeroOrElse(double, DoubleFunction, Object)
    /// @see #mapIfZeroOrElseGet(double, DoubleFunction, Supplier)
    /// @see #mapNonNullIfZeroOrElseGet(double, DoubleFunction, Supplier)
    ///
    public static <R> R mapNonNullIfZeroOrElse(double value, DoubleFunction<R> mapper, R orElse) {
        return value == 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse);
    }
    
    ///
    /// Maps the given double value to a new value using the given mapper function if the value is zero.
    /// If the `value` is not zero, the `orElse` supplier's value is returned.
    ///
    /// The mapper function and the `orElse` supplier may be `null`.
    ///
    /// @param value  The double value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is not zero.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is not zero.
    ///
    /// @see #mapIfZero(double, DoubleFunction)
    /// @see #mapNonNullIfZero(double, DoubleFunction)
    /// @see #mapIfZeroOrElse(double, DoubleFunction, Object)
    /// @see #mapNonNullIfZeroOrElse(double, DoubleFunction, Object)
    /// @see #mapNonNullIfZeroOrElseGet(double, DoubleFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfZeroOrElseGet(double value, DoubleFunction<@Nullable R> mapper, Supplier<@Nullable R> orElse) {
        return value == 0 ? mapper.apply(value) : orElse.get();
    }
    
    ///
    /// Maps the given double value to a new value using the given mapper function if the value is zero.
    /// If the `value` is not zero, the `orElse` supplier's value is returned.
    ///
    /// Both the mapper function and the `orElse` supplier must not return `null`.
    ///
    /// @param value  The double value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is not zero.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is not zero.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` supplier returns `null`.
    /// @see #mapIfZero(double, DoubleFunction)
    /// @see #mapNonNullIfZero(double, DoubleFunction)
    /// @see #mapIfZeroOrElse(double, DoubleFunction, Object)
    /// @see #mapNonNullIfZeroOrElse(double, DoubleFunction, Object)
    /// @see #mapIfZeroOrElseGet(double, DoubleFunction, Supplier)
    ///
    public static <R> R mapNonNullIfZeroOrElseGet(double value, DoubleFunction<R> mapper, Supplier<R> orElse) {
        return value == 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse.get());
    }
    
    // ========== NOT ZERO ==========
    
    ///
    /// Maps the given double value to a new value using the given mapper function if the value is not zero.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value  The double value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is zero.
    ///
    /// @see #mapNonNullIfNotZero(double, DoubleFunction)
    /// @see #mapIfNotZeroOrElse(double, DoubleFunction, Object)
    /// @see #mapNonNullIfNotZeroOrElse(double, DoubleFunction, Object)
    /// @see #mapIfNotZeroOrElseGet(double, DoubleFunction, Supplier)
    /// @see #mapNonNullIfNotZeroOrElseGet(double, DoubleFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNotZero(double value, DoubleFunction<@Nullable R> mapper) {
        return value != 0 ? mapper.apply(value) : null;
    }
    
    ///
    /// Maps the given double value to a new value using the given mapper function if the value is not zero.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value  The double value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is zero.
    ///
    /// @throws NullPointerException If the mapper function returns `null`.
    /// @see #mapIfNotZero(double, DoubleFunction)
    /// @see #mapIfNotZeroOrElse(double, DoubleFunction, Object)
    /// @see #mapNonNullIfNotZeroOrElse(double, DoubleFunction, Object)
    /// @see #mapIfNotZeroOrElseGet(double, DoubleFunction, Supplier)
    /// @see #mapNonNullIfNotZeroOrElseGet(double, DoubleFunction, Supplier)
    ///
    public static <R> @Nullable R mapNonNullIfNotZero(double value, DoubleFunction<R> mapper) {
        return value != 0 ? Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") : null;
    }
    
    ///
    /// Maps the given double value to a new value using the given mapper function if the value is not zero.
    /// If the `value` is zero, the `orElse` value is returned.
    ///
    /// The mapper function and the `orElse` value may be `null`.
    ///
    /// @param value  The double value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is zero.
    ///
    /// @return The mapped value, or `orElse` if the `value` is zero.
    ///
    /// @see #mapIfNotZero(double, DoubleFunction)
    /// @see #mapNonNullIfNotZero(double, DoubleFunction)
    /// @see #mapNonNullIfNotZeroOrElse(double, DoubleFunction, Object)
    /// @see #mapIfNotZeroOrElseGet(double, DoubleFunction, Supplier)
    /// @see #mapNonNullIfNotZeroOrElseGet(double, DoubleFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNotZeroOrElse(double value, DoubleFunction<@Nullable R> mapper, @Nullable R orElse) {
        return value != 0 ? mapper.apply(value) : orElse;
    }
    
    ///
    /// Maps the given double value to a new value using the given mapper function if the value is not zero.
    /// If the `value` is zero, the `orElse` value is returned.
    ///
    /// Both the mapper function and the `orElse` value must not return `null`.
    ///
    /// @param value  The double value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is zero.
    ///
    /// @return The mapped value, or `orElse` if the `value` is zero.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` value is `null`.
    /// @see #mapIfNotZero(double, DoubleFunction)
    /// @see #mapNonNullIfNotZero(double, DoubleFunction)
    /// @see #mapIfNotZeroOrElse(double, DoubleFunction, Object)
    /// @see #mapIfNotZeroOrElseGet(double, DoubleFunction, Supplier)
    /// @see #mapNonNullIfNotZeroOrElseGet(double, DoubleFunction, Supplier)
    ///
    public static <R> R mapNonNullIfNotZeroOrElse(double value, DoubleFunction<R> mapper, R orElse) {
        return value != 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse);
    }
    
    ///
    /// Maps the given double value to a new value using the given mapper function if the value is not zero.
    /// If the `value` is zero, the `orElse` supplier's value is returned.
    ///
    /// The mapper function and the `orElse` supplier may be `null`.
    ///
    /// @param value  The double value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is zero.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is zero.
    ///
    /// @see #mapIfNotZero(double, DoubleFunction)
    /// @see #mapNonNullIfNotZero(double, DoubleFunction)
    /// @see #mapIfNotZeroOrElse(double, DoubleFunction, Object)
    /// @see #mapNonNullIfNotZeroOrElse(double, DoubleFunction, Object)
    /// @see #mapNonNullIfNotZeroOrElseGet(double, DoubleFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNotZeroOrElseGet(double value, DoubleFunction<@Nullable R> mapper, Supplier<@Nullable R> orElse) {
        return value != 0 ? mapper.apply(value) : orElse.get();
    }
    
    ///
    /// Maps the given double value to a new value using the given mapper function if the value is not zero.
    /// If the `value` is zero, the `orElse` supplier's value is returned.
    ///
    /// Both the mapper function and the `orElse` supplier must not return `null`.
    ///
    /// @param value  The double value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is zero.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is zero.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` supplier returns `null`.
    /// @see #mapIfNotZero(double, DoubleFunction)
    /// @see #mapNonNullIfNotZero(double, DoubleFunction)
    /// @see #mapIfNotZeroOrElse(double, DoubleFunction, Object)
    /// @see #mapNonNullIfNotZeroOrElse(double, DoubleFunction, Object)
    /// @see #mapIfNotZeroOrElseGet(double, DoubleFunction, Supplier)
    ///
    public static <R> R mapNonNullIfNotZeroOrElseGet(double value, DoubleFunction<R> mapper, Supplier<R> orElse) {
        return value != 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse.get());
    }
    
    // ========== IN RANGE ==========
    
    ///
    /// Maps the given double value to a new value using the given mapper function if the value is within the given range.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value  The double value to map.
    /// @param min    The lower bound of the range, inclusive.
    /// @param max    The upper bound of the range, inclusive.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is outside the given range.
    ///
    /// @see #mapNonNullIfInRange(double, double, double, DoubleFunction)
    /// @see #mapIfInRangeOrElse(double, double, double, DoubleFunction, Object)
    /// @see #mapNonNullIfInRangeOrElse(double, double, double, DoubleFunction, Object)
    /// @see #mapIfInRangeOrElseGet(double, double, double, DoubleFunction, Supplier)
    /// @see #mapNonNullIfInRangeOrElseGet(double, double, double, DoubleFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfInRange(double value, double min, double max, DoubleFunction<@Nullable R> mapper) {
        return (value >= min && value <= max) ? mapper.apply(value) : null;
    }
    
    ///
    /// Maps the given double value to a new value using the given mapper function if the value is within the given range.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value  The double value to map.
    /// @param min    The lower bound of the range, inclusive.
    /// @param max    The upper bound of the range, inclusive.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is outside the given range.
    ///
    /// @throws NullPointerException If the mapper function returns `null`.
    /// @see #mapIfInRange(double, double, double, DoubleFunction)
    /// @see #mapIfInRangeOrElse(double, double, double, DoubleFunction, Object)
    /// @see #mapNonNullIfInRangeOrElse(double, double, double, DoubleFunction, Object)
    /// @see #mapIfInRangeOrElseGet(double, double, double, DoubleFunction, Supplier)
    /// @see #mapNonNullIfInRangeOrElseGet(double, double, double, DoubleFunction, Supplier)
    ///
    public static <R> @Nullable R mapNonNullIfInRange(double value, double min, double max, DoubleFunction<R> mapper) {
        return (value >= min && value <= max) ? Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") : null;
    }
    
    ///
    /// Maps the given double value to a new value using the given mapper function if the value is within the given range.
    /// If the `value` is outside the given range, the `orElse` value is returned.
    ///
    /// The mapper function and the `orElse` value may be `null`.
    ///
    /// @param value  The double value to map.
    /// @param min    The lower bound of the range, inclusive.
    /// @param max    The upper bound of the range, inclusive.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is outside the given range.
    ///
    /// @return The mapped value, or `orElse` if the `value` is outside the given range.
    ///
    /// @see #mapIfInRange(double, double, double, DoubleFunction)
    /// @see #mapNonNullIfInRange(double, double, double, DoubleFunction)
    /// @see #mapNonNullIfInRangeOrElse(double, double, double, DoubleFunction, Object)
    /// @see #mapIfInRangeOrElseGet(double, double, double, DoubleFunction, Supplier)
    /// @see #mapNonNullIfInRangeOrElseGet(double, double, double, DoubleFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfInRangeOrElse(double value, double min, double max, DoubleFunction<@Nullable R> mapper, @Nullable R orElse) {
        return (value >= min && value <= max) ? mapper.apply(value) : orElse;
    }
    
    ///
    /// Maps the given double value to a new value using the given mapper function if the value is within the given range.
    /// If the `value` is outside the given range, the `orElse` value is returned.
    ///
    /// Both the mapper function and the `orElse` value must not return `null`.
    ///
    /// @param value  The double value to map.
    /// @param min    The lower bound of the range, inclusive.
    /// @param max    The upper bound of the range, inclusive.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is outside the given range.
    ///
    /// @return The mapped value, or `orElse` if the `value` is outside the given range.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` value is `null`.
    /// @see #mapIfInRange(double, double, double, DoubleFunction)
    /// @see #mapNonNullIfInRange(double, double, double, DoubleFunction)
    /// @see #mapIfInRangeOrElse(double, double, double, DoubleFunction, Object)
    /// @see #mapIfInRangeOrElseGet(double, double, double, DoubleFunction, Supplier)
    /// @see #mapNonNullIfInRangeOrElseGet(double, double, double, DoubleFunction, Supplier)
    ///
    public static <R> R mapNonNullIfInRangeOrElse(double value, double min, double max, DoubleFunction<R> mapper, R orElse) {
        return (value >= min && value <= max) ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse);
    }
    
    ///
    /// Maps the given double value to a new value using the given mapper function if the value is within the given range.
    /// If the `value` is outside the given range, the `orElse` supplier's value is returned.
    ///
    /// The mapper function and the `orElse` supplier may be `null`.
    ///
    /// @param value  The double value to map.
    /// @param min    The lower bound of the range, inclusive.
    /// @param max    The upper bound of the range, inclusive.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is outside the given range.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is outside the given range.
    ///
    /// @see #mapIfInRange(double, double, double, DoubleFunction)
    /// @see #mapNonNullIfInRange(double, double, double, DoubleFunction)
    /// @see #mapIfInRangeOrElse(double, double, double, DoubleFunction, Object)
    /// @see #mapNonNullIfInRangeOrElse(double, double, double, DoubleFunction, Object)
    /// @see #mapNonNullIfInRangeOrElseGet(double, double, double, DoubleFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfInRangeOrElseGet(double value, double min, double max, DoubleFunction<@Nullable R> mapper, Supplier<@Nullable R> orElse) {
        return (value >= min && value <= max) ? mapper.apply(value) : orElse.get();
    }
    
    ///
    /// Maps the given double value to a new value using the given mapper function if the value is within the given range.
    /// If the `value` is outside the given range, the `orElse` supplier's value is returned.
    ///
    /// Both the mapper function and the `orElse` supplier must not return `null`.
    ///
    /// @param value  The double value to map.
    /// @param min    The lower bound of the range, inclusive.
    /// @param max    The upper bound of the range, inclusive.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is outside the given range.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is outside the given range.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` supplier returns `null`.
    /// @see #mapIfInRange(double, double, double, DoubleFunction)
    /// @see #mapNonNullIfInRange(double, double, double, DoubleFunction)
    /// @see #mapIfInRangeOrElse(double, double, double, DoubleFunction, Object)
    /// @see #mapNonNullIfInRangeOrElse(double, double, double, DoubleFunction, Object)
    /// @see #mapIfInRangeOrElseGet(double, double, double, DoubleFunction, Supplier)
    ///
    public static <R> R mapNonNullIfInRangeOrElseGet(double value, double min, double max, DoubleFunction<R> mapper, Supplier<R> orElse) {
        return (value >= min && value <= max) ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse.get());
    }
}
