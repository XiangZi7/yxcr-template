import {MenuData, ResultData} from "@/api/interface";

// 菜单栏
export const getAuthMenuListApi = () =>
    httpGet<ResultData>("/admin/permission");
// 字典
export const getDict = (dict: string) => httpGet<ResultData>(`dict/${dict}`);

/**
 * @description Menu 菜单管理
 */
// 菜单列表
export const getMenuTable = () => httpGet<ResultData>(`system/table`);
//菜单新增or菜单修改
export const systemMenu = (params: MenuData) =>
    httpPost<ResultData>("system/menu", params);
// 删除菜单
export const systemMenuDelete = (id: number) =>
    httpPost<ResultData>("system/menuDelete", {id: [id]});
