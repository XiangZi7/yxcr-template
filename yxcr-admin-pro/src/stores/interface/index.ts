export type LayoutType = "vertical" | "classic" | "transverse" | "columns";

export type AssemblySizeType = "large" | "default" | "small";

export type LanguageType = "zh" | "en" | null;

// 假设 StateType 描述了你的整个状态对象
export interface StateType {
  layout: LayoutType;
  assemblySize: AssemblySizeType;
  language: LanguageType;
  maximize: boolean;
  primary: string;
  isDark: boolean;
  isGrey: boolean;
  isWeak: boolean;
  asideInverted: boolean;
  headerInverted: boolean;
  isCollapse: boolean;
  accordion: boolean;
  breadcrumb: boolean;
  breadcrumbIcon: boolean;
  tabs: boolean;
  tabsIcon: boolean;
  footer: boolean;
}

export interface userModel {
  avatarUrl?: string;
  nickname?: string;
  userId?: number;
}

/**
 * userStore的实体类
 */
export interface UserState {
  userInfo: userModel;
  token: string;
}

/**
 * themeStore的实体类
 */
export interface ThemeState {
  layout: LayoutType;
  assemblySize: AssemblySizeType;
  language: LanguageType;
  maximize: boolean;
  primary: string;
  isDark: boolean;
  isGrey: boolean;
  isWeak: boolean;
  asideInverted: boolean;
  headerInverted: boolean;
  isCollapse: boolean;
  accordion: boolean;
  breadcrumb: boolean;
  breadcrumbIcon: boolean;
  tabs: boolean;
  tabsIcon: boolean;
  footer: boolean;
}

/* AuthState */
export interface AuthState {
  routeName: string;
  authButtonList: {
    [key: string]: string[];
  };
  authMenuList: MenuOptions[];
}

export interface MenuOptions {
  id: string;
  path: string;
  name: string;
  component?: string | (() => Promise<unknown>);
  redirect?: string;
  meta: MetaProps;
  children?: MenuOptions[];
}

interface MetaProps {
  icon: string;
  title: string;
  activeMenu?: string;
  isLink?: string;
  isHide: number;
  isFull: number;
  isKeepAlive: number;
}

/**
 * TabsState
 */
export interface TabsState {
  tabsMenuList: TabsMenuProps[];
}
/* tabsMenuProps */
export interface TabsMenuProps {
  icon: string;
  title: string;
  path: string;
  name: string;
}
