package base.server.executortarefasautomaticas.protocol.standardrequest;

public class TestRequest extends BaseRequest {

    public TestRequest(final String request, final String testDescription) {
        super(request, testDescription);
    }

    @Override
    protected String messageType() {
        return "2";
    }
}
