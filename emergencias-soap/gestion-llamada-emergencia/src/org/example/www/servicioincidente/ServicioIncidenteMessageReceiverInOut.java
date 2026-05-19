
/**
 * ServicioIncidenteMessageReceiverInOut.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.3  Built on : Jun 27, 2015 (11:17:49 BST)
 */
        package org.example.www.servicioincidente;

        /**
        *  ServicioIncidenteMessageReceiverInOut message receiver
        */

        public class ServicioIncidenteMessageReceiverInOut extends org.apache.axis2.receivers.AbstractInOutMessageReceiver{


        public void invokeBusinessLogic(org.apache.axis2.context.MessageContext msgContext, org.apache.axis2.context.MessageContext newMsgContext)
        throws org.apache.axis2.AxisFault{

        try {

        // get the implementation class for the Web Service
        Object obj = getTheImplementationObject(msgContext);

        ServicioIncidenteSkeleton skel = (ServicioIncidenteSkeleton)obj;
        //Out Envelop
        org.apache.axiom.soap.SOAPEnvelope envelope = null;
        //Find the axisOperation that has been set by the Dispatch phase.
        org.apache.axis2.description.AxisOperation op = msgContext.getOperationContext().getAxisOperation();
        if (op == null) {
        throw new org.apache.axis2.AxisFault("Operation is not located, if this is doclit style the SOAP-ACTION should specified via the SOAP Action to use the RawXMLProvider");
        }

        java.lang.String methodName;
        if((op.getName() != null) && ((methodName = org.apache.axis2.util.JavaUtils.xmlNameToJavaIdentifier(op.getName().getLocalPart())) != null)){


        

            if("actualizar".equals(methodName)){
                
                org.example.www.servicioincidente.ActualizarResponse actualizarResponse21 = null;
	                        org.example.www.servicioincidente.Actualizar wrappedParam =
                                                             (org.example.www.servicioincidente.Actualizar)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    org.example.www.servicioincidente.Actualizar.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               actualizarResponse21 =
                                                   
                                                   
                                                         skel.actualizar(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), actualizarResponse21, false, new javax.xml.namespace.QName("http://www.example.org/ServicioIncidente/",
                                                    "actualizar"));
                                    } else 

            if("crear".equals(methodName)){
                
                org.example.www.servicioincidente.CrearResponse crearResponse23 = null;
	                        org.example.www.servicioincidente.Crear wrappedParam =
                                                             (org.example.www.servicioincidente.Crear)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    org.example.www.servicioincidente.Crear.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               crearResponse23 =
                                                   
                                                   
                                                         skel.crear(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), crearResponse23, false, new javax.xml.namespace.QName("http://www.example.org/ServicioIncidente/",
                                                    "crear"));
                                    } else 

            if("crearIncidenteOasociarAviso".equals(methodName)){
                
                org.example.www.servicioincidente.CrearIncidenteOasociarAvisoResponse crearIncidenteOasociarAvisoResponse25 = null;
	                        org.example.www.servicioincidente.CrearIncidenteOasociarAviso wrappedParam =
                                                             (org.example.www.servicioincidente.CrearIncidenteOasociarAviso)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    org.example.www.servicioincidente.CrearIncidenteOasociarAviso.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               crearIncidenteOasociarAvisoResponse25 =
                                                   
                                                   
                                                         skel.crearIncidenteOasociarAviso(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), crearIncidenteOasociarAvisoResponse25, false, new javax.xml.namespace.QName("http://www.example.org/ServicioIncidente/",
                                                    "crearIncidenteOasociarAviso"));
                                    } else 

            if("consultar".equals(methodName)){
                
                org.example.www.servicioincidente.ConsultarResponse consultarResponse27 = null;
	                        org.example.www.servicioincidente.Consultar wrappedParam =
                                                             (org.example.www.servicioincidente.Consultar)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    org.example.www.servicioincidente.Consultar.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               consultarResponse27 =
                                                   
                                                   
                                                         skel.consultar(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), consultarResponse27, false, new javax.xml.namespace.QName("http://www.example.org/ServicioIncidente/",
                                                    "consultar"));
                                    } else 

            if("asociarAviso".equals(methodName)){
                
                org.example.www.servicioincidente.AsociarAvisoResponse asociarAvisoResponse29 = null;
	                        org.example.www.servicioincidente.AsociarAviso wrappedParam =
                                                             (org.example.www.servicioincidente.AsociarAviso)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    org.example.www.servicioincidente.AsociarAviso.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               asociarAvisoResponse29 =
                                                   
                                                   
                                                         skel.asociarAviso(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), asociarAvisoResponse29, false, new javax.xml.namespace.QName("http://www.example.org/ServicioIncidente/",
                                                    "asociarAviso"));
                                    
            } else {
              throw new java.lang.RuntimeException("method not found");
            }
        

        newMsgContext.setEnvelope(envelope);
        }
        }
        catch (java.lang.Exception e) {
        throw org.apache.axis2.AxisFault.makeFault(e);
        }
        }
        
        //
            private  org.apache.axiom.om.OMElement  toOM(org.example.www.servicioincidente.Actualizar param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.example.www.servicioincidente.Actualizar.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.example.www.servicioincidente.ActualizarResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.example.www.servicioincidente.ActualizarResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.example.www.servicioincidente.Crear param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.example.www.servicioincidente.Crear.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.example.www.servicioincidente.CrearResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.example.www.servicioincidente.CrearResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.example.www.servicioincidente.CrearIncidenteOasociarAviso param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.example.www.servicioincidente.CrearIncidenteOasociarAviso.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.example.www.servicioincidente.CrearIncidenteOasociarAvisoResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.example.www.servicioincidente.CrearIncidenteOasociarAvisoResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.example.www.servicioincidente.Consultar param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.example.www.servicioincidente.Consultar.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.example.www.servicioincidente.ConsultarResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.example.www.servicioincidente.ConsultarResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.example.www.servicioincidente.AsociarAviso param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.example.www.servicioincidente.AsociarAviso.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.example.www.servicioincidente.AsociarAvisoResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.example.www.servicioincidente.AsociarAvisoResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, org.example.www.servicioincidente.ActualizarResponse param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(org.example.www.servicioincidente.ActualizarResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private org.example.www.servicioincidente.ActualizarResponse wrapactualizar(){
                                org.example.www.servicioincidente.ActualizarResponse wrappedElement = new org.example.www.servicioincidente.ActualizarResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, org.example.www.servicioincidente.CrearResponse param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(org.example.www.servicioincidente.CrearResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private org.example.www.servicioincidente.CrearResponse wrapcrear(){
                                org.example.www.servicioincidente.CrearResponse wrappedElement = new org.example.www.servicioincidente.CrearResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, org.example.www.servicioincidente.CrearIncidenteOasociarAvisoResponse param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(org.example.www.servicioincidente.CrearIncidenteOasociarAvisoResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private org.example.www.servicioincidente.CrearIncidenteOasociarAvisoResponse wrapcrearIncidenteOasociarAviso(){
                                org.example.www.servicioincidente.CrearIncidenteOasociarAvisoResponse wrappedElement = new org.example.www.servicioincidente.CrearIncidenteOasociarAvisoResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, org.example.www.servicioincidente.ConsultarResponse param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(org.example.www.servicioincidente.ConsultarResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private org.example.www.servicioincidente.ConsultarResponse wrapconsultar(){
                                org.example.www.servicioincidente.ConsultarResponse wrappedElement = new org.example.www.servicioincidente.ConsultarResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, org.example.www.servicioincidente.AsociarAvisoResponse param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(org.example.www.servicioincidente.AsociarAvisoResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private org.example.www.servicioincidente.AsociarAvisoResponse wrapasociarAviso(){
                                org.example.www.servicioincidente.AsociarAvisoResponse wrappedElement = new org.example.www.servicioincidente.AsociarAvisoResponse();
                                return wrappedElement;
                         }
                    


        /**
        *  get the default envelope
        */
        private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory){
        return factory.getDefaultEnvelope();
        }


        private  java.lang.Object fromOM(
        org.apache.axiom.om.OMElement param,
        java.lang.Class type,
        java.util.Map extraNamespaces) throws org.apache.axis2.AxisFault{

        try {
        
                if (org.example.www.servicioincidente.Actualizar.class.equals(type)){
                
                        return org.example.www.servicioincidente.Actualizar.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
            
                if (org.example.www.servicioincidente.ActualizarResponse.class.equals(type)){
                
                        return org.example.www.servicioincidente.ActualizarResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
            
                if (org.example.www.servicioincidente.AsociarAviso.class.equals(type)){
                
                        return org.example.www.servicioincidente.AsociarAviso.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
            
                if (org.example.www.servicioincidente.AsociarAvisoResponse.class.equals(type)){
                
                        return org.example.www.servicioincidente.AsociarAvisoResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
            
                if (org.example.www.servicioincidente.Consultar.class.equals(type)){
                
                        return org.example.www.servicioincidente.Consultar.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
            
                if (org.example.www.servicioincidente.ConsultarResponse.class.equals(type)){
                
                        return org.example.www.servicioincidente.ConsultarResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
            
                if (org.example.www.servicioincidente.Crear.class.equals(type)){
                
                        return org.example.www.servicioincidente.Crear.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
            
                if (org.example.www.servicioincidente.CrearIncidenteOasociarAviso.class.equals(type)){
                
                        return org.example.www.servicioincidente.CrearIncidenteOasociarAviso.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
            
                if (org.example.www.servicioincidente.CrearIncidenteOasociarAvisoResponse.class.equals(type)){
                
                        return org.example.www.servicioincidente.CrearIncidenteOasociarAvisoResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
            
                if (org.example.www.servicioincidente.CrearResponse.class.equals(type)){
                
                        return org.example.www.servicioincidente.CrearResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
            
        } catch (java.lang.Exception e) {
        throw org.apache.axis2.AxisFault.makeFault(e);
        }
           return null;
        }



    

        /**
        *  A utility method that copies the namepaces from the SOAPEnvelope
        */
        private java.util.Map getEnvelopeNamespaces(org.apache.axiom.soap.SOAPEnvelope env){
        java.util.Map returnMap = new java.util.HashMap();
        java.util.Iterator namespaceIterator = env.getAllDeclaredNamespaces();
        while (namespaceIterator.hasNext()) {
        org.apache.axiom.om.OMNamespace ns = (org.apache.axiom.om.OMNamespace) namespaceIterator.next();
        returnMap.put(ns.getPrefix(),ns.getNamespaceURI());
        }
        return returnMap;
        }

        private org.apache.axis2.AxisFault createAxisFault(java.lang.Exception e) {
        org.apache.axis2.AxisFault f;
        Throwable cause = e.getCause();
        if (cause != null) {
            f = new org.apache.axis2.AxisFault(e.getMessage(), cause);
        } else {
            f = new org.apache.axis2.AxisFault(e.getMessage());
        }

        return f;
    }

        }//end of class
    