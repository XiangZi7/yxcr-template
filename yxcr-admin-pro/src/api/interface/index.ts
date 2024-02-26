// 请求响应参数（不包含data）
export interface Result {
  code: number;
  message: string;
  time: string;
}

// 请求响应参数（包含data）
export interface ResultData<T = any> extends Result {
  data?: T;
}

// MenuData
export interface MenuDataState {
  list: MenuData[];
  column: TableColumn[];
}
export interface MenuData {
  id: number;
  parentId: number | null;
  title: string;
  icon: string;
  path: string;
  name: string;
  type: number;
  orderNum: number;
  visible: number;
  permission: string;
  isKeepAlive: number;
  children?: MenuData[];
  component: string;
  redirect: string;
}

export interface MenuDataCL {
  id: number;
  meta: {
    icon: string;
    parentId: number | null;
    type: number;
    orderNum: number;
    visible: number;
    permission: string;
    isKeepAlive: number;
  };
  children?: MenuDataCL[];
  title: string;
  path: string;
  name: string;
  component: string;
  redirect: string;
}

// TableColumn 类型定义
export interface TableColumn {
  prop?: string;
  label?: string;
  slot?: string;
  type?: string;
  fixed?: string;
  align?: string;
  width?: string | number;
}

//菜单栏上传参数
