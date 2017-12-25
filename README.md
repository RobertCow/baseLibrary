# How To
## Step1: Add it in your root build.gradle at the end of repositories:
```java
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```  
## Step2: Add the dependency at your moudle build.gradle
```java
dependencies {
	        compile 'com.github.RobertCow:GithubDemo:1.0.5'
	}
```
