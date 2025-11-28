package model;

public class Sesion {
    private static Sesion instancia;
    private Usuario usuario;

    private Sesion() { }

    public synchronized static Sesion getInstancia() {
        if (instancia == null) {
            instancia = new Sesion();
        }
        return instancia;
    }

    public void iniciarSesion(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void cerrarSesion() {
        this.usuario = null;
    }

    public boolean haySesionActiva() {
        return usuario != null;
    }
}
