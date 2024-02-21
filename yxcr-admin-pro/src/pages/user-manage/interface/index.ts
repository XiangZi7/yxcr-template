export interface acceptParamModel {
  title: string;
  isView: boolean;
  dataForm: dataFormModel;
}

export interface AddOrUpdateState {
  drawerProps: drawerPropsState;
  dataRule: object;
  visible: false;
}

interface drawerPropsState {
  isView: false;
  //标题
  title: "";
  dataForm: dataFormModel;
  api: Promise;
}

interface dataFormModel {
  avatar: string;
  createTime: string;
  email: string;
  id: number;
  isDelete: boolean;
  phone: string;
  roleName: string;
  status: number;
  updateTime: string;
  username: string;
}
