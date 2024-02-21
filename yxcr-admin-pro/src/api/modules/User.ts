import { ResultData } from "@/api/interface";

/**
 * @description User 用户管理
 */
// 列表
export const userList = (params) => httpGet<ResultData>("user/list", params);
// 添加
export const addUser = (params) => httpPost<ResultData>("user/add", params);
// 编辑
export const editUser = (params) => httpPost<ResultData>("user/edit", params);
// 删除
export const deleteUser = (ids: number[]) =>
  httpPost<ResultData>("user/delete", { id: ids });
// 重置密码
export const RefreshUserPwd = (id: number) =>
  httpPost<ResultData>(`user/RefreshPwd/${id}`);
