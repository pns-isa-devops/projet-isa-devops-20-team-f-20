import cli.commands.*;
import cli.framework.Shell;
import api.LivrairPublicAPI;

/**
 * An Interactive shell that interacts with a Cookie on Demand instance
 * Use -Dexec.args="IP_ADDRESS PORT_NUMBER" to change host/port parameters
 */
public class Main extends Shell<LivrairPublicAPI> {

	public Main(String host, String port) {

		this.system  = new LivrairPublicAPI(host, port);
		this.invite  = "Livrair";

		// Registering the command available for the user
		register(
				// package finder
				getPackage.class,

				// delivery creator
				createDelivery.class,

				// planned deliveries
				getPlannedDeliveries.class
		);
	}

	public static void main(String[] args) {
		String host = ( args.length == 0 ? "localhost" : args[0] );
		String port = ( args.length < 2  ? "8080"      : args[1] );
		System.out.println("\n\nStarting Livrair by The F Team");
		System.out.println("  - Remote server: " + host);
		System.out.println("  - Port number:   " + port);
		Main main = new Main(host, port);
		main.run();
		System.out.println("Exiting Livrair by The F Team\n\n");
	}

}
