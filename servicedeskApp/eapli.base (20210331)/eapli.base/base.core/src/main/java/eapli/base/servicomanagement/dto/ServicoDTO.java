package eapli.base.servicomanagement.dto;

public class ServicoDTO {

    public String codUnico;
    public String titulo;
    public String descBreve;
    public String descCompleta;
    public String icone;

    public ServicoDTO(final String codigo, final String titulo, final String descBreve, final String descCompleta, final String icone) {

        this.codUnico = codigo;
        this.titulo = titulo;
        this.descBreve = descBreve;
        this.descCompleta = descCompleta;
        this.icone = icone;

    }
}
