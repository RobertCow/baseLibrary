# How To
## Step1: Add it in your root build.gradle at the end of repositories:
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```  
## Step2: Add the dependency at your moudle build.gradle
```
dependencies {
	        compile 'com.github.RobertCow:GithubDemo:1.1.1'
	}
```

## Step3: implement BaseApplication and evaluate titleColor
```
public class App extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        titleColor = getResources().getColor(R.color.colorAccent); 标题栏颜色
    }
}

```

## Step4: Change your AndroidManifest.xml 
```
<application
          android:name=".APP"
          ...
        >
  </application>
```
