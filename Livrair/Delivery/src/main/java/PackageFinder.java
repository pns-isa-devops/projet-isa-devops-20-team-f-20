import entities.Package;
import entities.PackageStatus;

import java.util.List;
import java.util.Optional;

public interface PackageFinder {
    Optional<Package> findById(String id);

    Optional<Package> findByCustomer(String customerName);

    Optional<List<Package>> findByStatus(PackageStatus status);
}
