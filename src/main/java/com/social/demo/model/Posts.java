package com.social.demo.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Posts {
    private int id;
    private String texto;
    private int n_curtidas;
    private int id_usuario;
    private int n_comentarios;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTexto() {
        return texto;
    }
    public void setTexto(String texto) {
        this.texto = texto;
    }
    public int getN_curtidas() {
        return n_curtidas;
    }
    public void setN_curtidas(int n_curtidas) {
        this.n_curtidas = n_curtidas;
    }
    public int getId_usuario() {
        return id_usuario;
    }
    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    public int getN_comentarios() {
        return n_comentarios;
    }
    public void setN_comentarios(int n_comentarios) {
        this.n_comentarios = n_comentarios;
    }

    public void salvar(){
        Conexao c = new Conexao();
        Connection dbConn = c.getConexao();

        String sql = "INSERT INTO posts (texto, n_curtidas, id_usuario, n_comentarios) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement  ps = dbConn.prepareStatement(sql);
            ps.setString(1, this.texto);
            ps.setInt(2, this.n_curtidas);
            ps.setInt(3, this.id_usuario);
            ps.setInt(4, this.n_comentarios);
            ps.executeUpdate();

            
            ResultSet rs =ps.getGeneratedKeys();
            if(rs.next()) {
                this.id = rs.getInt(1);
            }
        }
        catch (SQLException e) {
            System.out.println("Erro ao salvar post: " + e.getMessage());
            e.printStackTrace();
        }
    } //FIM DO SALVAR
    public static ArrayList<Posts> getAll() {
        ArrayList<Posts> lista = new ArrayList<Posts>();
        Conexao c = new Conexao();
        Connection dbConn = c.getConexao();
        String sql = "SELECT * FROM posts";
        try {
            Statement stmt = dbConn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                Posts post = new Posts();
                post.setId(rs.getInt("id"));
                post.setTexto(rs.getString("texto"));
                post.setN_curtidas(rs.getInt("n_curtidas"));
                post.setId_usuario(rs.getInt("id_usuario"));
                post.setN_comentarios(rs.getInt("n_comentarios"));
                lista.add(post);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar posts: " + e.getMessage());
            e.printStackTrace();    
        } return lista;
    }

    public void load() {
        Conexao c = new Conexao();
        Connection dbConn = c.getConexao();
        String sql = "SELECT * FROM posts WHERE id = ?";
        try {
            PreparedStatement ps = dbConn.prepareStatement(sql);
            ps.setInt(1, this.id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                this.texto = rs.getString("texto");
                this.n_curtidas = rs.getInt("n_curtidas");
                this.id_usuario = rs.getInt("id_usuario");
                this.n_comentarios = rs.getInt("n_comentarios");
                }          
            }catch (SQLException e) {
                System.out.println("Erro ao carregar post: " + e.getMessage());
                e.printStackTrace();
            }
    }
    
} //fim da classe