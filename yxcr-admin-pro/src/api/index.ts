import { ResultData } from "@/api/interface";

// 菜单栏
export const getAuthMenuListApi = () =>
  httpGet<ResultData>("/admin/permission");
// 字典
export const getDict = (dict: string) => httpGet<ResultData>(`dict/${dict}`);
// 菜单列表
export const getMenuTable = () => httpGet<ResultData>(`system/table`);
