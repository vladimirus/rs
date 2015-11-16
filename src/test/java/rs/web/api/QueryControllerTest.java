package rs.web.api;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import rs.model.SearchRequest;
import rs.service.QueryParser;
import rs.web.model.UiQuery;

@RunWith(MockitoJUnitRunner.class)
public class QueryControllerTest {
    @InjectMocks
    private QueryController queryController;
    @Mock
    private QueryParser queryParser;

    @Test
    public void shouldParse()  {
        // given
        given(queryParser.parse("test")).willReturn(SearchRequest.builder().type("web").query("test").topics(asList("one", "two")));

        // when
        UiQuery actual = queryController.parse("test");

        // then
        assertThat(actual.getQuery(), is("test subreddit: one, two"));
    }
}