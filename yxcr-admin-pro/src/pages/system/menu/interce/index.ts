export interface FormSchema {
  type: number;
  visible: number;
  orderNum: number;
  icon: string;
  path: string;
  permission: string;
  name: string;
  title: string;
  component:string,
  redirect:string
  isKeepAlive:number
  idList:[]
}

interface FormRules {
  [key: string]: Array<{ required: boolean; message: string; trigger: string }>;
}

interface CascaderProps {
  value: string;
  label: string;
  expandTrigger: string;
  checkStrictly: boolean;
}
// 菜单添加修改模型
export interface AddOrUpdateState {
  visible: boolean;
  dataForm: FormSchema;
  dataRule: FormRules;
  cascaderOptions: any[]; // Specify a more specific type if possible
  cascaderProps: CascaderProps;
  selectedMenu: number[]; // Or whatever the correct type is
}
