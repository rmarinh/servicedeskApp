package base.server.executortarefasautomaticas.presentation;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Message implements Serializable {

    private int versao;
    private int codigo;
    private int numeroBytes;
    private String dados;

    public Message(int versao, int codigo, int numeroBytes, String dados) {
        this.versao = versao;
        this.codigo = codigo;
        this.numeroBytes = numeroBytes;
        this.dados = dados;
    }

    public int versao() {
        return versao;
    }

    public int codigo() {
        return codigo;
    }

    public int numeroBytes() {
        return numeroBytes;
    }

    public String dados() {
        return dados;
    }

    @Override
    public String toString() {
        return "Message{" +
                "versao=" + versao +
                ", codigo=" + codigo +
                ", numeroBytes=" + numeroBytes +
                ", dados='" + dados + '\'' +
                '}';
    }
}