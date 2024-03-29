import { Menu } from "./interface";
import { MenuData } from "@/api/interface";

/**
 * @description 使用递归扁平化菜单，方便添加动态路由
 * @param {Array} menuList 菜单列表
 * @returns {Array}
 */
export function getFlatMenuList(
  menuList: Menu.MenuOptions[],
): Menu.MenuOptions[] {
  const newMenuList: Menu.MenuOptions[] = JSON.parse(JSON.stringify(menuList));
  return newMenuList.flatMap((item) => [
    item,
    ...(item.children ? getFlatMenuList(item.children) : []),
  ]);
}

/**
 * @description 使用递归找出所有面包屑存储到 pinia/vuex 中
 * @param {Array} menuList 菜单列表
 * @param {Array} parent 父级菜单
 * @param {Object} result 处理后的结果
 * @returns {Object}
 */
export const getAllBreadcrumbList = (
  menuList: Menu.MenuOptions[],
  parent = [],
  result: {
    [key: string]: any;
  } = {},
) => {
  for (const item of menuList) {
    result[item.path] = [...parent, item];
    if (item.children)
      getAllBreadcrumbList(item.children, result[item.path], result);
  }
  return result;
};

/**
 * @description 查找菜单的父id和子id
 * @param data
 * @param childId
 */
export function findParentIds(data: MenuData[], childId: number): number[] {
  // 显式地定义映射表的类型，其中数字键映射到 `Node`
  const idToNodeMap: {
    [key: number]: MenuData;
  } = {};

  // 一个递归函数，用于填充ID到节点的映射表
  function fillIdToNodeMap(node: MenuData) {
    idToNodeMap[node.id as number] = node;
    if (node.children) {
      node.children.forEach(fillIdToNodeMap);
    }
  }

  // 使用根节点数组初始化映射表
  data.forEach(fillIdToNodeMap);

  // 基于映射表，追踪从指定的子ID到它的所有父级ID
  const parentIds: number[] = [];
  let currentId = childId;

  while (idToNodeMap[currentId] && idToNodeMap[currentId].parentId !== null) {
    const parentId = idToNodeMap[currentId].parentId;
    if (typeof parentId === "number") {
      parentIds.unshift(parentId); // 将父ID添加到数组开头
      currentId = parentId;
    }
  }

  // 如果没有找到父级ID，返回包含单个 childId 的数组
  return parentIds.length === 0 ? [childId] : parentIds;
}

/**
 * @description 使用递归过滤出需要渲染在左侧菜单的列表 (需剔除 isHide == true 的菜单)
 * @param {Array} menuList 菜单列表
 * @returns {Array}
 * */
export function getShowMenuList(menuList) {
  const newMenuList: Menu.MenuOptions[] = JSON.parse(JSON.stringify(menuList));
  return newMenuList.filter((item) => {
    item.children?.length && (item.children = getShowMenuList(item.children));
    return item.meta?.visible != 0;
  });
}

/**
 * @description 获取浏览器默认语言
 * @returns {String}
 */
export function getBrowserLang() {
  const browserLang = navigator.language
    ? navigator.language
    : navigator.browserLanguage;
  let defaultBrowserLang = "";
  if (["cn", "zh", "zh-cn"].includes(browserLang.toLowerCase())) {
    defaultBrowserLang = "zh";
  } else {
    defaultBrowserLang = "en";
  }
  return defaultBrowserLang;
}

/**
 * 菜单数组转换为菜单树
 * @param items
 */
export function arrayToTree(items) {
  const rootItems = items.filter((item) => item.parentId === null); // 找到所有顶级节点

  const findChildren = (parent) => {
    parent.children = items.filter((item) => item.parentId === parent.id); // 找子节点
    if (parent.children.length) {
      parent.children.forEach(findChildren); // 递归找更深层的子节点
    } else {
      delete parent.children; // 如果没有子节点，则删除children属性
    }
  };

  rootItems.forEach(findChildren); // 初始化遍历根节点，递归建立树结构

  return rootItems;
}
