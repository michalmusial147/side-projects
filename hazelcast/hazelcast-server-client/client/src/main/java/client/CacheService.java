package client;

import cache.CollectionDataModel;
import cache.NestedItemModel;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.flakeidgen.FlakeIdGenerator;

import com.hazelcast.map.IMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;

import static com.hazelcast.client.HazelcastClient.newHazelcastClient;


@Slf4j
@Service
public class CacheService  {

    private final IMap<String, CollectionDataModel> map;
    private final FlakeIdGenerator idGenerator;

    public CacheService() {
        ClientConfig config = new ClientConfig();
        HazelcastInstance hazelcastInstanceClient =  HazelcastClient.newHazelcastClient(config);
        this.map = hazelcastInstanceClient.getMap("data");
        this.idGenerator = hazelcastInstanceClient.getFlakeIdGenerator("idGenerator");
    }

    public String addSnapshot(List<NestedItemModel> notes) {
        String newId = String.valueOf(idGenerator.newId());
        map.put(newId, CollectionDataModel.builder().id(newId).nestedItems(notes).build());
        log.info("Saved data id=[{}]", newId);
        return newId;
    }

    public CollectionDataModel getData(String id) {
        return map.get(id);
    }

    public String clearCache() {
        Iterable<String> keys = map.keySet();
        keys.iterator().forEachRemaining(map::remove);
        log.info("Cache cleared keys=[{}]", keys);
        return null;
    }
}
