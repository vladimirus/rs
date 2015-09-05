package rs.service;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import rs.dao.ModelDao;
import rs.model.Link;

import java.util.Collection;

@RunWith(MockitoJUnitRunner.class)
public class LinkManagerTest {
    @InjectMocks
    private LinkManager linkManager;
    @Mock
    private ModelDao<Link> modelDao;

    @Test
    public void shouldGet()  {

        // when
        Collection<Link> actual = linkManager.get(0, 50);

        // then
        verify(modelDao).get(0, 50);
    }
}