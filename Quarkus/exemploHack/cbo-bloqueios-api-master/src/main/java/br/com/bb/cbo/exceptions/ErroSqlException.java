package br.com.bb.cbo.exceptions;

import org.hibernate.JDBCException;
import br.com.bb.dev.ext.exceptions.ErroNegocialException;
import br.com.bb.dev.ext.error.ChavesMonitoradasPadrao;
import br.com.bb.dev.ext.error.ChavesMonitoradasSQL;

public class ErroSqlException extends ErroNegocialException {

	private static final long serialVersionUID = -5729172745910551668L;

	public ErroSqlException(Exception e){
        super(ErrosSistema.ERRO_SQL.get(), e.getCause());
        int code = -1;
        String sqlQuery = "";
        String motivo;

        if (e.getCause() instanceof JDBCException) {
            JDBCException jdbcException = (JDBCException) e.getCause();

            if (jdbcException.getSQLException() != null) {
                code = jdbcException.getSQLException().getErrorCode();
            }

            if (jdbcException.getSQL() != null && jdbcException.getSQL().length() > 0 ) {
                sqlQuery = jdbcException.getSQL();
            }

            if (jdbcException.getCause() != null) {
                motivo = jdbcException.getCause().getMessage();
            } else {
                motivo = jdbcException.getMessage();
            }
        } else {
            motivo = e.getMessage();
        }

        erro.addVariavel(ChavesMonitoradasPadrao.MOTIVO_ERRO.get(), motivo)
            .addVariavel(ChavesMonitoradasPadrao.ORIGEM_ERRO.get(), getSourceFromStackTraceSqlTrace())
            .addVariavel(ChavesMonitoradasSQL.SQL_CODE.get(), String.valueOf(code))
            .addVariavel(ChavesMonitoradasSQL.QUERY_SQL.get(),sqlQuery);
    }

    private String getSourceFromStackTraceSqlTrace() {
        int index = 0;
        if (this.getStackTrace().length > 1) {
            index = 1;
        }
        StackTraceElement stackTrace = this.getStackTrace()[index];
        return String.format(MENSAGEM_ORIGEM ,stackTrace.getClassName(), stackTrace.getLineNumber());
    }
}
