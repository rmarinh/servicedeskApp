package base.server.executortarefasautomaticas.protocol.badrequest;

import base.server.executortarefasautomaticas.protocol.badrequest.BaseErrorRequest;

public class ErrorInRequest extends BaseErrorRequest {

    public ErrorInRequest(final String request, final String errorDescription) {
        super(request, errorDescription);
    }

    @Override
    protected String codigoMensagem() {
        return "ERROR_IN_REQUEST";
    }
}
