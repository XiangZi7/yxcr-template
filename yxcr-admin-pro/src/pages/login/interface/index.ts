export interface adminLogin {
  isCurrent: string;
  showPopup: boolean;
  loginForm: LoginFormModel;
}

interface LoginFormModel {
  phone?: string | number;
  email?: string;
  password?: string | number;
}
