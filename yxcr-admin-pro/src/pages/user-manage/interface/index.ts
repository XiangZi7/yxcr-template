export interface acceptParamModel {
  title: string;
  isView: boolean;
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
  dataForm: object;
}
