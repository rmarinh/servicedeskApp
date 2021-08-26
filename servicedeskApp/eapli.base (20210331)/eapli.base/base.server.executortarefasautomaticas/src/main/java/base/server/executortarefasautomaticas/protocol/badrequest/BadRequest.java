package base.server.executortarefasautomaticas.protocol.badrequest;

public class BadRequest extends BaseErrorRequest {

    public BadRequest(final String request, final String errorDescription) {
        super(request, errorDescription);
    }

    @Override
    protected String codigoMensagem() {
        return "ERROR_IN_REQUEST";
    }
}
