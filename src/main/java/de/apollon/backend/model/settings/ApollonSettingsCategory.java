package de.apollon.backend.model.settings;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Entity
@Table(name = "apollon_settings_category")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApollonSettingsCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "icon_name")
    private String iconName;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id") // Erzeugt eine Fremdschlüsselspalte in apollon_setting
    private List<ApollonSetting> settings;
}