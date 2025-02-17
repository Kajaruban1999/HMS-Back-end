package com.HMS.HMS.Controller;

import com.HMS.HMS.Entities.Rooms;
import com.HMS.HMS.Entities.Services;
import com.HMS.HMS.Services.SerServiceIMP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/services")
public class ServiceController {

    private SerServiceIMP serService;

    @Autowired
    public ServiceController (SerServiceIMP serviceService){
        this.serService = serviceService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createService(@RequestBody Services services){
        try{
            serService.saveService(services);
        }
        catch (RuntimeException r){
            return ResponseEntity.ok((r.getMessage()+ "Something went wrong"));
        }
        return ResponseEntity.ok(("Ok"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Services>>getServiceById(@PathVariable Long id){
        return ResponseEntity.ok(serService.GetServiceById(id));
    }

    @GetMapping
    public ResponseEntity<List<Services>>getAllservice(){
        return ResponseEntity.ok(serService.GetAllService());
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<String>deleteService(@PathVariable Long id){
        serService.DeleteService(id);
        return ResponseEntity.ok("Delete successfully");
    }
}
