package base.server.executortarefasautomaticas.protocol;

import base.server.executortarefasautomaticas.application.ExecutarAtividadeManualController;
import base.server.executortarefasautomaticas.protocol.badrequest.BaseErrorRequest;
import base.server.executortarefasautomaticas.protocol.standardrequest.TestRequest;

public abstract class ExecutarPedidoProtocolRequest {
    protected final String version;
    protected final String request;
    protected final ExecutarAtividadeManualController controller;

    protected ExecutarPedidoProtocolRequest(final ExecutarAtividadeManualController controller,
                                            final String inputRequest) {
        // TODO: TORNAR A VERSAO NAO ESTATICA
        this.version = "1";
        this.request = inputRequest;
        this.controller = controller;
    }

    /**
     * Executes the requested action and builds the response to the client.
     *
     * @return the response to send back to the client
     */
    public abstract String execute();

    /**
     * Indicates the object is a goodbye message, that is, a message that will close the
     * connection to the client.
     *
     * @return {@code true} if the object is a a goodbye message.
     */
    public boolean isGoodbye() {
        return false;
    }

    protected String buildServerError(final String errorDescription) {
        final BaseErrorRequest r = new BaseErrorRequest(request, errorDescription) {

            @Override
            protected String codigoMensagem() {
                return "SERVER_ERROR";
            }

        };
        return r.buildResponse();
    }

    protected String buildBadRequest(final String errorDescription) {
        final BaseErrorRequest r = new BaseErrorRequest(request, errorDescription) {

            @Override
            protected String codigoMensagem() {
                return "BAD_REQUEST";
            }

        };
        return r.buildResponse();
    }

    protected String buildTestRequest(final String testDescription) {
        final TestRequest r = new TestRequest(request, testDescription) {

            @Override
            protected String messageType() {
                return "2";
            }

        };
        return r.buildResponse();
    }
}
