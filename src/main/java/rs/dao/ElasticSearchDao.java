package rs.dao;

import static org.elasticsearch.index.query.FilterBuilders.rangeFilter;
import static org.elasticsearch.index.query.MatchQueryBuilder.Operator.AND;
import static org.elasticsearch.index.query.MatchQueryBuilder.Type.BOOLEAN;
import static org.elasticsearch.index.query.QueryBuilders.functionScoreQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;
import static org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders.scriptFunction;
import static org.elasticsearch.search.sort.SortBuilders.scoreSort;

import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Repository;
import rs.model.Link;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ElasticSearchDao implements SearchDao {
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public Collection<Link> search(String query, Integer pageNo) {
//        String scriptRecency = "_score * ((0.08 / ((3.16*pow(10,-11)) * abs(currentTimeInMillis - doc['created'].date.getMillis()) + 0.05)) + 1.0)";
        String scriptRating = "_score * doc['score'].value";

        Map<String, Object> params = new HashMap<>();
        params.put("currentTimeInMillis", new Date().getTime());

        FunctionScoreQueryBuilder functionScoreQueryBuilder =
                functionScoreQuery(matchQuery("title", query).type(BOOLEAN).operator(AND))
//                        .add(scriptFunction(scriptRecency, params))
                        .add(scriptFunction(scriptRating));

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(functionScoreQueryBuilder)
                .withPageable(new PageRequest(pageNo, 10))
                .withSort(scoreSort())
                .withFilter(rangeFilter("score").gt(10))
                .withIndices("rs")
                .build();

        Page<Link> links = elasticsearchTemplate.queryForPage(searchQuery, Link.class);
        return links.getContent();
    }
}
