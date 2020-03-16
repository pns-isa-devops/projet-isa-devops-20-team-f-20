using System.Runtime.Serialization;
using System;

namespace Package.Data {
    [DataContract(Namespace = "http://partner/external/package/data/",
                  Name = "Package")]
    public class Package
    {
    [DataMember]
    public string id { get; set; }

    [DataMember]
    public string customerName { get; set; }

    [DataMember]
    public string address { get; set; }
    }
}