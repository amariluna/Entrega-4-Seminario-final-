package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Disponibilidad {
    private Medico medico;
    private LocalDateTime fechaHora;

    // Constructor
    public Disponibilidad(Medico medico, String fechaHora) {
        this.medico = medico;
        setFechaHora(fechaHora); // Convierte el String a LocalDateTime
    }

    // Getters y setters
    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        try {
            // Convertir String a LocalDateTime 
            this.fechaHora = LocalDateTime.parse(fechaHora, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (Exception e) {
            throw new IllegalArgumentException("Fecha y hora no válida. El formato debe ser 'yyyy-MM-dd HH:mm'.");
        }
    }

    @Override
    public String toString() {
        return "Disponibilidad: " + fechaHora.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + " con " + medico.getNombre();
    }
}
