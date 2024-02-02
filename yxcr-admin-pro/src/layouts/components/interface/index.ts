export interface MenuEntry {
  id: string;
  meta: {
    title: string;
  };
  children?: MenuEntry[]; // 可选，表示这个菜单项可能有子菜单项
}
