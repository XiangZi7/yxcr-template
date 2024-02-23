import { ResultData } from "@/api/interface";

export const AdminULogin = (params) =>
  httpPost<ResultData>("/admin/login", params);
export const adminLogout = () => httpPost<ResultData>("/admin/logout");
