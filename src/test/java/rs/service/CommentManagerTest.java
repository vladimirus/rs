package rs.service;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import rs.dao.CommentDao;

@RunWith(MockitoJUnitRunner.class)
public class CommentManagerTest {
    @InjectMocks
    private CommentManager commentManager;
    @Mock
    private CommentDao commentDao;

    @Test
    public void shouldGet()  {

        // when
        commentManager.get(0, 50);

        // then
        verify(commentDao).get(0, 50);
    }

    @Test
    public void shouldGetTopComment()  {

        // when
        commentManager.getTopComment("linkId");

        // then
        verify(commentDao).getTopComment("linkId");
    }
}