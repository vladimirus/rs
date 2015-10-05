package rs.service;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static rs.TestFactory.aLink;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import rs.dao.SearchDao;
import rs.model.SearchResponse;

@RunWith(MockitoJUnitRunner.class)
public class ElasticSearchManagerTest {
    @InjectMocks
    private ElasticSearchManager elasticSearchManager;
    @Mock
    private SearchDao searchDao;

    @Test
    public void shouldSearch() {
        // given
        SearchResponse response = SearchResponse.builder().totalPages(1).links(asList(aLink("1"), aLink("2"))).build();
        given(searchDao.search("test", 0)).willReturn(response);

        // when
        SearchResponse actual = elasticSearchManager.search("test", 0);

        // then
        assertThat(actual.getLinks(), hasSize(2));
        verify(searchDao).search(anyString(), eq(0));
    }

}