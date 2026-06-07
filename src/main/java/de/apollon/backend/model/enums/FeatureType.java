package de.apollon.backend.model.enums;

public enum FeatureType {
    SWITCH, // Ein- / Ausschalter (z.B. Relais)
    LAMP,   // Komplexe Steuerung (Dimmen, Farbe)
    SENSOR, // Nur Werte liefern (Temp, Feuchtigkeit, etc.)
    ACTOR   // Generische Aktoren (z.B. Motor, Ventil)
}