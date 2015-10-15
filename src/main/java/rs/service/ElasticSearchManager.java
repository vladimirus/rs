package rs.service;

import static java.util.stream.Collectors.toList;

import org.elasticsearch.common.base.Splitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.dao.SearchDao;
import rs.model.Link;
import rs.model.SearchResponse;
import rs.model.SuggestResponse;

import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class ElasticSearchManager implements SearchManager {
    @Autowired
    private SearchDao searchDao;

    @Override
    public SearchResponse search(String query, Integer pageNo) {
        return searchDao.search(query, pageNo);
    }

    @Override
    public SuggestResponse suggest(String query) {
        if (query == null) {
            return SuggestResponse.builder().suggestions(Collections.<String>emptyList()).build();
        }

        if (query.length() > 2) {
            String twoLastChars = query.substring(query.length() - 2);
            if (twoLastChars.startsWith(" ")) {
                return SuggestResponse.builder().suggestions(searchDao.search(query.substring(0, query.length() - 1), 0).getLinks().stream()
                        .limit(4)
                        .map(Link::getTitle)
                        .collect(toList())).build();
            }

            if (query.contains(" ")) {
                String lastWord = Splitter.on(" ").splitToList(query).stream()
                        .reduce((a, b) -> b)
                        .orElse(query);
                String fistWords = query.substring(0, query.length() - lastWord.length());

                return SuggestResponse.builder().suggestions(searchDao.suggest(lastWord).getSuggestions().stream()
                        .map(s -> fistWords + s)
                        .collect(Collectors.toList())).build();
            }
        }

        return searchDao.suggest(query);
    }
}
