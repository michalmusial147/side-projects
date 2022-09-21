package server;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.notes.SnapshotData;

@Repository
public interface SnapshotDataRepository extends JpaRepository<SnapshotData, String> {
}
