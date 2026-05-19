
/**
 * ServicioInformesSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.3  Built on : Jun 27, 2015 (11:17:49 BST)
 */
    package org.example.www.servicioinformes;

    import java.sql.*;
    import org.example.emergencias.types.v1.InformeType;
    import org.example.emergencias.utils.DBUtils;

    /**
     *  ServicioInformesSkeleton java skeleton for the axisService
     */
    public class ServicioInformesSkeleton{
        
         /**
          * Auto generated method signature
          * 
          * @param generarInformeEstrategico 
          * @return generarInformeEstrategicoResponse 
          */
         public org.example.www.servicioinformes.GenerarInformeEstrategicoResponse generarInformeEstrategico
          (
          org.example.www.servicioinformes.GenerarInformeEstrategico generarInformeEstrategico
          )
         {
            GenerarInformeEstrategicoResponse response = new GenerarInformeEstrategicoResponse();
            InformeType input = generarInformeEstrategico.getInformeEstrategico();
            InformeType output = new InformeType();
            boolean generado = false;
            try (Connection conn = DBUtils.getConnection();
                 PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO informe (expediente_id, tipo, resumen) VALUES (?, 'ESTRATEGICO', ?)",
                     Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, Integer.parseInt(input.getExpedienteId()));
                ps.setString(2, input.getResumen());
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                int id = -1;
                if (rs.next()) {
                    id = rs.getInt(1);
                }
                output.setInformeId(String.valueOf(id));
                output.setTipo("ESTRATEGICO");
                output.setResumen(input.getResumen());
                output.setExpedienteId(input.getExpedienteId());
                generado = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            response.setGenerado(generado);
            response.setInformeGenerado(output);
            return response;
         }
     
         /**
          * Auto generated method signature
          * 
          * @param generarInformeOperativo 
          * @return generarInformeOperativoResponse 
          */
         public org.example.www.servicioinformes.GenerarInformeOperativoResponse generarInformeOperativo
          (
          org.example.www.servicioinformes.GenerarInformeOperativo generarInformeOperativo
          )
         {
            GenerarInformeOperativoResponse response = new GenerarInformeOperativoResponse();
            InformeType input = generarInformeOperativo.getInfomeOperativo();
            InformeType output = new InformeType();
            boolean generado = false;
            try (Connection conn = DBUtils.getConnection();
                 PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO informe (expediente_id, tipo, resumen) VALUES (?, 'OPERATIVO', ?)",
                     Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, Integer.parseInt(input.getExpedienteId()));
                ps.setString(2, input.getResumen());
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                int id = -1;
                if (rs.next()) {
                    id = rs.getInt(1);
                }
                output.setInformeId(String.valueOf(id));
                output.setTipo("OPERATIVO");
                output.setResumen(input.getResumen());
                output.setExpedienteId(input.getExpedienteId());
                generado = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            response.setGenerado(generado);
            response.setInformeGenerado(output);
            return response;
         }
     }
     