
/**
 * ServicioRecursosSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.3  Built on : Jun 27, 2015 (11:17:49 BST)
 */
    package org.example.www.serviciorecursos;

    import java.sql.*;
    import java.util.ArrayList;
    import java.util.Calendar;
    import java.util.List;
    import org.example.emergencias.types.v1.DatoServicioType;
    import org.example.emergencias.utils.DBUtils;

    /**
     *  ServicioRecursosSkeleton java skeleton for the axisService
     */
    public class ServicioRecursosSkeleton{
        
         /**
          * Auto generated method signature
          * 
                                      * @param obtenerDatos 
              * @return obtenerDatosResponse 
          */
         
                  public org.example.www.serviciorecursos.ObtenerDatosResponse obtenerDatos
                   (
                   org.example.www.serviciorecursos.ObtenerDatos obtenerDatos
                   )
            {
                ObtenerDatosResponse response = new ObtenerDatosResponse();
                String expedienteIdStr = obtenerDatos.getExpedienteId();
                List<DatoServicioType> lista = new ArrayList<>();
                
                try (Connection conn = DBUtils.getConnection()) {
                    // Obtener incidente_id del expediente
                    PreparedStatement psExp = conn.prepareStatement("SELECT incidente_id FROM expediente WHERE id = ?");
                    psExp.setInt(1, Integer.parseInt(expedienteIdStr));
                    ResultSet rsExp = psExp.executeQuery();
                    if (!rsExp.next()) {
                        response.setDatoServicio(lista.toArray(new DatoServicioType[0]));
                        return response;
                    }
                    int incidenteId = rsExp.getInt("incidente_id");
                     
                    // Obtener que servicios requiere el incidente
                    PreparedStatement psInc = conn.prepareStatement(
                        "SELECT requiere_bomberos, requiere_policia, requiere_sanitarios FROM incidente WHERE id = ?");
                    psInc.setInt(1, incidenteId);
                    ResultSet rsInc = psInc.executeQuery();
                    if (!rsInc.next()) {
                        response.setDatoServicio(lista.toArray(new DatoServicioType[0]));
                        return response;
                    }
                    boolean reqBomberos = rsInc.getBoolean("requiere_bomberos");
                    boolean reqPolicia = rsInc.getBoolean("requiere_policia");
                    boolean reqSanitarios = rsInc.getBoolean("requiere_sanitarios");
                     
                    if (reqBomberos) {
                        DatoServicioType ds = obtenerDatoServicio(conn, Integer.parseInt(expedienteIdStr), incidenteId, "BOMBEROS");
                        if (ds != null) lista.add(ds);
                    }
                    if (reqPolicia) {
                        DatoServicioType ds = obtenerDatoServicio(conn, Integer.parseInt(expedienteIdStr), incidenteId, "POLICIA");
                        if (ds != null) lista.add(ds);
                    }
                    if (reqSanitarios) {
                        DatoServicioType ds = obtenerDatoServicio(conn, Integer.parseInt(expedienteIdStr), incidenteId, "HOSPITAL");
                        if (ds != null) lista.add(ds);
                    }
                     
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                response.setDatoServicio(lista.toArray(new DatoServicioType[0]));
                return response;
        }
        
        private DatoServicioType obtenerDatoServicio(Connection conn, int expedienteId, int incidenteId, String servicio) throws SQLException {
            // Primero buscar en dato_servicio del expediente
            PreparedStatement psDs = conn.prepareStatement(
                "SELECT * FROM dato_servicio WHERE expediente_id = ? AND servicio = ?");
            psDs.setInt(1, expedienteId);
            psDs.setString(2, servicio);
            ResultSet rsDs = psDs.executeQuery();
            if (rsDs.next()) {
                return buildDatoServicio(rsDs);
            }
            
            // Si no esta en dato_servicio, construir desde tablas especificas
            DatoServicioType ds = new DatoServicioType();
            ds.setServicio(servicio);
            
            if ("BOMBEROS".equals(servicio)) {
                InfoBomberos info = getInfoBomberos(conn, incidenteId);
                ds.setRecursosUtilizados(info.despliegue + " | " + info.resumen);
                if (info.horaSalida != null) ds.setHoraSalida(info.horaSalida);
                if (info.totalDesplegados > 0) {
                    ds.setActuaciones("Despliegue de bomberos: " + info.totalDesplegados + " vehiculo(s)");
                } else {
                    ds.setActuaciones("Sin despliegue de bomberos registrado");
                }
                
                if (info.totalDesplegados == 0) {
                    ds.setObservaciones("ALERTA: Sin vehiculos de bomberos desplegados para este incidente");
                } else if (info.noDisponible) {
                    ds.setObservaciones("Precaucion: al menos un vehiculo desplegado no estaba disponible");
                } else {
                    ds.setObservaciones("Despliegue de bomberos activo y operativo");
                }
                
            } else if ("POLICIA".equals(servicio)) {
                InfoPolicia info = getInfoPolicia(conn, incidenteId);
                ds.setRecursosUtilizados(info.despliegue + " | " + info.resumen);
                if (info.horaSalida != null) ds.setHoraSalida(info.horaSalida);
                if (info.totalDesplegadas > 0) {
                    ds.setActuaciones("Despliegue policial: " + info.totalDesplegadas + " unidad(es)");
                } else {
                    ds.setActuaciones("Sin despliegue registrado");
                }
                
                if (info.totalDesplegadas == 0) {
                    ds.setObservaciones("ALERTA: Sin unidades desplegadas para este incidente");
                } else if (info.disponible) {
                    ds.setObservaciones("Precaucion: al menos una unidad desplegada no estaba disponible");
                } else {
                    ds.setObservaciones("Despliegue policial activo y operativo");
                }
                
            } else if ("HOSPITAL".equals(servicio)) {
                InfoHospital info = getInfoHospital(conn, incidenteId);
                ds.setRecursosUtilizados(info.hospitales);
                if (info.totalPacientes > 0) {
                    ds.setActuaciones("Atencion sanitaria en curso para " + info.totalPacientes + " paciente(s)");
                } else {
                    ds.setActuaciones("Atencion sanitaria coordinada");
                }
                
                if (info.totalHospitales == 0) {
                    ds.setObservaciones("ALERTA: Sin hospitales asignados al incidente");
                } else if (info.personalReq > info.totalPersonal) {
                    ds.setObservaciones("Precaucion: deficit de personal medico frente a lo requerido");
                } else if (info.camasReq > info.totalCapacidad) {
                    ds.setObservaciones("Precaucion: deficit de camas frente a la capacidad total");
                } else if (info.totalPacientes == 0) {
                    ds.setObservaciones("Sin pacientes registrados en este incidente");
                } else {
                    ds.setObservaciones("Recursos sanitarios adecuados para la atencion");
                }
            }
            
            // Inferir hora de llegada si no esta presente
            if (ds.getHoraLlegada() == null && ds.getHoraSalida() != null) {
                ds.setHoraLlegada(inferirHoraLlegada(ds.getHoraSalida()));
            }
            
            // Persistir en dato_servicio para futuras consultas
            saveDatoServicio(conn, expedienteId, ds);
            
            return ds;
        }
        
        /**
         * Guardar un DatoServicioType
         * @param conn
         * @param expedienteId
         * @param ds
         * @throws SQLException
         */
        private void saveDatoServicio(Connection conn, int expedienteId, DatoServicioType ds) throws SQLException {
            String sqlInsert = "INSERT INTO dato_servicio (expediente_id, servicio, hora_salida, hora_llegada, actuaciones, recursos_utilizados, observaciones) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement psInsert = conn.prepareStatement(sqlInsert);
            psInsert.setInt(1, expedienteId);
            psInsert.setString(2, ds.getServicio());
            if (ds.getHoraSalida() != null) {
                psInsert.setTimestamp(3, new Timestamp(ds.getHoraSalida().getTimeInMillis()));
            } else {
                psInsert.setNull(3, Types.TIMESTAMP);
            }
            if (ds.getHoraLlegada() != null) {
                psInsert.setTimestamp(4, new Timestamp(ds.getHoraLlegada().getTimeInMillis()));
            } else {
                psInsert.setNull(4, Types.TIMESTAMP);
            }
            psInsert.setString(5, ds.getActuaciones());
            psInsert.setString(6, ds.getRecursosUtilizados());
            psInsert.setString(7, ds.getObservaciones());
            psInsert.executeUpdate();
        }
        
        /**
         * Infiere una hora de llegada realista a partir de la hora de salida
         * sumando un offset aleatorio entre 5 y 60 minutos.
         * @param horaSalida
         * @return
         */
        private Calendar inferirHoraLlegada(Calendar horaSalida) {
            if (horaSalida == null) return null;
            Calendar llegada = (Calendar) horaSalida.clone();
            int minutos = 5 + (int)(Math.random() * 56); // rango 5..60
            llegada.add(Calendar.MINUTE, minutos);
            return llegada;
        }
        
        /**
         * Construir un DatoServicioType
         * @param rs
         * @return
         * @throws SQLException
         */
        private DatoServicioType buildDatoServicio(ResultSet rs) throws SQLException {
            DatoServicioType ds = new DatoServicioType();
            ds.setServicio(rs.getString("servicio"));
            Timestamp hs = rs.getTimestamp("hora_salida");
            if (hs != null) {
                Calendar c = Calendar.getInstance();
                c.setTime(hs);
                ds.setHoraSalida(c);
            }
            Timestamp hl = rs.getTimestamp("hora_llegada");
            if (hl != null) {
                Calendar c = Calendar.getInstance();
                c.setTime(hl);
                ds.setHoraLlegada(c);
            } else if (hs != null) {
                Calendar salida = Calendar.getInstance();
                salida.setTime(hs);
                ds.setHoraLlegada(inferirHoraLlegada(salida));
            }
            ds.setActuaciones(rs.getString("actuaciones"));
            ds.setRecursosUtilizados(rs.getString("recursos_utilizados"));
            ds.setObservaciones(rs.getString("observaciones"));
            return ds;
        }
        
        /**
         * Clase que representa la informacin de Policias de la base de datos
         * @author Javier
         *
         */
        private static class InfoPolicia {
            String despliegue = "";
            String resumen = "";
            Calendar horaSalida;
            int totalDesplegadas = 0;
            boolean disponible = false;
        }
        
        /**
         * Clase que representa la informacin de Hospitales de la base de datos
         * @author Javier
         *
         */
        private static class InfoHospital {
            String hospitales = "";
            int totalHospitales = 0;
            int totalPersonal = 0;
            int totalCapacidad = 0;
            int camasReq = 0;
            int personalReq = 0;
            int totalPacientes = 0;
        }
        
        /**
         * Clase que representa la informacion de Bomberos de la base de datos
         * @author Javier
         *
         */
        private static class InfoBomberos {
            String despliegue = "";
            String resumen = "";
            Calendar horaSalida;
            int totalDesplegados = 0;
            boolean noDisponible = false;
        }
        
        /**
         * Obtener info Policia
         * @param conn
         * @param incidenteId
         * @return
         * @throws SQLException
         */
        private InfoPolicia getInfoPolicia(Connection conn, int incidenteId) throws SQLException {
            InfoPolicia info = new InfoPolicia();
            
            // Despliegues del incidente
            PreparedStatement ps = conn.prepareStatement(
                "SELECT up.id, up.comisaria_id, up.cuerpo, up.disponibilidad, rd.hora_despacho " +
                "FROM registro_despliegue rd " +
                "JOIN unidad_policia up ON rd.unidad_policia_id = up.id " +
                "WHERE rd.incidente_id = ?");
            ps.setInt(1, incidenteId);
            ResultSet rs = ps.executeQuery();
            String recursos = "";
            while (rs.next()) {
                info.totalDesplegadas++;
                if (!rs.getBoolean("disponibilidad")) info.disponible = true;
                if (recursos.length() > 0) recursos = recursos + ", ";
                recursos = recursos + "Unidad " + rs.getInt("id") + " " + rs.getString("cuerpo");
                if (info.horaSalida == null && rs.getTimestamp("hora_despacho") != null) {
                    info.horaSalida = Calendar.getInstance();
                    info.horaSalida.setTime(rs.getTimestamp("hora_despacho"));
                }
            }
            if (recursos.length() > 0) {
                info.despliegue = "Desplegadas: " + info.totalDesplegadas + " unidades - " + recursos;
            } else {
                info.despliegue = "Sin despliegue registrado";
            }
            
            // Resumen del sistema
            PreparedStatement psResumen = conn.prepareStatement(
                "SELECT COUNT(*) as total, SUM(CASE WHEN disponibilidad = TRUE THEN 1 ELSE 0 END) as disponibles FROM unidad_policia");
            ResultSet rsResumen = psResumen.executeQuery();
            if (rsResumen.next()) {
                info.resumen = "Total sistema: " + rsResumen.getInt("total") + " (disponibles:" + rsResumen.getInt("disponibles") + ")";
            } else {
                info.resumen = "Total sistema: 0";
            }
            
            return info;
        }
        
        /**
         * Obtener info Bomberos
         * @param conn
         * @param incidenteId
         * @return
         * @throws SQLException
         */
        private InfoBomberos getInfoBomberos(Connection conn, int incidenteId) throws SQLException {
            InfoBomberos info = new InfoBomberos();
            
            // Despliegues de bomberos del incidente
            PreparedStatement ps = conn.prepareStatement(
                "SELECT vb.idVehiculo, vb.tipo, vb.estado, vb.matricula, pb.nombre as parque, rdb.hora_despacho " +
                "FROM registro_despliegue_bomberos rdb " +
                "JOIN vehiculo_bomberos vb ON rdb.vehiculo_bomberos_id = vb.idVehiculo " +
                "JOIN parque_bomberos pb ON vb.parque_id = pb.id " +
                "WHERE rdb.incidente_id = ?");
            ps.setInt(1, incidenteId);
            ResultSet rs = ps.executeQuery();
            String recursos = "";
            while (rs.next()) {
                info.totalDesplegados++;
                String estado = rs.getString("estado");
                if (!"disponible".equalsIgnoreCase(estado)) info.noDisponible = true;
                if (recursos.length() > 0) recursos = recursos + ", ";
                recursos = recursos + rs.getString("tipo") + " " + rs.getString("matricula") + " (" + rs.getString("parque") + ")";
                if (info.horaSalida == null && rs.getTimestamp("hora_despacho") != null) {
                    info.horaSalida = Calendar.getInstance();
                    info.horaSalida.setTime(rs.getTimestamp("hora_despacho"));
                }
            }
            if (recursos.length() > 0) {
                info.despliegue = "Desplegados: " + info.totalDesplegados + " vehiculos - " + recursos;
            } else {
                info.despliegue = "Sin despliegue de bomberos registrado";
            }
            
            // Resumen del sistema
            PreparedStatement psResumen = conn.prepareStatement(
                "SELECT COUNT(*) as total, SUM(CASE WHEN estado = 'disponible' THEN 1 ELSE 0 END) as disponibles FROM vehiculo_bomberos");
            ResultSet rsResumen = psResumen.executeQuery();
            if (rsResumen.next()) {
                info.resumen = "Total sistema: " + rsResumen.getInt("total") + " (disponibles:" + rsResumen.getInt("disponibles") + ")";
            } else {
                info.resumen = "Total sistema: 0";
            }
            
            return info;
        }
        
        /**
         * Obtener info hospitales agrupada por hospital con recursos y pacientes reales
         * @param conn
         * @param incidenteId
         * @return
         * @throws SQLException
         */
        private InfoHospital getInfoHospital(Connection conn, int incidenteId) throws SQLException {
            InfoHospital info = new InfoHospital();
            String detalle = "";

            // Consulta unica que trae recurso + hospital + paciente en una fila por recurso usado
            PreparedStatement ps = conn.prepareStatement(
                "SELECT rh.descripcion, rh.codigo, rh.camasRequeridas, rh.personalRequerido, " +
                "h.nombre as hospital, h.capacidad, h.personal_medico, " +
                "p.nombre as paciente, p.sexo, p.alergias " +
                "FROM incidente_hospital ih " +
                "JOIN hospital h ON ih.hospital_id = h.id " +
                "LEFT JOIN paciente p ON ih.paciente_id = p.id " +
                "JOIN incidente_hospital_recursosHospital ihr ON ih.id = ihr.incidente_hospital_id " +
                "JOIN recursosHospital rh ON ihr.recurso_id = rh.id " +
                "WHERE ih.incidente_id = ?");
            ps.setInt(1, incidenteId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                info.camasReq += rs.getInt("camasRequeridas");
                info.personalReq += rs.getInt("personalRequerido");

                if (!detalle.isEmpty()) detalle = detalle + " | ";
                detalle = detalle + rs.getString("descripcion")
                       + " (camas: " + rs.getInt("camasRequeridas")
                       + ", personal requerido: " + rs.getInt("personalRequerido") + ")"
                       + " del " + rs.getString("hospital")
                       + " (capacidad: " + rs.getInt("capacidad")
                       + ", personal: " + rs.getInt("personal_medico") + ")";

                String paciente = rs.getString("paciente");
                if (paciente != null) {
                    detalle = detalle + " con el paciente: " + paciente
                           + " (sexo: " + rs.getString("sexo")
                           + ", alergias: " + rs.getString("alergias") + ")";
                } else {
                    detalle = detalle + ": Paciente: Sin paciente asignado";
                }
            }

            // Totales para las observaciones (consulta auxiliar ligera)
            PreparedStatement psTot = conn.prepareStatement(
                "SELECT COUNT(DISTINCT h.id) as totalHosp, " +
                "SUM(DISTINCT h.capacidad) as totalCap, " +
                "SUM(DISTINCT h.personal_medico) as totalPers, " +
                "COUNT(DISTINCT p.id) as totalPac " +
                "FROM incidente_hospital ih " +
                "JOIN hospital h ON ih.hospital_id = h.id " +
                "LEFT JOIN paciente p ON ih.paciente_id = p.id " +
                "WHERE ih.incidente_id = ?");
            psTot.setInt(1, incidenteId);
            ResultSet rsTot = psTot.executeQuery();
            if (rsTot.next()) {
                info.totalHospitales = rsTot.getInt("totalHosp");
                info.totalCapacidad = rsTot.getInt("totalCap");
                info.totalPersonal = rsTot.getInt("totalPers");
                info.totalPacientes = rsTot.getInt("totalPac");
            }

            info.hospitales = detalle;
            if (info.hospitales.isEmpty()) {
                info.hospitales = "Sin hospitales asignados";
            }

            return info;
        }
     }
      
