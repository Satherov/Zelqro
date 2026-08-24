package dev.satherov.zelqro.mapping;

import lombok.experimental.UtilityClass;

import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.Nullable;

import java.util.Objects;
import java.util.function.Supplier;

@UtilityClass
public class BooleanMapping {
    
    // ========== CONVERSION ==========
    
    ///
    /// Converts the given boolean value to a binary representation.
    ///
    /// @param value The boolean value to convert.
    ///
    /// @return `1` if the value is `true`, `0` otherwise.
    ///
    /// @see #toBipolar(boolean)
    ///
    @Contract(pure = true)
    public static int toBinary(boolean value) {
        return value ? 1 : 0;
    }
    
    ///
    /// Converts the given boolean value to a bipolar representation.
    ///
    /// @param value The boolean value to convert.
    ///
    /// @return `1` if the value is `true`, `-1` otherwise.
    ///
    /// @see #toBinary(boolean)
    ///
    @Contract(pure = true)
    public static int toBipolar(boolean value) {
        return value ? 1 : -1;
    }
    
    // ========== TRUE ==========
    
    ///
    /// Maps the given boolean value to a new value using the given mapper function if the value is `true`.
    ///
    /// Since the supplied value would always be `true`, the mapper is just a supplier.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value  The boolean value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is `false`.
    ///
    /// @see #mapNonNullIfTrue(boolean, Supplier)
    /// @see #mapIfTrueOrElse(boolean, Supplier, Object)
    /// @see #mapNonNullIfTrueOrElse(boolean, Supplier, Object)
    /// @see #mapIfTrueOrElseGet(boolean, Supplier, Supplier)
    /// @see #mapNonNullIfTrueOrElseGet(boolean, Supplier, Supplier)
    ///
    @Contract("false, _ -> null")
    public static <R> @Nullable R mapIfTrue(boolean value, Supplier<@Nullable R> mapper) {
        return value ? mapper.get() : null;
    }
    
    ///
    /// Maps the given boolean value to a new value using the given mapper function if the value is `true`.
    ///
    /// Since the supplied value would always be `true`, the mapper is just a supplier.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value  The boolean value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is `false`.
    ///
    /// @throws NullPointerException If the mapper function returns `null`.
    /// @see #mapIfTrue(boolean, Supplier)
    /// @see #mapIfTrueOrElse(boolean, Supplier, Object)
    /// @see #mapNonNullIfTrueOrElse(boolean, Supplier, Object)
    /// @see #mapIfTrueOrElseGet(boolean, Supplier, Supplier)
    /// @see #mapNonNullIfTrueOrElseGet(boolean, Supplier, Supplier)
    ///
    @Contract("false, _ -> null")
    public static <R> @Nullable R mapNonNullIfTrue(boolean value, Supplier<R> mapper) {
        return value ? Objects.requireNonNull(mapper.get(), "Mapper function must not return null") : null;
    }
    
    ///
    /// Maps the given boolean value to a new value using the given mapper function if the value is `true`.
    /// If the `value` is `false`, the `orElse` value is returned.
    ///
    /// Since the supplied value would always be `true`, the mapper is just a supplier.
    ///
    /// The mapper function and the `orElse` value may be `null`.
    ///
    /// @param value  The boolean value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is `false`.
    ///
    /// @return The mapped value, or `orElse` if the `value` is `false`.
    ///
    /// @see #mapIfTrue(boolean, Supplier)
    /// @see #mapNonNullIfTrue(boolean, Supplier)
    /// @see #mapNonNullIfTrueOrElse(boolean, Supplier, Object)
    /// @see #mapIfTrueOrElseGet(boolean, Supplier, Supplier)
    /// @see #mapNonNullIfTrueOrElseGet(boolean, Supplier, Supplier)
    ///
    @Contract("false, _, _ -> param3")
    public static <R> @Nullable R mapIfTrueOrElse(boolean value, Supplier<@Nullable R> mapper, @Nullable R orElse) {
        return value ? mapper.get() : orElse;
    }
    
    ///
    /// Maps the given boolean value to a new value using the given mapper function if the value is `true`.
    /// If the `value` is `false`, the `orElse` value is returned.
    ///
    /// Since the supplied value would always be `true`, the mapper is just a supplier.
    ///
    /// Both the mapper function and the `orElse` value must not return `null`.
    ///
    /// @param value  The boolean value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is `false`.
    ///
    /// @return The mapped value, or `orElse` if the `value` is `false`.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` value is `null`.
    /// @see #mapIfTrue(boolean, Supplier)
    /// @see #mapNonNullIfTrue(boolean, Supplier)
    /// @see #mapIfTrueOrElse(boolean, Supplier, Object)
    /// @see #mapIfTrueOrElseGet(boolean, Supplier, Supplier)
    /// @see #mapNonNullIfTrueOrElseGet(boolean, Supplier, Supplier)
    ///
    @Contract("false, _, _ -> param3")
    public static <R> R mapNonNullIfTrueOrElse(boolean value, Supplier<R> mapper, R orElse) {
        return value ?
                Objects.requireNonNull(mapper.get(), "Mapper function must not return null") :
                Objects.requireNonNull(orElse);
    }
    
