package eapli.base.formulario;


    /*
         Um
atributo caracteriza-se por um nome de variável,
uma etiqueta (label) de apresentação,
uma descrição de ajuda,
um tipo de dados base (e.g., numérico, texto, data, booleano,
seleção de valores pré-definidos)
uma expressão regular que permita a sua validação local.
     */


//    nome
//    titulo
//    descricao
//



import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * A palavra-chaves de um Servico.
 * <p>
 * Esta class representa uma palavra chave relacionada um Servico.
 *
 * @author Rui Marinho
 */

@Embeddable
@XmlRootElement( name = "Atributo" )
public class Atributo implements ValueObject, Comparable<Atributo>, Serializable {

    private static final long serialVersionUID = 1L;
    @XmlElement
    private String nome; //Campo Telefone
    @XmlElement
    private String titulo; //Labl : TEL
    @XmlElement
    private String descricao; // Preencha o seu telefone
    @XmlElement
    private String tipoDados; //Numerico
    @XmlElement
    private String validador; //[0-9]*
    @XmlElement
    private String preenchimento;

    // TODO RM - Validar quais sao os campos necessários não serem empty nem null
    public Atributo( final String nome, final String titulo, final String descricao, final String tipoDados, String validador) {

        Preconditions.nonEmpty(nome);
        Preconditions.nonEmpty(tipoDados);
        Preconditions.noneNull(nome);
        Preconditions.noneNull(tipoDados);
        this.nome = nome;
        this.titulo = titulo;
        this.descricao = descricao;
        this.tipoDados = tipoDados;
        this.validador = validador;
        this.preenchimento = "";
    }

    public Atributo( final String nome, final String titulo, final String descricao, final String tipoDados, String validador, String preenchimento) {

        Preconditions.nonEmpty(nome);
        Preconditions.nonEmpty(tipoDados);
        Preconditions.noneNull(nome);
        Preconditions.noneNull(tipoDados);
        this.nome = nome;
        this.titulo = titulo;
        this.descricao = descricao;
        this.tipoDados = tipoDados;
        this.validador = validador;
        this.preenchimento = preenchimento;
    }

    protected Atributo() {
        // for ORM
    }

    public String nome() {
        return this.nome;
    }
    public String titulo() {
        return this.titulo;
    }
    public String descricao() {
        return this.descricao;
    }
    public String tipoDados() {
        return this.tipoDados;
    }
    public String validador() {
        return this.validador;
    }
    public String preenchimento() {
        return this.preenchimento;
    }
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Atributo)) {
            return false;
        }

        final Atributo that = (Atributo) o;
        return this.nome.equals(that.nome);
    }

    @Override
    public int hashCode() {
        return this.nome.hashCode();
    }

    @Override
    public String toString() {
        return this.nome;
    }

    @Override
    public int compareTo(final Atributo arg0) {
        return nome.compareTo(arg0.nome);
    }

    public Atributo atributo(){
        return new Atributo(this.nome, this.titulo,this.descricao, this.tipoDados,this.validador, this.preenchimento);
    }

}

