package model;

import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Entrenamiento {

    private int ID;
    private String TIPO;
    private int SERIES;
    private ArrayList<String> Intensidad;

    public Entrenamiento(int ID, String TIPO, int SERIES, ArrayList<String> intensidad) {
        this.ID = ID;
        this.TIPO = TIPO;
        this.SERIES = SERIES;
        Intensidad = intensidad;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTIPO() {
        return TIPO;
    }

    public void setTIPO(String TIPO) {
        this.TIPO = TIPO;
    }

    public int getSERIES() {
        return SERIES;
    }

    public void setSERIES(int SERIES) {
        this.SERIES = SERIES;
    }

    public ArrayList<String> getIntensidad() {
        return Intensidad;
    }

    public void setIntensidad(ArrayList<String> intensidad) {
        Intensidad = intensidad;
    }


    public int ejecutar(String sql) throws SQLException { //vale para cualquier sentencia menos para el select porque devuelven un resetSet

        Connection connection = null;
        PreparedStatement stnmt = null;
        int datos = 0;

        try {
            connection = DBConnection.getConnection();
            stnmt = connection.prepareStatement(sql);
            datos = stnmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return datos;
    }
}
