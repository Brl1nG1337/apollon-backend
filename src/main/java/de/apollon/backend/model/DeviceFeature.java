package de.apollon.backend.model;

import de.apollon.backend.model.enums.FeatureType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "device_features")
@Getter // Wir nutzen explizit Getter/Setter statt nur @Data für mehr Kontrolle
@Setter
@NoArgsConstructor // Pflicht für JPA
@AllArgsConstructor // Hilfreich für Builder
@Builder // Macht das Erstellen von Testdaten viel einfacher
public class DeviceFeature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // --- Identifikation ---
    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FeatureType type;

    // --- Kommunikation ---
    // Wir machen diese Unique, damit wir sie effizient per Repository finden können
    @Column(unique = true)
    private String commandTopic;

    @Column(unique = true)
    private String stateTopic;

    // --- Status ---
    @Column(columnDefinition = "TEXT")
    private String lastPayload;

    private LocalDateTime lastUpdated;

    // --- Verknüpfung ---
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id", nullable = false)
    private Device device;
}