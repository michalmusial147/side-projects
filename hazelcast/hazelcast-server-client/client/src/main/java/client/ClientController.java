package client;


import client.notes.Note;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ClientController {

    private CacheService cacheService;

    public ClientController(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @PostMapping(value = "/data")
    public Long addData(@RequestBody List<Note> quotes) {
        return cacheService.addSnapshot(quotes);
    }


}
