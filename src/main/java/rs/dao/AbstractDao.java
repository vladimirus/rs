package rs.dao;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;
import static org.elasticsearch.search.sort.SortBuilders.fieldSort;
import static org.elasticsearch.search.sort.SortOrder.ASC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.FacetedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.util.Collection;

public abstract class AbstractDao<T> implements ModelDao<T> {
    @Autowired
    private ElasticsearchTemplate template;

    public abstract Class<T> modelClass();

    @Override
    public Collection<T> get(int pageNumber, int size) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchAllQuery())
                .withSort(fieldSort("created").order(ASC))
                .withPageable(new PageRequest(pageNumber, size))
                .withIndices("rs")
                .withTypes("link")
                .build();

        FacetedPage<T> page = template.queryForPage(searchQuery, modelClass());
        return page.getContent();
    }
}
