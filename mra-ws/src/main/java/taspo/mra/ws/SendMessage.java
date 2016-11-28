package taspo.mra.ws;

import java.security.Principal;

import javax.security.auth.x500.X500Principal;
import javax.security.cert.X509Certificate;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;

import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


@Endpoint
public class SendMessage {

    private static final String NAMESPACE = "urn:IE_US_MR_Schema";
   
    @PayloadRoot(localPart = "Envelope", namespace = NAMESPACE)
    @ResponsePayload
    public Element handleRequest(@RequestPayload Element request, MessageContext context) throws ParserConfigurationException, SOAPException {
   	
    	
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Element messageElement = document.createElementNS(NAMESPACE, "Return");
        messageElement.appendChild(addElementWithValue(document, "city", "Hamburg"));
        
        return messageElement;
    }
   
    private Element addElementWithValue(Document document, String element, String value){
        Element child = document.createElementNS(NAMESPACE, element);
        child.appendChild(document.createTextNode(value));
        return child;
    }
}
