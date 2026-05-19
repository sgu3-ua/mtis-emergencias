/**
 * ServicioGestionLlamadaEmergenciaSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.3  Built on : Jun 27, 2015 (11:17:49 BST)
 */
package org.example.www.serviciogestionllamadaemergencia;
/**
 *  ServicioGestionLlamadaEmergenciaSkeleton java skeleton for the axisService
 */

import org.example.www.tipos.*;
import org.example.www.servicioaviso.ServicioAvisoSkeleton;
import org.example.www.servicioaviso.Crear;
import org.example.www.servicioaviso.CrearResponse;
import org.example.www.servicioincidente.ServicioIncidenteSkeleton;
import org.example.www.servicioincidente.CrearIncidenteOasociarAviso;
import org.example.www.servicioincidente.CrearIncidenteOasociarAvisoResponse;

public class ServicioGestionLlamadaEmergenciaSkeleton{
        
        /**
         * Auto generated method signature
         * 
         * @param gestionarLlamadaEmergencia 
         * @return gestionarLlamadaEmergenciaResponse 
         */
        
        public org.example.www.serviciogestionllamadaemergencia.GestionarLlamadaEmergenciaResponse gestionarLlamadaEmergencia
         (
          org.example.www.serviciogestionllamadaemergencia.GestionarLlamadaEmergencia gestionarLlamadaEmergencia
          )
        {
        	GestionarLlamadaEmergenciaResponse resp = new GestionarLlamadaEmergenciaResponse();
        	Aviso aviso = gestionarLlamadaEmergencia.getAviso();
        	
        	// Crear el aviso
        	ServicioAvisoSkeleton servicioAviso = new ServicioAvisoSkeleton();
        	Crear crearAvisoRequest = new Crear();
        	crearAvisoRequest.setAviso(aviso);
        	CrearResponse avisoResponse = servicioAviso.crear(crearAvisoRequest);
        	
        	if (!avisoResponse.getOk()) {
        		resp.setOk(false);
        		resp.setError(avisoResponse.getError());
        		return resp;
        	}
        	
        	int idAviso = avisoResponse.getIdAviso();
        	
        	// Crear el incidente o asociar el aviso a un incidente ya existente
        	ServicioIncidenteSkeleton servicioIncidente = new ServicioIncidenteSkeleton();
        	CrearIncidenteOasociarAviso crearIncidenteRequest = new CrearIncidenteOasociarAviso();
        	crearIncidenteRequest.setIdAviso(idAviso);
        	CrearIncidenteOasociarAvisoResponse incidenteResponse = servicioIncidente.crearIncidenteOasociarAviso(crearIncidenteRequest);
        	
        	if (!incidenteResponse.getOk()) {
        		resp.setOk(false);
        		resp.setError("Error al crear el incidente o asociar el aviso");
        		return resp;
        	}
        	
        	resp.setOk(true);
        	resp.setIdIncidente(incidenteResponse.getIdIncidente());
        	return resp;
        }
}   