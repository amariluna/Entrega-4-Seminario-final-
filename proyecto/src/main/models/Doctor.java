package models;

import java.util.Objects;

public class Doctor {
    private String name;
    private String specialty;

    // Constructor con validaciones
    public Doctor(String name, String specialty) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del doctor no puede estar vacío.");
        }
        if (specialty == null || specialty.trim().isEmpty()) {
            throw new IllegalArgumentException("La especialidad no puede estar vacía.");
        }
        this.name = name;
        this.specialty = specialty;
    }

    // Getters y setters con validaciones
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del doctor no puede estar vacío.");
        }
        this.name = name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        if (specialty == null || specialty.trim().isEmpty()) {
            throw new IllegalArgumentException("La especialidad no puede estar vacía.");
        }
        this.specialty = specialty;
    }

    // Método toString para representar al doctor
    @Override
    public String toString() {
        return "Dr(a). " + name + " - Especialidad: " + specialty;
    }

    // Método equals para comparar dos doctores
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return Objects.equals(name, doctor.name) && Objects.equals(specialty, doctor.specialty);
    }

    // Método hashCode para asegurar la consistencia en colecciones como HashSet o HashMap
    @Override
    public int hashCode() {
        return Objects.hash(name, specialty);
    }
}

