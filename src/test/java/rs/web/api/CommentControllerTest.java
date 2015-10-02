package rs.web.api;

import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static rs.TestFactory.aComment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import rs.model.Comment;
import rs.service.CommentManager;

import java.util.Collection;

@RunWith(MockitoJUnitRunner.class)
public class CommentControllerTest {
    @InjectMocks
    private CommentController commentController;
    @Mock
    private CommentManager commentManager;

    @Test
    public void shouldList()  {
        // given
        given(commentManager.get(anyInt(), anyInt())).willReturn(singletonList(aComment("id")));

        // when
        Collection<Comment> actual = commentController.list(0, 50);

        // then
        assertThat(actual, hasSize(1));
        verify(commentManager).get(0, 50);
    }
}