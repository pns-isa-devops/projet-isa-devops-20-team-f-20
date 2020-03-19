using System;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Collections.Generic;


namespace delivery {

    [ServiceContract]
    public interface IPackageService
    {
        [OperationContract]
        [WebInvoke( Method = "GET", UriTemplate = "partners/{apikey}/packages", ResponseFormat = WebMessageFormat.Json)]
        Package[] RetrievePackages(string apikey);
    }
}