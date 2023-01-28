package com.iuran_bulanan_warga.Controllers;

import com.iuran_bulanan_warga.Services.HouseFeaturesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("api/houses")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Houses Features", description = "Additional features for managing the UI of houses data")
public class HouseFeaturesController {
  @Autowired
  HouseFeaturesService houseFeaturesService;

  @GetMapping("/views/{id}")
  @Operation(summary = "Show One House Data", description = "Showing owner name, city name, and province name of house")
  public ResponseEntity<?> showHouseByID(@PathParam("id") Integer houseId) {
    return houseFeaturesService.showHouseData(houseId);
  }

  @GetMapping("/views/page")
  @Operation(summary = "Show Houses Data", description = "Showing the data of houses with pagination")
  public ResponseEntity<?> showHousesWithPage(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "3") int size) {
    return houseFeaturesService.showHousesWithPage(page, size);
  }

  @PostMapping("/upload/source/{houseId}")
  @Operation(summary = "Upload Houses Picture Source", description = "Uploading houses Picture Source")
  public ResponseEntity<?> uploadHousesPictureSource(
      @RequestParam(value = "picture") MultipartFile picture, @PathVariable("houseId") Integer houseId) {
    return houseFeaturesService.uploadHousesPictureSource(houseId, picture);
  }

  @PostMapping("/upload/picture/{houseId}")
  @Operation(summary = "Upload Houses Picture ", description = "Uploading houses Picture Source")
  public ResponseEntity<?> uploadHousesPicture(
      @RequestParam(value = "picture") MultipartFile picture, @PathVariable("houseId") Integer houseId) {
    return houseFeaturesService.uploadHousesPicture(houseId, picture);
  }

  @GetMapping("/monthlyDues/views/page")
  @Operation(summary = "Show Houses Mothly Dues", description = "Showing Mothly Dues for Houses")
  public ResponseEntity<?> showHousesMothlyDues(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {
    return houseFeaturesService.showHousesMonthlyDues(page, size);
  }

  @GetMapping("/monthlyDues/detail/{houseId}")
  @Operation(summary = "Show House Mothly Dues", description = "Showing Mothly Dues for House")
  public ResponseEntity<?> showHousesMothlyDues(@PathVariable("houseId") Integer houseId) {
    return houseFeaturesService.showHousesDues(houseId);
  }

  @PostMapping("/{houseId}/addDues/{duesId}")
  @Operation(summary = "Add Dues To House", description = "Feature for adding dues to spesific house")
  public ResponseEntity<?> addDuesToHouse(@PathVariable("houseId") Integer houseId,
      @PathVariable("duesId") Integer duesId) {
    return houseFeaturesService.addDuesToHouseService(houseId, duesId);
  }
}
