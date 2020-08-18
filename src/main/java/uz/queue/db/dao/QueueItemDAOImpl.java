package uz.queue.db.dao;

import org.springframework.stereotype.Service;
import uz.queue.db.dao.interfaces.DoctorDAO;
import uz.queue.db.dao.interfaces.QueueItemDAO;
import uz.queue.db.dto.DoctorDTO;
import uz.queue.db.dto.DoctorListDTO;
import uz.queue.db.dto.QueueItemDTO;
import uz.queue.db.entities.QueueItem;
import uz.queue.db.factories.QueueItemDTOFactory;
import uz.queue.db.repositories.QueueItemRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Service
public class QueueItemDAOImpl implements QueueItemDAO {

    private final QueueItemRepository repository;
    private final DoctorDAO doctorDAO;

    public QueueItemDAOImpl(QueueItemRepository repository, DoctorDAO doctorDAO) {
        this.repository = repository;
        this.doctorDAO = doctorDAO;
    }

    @Override
    public QueueItemDTO increaseCounterAndSave(UUID id) {
        DoctorDTO doctorDTO = doctorDAO.get(id);
        QueueItem queueItem = repository.findByDoctorAndDayPassedFalse(id);

        queueItem.setCounter(queueItem.getCounter() + 1);
        queueItem.setLastOrderTimestamp(queueItem.getCurrentOrderTimestamp());
        queueItem.setCurrentOrderTimestamp(Calendar.getInstance());

        queueItem = repository.save(queueItem);

        return QueueItemDTOFactory.create(queueItem, doctorDTO);
    }

    @Override
    public void makeNewQueueItemsForNewDay() {
        repository.setAllPreviousDaysAsPassed();

        DoctorListDTO doctors = doctorDAO.getAll();
        List<QueueItem> items = new ArrayList<>();

        for (DoctorDTO doc : doctors.getDoctors()) {
            QueueItem queue = new QueueItem();
            queue.setDoctor(doc.getId());
            queue.setCounter(0);
            queue.setCreationDate(Calendar.getInstance());
            queue.setDayPassed(false);

            items.add(queue);
        }

        repository.saveAll(items);
    }
}
