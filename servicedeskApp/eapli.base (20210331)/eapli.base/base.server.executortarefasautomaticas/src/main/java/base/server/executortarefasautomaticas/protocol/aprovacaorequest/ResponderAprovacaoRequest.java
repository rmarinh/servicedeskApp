package base.server.executortarefasautomaticas.protocol.aprovacaorequest;

public class ResponderAprovacaoRequest extends BaseAprovarRequest {

    public ResponderAprovacaoRequest(final String request, final String testDescription) {
        super(request, testDescription);
    }

    @Override
    protected String messageType() {
        return "2";
    }
}
