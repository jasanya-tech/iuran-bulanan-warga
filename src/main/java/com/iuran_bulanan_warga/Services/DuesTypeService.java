package com.iuran_bulanan_warga.Services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.iuran_bulanan_warga.Helpers.DTO.Requests.DuesTypeRequest;
import com.iuran_bulanan_warga.Helpers.DTO.Responses.MessageResponse;
import com.iuran_bulanan_warga.Models.Entities.DuesType;
import com.iuran_bulanan_warga.Models.Repositories.DuesTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DuesTypeService {
  @Autowired
  DuesTypeRepository duesTypeRepository;

  public ResponseEntity<?> serviceGetAll() {
    try {
      List<DuesType> duesType = duesTypeRepository.findAll();
      if (duesType.isEmpty()) {
        throw new NoSuchElementException("No dues type found!");
      }
      return ResponseEntity.ok().body(duesType);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
    }
  }

  public ResponseEntity<?> serviceGetById(Integer id) {
    try {
      Optional<DuesType> duesType = duesTypeRepository.findById(id);
      if (duesType.isPresent()) {
        return ResponseEntity.ok().body(duesType.get());
      } else {
        throw new NoSuchElementException("No dues type found!");
      }
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
    }
  }

  public ResponseEntity<?> serviceCreate(DuesTypeRequest duesTypeRequest) {
    try {
      DuesType duesType = new DuesType(
          duesTypeRequest.getDuesName(),
          duesTypeRequest.getCost());
      duesTypeRepository.save(duesType);
      return ResponseEntity.ok().body(duesType);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
    }
  }

  public ResponseEntity<?> serviceUpdate(Integer id, DuesTypeRequest duesTypeRequest) {
    try {
      Optional<DuesType> duesType = duesTypeRepository.findById(id);
      if (!duesType.isPresent()) {
        throw new NoSuchElementException("Dues type" + id + "doesn't exist!");
      }
      DuesType duesTypeData = duesType.get();
      duesTypeData.setDuesName(duesTypeRequest.getDuesName());
      duesTypeData.setCost(duesTypeRequest.getCost());
      duesTypeRepository.save(duesTypeData);
      return ResponseEntity.ok().body(duesType);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
    }
  }

  public ResponseEntity<?> serviceDeleteById(Integer id) {
    try {
      Optional<DuesType> duesType = duesTypeRepository.findById(id);
      if (!duesType.isPresent()) {
        throw new NoSuchElementException("Dues type " + id + " doesn't exist!");
      }
      duesTypeRepository.deleteById(id);
      return ResponseEntity.ok().body(new MessageResponse("Dues type " + id + " has been deleted"));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
    }
  }

  public ResponseEntity<?> serviceDeleteAll() {
    try {
      duesTypeRepository.deleteAll();
      return ResponseEntity.ok().body(new MessageResponse("All dues type has been deleted"));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
    }
  }
}
