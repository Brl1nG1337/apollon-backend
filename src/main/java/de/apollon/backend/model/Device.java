package de.apollon.backend.model;

import de.apollon.backend.model.enums.DeviceStatus;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "devices")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Device {

    @Id
    @Column(name = "mac_address", length = 17)
    private String id; // Format: "A1:B2:C3:D4:E5:F6"

    private String name; // z.B. "Wohnzimmer Node"
    private String ipAddress;
    private LocalDateTime lastSeen;

    @Enumerated(EnumType.STRING)
    private DeviceStatus status;

    // Ein ESP32 kann viele Sensoren/Aktoren haben
    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DeviceFeature> features;
}