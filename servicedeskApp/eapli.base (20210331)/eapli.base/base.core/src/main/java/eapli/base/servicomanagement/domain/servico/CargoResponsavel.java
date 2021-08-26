package eapli.base.servicomanagement.domain.servico;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

/**
 *   CArgo Responsavel de Aprovacao
 *
 * Esta class representa um desginacao ou aprovacao que identifica um Nivel de Criticidade.
 *
 * @author Rui Marinho
 *
 */
@Embeddable
public class CargoResponsavel implements ValueObject, Comparable<CargoResponsavel>{

    private static final long serialVersionUID = 1L;


    private String cargoResponsavel;

    public CargoResponsavel(final String cargoResponsavel) {

        // TODO validate invariants, i.e., identifier designacao regular
        // expression
        this.cargoResponsavel = cargoResponsavel;
    }

    protected CargoResponsavel() {
        // for ORM
    }

    public static CargoResponsavel valueOf(final String designacao) {
        return new CargoResponsavel(designacao);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CargoResponsavel)) {
            return false;
        }

        final CargoResponsavel that = (CargoResponsavel) o;
        return this.cargoResponsavel.equals(that.cargoResponsavel);
    }

    @Override
    public int hashCode() {
        return this.cargoResponsavel.hashCode();
    }

    @Override
    public String toString() {
        return this.cargoResponsavel.toString();
    }

    @Override
    public int compareTo(final CargoResponsavel arg0) {
        return cargoResponsavel.compareTo(arg0.cargoResponsavel);
    }
}
