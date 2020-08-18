package uz.queue.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.queue.db.dao.interfaces.QueueItemDAO;

@Component
@Slf4j
public class ApplicationStartupRunner implements CommandLineRunner {
    private final QueueItemDAO queueItemDAO;

    public ApplicationStartupRunner(QueueItemDAO queueItemDAO) {
        this.queueItemDAO = queueItemDAO;
    }

    @Override
    public void run(String... args) throws Exception {
        log.warn("All previous order lists were reset. Now, counter starts at 0 again.");
        queueItemDAO.makeNewQueueItemsForNewDay();
    }
}