    ///
    /// Maps the given boolean value to a new value using the given mapper function if the value is `true`.
    /// If the `value` is `false`, the `orElse` supplier's value is returned.
    ///
    /// Since the supplied value would always be `true`, the mapper is just a supplier.
    ///
    /// The mapper function and the `orElse` supplier may be `null`.
    ///
    /// @param value  The boolean value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is `false`.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is `false`.
    ///
    /// @see #mapIfTrue(boolean, Supplier)
    /// @see #mapNonNullIfTrue(boolean, Supplier)
    /// @see #mapIfTrueOrElse(boolean, Supplier, Object)
    /// @see #mapNonNullIfTrueOrElse(boolean, Supplier, Object)
    /// @see #mapNonNullIfTrueOrElseGet(boolean, Supplier, Supplier)
    ///
    public static <R> @Nullable R mapIfTrueOrElseGet(boolean value, Supplier<@Nullable R> mapper, Supplier<@Nullable R> orElse) {
        return value ? mapper.get() : orElse.get();
    }
    
    ///
    /// Maps the given boolean value to a new value using the given mapper function if the value is `true`.
    /// If the `value` is `false`, the `orElse` supplier's value is returned.
    ///
    /// Since the supplied value would always be `true`, the mapper is just a supplier.
    ///
    /// Both the mapper function and the `orElse` supplier must not return `null`.
    ///
    /// @param value  The boolean value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is `false`.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is `false`.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` supplier returns `null`.
    /// @see #mapIfTrue(boolean, Supplier)
    /// @see #mapNonNullIfTrue(boolean, Supplier)
    /// @see #mapIfTrueOrElse(boolean, Supplier, Object)
    /// @see #mapNonNullIfTrueOrElse(boolean, Supplier, Object)
    /// @see #mapIfTrueOrElseGet(boolean, Supplier, Supplier)
    ///
    public static <R> R mapNonNullIfTrueOrElseGet(boolean value, Supplier<R> mapper, Supplier<R> orElse) {
        return value ?
                Objects.requireNonNull(mapper.get(), "Mapper function must not return null") :
                Objects.requireNonNull(orElse.get());
    }
    
    // ========== FALSE ==========
    
    ///
    /// Maps the given boolean value to a new value using the given mapper function if the value is `false`.
    ///
    /// Since the supplied value would always be `false`, the mapper is just a supplier.
    ///
    /// The mapper function may return `null`.
    ///
    /// @param value  The boolean value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is `true`.
    ///
    /// @see #mapNonNullIfFalse(boolean, Supplier)
    /// @see #mapIfFalseOrElse(boolean, Supplier, Object)
    /// @see #mapNonNullIfFalseOrElse(boolean, Supplier, Object)
    /// @see #mapIfFalseOrElseGet(boolean, Supplier, Supplier)
    /// @see #mapNonNullIfFalseOrElseGet(boolean, Supplier, Supplier)
    ///
    @Contract("true, _ -> null")
    public static <R> @Nullable R mapIfFalse(boolean value, Supplier<@Nullable R> mapper) {
        return !value ? mapper.get() : null;
    }
    
    ///
    /// Maps the given boolean value to a new value using the given mapper function if the value is `false`.
    ///
    /// Since the supplied value would always be `false`, the mapper is just a supplier.
    ///
    /// The mapper function must not return `null`.
    ///
    /// @param value  The boolean value to map.
    /// @param mapper The mapper function.
    ///
    /// @return The mapped value, or `null` if the `value` is `true`.
    ///
    /// @throws NullPointerException If the mapper function returns `null`.
    /// @see #mapIfFalse(boolean, Supplier)
    /// @see #mapIfFalseOrElse(boolean, Supplier, Object)
    /// @see #mapNonNullIfFalseOrElse(boolean, Supplier, Object)
    /// @see #mapIfFalseOrElseGet(boolean, Supplier, Supplier)
    /// @see #mapNonNullIfFalseOrElseGet(boolean, Supplier, Supplier)
    ///
    @Contract("true, _ -> null")
    public static <R> @Nullable R mapNonNullIfFalse(boolean value, Supplier<R> mapper) {
        return !value ? Objects.requireNonNull(mapper.get(), "Mapper function must not return null") : null;
    }
    
