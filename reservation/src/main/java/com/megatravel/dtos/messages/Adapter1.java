//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.22 at 05:53:10 PM CEST 
//


package com.megatravel.dtos.messages;

import com.megatravel.util.DateConverter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Date;

public class Adapter1
    extends XmlAdapter<String, Date>
{


    public Date unmarshal(String value) {
        return (DateConverter.parseDate(value));
    }

    public String marshal(Date value) {
        return (DateConverter.printDate(value));
    }

}
