import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FillLevelRecordRepository
        extends JpaRepository<FillLevelRecord, Long> {

    List<FillLevelRecord> findTopNByBinIdOrderByRecordedAtDesc(Long binId, int limit);
}
