package client;

import client.notes.Note;
import client.notes.SnapshotData;
import client.notes.SnapshotDataRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.flakeidgen.FlakeIdGenerator;
import com.hazelcast.map.MapLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CacheService implements MapLoader<Long, SnapshotData> {

    private final ConcurrentMap<Long, SnapshotData> map;
    private final FlakeIdGenerator idGenerator;

    private final SnapshotDataRepository repository;

    @Autowired
    public CacheService(SnapshotDataRepository repository) {
        ClientConfig config = new ClientConfig();
        HazelcastInstance hazelcastInstanceClient = com.hazelcast.client.HazelcastClient.newHazelcastClient(config);
        this.map = hazelcastInstanceClient.getMap("data");
        this.idGenerator = hazelcastInstanceClient.getFlakeIdGenerator("idGenerator");
        this.repository = repository;
    }

    public Long addSnapshoot(List<Note> quotes){
        Long newId = idGenerator.newId();
        map.put(newId, SnapshotData.builder().id(newId).quotes(quotes).build());
        return newId;
    }

    public ConcurrentMap<Long, SnapshotData> getCache(){
        return map;
    }

    @Override
    public SnapshotData load(Long key) {
        return repository.findById(key).orElse(null);
    }

    @Override
    public Map<Long, SnapshotData> loadAll(Collection<Long> keys) {
        return repository.findAllById(keys)
                .stream()
                .collect(Collectors.toMap(SnapshotData::getId, Function.identity()));
    }

    @Override
    public Iterable<Long> loadAllKeys() {
        return null;
    }
}
