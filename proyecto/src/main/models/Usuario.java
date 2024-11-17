package models;

import java.util.HashMap;
import java.util.Map;

public class Usuario {
    private int id;
    private String nombre;
    private String email;
    private String contraseña;
    private TipoUsuario tipoUsuario; // Usamos un enum para definir los tipos de usuario

    // Almacenamiento de usuarios para validación (puede ser un almacenamiento temporal para ejemplo)
    private static Map<String, Usuario> usuariosRegistrados = new HashMap<>();

    // Enum para los tipos de usuario
    public enum TipoUsuario {
        PACIENTE,
        MEDICO,
        ADMINISTRADOR
    }

    // Constructor, getters y setters
    public Usuario(int id, String nombre, String email, String contraseña, TipoUsuario tipoUsuario) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.contraseña = contraseña;
        this.tipoUsuario = tipoUsuario;
    }


    // Método para registrar un nuevo usuario
    public static void registrarUsuario(Usuario usuario) {
        usuariosRegistrados.put(usuario.getEmail(), usuario); // Guardamos al usuario con su email como clave
        System.out.println("Usuario registrado exitosamente.");
    }

    // Método para iniciar sesión, validando el correo y la contraseña
    public boolean iniciarSesion(String email, String contraseña) {
        Usuario usuario = usuariosRegistrados.get(email);
        if (usuario != null && usuario.getContraseña().equals(contraseña)) {
            System.out.println("Inicio de sesión exitoso para: " + usuario.getNombre());
            return true;
        }
        System.out.println("Error: Credenciales incorrectas.");
        return false;
    }

    // Método para cerrar sesión
    public void cerrarSesion() {
        System.out.println("Sesión cerrada para: " + this.nombre);
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    // Métodos de tipo de usuario
    public boolean esAdministrador() {
        return TipoUsuario.ADMINISTRADOR.equals(this.tipoUsuario);
    }

    public boolean esMedico() {
        return TipoUsuario.MEDICO.equals(this.tipoUsuario);
    }

    public boolean esPaciente() {
        return TipoUsuario.PACIENTE.equals(this.tipoUsuario);
    }

    // Método para cambiar la contraseña
    public void cambiarContraseña(String nuevaContraseña) {
        this.contraseña = nuevaContraseña;
        System.out.println("Contraseña cambiada exitosamente.");
    }

    // Método de validación de contraseña
    public boolean validarContraseña(String contraseña) {
        return this.contraseña.equals(contraseña);
    }
}

