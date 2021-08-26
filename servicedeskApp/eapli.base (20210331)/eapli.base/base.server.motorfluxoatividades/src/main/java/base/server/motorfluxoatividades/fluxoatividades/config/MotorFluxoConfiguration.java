package base.server.motorfluxoatividades.fluxoatividades.config;

public class MotorFluxoConfiguration {

    private String tipo;
    private String algoritmoColaborador;
    private String algoritmoExecutor;

    public String tipo() {
        return tipo;
    }

    public void defineTipo(String tipo) {
        this.tipo = tipo;
    }

    public String algoritmoColaborador() {
        return this.algoritmoColaborador;
    }

    public String algoritmoExecutor() {
        return this.algoritmoExecutor;
    }

    public void defineAlgoritmoColaborador(String algoritmoColaborador) {
        this.algoritmoColaborador = algoritmoColaborador;
    }

    public void defineAlgoritmoExecutor(String algoritmoExecutor) {
        this.algoritmoExecutor = algoritmoExecutor;
    }
}
