import { ResultData } from "@/api/interface";

// 菜单栏
export const getAuthMenuListApi = () =>
  httpGet<ResultData>("/admin/permission");
