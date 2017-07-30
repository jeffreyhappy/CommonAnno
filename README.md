# CommonAnno

## 支持注解
### R资源相关 
1. @BindLayout(int default -1)
> 取默认值-1时，Layout Id资源从变量名获得，此时需要设置R类
> 取用户设置的id时，资源id已确定，不需要设置R类
2. @BindView
> 取默认值-1时，View Id资源从变量名获得，此时需要设置R类
> 取用户设置的id时，资源id已确定，不需要设置R类
3. @BindLayout2(String)
> Layout Id资源从变注解值获得，此时需要设置R类
4. @OnClick @Click2
5. @OnLongClick @OnLongClick2
6. @OnItemClick @OnItemClick2

```Java
  Injectors.get().injectActivity(this);
  Injectors.get().setRClass(R.class).injectActivity(this);
```

### 路由相关
1. @BindRoute(String)

```Java
  Injectors.get().loadRouteInfos("app", "a_module", "b_module");
  Injectors.get().buildRouter("jin.test.ui.appsecond?a=1&b=1.1f&c=1.2d&d=str")
                .putInt("a", 2)
                .putFloat("b", 2.1f)
                .putDouble("c", 2.2d)
                .putString("d", "str2")
                .navigate(this);
```

## Module支持用户自定义设置，ModuleName 必须独一无二
## Module用户不设置，那么从默认提供的Module池中获取，默认提供20个Name池
```Gradle
android {

    ...

    defaultConfig {
    
        ...

        javaCompileOptions {
            annotationProcessorOptions {
                arguments = [ moduleName : project.getName() ]
            }
        }

    }

}
```
