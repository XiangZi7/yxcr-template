import { ResultData } from "@/api/interface";

export const AdminULogin = (params: any) =>
  httpPost<ResultData>("/admin/login", params);
export const adminLogout = () => httpPost<ResultData>("/admin/logout");
