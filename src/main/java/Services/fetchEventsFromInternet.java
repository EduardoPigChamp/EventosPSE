package Services;

import Beans.LocationBEAN;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;


/**
 *
 * @author equintana
 */
@Named
@RequestScoped 
public class fetchEventsFromInternet {
    
    private Date fecha;
    private List<LocationBEAN> locations = new ArrayList<>();

    public List<LocationBEAN> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationBEAN> locations) {
        this.locations = locations;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public List<LocationBEAN> getFreeLocations(Date fecha) throws Exception
    {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");        
        
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://valdavia.infor.uva.es:8080/disponibilidad_estadios/webresources/dispestadios/estadios?fecha="+sf.format(fecha));
        Response res = target.request(MediaType.APPLICATION_JSON).get();
        
        if(res.getStatus() == 404) throw new Exception("No se han encontrado estadios ese dia. Error 404");
        
        String respuesta = res.readEntity(String.class);
        res.close();
        
        return parseFromJSONtoList(respuesta);
    }
    
    public void getFreeLocations() throws Exception
    {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");        
        
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://valdavia.infor.uva.es:8080/disponibilidad_estadios/webresources/dispestadios/estadios?fecha="+sf.format(fecha));
        Response res = target.request(MediaType.APPLICATION_JSON).get();
        
        if(res.getStatus() == 404) throw new Exception("No se han encontrado estadios ese dia. Error 404");
        
        String respuesta = res.readEntity(String.class);
        res.close();
        
        locations = parseFromJSONtoList(respuesta);
    }
    
    private List<LocationBEAN> parseFromJSONtoList(String response) throws JSONException, ParseException
    {
        ArrayList<LocationBEAN> list = new ArrayList<>();
        LocationBEAN location;
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        
        JSONObject jsonLocation;
        JSONArray jsonArray = new JSONArray(response);
        
        for(int i = 0; i < jsonArray.length(); i++)
        {
            jsonLocation = jsonArray.getJSONObject(i);
            
            location = new LocationBEAN();
            location.setDisponibilidad(jsonLocation.getBoolean(LocationBEAN.DISPONIBILIDAD));
            location.setFecha(sf.parse(jsonLocation.getString(LocationBEAN.FECHA)));
            location.setId(jsonLocation.getInt(LocationBEAN.ID));
            location.setNombre_estadio(jsonLocation.getString(LocationBEAN.NOMBRE_ESTADIO));
            
            list.add(location);
        }
        
        return list;
    }
    
}
