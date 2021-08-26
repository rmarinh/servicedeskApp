package base.server.executortarefasautomaticas.protocol;

import base.server.executortarefasautomaticas.application.ExecutarAtividadeManualController;
import base.server.executortarefasautomaticas.protocol.aprovacaorequest.ResponderAprovacaoRequest;
import base.server.executortarefasautomaticas.protocol.badrequest.ErrorInRequest;
import base.server.executortarefasautomaticas.protocol.badrequest.UnknownRequest;
import base.server.executortarefasautomaticas.protocol.execucaorequest.ResponderExecutarRequest;
import base.server.executortarefasautomaticas.protocol.standardrequest.CloseConnectionRequest;
import base.server.executortarefasautomaticas.protocol.standardrequest.TestRequest;
import eapli.framework.csv.util.CsvLineMarshaler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;

public class ExecutarPedidoProtocolMessageParser {
    private static final Logger LOGGER = LogManager.getLogger(ExecutarPedidoProtocolMessageParser.class);

    private static ExecutarAtividadeManualController controller;

    private ExecutarPedidoProtocolMessageParser() {
        // avoid instantiation
    }

    /**
     * To inject a different controller for testing purposes.
     *
     * @param controller
     */
    /* package */
    public static void injectController(final ExecutarAtividadeManualController controller) {
        synchronized (lock) {
            ExecutarPedidoProtocolMessageParser.controller = controller;
        }
    }

    private static final Object lock = new Object();

    private static ExecutarAtividadeManualController getController() {
        synchronized (lock) {
            if (ExecutarPedidoProtocolMessageParser.controller != null) {
                return ExecutarPedidoProtocolMessageParser.controller;
            }
        }
        return new ExecutarAtividadeManualController();
    }

    /**
     * Parse and build the request.
     *
     * @param inputLine
     * @return
     */
    public static ExecutarPedidoProtocolRequest parse(final String inputLine) {
        // as a fallback make sure we return unknown
        ExecutarPedidoProtocolRequest request = new UnknownRequest(inputLine);
        // parse to determine which type of request and if it is sintactally valid
        String[] tokens;

        try {
            tokens = CsvLineMarshaler.tokenize(inputLine).toArray(new String[0]);
            if ("0".equals(tokens[1])) {
                // test request
                request = new TestRequest(inputLine, "");
            } else if ("1".equals(tokens[1])) {
                // fechar request
                request = new CloseConnectionRequest(inputLine, "");
            } else if ("11".equals(tokens[1])) {
                // Aprovar request
                request = new ResponderAprovacaoRequest(inputLine, "");
            } else if ("21".equals(tokens[1])) {
                // Executar request
                request = new ResponderExecutarRequest(inputLine, "");
            }
        } catch (final ParseException e) {
            LOGGER.info("Unable to parse request: {}", inputLine);
            request = new ErrorInRequest(inputLine, "Unable to parse request");
        }

        return request;
    }

    private static ExecutarPedidoProtocolRequest listarCatalogosDisponveils(final String inputLine, final String[] tokens) {
        ExecutarPedidoProtocolRequest request = null;
        // TODO: VALIDAR A VERSAO CORRECTA
        // TODO: VALIDAR SE A DATA TEM O TAMANHO DO NUMERO DE BYTES
        // TODO: VALIDAR SE TODOS OS VALORES NUMEROS SAO POSITIVOS E INFERIORES A 255 (0 ~ 255)
        if (tokens.length != 4) {
            request = new ErrorInRequest(inputLine, "Numero errado de parametros");
        } else if (!isStringParam(tokens[0])) {
            request = new ErrorInRequest(inputLine, "Versao tem de ser um valor numerico");
        } else if (!isStringParam(tokens[1])) {
            request = new ErrorInRequest(inputLine, "Codigo tem de ser um valor numerico");
        } else if (!isStringParam(tokens[2])) {
            request = new ErrorInRequest(inputLine, "Numero de bytes tem de ser um valor numerico");
        } else {
            controller = new ExecutarAtividadeManualController();
            // listar
        }
        return request;
    }

    private static boolean isStringParam(final String string) {
        return string.length() >= 2 && string.charAt(0) == '"' && string.charAt(string.length() - 1) == '"';
    }
}
