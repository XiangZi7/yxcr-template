/* Menu */
// eslint-disable-next-line no-unused-vars,@typescript-eslint/no-unused-vars
export declare namespace Menu {
  export interface MenuOptions {
    id: string | number;
    path: string;
    name: string;
    component?: string | (() => Promise<unknown>);
    redirect?: string;
    meta: MetaProps;
    children?: MenuOptions[];
  }

  export interface MetaProps {
    icon: string;
    title: string;
    activeMenu?: string;
    isLink?: string;
    isHide: boolean;
    isFull: number | boolean;
    isAffix: boolean;
    isKeepAlive: boolean;
  }
}
