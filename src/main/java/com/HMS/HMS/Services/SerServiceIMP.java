package com.HMS.HMS.Services;

import com.HMS.HMS.Entities.Rooms;
import com.HMS.HMS.Entities.Roomsimages;
import com.HMS.HMS.Entities.Serviceimages;
import com.HMS.HMS.Entities.Services;
import com.HMS.HMS.Repositories.ServiceImageRepo;
import com.HMS.HMS.Repositories.ServiceRepo;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SerServiceIMP implements SerService {

    @Autowired
    private ServiceRepo serviceRepo;

    @Autowired
    private ServiceImageRepo serviceImageRepo;

    @Override
    public Services saveService(Services services) {
        Services savedService = serviceRepo.save(services);

        if (services.getImages() != null) {
            for (Serviceimages image : services.getImages()) {
                image.setPic(Base64.decodeBase64(new String(image.getPic())));
                image.setService(savedService);
                serviceImageRepo.save(image);
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
    public void DeleteService(Long id) {
        serviceRepo.deleteById(id);
    }
}
