package client.notes;


import org.springframework.data.jpa.repository.JpaRepository;

public interface SnapshotDataRepository extends JpaRepository<SnapshotData, String> {
}
