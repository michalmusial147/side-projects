package client;


import client.notes.Note;
import client.notes.SnapshotData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class ClientController {

    private CacheService cacheService;

    public ClientController(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @PostMapping(value = "/data")
    public String addData(@RequestBody List<Note> quotes) {
        log.info("Adding data=[{}]", quotes);
        return cacheService.addSnapshot(quotes);
    }

    @PostMapping(value = "/clearCache")
    public String clearCache() {
        log.info("Clearing cache!");
        return cacheService.clearCache();
    }

    @GetMapping(value = "/{id}/data")
    public SnapshotData getData(@PathVariable String id) {
        log.info("Getting data id=[{}]", id);
        return cacheService.getData(id);
    }


}
