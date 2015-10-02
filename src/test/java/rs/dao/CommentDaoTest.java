package rs.dao;

import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static rs.TestFactory.aLink;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.FacetedPage;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import rs.model.Comment;

import java.util.Collection;

@RunWith(MockitoJUnitRunner.class)
public class CommentDaoTest {
    @InjectMocks
    private CommentDao commentDao;
    @Mock
    private ElasticsearchTemplate elasticsearchTemplate;

    @SuppressWarnings("unchecked")
    @Test
    public void shouldGet()  {
        // given
        FacetedPage page = mock(FacetedPage.class);
        given(elasticsearchTemplate.queryForPage(isA(SearchQuery.class), eq(Comment.class))).willReturn(page);
        given(page.getContent()).willReturn(singletonList(aLink("1")));

        // when
        Collection<Comment> actual = commentDao.get(1, 50);

        // then
        assertThat(actual, hasSize(1));
    }
}