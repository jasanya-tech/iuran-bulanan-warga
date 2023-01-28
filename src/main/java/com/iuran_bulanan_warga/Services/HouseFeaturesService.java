package com.iuran_bulanan_warga.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.iuran_bulanan_warga.Helpers.DTO.Responses.AddDuesHouseResponse;
import com.iuran_bulanan_warga.Helpers.DTO.Responses.HouseResponse;
import com.iuran_bulanan_warga.Helpers.DTO.Responses.MessageResponse;
import com.iuran_bulanan_warga.Helpers.DTO.Responses.MonthlyDuesDetailResponse;
import com.iuran_bulanan_warga.Helpers.DTO.Responses.MonthlyDuesResponse;
import com.iuran_bulanan_warga.Helpers.Entities.TypePicture;
import com.iuran_bulanan_warga.Helpers.utils.ImageUtils;
import com.iuran_bulanan_warga.Models.Entities.DuesType;
import com.iuran_bulanan_warga.Models.Entities.Houses;
import com.iuran_bulanan_warga.Models.Entities.ImageHouses;
import com.iuran_bulanan_warga.Models.Repositories.DuesTypeRepository;
import com.iuran_bulanan_warga.Models.Repositories.HouseRepository;
import com.iuran_bulanan_warga.Models.Repositories.ImageHouseRepository;
import com.iuran_bulanan_warga.Models.Repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class HouseFeaturesService {
  @Autowired
  HouseRepository houseRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  DuesTypeRepository duesTypeRepository;

  @Autowired
  ImageHouseRepository imageHouseRepository;

  public ResponseEntity<?> showHouseData(Integer houseId) {
    try {
      Optional<Houses> house = houseRepository.findById(houseId);
      HouseResponse houseResponse = new HouseResponse();
      String address = house.get().getStreet() + " NO." + house.get().getHouseNumber() + " RT " + house.get().getRt()
          + " / RW " + house.get().getRw();
      houseResponse.setAddress(address);
      houseResponse.setOwnerName(house.get().getOwner().getFullName());
      houseResponse.setCityName(house.get().getCity().getCityName());
      houseResponse.setProvinceName(house.get().getCity().getProvince().getProvinceName());
      return ResponseEntity.ok().body(houseResponse);
    } catch (Exception e) {
      return ResponseEntity.ok().body(new MessageResponse(e.getMessage()));
    }
  }

  public ResponseEntity<?> showHousesWithPage(int page, int size) {
    try {
      List<Houses> houses = new ArrayList<Houses>();

      Pageable paging = PageRequest.of(page, size);
      Page<Houses> pageHouses = houseRepository.findAll(paging);
      houses = pageHouses.getContent();

      Map<String, Object> res = new HashMap<>();
      res.put("data", houses);
      res.put("size", size);
      res.put("currentPage", paging.getPageNumber());
      res.put("totalItems", pageHouses.getTotalElements());
      res.put("totalPage", pageHouses.getTotalPages());
      return ResponseEntity.ok().body(res);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  // Upload picture source
  public ResponseEntity<?> uploadHousesPictureSource(Integer houseId, MultipartFile picture) {
    try {
      byte[] compresImage = ImageUtils.compressImage(picture.getBytes());
      Houses house = houseRepository.findById(houseId).get();
      ImageHouses imageHouse = new ImageHouses();
      imageHouse.setFileName(picture.getOriginalFilename());
      imageHouse.setMimeType(picture.getContentType());
      imageHouse.setTypePicture(TypePicture.source);
      imageHouse.setSource(compresImage);
      imageHouse.setPath("/loadPicture/source/" + picture.getOriginalFilename());
      imageHouseRepository.save(imageHouse);
      house.getPictures().add(imageHouse);
      houseRepository.save(house);
      return ResponseEntity.ok().body(house.getPictures());
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  // Upload picture to local API
  public ResponseEntity<?> uploadHousesPicture(Integer houseId, MultipartFile picture) {
    try {
      String fileName = StringUtils.cleanPath(picture.getOriginalFilename());
      Houses house = houseRepository.findById(houseId).get();
      ImageHouses imageHouse = new ImageHouses();
      imageHouse.setFileName(fileName);
      imageHouse.setMimeType(picture.getContentType());
      imageHouse.setTypePicture(TypePicture.path);
      String fileCode = ImageUtils.saveFile(fileName, picture);
      imageHouse.setPath("/loadPicture/" + fileCode);
      imageHouseRepository.save(imageHouse);
      house.getPictures().add(imageHouse);
      houseRepository.save(house);
      return ResponseEntity.ok().body(house.getPictures());
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  public ResponseEntity<?> showHousesMonthlyDues(int page, int size) {
    try {
      List<Houses> houses = new ArrayList<Houses>();
      Pageable paging = PageRequest.of(page, size);
      Page<Houses> pageHouses = houseRepository.findAll(paging);
      houses = pageHouses.getContent();
      List<MonthlyDuesResponse> listMonthlyDuesResponses = new ArrayList<MonthlyDuesResponse>();
      houses.forEach(house -> {
        String owner = house.getOwner() != null ? house.getOwner().getFullName() : "tidak di ketahui";
        MonthlyDuesResponse monthlyDuesResponse = new MonthlyDuesResponse();
        String address = house.getStreet() != null
            ? house.getStreet() + " NO." + house.getHouseNumber() + " RT " + house.getRt()
                + " / RW " + house.getRw()
            : "belum di setting";
        monthlyDuesResponse.setId(house.getId());
        monthlyDuesResponse.setHouseName(house.getHouseName());
        monthlyDuesResponse.setOwner(owner);
        monthlyDuesResponse.setAddress(address);
        house.getMonthlyDues().forEach(duesType -> {
          monthlyDuesResponse.setTotalCost(monthlyDuesResponse.getTotalCost() + Integer.parseInt(duesType.getCost()));
        });
        listMonthlyDuesResponses.add(monthlyDuesResponse);
      });
      Map<String, Object> res = new HashMap<>();
      res.put("data", listMonthlyDuesResponses);
      res.put("size", size);
      res.put("currentPage", paging.getPageNumber());
      res.put("totalItems", pageHouses.getTotalElements());
      res.put("totalPage", pageHouses.getTotalPages());
      return ResponseEntity.ok().body(res);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  public ResponseEntity<?> showHousesDues(Integer houseId) {
    try {
      Houses house = houseRepository.findById(houseId).get();
      String address = house.getStreet() != null
          ? house.getStreet() + " NO." + house.getHouseNumber() + " RT " + house.getRt()
              + " / RW " + house.getRw()
          : "belum di setting";
      String owner = house.getOwner() != null ? house.getOwner().getFullName() : "tidak di ketahui";
      MonthlyDuesDetailResponse monthlyDuesDetailResponse = new MonthlyDuesDetailResponse();
      monthlyDuesDetailResponse.setId(house.getId());
      monthlyDuesDetailResponse.setPictures(house.getPictures());
      monthlyDuesDetailResponse.setHouseName(house.getHouseName());
      monthlyDuesDetailResponse.setOwner(owner);
      monthlyDuesDetailResponse.setAddress(address);
      monthlyDuesDetailResponse
          .setCityName(house.getCity() != null ? house.getCity().getCityName() : "belum di setting");
      monthlyDuesDetailResponse
          .setProvinceName(
              house.getCity() != null ? house.getCity().getProvince().getProvinceName() : "belum di setting");
      monthlyDuesDetailResponse.setDuesTypes(house.getMonthlyDues());

      return ResponseEntity.ok().body(monthlyDuesDetailResponse);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  public ResponseEntity<?> addDuesToHouseService(Integer houseId, Integer duesId) {
    try {
      Houses house = houseRepository.findById(houseId).get();
      DuesType duesType = duesTypeRepository.findById(duesId).get();
      AddDuesHouseResponse addDuesHouseResponse = new AddDuesHouseResponse();
      house.getMonthlyDues().add(duesType);
      houseRepository.save(house);
      addDuesHouseResponse.setDuesTypes(house.getMonthlyDues());
      house.getMonthlyDues().forEach(dues -> {
        addDuesHouseResponse.setTotalCost(addDuesHouseResponse.getTotalCost() + Integer.parseInt(dues.getCost()));
      });
      return ResponseEntity.ok().body(addDuesHouseResponse);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
