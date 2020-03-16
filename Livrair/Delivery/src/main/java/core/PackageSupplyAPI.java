package core;

import com.google.gson.Gson;
import entities.Package;

import java.util.ArrayList;
import java.util.List;

import entities.PackageStatus;
import entities.Supplier;
import exceptions.ExternalPartnerException;
import org.apache.cxf.jaxrs.client.WebClient;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.json.JsonObject;

public class PackageSupplyAPI {
    private String APIkey;
    private String url;
    private Supplier supplier;
    public PackageSupplyAPI(String host, String port, String APIkey, Supplier supplier) {
        this.url = "http://" + host + ":" + port;
        this.APIkey = APIkey;
        this.supplier = supplier;
    }

    public List<Package> retrievePackages() throws ExternalPartnerException{
        // Retrieving the packages from transporters
        JSONArray packages;
        List<Package> packagesReceived = new ArrayList<>();
        try {
            String response = WebClient.create(url).path("/partners/" + APIkey + "/packages").get(String.class);
            packages = new JSONArray(response);
            for(int i =0; i< packages.length(); i++){
                Package p = new Gson().fromJson(packages.get(i).toString(), Package.class);
                p.setPackageStatus(PackageStatus.REGISTERED);
                p.setSupplier(supplier);
                packagesReceived.add(p);
            }
        } catch (Exception e) {
            throw new ExternalPartnerException("/partners/" + APIkey + "/packages", e);
        }
        return packagesReceived;
    }
}
