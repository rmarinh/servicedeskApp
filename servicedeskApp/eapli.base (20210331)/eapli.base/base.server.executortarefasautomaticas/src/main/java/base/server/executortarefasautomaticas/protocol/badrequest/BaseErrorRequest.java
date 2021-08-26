package base.server.executortarefasautomaticas.protocol.badrequest;

import base.server.executortarefasautomaticas.protocol.ExecutarPedidoProtocolRequest;
import eapli.framework.csv.CsvRecord;

public abstract class BaseErrorRequest extends ExecutarPedidoProtocolRequest {

    private final String errorDescription;

    protected BaseErrorRequest(final String request, final String errorDescription) {
        super(null, request);
        this.errorDescription = errorDescription;
    }

    protected BaseErrorRequest(final String request) {
        super(null, request);
        this.errorDescription = null;
    }

    @Override
    public String execute() {
        // nothing to do, just build the response
        return buildResponse();
    }

    public String buildResponse() {
        final Object[] fields = {
                codigoMensagem(),
                request,
                errorDescription
        };
        final boolean[] mask = { false, true, true };
        return CsvRecord.valueOf(fields, mask).toString();
    }

    protected abstract String codigoMensagem();
}
