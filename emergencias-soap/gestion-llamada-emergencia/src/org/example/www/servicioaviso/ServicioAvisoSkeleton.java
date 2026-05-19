/**
 * ServicioAvisoSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.3  Built on : Jun 27, 2015 (11:17:49 BST)
 */
package org.example.www.servicioaviso; 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DBUtil;
import org.example.www.tipos.*;

/**
 *  ServicioAvisoSkeleton java skeleton for the axisService
 */
public class ServicioAvisoSkeleton{
             
            /**
             * Auto generated method signature
             * 
             * @param crear 
             * @return crearResponse 
             */
            
            public org.example.www.servicioaviso.CrearResponse crear
            (
             org.example.www.servicioaviso.Crear crear
        	)
            {
            	CrearResponse response = new CrearResponse();
                Connection conn = null;
                PreparedStatement stmt = null;
                ResultSet keys = null;
                
                try {
                    Aviso aviso = crear.getAviso();

                    conn = DBUtil.getConnection();
                    
                    String sql = "INSERT INTO aviso (" +
                            "telefono, " +
                            "localizacion, " +
                            "descripcion, " +
                            "afectados, " +
                            "hay_fuego, " +
                            "hay_humo, " +
                            "personas_atrapadas, " +
                            "personas_heridas, " +
                            "riesgo_seguridad, " +
                            "riesgo_estructural, " +
                            "alteracion_orden_publico " +
                            ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                    stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                    
                    stmt.setString(1, aviso.getTelefono());
                    stmt.setString(2, aviso.getLocalizacion());
                    stmt.setString(3, aviso.getDescripcion());
                    if (aviso.getAfectados() != null) {
                        stmt.setInt(4, aviso.getAfectados().intValue());
                    } else {
                        stmt.setNull(4, java.sql.Types.INTEGER);
                    }
                    stmt.setBoolean(5, aviso.getHayFuego());
                    stmt.setBoolean(6, aviso.getHayHumo());
                    stmt.setBoolean(7, aviso.getPersonasAtrapadas());
                    stmt.setBoolean(8, aviso.getPersonasHeridas());
                    stmt.setBoolean(9, aviso.getRiesgoSeguridad());
                    stmt.setBoolean(10, aviso.getRiesgoEstructural());
                    stmt.setBoolean(11, aviso.getAlteracionOrdenPublico());
                    stmt.executeUpdate();
                    
                    // Obtener el id del aviso recien creado para poder devolverlo
                    keys = stmt.getGeneratedKeys();
                    if (keys.next()) {
                    	response.setIdAviso(keys.getInt(1));
                    }

                    response.setOk(true);
                    
                } catch (Exception e) {
                    response.setOk(false);
                    response.setError(e.getMessage());
                    e.printStackTrace();
                    
                } finally {
                	try {
                		if (keys != null)
                	        keys.close();
                    } catch (Exception e) {}
                    try {
                        if (stmt != null)
                            stmt.close();
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
             * @param consultar 
             * @return consultarResponse 
             */
             
            public org.example.www.servicioaviso.ConsultarResponse consultar
            (
             org.example.www.servicioaviso.Consultar consultar
            )
            {
            	ConsultarResponse response = new ConsultarResponse();

                Connection conn = null;
                PreparedStatement stmt = null;
                ResultSet rs = null;

                try {
                    int idAviso = consultar.getIdAviso();

                    conn = DBUtil.getConnection();

                    String sql = "SELECT * FROM aviso WHERE id = ?";

                    stmt = conn.prepareStatement(sql);
                    stmt.setInt(1, idAviso);

                    rs = stmt.executeQuery();
                    
                    if (rs.next()) {
                        Aviso aviso = new Aviso();

                        aviso.setIdAviso(rs.getInt("id"));
                        aviso.setTelefono(rs.getString("telefono"));
                        aviso.setLocalizacion(rs.getString("localizacion"));
                        aviso.setDescripcion(rs.getString("descripcion"));
                        int afectados = rs.getInt("afectados");

                        if (rs.wasNull()) {
                            aviso.setAfectados(null);
                        } else {
                        	aviso.setAfectados(java.math.BigInteger.valueOf(afectados));
                        }

                        aviso.setHayFuego(rs.getBoolean("hay_fuego"));
                        aviso.setHayHumo(rs.getBoolean("hay_humo"));
                        aviso.setPersonasAtrapadas(rs.getBoolean("personas_atrapadas"));
                        aviso.setPersonasHeridas(rs.getBoolean("personas_heridas"));
                        aviso.setRiesgoSeguridad(rs.getBoolean("riesgo_seguridad"));
                        aviso.setRiesgoEstructural(rs.getBoolean("riesgo_estructural"));
                        aviso.setAlteracionOrdenPublico(rs.getBoolean("alteracion_orden_publico"));
                        response.setAviso(aviso);
                        response.setOk(true);
                        response.setError("");

                    } else {
                        response.setOk(false);
                        response.setError("No existe el aviso con ID: " + idAviso);
                    }
                    
                } catch (Exception e) {
                    response.setOk(false);
                    response.setError(e.getMessage());
                    e.printStackTrace();
                    
                } finally {
                    try {
                        if (rs != null)
                            rs.close();
                    } catch (Exception e) {}
                    try {
                        if (stmt != null)
                            stmt.close();
                    } catch (Exception e) {}
                    try {
                        if (conn != null)
                            conn.close();
                    } catch (Exception e) {}
                }
                
                return response;
            }
}    