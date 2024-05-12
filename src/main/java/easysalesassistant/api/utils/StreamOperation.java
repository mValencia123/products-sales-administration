package easysalesassistant.api.utils;

import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class StreamOperation {
    public static <T> Stream<T> getStreamFromIterable(Iterable<T> iterable) {
        Spliterator<T> spliterator = iterable.spliterator();
        return StreamSupport.stream(spliterator, false);
    }
}
