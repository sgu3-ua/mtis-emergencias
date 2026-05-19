
/**
 * ServicioParametrosSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.3  Built on : Jun 27, 2015 (11:17:49 BST)
 */
    package org.example.www.servicioparametros;

    import java.util.*;
    import org.example.emergencias.types.v1.ParametroType;

    /**
     *  ServicioParametrosSkeleton java skeleton for the axisService
     */
    public class ServicioParametrosSkeleton{
        
         /**
          * Auto generated method signature
          * 
          * @param verificar 
          * @return verificarResponse 
          */
         public org.example.www.servicioparametros.VerificarResponse verificar
          (
          org.example.www.servicioparametros.Verificar verificar
          )
         {
            VerificarResponse response = new VerificarResponse();
            ParametroType param = verificar.getParametro();
            boolean correcto = false;
            
            if (param != null && param.getValor() != null) {
                try {
                    double val = Double.parseDouble(param.getValor());
                    double umbralMin = verificar.getUmbralMin();
                    double umbralMax = verificar.getUmbralMax();
                    
                    if (Double.isNaN(umbralMin) || Double.isNaN(umbralMax)) {
                        double[] defaults = {0, 10};
                        if (defaults != null) {
                            if (Double.isNaN(umbralMin)) umbralMin = defaults[0];
                            if (Double.isNaN(umbralMax)) umbralMax = defaults[1];
                        }
                    }
                    
                    correcto = val >= umbralMin && val <= umbralMax;
                } catch (NumberFormatException e) {
                    correcto = false;
                }
            }
            
            // Simulacion de fallo aleatorio (30% de probabilidad) para demostrar flujo de reintento
            if (Math.random() < 0.30) {
                correcto = false;
            }
            
            response.setCorrecto(correcto);
            response.setParametro(param);
            return response;
         }
     
         /**
          * Auto generated method signature
          * 
          * @param calcular 
          * @return calcularResponse 
          */
         public org.example.www.servicioparametros.CalcularResponse calcular
          (
          org.example.www.servicioparametros.Calcular calcular
          )
         {
            CalcularResponse response = new CalcularResponse();
            ParametroType[] params = calcular.getParametro();
            if (params == null) {
                params = new ParametroType[0];
            }
            
            List<ParametroType> resultado = new ArrayList<>();
            
            Map<String, Double> valores = new HashMap<>();
            for (ParametroType p : params) {
                if (p != null && p.getNombre() != null && p.getValor() != null) {
                    try {
                        valores.put(p.getNombre().toLowerCase(), Double.parseDouble(p.getValor()));
                    } catch (NumberFormatException e) {
                        // ignorar
                    }
                }
            }
            
            // Calcular tasa de actuacion
            if (valores.containsKey("serviciosconactuacion") && valores.containsKey("numeroservicios")) {
                double conAct = valores.get("serviciosconactuacion");
                double num = valores.get("numeroservicios");
                if (num > 0) {
                    ParametroType p = new ParametroType();
                    p.setNombre("tasaActuacion");
                    p.setValor(String.format(Locale.US, "%.2f", (conAct / num) * 100.0));
                    resultado.add(p);
                }
            }
            
            // Calcular eficiencia temporal basada en tiempo promedio
            if (valores.containsKey("tiemporespuestapromedio")) {
                double avg = valores.get("tiemporespuestapromedio");
                ParametroType p = new ParametroType();
                p.setNombre("eficienciaTemporal");
                if (avg <= 10) p.setValor("100.00");
                else if (avg <= 20) p.setValor("75.00");
                else if (avg <= 30) p.setValor("50.00");
                else p.setValor("25.00");
                resultado.add(p);
            }
            
            // Calcular dispersion temporal
            if (valores.containsKey("tiemporespuestamaximo") && valores.containsKey("tiemporespuestapromedio")) {
                double max = valores.get("tiemporespuestamaximo");
                double avg = valores.get("tiemporespuestapromedio");
                if (avg > 0) {
                    ParametroType p = new ParametroType();
                    p.setNombre("dispersionTemporal");
                    p.setValor(String.format(Locale.US, "%.2f", max / avg));
                    resultado.add(p);
                }
            }
            
            response.setResultado(resultado.toArray(new ParametroType[0]));
            return response;
         }
     
         /**
          * Auto generated method signature
          * 
          * @param comprobar 
          * @return comprobarResponse 
          */
         public org.example.www.servicioparametros.ComprobarResponse comprobar
          (
          org.example.www.servicioparametros.Comprobar comprobar
          )
         {
            ComprobarResponse response = new ComprobarResponse();
            ParametroType param = comprobar.getParametro();
            boolean correcto = param != null && param.getNombre() != null && !param.getNombre().isEmpty()
                    && param.getValor() != null && !param.getValor().isEmpty();
            response.setCorrecto(correcto);
            response.setParamCorrecto(param);
            return response;
         }
     }
     
