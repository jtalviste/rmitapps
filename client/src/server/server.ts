import axios from "axios";
import serverConfig from "@/server/serverconfig";
import type { ApplicationResponse } from "@/server/dto/applicationresponse";
import type { AppServiceResponse } from "@/server/dto/appserviceresponse";
import type { ApplicationAddRequest } from "@/server/dto/applicationaddrequest";
import type { AppServiceAddRequest } from "@/server/dto/appserviceaddrequest";

const server = {
  searchApplication : async (serviceName: string) => {
    const application = await axios.get(serverConfig.applicationSearchEndpoint, { params: { service_name : serviceName } });
    return application.data as ApplicationResponse | null;
  },
  searchServices : async (applicationName: string) => {
    const applications = await axios.get(serverConfig.serviceSearchEndpoint, { params: { application_name : applicationName } });
    return applications.data as [AppServiceResponse];
  },
  async addApplication(application: ApplicationAddRequest) {
    await axios.post(serverConfig.applicationEndpoint, application)
  },
  async addService(service: AppServiceAddRequest) {
    await axios.post(serverConfig.serviceEndpoint, service)
  }
}

export default server;