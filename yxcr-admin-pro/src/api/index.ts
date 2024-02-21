import { ResultData } from "@/api/interface";
// 字典
export const getDict = (dict: string) => httpGet<ResultData>(`dict/${dict}`);
