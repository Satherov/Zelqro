package dev.satherov.zelqro.mapping;

import lombok.experimental.UtilityClass;

import dev.satherov.zelqro.function.CharFunction;
import dev.satherov.zelqro.predicate.CharPredicate;

import org.jspecify.annotations.Nullable;

import java.util.Objects;
import java.util.function.Supplier;

///
/// `char` is unsigned, so it has no positive or negative conditions.
///
/// A zero `char` is the null character, `U+0000`.
///
@UtilityClass
public class CharMapping {
    
    // ========== PREDICATE ==========
    
    ///
    /// Maps the given char value to a new value using the given mapper function if the predicate returns `true`.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value     The char value to map.
    /// @param predicate The predicate to check against.
    /// @param mapper    The mapper function.
    ///
    /// @return The mapped value, or `null` if the predicate returns `false`.
    ///
    /// @see #mapNonNullIf(char, CharPredicate, CharFunction)
    /// @see #mapIfOrElse(char, CharPredicate, CharFunction, Object)
    /// @see #mapNonNullIfOrElse(char, CharPredicate, CharFunction, Object)
    /// @see #mapIfOrElseGet(char, CharPredicate, CharFunction, Supplier)
    /// @see #mapNonNullIfOrElseGet(char, CharPredicate, CharFunction, Supplier)
    ///
    public static <R> @Nullable R mapIf(char value, CharPredicate predicate, CharFunction<@Nullable R> mapper) {
        return predicate.test(value) ? mapper.apply(value) : null;
    }
    
    ///
    /// Maps the given char value to a new value using the given mapper function if the predicate returns `true`.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value     The char value to map.
    /// @param predicate The predicate to check against.
    /// @param mapper    The mapper function.
    ///
    /// @return The mapped value, or `null` if the predicate returns `false`.
    ///
    /// @throws NullPointerException If the mapper function returns `null`.
    /// @see #mapIf(char, CharPredicate, CharFunction)
    /// @see #mapIfOrElse(char, CharPredicate, CharFunction, Object)
    /// @see #mapNonNullIfOrElse(char, CharPredicate, CharFunction, Object)
    /// @see #mapIfOrElseGet(char, CharPredicate, CharFunction, Supplier)
    /// @see #mapNonNullIfOrElseGet(char, CharPredicate, CharFunction, Supplier)
    ///
    public static <R> @Nullable R mapNonNullIf(char value, CharPredicate predicate, CharFunction<R> mapper) {
        return predicate.test(value) ? Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") : null;
    }
    
