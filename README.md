# Kotlin Multiplatform Demo

## 运行
- Desktop
```
.\gradlew.bat run
```
- Web
```
.\gradlew.bat wasmJsRun
```

## 打包
- Desktop(`.\composeApp\build\compose\binaries\main\msi\`)
```
.\gradlew.bat packageDistributionForCurrentOS
```
- Web (`.\composeApp\build\dist\wasmJs\productionExecutable\`)
```
.\gradlew.bat wasmJsBrowserDistribution
```

## 主题
[Material Theme Builder](https://m3.material.io/theme-builder#/custom)
> 用导出的两个文件替换`ui/theme`下的两个文件，注意修改源文件自动生成的`package`语句

## 其他
[Kotlin Multiplatform Wizard](https://kmp.jetbrains.com/)
> Kotlin Multiplatform 初始项目生成