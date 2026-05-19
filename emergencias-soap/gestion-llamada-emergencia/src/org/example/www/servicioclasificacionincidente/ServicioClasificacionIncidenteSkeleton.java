/**
 * ServicioClasificacionIncidenteSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.3  Built on : Jun 27, 2015 (11:17:49 BST)
 */
package org.example.www.servicioclasificacionincidente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DBUtil;
import org.example.www.tipos.*;

/**
 *  ServicioClasificacionIncidenteSkeleton java skeleton for the axisService
 */
public class ServicioClasificacionIncidenteSkeleton{
        
         
        /**
         * Auto generated method signature
         * 
         * @param determinarUrgenciaIncidente 
         * @return determinarUrgenciaIncidenteResponse 
         */
        
        public org.example.www.servicioclasificacionincidente.DeterminarUrgenciaIncidenteResponse determinarUrgenciaIncidente
         (
          org.example.www.servicioclasificacionincidente.DeterminarUrgenciaIncidente determinarUrgenciaIncidente
         )
        {
        	DeterminarUrgenciaIncidenteResponse response = new DeterminarUrgenciaIncidenteResponse();

            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;

            try {
                conn = DBUtil.getConnection();

                String sql = "SELECT * FROM aviso WHERE id = ?";

                ps = conn.prepareStatement(sql);
                ps.setInt(1, determinarUrgenciaIncidente.getIdAviso());

                rs = ps.executeQuery();
                
                if (rs.next()) {
                    boolean personasHeridas = rs.getBoolean("personas_heridas");
                    boolean hayFuego = rs.getBoolean("hay_fuego");
                    boolean personasAtrapadas = rs.getBoolean("personas_atrapadas");
                    boolean hayHumo = rs.getBoolean("hay_humo");
                    boolean riesgoEstructural = rs.getBoolean("riesgo_estructural");
                    boolean riesgoSeguridad = rs.getBoolean("riesgo_seguridad");

                    String urgencia;

                    // URGENCIA ALTA
                    if (personasHeridas || hayFuego) {
                        urgencia = "ALTA";
                    }
                    // URGENCIA MEDIA
                    else if (personasAtrapadas || hayHumo || riesgoEstructural || riesgoSeguridad) {
                        urgencia = "MEDIA";
                    }
                    // URGENCIA BAJA (alteracionOrdenPublico)
                    else {
                        urgencia = "BAJA";
                    }

                    response.setNivelUrgencia(urgencia);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Error determinando urgencia del incidente", e);
            } finally {
                try {
                    if (rs != null)
                        rs.close();
                } catch (Exception e) {}
                try {
                    if (ps != null)
                        ps.close();
                } catch (Exception e) {}
                try {
                    if (conn != null)
                        conn.close();
                } catch (Exception e) {}
            }
            
            return response;
    	}
     
         
        /**
         * Auto generated method signature
         * 
         * @param determinarTipoIncidente 
         * @return determinarTipoIncidenteResponse 
         */
        
        public org.example.www.servicioclasificacionincidente.DeterminarTipoIncidenteResponse determinarTipoIncidente
         (
         org.example.www.servicioclasificacionincidente.DeterminarTipoIncidente determinarTipoIncidente
         )
        {
        	DeterminarTipoIncidenteResponse response = new DeterminarTipoIncidenteResponse();

            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;

            try {
                conn = DBUtil.getConnection();

                String sql = "SELECT * FROM aviso WHERE id = ?";
                
                ps = conn.prepareStatement(sql);
                ps.setInt(1, determinarTipoIncidente.getIdAviso());

                rs = ps.executeQuery();

                if (rs.next()) {
                    boolean hayFuego = rs.getBoolean("hay_fuego");
                    boolean hayHumo = rs.getBoolean("hay_humo");
                    boolean personasHeridas = rs.getBoolean("personas_heridas");
                    boolean personasAtrapadas = rs.getBoolean("personas_atrapadas");
                    boolean riesgoSeguridad = rs.getBoolean("riesgo_seguridad");
                    boolean riesgoEstructural = rs.getBoolean("riesgo_estructural");
                    boolean alteracionOrdenPublico = rs.getBoolean("alteracion_orden_publico");

                    TipoIncidente tipo = new TipoIncidente();

                    boolean requiereBomberos = false;
                    boolean requierePolicia = false;
                    boolean requiereSanitarios = false;
                    
                    // BOMBEROS
                    if (hayFuego || hayHumo || personasAtrapadas || riesgoEstructural) {
                        requiereBomberos = true;
                    }

                    // SANITARIOS
                    if (personasHeridas) {
                        requiereSanitarios = true;
                    }

                    // POLICIA
                    if (riesgoSeguridad || alteracionOrdenPublico) {
                        requierePolicia = true;
                    }

                    tipo.setRequiereBomberos(requiereBomberos);
                    tipo.setRequierePolicia(requierePolicia);
                    tipo.setRequiereSanitarios(requiereSanitarios);

                    response.setTipoIncidente(tipo);
                } 
                
            } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException("Error determinando tipo de incidente", e);
                } finally {
                    try {
                        if (rs != null)
                            rs.close();
                    } catch (Exception e) {}
                    try {
                        if (ps != null)
                            ps.close();
                    } catch (Exception e) {}
                    try {
                        if (conn != null)
                            conn.close();
                    } catch (Exception e) {}
                }

                return response;
        }
}    