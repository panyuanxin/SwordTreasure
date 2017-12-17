package indi.sword.util.webService._01_helloworld.client.wsdl2java;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.1.4
 * 2017-12-17T11:09:03.314+08:00
 * Generated source version: 3.1.4
 * 
 */
@WebService(targetNamespace = "http://iservice.server._01_helloworld.webService.util.sword.indi/", name = "HelloworldWsInterface")
@XmlSeeAlso({ObjectFactory.class})
public interface HelloworldWsInterface {

    @WebMethod
    @RequestWrapper(localName = "getAllCats", targetNamespace = "http://iservice.server._01_helloworld.webService.util.sword.indi/", className = "indi.sword.util.webService._01_helloworld.client.wsdl2java.GetAllCats")
    @ResponseWrapper(localName = "getAllCatsResponse", targetNamespace = "http://iservice.server._01_helloworld.webService.util.sword.indi/", className = "indi.sword.util.webService._01_helloworld.client.wsdl2java.GetAllCatsResponse")
    @WebResult(name = "return", targetNamespace = "")
    public StringCat getAllCats();

    @WebMethod
    @RequestWrapper(localName = "getCatsByUser", targetNamespace = "http://iservice.server._01_helloworld.webService.util.sword.indi/", className = "indi.sword.util.webService._01_helloworld.client.wsdl2java.GetCatsByUser")
    @ResponseWrapper(localName = "getCatsByUserResponse", targetNamespace = "http://iservice.server._01_helloworld.webService.util.sword.indi/", className = "indi.sword.util.webService._01_helloworld.client.wsdl2java.GetCatsByUserResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<Cat> getCatsByUser(
            @WebParam(name = "arg0", targetNamespace = "")
                    User arg0
    );

    @WebMethod
    @RequestWrapper(localName = "sayHi", targetNamespace = "http://iservice.server._01_helloworld.webService.util.sword.indi/", className = "indi.sword.util.webService._01_helloworld.client.wsdl2java.SayHi")
    @ResponseWrapper(localName = "sayHiResponse", targetNamespace = "http://iservice.server._01_helloworld.webService.util.sword.indi/", className = "indi.sword.util.webService._01_helloworld.client.wsdl2java.SayHiResponse")
    @WebResult(name = "return", targetNamespace = "")
    public String sayHi(
            @WebParam(name = "arg0", targetNamespace = "")
                    String arg0
    );
}
