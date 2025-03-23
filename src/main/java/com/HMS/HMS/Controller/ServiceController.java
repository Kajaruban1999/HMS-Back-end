package com.HMS.HMS.Controller;

import com.HMS.HMS.Entities.Serviceimages;
import com.HMS.HMS.Entities.Services;
import com.HMS.HMS.Services.SerServiceIMP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/services")
public class ServiceController {

    private SerServiceIMP serService;

    @Autowired
    public ServiceController(SerServiceIMP serviceService) {
        this.serService = serviceService;
    }

    @PostMapping(value = "/create", consumes = {"multipart/form-data"})
    @Transactional
    public ResponseEntity<String> createService(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("price") int price,
            @RequestParam(value = "image", required = false) MultipartFile image) {
        try {
            Services service = new Services();
            service.setName(name);
            service.setDescription(description);
            service.setPrice(price);

            List<Serviceimages> images = new ArrayList<>();
            if (image != null && !image.isEmpty()) {
                Serviceimages serviceImage = new Serviceimages();
                // Convert image to Base64
                String base64Image = Base64.getEncoder().encodeToString(image.getBytes());
                serviceImage.setPic(base64Image);
                serviceImage.setService(service);
                images.add(serviceImage);
            }
            service.setImages(images);

            serService.saveService(service);
            return ResponseEntity.ok("Service created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error creating service: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Services>> getServiceById(@PathVariable Long id) {
        return ResponseEntity.ok(serService.GetServiceById(id));
    }

    @GetMapping
    public ResponseEntity<List<Services>> getAllservice() {
        return ResponseEntity.ok(serService.GetAllService());
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> deleteService(@PathVariable Long id) {
        try {
            serService.DeleteService(id);
            return ResponseEntity.ok("Delete successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting service: " + e.getMessage());
        }
    }

    @PutMapping(value = "/update/{id}", consumes = {"multipart/form-data"})
    @Transactional
    public ResponseEntity<Services> updateService(
            @PathVariable Long id,
            @RequestParam("name") String name,
            @RequestParam("price") int price,
            @RequestParam("description") String description,
            @RequestParam(value = "image", required = false) MultipartFile image) throws IOException {
        Services updatedService = new Services();
        updatedService.setName(name);
        updatedService.setPrice(price);
        updatedService.setDescription(description);

        if (image != null && !image.isEmpty()) {
            Serviceimages serviceImage = new Serviceimages();
            String base64Image = Base64.getEncoder().encodeToString(image.getBytes());
            serviceImage.setPic(base64Image);
            updatedService.setImages(List.of(serviceImage));
        }

        Services service = serService.updateService(id, updatedService);
        if (service != null) {
            return ResponseEntity.ok(service);
        }
        return ResponseEntity.notFound().build();
    }
}