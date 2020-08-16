package uz.queue.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.queue.db.dao.interfaces.DoctorDAO;
import uz.queue.db.dto.DoctorDTO;
import uz.queue.db.dto.DoctorListDTO;
import uz.queue.db.dto.ResponseDTO;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@RestController
@RequestMapping("/doctor")
@CrossOrigin
public class DoctorController {

    private final DoctorDAO dao;
    private ResponseDTO dto;

    public DoctorController(DoctorDAO dao) {
        this.dao = dao;
    }

    @GetMapping(value = "/get")
    public ResponseEntity<ResponseDTO> getAll() {
        dto = new ResponseDTO();
        DoctorListDTO list = dao.getAll();

        if (list.getDoctors().size() == 0) {
            dto.setData(list);
            dto.setMessage("Doktorlar ro'yhati hozircha bo'sh. Iltimos, bazani doktorlar ro'yhati bilan to'ldiring.");
            dto.setStatus(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
        }

        dto.setData(list);
        dto.setMessage("Doktorlar ro'yhati.");
        dto.setStatus(HttpStatus.OK);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<ResponseDTO> getAll(@PathVariable UUID id) {
        dto = new ResponseDTO();
        try {
            dto.setData(dao.get(id));
            dto.setMessage("Doktor bazadan topildi.");
            dto.setStatus(HttpStatus.FOUND);
        } catch (EntityNotFoundException e) {
            dto.setData(null);
            dto.setMessage("Tanlangan doktor bazadan topilmadi.");
            dto.setStatus(HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(dto, HttpStatus.FOUND);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<ResponseDTO> save(@RequestBody DoctorDTO doc) {
        dto = new ResponseDTO();
        DoctorDTO docDTO = dao.save(doc);
        dto.setData(docDTO);
        dto.setMessage("Yangi doktor ma'lumotlar bazasiga qo'shildi");
        dto.setStatus(HttpStatus.CREATED);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping(value = "/save")
    public ResponseEntity<ResponseDTO> update(@RequestBody DoctorDTO doc) {
        dto = new ResponseDTO();
        DoctorDTO docDTO = dao.save(doc);
        dto.setData(docDTO);
        dto.setMessage("Tanlangan doktor ma'lumotlari yangilandi");
        dto.setStatus(HttpStatus.CREATED);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
