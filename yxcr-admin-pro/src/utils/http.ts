import axios, {
  AxiosInstance,
  AxiosResponse,
  InternalAxiosRequestConfig,
} from "axios";
import NProgress from "@/config/nprogress";

export interface CustomAxiosRequestConfig extends InternalAxiosRequestConfig {
  noLoading?: boolean;
}

// 创建axios实例并添加类型注解
const instance: AxiosInstance = axios.create({
  baseURL: import.meta.env.VITE_API_URL,
  timeout: 10000,
  withCredentials: true,
});

// 请求拦截器
instance.interceptors.request.use(
  (config: CustomAxiosRequestConfig) => {
    const userStore = useUserStore();
    // 开启进度条
    NProgress.start();
    // 检查token
    const token = userStore.userInfo.token;
    if (token != undefined && token != "") {
      config.headers.Authorization = token;
    }
    return config;
  },
  (error): Promise<any> => Promise.reject(error),
);

// 响应拦截器
instance.interceptors.response.use(
  (response: AxiosResponse) => {
    // 进度条结束
    NProgress.done();
    // 返回完整的response对象，不仅是data部分
    return response.data;
  },
  (error) => {
    // 响应错误时也结束进度条
    NProgress.done();
    // 继续 reject 错误，这样调用 http 请求的代码就能捕获到异常
    return Promise.reject(error);
  },
);

// 封装get方法
// 函数以正确地使用泛型类型
export const httpGet = <T>(url: string, params?: object): Promise<T> => {
  return instance.get(url, { params });
};

// 封装post方法
export const httpPost = <T>(
  url: string,
  data?: object,
  _object = {},
): Promise<T> => {
  return instance.post(url, data, _object);
};
