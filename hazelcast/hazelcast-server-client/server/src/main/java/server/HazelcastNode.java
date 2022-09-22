package server;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MapStoreConfig;
import com.hazelcast.config.XmlConfigBuilder;
import com.hazelcast.core.Hazelcast;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class HazelcastNode {

    private  SnapshotDataRepository repository;

    private  ModelMapper modelMapper;

    public HazelcastNode(SnapshotDataRepository repository, ModelMapper modelMapper){
        this.repository = repository;
        this.modelMapper = modelMapper;
        Config config = createNewConfig("data");
        log.info("Starting node");
        Hazelcast.newHazelcastInstance(config);
     }

    private  Config createNewConfig(String mapName) {
        MapStoreConfig mapStoreConfig = new MapStoreConfig();
        AppMapLoader loader = new AppMapLoader(repository, modelMapper);
        mapStoreConfig.setImplementation(loader);
        mapStoreConfig.setWriteDelaySeconds(0);
        XmlConfigBuilder configBuilder = new XmlConfigBuilder();
        Config config = configBuilder.build();
        MapConfig mapConfig = config.getMapConfig(mapName);
        mapConfig.setMapStoreConfig(mapStoreConfig);

        return config;
    }
}
