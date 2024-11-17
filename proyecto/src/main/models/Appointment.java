package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Appointment implements Comparable<Appointment> {
    private String doctor;
    private LocalDateTime datetime;

    // Constructor
    public Appointment(String doctor, String datetime) {
        this.doctor = doctor;
        setDatetime(datetime); 
    }

    // Getter para 'doctor'
    public String getDoctor() {
        return doctor;
    }

    // Setter para 'doctor'
    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    // Getter para 'datetime'
    public LocalDateTime getDatetime() {
        return datetime;
    }

    // Setter para 'datetime' que convierte el String a LocalDateTime
    public void setDatetime(String datetime) {
        try {
            this.datetime = LocalDateTime.parse(datetime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (Exception e) {
            throw new IllegalArgumentException("Fecha y hora no válida. El formato debe ser 'yyyy-MM-dd HH:mm'.");
        }
    }

    // Método toString para representar un turno
    @Override
    public String toString() {
        return "Cita con Dr(a). " + doctor + " el " + datetime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    // Método equals para comparar dos turnos
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return Objects.equals(doctor, that.doctor) && Objects.equals(datetime, that.datetime);
    }

    // Método hashCode para asegurar la consistencia en colecciones como HashSet o HashMap
    @Override
    public int hashCode() {
        return Objects.hash(doctor, datetime);
    }

    // Implementación del método compareTo para ordenar los turnos por fecha
    @Override
    public int compareTo(Appointment o) {
        return this.datetime.compareTo(o.datetime); // Compara las fechas
    }
}


