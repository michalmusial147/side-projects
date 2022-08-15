package client;

import client.entities.SnapshotData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentMap;

@Service
public class CacheService {

    private ConcurrentMap<Integer, String> map;
    private ObjectMapper mapper = new ObjectMapper();


    public CacheService(){
        ClientConfig config = new ClientConfig();
        HazelcastInstance hazelcastInstanceClient = com.hazelcast.client.HazelcastClient.newHazelcastClient(config);
        map = hazelcastInstanceClient.getMap("data");
    }

    public Integer addSnapshoot(SnapshotData snapshoot)  {
        int code = snapshoot.hashCode();
        try {
            map.put(code, mapper.writeValueAsString(snapshoot));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return code;
    }

    public Map<Integer, String> getCache(){
        return map;
    }
}
