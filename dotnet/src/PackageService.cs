using System;
using System.Net;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Collections.Generic;
using System.Linq;

namespace delivery {

    [ServiceBehavior(InstanceContextMode = InstanceContextMode.Single)]
    public class PackageService : IPackageService
    {
    private Package p1 = new Package();
    private Package p2 = new Package();
    private Package p3 = new Package();



        public Package[] RetrievePackages(string apikey)
        {
            p1.customerName = "Denis";
            p2.customerName = "Yann";
            p3.customerName = "Patrick";
            p1.id = "0";
            p2.id = "1";
            p3.id = "2";
            p1.address = "210 rue de roumanille";
            p2.address = "9 rue de la touche";
            p3.address = "310 promenade des anglais";
            Console.WriteLine("\n[LOG] [" + DateTime.Now.ToString("HH:mm:ss") + "] New GetPackages request received\n");
            return new Package[] { p1, p2, p3 };
        }
    }
}