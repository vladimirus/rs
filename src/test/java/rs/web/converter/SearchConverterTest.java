package rs.web.converter;

import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static rs.TestFactory.aLink;
import static rs.TestFactory.aLinkBuilder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import rs.model.Link;
import rs.model.SearchResponse;
import rs.web.model.UiLink;
import rs.web.model.UiSearchResponse;

@RunWith(MockitoJUnitRunner.class)
public class SearchConverterTest {
    @InjectMocks
    private SearchConverter searchConverter;

    @Test
    public void shouldConvert() {
        // given
        Link link = aLink("id");
        SearchResponse response = SearchResponse.builder().totalPages(1).links(singletonList(link)).build();

        // when
        UiSearchResponse actual = searchConverter.convert(response);

        // then
        assertThat(actual.getLinks(), hasSize(1));
    }

    @Test
    public void shouldConvertLink() {
        // given
        Link link = aLinkBuilder()
                .url("url")
                .title("title")
                .build();

        // when
        UiLink actual = searchConverter.convert(link);

        // then
        assertThat(actual.getTitle(), is("title"));
    }

    @Test
    public void shouldConvertImageUrl() {

        // when
        String actual = searchConverter.imageUrl("https://i.imgur.com/86XDCWJ.gifv");

        // then
        assertThat(actual, is("http://i.imgur.com/86XDCWJm.jpg"));
    }

    @Test
    public void shouldNotConvertImageUrl() {

        // when
        String actual = searchConverter.imageUrl("https://www.youtube.com/watch?v=sX7Z01JfvUA&amp;");

        // then
        assertThat(actual, is(nullValue()));
    }
}