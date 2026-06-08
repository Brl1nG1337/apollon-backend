package de.apollon.backend.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class DeviceDto {

    @NotBlank(message = "Gerätename darf nicht leer sein")
    @Size(max = 100, message = "Gerätename ist zu lang")
    private String name;

    @NotBlank(message = "MAC-Adresse darf nicht leer sein")
    @Pattern(
            regexp = "^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$",
            message = "Ungültiges MAC-Adressen-Format"
    )
    private String macAddress;
}