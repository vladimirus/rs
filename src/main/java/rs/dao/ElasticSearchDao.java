package rs.dao;

import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang.StringUtils.isNotBlank;
import static org.elasticsearch.index.query.CommonTermsQueryBuilder.Operator.AND;
import static org.elasticsearch.index.query.FilterBuilders.andFilter;
import static org.elasticsearch.index.query.FilterBuilders.orFilter;
import static org.elasticsearch.index.query.FilterBuilders.termFilter;
import static org.elasticsearch.index.query.QueryBuilders.functionScoreQuery;
import static org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders.scriptFunction;
import static org.elasticsearch.search.sort.SortBuilders.scoreSort;

import org.elasticsearch.action.suggest.SuggestResponse;
import org.elasticsearch.index.query.FilterBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.search.suggest.completion.CompletionSuggestion;
import org.elasticsearch.search.suggest.completion.CompletionSuggestionBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.facet.request.TermFacetRequestBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Repository;
import rs.model.Link;
import rs.model.SearchRequest;
import rs.model.SearchResponse;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ElasticSearchDao implements SearchDao {
    @Value("${rs.index.name:rs}")
    String indexName;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private PageConverter pageConverter;

    @Override
    public SearchResponse search(SearchRequest request) {
        String scriptRecency = "_score * ((0.08 / ((3.16*pow(10,-11)) * abs(currentTimeInMillis - doc['created'].date.getMillis()) + 0.05)) + 1.0)";
        String scriptRating = "_score * doc['score'].value";

        Map<String, Object> params = new HashMap<>();
        params.put("currentTimeInMillis", System.currentTimeMillis());

        FunctionScoreQueryBuilder functionScoreQueryBuilder =
                functionScoreQuery(QueryBuilders.commonTermsQuery("title", request.getQuery())
                        .cutoffFrequency(0.2f).highFreqOperator(AND)
                        .highFreqMinimumShouldMatch("20%")
                        .lowFreqMinimumShouldMatch("3<80%"))
                        .add(scriptFunction(scriptRecency, params))
                        .add(scriptFunction(scriptRating));

//                functionScoreQuery(QueryBuilders.matchQuery("title", query).type(Type.BOOLEAN).operator(Operator.AND).analyzer("standard"))
//                        .add(scriptFunction(scriptRecency, params))
//                        .add(scriptFunction(scriptRating));

        NativeSearchQueryBuilder searchQuery = new NativeSearchQueryBuilder()
                .withQuery(functionScoreQueryBuilder)
                .withPageable(new PageRequest(request.getPageNo(), 10))
                .withSort(scoreSort())
                .withIndices(indexName)
                .withFacet(new TermFacetRequestBuilder("topics").applyQueryFilter().fields("topic").size(5).build())
//                .withFacet(new TermFacetRequestBuilder("authors").applyQueryFilter().fields("author").size(5).build())
//                .withFacet(new TermFacetRequestBuilder("commenters").applyQueryFilter().fields("comments.author").excludeTerms("deleted").size(5).build())
                ;

        FilterBuilder filter = null;
        if (request.getTopics() != null && !request.getTopics().isEmpty()) {

            FilterBuilder[] filters = request.getTopics().stream()
                    .map(text -> termFilter("topic", text))
                    .collect(toList()).toArray(new FilterBuilder[request.getTopics().size()]);

            filter = addFilter(filter, orFilter(filters));
        }

        if (isNotBlank(request.getType()) && request.getType().equals("images")) {
            filter = addFilter(filter, termFilter("domain", "i.imgur.com"));
        }

        if (filter != null) {
            searchQuery.withFilter(filter);
        }

        return pageConverter.convert(elasticsearchTemplate.queryForPage(searchQuery.build(), Link.class));
    }

    private FilterBuilder addFilter(FilterBuilder filter, FilterBuilder topicFilter) {
        if (filter != null) {
            return andFilter(filter, topicFilter);
        } else {
            return topicFilter;
        }
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
