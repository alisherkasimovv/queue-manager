package uz.queue.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import uz.queue.db.entities.QueueItem;

import javax.transaction.Transactional;
import java.util.UUID;

public interface QueueItemRepository extends JpaRepository<QueueItem, UUID> {

    QueueItem findByDoctorAndDayPassedFalse(UUID id);

    @Transactional
    @Modifying
    @Query("UPDATE QueueItem qi SET qi.dayPassed = TRUE")
    void setAllPreviousDaysAsPassed();

}
