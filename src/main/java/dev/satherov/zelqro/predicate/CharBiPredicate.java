package dev.satherov.zelqro.predicate;

///
/// Represents a predicate of two `char` arguments.
///
@FunctionalInterface
public interface CharBiPredicate {
    
    ///
    /// Evaluates this predicate on the given values.
    ///
    /// @param left  The first predicate argument.
    /// @param right The second predicate argument.
    ///
    /// @return `true` if the values match this predicate, `false` otherwise.
    ///
    boolean test(char left, char right);
    
    ///
    /// Returns a composed predicate that represents a short-circuiting logical AND of this predicate and the `other` predicate.
    ///
    /// @param other The predicate to combine with this one.
    ///
    /// @return A composed predicate that matches only when both this predicate and the `other` predicate match.
    ///
    default CharBiPredicate and(CharBiPredicate other) {
        return (left, right) -> this.test(left, right) && other.test(left, right);
    }
    
    ///
    /// Returns a composed predicate that represents a short-circuiting logical OR of this predicate and the `other` predicate.
    ///
    /// @param other The predicate to combine with this one.
    ///
    /// @return A composed predicate that matches when either this predicate or the `other` predicate matches.
    ///
    default CharBiPredicate or(CharBiPredicate other) {
        return (left, right) -> this.test(left, right) || other.test(left, right);
    }
    
    ///
    /// @return A predicate that matches exactly when this predicate does not.
    ///
    default CharBiPredicate negate() {
        return (left, right) -> !this.test(left, right);
    }
}
