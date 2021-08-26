package base.server.executortarefasautomaticas.protocol.execucaorequest;

public class ResponderExecutarRequest extends BaseExecutarRequest {

    public ResponderExecutarRequest(final String request, final String testDescription) {
        super(request, testDescription);
    }

    @Override
    protected String messageType() {
        return "2";
    }
}
