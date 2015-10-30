package rs.service;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.IntStream.range;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import rs.model.SearchRequest;
import rs.model.SearchRequest.SearchRequestBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class QueryParser {

    public SearchRequestBuilder parse(String query) {
        SearchRequestBuilder builder = SearchRequest.builder();

        List<String> components = split(prepare(query));

        builder.topics(divide(find(components, "subreddit")));
        query = components.stream()
                .filter(StringUtils::isNotBlank)
                .filter(str -> str.length() > 0)
                .reduce("", (a, b) -> a + " " + b).trim();

        return builder.query(query);
    }

    private Collection<String> divide(Collection<String> output) {
        return output.stream()
                .flatMap(str -> asList(str.split(",")).stream())
                .collect(toSet());
    }

    private Collection<String> find(List<String> components, String key) {
        Collection<Integer> keyPositions = keyPositions(components, key);
        Collection<Integer> valuePositions = valuePositions(keyPositions, components.size());

        Collection<String> result = valuePositions.stream()
                .map(components::get)
                .collect(toSet());

        keyPositions.stream().forEach(i -> components.set(i, ""));
        valuePositions.stream().forEach(i -> components.set(i, ""));

        return result;
    }

    private Collection<Integer> keyPositions(List<String> components, String key) {
        return range(0, components.size())
                .filter(i -> components.get(i).equals(key + ":"))
                .boxed()
                .collect(toSet());
    }

    private Collection<Integer> valuePositions(Collection<Integer> keyPositions, int componentsSize) {
        return keyPositions.stream()
                .filter(pos -> componentsSize > pos + 1)
                .map(pos -> pos + 1)
                .collect(toSet());
    }

    private String prepare(String query) {
        return query
                .replaceAll(",\\s*", ",")
                .replaceAll(":", ": ");
    }

    private List<String> split(String text) {
        List<String> list = new ArrayList<>();
        Matcher m = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(text);
        while (m.find()) {
            list.add(m.group(1));
        }
        return list;
    }
}
