package rs.web.api;

import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static rs.TestFactory.aLink;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import rs.model.Link;
import rs.service.ModelManager;

import java.util.Collection;

@RunWith(MockitoJUnitRunner.class)
public class LinkControllerTest {
    @InjectMocks
    private LinkController linkController;
    @Mock
    private ModelManager modelManager;

    @Test
    public void shouldList()  {
        // given
        given(modelManager.get(anyInt(), anyInt())).willReturn(singletonList(aLink("1")));

        // when
        Collection<Link> actual = linkController.list(0, 50);

        // then
        assertThat(actual, hasSize(1));
        verify(modelManager).get(0, 50);
    }
}