    ///
    /// Maps the given boolean value to a new value using the given mapper function if the value is `false`.
    /// If the `value` is `true`, the `orElse` value is returned.
    ///
    /// Since the supplied value would always be `false`, the mapper is just a supplier.
    ///
    /// The mapper function and the `orElse` value may be `null`.
    ///
    /// @param value  The boolean value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is `true`.
    ///
    /// @return The mapped value, or `orElse` if the `value` is `true`.
    ///
    /// @see #mapIfFalse(boolean, Supplier)
    /// @see #mapNonNullIfFalse(boolean, Supplier)
    /// @see #mapNonNullIfFalseOrElse(boolean, Supplier, Object)
    /// @see #mapIfFalseOrElseGet(boolean, Supplier, Supplier)
    /// @see #mapNonNullIfFalseOrElseGet(boolean, Supplier, Supplier)
    ///
    @Contract("true, _, _ -> param3")
    public static <R> @Nullable R mapIfFalseOrElse(boolean value, Supplier<@Nullable R> mapper, @Nullable R orElse) {
        return !value ? mapper.get() : orElse;
    }
    
    ///
    /// Maps the given boolean value to a new value using the given mapper function if the value is `false`.
    /// If the `value` is `true`, the `orElse` value is returned.
    ///
    /// Since the supplied value would always be `false`, the mapper is just a supplier.
    ///
    /// Both the mapper function and the `orElse` value must not return `null`.
    ///
    /// @param value  The boolean value to map.
    /// @param mapper The mapper function.
    /// @param orElse The value to return if the `value` is `true`.
    ///
    /// @return The mapped value, or `orElse` if the `value` is `true`.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` value is `null`.
    /// @see #mapIfFalse(boolean, Supplier)
    /// @see #mapNonNullIfFalse(boolean, Supplier)
    /// @see #mapIfFalseOrElse(boolean, Supplier, Object)
    /// @see #mapIfFalseOrElseGet(boolean, Supplier, Supplier)
    /// @see #mapNonNullIfFalseOrElseGet(boolean, Supplier, Supplier)
    ///
    @Contract("true, _, _ -> param3")
    public static <R> R mapNonNullIfFalseOrElse(boolean value, Supplier<R> mapper, R orElse) {
        return !value ?
                Objects.requireNonNull(mapper.get(), "Mapper function must not return null") :
                Objects.requireNonNull(orElse);
    }
    
    ///
    /// Maps the given boolean value to a new value using the given mapper function if the value is `false`.
    /// If the `value` is `true`, the `orElse` supplier's value is returned.
    ///
    /// Since the supplied value would always be `false`, the mapper is just a supplier.
    ///
    /// The mapper function and the `orElse` supplier may be `null`.
    ///
    /// @param value  The boolean value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is `true`.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is `true`.
    ///
    /// @see #mapIfFalse(boolean, Supplier)
    /// @see #mapNonNullIfFalse(boolean, Supplier)
    /// @see #mapIfFalseOrElse(boolean, Supplier, Object)
    /// @see #mapNonNullIfFalseOrElse(boolean, Supplier, Object)
    /// @see #mapNonNullIfFalseOrElseGet(boolean, Supplier, Supplier)
    ///
    public static <R> @Nullable R mapIfFalseOrElseGet(boolean value, Supplier<@Nullable R> mapper, Supplier<@Nullable R> orElse) {
        return !value ? mapper.get() : orElse.get();
    }
    
    ///
    /// Maps the given boolean value to a new value using the given mapper function if the value is `false`.
    /// If the `value` is `true`, the `orElse` supplier's value is returned.
    ///
    /// Since the supplied value would always be `false`, the mapper is just a supplier.
    ///
    /// Both the mapper function and the `orElse` supplier must not return `null`.
    ///
    /// @param value  The boolean value to map.
    /// @param mapper The mapper function.
    /// @param orElse The supplier of the value to return if the `value` is `true`.
    ///
    /// @return The mapped value, or the value returned by the `orElse` supplier if the `value` is `true`.
    ///
    /// @throws NullPointerException If the mapper function or the `orElse` supplier returns `null`.
    /// @see #mapIfFalse(boolean, Supplier)
    /// @see #mapNonNullIfFalse(boolean, Supplier)
    /// @see #mapIfFalseOrElse(boolean, Supplier, Object)
    /// @see #mapNonNullIfFalseOrElse(boolean, Supplier, Object)
    /// @see #mapIfFalseOrElseGet(boolean, Supplier, Supplier)
    ///
    public static <R> R mapNonNullIfFalseOrElseGet(boolean value, Supplier<R> mapper, Supplier<R> orElse) {
        return !value ?
                Objects.requireNonNull(mapper.get(), "Mapper function must not return null") :
                Objects.requireNonNull(orElse.get());
    }
}
