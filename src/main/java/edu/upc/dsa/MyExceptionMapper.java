package edu.upc.dsa;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

    @Provider // Marca esta clase como proveedor JAX-RS para manejar excepciones
    public class MyExceptionMapper implements ExceptionMapper<Exception> {

        @Override
        public Response toResponse(Exception ex) {
            // Retorna el stack trce como texto plano y código de error 500
            return Response
                    .status(500)
                    .entity(getStackTraceAsString(ex)) // Devuelve el error como texto
                    .type("text/plain")
                    .build();
        }

        // Convierte una excepción (Exception) a una cadena legible (stack trace)
        private String getStackTraceAsString(Exception ex) {
            StringBuilder sb = new StringBuilder();
            for (StackTraceElement element : ex.getStackTrace()) {
                sb.append(element.toString()).append("\n");
            }
            return sb.toString();
        }
    }

