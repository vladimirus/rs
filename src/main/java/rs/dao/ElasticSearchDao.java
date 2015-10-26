package rs.dao;

import static java.util.stream.Collectors.toList;
import static org.elasticsearch.index.query.CommonTermsQueryBuilder.Operator.AND;
import static org.elasticsearch.index.query.FilterBuilders.rangeFilter;
import static org.elasticsearch.index.query.QueryBuilders.functionScoreQuery;
import static org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders.scriptFunction;
import static org.elasticsearch.search.sort.SortBuilders.scoreSort;

import org.elasticsearch.action.suggest.SuggestResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.search.suggest.completion.CompletionSuggestion;
import org.elasticsearch.search.suggest.completion.CompletionSuggestionBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Repository;
import rs.model.Link;
import rs.model.SearchResponse;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ElasticSearchDao implements SearchDao {
    @Value("${rs.index.name:rs}")
    String indexName;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public SearchResponse search(String query, Integer pageNo) {
        String scriptRecency = "_score * ((0.08 / ((3.16*pow(10,-11)) * abs(currentTimeInMillis - doc['created'].date.getMillis()) + 0.05)) + 1.0)";
        String scriptRating = "_score * doc['score'].value";

        Map<String, Object> params = new HashMap<>();
        params.put("currentTimeInMillis", System.currentTimeMillis());

        FunctionScoreQueryBuilder functionScoreQueryBuilder =
                functionScoreQuery(QueryBuilders.commonTermsQuery("title", query)
                        .cutoffFrequency(0.2f).highFreqOperator(AND)
                        .highFreqMinimumShouldMatch("3<80%")
                        .lowFreqMinimumShouldMatch("50%"))
                        .add(scriptFunction(scriptRecency, params))
                        .add(scriptFunction(scriptRating));

//                functionScoreQuery(QueryBuilders.matchQuery("title", query).type(Type.BOOLEAN).operator(Operator.AND).analyzer("standard"))
//                        .add(scriptFunction(scriptRecency, params))
//                        .add(scriptFunction(scriptRating));

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(functionScoreQueryBuilder)
                .withPageable(new PageRequest(pageNo, 10))
                .withSort(scoreSort())
                .withFilter(rangeFilter("score").gt(10))
                .withIndices(indexName)
                .build();

        Page<Link> page = elasticsearchTemplate.queryForPage(searchQuery, Link.class);
        return SearchResponse.builder().links(page.getContent())
                .totalElements(page.getTotalElements())
                .currentPage(page.getNumber())
                .totalPages(page.getTotalPages())
                .build();
    }

    @Override
    public rs.model.SuggestResponse suggest(String query) {

        CompletionSuggestionBuilder completionSuggestionBuilder = new CompletionSuggestionBuilder("suggestion")
                .text(query)
                .field("suggest")
                .size(4);

        SuggestResponse suggestResponse = elasticsearchTemplate.suggest(completionSuggestionBuilder, indexName);
        CompletionSuggestion completionSuggestion = suggestResponse.getSuggest().getSuggestion("suggestion");

        return rs.model.SuggestResponse.builder().suggestions(
                completionSuggestion.getEntries().get(0).getOptions().stream()
                        .map(o -> o.getText().string())
                        .collect(toList())).build();
    }
}
