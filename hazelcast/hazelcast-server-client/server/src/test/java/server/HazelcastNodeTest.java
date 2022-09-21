package server;

import cache.CollectionDataModel;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import server.entities.CollectionDataEntity;
import server.entities.NestedItemEntity;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class HazelcastNodeTest {

    private ModelMapper modelMapper = new ModelMapper();
    @Test
    void testMappingTest() {
        CollectionDataEntity collectionDataEntity = new CollectionDataEntity("id",
                Collections.singletonList(NestedItemEntity
                        .builder()
                        .id("id").name("name")
                        .price(2f)
                        .build())
        );
        CollectionDataModel result = modelMapper.map(collectionDataEntity, CollectionDataModel.class);
        assertThat(result.getId()).isEqualTo("id");
    }

}