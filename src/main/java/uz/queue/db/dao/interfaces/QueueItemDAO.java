package uz.queue.db.dao.interfaces;

import uz.queue.db.dto.QueueItemDTO;

import java.util.UUID;

public interface QueueItemDAO {

    QueueItemDTO increaseCounterAndSave(UUID id);
    void makeNewQueueItemsForNewDay();

}
