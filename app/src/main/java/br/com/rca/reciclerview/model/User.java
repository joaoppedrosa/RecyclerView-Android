package br.com.rca.reciclerview.model;

/**
 * Created by rafael on 06/04/15.
 */
public class User {

    private String email;
    private String nome;

    public User(String nome, String email) {

        this.nome = nome;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
