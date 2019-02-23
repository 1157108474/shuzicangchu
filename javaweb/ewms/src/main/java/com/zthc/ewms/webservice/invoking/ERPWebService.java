package com.zthc.ewms.webservice.invoking;

import com.zthc.ewms.sheet.entity.guard.*;
import org.springframework.stereotype.Controller;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;
import java.util.Map;

@WebService
@Controller
public interface ERPWebService {


    @WebMethod
    String pushXML(String strXml);

    @WebMethod
    String getResult(String json);






}
