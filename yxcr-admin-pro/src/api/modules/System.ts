import {MenuData, ResultData} from "@/api/interface";

/**
 * @description Menu 菜单管理
 */
// 菜单栏
export const getAuthMenuListApi = () =>
    httpGet<ResultData>("/admin/permission");
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


// 角色
export const RoleList = () => httpGet<ResultData>(`system/roleList`);
export const addRole = (params) => httpPost<ResultData>(`system/addRole`, params);
export const editRole = (params) => httpPost<ResultData>(`system/editRole`, params);
export const deleteRole = (ids: number[]) => httpPost<ResultData>(`system/deleteRole`, {id: ids});
// 菜单列表
export const getMenuTables = () => httpGet<ResultData>(`system/tables`);
export const getRoleMenuByRoleId = (roleId: number) => httpGet<ResultData>(`system/role/info/${roleId}`);
