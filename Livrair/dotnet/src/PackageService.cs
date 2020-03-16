using System;
using System.Net;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Collections.Generic;
using System.Linq;
using Partner.Data;

namespace Package.Service {

    [ServiceBehavior(InstanceContextMode = InstanceContextMode.Single)]
    public class PackageService : IPackageService
    {
    private Package p1 = new Package("10","denis","rue 1");
    private Package p2 = new Package("12","yann","rue 2");
    private Package p3 = new Package("14","patrick","rue 3");


    private Package[] packages = new Package[]{p1,p2,p3};
    }
}