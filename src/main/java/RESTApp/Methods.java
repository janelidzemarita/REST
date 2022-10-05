package RESTApp;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.PUT;
import org.jboss.weld.context.http.Http;
import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public class Methods {

    @WebMethod
    @Http
    @GET
    @QueryParam("id")
    @HeaderParam("s")
    public Response.Status getPerson(String id){
        try {
            File file = new File("persons.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();

            if(doc.getDocumentElement().hasAttribute(id)){

                return Response.Status.OK;

            }else {
                return Response.Status.NOT_FOUND;
            }

        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }

    }


    @WebMethod
    @Http
    @POST

    public Response.Status addPerson(@NotNull String id){
        if(id.isEmpty()){
            return Response.Status.OK;
        }
        else{
            return Response.Status.CONFLICT;
        }
    }

    @WebMethod
    @Http
    @PUT

    public Response.Status updatePerson(@NotNull String id){
        if(id.isEmpty()){
            return Response.Status.OK;
        }
        else{
            return Response.Status.NOT_FOUND;
        }
    }

    @WebMethod
    @Http
    @DELETE
    public Response.Status deletePerson(@NotNull String id){
        if(id.isEmpty()){
            return Response.Status.OK;
        }
        else{
            return Response.Status.NOT_FOUND;
        }
    }

    @WebMethod
    @Http
    @GET
    public Response.Status listPersons(){
        return Response.Status.OK;
    }
}
