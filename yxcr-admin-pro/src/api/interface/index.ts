// 请求响应参数（不包含data）
export interface Result {
    code: string;
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
    id: number | string
    title: string;
    icon: string;
    path: string;
    name: string;
    type?: string;
    orderNum: number;
    visible: boolean;
    permission: string;
    isKeepAlive: boolean;
    children?: MenuData[];
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
