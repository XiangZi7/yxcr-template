export interface MenuEntry {
  id: string;
  path: string;
  name: string;
  meta: {
    title: string;
    icon: string;
  };
  children?: MenuEntry[]; // 可选，表示这个菜单项可能有子菜单项
}
