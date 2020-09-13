package uz.queue.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.queue.db.dao.interfaces.QueueItemDAO;

@Component
@Slf4j
public class ApplicationStartupRunner implements CommandLineRunner {
    private final QueueItemDAO queueItemDAO;
    private final PrinterService printerService;
    private final Drawer drawer;

    public ApplicationStartupRunner(QueueItemDAO queueItemDAO, PrinterService printerService, Drawer drawer) {
        this.queueItemDAO = queueItemDAO;
        this.printerService = printerService;
        this.drawer = drawer;
    }

    @Override
    public void run(String... args) {
        log.warn("All previous order lists were reset. Now, counter starts at 0 again.");
        queueItemDAO.makeNewQueueItemsForNewDay();

        String[] text = {
                "Ваш номер очереди",
                "0000",
                "TEST",
                "Дата: 00.00.0000 00:00:00",
                "Благодарим за то, что выбрали",
                "Global Medical Center"
        };
        drawer.createImageWithText(text);
        log.info("Printing test data.");
        printerService.printData();
    }
}
