package rs.service;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import rs.dao.SearchDao;
import rs.model.SearchRequest;
import rs.model.SearchResponse;
import rs.model.SuggestResponse;

@RunWith(MockitoJUnitRunner.class)
public class ElasticSearchManagerTest {
    @InjectMocks
    private ElasticSearchManager elasticSearchManager;
    @Mock
    private SearchDao searchDao;
    @Mock
    private QueryParser queryParser;

    @Test
    public void shouldSearch() {
        // given
        given(queryParser.parse("test")).willReturn(SearchRequest.builder().query("test"));

        // when
        SearchResponse actual = elasticSearchManager.search("test", 0, "web");

        // then
        verify(searchDao).search(any(SearchRequest.class));
        verify(queryParser).parse("test");
    }

    @Test
    public void shouldSuggest() {
        // given
        SuggestResponse response = SuggestResponse.builder().suggestions(asList("1", "2")).build();
        given(searchDao.suggest("test")).willReturn(response);

        // when
        SuggestResponse actual = elasticSearchManager.suggest("test");

        // then
        assertThat(actual.getSuggestions(), hasSize(2));
        verify(searchDao).suggest(anyString());
    }

}