package rs.dao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.FacetedPage;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import rs.model.SearchRequest;
import rs.model.SearchResponse;

@RunWith(MockitoJUnitRunner.class)
public class ElasticSearchDaoTest {
    @InjectMocks
    private ElasticSearchDao elasticSearchDao;
    @Mock
    private ElasticsearchTemplate elasticsearchTemplate;
    @Mock
    private PageConverter pageConverter;

    @Test
    public void shouldSearch() {
        // given
        FacetedPage page = Mockito.mock(FacetedPage.class);
        given(elasticsearchTemplate.queryForPage(isA(SearchQuery.class), any())).willReturn(page);
        given(pageConverter.convert(page, 10)).willReturn(SearchResponse.builder().build());
        SearchRequest request = SearchRequest.builder().type("web").query("test").pageNo(0).build();

        // when
        SearchResponse actual = elasticSearchDao.search(request);

        // then
        assertThat(actual, is(notNullValue()));
        verify(elasticsearchTemplate).queryForPage(isA(SearchQuery.class), any());
        verify(pageConverter).convert(isA(FacetedPage.class), anyInt());
    }
}