package de.apollon.backend.controller;

import de.apollon.backend.model.devices.ApollonDevice;
import de.apollon.backend.model.dto.DeviceDto;
import de.apollon.backend.model.enums.ApollonDeviceStatus;
import de.apollon.backend.repository.DeviceRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/devices")
@CrossOrigin(origins = "*")
public class DeviceController {

    @Autowired
    private DeviceRepository deviceRepository;

    @PostMapping
    public ResponseEntity<ApollonDevice> createDevice(@Valid @RequestBody DeviceDto dto) {
        // Wir nutzen deinen @Builder aus der Device-Klasse
        ApollonDevice device = ApollonDevice.builder()
                .id(dto.getMacAddress()) // Die MAC-Adresse wird als ID gesetzt (mac_address in der DB)
                .name(dto.getName())
                .status(ApollonDeviceStatus.ONLINE) // Standard-Status beim Hinzufügen
                .lastSeen(LocalDateTime.now())
                .build();

        ApollonDevice savedDevice = deviceRepository.save(device);
        return new ResponseEntity<>(savedDevice, HttpStatus.CREATED);
    }
}