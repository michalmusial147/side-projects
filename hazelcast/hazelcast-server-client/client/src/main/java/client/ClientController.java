package client;


import client.notes.Note;
import client.notes.SnapshotData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ClientController {

    @Autowired
    private CacheService cacheService;

    @PostMapping(value = "/data")
    public Long addData(@RequestBody List<Note> quotes) {
        return cacheService.addSnapshoot(quotes);
    }

    @GetMapping("/data")
    public Map<Long, SnapshotData> getData() {
        return cacheService.getCache();
    }

}
