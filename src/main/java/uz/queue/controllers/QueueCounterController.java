package uz.queue.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.queue.db.dao.interfaces.QueueItemDAO;
import uz.queue.db.dto.QueueItemDTO;
import uz.queue.db.dto.ResponseDTO;

import java.util.UUID;

@RestController
@RequestMapping("/queue")
@CrossOrigin
public class QueueCounterController {

    private final QueueItemDAO queueItemDAO;

    public QueueCounterController(QueueItemDAO queueItemDAO) {
        this.queueItemDAO = queueItemDAO;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseDTO> queueNewPerson(@PathVariable UUID id) {
        QueueItemDTO dto = queueItemDAO.increaseCounterAndSave(id);

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(dto);
        responseDTO.setMessage("Yangi odam ro'yhatga qo'shildi.");
        responseDTO.setStatus(HttpStatus.OK);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}
