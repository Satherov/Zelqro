package dev.satherov.zelqro.utils;

import java.util.Map;
import java.util.stream.Collectors;

public class ReflectionUtils {
    
    private static final Map<Class<?>, Class<?>> PRIMITIVE_TO_WRAPPER = Map.of(
        byte.class, Byte.class,
        short.class, Short.class,
        int.class, Integer.class,
        long.class, Long.class,
        float.class, Float.class,
        double.class, Double.class,
        char.class, Character.class,
        boolean.class, Boolean.class
    );
    
    private static final Map<Class<?>, Class<?>> WRAPPER_TO_PRIMITIVE = ReflectionUtils.PRIMITIVE_TO_WRAPPER.entrySet()
            .stream()
            .collect(Collectors.toUnmodifiableMap(
                    Map.Entry::getValue,
                    Map.Entry::getKey
            ));
    
    ///
    /// Gets the primitive type of the given class or returns
    /// itself if the class does not have a primitive equivalent.
    ///
    /// @param clazz The class to get the primitive type of.
    ///
    /// @return The primitive type of the given class, or itself if the class does not have a primitive equivalent.
    ///
    public static Class<?> getPrimitiveType(Class<?> clazz) {
        return ReflectionUtils.WRAPPER_TO_PRIMITIVE.getOrDefault(clazz, clazz);
    }
    
    ///
    /// Gets the wrapper type of the given class or returns
    /// itself if the class does not have a wrapper equivalent.
    ///
    /// @param clazz The class to get the wrapper type of.
    ///
    /// @return The wrapper type of the given class, or itself if the class does not have a wrapper equivalent.
    ///
    public static Class<?> getWrapperType(Class<?> clazz) {
        return ReflectionUtils.PRIMITIVE_TO_WRAPPER.getOrDefault(clazz, clazz);
    }
}
