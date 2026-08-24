package dev.satherov.zelqro.predicate;

///
/// Represents a predicate of one `int` argument.
///
@FunctionalInterface
public interface IntPredicate {
    
    ///
    /// Evaluates this predicate on the given value.
    ///
    /// @param value The predicate argument.
    ///
    /// @return `true` if the value matches this predicate, `false` otherwise.
    ///
    boolean test(int value);
    
    ///
    /// Returns a composed predicate that represents a short-circuiting logical AND of this predicate and the `other` predicate.
    ///
    /// @param other The predicate to combine with this one.
    ///
    /// @return A composed predicate that matches only when both this predicate and the `other` predicate match.
    ///
    default IntPredicate and(IntPredicate other) {
        return value -> this.test(value) && other.test(value);
    }
    
    ///
    /// Returns a composed predicate that represents a short-circuiting logical OR of this predicate and the `other` predicate.
    ///
    /// @param other The predicate to combine with this one.
    ///
    /// @return A composed predicate that matches when either this predicate or the `other` predicate matches.
    ///
    default IntPredicate or(IntPredicate other) {
        return value -> this.test(value) || other.test(value);
    }
    
    ///
    /// @return A predicate that matches exactly when this predicate does not.
    ///
    default IntPredicate negate() {
        return value -> !this.test(value);
    }
}
