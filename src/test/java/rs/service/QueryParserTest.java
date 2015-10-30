package rs.service;

import static org.hamcrest.MatcherAssert.assertThat;
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
}