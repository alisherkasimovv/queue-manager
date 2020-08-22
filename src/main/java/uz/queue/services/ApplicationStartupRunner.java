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

    public ApplicationStartupRunner(QueueItemDAO queueItemDAO, PrinterService printerService) {
        this.queueItemDAO = queueItemDAO;
        this.printerService = printerService;
    }

    @Override
    public void run(String... args) throws Exception {
        log.warn("All previous order lists were reset. Now, counter starts at 0 again.");
        queueItemDAO.makeNewQueueItemsForNewDay();
        checkPrinterConnection();
        printTestData();
    }

    private void checkPrinterConnection() {
        log.info(String.valueOf(printerService.getPrinters()));
    }

    private void printTestData() {
        // Print text showing that printer is connected
        printerService.printString("XP-80", "\n\n Printer programma bilan bog'landi. \n\n");

        // Cut paper
        byte[] cutP = new byte[] { 0x1d, 'V', 1 };
        printerService.printBytes("XP-80", cutP);
    }
}
