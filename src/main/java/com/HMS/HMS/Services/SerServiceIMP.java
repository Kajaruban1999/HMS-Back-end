package com.HMS.HMS.Services;

import com.HMS.HMS.Entities.Serviceimages;
import com.HMS.HMS.Entities.Services;
import com.HMS.HMS.Repositories.ServiceImageRepo;
import com.HMS.HMS.Repositories.ServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SerServiceIMP implements SerService {

    @Autowired
    private ServiceRepo serviceRepo;

    @Autowired
    private ServiceImageRepo serviceImageRepo;

    @Override
    @Transactional
    public Services saveService(Services services) {
        Services savedService = serviceRepo.save(services);

        if (services.getImages() != null && !services.getImages().isEmpty()) {
            for (Serviceimages image : services.getImages()) {
                image.setService(savedService);
                try {
                    serviceImageRepo.save(image);
                } catch (Exception e) {
                    System.out.println("Error saving image: " + e.getMessage());
                }
            }
        }

        return savedService;
    }

    @Override
    public Optional<Services> GetServiceById(Long id) {
        return serviceRepo.findById(id);
    }

    @Override
    public List<Services> GetAllService() {
        return serviceRepo.findAll();
    }

    @Override
    @Transactional
    public void DeleteService(Long id) {
        serviceRepo.deleteById(id);
    }

    @Transactional
    public Services updateService(Long id, Services updatedService) {
        Optional<Services> existingServiceOpt = serviceRepo.findById(id);
        if (existingServiceOpt.isPresent()) {
            Services service = existingServiceOpt.get();
            service.setName(updatedService.getName());
            service.setPrice(updatedService.getPrice());
            service.setDescription(updatedService.getDescription());
            if (updatedService.getImages() != null && !updatedService.getImages().isEmpty()) {
                service.getImages().clear();
                List<Serviceimages> newImages = updatedService.getImages();
                newImages.forEach(img -> {
                    img.setService(service);
                });
                service.getImages().addAll(newImages);
            }
            return serviceRepo.save(service);
        }
        return null;
    }
}