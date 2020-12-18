package Persistencia;

import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Test;

public class AgenteTest {


    Agente ag = new Agente();
    @Test
    public void UpdateTest() {
        ag.Update("UPDATE Bebida SET stock=8 WHERE nombre='Fanta';");
    }

    @Test
    public void ReadTest() {
        ag.Read("SELECT * FROM Bebida");
    }

    @Test
    public void CreateTest() throws SQLException {
        ag.Insert("INSERT INTO Bebida (nombre, codigo, stock) VALUES ('Nestea', 7, 14);");
    }

    @Test
    public void DeleteTest() throws SQLException {
        ag.Delete("DELETE FROM Bebida WHERE nombre='Nestea';");
    }
}