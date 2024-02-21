// 定义接口或类型用于apiFunction的返回类型，这里假设它返回一个分页数据对象
interface ApiResponse<T> {
  data: {
    records: T[];
    total: number;
  };
}

interface Params {
  pageNum?: number;
  pageSize?: number;

  [key: string]: any;
}

export function useUserListPagination<T>(
  apiFunction: () => Promise<ApiResponse<T>>,
  initialParams: Params,
) {
  // 分页数据
  const tableData = ref<T>([]);
  const currentPage = ref<number>(1);
  const pageSize = ref<number>(10);
  const pageTotal = ref<number>(0);

  // 请求数据
  const fetchData = async (params: Params = initialParams) => {
    const { data } = await apiFunction({
      ...params,
      pageNum: currentPage.value,
      pageSize: pageSize.value,
    });
    tableData.value = data.records;
    pageTotal.value = data.total;
  };

  // 改变分页大小
  function handleCurrentChange(e) {
    currentPage.value = e;
    fetchData();
  }

  // 改变当前页数
  function handleSizeChange(e) {
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
