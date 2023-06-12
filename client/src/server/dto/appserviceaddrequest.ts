import type { AppServiceType } from "@/server/dto/appservicetype";
import type { AppServiceSubType } from "@/server/dto/appservicesubtype";

export interface AppServiceAddRequest {
  code: string;
  name: string;
  description: string;
  applicationCode: string;
  type: AppServiceType;
  subType: AppServiceSubType;
}