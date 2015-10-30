package rs.service;

import org.springframework.stereotype.Component;
import rs.model.SearchRequest;

@Component
public class QueryParser {

    public SearchRequest.SearchRequestBuilder parse(String query) {
        return SearchRequest.builder().query(query);
    }
}
