
## 1. 下载已编译好的PCL库


```
链接: https://pan.baidu.com/s/1wuLVRfEyOCg5D4hUQITuZA 提取码: 7yx3 
```

## 2. 解压到本地

```
本地地址（举例）：/Users/xxx/Develop/pcl/build
```

## 3. 使用pcl本地目录 替换CMakeLists.txt中的 $ENV{PCL_ANDROID} 值


> 例子：

```
set(PCL_BUILD $ENV{PCL_ANDROID})
替换为
set(PCL_BUILD "/Users/xxx/Develop/pcl/build")
```