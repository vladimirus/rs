package rs.web.convert;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static rs.TestFactory.aLink;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import rs.web.ui.LinkUi;

@RunWith(MockitoJUnitRunner.class)
public class LinkConverterTest {
    @InjectMocks
    private LinkConverter linkConverter;

    @Test
    public void shouldConvert()  {

        // when
        LinkUi actual = linkConverter.convert(aLink("id"));

        // then
        assertThat(actual, notNullValue());
    }
}