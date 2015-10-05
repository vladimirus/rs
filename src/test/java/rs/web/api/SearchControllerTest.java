package rs.web.api;

import static java.util.Collections.singletonList;
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
import rs.model.Link;
import rs.model.SearchResponse;
import rs.service.SearchManager;

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
        SearchResponse response = SearchResponse.builder().totalPages(1).links(singletonList(link)).build();

        given(searchManager.search("test", 0)).willReturn(response);

        // when
        SearchResponse actual = searchController.search("test", 0);

        // then
        assertThat(actual.getLinks(), hasSize(1));
        verify(searchManager).search(anyString(), eq(0));
    }
}