package rs.web;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class IndexControllerTest {
    @InjectMocks
    private IndexController indexController;

    @Test
    public void shouldIndex()  {

        // when
        String actual = indexController.index();

        // then
        assertThat(actual, is("index"));
    }
}