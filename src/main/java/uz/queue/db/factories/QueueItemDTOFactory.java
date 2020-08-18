package uz.queue.db.factories;

import uz.queue.db.dto.DoctorDTO;
import uz.queue.db.dto.QueueItemDTO;
import uz.queue.db.entities.QueueItem;

import java.util.Calendar;

public final class QueueItemDTOFactory {

    public QueueItemDTOFactory() {
    }

    public static QueueItemDTO create(QueueItem item, DoctorDTO dto) {
        return new QueueItemDTO(
                dto,
                item.getCounter(),
                item.getLastOrderTimestamp(),
                item.getCurrentOrderTimestamp()
        );
    }

}
