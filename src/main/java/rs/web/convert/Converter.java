package rs.web.convert;

@FunctionalInterface
public interface Converter<F, T> {
    T convert(F from);
}
