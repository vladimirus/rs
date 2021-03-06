package rs.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rs.model.Link;
import rs.service.ModelManager;

import java.util.Collection;

@RestController
@RequestMapping("/api/links")
public class LinkController {
    @Autowired
    private ModelManager<Link> linkManager;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Link> list(@RequestParam(value="pageNumber", defaultValue="0") Integer pageNumber,
                                 @RequestParam(value="size", defaultValue="10") Integer size) {
        return linkManager.get(pageNumber, size);
    }
}
