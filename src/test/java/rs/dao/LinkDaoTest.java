package rs.dao;

import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static rs.TestFactory.aLink;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.FacetedPage;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import rs.model.Link;

import java.util.Collection;

@RunWith(MockitoJUnitRunner.class)
public class LinkDaoTest {
    @InjectMocks
    private LinkDao linkDao;
    @Mock
    private ElasticsearchTemplate elasticsearchTemplate;

    @SuppressWarnings("unchecked")
    @Test
    public void shouldGet()  {
        // given
        FacetedPage page = mock(FacetedPage.class);
        given(elasticsearchTemplate.queryForPage(Mockito.isA(SearchQuery.class), Mockito.eq(Link.class))).willReturn(page);
        given(page.getContent()).willReturn(singletonList(aLink("1")));

        // when
        Collection<Link> actual = linkDao.get(1, 50);

        // then
        assertThat(actual, hasSize(1));
    }
}