package rs.service;

import static java.util.Arrays.asList;

import org.springframework.stereotype.Service;
import rs.model.Link;

import java.util.Collection;

@Service
public class ElasticSearchManager implements SearchManager {
    @Override
    public Collection<Link> search(String query) {
        return asList(
                Link.builder().url("http://google.com").title(query).build(),
                Link.builder().url("http://google.com").title("two").build(),
                Link.builder().url("http://google.com").title("three").build());
    }
}
