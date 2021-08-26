package base.server.executortarefasautomaticas.protocol.aprovacaorequest;

import base.server.executortarefasautomaticas.protocol.ExecutarPedidoProtocolRequest;
import eapli.framework.csv.CsvRecord;

public abstract class BaseAprovarRequest extends ExecutarPedidoProtocolRequest {

    private final String respostaDados;

    public BaseAprovarRequest(String inputRequest, String respostaDados) {
        super(null, inputRequest);
        this.respostaDados = respostaDados;
    }

    @Override
    public String execute() {
        // nothing to do, just build the response
        return buildResponse();
    }

    public String buildResponse() {
        final Object[] fields = {
                super.version,
                messageType(),
                respostaDados.length(),
                respostaDados
        };

        final boolean[] mask = {false, false, false, true};
        return CsvRecord.valueOf(fields, mask).toString();
    }

    protected abstract String messageType();
}
