package eapli.base.pedidomanagement.domain;

public enum PedidoStatus {
    /**
     * O pedido foi submetido.
     * Esta pronto para ser aprovado/recusado
     */
    SUBMETIDO,

    // FLUXO DE APROVACAO
    /**
     * O pedido esta em aprovacao.
     */
    EM_APROVACAO,

    /**
     * O pedido foi aprovado.
     */
    APROVADO,

    /**
     * O pedido foi rejeitado.
     */
    REJEITADO,

    // FLUXO DE EXECUCAO
    /**
     * O pedido foi executado.
     */
    EM_EXECUCAO,

    /**
     * O pedido foi finalizado.
     */
    CONCLUIDO
}
