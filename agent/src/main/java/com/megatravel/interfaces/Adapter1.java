//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.08.31 at 10:45:46 PM CEST 
//


package com.megatravel.interfaces;

import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class Adapter1
    extends XmlAdapter<String, Date>
{


    public Date unmarshal(String value) {
        return (com.megatravel.util.DateConverter.parseDate(value));
    }

    public String marshal(Date value) {
        return (com.megatravel.util.DateConverter.printDate(value));
    }

}
