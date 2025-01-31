package core;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import entities.Package;
import entities.PackageStatus;
import entities.Supplier;
import exceptions.ExternalPartnerException;
import org.apache.cxf.jaxrs.client.WebClient;

import java.util.ArrayList;
import java.util.List;


public class PackageSupplyAPI {
    private final String APIkey;
    private final String url;
    private final Supplier supplier;

    public PackageSupplyAPI(String host, String port, String APIkey, Supplier supplier) {
        this.url = "http://" + host + ":" + port;
        this.APIkey = APIkey;
        this.supplier = supplier;
    }

    public List<Package> retrievePackages() throws ExternalPartnerException {
        // Retrieving the packages from transporters

        List<Package> packagesReceived = new ArrayList<>();
        try {
            String response = WebClient.create(url).path("/partners/" + APIkey + "/packages").get(String.class);
            JsonParser parser = new JsonParser();
            JsonArray packages = parser.parse(response).getAsJsonArray();
            for (JsonElement pack : packages) {
                Package p = new Gson().fromJson(pack, Package.class);
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
