package rs.web.api;

import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import rs.model.SuggestResponse;
import rs.service.SearchManager;

import java.util.Collection;

@RunWith(MockitoJUnitRunner.class)
public class SuggestControllerTest {
    @InjectMocks
    private SuggestController suggestController;
    @Mock
    private SearchManager searchManager;

    @Test
    public void shouldSearch()  {
        // given
        SuggestResponse response = SuggestResponse.builder().suggestions(singletonList("one")).build();
        given(searchManager.suggest("test")).willReturn(response);

        // when
        Collection<String> actual = suggestController.suggest("test");

        // then
        assertThat(actual, hasSize(1));
        verify(searchManager).suggest(anyString());
    }
}