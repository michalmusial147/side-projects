package client;


import client.entities.SnapshotData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ClientController {

    @Autowired
    private CacheService cacheService;

    @GetMapping("/info")
    public String getConfig() {
        return "Helloworld";
    }

    @PostMapping(value = "/data")
    public Integer addData(@RequestBody SnapshotData data) {
        return cacheService.addSnapshoot(data);
    }

    @GetMapping("/data")
    public Map<Integer, String> getData() {
        return cacheService.getCache();
    }

}
