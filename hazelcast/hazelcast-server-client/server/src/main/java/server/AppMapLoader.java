package server;



import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.MapLoader;
import com.hazelcast.map.MapStore;

import lombok.extern.slf4j.Slf4j;
import server.notes.SnapshotData;


import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public class AppMapLoader implements MapLoader<String, SnapshotData>, MapStore<String, SnapshotData> {

    private final SnapshotDataRepository repository;
    private final Map<String, SnapshotData> map;

    public AppMapLoader(SnapshotDataRepository repository) {
        ClientConfig config = new ClientConfig();
        HazelcastInstance hazelcastInstanceClient = com.hazelcast.client.HazelcastClient.newHazelcastClient(config);
        this.map = hazelcastInstanceClient.getMap("data");
        this.repository = repository;
    }

    @Override
    public  SnapshotData load(String key) {
        return repository.findById(key).orElse(null);
    }

    @Override
    public Map<String, SnapshotData> loadAll(Collection<String> keys) {
        return repository.findAllById(keys)
                .stream()
                .collect(Collectors.toMap(SnapshotData::getId, Function.identity()));
    }

    @Override
    public Iterable<String> loadAllKeys() {
        return map.keySet();
    }

    @Override
    public void store(String key, SnapshotData value) {
        log.info("Saving data to repository id=[{}]", key);
        repository.save(value);
    }

    @Override
    public void storeAll(Map<String, SnapshotData> map) {
        log.info("Saving data to repository data=[{}]", map);
        repository.saveAll(map.values());
    }

    @Override
    public void delete(String key) {
        log.info("Deleting data from repository id=[{}]", key);
        repository.deleteById(key);
    }

    @Override
    public void deleteAll(Collection<String> keys) {
        log.info("Deleting data from repository data=[{}]", map);
        repository.deleteAllById(keys);
    }
}
