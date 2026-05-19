/**
 * ServicioIncidenteSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.3  Built on : Jun 27, 2015 (11:17:49 BST)
 */
package org.example.www.servicioincidente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import util.DBUtil;
import org.example.www.tipos.*;
import org.example.www.servicioaviso.ServicioAvisoSkeleton;
import org.example.www.servicioclasificacionincidente.ServicioClasificacionIncidenteSkeleton;

/**
 *  ServicioIncidenteSkeleton java skeleton for the axisService
 */
public class ServicioIncidenteSkeleton{
        
         
        /**
         * Auto generated method signature
         * 
         * @param actualizar 
         * @return actualizarResponse 
         */
        
        public org.example.www.servicioincidente.ActualizarResponse actualizar
         (
         org.example.www.servicioincidente.Actualizar actualizar
         )
        {
        	ActualizarResponse response = new ActualizarResponse();

            Connection conn = null;
            PreparedStatement stmt = null;

            try {
                Incidente incidente = actualizar.getIncidente();

                conn = DBUtil.getConnection();

                String sql =
                        "UPDATE incidente SET " +
                        "requiere_bomberos=?, " +
                        "requiere_policia=?, " +
                        "requiere_sanitarios=?, " +
                        "urgencia=?, " +
                        "descripcion=?, " +
                        "localizacion=?, " +
                        "estado=? " +
                        "WHERE id=?";

                stmt = conn.prepareStatement(sql);

                stmt.setBoolean(
                        1,
                        incidente.getTipoIncidente()
                                  .getRequiereBomberos());

                stmt.setBoolean(
                        2,
                        incidente.getTipoIncidente()
                                  .getRequierePolicia());

                stmt.setBoolean(
                        3,
                        incidente.getTipoIncidente()
                                  .getRequiereSanitarios());

                stmt.setString(
                        4,
                        incidente.getNivelUrgencia()
                                  .getValue());

                stmt.setString(
                        5,
                        incidente.getDescripcion());

                stmt.setString(
                        6,
                        incidente.getLocalizacion());

                stmt.setString(
                        7,
                        incidente.getEstado()
                                  .getValue());

                stmt.setInt(
                        8,
                        incidente.getIdIncidente());

                int filas = stmt.executeUpdate();

                if (filas > 0) {
                    response.setOk(true);

                } else {
                    response.setOk(false);
                    response.setError("No existe el incidente");
                }

            } catch (Exception e) {
                response.setOk(false);
                response.setError(e.getMessage());
                e.printStackTrace();

            } finally {

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
         * @param crear 
         * @return crearResponse 
         */
        
        public org.example.www.servicioincidente.CrearResponse crear
         (
         org.example.www.servicioincidente.Crear crear
         )
        {
        	CrearResponse response = new CrearResponse();

            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet keys = null;

            try {
                Incidente incidente = crear.getIncidente();

                conn = DBUtil.getConnection();

                String sql = "INSERT INTO incidente (" +
                        "requiere_bomberos, " +
                        "requiere_policia, " +
                        "requiere_sanitarios, " +
                        "urgencia, " +
                        "descripcion, " +
                        "localizacion, " +
                        "estado" +
                        ") VALUES (?, ?, ?, ?, ?, ?, ?)";
                
                stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

                stmt.setBoolean(1,
                        incidente.getTipoIncidente()
                                  .getRequiereBomberos());

                stmt.setBoolean(2,
                        incidente.getTipoIncidente()
                                  .getRequierePolicia());

                stmt.setBoolean(3,
                        incidente.getTipoIncidente()
                                  .getRequiereSanitarios());

                stmt.setString(4, incidente.getNivelUrgencia().toString());
                stmt.setString(5, incidente.getDescripcion());
                stmt.setString(6, incidente.getLocalizacion());
                stmt.setString(7, incidente.getEstado().toString());

                stmt.executeUpdate();
                
                // Obtener el id del incidente recien creado para poder devolverlo
                keys = stmt.getGeneratedKeys();

                if (keys.next()) {
                	response.setIdIncidente(keys.getInt(1));
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
         * @param crearIncidenteOasociarAviso 
         * @return crearIncidenteOasociarAvisoResponse 
         */
        
        public org.example.www.servicioincidente.CrearIncidenteOasociarAvisoResponse crearIncidenteOasociarAviso
         (
         org.example.www.servicioincidente.CrearIncidenteOasociarAviso crearIncidenteOasociarAviso
         )
        {
        	CrearIncidenteOasociarAvisoResponse response = new CrearIncidenteOasociarAvisoResponse();

        	try {
                int idAviso = crearIncidenteOasociarAviso.getIdAviso();

                // 1. Consultar el aviso
                ServicioAvisoSkeleton servicioAviso = new ServicioAvisoSkeleton();
                org.example.www.servicioaviso.Consultar consultarRequest = new org.example.www.servicioaviso.Consultar();
                consultarRequest.setIdAviso(idAviso);
                org.example.www.servicioaviso.ConsultarResponse avisoResponse = servicioAviso.consultar(consultarRequest);

                if (avisoResponse == null || !avisoResponse.getOk() || avisoResponse.getAviso() == null) {
                	response.setOk(false);
                    response.setError("No existe el aviso con id " + idAviso);
                    return response;
                }

                Aviso aviso = avisoResponse.getAviso();
                
                // Llamar al servicio de clasificacion de incidente
                ServicioClasificacionIncidenteSkeleton servicioClasificacion = new ServicioClasificacionIncidenteSkeleton();
                
                // Determinar el tipo de incidente
                org.example.www.servicioclasificacionincidente.DeterminarTipoIncidente determinarTipoIncidenteRequest = new org.example.www.servicioclasificacionincidente.DeterminarTipoIncidente();
                determinarTipoIncidenteRequest.setIdAviso(idAviso);
                org.example.www.servicioclasificacionincidente.DeterminarTipoIncidenteResponse tipoIncidenteResponse = servicioClasificacion.determinarTipoIncidente(determinarTipoIncidenteRequest);

                if (tipoIncidenteResponse == null || tipoIncidenteResponse.getTipoIncidente() == null) {
                	response.setOk(false);
                    response.setError("No se pudo determinar el tipo de incidente");
                    return response;
                }
                
                TipoIncidente tipoIncidente = tipoIncidenteResponse.getTipoIncidente();    
                    
                // 2. Comprobar si ya existe un incidente relacionado con el aviso
                Connection conn = null;
                PreparedStatement ps = null;
                String sql = 
                		"SELECT id " +
                        "FROM incidente " +
                        "WHERE requiere_bomberos = ? " +
                          "AND requiere_policia = ? " +
                          "AND requiere_sanitarios = ? " +
                          "AND estado = 'ACTIVO' " +
                          "AND localizacion LIKE ? " +
                        "LIMIT 1";

            	conn = DBUtil.getConnection();
            	ps = conn.prepareStatement(sql);
	                    
            	ps.setBoolean(1, tipoIncidente.getRequiereBomberos());
                ps.setBoolean(2, tipoIncidente.getRequierePolicia());
                ps.setBoolean(3, tipoIncidente.getRequiereSanitarios());
                ps.setString(4, "%" + aviso.getLocalizacion() + "%");

                ResultSet rs = ps.executeQuery();
                
                //
                int idIncidente;
                
                // Si existe, quedarse con el id de ese incidente
                if (rs.next()) {
                	idIncidente = rs.getInt("id");
                } 
	            
                // En caso de que no exista, crear un nuevo incidente a partir de la info del aviso
                else {
                	Incidente nuevoIncidente = new Incidente();
                    nuevoIncidente.setDescripcion(aviso.getDescripcion());
                    nuevoIncidente.setEstado(
                            EstadoIncidente.Factory.fromValue("ACTIVO")
                    );
                    nuevoIncidente.setLocalizacion(aviso.getLocalizacion());
                    nuevoIncidente.setTipoIncidente(tipoIncidente);
                    
                    // Determinar la urgencia
                    org.example.www.servicioclasificacionincidente.DeterminarUrgenciaIncidente determinarUrgenciaIncidenteRequest = new org.example.www.servicioclasificacionincidente.DeterminarUrgenciaIncidente();
                    determinarUrgenciaIncidenteRequest.setIdAviso(idAviso);
                    org.example.www.servicioclasificacionincidente.DeterminarUrgenciaIncidenteResponse urgenciaIncidenteResponse = servicioClasificacion.determinarUrgenciaIncidente(determinarUrgenciaIncidenteRequest);
                    
                    if (urgenciaIncidenteResponse == null || urgenciaIncidenteResponse.getNivelUrgencia() == null) {
                        throw new Exception("No se pude determinar la urgencia del incidente para el aviso con id " + idAviso);
                    }
                    
                    NivelUrgencia urgencia =
                            NivelUrgencia.Factory.fromValue(
                                    urgenciaIncidenteResponse.getNivelUrgencia());
                    
                    nuevoIncidente.setNivelUrgencia(urgencia);

                    // Crear incidente
                    Crear crearRequest = new Crear();
                    crearRequest.setIncidente(nuevoIncidente);
                    CrearResponse crearResponse = crear(crearRequest);

                    if (crearResponse == null || !crearResponse.getOk()) {
                        throw new Exception(
                                crearResponse != null
                                        ? crearResponse.getError()
                                        : "Error al crear incidente"
                        );
                    }

                    idIncidente = crearResponse.getIdIncidente();
                }
                
                // 3. Asociar aviso al incidente
                AsociarAviso asociarRequest = new AsociarAviso();
                asociarRequest.setIdAviso(idAviso);
                asociarRequest.setIdIncidente(idIncidente);

                AsociarAvisoResponse asociarResponse = asociarAviso(asociarRequest);

                if (asociarResponse == null || !asociarResponse.getOk()) {
                    throw new Exception(
                            asociarResponse != null
                                    ? asociarResponse.getError()
                                    : "No se pudo asociar el aviso al incidente"
                    );
                }
                
                // 4. Devolver resultado
                response.setOk(true);
                response.setIdIncidente(idIncidente);
                return response;
                
        	} catch (Exception e) {
        		response.setOk(false);
        	    response.setError("Error interno: " + e.getMessage());
        	    return response;
            }
        }
     
         
        /**
         * Auto generated method signature
         * 
         * @param consultar 
         * @return consultarResponse 
         */
        
        public org.example.www.servicioincidente.ConsultarResponse consultar
         (
         org.example.www.servicioincidente.Consultar consultar
         )
        {
        	ConsultarResponse response = new ConsultarResponse();

            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            PreparedStatement stmtAvisos = null;
            ResultSet rsAvisos = null;

            try {
                conn = DBUtil.getConnection();

                String sql = "SELECT * FROM incidente WHERE id = ?";

                stmt = conn.prepareStatement(sql);

                stmt.setInt(1, consultar.getIdIncidente());

                rs = stmt.executeQuery();

                if (rs.next()) {
                    Incidente incidente = new Incidente();

                    incidente.setIdIncidente(rs.getInt("id"));

                    incidente.setNivelUrgencia(
                	    	NivelUrgencia.Factory
                	        			   .fromValue(rs.getString("urgencia")));

                    incidente.setDescripcion(
                            rs.getString("descripcion"));

                    incidente.setLocalizacion(
                            rs.getString("localizacion"));

                    incidente.setEstado(
                    	    	EstadoIncidente.Factory
                    	        			   .fromValue(rs.getString("estado")));

                    TipoIncidente tipo = new TipoIncidente();

                    tipo.setRequiereBomberos(
                            rs.getBoolean("requiere_bomberos"));

                    tipo.setRequierePolicia(
                            rs.getBoolean("requiere_policia"));

                    tipo.setRequiereSanitarios(
                            rs.getBoolean("requiere_sanitarios"));

                    incidente.setTipoIncidente(tipo);

                    // Cargar avisos asociados
                    ArrayList<Aviso> avisos = new ArrayList<Aviso>();

                    stmtAvisos = conn.prepareStatement(

                            "SELECT a.* " +
                            "FROM aviso a " +
                            "INNER JOIN incidente_aviso ia " +
                            "ON a.id = ia.aviso_id " +
                            "WHERE ia.incidente_id = ?");

                    stmtAvisos.setInt(1,
                            rs.getInt("id"));

                    rsAvisos = stmtAvisos.executeQuery();

                    while (rsAvisos.next()) {
                        Aviso aviso = new Aviso();

                        aviso.setIdAviso(
                        		rsAvisos.getInt("id"));

                        aviso.setTelefono(
                                rsAvisos.getString("telefono"));

                        aviso.setLocalizacion(
                                rsAvisos.getString("localizacion"));

                        aviso.setDescripcion(
                                rsAvisos.getString("descripcion"));

                        int afectados =
                                rsAvisos.getInt("afectados");

                        if (!rsAvisos.wasNull()) {

                            aviso.setAfectados(
                                    java.math.BigInteger
                                    .valueOf(afectados));
                        }

                        aviso.setHayFuego(
                                rsAvisos.getBoolean("hay_fuego"));

                        aviso.setHayHumo(
                                rsAvisos.getBoolean("hay_humo"));

                        aviso.setPersonasAtrapadas(
                                rsAvisos.getBoolean("personas_atrapadas"));

                        aviso.setPersonasHeridas(
                                rsAvisos.getBoolean("personas_heridas"));

                        aviso.setRiesgoSeguridad(
                                rsAvisos.getBoolean("riesgo_seguridad"));
                        
                        aviso.setRiesgoEstructural(
                                rsAvisos.getBoolean("riesgo_estructural"));

                        aviso.setAlteracionOrdenPublico(
                                rsAvisos.getBoolean("alteracion_orden_publico"));

                        avisos.add(aviso);
                    }

                    incidente.setAvisos(
                            avisos.toArray(new Aviso[0]));

                    response.setIncidente(incidente);

                    response.setOk(true);

                } else {
                    response.setOk(false);
                    response.setError("No existe el incidente");
                }
                
            } catch (Exception e) {
                response.setOk(false);
                response.setError(e.getMessage());
                e.printStackTrace();

            } finally {
            	try{
            		if (rsAvisos != null)
            	        rsAvisos.close();
            	} catch (Exception e) {}
            	try  {
            		if (stmtAvisos != null)
            	        stmtAvisos.close();
            	} catch (Exception e) {}
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
     
         
        /**
         * Auto generated method signature
         * 
         * @param asociarAviso 
         * @return asociarAvisoResponse 
         */
        
        public org.example.www.servicioincidente.AsociarAvisoResponse asociarAviso
         (
         org.example.www.servicioincidente.AsociarAviso asociarAviso
         )
        {
        	AsociarAvisoResponse response = new AsociarAvisoResponse();

            Connection conn = null;
            PreparedStatement stmt = null;

            try {
                conn = DBUtil.getConnection();

                String sql =
                        "INSERT INTO incidente_aviso " +
                        "(incidente_id, aviso_id) " +
                        "VALUES (?, ?)";

                stmt = conn.prepareStatement(sql);

                stmt.setInt(
                        1,
                        asociarAviso.getIdIncidente());

                stmt.setInt(
                        2,
                        asociarAviso.getIdAviso());

                stmt.executeUpdate();

                response.setOk(true);

            } catch (Exception e) {
                response.setOk(false);
                response.setError(e.getMessage());
                e.printStackTrace();

            } finally {
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