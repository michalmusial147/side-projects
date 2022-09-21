package server;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MapStoreConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class HazelcastNode {

    private SnapshotDataRepository repository;

    public HazelcastNode(SnapshotDataRepository repository){
        log.info("Starting node");
        Config config = new Config();
        MapConfig mapConfig = config.getMapConfig("data");
        MapStoreConfig mapStoreConfig = mapConfig.getMapStoreConfig();
        mapStoreConfig.setEnabled(true);
        config.addMapConfig(mapConfig);
        AppMapLoader customerMapLoader = new AppMapLoader(repository);
        mapStoreConfig.setImplementation(customerMapLoader);
        Hazelcast.newHazelcastInstance(config);
     }
}
