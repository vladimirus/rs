package rs.web.api;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import rs.web.ui.Link;

import java.util.Collection;

@RunWith(MockitoJUnitRunner.class)
public class SearchControllerTest {
    @InjectMocks
    private SearchController searchController;

    @Test
    public void shouldSearch()  {

        // when
        Collection<Link> actual = searchController.search("test");

        // then
        assertThat(actual, hasSize(3));
    }
}