package rs.dao;

import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.FacetedPage;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import rs.TestFactory;
import rs.model.Topic;

import java.util.Collection;

@RunWith(MockitoJUnitRunner.class)
public class TopicDaoTest {
    @InjectMocks
    private TopicDao topicDao;
    @Mock
    private ElasticsearchTemplate elasticsearchTemplate;

    @SuppressWarnings("unchecked")
    @Test
    public void shouldGet()  {
        // given
        FacetedPage page = mock(FacetedPage.class);
        given(elasticsearchTemplate.queryForPage(Mockito.isA(SearchQuery.class), Mockito.eq(Topic.class))).willReturn(page);
        given(page.getContent()).willReturn(singletonList(TestFactory.aTopic("id")));

        // when
        Collection<Topic> actual = topicDao.get(1, 50);

        // then
        assertThat(actual, hasSize(1));
    }
}