/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.atividade10.classes;
import java.time.LocalDateTime;
/**
 *
 * @author jotin
 */
public class Usuario {
    private long id;
    private PerfilUsuario perfil;
    private String nomeUsuario;
    private String email;
    private String senha;
    private LocalDateTime ultimoLogin; 
    private Boolean ativo;

    public Usuario(long id, PerfilUsuario perfil, String nomeUsuario, String email, String senha, LocalDateTime ultimoLogin, Boolean ativo) {
        this.id = id;
        this.perfil = perfil;
        this.nomeUsuario = nomeUsuario;
        this.email = email;
        this.senha = senha;
        this.ultimoLogin = ultimoLogin;
        this.ativo = ativo;
    }

    public long getId() {
        return id;
    }

    public PerfilUsuario getPerfil() {
        return perfil;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public LocalDateTime getUltimoLogin() {
        return ultimoLogin;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPerfil(PerfilUsuario perfil) {
        this.perfil = perfil;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setUltimoLogin(LocalDateTime ultimoLogin) {
        this.ultimoLogin = ultimoLogin;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
    
    
   @Override
public String toString() {
    return "Usuario{" +
           "id=" + id +
           ", perfil=" + perfil +
           ", nomeUsuario='" + nomeUsuario + '\'' +
           ", email='" + email + '\'' +
           ", senha='" + senha + '\'' +
           ", ultimoLogin=" + ultimoLogin +
           ", ativo=" + ativo +
           '}';
}

}

  