package rs.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import rs.model.SearchRequest;

@RunWith(MockitoJUnitRunner.class)
public class QueryParserTest {
    @InjectMocks
    private QueryParser queryParser;

    @Test
    public void shouldParse() {

        // when
        SearchRequest actual = queryParser.parse("test").build();

        // then
        assertThat(actual.getQuery(), is("test"));
    }

    @Test
    public void shouldParseKey() {

        // when
        SearchRequest actual = queryParser.parse("test one \"two three\" subreddit:lol, funny, lol, something four").build();

        // then
        assertThat(actual.getQuery(), is("test one \"two three\" four"));
        assertThat(actual.getTopics(), hasSize(3));
        assertThat(actual.getTopics(), hasItems("funny", "lol", "something"));
    }

    @Test
    public void shouldParseEmptyKey() {

        // when
        SearchRequest actual = queryParser.parse("test one subreddit: ").build();

        // then
        assertThat(actual.getQuery(), is("test one"));
        assertThat(actual.getTopics(), hasSize(0));
    }
}