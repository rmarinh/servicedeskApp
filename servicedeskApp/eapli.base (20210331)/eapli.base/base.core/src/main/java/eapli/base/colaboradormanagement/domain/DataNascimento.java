package eapli.base.colaboradormanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A data de nascimento do Colaborador.
 * <p>
 * Esta class representa a data de nascimento do Colaborador.
 *
 * @author Rafael Soares 1181882@isep.ipp.pt
 */
@Embeddable
public class DataNascimento implements ValueObject, Comparable<DataNascimento> {

    private static final long serialVersionUID = 1L;

    @Temporal(TemporalType.DATE)
    private Calendar dataNascimento;

    public DataNascimento(final Calendar dataNascimento) {
        if (dataNascimento.equals(null)) {
            throw new IllegalArgumentException(
                    "Identifier dataNascimento for Catalog should neither be null nor empty");
        }
        // TODO validate invariants, i.e., identifier dataNascimento regular
        // expression
        this.dataNascimento = dataNascimento;
    }

    public DataNascimento(final String dataNascimento) {
        if (dataNascimento.equals(null)) {
            throw new IllegalArgumentException(
                    "Identifier dataNascimento for Catalog should neither be null nor empty");
        }

        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(dataNascimento.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        this.dataNascimento = Calendar.getInstance();
        this.dataNascimento.setTime(date);

    }

    protected DataNascimento() {
        // for ORM
    }

    public static DataNascimento valueOf(final Calendar dataNascimento) {
        return new DataNascimento(dataNascimento);
    }

    public static DataNascimento valueOf(final String dataNascimento) {
        return new DataNascimento(dataNascimento);
    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DataNascimento)) {
            return false;
        }

        final DataNascimento that = (DataNascimento) o;
        return this.dataNascimento.equals(that.dataNascimento);
    }

    @Override
    public int hashCode() {
        return this.dataNascimento.hashCode();
    }

    @Override
    public String toString() {
        return this.dataNascimento.toString();
    }

    @Override
    public int compareTo(final DataNascimento arg0) {
        return dataNascimento.compareTo(arg0.dataNascimento);
    }
}
