package rs.web.api;

import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static rs.TestFactory.aTopic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import rs.model.Topic;
import rs.service.ModelManager;

import java.util.Collection;

@RunWith(MockitoJUnitRunner.class)
public class TopicControllerTest {
    @InjectMocks
    private TopicController topicController;
    @Mock
    private ModelManager modelManager;

    @Test
    public void shouldList()  {
        // given
        given(modelManager.get(anyInt(), anyInt())).willReturn(singletonList(aTopic("1")));

        // when
        Collection<Topic> actual = topicController.list(0, 50);

        // then
        assertThat(actual, hasSize(1));
        verify(modelManager).get(0, 50);
    }
}