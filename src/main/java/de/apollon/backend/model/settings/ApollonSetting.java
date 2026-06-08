package de.apollon.backend.model.settings;

import de.apollon.backend.model.enums.ApollonSettingType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "apollon_setting")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApollonSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "setting_key", nullable = false)
    private String key;

    @Column(nullable = false)
    private String label;

    // Wir speichern den Wert als String. Das Flutter-Model castet ihn dann passend.
    @Column(name = "setting_value")
    private String value;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApollonSettingType type;
}