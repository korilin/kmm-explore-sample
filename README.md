# kmm-explore-sample
KMM 探索例子

一个 KMM 入手例子。

## 截图

<img width="1025" alt="image" src="https://user-images.githubusercontent.com/48782433/189543958-a02377d6-30c3-4ff5-b87c-88f8d65e18ee.png">

## 服务端项目

一个的 Kotlin + WebFlux 项目，没有数据库，IDEA 导入等依赖加载完就可以跑了，端口为 8888

## 客户端 UI

- Android: Jetpack Compose
- iOS: Swift UI

调用 shared 模块的逻辑均放在 ViewModel 中。

## 本地存储

两端都是用 MMKV，相关存储逻辑写在 shared 模块 [MessageStorageWorker](ExploreSample/shared/src/commonMain/kotlin/com/korilin/kmm/explore/datasource/storage/MessageStorageWorker.kt)

[ctripcorp/mmkv-kotlin](https://github.com/ctripcorp/mmkv-kotlin)

## 网络请求

shared/commonMain 定义了提供需要请求能力的接口 [NetRequester](ExploreSample/shared/src/commonMain/kotlin/com/korilin/kmm/explore/datasource/network/NetRequester.kt)

- shared/androidMain: 使用 OkHttp 向 commonMain 提供网络请求能力
- shared/iosMain: 使用 Ktor Client 向 commonMain 提供网络请求能力

## IOS 的 MessageRepository

由于 Swift 接入 Kotlin 部分类型不太方便转换，例如 Kotlin 的 `Result` 在 Swift 接收的是一个 Optional 的 Success 或者 Failure 类型，这两个类型在 Swift 中找不到，因此在 `shared/iosMain` 中加了个 Repository 来把相关类型直接返回到 Swift 中。

## 逻辑整理

1. 点击 PostMessage 后使用 MessagePoster 发送操作的系统和输入的文本给后端
2. 后端返回拼接系统和输入信息的文本、当前时间和一张随机选择的图片地址
3. 客户端收到响应将内容作为一条记录插入到列表展示出来
4. 同时使用 MessageStorageWorker 将该记录存到本地
5. 下次启动使用 MessageStorageWorker 从本地获取之前的记录列表

<img width="1182" alt="image" src="https://user-images.githubusercontent.com/48782433/189545471-af64c116-cbb9-4589-aad3-5424abb73002.png">

## 问题收集与相关链接

- KMM plugin can't grab Xcode schemes: https://youtrack.jetbrains.com/issue/KT-41691
- Gradle 出现 No value present: https://youtrack.jetbrains.com/issue/KTIJ-20110
- iosArm64 找不到 MMKV module，需要单独把 MMKV 和 MMKV Core 导入到 Pod 中，可参考 ctripcorp/mmkv-kotlin 的 README
- Swift 与 Kotlin 类型使用对照表可参考 https://kotlinlang.org/docs/native-objc-interop.html#mappings
- 使用 Swift 的协程调用 Kotlin 侧的协程目前只支持在主线程调用，因此需要加上 `@MainActor` 注解
- iOS 平台使用 Ktor Client 需要在 gradle.properties 中添加 `kotlin.native.binary.memoryModel=experimental` 否则会抛出 `InvalidMutabilityException`, 相关链接: https://youtrack.jetbrains.com/issue/KTOR-4266/

