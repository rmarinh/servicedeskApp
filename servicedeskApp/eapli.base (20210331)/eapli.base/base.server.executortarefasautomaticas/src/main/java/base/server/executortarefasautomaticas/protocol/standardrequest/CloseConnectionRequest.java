package base.server.executortarefasautomaticas.protocol.standardrequest;

public class CloseConnectionRequest extends BaseRequest {

    public CloseConnectionRequest(final String request, final String testDescription) {
        super(request, testDescription);
    }

    @Override
    protected String messageType() {
        return "2";
    }
}
