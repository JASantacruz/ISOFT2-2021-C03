package Persistencia;

import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class AgenteTest {

    static Agente ag;
    public ExpectedException expectedException = ExpectedException.none();
    
    @BeforeClass
    public static void setUpBeforeClass() {
    	ag = new Agente();
    	try {
			ag.Insert("INSERT INTO Ingrediente (nombre, stock) VALUES ('test', 14)");
			ag.Insert("INSERT INTO Ingrediente (nombre, stock) VALUES ('test3', 14)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    @Test
    public void UpdateTest() {
        ag.Update("UPDATE Ingrediente SET stock=8 WHERE nombre = 'test3';");
    }
    
    @Test
    public void UpdateExceptionTest() {
    	expectedException.expect(SQLException.class);
        ag.Update("UPDATE Ingrediente SET stock=8 WHERE fail = 'Fanta';");
    }

    @Test
    public void ReadTest() {
        ag.Read("SELECT * FROM Ingrediente");
    }
    
    @Test
    public void ReadExceptionTest() {
    	expectedException.expect(SQLException.class);
        ag.Read("SELECT * FROM Ingrediente WHERE fail = 'Fanta';");
    }

    @Test
    public void CreateTest() throws SQLException {
    	ag.Insert("INSERT INTO Ingrediente (nombre, stock) VALUES ('test2', 14)");
    }
    
    @Test
    public void CreateExceptionTest() throws SQLException {
    	expectedException.expect(SQLException.class);
        ag.Insert("INSERT INTO tablaInexistente (nombre, codigo, stock) VALUES (2, 7, 14);");
    }

    @Test
    public void DeleteTest() throws SQLException {
        ag.Delete("DELETE FROM Ingrediente WHERE nombre='test';");
    }
    
    @Test
    public void DeleteExceptionTest() throws SQLException {
    	expectedException.expect(SQLException.class);
        ag.Delete("DELETE FROM tablaInexistente WHERE nombre='Nestea';");
    }
    
    @AfterClass
    public static void tearDownAfterClass() {
    	try {
			ag.Delete("DELETE FROM Ingrediente WHERE nombre='test2'");
			ag.Delete("DELETE FROM Ingrediente WHERE nombre='test3'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
}