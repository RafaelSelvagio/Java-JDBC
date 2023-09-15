package dao;

import model.Pais;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PaisDAO {
	private final String url = "jdbc:mysql://localhost:3306/PrimeiroBanco";
    private final String usuario = "root";
    private final String senha = "aluno";
    private Connection connection;
    
    /*
     * Método que adiciona um país, recebido por parâmetro, a listaDePaises
     */
    public void adicionarPais(Pais pais) {
        try (Connection connection = DriverManager.getConnection(url, usuario, senha)) {
            String sql = "INSERT INTO pais (nome, capital) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, pais.getNome());
            preparedStatement.setString(2, pais.getCapital());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * Método que retorna a lista de países
     */
    public List<Pais> obterListaDePaisess() {
    	List<Pais> listaDePaises = new ArrayList<>();
    	
        try (Connection connection = DriverManager.getConnection(url, usuario, senha)) {
        	
            String sql = "SELECT * FROM pais";
            
            Statement statement = connection.prepareStatement(sql);
            
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String capital = resultSet.getString("capital");
                
                Pais pais = new Pais(nome, capital);
                
                listaDePaises.add(pais);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return listaDePaises;
    }
    
    public List<Pais> obterListaDePaises() {
        List<Pais> listaDePaises = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, usuario, senha)) {
        	
            String sql = "SELECT * FROM pais";
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String capital = resultSet.getString("capital");
                
                Pais pais = new Pais(nome, capital);
                
                listaDePaises.add(pais);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaDePaises;
    }


    /*
     * Método que remove um pais, recebido por parâmetro, da listaDePaises
     */
    public void removerPais(Pais pais) {
    	try (Connection connection = DriverManager.getConnection(url, usuario, senha)) {
            String sql = "DELETE FROM pais WHERE nome = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, pais.getNome());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * Método que limpa a lista de países
     */
    public void limparListaDePaises() {
    	try (Connection connection = DriverManager.getConnection(url, usuario, senha)) {
            String sql = "TRUNCATE TABLE pais";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * Método que obtem e retorna o primeiro país da lista
     */
    public Pais obterPrimeiroPais() {
    	try (Connection connection = DriverManager.getConnection(url, usuario, senha)) {
            String sql = "SELECT * FROM pais LIMIT 1;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String capital = resultSet.getString("capital");
                return new Pais(nome, capital);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
     * Os métodos obterTerceiroPais e obterQuartoPais são semelhantes  ao  método
     * obterPrimeiroPais.
     */
    public Pais obterTerceiroPais() {
    	try (Connection connection = DriverManager.getConnection(url, usuario, senha)) {
            String sql = "SELECT * FROM pais LIMIT 2, 1;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String capital = resultSet.getString("capital");
                return new Pais(nome, capital);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Pais obterQuartoPais() {
        if (abreConexao()) {
            try {
                String sql = "SELECT * FROM pais LIMIT 3, 1;";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    String nome = resultSet.getString("nome");
                    String capital = resultSet.getString("capital");
                    return new Pais(nome, capital);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                fechaConexao(); // Certifique-se de fechar a conexão quando terminar
            }
        }
        return null;
    }
    



    
    
    
    
    public boolean testaConexao() {
    	boolean conectou = abreConexao();
    	
    	fechaConexao();
    	
    	return conectou;
    }
    
    public boolean abreConexao() {
    	try {
            // Carrega o driver JDBC específico (substitua pelo driver do seu banco de dados)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Estabelece a conexão com o banco de dados
            connection = DriverManager.getConnection(url, usuario, senha);

            if (connection != null) {
                return true;
            } else {
                return false;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public void fechaConexao() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
