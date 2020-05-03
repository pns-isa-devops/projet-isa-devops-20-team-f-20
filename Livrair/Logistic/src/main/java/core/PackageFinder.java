package core;

import entities.Package;
import entities.PackageStatus;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

@Local
public interface PackageFinder {
    Optional<Package> findById(String id);

    Optional<List<Package>> findByCustomer(String customerName);

    Optional<List<Package>> findByStatus(PackageStatus status);

    Optional<List<Package>> getAllPackages();
}
