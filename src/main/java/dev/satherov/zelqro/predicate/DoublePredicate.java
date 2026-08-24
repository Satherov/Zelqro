package dev.satherov.zelqro.predicate;

///
/// Represents a predicate of one `double` argument.
///
@FunctionalInterface
public interface DoublePredicate {
    
    ///
    /// Evaluates this predicate on the given value.
    ///
    /// @param value The predicate argument.
    ///
    /// @return `true` if the value matches this predicate, `false` otherwise.
    ///
    boolean test(double value);
    
    ///
    /// Returns a composed predicate that represents a short-circuiting logical AND of this predicate and the `other` predicate.
    ///
    /// @param other The predicate to combine with this one.
    ///
    /// @return A composed predicate that matches only when both this predicate and the `other` predicate match.
    ///
    default DoublePredicate and(DoublePredicate other) {
        return value -> this.test(value) && other.test(value);
    }
    
    ///
    /// Returns a composed predicate that represents a short-circuiting logical OR of this predicate and the `other` predicate.
    ///
    /// @param other The predicate to combine with this one.
    ///
    /// @return A composed predicate that matches when either this predicate or the `other` predicate matches.
    ///
    default DoublePredicate or(DoublePredicate other) {
        return value -> this.test(value) || other.test(value);
    }
    
    ///
    /// @return A predicate that matches exactly when this predicate does not.
    ///
    default DoublePredicate negate() {
        return value -> !this.test(value);
    }
}
