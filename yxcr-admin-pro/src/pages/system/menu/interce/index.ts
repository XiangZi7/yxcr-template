import { MenuData } from "@/api/interface";

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
  dataForm: MenuData;
  dataRule: FormRules;
  cascaderOptions: any[]; // Specify a more specific type if possible
  cascaderProps: CascaderProps;
  selectedMenu: number[] | undefined; // Or whatever the correct type is
  disabled: boolean;
  title: string;
}

export interface acceptParamModel {
  title: string;
  dataForm: MenuData;
  disabled: boolean;
  idList: number[];
}