    ///
    /// Maps the given char value to a new value using the given mapper function if the predicate returns `true`.
    /// If the predicate returns `false`, the `orElse` value is returned.
    ///
    /// The mapper function and the `orElse` value may be `null`.
    ///
    /// @param value     The char value to map.
    /// @param predicate The predicate to check against.
    /// @param mapper    The mapper function.
    /// @param orElse    The value to return if the predicate returns `false`.
    ///
    /// @return The mapped value, or `orElse` if the predicate returns `false`.
    ///
    /// @see #mapIf(char, CharPredicate, CharFunction)
    /// @see #mapNonNullIf(char, CharPredicate, CharFunction)
    /// @see #mapNonNullIfOrElse(char, CharPredicate, CharFunction, Object)
    /// @see #mapIfOrElseGet(char, CharPredicate, CharFunction, Supplier)
    /// @see #mapNonNullIfOrElseGet(char, CharPredicate, CharFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfOrElse(char value, CharPredicate predicate, CharFunction<@Nullable R> mapper, @Nullable R orElse) {
        return predicate.test(value) ? mapper.apply(value) : orElse;
    }
    
    ///
    /// Maps the given char value to a new value using the given mapper function if the predicate returns `true`.
    /// If the predicate returns `false`, the `orElse` value is returned.
    ///
    /// Both the mapper function and the `orElse` value must not return `null`.
    ///
    /// @param value     The char value to map.
    /// @param predicate The predicate to check against.
    /// @param mapper    The mapper function.
    /// @param orElse    The value to return if the predicate returns `false`.
    ///
    /// @return The mapped value, or `orElse` if the predicate returns `false`.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` value is `null`.
    /// @see #mapIf(char, CharPredicate, CharFunction)
    /// @see #mapNonNullIf(char, CharPredicate, CharFunction)
    /// @see #mapIfOrElse(char, CharPredicate, CharFunction, Object)
    /// @see #mapIfOrElseGet(char, CharPredicate, CharFunction, Supplier)
    /// @see #mapNonNullIfOrElseGet(char, CharPredicate, CharFunction, Supplier)
    ///
    public static <R> R mapNonNullIfOrElse(char value, CharPredicate predicate, CharFunction<R> mapper, R orElse) {
        return predicate.test(value) ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse);
    }
    
    ///
    /// Maps the given char value to a new value using the given mapper function if the predicate returns `true`.
    /// If the predicate returns `false`, the `orElse` supplier's value is returned.
    ///
    /// The mapper function and the `orElse` supplier may be `null`.
    ///
    /// @param value     The char value to map.
    /// @param predicate The predicate to check against.
    /// @param mapper    The mapper function.
    /// @param orElse    The supplier of the value to return if the predicate returns `false`.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the predicate returns `false`.
    ///
    /// @see #mapIf(char, CharPredicate, CharFunction)
    /// @see #mapNonNullIf(char, CharPredicate, CharFunction)
    /// @see #mapIfOrElse(char, CharPredicate, CharFunction, Object)
    /// @see #mapNonNullIfOrElse(char, CharPredicate, CharFunction, Object)
    /// @see #mapNonNullIfOrElseGet(char, CharPredicate, CharFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfOrElseGet(char value, CharPredicate predicate, CharFunction<@Nullable R> mapper, Supplier<@Nullable R> orElse) {
        return predicate.test(value) ? mapper.apply(value) : orElse.get();
    }
    
    ///
    /// Maps the given char value to a new value using the given mapper function if the predicate returns `true`.
    /// If the predicate returns `false`, the `orElse` supplier's value is returned.
    ///
    /// Both the mapper function and the `orElse` supplier must not return `null`.
    ///
    /// @param value     The char value to map.
    /// @param predicate The predicate to check against.
    /// @param mapper    The mapper function.
    /// @param orElse    The supplier of the value to return if the predicate returns `false`.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the predicate returns `false`.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` supplier returns `null`.
    /// @see #mapIf(char, CharPredicate, CharFunction)
    /// @see #mapNonNullIf(char, CharPredicate, CharFunction)
    /// @see #mapIfOrElse(char, CharPredicate, CharFunction, Object)
    /// @see #mapNonNullIfOrElse(char, CharPredicate, CharFunction, Object)
    /// @see #mapIfOrElseGet(char, CharPredicate, CharFunction, Supplier)
    ///
    public static <R> R mapNonNullIfOrElseGet(char value, CharPredicate predicate, CharFunction<R> mapper, Supplier<R> orElse) {
        return predicate.test(value) ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse.get());
    }
    
    // ========== ZERO ==========
    
    ///
    /// Maps the given char value to a new value using the given mapper function if the value is zero.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value  The char value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is not zero.
    ///
    /// @see #mapNonNullIfZero(char, CharFunction)
    /// @see #mapIfZeroOrElse(char, CharFunction, Object)
    /// @see #mapNonNullIfZeroOrElse(char, CharFunction, Object)
    /// @see #mapIfZeroOrElseGet(char, CharFunction, Supplier)
    /// @see #mapNonNullIfZeroOrElseGet(char, CharFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfZero(char value, CharFunction<@Nullable R> mapper) {
        return value == 0 ? mapper.apply(value) : null;
    }
    
    ///
    /// Maps the given char value to a new value using the given mapper function if the value is zero.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value  The char value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is not zero.
    ///
    /// @throws NullPointerException If the mapper function returns `null`.
    /// @see #mapIfZero(char, CharFunction)
    /// @see #mapIfZeroOrElse(char, CharFunction, Object)
    /// @see #mapNonNullIfZeroOrElse(char, CharFunction, Object)
    /// @see #mapIfZeroOrElseGet(char, CharFunction, Supplier)
    /// @see #mapNonNullIfZeroOrElseGet(char, CharFunction, Supplier)
    ///
    public static <R> @Nullable R mapNonNullIfZero(char value, CharFunction<R> mapper) {
        return value == 0 ? Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") : null;
    }
    
    ///
    /// Maps the given char value to a new value using the given mapper function if the value is zero.
    /// If the `value` is not zero, the `orElse` value is returned.
    ///
    /// The mapper function and the `orElse` value may be `null`.
    ///
    /// @param value  The char value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is not zero.
    ///
    /// @return The mapped value, or `orElse` if the `value` is not zero.
    ///
    /// @see #mapIfZero(char, CharFunction)
    /// @see #mapNonNullIfZero(char, CharFunction)
    /// @see #mapNonNullIfZeroOrElse(char, CharFunction, Object)
    /// @see #mapIfZeroOrElseGet(char, CharFunction, Supplier)
    /// @see #mapNonNullIfZeroOrElseGet(char, CharFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfZeroOrElse(char value, CharFunction<@Nullable R> mapper, @Nullable R orElse) {
        return value == 0 ? mapper.apply(value) : orElse;
    }
    
    ///
    /// Maps the given char value to a new value using the given mapper function if the value is zero.
    /// If the `value` is not zero, the `orElse` value is returned.
    ///
    /// Both the mapper function and the `orElse` value must not return `null`.
    ///
    /// @param value  The char value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is not zero.
    ///
    /// @return The mapped value, or `orElse` if the `value` is not zero.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` value is `null`.
    /// @see #mapIfZero(char, CharFunction)
    /// @see #mapNonNullIfZero(char, CharFunction)
    /// @see #mapIfZeroOrElse(char, CharFunction, Object)
    /// @see #mapIfZeroOrElseGet(char, CharFunction, Supplier)
    /// @see #mapNonNullIfZeroOrElseGet(char, CharFunction, Supplier)
    ///
    public static <R> R mapNonNullIfZeroOrElse(char value, CharFunction<R> mapper, R orElse) {
        return value == 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse);
    }
    
    ///
    /// Maps the given char value to a new value using the given mapper function if the value is zero.
    /// If the `value` is not zero, the `orElse` supplier's value is returned.
    ///
    /// The mapper function and the `orElse` supplier may be `null`.
    ///
    /// @param value  The char value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is not zero.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is not zero.
    ///
    /// @see #mapIfZero(char, CharFunction)
    /// @see #mapNonNullIfZero(char, CharFunction)
    /// @see #mapIfZeroOrElse(char, CharFunction, Object)
    /// @see #mapNonNullIfZeroOrElse(char, CharFunction, Object)
    /// @see #mapNonNullIfZeroOrElseGet(char, CharFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfZeroOrElseGet(char value, CharFunction<@Nullable R> mapper, Supplier<@Nullable R> orElse) {
        return value == 0 ? mapper.apply(value) : orElse.get();
    }
    
    ///
    /// Maps the given char value to a new value using the given mapper function if the value is zero.
    /// If the `value` is not zero, the `orElse` supplier's value is returned.
    ///
    /// Both the mapper function and the `orElse` supplier must not return `null`.
    ///
    /// @param value  The char value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is not zero.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is not zero.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` supplier returns `null`.
    /// @see #mapIfZero(char, CharFunction)
    /// @see #mapNonNullIfZero(char, CharFunction)
    /// @see #mapIfZeroOrElse(char, CharFunction, Object)
    /// @see #mapNonNullIfZeroOrElse(char, CharFunction, Object)
    /// @see #mapIfZeroOrElseGet(char, CharFunction, Supplier)
    ///
    public static <R> R mapNonNullIfZeroOrElseGet(char value, CharFunction<R> mapper, Supplier<R> orElse) {
        return value == 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse.get());
    }
    
    // ========== NOT ZERO ==========
    
    ///
    /// Maps the given char value to a new value using the given mapper function if the value is not zero.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value  The char value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is zero.
    ///
    /// @see #mapNonNullIfNotZero(char, CharFunction)
    /// @see #mapIfNotZeroOrElse(char, CharFunction, Object)
    /// @see #mapNonNullIfNotZeroOrElse(char, CharFunction, Object)
    /// @see #mapIfNotZeroOrElseGet(char, CharFunction, Supplier)
    /// @see #mapNonNullIfNotZeroOrElseGet(char, CharFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNotZero(char value, CharFunction<@Nullable R> mapper) {
        return value != 0 ? mapper.apply(value) : null;
    }
    
    ///
    /// Maps the given char value to a new value using the given mapper function if the value is not zero.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value  The char value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is zero.
    ///
    /// @throws NullPointerException If the mapper function returns `null`.
    /// @see #mapIfNotZero(char, CharFunction)
    /// @see #mapIfNotZeroOrElse(char, CharFunction, Object)
    /// @see #mapNonNullIfNotZeroOrElse(char, CharFunction, Object)
    /// @see #mapIfNotZeroOrElseGet(char, CharFunction, Supplier)
    /// @see #mapNonNullIfNotZeroOrElseGet(char, CharFunction, Supplier)
    ///
    public static <R> @Nullable R mapNonNullIfNotZero(char value, CharFunction<R> mapper) {
        return value != 0 ? Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") : null;
    }
    
    ///
    /// Maps the given char value to a new value using the given mapper function if the value is not zero.
    /// If the `value` is zero, the `orElse` value is returned.
    ///
    /// The mapper function and the `orElse` value may be `null`.
    ///
    /// @param value  The char value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is zero.
    ///
    /// @return The mapped value, or `orElse` if the `value` is zero.
    ///
    /// @see #mapIfNotZero(char, CharFunction)
    /// @see #mapNonNullIfNotZero(char, CharFunction)
    /// @see #mapNonNullIfNotZeroOrElse(char, CharFunction, Object)
    /// @see #mapIfNotZeroOrElseGet(char, CharFunction, Supplier)
    /// @see #mapNonNullIfNotZeroOrElseGet(char, CharFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNotZeroOrElse(char value, CharFunction<@Nullable R> mapper, @Nullable R orElse) {
        return value != 0 ? mapper.apply(value) : orElse;
    }
    
    ///
    /// Maps the given char value to a new value using the given mapper function if the value is not zero.
    /// If the `value` is zero, the `orElse` value is returned.
    ///
    /// Both the mapper function and the `orElse` value must not return `null`.
    ///
    /// @param value  The char value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is zero.
    ///
    /// @return The mapped value, or `orElse` if the `value` is zero.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` value is `null`.
    /// @see #mapIfNotZero(char, CharFunction)
    /// @see #mapNonNullIfNotZero(char, CharFunction)
    /// @see #mapIfNotZeroOrElse(char, CharFunction, Object)
    /// @see #mapIfNotZeroOrElseGet(char, CharFunction, Supplier)
    /// @see #mapNonNullIfNotZeroOrElseGet(char, CharFunction, Supplier)
    ///
    public static <R> R mapNonNullIfNotZeroOrElse(char value, CharFunction<R> mapper, R orElse) {
        return value != 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse);
    }
    
    ///
    /// Maps the given char value to a new value using the given mapper function if the value is not zero.
    /// If the `value` is zero, the `orElse` supplier's value is returned.
    ///
    /// The mapper function and the `orElse` supplier may be `null`.
    ///
    /// @param value  The char value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is zero.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is zero.
    ///
    /// @see #mapIfNotZero(char, CharFunction)
    /// @see #mapNonNullIfNotZero(char, CharFunction)
    /// @see #mapIfNotZeroOrElse(char, CharFunction, Object)
    /// @see #mapNonNullIfNotZeroOrElse(char, CharFunction, Object)
    /// @see #mapNonNullIfNotZeroOrElseGet(char, CharFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfNotZeroOrElseGet(char value, CharFunction<@Nullable R> mapper, Supplier<@Nullable R> orElse) {
        return value != 0 ? mapper.apply(value) : orElse.get();
    }
    
    ///
    /// Maps the given char value to a new value using the given mapper function if the value is not zero.
    /// If the `value` is zero, the `orElse` supplier's value is returned.
    ///
    /// Both the mapper function and the `orElse` supplier must not return `null`.
    ///
    /// @param value  The char value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is zero.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is zero.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` supplier returns `null`.
    /// @see #mapIfNotZero(char, CharFunction)
    /// @see #mapNonNullIfNotZero(char, CharFunction)
    /// @see #mapIfNotZeroOrElse(char, CharFunction, Object)
    /// @see #mapNonNullIfNotZeroOrElse(char, CharFunction, Object)
    /// @see #mapIfNotZeroOrElseGet(char, CharFunction, Supplier)
    ///
    public static <R> R mapNonNullIfNotZeroOrElseGet(char value, CharFunction<R> mapper, Supplier<R> orElse) {
        return value != 0 ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse.get());
    }
    
    // ========== IN RANGE ==========
    
    ///
    /// Maps the given char value to a new value using the given mapper function if the value is within the given range.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value  The char value to map.
    /// @param min    The lower bound of the range, inclusive.
    /// @param max    The upper bound of the range, inclusive.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is outside the given range.
    ///
    /// @see #mapNonNullIfInRange(char, char, char, CharFunction)
    /// @see #mapIfInRangeOrElse(char, char, char, CharFunction, Object)
    /// @see #mapNonNullIfInRangeOrElse(char, char, char, CharFunction, Object)
    /// @see #mapIfInRangeOrElseGet(char, char, char, CharFunction, Supplier)
    /// @see #mapNonNullIfInRangeOrElseGet(char, char, char, CharFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfInRange(char value, char min, char max, CharFunction<@Nullable R> mapper) {
        return (value >= min && value <= max) ? mapper.apply(value) : null;
    }
    
    ///
    /// Maps the given char value to a new value using the given mapper function if the value is within the given range.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value  The char value to map.
    /// @param min    The lower bound of the range, inclusive.
    /// @param max    The upper bound of the range, inclusive.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is outside the given range.
    ///
    /// @throws NullPointerException If the mapper function returns `null`.
    /// @see #mapIfInRange(char, char, char, CharFunction)
    /// @see #mapIfInRangeOrElse(char, char, char, CharFunction, Object)
    /// @see #mapNonNullIfInRangeOrElse(char, char, char, CharFunction, Object)
    /// @see #mapIfInRangeOrElseGet(char, char, char, CharFunction, Supplier)
    /// @see #mapNonNullIfInRangeOrElseGet(char, char, char, CharFunction, Supplier)
    ///
    public static <R> @Nullable R mapNonNullIfInRange(char value, char min, char max, CharFunction<R> mapper) {
        return (value >= min && value <= max) ? Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") : null;
    }
    
    ///
    /// Maps the given char value to a new value using the given mapper function if the value is within the given range.
    /// If the `value` is outside the given range, the `orElse` value is returned.
    ///
    /// The mapper function and the `orElse` value may be `null`.
    ///
    /// @param value  The char value to map.
    /// @param min    The lower bound of the range, inclusive.
    /// @param max    The upper bound of the range, inclusive.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is outside the given range.
    ///
    /// @return The mapped value, or `orElse` if the `value` is outside the given range.
    ///
    /// @see #mapIfInRange(char, char, char, CharFunction)
    /// @see #mapNonNullIfInRange(char, char, char, CharFunction)
    /// @see #mapNonNullIfInRangeOrElse(char, char, char, CharFunction, Object)
    /// @see #mapIfInRangeOrElseGet(char, char, char, CharFunction, Supplier)
    /// @see #mapNonNullIfInRangeOrElseGet(char, char, char, CharFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfInRangeOrElse(char value, char min, char max, CharFunction<@Nullable R> mapper, @Nullable R orElse) {
        return (value >= min && value <= max) ? mapper.apply(value) : orElse;
    }
    
    ///
    /// Maps the given char value to a new value using the given mapper function if the value is within the given range.
    /// If the `value` is outside the given range, the `orElse` value is returned.
    ///
    /// Both the mapper function and the `orElse` value must not return `null`.
    ///
    /// @param value  The char value to map.
    /// @param min    The lower bound of the range, inclusive.
    /// @param max    The upper bound of the range, inclusive.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is outside the given range.
    ///
    /// @return The mapped value, or `orElse` if the `value` is outside the given range.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` value is `null`.
    /// @see #mapIfInRange(char, char, char, CharFunction)
    /// @see #mapNonNullIfInRange(char, char, char, CharFunction)
    /// @see #mapIfInRangeOrElse(char, char, char, CharFunction, Object)
    /// @see #mapIfInRangeOrElseGet(char, char, char, CharFunction, Supplier)
    /// @see #mapNonNullIfInRangeOrElseGet(char, char, char, CharFunction, Supplier)
    ///
    public static <R> R mapNonNullIfInRangeOrElse(char value, char min, char max, CharFunction<R> mapper, R orElse) {
        return (value >= min && value <= max) ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse);
    }
    
    ///
    /// Maps the given char value to a new value using the given mapper function if the value is within the given range.
    /// If the `value` is outside the given range, the `orElse` supplier's value is returned.
    ///
    /// The mapper function and the `orElse` supplier may be `null`.
    ///
    /// @param value  The char value to map.
    /// @param min    The lower bound of the range, inclusive.
    /// @param max    The upper bound of the range, inclusive.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is outside the given range.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is outside the given range.
    ///
    /// @see #mapIfInRange(char, char, char, CharFunction)
    /// @see #mapNonNullIfInRange(char, char, char, CharFunction)
    /// @see #mapIfInRangeOrElse(char, char, char, CharFunction, Object)
    /// @see #mapNonNullIfInRangeOrElse(char, char, char, CharFunction, Object)
    /// @see #mapNonNullIfInRangeOrElseGet(char, char, char, CharFunction, Supplier)
    ///
    public static <R> @Nullable R mapIfInRangeOrElseGet(char value, char min, char max, CharFunction<@Nullable R> mapper, Supplier<@Nullable R> orElse) {
        return (value >= min && value <= max) ? mapper.apply(value) : orElse.get();
    }
    
    ///
    /// Maps the given char value to a new value using the given mapper function if the value is within the given range.
    /// If the `value` is outside the given range, the `orElse` supplier's value is returned.
    ///
    /// Both the mapper function and the `orElse` supplier must not return `null`.
    ///
    /// @param value  The char value to map.
    /// @param min    The lower bound of the range, inclusive.
    /// @param max    The upper bound of the range, inclusive.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is outside the given range.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is outside the given range.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` supplier returns `null`.
    /// @see #mapIfInRange(char, char, char, CharFunction)
    /// @see #mapNonNullIfInRange(char, char, char, CharFunction)
    /// @see #mapIfInRangeOrElse(char, char, char, CharFunction, Object)
    /// @see #mapNonNullIfInRangeOrElse(char, char, char, CharFunction, Object)
    /// @see #mapIfInRangeOrElseGet(char, char, char, CharFunction, Supplier)
    ///
    public static <R> R mapNonNullIfInRangeOrElseGet(char value, char min, char max, CharFunction<R> mapper, Supplier<R> orElse) {
        return (value >= min && value <= max) ?
                Objects.requireNonNull(mapper.apply(value), "Mapper function must not return null") :
                Objects.requireNonNull(orElse.get());
    }
}
