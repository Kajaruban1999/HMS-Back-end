package com.HMS.HMS.Services;

import com.HMS.HMS.Entities.Rooms;
import com.HMS.HMS.Entities.Services;

import java.util.List;
import java.util.Optional;

public interface SerService {
    Services saveService (Services services);
    Optional<Services> GetServiceById(Long id);
    List<Services> GetAllService();
    void DeleteService(Long id);
}
