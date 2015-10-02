package rs.web.api;

import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static rs.TestFactory.aLink;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import rs.model.Link;
import rs.service.SearchManager;

import java.util.Collection;

@RunWith(MockitoJUnitRunner.class)
public class SearchControllerTest {
    @InjectMocks
    private SearchController searchController;
    @Mock
    private SearchManager searchManager;

    @Test
    public void shouldSearch()  {
        // given
        Link link = aLink("id");
        given(searchManager.search("test")).willReturn(singletonList(link));

        // when
        Collection<Link> actual = searchController.search("test");

        // then
        assertThat(actual, hasSize(1));
        verify(searchManager).search(anyString());
    }
}