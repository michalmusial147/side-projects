package server;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.entities.CollectionDataEntity;

@Repository
public interface SnapshotDataRepository extends JpaRepository<CollectionDataEntity, String> {
}
