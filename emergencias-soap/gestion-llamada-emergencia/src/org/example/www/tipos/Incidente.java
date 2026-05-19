
/**
 * Incidente.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.3  Built on : Jun 27, 2015 (11:18:31 BST)
 */

            
                package org.example.www.tipos;
            

            /**
            *  Incidente bean class
            */
            @SuppressWarnings({"unchecked","unused"})
        
        public  class Incidente
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = Incidente
                Namespace URI = http://www.example.org/tipos
                Namespace Prefix = ns1
                */
            

                        /**
                        * field for IdIncidente
                        */

                        
                                    protected int localIdIncidente ;
                                

                           /**
                           * Auto generated getter method
                           * @return int
                           */
                           public  int getIdIncidente(){
                               return localIdIncidente;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param IdIncidente
                               */
                               public void setIdIncidente(int param){
                            
                                            this.localIdIncidente=param;
                                       

                               }
                            

                        /**
                        * field for Avisos
                        * This was an Array!
                        */

                        
                                    protected org.example.www.tipos.Aviso[] localAvisos ;
                                

                           /**
                           * Auto generated getter method
                           * @return org.example.www.tipos.Aviso[]
                           */
                           public  org.example.www.tipos.Aviso[] getAvisos(){
                               return localAvisos;
                           }

                           
                        


                               
                              /**
                               * validate the array for Avisos
                               */
                              protected void validateAvisos(org.example.www.tipos.Aviso[] param){
                             
                              if ((param != null) && (param.length < 1)){
                                throw new java.lang.RuntimeException("Input values do not follow defined XSD restrictions");
                              }
                              
                              }


                             /**
                              * Auto generated setter method
                              * @param param Avisos
                              */
                              public void setAvisos(org.example.www.tipos.Aviso[] param){
                              
                                   validateAvisos(param);

                               
                                      this.localAvisos=param;
                              }

                               
                             
                             /**
                             * Auto generated add method for the array for convenience
                             * @param param org.example.www.tipos.Aviso
                             */
                             public void addAvisos(org.example.www.tipos.Aviso param){
                                   if (localAvisos == null){
                                   localAvisos = new org.example.www.tipos.Aviso[]{};
                                   }

                            

                               java.util.List list =
                            org.apache.axis2.databinding.utils.ConverterUtil.toList(localAvisos);
                               list.add(param);
                               this.localAvisos =
                             (org.example.www.tipos.Aviso[])list.toArray(
                            new org.example.www.tipos.Aviso[list.size()]);

                             }
                             

                        /**
                        * field for TipoIncidente
                        */

                        
                                    protected org.example.www.tipos.TipoIncidente localTipoIncidente ;
                                

                           /**
                           * Auto generated getter method
                           * @return org.example.www.tipos.TipoIncidente
                           */
                           public  org.example.www.tipos.TipoIncidente getTipoIncidente(){
                               return localTipoIncidente;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param TipoIncidente
                               */
                               public void setTipoIncidente(org.example.www.tipos.TipoIncidente param){
                            
                                            this.localTipoIncidente=param;
                                       

                               }
                            

                        /**
                        * field for NivelUrgencia
                        */

                        
                                    protected org.example.www.tipos.NivelUrgencia localNivelUrgencia ;
                                

                           /**
                           * Auto generated getter method
                           * @return org.example.www.tipos.NivelUrgencia
                           */
                           public  org.example.www.tipos.NivelUrgencia getNivelUrgencia(){
                               return localNivelUrgencia;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param NivelUrgencia
                               */
                               public void setNivelUrgencia(org.example.www.tipos.NivelUrgencia param){
                            
                                            this.localNivelUrgencia=param;
                                       

                               }
                            

                        /**
                        * field for Descripcion
                        */

                        
                                    protected java.lang.String localDescripcion ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getDescripcion(){
                               return localDescripcion;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Descripcion
                               */
                               public void setDescripcion(java.lang.String param){
                            
                                            this.localDescripcion=param;
                                       

                               }
                            

                        /**
                        * field for Localizacion
                        */

                        
                                    protected java.lang.String localLocalizacion ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getLocalizacion(){
                               return localLocalizacion;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Localizacion
                               */
                               public void setLocalizacion(java.lang.String param){
                            
                                            this.localLocalizacion=param;
                                       

                               }
                            

                        /**
                        * field for Estado
                        */

                        
                                    protected org.example.www.tipos.EstadoIncidente localEstado ;
                                

                           /**
                           * Auto generated getter method
                           * @return org.example.www.tipos.EstadoIncidente
                           */
                           public  org.example.www.tipos.EstadoIncidente getEstado(){
                               return localEstado;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Estado
                               */
                               public void setEstado(org.example.www.tipos.EstadoIncidente param){
                            
                                            this.localEstado=param;
                                       

                               }
                            

     
     
        /**
        *
        * @param parentQName
        * @param factory
        * @return org.apache.axiom.om.OMElement
        */
       public org.apache.axiom.om.OMElement getOMElement (
               final javax.xml.namespace.QName parentQName,
               final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException{


        
               org.apache.axiom.om.OMDataSource dataSource =
                       new org.apache.axis2.databinding.ADBDataSource(this,parentQName);
               return factory.createOMElement(dataSource,parentQName);
            
        }

         public void serialize(final javax.xml.namespace.QName parentQName,
                                       javax.xml.stream.XMLStreamWriter xmlWriter)
                                throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
                           serialize(parentQName,xmlWriter,false);
         }

         public void serialize(final javax.xml.namespace.QName parentQName,
                               javax.xml.stream.XMLStreamWriter xmlWriter,
                               boolean serializeType)
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
            
                


                java.lang.String prefix = null;
                java.lang.String namespace = null;
                

                    prefix = parentQName.getPrefix();
                    namespace = parentQName.getNamespaceURI();
                    writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);
                
                  if (serializeType){
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"http://www.example.org/tipos");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":Incidente",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "Incidente",
                           xmlWriter);
                   }

               
                   }
               
                                    namespace = "http://www.example.org/tipos";
                                    writeStartElement(null, namespace, "idIncidente", xmlWriter);
                             
                                               if (localIdIncidente==java.lang.Integer.MIN_VALUE) {
                                           
                                                         throw new org.apache.axis2.databinding.ADBException("idIncidente cannot be null!!");
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIdIncidente));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             
                                       if (localAvisos!=null){
                                            for (int i = 0;i < localAvisos.length;i++){
                                                if (localAvisos[i] != null){
                                                 localAvisos[i].serialize(new javax.xml.namespace.QName("http://www.example.org/tipos","avisos"),
                                                           xmlWriter);
                                                } else {
                                                   
                                                           throw new org.apache.axis2.databinding.ADBException("avisos cannot be null!!");
                                                    
                                                }

                                            }
                                     } else {
                                        
                                               throw new org.apache.axis2.databinding.ADBException("avisos cannot be null!!");
                                        
                                    }
                                 
                                            if (localTipoIncidente==null){
                                                 throw new org.apache.axis2.databinding.ADBException("tipoIncidente cannot be null!!");
                                            }
                                           localTipoIncidente.serialize(new javax.xml.namespace.QName("http://www.example.org/tipos","tipoIncidente"),
                                               xmlWriter);
                                        
                                            if (localNivelUrgencia==null){
                                                 throw new org.apache.axis2.databinding.ADBException("nivelUrgencia cannot be null!!");
                                            }
                                           localNivelUrgencia.serialize(new javax.xml.namespace.QName("http://www.example.org/tipos","nivelUrgencia"),
                                               xmlWriter);
                                        
                                    namespace = "http://www.example.org/tipos";
                                    writeStartElement(null, namespace, "descripcion", xmlWriter);
                             

                                          if (localDescripcion==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("descripcion cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localDescripcion);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             
                                    namespace = "http://www.example.org/tipos";
                                    writeStartElement(null, namespace, "localizacion", xmlWriter);
                             

                                          if (localLocalizacion==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("localizacion cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localLocalizacion);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             
                                            if (localEstado==null){
                                                 throw new org.apache.axis2.databinding.ADBException("estado cannot be null!!");
                                            }
                                           localEstado.serialize(new javax.xml.namespace.QName("http://www.example.org/tipos","estado"),
                                               xmlWriter);
                                        
                    xmlWriter.writeEndElement();
               

        }

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://www.example.org/tipos")){
                return "ns1";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Utility method to write an element start tag.
         */
        private void writeStartElement(java.lang.String prefix, java.lang.String namespace, java.lang.String localPart,
                                       javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
            if (writerPrefix != null) {
                xmlWriter.writeStartElement(namespace, localPart);
            } else {
                if (namespace.length() == 0) {
                    prefix = "";
                } else if (prefix == null) {
                    prefix = generatePrefix(namespace);
                }

                xmlWriter.writeStartElement(prefix, localPart, namespace);
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
        }
        
        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix,java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (xmlWriter.getPrefix(namespace) == null) {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            xmlWriter.writeAttribute(namespace,attName,attValue);
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName,attValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace,attName,attValue);
            }
        }


           /**
             * Util method to write an attribute without the ns prefix
             */
            private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                                             javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

                java.lang.String attributeNamespace = qname.getNamespaceURI();
                java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
                if (attributePrefix == null) {
                    attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
                }
                java.lang.String attributeValue;
                if (attributePrefix.trim().length() > 0) {
                    attributeValue = attributePrefix + ":" + qname.getLocalPart();
                } else {
                    attributeValue = qname.getLocalPart();
                }

                if (namespace.equals("")) {
                    xmlWriter.writeAttribute(attName, attributeValue);
                } else {
                    registerPrefix(xmlWriter, namespace);
                    xmlWriter.writeAttribute(namespace, attName, attributeValue);
                }
            }
        /**
         *  method to handle Qnames
         */

        private void writeQName(javax.xml.namespace.QName qname,
                                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix,namespaceURI);
                }

                if (prefix.trim().length() > 0){
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                                 javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }
                    namespaceURI = qnames[i].getNamespaceURI();
                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);
                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix,namespaceURI);
                        }

                        if (prefix.trim().length() > 0){
                            stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                    }
                }
                xmlWriter.writeCharacters(stringToWrite.toString());
            }

        }


        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);
            if (prefix == null) {
                prefix = generatePrefix(namespace);
                javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();
                while (true) {
                    java.lang.String uri = nsContext.getNamespaceURI(prefix);
                    if (uri == null || uri.length() == 0) {
                        break;
                    }
                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            return prefix;
        }


  
        /**
        * databinding method to get an XML representation of this object
        *
        */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
                    throws org.apache.axis2.databinding.ADBException{


        
                 java.util.ArrayList elementList = new java.util.ArrayList();
                 java.util.ArrayList attribList = new java.util.ArrayList();

                
                                      elementList.add(new javax.xml.namespace.QName("http://www.example.org/tipos",
                                                                      "idIncidente"));
                                 
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localIdIncidente));
                            
                             if (localAvisos!=null) {
                                 for (int i = 0;i < localAvisos.length;i++){

                                    if (localAvisos[i] != null){
                                         elementList.add(new javax.xml.namespace.QName("http://www.example.org/tipos",
                                                                          "avisos"));
                                         elementList.add(localAvisos[i]);
                                    } else {
                                        
                                               throw new org.apache.axis2.databinding.ADBException("avisos cannot be null !!");
                                            
                                    }

                                 }
                             } else {
                                 
                                        throw new org.apache.axis2.databinding.ADBException("avisos cannot be null!!");
                                    
                             }

                        
                            elementList.add(new javax.xml.namespace.QName("http://www.example.org/tipos",
                                                                      "tipoIncidente"));
                            
                            
                                    if (localTipoIncidente==null){
                                         throw new org.apache.axis2.databinding.ADBException("tipoIncidente cannot be null!!");
                                    }
                                    elementList.add(localTipoIncidente);
                                
                            elementList.add(new javax.xml.namespace.QName("http://www.example.org/tipos",
                                                                      "nivelUrgencia"));
                            
                            
                                    if (localNivelUrgencia==null){
                                         throw new org.apache.axis2.databinding.ADBException("nivelUrgencia cannot be null!!");
                                    }
                                    elementList.add(localNivelUrgencia);
                                
                                      elementList.add(new javax.xml.namespace.QName("http://www.example.org/tipos",
                                                                      "descripcion"));
                                 
                                        if (localDescripcion != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localDescripcion));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("descripcion cannot be null!!");
                                        }
                                    
                                      elementList.add(new javax.xml.namespace.QName("http://www.example.org/tipos",
                                                                      "localizacion"));
                                 
                                        if (localLocalizacion != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localLocalizacion));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("localizacion cannot be null!!");
                                        }
                                    
                            elementList.add(new javax.xml.namespace.QName("http://www.example.org/tipos",
                                                                      "estado"));
                            
                            
                                    if (localEstado==null){
                                         throw new org.apache.axis2.databinding.ADBException("estado cannot be null!!");
                                    }
                                    elementList.add(localEstado);
                                

                return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());
            
            

        }

  

     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{

        
        

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static Incidente parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            Incidente object =
                new Incidente();

            int event;
            java.lang.String nillableValue = null;
            java.lang.String prefix ="";
            java.lang.String namespaceuri ="";
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                
                if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","type")!=null){
                  java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                        "type");
                  if (fullTypeName!=null){
                    java.lang.String nsPrefix = null;
                    if (fullTypeName.indexOf(":") > -1){
                        nsPrefix = fullTypeName.substring(0,fullTypeName.indexOf(":"));
                    }
                    nsPrefix = nsPrefix==null?"":nsPrefix;

                    java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":")+1);
                    
                            if (!"Incidente".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (Incidente)org.example.www.servicioincidente.ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                
                    
                    reader.next();
                
                        java.util.ArrayList list2 = new java.util.ArrayList();
                    
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.example.org/tipos","idIncidente").equals(reader.getName())){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"idIncidente" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setIdIncidente(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.example.org/tipos","avisos").equals(reader.getName())){
                                
                                    
                                    
                                    // Process the array and step past its final element's end.
                                    list2.add(org.example.www.tipos.Aviso.Factory.parse(reader));
                                                                
                                                        //loop until we find a start element that is not part of this array
                                                        boolean loopDone2 = false;
                                                        while(!loopDone2){
                                                            // We should be at the end element, but make sure
                                                            while (!reader.isEndElement())
                                                                reader.next();
                                                            // Step out of this element
                                                            reader.next();
                                                            // Step to next element event.
                                                            while (!reader.isStartElement() && !reader.isEndElement())
                                                                reader.next();
                                                            if (reader.isEndElement()){
                                                                //two continuous end elements means we are exiting the xml structure
                                                                loopDone2 = true;
                                                            } else {
                                                                if (new javax.xml.namespace.QName("http://www.example.org/tipos","avisos").equals(reader.getName())){
                                                                    list2.add(org.example.www.tipos.Aviso.Factory.parse(reader));
                                                                        
                                                                }else{
                                                                    loopDone2 = true;
                                                                }
                                                            }
                                                        }
                                                        // call the converter utility  to convert and set the array
                                                        
                                                        object.setAvisos((org.example.www.tipos.Aviso[])
                                                            org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                                                                org.example.www.tipos.Aviso.class,
                                                                list2));
                                                            
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.example.org/tipos","tipoIncidente").equals(reader.getName())){
                                
                                                object.setTipoIncidente(org.example.www.tipos.TipoIncidente.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.example.org/tipos","nivelUrgencia").equals(reader.getName())){
                                
                                                object.setNivelUrgencia(org.example.www.tipos.NivelUrgencia.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.example.org/tipos","descripcion").equals(reader.getName())){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"descripcion" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setDescripcion(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.example.org/tipos","localizacion").equals(reader.getName())){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"localizacion" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setLocalizacion(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://www.example.org/tipos","estado").equals(reader.getName())){
                                
                                                object.setEstado(org.example.www.tipos.EstadoIncidente.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                              
                            while (!reader.isStartElement() && !reader.isEndElement())
                                reader.next();
                            
                                if (reader.isStartElement())
                                // A start element we are not expecting indicates a trailing invalid property
                                throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                            



            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }

            return object;
        }

        }//end of factory class

        

        }
           
    