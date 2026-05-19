
/**
 * ServicioExpedienteSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.3  Built on : Jun 27, 2015 (11:17:49 BST)
 */
    package org.example.www.servicioexpediente;

    import java.sql.*;
    import java.util.ArrayList;
    import java.util.List;
    import org.example.emergencias.types.v1.DatoServicioType;
    import org.example.emergencias.types.v1.ExpedienteType;
    import org.example.emergencias.utils.DBUtils;

    /**
     *  ServicioExpedienteSkeleton java skeleton for the axisService
     */
    public class ServicioExpedienteSkeleton{
        
         /**
          * Auto generated method signature
          * 
          * @param crear 
          * @return crearResponse 
          */
         public org.example.www.servicioexpediente.CrearResponse crear
          (
          org.example.www.servicioexpediente.Crear crear
          )
         {
        	 CrearResponse response = new CrearResponse();
        	 ExpedienteType exp = crear.getExpediente();
        	 boolean creado = false;
        	 int expedienteId = -1;
        	 String error = "";

        	 try (Connection conn = DBUtils.getConnection()) {
        	     conn.setAutoCommit(false);

        	     try {
        	         // 1) Buscar si ya existe el expediente por incidente_id
        	         String sqlSel = "SELECT id FROM expediente WHERE incidente_id = ?";
        	         try (PreparedStatement psSel = conn.prepareStatement(sqlSel)) {
        	             psSel.setInt(1, Integer.parseInt(exp.getIncidenteId()));
        	             try (ResultSet rsSel = psSel.executeQuery()) {
        	                 if (rsSel.next()) {
        	                     expedienteId = rsSel.getInt("id");
        	                     creado = true; // ya existe, se reutiliza
        	                 } else {
        	                     // Si no existe, crear el expediente
        	                     String sqlIns = "INSERT INTO expediente (incidente_id, clasificacion, estado) VALUES (?, ?, ?)";
        	                     try (PreparedStatement psIns = conn.prepareStatement(sqlIns, Statement.RETURN_GENERATED_KEYS)) {
        	                         psIns.setInt(1, Integer.parseInt(exp.getIncidenteId()));
        	                         psIns.setString(2, exp.getClasificacion());
        	                         psIns.setString(3, exp.getEstado() != null ? exp.getEstado() : "ACTIVO");

        	                         int filas = psIns.executeUpdate();
        	                         if (filas > 0) {
        	                             try (ResultSet rs = psIns.getGeneratedKeys()) {
        	                                 if (rs.next()) {
        	                                     expedienteId = rs.getInt(1);
        	                                     creado = true;
        	                                 } else {
        	                                     throw new SQLException("No se pudo obtener el ID generado del expediente");
        	                                 }
        	                             }
        	                         } else {
        	                             throw new SQLException("No se ha podido crear el expediente");
        	                         }
        	                     }
        	                 }
        	             }
        	         }

        	         // Insertar dato_servicio si existe
        	         if (expedienteId > 0 && exp.getDatoServicio() != null && exp.getDatoServicio().length > 0) {
        	             String sqlDs = "INSERT INTO dato_servicio (expediente_id, servicio, hora_salida, hora_llegada, actuaciones, recursos_utilizados, observaciones) VALUES (?, ?, ?, ?, ?, ?, ?)";
        	             try (PreparedStatement psDs = conn.prepareStatement(sqlDs)) {
        	                 for (DatoServicioType ds : exp.getDatoServicio()) {
        	                     psDs.setInt(1, expedienteId);
        	                     psDs.setString(2, ds.getServicio());

        	                     if (ds.getHoraSalida() != null) {
        	                         psDs.setTimestamp(3, new Timestamp(ds.getHoraSalida().getTimeInMillis()));
        	                     } else {
        	                         psDs.setNull(3, Types.TIMESTAMP);
        	                     }

        	                     if (ds.getHoraLlegada() != null) {
        	                         psDs.setTimestamp(4, new Timestamp(ds.getHoraLlegada().getTimeInMillis()));
        	                     } else {
        	                         psDs.setNull(4, Types.TIMESTAMP);
        	                     }

        	                     psDs.setString(5, ds.getActuaciones());
        	                     psDs.setString(6, ds.getRecursosUtilizados());
        	                     psDs.setString(7, ds.getObservaciones());
        	                     psDs.addBatch();
        	                 }
        	                 psDs.executeBatch();
        	             }
        	         }

        	         conn.commit();
        	     } catch (Exception e) {
        	         conn.rollback();
        	         throw e;
        	     }
        	 } catch (Exception e) {
        	     e.printStackTrace();
        	     creado = false;
        	     error = e.getMessage();
        	 }

        	 response.setExpedienteId(expedienteId);
        	 response.setCreado(creado);
        	 response.setError(error);
        	 return response;
         }
     
         /**
          * Auto generated method signature
          * 
          * @param clasificar 
          * @return clasificarResponse 
          */
         public org.example.www.servicioexpediente.ClasificarResponse clasificar
          (
          org.example.www.servicioexpediente.Clasificar clasificar
          )
         {
            ClasificarResponse response = new ClasificarResponse();
            boolean clasificado = false;
            try (Connection conn = DBUtils.getConnection();
                 PreparedStatement ps = conn.prepareStatement("UPDATE expediente SET clasificacion = ? WHERE id = ?")) {
                ps.setString(1, clasificar.getClasificacion());
                ps.setInt(2, Integer.parseInt(clasificar.getExpedienteId()));
                clasificado = ps.executeUpdate() > 0;
            } catch (Exception e) {
                e.printStackTrace();
            }
            response.setClasificado(clasificado);
            return response;
         }
     
         /**
          * Auto generated method signature
          * 
          * @param obtener 
          * @return obtenerResponse 
          */
         public org.example.www.servicioexpediente.ObtenerResponse obtener
          (
          org.example.www.servicioexpediente.Obtener obtener
          )
         {
            ObtenerResponse response = new ObtenerResponse();
            ExpedienteType exp = new ExpedienteType();
            String expIdStr = obtener.getExpedienteId();
            try (Connection conn = DBUtils.getConnection()) {
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM expediente WHERE id = ?");
                ps.setInt(1, Integer.parseInt(expIdStr));
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    exp.setExpedienteId(String.valueOf(rs.getInt("id")));
                    exp.setIncidenteId(String.valueOf(rs.getInt("incidente_id")));
                    exp.setClasificacion(rs.getString("clasificacion"));
                    exp.setEstado(rs.getString("estado"));
                    
                    PreparedStatement psDs = conn.prepareStatement("SELECT * FROM dato_servicio WHERE expediente_id = ?");
                    psDs.setInt(1, rs.getInt("id"));
                    ResultSet rsDs = psDs.executeQuery();
                    List<DatoServicioType> lista = new ArrayList<>();
                    while (rsDs.next()) {
                        DatoServicioType ds = new DatoServicioType();
                        ds.setServicio(rsDs.getString("servicio"));
                        Timestamp hs = rsDs.getTimestamp("hora_salida");
                        if (hs != null) {
                            java.util.Calendar c = java.util.Calendar.getInstance();
                            c.setTime(hs);
                            ds.setHoraSalida(c);
                        }
                        Timestamp hl = rsDs.getTimestamp("hora_llegada");
                        if (hl != null) {
                            java.util.Calendar c = java.util.Calendar.getInstance();
                            c.setTime(hl);
                            ds.setHoraLlegada(c);
                        }
                        ds.setActuaciones(rsDs.getString("actuaciones"));
                        ds.setRecursosUtilizados(rsDs.getString("recursos_utilizados"));
                        ds.setObservaciones(rsDs.getString("observaciones"));
                        lista.add(ds);
                    }
                    if (!lista.isEmpty()) {
                        exp.setDatoServicio(lista.toArray(new DatoServicioType[0]));
                    }
                } else {
                    exp.setExpedienteId("-1");
                    exp.setIncidenteId("-1");
                }
            } catch (Exception e) {
                exp.setExpedienteId("-1");
                exp.setIncidenteId("-1");
            }
            response.setExpediente(exp);
            return response;
         }
     
         /**
          * Auto generated method signature
          * 
          * @param archivar 
          * @return archivarResponse 
          */
         public org.example.www.servicioexpediente.ArchivarResponse archivar
          (
          org.example.www.servicioexpediente.Archivar archivar
          )
         {
            ArchivarResponse response = new ArchivarResponse();
            boolean archivado = false;
            try (Connection conn = DBUtils.getConnection();
                 PreparedStatement ps = conn.prepareStatement("UPDATE expediente SET estado = 'ARCHIVADO', fecha_cierre = NOW() WHERE id = ?")) {
                ps.setInt(1, Integer.parseInt(archivar.getExpedienteId()));
                archivado = ps.executeUpdate() > 0;
            } catch (Exception e) {
                e.printStackTrace();
            }
            response.setArchivado(archivado);
            return response;
         }
     }
     