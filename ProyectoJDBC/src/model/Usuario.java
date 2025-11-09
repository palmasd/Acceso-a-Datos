package model;

public class Usuario{
    private final long id;
    private String nombre;
    private int edad;


    public Usuario(String nombre, int edad) {
        this.id = 1L;
        this.nombre = nombre;
        this.edad = edad;
    }

    public long getId(){
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                '}';
    }


    public void generarInsertar(Usuario item){
        String sql = "";
        StringBuilder sqlObject = null;

        try{
            sql = "INSERT INTO usuarios (ID, NOMBRE, EDAD) VALUES (";
            sqlObject = new StringBuilder();

            sqlObject.append(id);
            sqlObject.append(", ");
            sqlObject.append(nombre);
            sqlObject.append(", ");
            sqlObject.append(edad);
            sqlObject.append(");");

            sql+= sqlObject.toString();

        }catch (NullPointerException e) {
            System.err.println(e.getMessage());
        }
        System.out.println(sql);
    }
}
