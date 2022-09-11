# kmm-explore-sample
KMM 探索例子

一个 KMM 入手例子。

## 截图

<img width="1175" alt="image" src="https://user-images.githubusercontent.com/48782433/189541908-a5700348-09b2-41f5-853e-19b3d3f55d5f.png">

## UI 开发

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

## 问题收集与相关链接

- iosArm64 找不到 MMKV module，需要单独把 MMKV 和 MMKV Core 导入到 Pod 中，可参考 ctripcorp/mmkv-kotlin 的 README
- Swift 与 Kotlin 类型使用对照表可参考 https://kotlinlang.org/docs/native-objc-interop.html#mappings
- 使用 Swift 的协程调用 Kotlin 侧的协程目前只支持在主线程调用，因此需要加上 `@MainActor` 注解
- iOS 平台使用 Ktor Client 需要在 gradle.properties 中添加 `kotlin.native.binary.memoryModel=experimental` 否则会抛出 `InvalidMutabilityException`, 相关链接: [youtrack](https://youtrack.jetbrains.com/issue/KTOR-4266/kotlinnativeconcurrentInvalidMutabilityException-mutation-attempt-of-frozen-ioktorclientrequestHttpRequestPipeline)

