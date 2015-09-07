package rs.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rs.model.Topic;
import rs.service.ModelManager;

import java.util.Collection;

@RestController
@RequestMapping("/api/topics")
public class TopicController {
    @Autowired
    private ModelManager<Topic> topicManager;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Topic> list(@RequestParam(value="pageNumber", defaultValue="0") Integer pageNumber,
                                 @RequestParam(value="size", defaultValue="50") Integer size) {
        return topicManager.get(pageNumber, size);
    }
}
