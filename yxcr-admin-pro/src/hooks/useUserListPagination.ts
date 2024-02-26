import { ResultData } from "@/api/interface";

export interface Params {
  // 预期 Params 接口具有的属性
  [key: string]: any;
}

export function useUserListPagination<T>(
  /* eslint-disable no-unused-vars */
  apiFunction: (Params: Params) => Promise<ResultData>,
  initialParams?: Params,
) {
  // 分页数据
  const tableData = ref<T[]>([]);
  const currentPage = ref<number>(1);
  const pageSize = ref<number>(10);
  const pageTotal = ref<number>(0);

  // 请求数据
  const fetchData = async (params?: Params) => {
    // 如果没有提供params参数，或者params是null，那么使用initialParams作为默认值
    const effectiveParams = params ?? initialParams;
    const { data } = await apiFunction({
      ...effectiveParams,
      pageNum: currentPage.value,
      pageSize: pageSize.value,
    });
    tableData.value = data.records;
    pageTotal.value = data.total;
  };

  // 改变分页大小
  function handleCurrentChange(e: number) {
    currentPage.value = e;
    fetchData();
  }

  // 改变当前页数
  function handleSizeChange(e: number) {
    pageSize.value = e;
    fetchData();
  }

  return {
    tableData,
    currentPage,
    pageSize,
    pageTotal,
    handleSizeChange,
    handleCurrentChange,
    fetchData,
  };
}
