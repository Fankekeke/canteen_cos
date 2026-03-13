### 基于SpringBoot + Vue的食堂采购系统

团膳供应管理、采供对账系统、食堂库存周转

##### 基础档案与准入管理
###### 商户管理： 统一记录供应商与内部食堂档口信息，建立完整的合作档案，为采购溯源与结算提供基础依据。

###### 套餐管理： 预设标准化的食材或菜品组合方案，简化下单流程，实现食堂高频物资采购的快速配置与下发。

###### 耗材类别： 对生鲜、干货、餐具等物资进行科学分类，通过分级管理确保库房海量物品的检索与归档条理清晰。

##### 需求申请与审批决策
###### 制定申请物品： 商户或档口根据消耗情况发起物资领用申请，系统自动记录品种与数量，触发标准审批流程。

###### 申请记录/审批： 管理员实时审核各部门的物品需求单据，严格把控预算与计划，确保物资申领合规且有据可查。

###### 制定采购计划： 汇总审批通过的申请单，自动生成集约化采购计划，优化订货周期，从源头降低食堂运营成本。

##### 采购履约与仓储作业
###### 采购员采购： 采购人员根据核准计划执行物资采买，实时反馈执行状态，确保食材按质按量并及时供应到位。

###### 库房管理/明细： 数字化展示库位分布与实时库存，精确记录每一件物品的SKU属性，实现仓储空间的精细化利用。

###### 入库/出库记录： 严控收货验点与领用发放环节，系统自动更新动态库存，确保实物流转过程全程留痕、闭环管理。

##### 经营分析与数据监控
报表统计： 聚合采购金额、损耗率及出入库数据生成可视化报表，为食堂库存优化与经营成本控制提供决策决策。

#### 安装环境

JAVA 环境 

Node.js环境 [https://nodejs.org/en/] 选择14.17

Yarn 打开cmd， 输入npm install -g yarn !!!必须安装完毕nodejs

Mysql 数据库 [https://blog.csdn.net/qq_40303031/article/details/88935262] 一定要把账户和密码记住

redis

Idea 编译器 [https://blog.csdn.net/weixin_44505194/article/details/104452880]

WebStorm OR VScode 编译器 [https://www.jianshu.com/p/d63b5bae9dff]

#### 采用技术及功能

后端：SpringBoot、MybatisPlus、MySQL、Redis、
前端：Vue、Apex、Antd、Axios
报表：Spread.js

平台前端：vue(框架) + vuex(全局缓存) + rue-router(路由) + axios(请求插件) + apex(图表)  + antd-ui(ui组件)

平台后台：springboot(框架) + redis(缓存中间件) + shiro(权限中间件) + mybatisplus(orm) + restful风格接口 + mysql(数据库)

开发环境：windows10 or windows7 ， vscode or webstorm ， idea + lambok

商户管理，套餐管理，申请记录，库房管理，入库记录，出库记录，采购计划，报表统计，耗材类别，出入库物品明细

商户制定申请物品->管理员审批制定采购计划->采购员采购->入库->出库


#### 前台启动方式
安装所需文件 yarn install 
运行 yarn run dev

#### 后端启动方式

1.首先启动redis，进入redis目录终端。输入redis-server回车
2.导入sql文件，修改数据库与redis连接配置
3.idea中启动后端项目

#### 默认后台账户密码
[管理员]
admin
1234qwer

[采购员]
caigou
1234qwer

[商户]
lisi
1234qwer
#### 项目截图

|  |  |
|---------------------|---------------------|
|![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/6f691b423447b54af6dc384af6bfe9e.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/a57af3c4d496ae928d9f157b2334c19.png) |
|![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/6d2992d61f992b2606f1b2c4cc4f767.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/9993d37501b750568459485199a6042.png) |
|![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/f4f56352387c823e5c9165a77ab5f05.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/2552c7a1c079c84ad5d860b5270f502.png) |
|![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/da441d2c2cc91e673614555debe2e67.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/634bf67927f19ca2c35722438ef7555.png) |
|![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/cc7b251d167dde516dd888cb2dafe0a.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/61d611a5513d1b50756adee33bb86ce.png) |
|![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/a06652986cc2758f9995a3590ba1990.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/7ea8848d81a5741350caf73f5eb61ea.png) |
|![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/work/936e9baf53eb9a217af4f89c616dc19.png) |


#### 演示视频

暂无

#### 获取方式

Email: fan1ke2ke@gmail.com

WeChat: `Storm_Berserker`

`附带部署与讲解服务，因为要恰饭资源非免费，伸手党勿扰，谢谢理解😭`

> 1.项目纯原创，不做二手贩子 2.一次购买终身有效 3.项目讲解持续到答辩结束 4.非常负责的答辩指导 5.**黑奴价格**

> 项目部署调试不好包退！功能逻辑没讲明白包退！

#### 其它资源

[2025年-答辩顺利通过-客户评价🍜](https://berserker287.github.io/2025/06/18/2025%E5%B9%B4%E7%AD%94%E8%BE%A9%E9%A1%BA%E5%88%A9%E9%80%9A%E8%BF%87/)

[2024年-答辩顺利通过-客户评价👻](https://berserker287.github.io/2024/06/06/2024%E5%B9%B4%E7%AD%94%E8%BE%A9%E9%A1%BA%E5%88%A9%E9%80%9A%E8%BF%87/)

[2023年-答辩顺利通过-客户评价🐢](https://berserker287.github.io/2023/06/14/2023%E5%B9%B4%E7%AD%94%E8%BE%A9%E9%A1%BA%E5%88%A9%E9%80%9A%E8%BF%87/)

[2022年-答辩通过率100%-客户评价🐣](https://berserker287.github.io/2022/05/25/%E9%A1%B9%E7%9B%AE%E4%BA%A4%E6%98%93%E8%AE%B0%E5%BD%95/)

[毕业答辩导师提问的高频问题](https://berserker287.github.io/2023/06/13/%E6%AF%95%E4%B8%9A%E7%AD%94%E8%BE%A9%E5%AF%BC%E5%B8%88%E6%8F%90%E9%97%AE%E7%9A%84%E9%AB%98%E9%A2%91%E9%97%AE%E9%A2%98/)

[50个高频答辩问题-技术篇](https://berserker287.github.io/2023/06/13/50%E4%B8%AA%E9%AB%98%E9%A2%91%E7%AD%94%E8%BE%A9%E9%97%AE%E9%A2%98-%E6%8A%80%E6%9C%AF%E7%AF%87/)

[计算机毕设答辩时都会问到哪些问题？](https://www.zhihu.com/question/31020988)

[计算机专业毕业答辩小tips](https://zhuanlan.zhihu.com/p/145911029)

#### 接JAVAWEB毕设，纯原创，价格公道，诚信第一

`网站建设、小程序、H5、APP、各种系统 选题+开题报告+任务书+程序定制+安装调试+项目讲解+论文+答辩PPT`

More info: [悲伤的橘子树](https://berserker287.github.io/)

<p><img align="center" src="https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/%E5%90%88%E4%BD%9C%E7%89%A9%E6%96%99%E6%A0%B7%E5%BC%8F%20(3).png" alt="fankekeke" /></p>
