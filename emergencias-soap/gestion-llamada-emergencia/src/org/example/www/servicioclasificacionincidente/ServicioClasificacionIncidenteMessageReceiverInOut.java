
/**
 * ServicioClasificacionIncidenteMessageReceiverInOut.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.3  Built on : Jun 27, 2015 (11:17:49 BST)
 */
        package org.example.www.servicioclasificacionincidente;

        /**
        *  ServicioClasificacionIncidenteMessageReceiverInOut message receiver
        */

        public class ServicioClasificacionIncidenteMessageReceiverInOut extends org.apache.axis2.receivers.AbstractInOutMessageReceiver{


        public void invokeBusinessLogic(org.apache.axis2.context.MessageContext msgContext, org.apache.axis2.context.MessageContext newMsgContext)
        throws org.apache.axis2.AxisFault{

        try {

        // get the implementation class for the Web Service
        Object obj = getTheImplementationObject(msgContext);

        ServicioClasificacionIncidenteSkeleton skel = (ServicioClasificacionIncidenteSkeleton)obj;
        //Out Envelop
        org.apache.axiom.soap.SOAPEnvelope envelope = null;
        //Find the axisOperation that has been set by the Dispatch phase.
        org.apache.axis2.description.AxisOperation op = msgContext.getOperationContext().getAxisOperation();
        if (op == null) {
        throw new org.apache.axis2.AxisFault("Operation is not located, if this is doclit style the SOAP-ACTION should specified via the SOAP Action to use the RawXMLProvider");
        }

        java.lang.String methodName;
        if((op.getName() != null) && ((methodName = org.apache.axis2.util.JavaUtils.xmlNameToJavaIdentifier(op.getName().getLocalPart())) != null)){


        

            if("determinarUrgenciaIncidente".equals(methodName)){
                
                org.example.www.servicioclasificacionincidente.DeterminarUrgenciaIncidenteResponse determinarUrgenciaIncidenteResponse9 = null;
	                        org.example.www.servicioclasificacionincidente.DeterminarUrgenciaIncidente wrappedParam =
                                                             (org.example.www.servicioclasificacionincidente.DeterminarUrgenciaIncidente)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    org.example.www.servicioclasificacionincidente.DeterminarUrgenciaIncidente.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               determinarUrgenciaIncidenteResponse9 =
                                                   
                                                   
                                                         skel.determinarUrgenciaIncidente(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), determinarUrgenciaIncidenteResponse9, false, new javax.xml.namespace.QName("http://www.example.org/ServicioClasificacionIncidente/",
                                                    "determinarUrgenciaIncidente"));
                                    } else 

            if("determinarTipoIncidente".equals(methodName)){
                
                org.example.www.servicioclasificacionincidente.DeterminarTipoIncidenteResponse determinarTipoIncidenteResponse11 = null;
	                        org.example.www.servicioclasificacionincidente.DeterminarTipoIncidente wrappedParam =
                                                             (org.example.www.servicioclasificacionincidente.DeterminarTipoIncidente)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    org.example.www.servicioclasificacionincidente.DeterminarTipoIncidente.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               determinarTipoIncidenteResponse11 =
                                                   
                                                   
                                                         skel.determinarTipoIncidente(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), determinarTipoIncidenteResponse11, false, new javax.xml.namespace.QName("http://www.example.org/ServicioClasificacionIncidente/",
                                                    "determinarTipoIncidente"));
                                    
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
            private  org.apache.axiom.om.OMElement  toOM(org.example.www.servicioclasificacionincidente.DeterminarUrgenciaIncidente param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.example.www.servicioclasificacionincidente.DeterminarUrgenciaIncidente.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.example.www.servicioclasificacionincidente.DeterminarUrgenciaIncidenteResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.example.www.servicioclasificacionincidente.DeterminarUrgenciaIncidenteResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.example.www.servicioclasificacionincidente.DeterminarTipoIncidente param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.example.www.servicioclasificacionincidente.DeterminarTipoIncidente.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.example.www.servicioclasificacionincidente.DeterminarTipoIncidenteResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.example.www.servicioclasificacionincidente.DeterminarTipoIncidenteResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, org.example.www.servicioclasificacionincidente.DeterminarUrgenciaIncidenteResponse param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(org.example.www.servicioclasificacionincidente.DeterminarUrgenciaIncidenteResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private org.example.www.servicioclasificacionincidente.DeterminarUrgenciaIncidenteResponse wrapdeterminarUrgenciaIncidente(){
                                org.example.www.servicioclasificacionincidente.DeterminarUrgenciaIncidenteResponse wrappedElement = new org.example.www.servicioclasificacionincidente.DeterminarUrgenciaIncidenteResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, org.example.www.servicioclasificacionincidente.DeterminarTipoIncidenteResponse param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(org.example.www.servicioclasificacionincidente.DeterminarTipoIncidenteResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private org.example.www.servicioclasificacionincidente.DeterminarTipoIncidenteResponse wrapdeterminarTipoIncidente(){
                                org.example.www.servicioclasificacionincidente.DeterminarTipoIncidenteResponse wrappedElement = new org.example.www.servicioclasificacionincidente.DeterminarTipoIncidenteResponse();
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
        
                if (org.example.www.servicioclasificacionincidente.DeterminarTipoIncidente.class.equals(type)){
                
                        return org.example.www.servicioclasificacionincidente.DeterminarTipoIncidente.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
            
                if (org.example.www.servicioclasificacionincidente.DeterminarTipoIncidenteResponse.class.equals(type)){
                
                        return org.example.www.servicioclasificacionincidente.DeterminarTipoIncidenteResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
            
                if (org.example.www.servicioclasificacionincidente.DeterminarUrgenciaIncidente.class.equals(type)){
                
                        return org.example.www.servicioclasificacionincidente.DeterminarUrgenciaIncidente.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
            
                if (org.example.www.servicioclasificacionincidente.DeterminarUrgenciaIncidenteResponse.class.equals(type)){
                
                        return org.example.www.servicioclasificacionincidente.DeterminarUrgenciaIncidenteResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

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
    