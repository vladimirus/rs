package rs.dao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.elasticsearch.core.FacetedPage;
import rs.model.Link;
import rs.model.SearchResponse;

@RunWith(MockitoJUnitRunner.class)
public class PageConverterTest {
    @InjectMocks
    private PageConverter pageConverter;

    @Test
    public void shouldConvert() {
        // given
        FacetedPage<Link> page = Mockito.mock(FacetedPage.class);

        // when
        SearchResponse actual = pageConverter.convert(page);

        // then
        assertThat(actual, is(notNullValue()));
        verify(page).getContent();
        verify(page).getTotalElements();
        verify(page).getNumber();
        verify(page).getTotalPages();
    }

}