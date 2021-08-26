package eapli.base.formulario;


import eapli.framework.domain.model.ValueObject;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Embeddable
@XmlRootElement
public class Formulario implements ValueObject, Comparable<Formulario>, Serializable {
    @XmlElement
    private String nome;

    @ElementCollection
    @XmlElementWrapper(name = "atributos")
    @XmlElement(name = "atributo")
    private Set<Atributo> atributos ;

    @Embedded
    @XmlElement(name = "scriptvalidacao")
    private ScriptValidacao scriptValidacao;

    public Formulario(String nome, Set<Atributo> atributos, ScriptValidacao script) {
        this.nome = nome;
        this.atributos = atributos;
        this.scriptValidacao = script;
    }

    protected Formulario() {
        // for ORM only
    }

    @Override
    public int compareTo(Formulario o) {
        return nome.compareTo(o.nome);
    }

    public String nome(){
        return this.nome;
    }

    public Set<Atributo> atributos(){
        Set<Atributo> atributos2= new LinkedHashSet<>();
        for (Atributo a : atributos){
            atributos2.add( a.atributo());
        }
        atributos2.addAll(atributos);
        return atributos2;
    }
    public boolean hasAtributo(String nome){
      Atributo a = new Atributo(nome,"","","","");
        return this.atributos.contains(a);
    }
    public Atributo atributo(String nome){

        for ( Atributo atributo : atributos){
            if(atributo.nome().toUpperCase().equals(nome.toUpperCase()))
                return atributo;
        }
        return null;
    }
    public ScriptValidacao scriptValidacao(){
        return new ScriptValidacao(this.scriptValidacao.toString());
    }

    public Formulario formulario(){
        return  new Formulario(nome(),atributos(),scriptValidacao());
    }

    @Override
    public String toString() {
        return "Formulario{" +
                "nome='" + nome + '\'' +
                ", atributos=" + atributos +
                ", scriptValidacao=" + scriptValidacao +
                '}';
    }
    public static String formularioToXML(Formulario form){
        if (form != null ) {
            try {
                StringWriter sw = new StringWriter();
                JAXBContext jaxbContext = JAXBContext.newInstance(form.getClass());
                Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
                jaxbMarshaller.marshal(form, sw);
                String xmlFormulario = sw.toString();
                return  xmlFormulario;
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        }
            return new String("");

    }
    public static Formulario xmlToFormulario(String form){

        if(!form.isEmpty()){

            JAXBContext jaxbContext     = null;
            try {
                jaxbContext = JAXBContext.newInstance( Formulario.class);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                Formulario formulario = (Formulario) jaxbUnmarshaller.unmarshal(new StringReader(form));
                return formulario;
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        }
        return null;

    }
}
