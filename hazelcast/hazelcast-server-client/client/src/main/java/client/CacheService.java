package client;

import client.notes.Note;
import client.notes.SnapshotData;
import client.notes.SnapshotDataRepository;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.flakeidgen.FlakeIdGenerator;
import com.hazelcast.map.MapLoader;
import com.hazelcast.map.MapStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CacheService  {

    private final Map<String, SnapshotData> map;
    private final FlakeIdGenerator idGenerator;

    public CacheService(SnapshotDataRepository repository) {
        ClientConfig config = new ClientConfig();
        HazelcastInstance hazelcastInstanceClient = com.hazelcast.client.HazelcastClient.newHazelcastClient(config);
        this.map = hazelcastInstanceClient.getMap("data");
        this.idGenerator = hazelcastInstanceClient.getFlakeIdGenerator("idGenerator");
        this.repository = repository;
    }

    public String addSnapshot(List<Note> notes) {
        String newId = String.valueOf(idGenerator.newId());
        map.put(newId, SnapshotData.builder().id(newId).notes(notes).build());
        log.info("Saved data id=[{}]", newId);
        return newId;
    }

    public SnapshotData getData(String id) {
        return map.get(id);
    }



    public String clearCache() {
        Iterable<String> keys = map.keySet();
        keys.iterator().forEachRemaining(map::remove);
        log.info("Cache cleared keys=[{}]", keys);
        return null;
    }
}
