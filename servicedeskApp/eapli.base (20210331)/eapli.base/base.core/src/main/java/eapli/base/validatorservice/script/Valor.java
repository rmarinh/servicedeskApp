package eapli.base.validatorservice.script;

import java.util.Date;
import java.util.List;
/*

Valores suportados na lingiuagem definida
    Numerico
    Decimal
    Booleano
    String
    Null
    Date
    "Retorno VOID" ->  Usado para a maior parte das funçoe menos o assert
 */
public class Valor implements Comparable<Valor> {

    //Criados por definicao para retorno
    public static final Valor NULL = new Valor();
    public static final Valor VOID = new Valor();

    //VAlor apanhado poor definicao e posteriormente feito o cast
    private Object value;

    //Contrutor apenas para Valores Null e Void
    private Valor() {
        value = new Object();
    }

    Valor(Object v) {
        if(v == null) {
            throw new RuntimeException("v == null");
        }
        value = v;
        // Caso nao seja possivel o cast deve lançar uma exceçao pois nao é um valor suportado
        if(!(isBoolean() ||
                isList() ||
                isNumber() ||
                isString() ||
                isDate())) {
            throw new RuntimeException("Tipo de dados Invalido: " + v + " (" + v.getClass() + ")");
        }
    }

    //Cast para Booleano
    public Boolean asBoolean() {
        return (Boolean)value;
    }

    //Cast para tipo double
    public Double asDouble() {
        return ((Number)value).doubleValue();
    }

    //Lista usado no retorno pesquisa
    @SuppressWarnings("unchecked")
    public List<Valor> asList() {
        return (List<Valor>)value;
    }

    //Cast paar String
    public String asString() {
        return (String)value;
    }

    //Cast para Data
    public Date asDate() {
        return (Date) value;
    }

    /*
    Compare to
    Verifica primeiro o tipo de valor do ramo esquerdo e direito e faz posteriormente a comparacao
    Ambos tem de ser do mesmo tipo caso contrario lançada uma nova exceçao
     */
    @Override
    public int compareTo(Valor that) {
        if(this.isNumber() && that.isNumber()) {
            if(this.equals(that)) {
                return 0;
            }
            else {
                return this.asDouble().compareTo(that.asDouble());
            }
        }
        else if(this.isString() && that.isString()) {
            return this.asString().compareTo(that.asString());
        }
        else if (this.isDate() &&that.isDate()){
            return this.asDate().compareTo(that.asDate());
        }
        else {
            throw new RuntimeException("Illegal Expression: comaparacao nao possivel val esquerdo'"
                    + this + "' com val direito '" + that + "`");
        }
    }

    @Override
    public boolean equals(Object o) {
        //Verificacao de iguualdade
        if(this == VOID || o == VOID) {
            throw new RuntimeException("can't use VOID: " + this + " ==/!= " + o);
        }
        if(this == o) {
            return true;
        }
        if(o == null ) {
            return false;
        }
        if (this.getClass() != o.getClass()){
            return false;
        }

        Valor that = (Valor)o;
        if(this.isNumber() && that.isNumber()) {
            double diff = Math.abs(this.asDouble() - that.asDouble());
            return diff < 0.00001;
        }
        else if (this.isDate() && that.isDate()){
            return this.asDate().equals(that.asDate());
        }
        else {
            return this.value.equals(that.value);
        }
    }

    /*
    Verificar os tipos de valores atraves do instanceof
     */
    public boolean isBoolean() {
        return value instanceof Boolean;
    }

    public boolean isNumber() {
        return value instanceof Number;
    }

    public boolean isList() {
        return value instanceof List<?>;
    }

    public boolean isNull() {
        return this == NULL;
    }

    public boolean isVoid() {
        return this == VOID;
    }

    public boolean isString() {
        return value instanceof String;
    }

    public boolean isDate() {
        return value instanceof Date;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }


    @Override
    public String toString() {
        if(isNull()){ //prevenir nullpointer
            return "NULL";
        }else{
            if (isVoid()){
                return "VOID" ;
            }else{
               return  String.valueOf(value);
            }
        }
    }
}
