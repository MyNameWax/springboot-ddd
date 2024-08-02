<h1 align="center">基于DDD架构的Web管理系统</h1>

<br/>


<br/>

## 项目说明

本项目使用 **DDD**架构进行开发,服务端使用**SpringBoot + MyBatisPlus + ElasticSearch + Canal**来实现,数据库采用了**MySQL、Redis**

## 说明

> 本项目是作者在学习DDD架构中的练手项目，仅供学习参考，**项目中有任何问题,请提issue感谢各位**

## 技术栈

### 后端

**SpringBoot + MyBatis + Redis + ElasticSearch + Canal**


## 赞助

如果此项目对你确实有帮助，欢迎给我打赏一杯咖啡～😄

<img src="./img/WxChatPay.jpg" height="300px"/>

<br/>

## 联系方式

**1、邮箱📮：[waxjava04@163.com](mailto:waxjava04@163.com)**

> **定位主要是临时帮大家解决问题，不发表其他东西，有疑问可以临时联系。**


<br/>

## License

Copyright (c) 2024 wax 

当然可以！以下是一个简单的Java程序，实现冒泡排序算法：

```java
public class BubbleSort {

    public static void main(String[] args) {
        int[] array = {
            64,
            34,
            25,
            12,
            22,
            11,
            90
        };

        System.out.println("排序前数组：");
        printArray(array);

        bubbleSort(array);

        System.out.println("\n排序后数组：");
        printArray(array);
    }
    // 冒泡排序方法
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j
                ] > arr[j + 1
                ]) {
                    // 交换 arr[j] 和 arr[j+1]
                    int temp = arr[j
                    ];
                    arr[j
                    ] = arr[j + 1
                    ];
                    arr[j + 1
                    ] = temp;
                }
            }
        }
    }
    // 打印数组的方法
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i
            ] + " ");
        }
        System.out.println();
    }
}
```

