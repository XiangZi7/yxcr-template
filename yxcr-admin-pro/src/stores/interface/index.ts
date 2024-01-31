export type LayoutType = "vertical" | "classic" | "transverse" | "columns";

export type AssemblySizeType = "large" | "default" | "small";

export type LanguageType = "zh" | "en" | null;

export interface userModel {
  avatarUrl?: string;
  nickname?: string;
  userId?: number;
}

export interface UserState {
  userInfo: userModel;
  token: string;
}

// setting
export interface SettingState {
  loadLyric: boolean;
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
