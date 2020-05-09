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
       private Package p4 = new Package();
       private Package p5 = new Package();
       private Package p6 = new Package();
       private Package p7 = new Package();
       private Package p8 = new Package();
       private Package p9 = new Package();
       private Package p10 = new Package();

           public Package[] RetrievePackages(string apikey)
           {
               p1.customerName = "Denis";
               p2.customerName = "Yann";
               p3.customerName = "Patrick";
               p4.customerName = "Fernand";
               p5.customerName = "Daniel";
               p6.customerName = "Jose";
               p7.customerName = "Paul";
               p8.customerName = "Jacques";
               p9.customerName = "Beatrice";
               p10.customerName = "Valentine";
               p1.id = "0";
               p2.id = "1";
               p3.id = "2";
               p4.id = "3";
               p5.id = "4";
               p6.id = "5";
               p7.id = "6";
               p8.id = "7";
               p9.id = "8";
               p10.id = "9";
               p1.address = "210 rue de roumanille";
               p2.address = "9 rue de la touche";
               p3.address = "310 promenade des anglais";
               p4.address = "15 rue des roses";
               p5.address = "136 avenue des tulipes";
               p6.address = "7856 chemin des passeraux";
               p7.address = "96 impasse des pins";
               p8.address = "65 rue des ecoles";
               p9.address = "426 chemin des figuiers";
               p10.address = "12 rue des feuilles";
               Console.WriteLine("\n[LOG] [" + DateTime.Now.ToString("HH:mm:ss") + "] New GetPackages request received\n");
               return new Package[] { p1, p2, p3, p4, p5, p6, p7, p8, p9, p10  };
           }
    }
}