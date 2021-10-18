# 北京工业大学，软件高级开发课程设计。

前端调用框架功能实现了图片上传，后端调用了Python脚本实现车位识别功能。

代码贼乱，也没有文档，实在是懒得写了果咩纳塞。

自定义代码目录为`custom`，后端目录`ruoyi-admin/src/main/java/com/ruoyi/custom`，前端目录`ruoyi-admin/src/main/resources/templates/custom。

主要功能位于`ruoyi-admin/src/main/java/com/ruoyi/custom/controller/CusPinitController.java`Controller中。

数据库初始化sql在`sql`目录下，直接运行即可。

代码中使用的停车场车位视频，识别模型和规模模型均在 [2021_winter](https://github.com/LambChuckEye/2021_winter) 中提供，直接复制到配置的文件地址即可。
