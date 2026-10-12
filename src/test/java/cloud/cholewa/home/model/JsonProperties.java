package cloud.cholewa.home.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * What Jackson publishes of a generated model, read from its getters - there is no Jackson on
 * the test classpath to serialize one with.
 */
final class JsonProperties {

    private JsonProperties() {
    }

    static Set<String> of(Class<?> model) {
        return Arrays.stream(model.getMethods())
            .filter(method -> method.getName().startsWith("get"))
            .map(method -> method.getAnnotation(JsonProperty.class))
            .filter(Objects::nonNull)
            .map(JsonProperty::value)
            .collect(Collectors.toSet());
    }
}
