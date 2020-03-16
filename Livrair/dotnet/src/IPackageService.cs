using System;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Collections.Generic;

using Package.Data;

namespace Package.Service {

    [ServiceContract]
    public interface IPackageService
    {
        [OperationContract]
        [WebInvoke( Method = "GET", UriTemplate = "partners/{ APIkey }/packages",
                    ResponseFormat = WebMessageFormat.Json)]
        Package[] ReceivePackages();
    }
}