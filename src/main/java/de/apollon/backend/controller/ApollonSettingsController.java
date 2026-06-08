package de.apollon.backend.controller;

import de.apollon.backend.model.settings.ApollonSetting;
import de.apollon.backend.model.settings.ApollonSettingsProfile;
import de.apollon.backend.repository.ApollonSettingRepository;
import de.apollon.backend.repository.ApollonSettingsProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/settings")
@RequiredArgsConstructor // Generiert den Konstruktor für die Repositories (Constructor Injection)
@CrossOrigin(origins = "*") // Erlaubt der Flutter-App den Zugriff von außen ohne CORS-Probleme
public class ApollonSettingsController {

    private final ApollonSettingsProfileRepository profileRepository;
    private final ApollonSettingRepository settingRepository;

    /**
     * GET http://localhost:8080/api/settings/profiles/Default
     */
    @GetMapping("/profiles/{profileName}")
    public ResponseEntity<ApollonSettingsProfile> getProfile(@PathVariable String profileName) {
        return profileRepository.findByName(profileName)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * PUT http://localhost:8080/api/settings/profiles/Default/settings/mqtt_host
     */
    @PutMapping("/profiles/{profileName}/settings/{settingKey}")
    public ResponseEntity<Void> updateSetting(
            @PathVariable String profileName,
            @PathVariable String settingKey,
            @RequestBody ApollonSetting updatedSetting) {

        return settingRepository.findByProfileNameAndSettingKey(profileName, settingKey)
                .map(setting -> {
                    // Wir aktualisieren nur den reinen Wert.
                    // Label, Key und Typ bleiben aus Konsistenzgründen fest.
                    setting.setValue(updatedSetting.getValue());
                    settingRepository.save(setting);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}