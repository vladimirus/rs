package rs.service;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import rs.dao.ModelDao;
import rs.model.Link;
import rs.model.Topic;

import java.util.Collection;

@RunWith(MockitoJUnitRunner.class)
public class TopicManagerTest {
    @InjectMocks
    private TopicManager topicManager;
    @Mock
    private ModelDao<Link> modelDao;

    @Test
    public void shouldGet()  {

        // when
        Collection<Topic> actual = topicManager.get(0, 50);

        // then
        verify(modelDao).get(0, 50);
    }
}