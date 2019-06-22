package com.megatravel.gateway.interfaces;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("xmlservice")
public interface XmlServiceFeignClient {

	@RequestMapping(value = "/verify", method = RequestMethod.POST, consumes = MediaType.TEXT_XML_VALUE, produces = MediaType.TEXT_XML_VALUE)
	public ResponseEntity<String> verifySignatureAndDecode(@RequestBody String message, 
			   											   @RequestParam("recipient") String recipient);
	
	@RequestMapping(value = "/sign", method = RequestMethod.POST, consumes = MediaType.TEXT_XML_VALUE, produces = MediaType.TEXT_XML_VALUE)
	public ResponseEntity<String> signAndEncode(@RequestBody String message,
												@RequestParam("recipient-serial-number") String recipientSerialNumber,
												@RequestParam("sender") String sender);
	
}
