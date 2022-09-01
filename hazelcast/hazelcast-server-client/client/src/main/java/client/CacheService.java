package client;

import client.notes.Note;
import client.notes.SnapshotData;
import client.notes.SnapshotDataRepository;
import client.users.AppUser;
import client.users.AppUserRepository;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.flakeidgen.FlakeIdGenerator;
import com.hazelcast.map.MapLoader;
import com.hazelcast.map.MapStore;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CacheService implements MapLoader<Long, SnapshotData>, MapStore<Long, SnapshotData> {

    private final Map<Long, SnapshotData> map;
    private final FlakeIdGenerator idGenerator;
    private final AppUserRepository appUserRepository;
    private final SnapshotDataRepository repository;

    public CacheService(SnapshotDataRepository repository, AppUserRepository appUserRepository) {
        ClientConfig config = new ClientConfig();
        HazelcastInstance hazelcastInstanceClient = com.hazelcast.client.HazelcastClient.newHazelcastClient(config);
        this.map = hazelcastInstanceClient.getMap("data");
        this.idGenerator = hazelcastInstanceClient.getFlakeIdGenerator("idGenerator");
        this.repository = repository;
        this.appUserRepository = appUserRepository;
    }

    public Long addSnapshot(List<Note> notes) {
        Long newId = idGenerator.newId();
        map.put(newId, SnapshotData.builder().id(newId).notes(notes).build());
        appUserRepository.save(AppUser.builder().build());
        return newId;
    }

    public SnapshotData findById(Long id) {
        return map.get(id);
    }

    @Override
    public  SnapshotData load(Long key) {
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
        return map.keySet();
    }

    @Override
    public void store(Long key, SnapshotData value) {

    }

    @Override
    public void storeAll(Map<Long, SnapshotData> map) {

    }

    @Override
    public void delete(Long key) {

    }

    @Override
    public void deleteAll(Collection<Long> keys) {

    }
}
