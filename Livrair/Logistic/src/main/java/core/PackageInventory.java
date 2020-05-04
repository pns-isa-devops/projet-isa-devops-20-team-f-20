package core;

import javax.ejb.Local;

@Local
public interface PackageInventory {
    void retrieveIncomingPackages();

    void useSupplierReference(PackageSupplyAPI p);
}
