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
//菜单新增
export const addMenu = (params: MenuData) =>
    httpPost<ResultData>("system/addMenu", params);
//菜单编辑
export const editMenu = (params: MenuData) =>
    httpPost<ResultData>("system/editMenu", params);
// 删除菜单
export const systemMenuDelete = (id: number) =>
    httpPost<ResultData>("system/deleteMenu", {id: [id]});

/**
 * @description User 用户管理
 */
export const userList = (params) => httpGet<ResultData>("user/list", params);
