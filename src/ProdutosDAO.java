

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class ProdutosDAO {
    
    private Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        
        
        conn = new conectaDAO().connectDB();
        
        String sql = "INSERT INTO produtos(nome, valor, status) VALUES " + "(?, ?, ?)";
        
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setInt(2, produto.getValor());
            stmt.setString(3, produto.getStatus());
            
            stmt.execute();

        }
        
        catch (Exception e) {
            System.out.println("Erro ao cadastrar produto: " + e.getMessage());
        }
        
        
    }
    
    public List<ProdutosDTO> listarProdutos(){
        
        conn = new conectaDAO().connectDB();
        
        String sql = "SELECT * FROM produtos";

        try {
            
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();            

            List<ProdutosDTO> listaProdutos = new ArrayList<>();

            while (rs.next()) { 
                ProdutosDTO produtos = new ProdutosDTO ();

                produtos.setId(rs.getInt("id"));
                produtos.setNome(rs.getString("nome"));
                produtos.setValor(rs.getInt("valor"));
                produtos.setStatus(rs.getString("status"));
                     
                listaProdutos.add(produtos);    
            }
            return listaProdutos;
  
        }
        catch (Exception e) {
            return null;
        }
     
    }
    
    
    
        
}

