package rs.web;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.Model;

@RunWith(MockitoJUnitRunner.class)
public class IndexControllerTest {
    @InjectMocks
    private IndexController indexController;

    @Test
    public void shouldIndex()  {
        // given
        Model model = mock(Model.class);

        // when
        String actual = indexController.index(model);

        // then
        assertThat(actual, is("index"));
        verify(model).addAttribute(eq("jsVersion"), any());
    }
}