package api;

/*import stubs.cart.CartWebService;
import stubs.cart.CartWebServiceImplService;
import stubs.customerCare.CustomerCareService;
import stubs.customerCare.CustomerCareServiceImplService;*/

import fr.unice.polytech.si._4a.isa.drone_delivery.delivery.DeliveryWebService;
import fr.unice.polytech.si._4a.isa.drone_delivery.delivery.DeliveryWebServiceImplService;

import javax.xml.ws.BindingProvider;
import java.net.URL;

public class LivrairPublicAPI {

	/*public CartWebService carts;
	public CustomerCareService ccs;*/
	public DeliveryWebService ccs;

	public LivrairPublicAPI(String host, String port) {
		/*initCart(host, port);
		initCCS(host, port);*/
		initHello(host, port);
	}

	/*private void initCart(String host, String port) {
		URL wsdlLocation = LivrairPublicAPI.class.getResource("/CartWS.wsdl");
		CartWebServiceImplService factory = new CartWebServiceImplService(wsdlLocation);
		this.carts = factory.getCartWebServiceImplPort();
		String address = "http://" + host + ":" + port + "/tcf-backend/webservices/CartWS";
		((BindingProvider) carts).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
	}

	private void initCCS(String host, String port) {
		URL wsdlLocation = LivrairPublicAPI.class.getResource("/CustomerCareWS.wsdl");
		CustomerCareServiceImplService factory = new CustomerCareServiceImplService(wsdlLocation);
		this.ccs = factory.getCustomerCareServiceImplPort();
		String address = "http://" + host + ":" + port + "/tcf-backend/webservices/CustomerCareWS";
		((BindingProvider) ccs).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
	}*/

	private void initHello(String host, String port) {
		URL wsdlLocation = LivrairPublicAPI.class.getResource("/DeliveryWebServiceImpl.wsdl");
		DeliveryWebServiceImplService factory = new DeliveryWebServiceImplService(wsdlLocation);
		this.ccs = factory.getDeliveryWebServiceImplPort();
		String address = "http://" + host + ":" + port + "/delivery/webservices/DeliveryWS";
		((BindingProvider) ccs).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
	}

}
