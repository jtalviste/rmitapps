import type { AppServiceType } from "@/server/dto/appservicetype";
import type { AppServiceSubType } from "@/server/dto/appservicesubtype";

export interface AppServiceResponse {
  serviceCode: string;
  name: string;
  type: AppServiceType;
  subType: AppServiceSubType;
  description: string;
  lastModified: string; // Assuming LocalDateTime is converted to a string
}