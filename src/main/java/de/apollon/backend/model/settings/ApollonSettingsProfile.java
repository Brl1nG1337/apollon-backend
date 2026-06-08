package de.apollon.backend.model.settings;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Entity
@Table(name = "apollon_settings_profile")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApollonSettingsProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name; // z.B. "Default"

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_id") // Erzeugt eine Fremdschlüsselspalte in apollon_settings_category
    private List<ApollonSettingsCategory> categories;
}