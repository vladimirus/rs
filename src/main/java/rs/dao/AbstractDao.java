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

public abstract class AbstractDao<T> {
    @Autowired
    private ElasticsearchTemplate template;

    @SuppressWarnings("unchecked")
    public Collection<T> get(RsQuery query) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchAllQuery())
                .withSort(fieldSort("created").order(ASC))
                .withPageable(new PageRequest(query.getPageNumber(), query.getSize()))
                .withIndices("rs")
                .withTypes(query.getType())
                .build();

        FacetedPage<T> page = template.queryForPage(searchQuery, query.getClazz());
        return page.getContent();
    }
}
