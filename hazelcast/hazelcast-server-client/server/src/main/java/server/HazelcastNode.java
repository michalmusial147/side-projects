package server;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class HazelcastNode {

    private Map<Long, Object> map;

    public HazelcastNode(){
        log.info("Starting node");
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();
        map = hazelcastInstance.getMap("data");
     }
}
