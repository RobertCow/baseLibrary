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
	        compile 'com.github.RobertCow:GithubDemo:1.0.4'
	}
```