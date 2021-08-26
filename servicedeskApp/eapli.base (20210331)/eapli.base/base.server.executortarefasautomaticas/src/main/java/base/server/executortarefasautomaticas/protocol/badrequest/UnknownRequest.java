package base.server.executortarefasautomaticas.protocol.badrequest;

import base.server.executortarefasautomaticas.protocol.badrequest.BaseErrorRequest;

public class UnknownRequest extends BaseErrorRequest {

    public UnknownRequest(final String inputLine) {
        super(inputLine);
    }

    @Override
    protected String codigoMensagem() {
        return "UNKNOWN_REQUEST";
    }
}
